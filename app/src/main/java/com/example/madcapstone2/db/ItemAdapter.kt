package com.example.madcapstone2.db

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.madcapstone2.R
import com.example.madcapstone2.databinding.ItemItemBinding

class ItemAdapter(private val items: List<Item>, val onCheckChanged: (selected: Int, total: Int) -> Unit) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        var totalCheckedItems = 0;

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val binding = ItemItemBinding.bind(itemView)

            fun databind(item: Item) {
                binding.cbItem.text = item.itemText
            }
        }

        /**
         * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
         */
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            return ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_item, parent, false)
            )
        }

        /**
         * Returns the size of the list
         */
        override fun getItemCount(): Int {
            return items.size
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         */
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.findViewById<CheckBox>(R.id.cbItem).setOnClickListener{
                if (holder.itemView.findViewById<CheckBox>(R.id.cbItem).isChecked) {
                    totalCheckedItems++
                } else {
                    totalCheckedItems--
                }
                Log.d("tag",totalCheckedItems.toString())
                onCheckChanged(totalCheckedItems, items.size)
            }
            holder.databind(items[position])
        }
}