package `in`.tutorial.sevenminworkout

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.sevenminworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items:ArrayList<ExcerciseModel>):
    RecyclerView.Adapter<ExerciseStatusAdapter.MainViewHolder>() {
    inner class MainViewHolder(val itemBinding:ItemExerciseStatusBinding):
        RecyclerView.ViewHolder(itemBinding.root) {
        val tvItem = itemBinding.tvItem
        fun itemBinding(exercise: ExcerciseModel){
            tvItem.text = exercise.getId().toString()
            when{
                exercise.getIsSelected()->{
                    tvItem.background = ContextCompat.getDrawable(this.itemView.context,
                        R.drawable.item_circular_thin_color_accent_border)
                    tvItem.setTextColor(Color.parseColor("#212121"))
                }
                exercise.getIsCompleted()->{
                    tvItem.background = ContextCompat.getDrawable(this.itemView.context,
                        R.drawable.item_circular_color_accent_bck)
                    tvItem.setTextColor(Color.parseColor("#FFFFFF"))
                }
                else ->{
                    tvItem.background = ContextCompat.getDrawable(this.itemView.context,
                        R.drawable.item_circular_color_gray_background)
                    tvItem.setTextColor(Color.parseColor("#212121"))
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context),
                parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemBinding(items[position])
    }
}