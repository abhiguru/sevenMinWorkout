package `in`.tutorial.sevenminworkout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import `in`.tutorial.sevenminworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(val histList: ArrayList<String>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(binding: ItemHistoryRowBinding) :RecyclerView.ViewHolder(binding.root){
        val llHistoryItemMain = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return histList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date : String = histList[position]
        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date
        if(position%2 == 0){
            holder.llHistoryItemMain?.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.colorLightGrey))
        }else{
            holder.llHistoryItemMain?.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.white))
        }
    }
}