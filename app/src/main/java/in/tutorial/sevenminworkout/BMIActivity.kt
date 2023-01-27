package `in`.tutorial.sevenminworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import `in`.tutorial.sevenminworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {
    private var binding:ActivityBmiactivityBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        setSupportActionBar(binding?.toolbarBmiActivity)
        if(supportActionBar!=null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "Calculate BMI"
        }
        binding?.toolbarBmiActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
        setSupportActionBar(binding?.toolbarBmiActivity)
        binding?.bindCalc?.setOnClickListener {
            if(validateMetricUnits()){
                val heightValue : Float = binding?.etMetricUnitHeight?.text.toString().toFloat()/100
                val weightValue : Float = binding?.etMetricUnitWeight?.text.toString().toFloat()
                val bmi = ((weightValue/heightValue)/heightValue)
                displayBMIResult(bmi)
            }else if(validateUSUnits()){
                val heightFeetValue : Float = binding?.etUSUnitHeightFeet?.text.toString().toFloat() * 30.48f
                val heightInchesValue : Float = binding?.etUSUnitHeightInches?.text.toString().toFloat() * 2.54f
                val heightValue = (heightFeetValue + heightInchesValue)/100;
                val weightValue  : Float = binding?.etUSUnitWeight?.text.toString().toFloat() / 2.205f
                val bmi = ((weightValue/heightValue)/heightValue)
                displayBMIResult(bmi)
            }else{

            }
        }
        binding?.rgUnits?.setOnCheckedChangeListener { group, checkedId ->
            if(group.checkedRadioButtonId == binding?.rbMetricUnits?.id){
                binding?.mertricInputLayout?.visibility = View.VISIBLE
                binding?.USInputLayout?.visibility = View.INVISIBLE
            }else if(group.checkedRadioButtonId == binding?.rbAmericanUnits?.id){
                binding?.mertricInputLayout?.visibility = View.INVISIBLE
                binding?.USInputLayout?.visibility = View.VISIBLE
            }
        }
    }

    private fun displayBMIResult(bmi:Float){
       val bmiLabel:String
       val bmiDesc:String
       if(bmi.compareTo(15f)<=0){
           bmiLabel = "Very underweight"
           bmiDesc = "Oops! You really need to take better care"
       }else if(bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <=0){
           bmiLabel = "Severely underweight"
           bmiDesc = "Oops! You really need to take better care"
       }else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <=0){
           bmiLabel = "Underweight!"
           bmiDesc = "Oops! You really need to take better care"
       }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <=0){
           bmiLabel = "Normal!"
           bmiDesc = "Oops! You really take care of yourself"
       } else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <=0){
           bmiLabel = "Overweight!"
           bmiDesc = "Oops! You really take care of yourself"
       }else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <=0){
           bmiLabel = "Obese Class"
           bmiDesc = "Oops! You really take care of yourself"
       }else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <=0){
           bmiLabel = "Severely Obese Class"
           bmiDesc = "Oops! You really take care of yourself"
       }else{
           bmiLabel = "Severely Severely Obese Class"
           bmiDesc = "Oops! You really take care of yourself"
       }
       val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()
       binding?.llDisplayBMIResult?.visibility = View.VISIBLE
       binding?.tvBMIValue?.text = bmiValue
       binding?.tvBMIType?.text = bmiLabel
       binding?.tvBMIDescription?.text = bmiDesc


   }
    private fun validateUSUnits():Boolean{
        var isValid = true
        if(binding?.etUSUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUSUnitHeightFeet?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etUSUnitHeightInches?.text.toString().isEmpty()){
            isValid = false
        }
        return isValid
    }
    private fun validateMetricUnits():Boolean{
       var isValid = true
       if(binding?.etMetricUnitWeight?.text.toString().isEmpty()){
           isValid = false
       }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
           isValid = false
       }
       return isValid
    }
}