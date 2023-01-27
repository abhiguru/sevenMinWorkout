package `in`.tutorial.sevenminworkout

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import `in`.tutorial.sevenminworkout.databinding.ActivityExerciseBinding
import `in`.tutorial.sevenminworkout.databinding.ActivityMainBinding
import `in`.tutorial.sevenminworkout.databinding.DialogCustomBackConfirmationBinding
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var binding:ActivityExerciseBinding? = null
    private var restTimer:CountDownTimer? = null
    private var restProgress:Int = 0
    private var exerciseTimer:CountDownTimer? = null
    private var excerciseProgress:Int = 0
    private var excerciseList:ArrayList<ExcerciseModel>? = null
    private var currentExercisePos = -1
    private var tts:TextToSpeech? = null
    private var player:MediaPlayer? = null
    private var exerciseAdapter: ExerciseStatusAdapter? = null
    private var restTimerDuration:Long = 1
    private var exerciseTimerDuration:Long = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarExercise)
        if(supportActionBar != null){
             supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }
        excerciseList = Constants.defaultExerciseList()
        setupRestView()
        tts = TextToSpeech(this, this)
        setupExerciseStatusRecyclerView()
        binding?.toolbarExercise?.setNavigationOnClickListener {
            customProgressDialogFunction()
        }
    }

    override fun onBackPressed() {
        customProgressDialogFunction()
        //super.onBackPressed()
    }
    private fun customProgressDialogFunction(){
        val customProgressDialog = Dialog(this)
        val dialogBinding = DialogCustomBackConfirmationBinding.inflate(layoutInflater)
        customProgressDialog.setContentView(dialogBinding.root)
        customProgressDialog.setCanceledOnTouchOutside(false)
        dialogBinding.tvYes?.setOnClickListener {
            this@ExerciseActivity.finish()
            customProgressDialog.hide()
        }
        dialogBinding.tvNo?.setOnClickListener {
            customProgressDialog.hide()
        }
        customProgressDialog.show()
    }

    private fun setupExerciseStatusRecyclerView(){
        binding?.rvExerciseStatus?.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        exerciseAdapter = ExerciseStatusAdapter(excerciseList!!)
        binding?.rvExerciseStatus?.adapter = exerciseAdapter

    }

    private fun setupExerciseView(){
        if(exerciseTimer!=null){
            exerciseTimer?.cancel()
            excerciseProgress = 0
        }
        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.INVISIBLE
        binding?.tvUpcomingLabel?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.ivImage?.setImageResource(excerciseList!![currentExercisePos].getImage())
        binding?.tvExerciseName?.text = excerciseList!![currentExercisePos].getName()
        speakOut(binding?.tvExerciseName?.text.toString())
        setExerciseProgressBar()
    }
    private fun setupRestView(){
        try {
            val soundUri = (Uri.parse(
                "android.resource://in.tutorial.sevenminworkout/"+R.raw.press_start))
            player = MediaPlayer.create(applicationContext, soundUri)
            player?.isLooping = false
            player?.start()
        }catch (e:Exception){

        }
        if(restTimer!=null){
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvUpcomingExerciseName?.visibility = View.VISIBLE
        binding?.tvUpcomingLabel?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Get Ready for"

        excerciseList!![currentExercisePos + 1]?.let {
            binding?.tvUpcomingExerciseName?.text = it.getName()
        }
        val speakString = "Get Ready for "+binding?.tvUpcomingExerciseName?.text.toString()
        speakOut(speakString)
        setRestProgressBar()
    }
    private fun speakOut(text:String){
        tts?.speak(text,TextToSpeech.QUEUE_FLUSH, null, "")
    }
    private fun setExerciseProgressBar(){
        binding?.exerciseProgressBar?.progress = excerciseProgress
        exerciseTimer = object : CountDownTimer(exerciseTimerDuration*1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                binding?.exerciseProgressBar?.progress = 30 - excerciseProgress
                binding?.tvExerciseTimer?.text = (30 - excerciseProgress).toString()
            }
            override fun onFinish() {
                //Toast.makeText(this@ExerciseActivity, "Workout Complete.", Toast.LENGTH_SHORT).show()
                if(currentExercisePos < excerciseList?.size!! - 1){
                    excerciseList!![currentExercisePos].setIsSelected(false)
                    excerciseList!![currentExercisePos].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()
                    setupRestView()
                }else{
                    val intent = android.content.Intent(this@ExerciseActivity,
                        ExerciseCompleteActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(this@ExerciseActivity, "Workout Complete!", Toast.LENGTH_SHORT).show()
                }

            }
        }.start()
    }
    private fun setRestProgressBar(){
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.restProgressBar?.progress = restProgress
        restTimer = object : CountDownTimer(restTimerDuration*1000, 1000){
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.restProgressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }
            override fun onFinish() {
                Toast.makeText(this@ExerciseActivity, "Start Workout!", Toast.LENGTH_SHORT).show()
                currentExercisePos++
                excerciseList!![currentExercisePos].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()
                setupExerciseView()
            }
        }.start()
    }
    override fun onDestroy() {
        super.onDestroy()
        restTimer?.let {
            restTimer?.cancel()
        }
        exerciseTimer?.let {
            exerciseTimer?.cancel()
        }
        if(tts!=null){
            tts?.stop()
            tts?.shutdown()
        }
        if(player!=null){
            player?.stop()
        }
        excerciseProgress = 0
        restProgress = 0
        binding = null
    }

    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.ENGLISH)
            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                //log
            }
        }else{
            //log
        }
    }
}