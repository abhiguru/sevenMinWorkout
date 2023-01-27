package `in`.tutorial.sevenminworkout

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import `in`.tutorial.sevenminworkout.databinding.ActivityCompleteBinding
import `in`.tutorial.sevenminworkout.databinding.DialogCustomBackConfirmationBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ExerciseCompleteActivity : AppCompatActivity() {
    var binding:ActivityCompleteBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompleteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarComplete)
        if(supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarComplete?.setNavigationOnClickListener {
            onBackPressed()
        }
        binding?.flEnd?.setOnClickListener {
            finish()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val historyDao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(historyDao)
    }
    private fun addDateToDatabase(historyDao: HistoryDao){
        lifecycleScope.launch {
            val c = Calendar.getInstance()
            val datetime = c.time
            Log.e("Date: ", " $datetime")
            val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
            val date = sdf.format(datetime)
            val historyEntity = HistoryEntity(date = date)
            historyDao.insert(historyEntity)
            Toast.makeText(this@ExerciseCompleteActivity, "Record Saved", Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}