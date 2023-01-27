package `in`.tutorial.sevenminworkout

import android.widget.ImageView

class ExcerciseModel (
    private var id:Int,
    private var name: String,
    private var image: Int,
    private var isCompleted:Boolean,
    private var isSelected:Boolean
    ){
    fun getId():Int{
        return id
    }
    fun setId(value:Int){
        this.id = value
    }
    fun getName():String{
        return name
    }
    fun setName(value:String){
        this.name = value
    }
    fun getImage():Int{
        return image
    }
    fun setImage(value:Int){
        this.image = value
    }
    fun getIsCompleted():Boolean{
        return isCompleted
    }
    fun setIsCompleted(value:Boolean){
        this.isCompleted = value
    }
    fun getIsSelected():Boolean{
        return isSelected
    }
    fun setIsSelected(value:Boolean){
        this.isSelected = value
    }
}