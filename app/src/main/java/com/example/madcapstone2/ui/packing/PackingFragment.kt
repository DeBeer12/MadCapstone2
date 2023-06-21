package com.example.madcapstone2.ui.packing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import androidx.core.view.children
import com.example.madcapstone2.R
import com.example.madcapstone2.db.Item
import com.example.madcapstone2.db.ItemAdapter
import com.example.madcapstone2.db.ItemRepository
import com.example.madcapstone2.ui.packing.PackingViewModel

class PackingFragment : Fragment() {

    private lateinit var dashboardViewModel: PackingViewModel
    private val items = arrayListOf<Item>()
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemRepository: ItemRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(PackingViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_packing, container, false)
        itemAdapter = ItemAdapter(items){selected, total -> root.findViewById<TextView>(R.id.packingText).text = "There are $selected items left on packing list"; }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnClear  = view.findViewById<Button>(R.id.packingButton)
        val recyclerView: RecyclerView = view.findViewById(R.id.rvItems)
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this.itemAdapter
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        itemRepository = ItemRepository(requireContext())
        getItemsFromDatabase()
        btnClear.setOnClickListener {clearBoxes(view)}
    }

    private fun getItemsFromDatabase() {
        mainScope.launch {
            val storedItems = withContext(Dispatchers.IO) {
                itemRepository.getAllItems()
            }
            if(storedItems.isEmpty()){
                itemRepository.insertItem(Item(
                    itemText = "Raincoat",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Sunscreen",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Food",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Hat",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Tent",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Water",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Beer",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Marshmallows",
                ))
                itemRepository.insertItem(Item(
                    itemText = "Phone",
                ))

                items.addAll(storedItems)
                itemAdapter.notifyDataSetChanged()
            } else {
                items.clear()
                items.addAll(storedItems)
                itemAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun clearBoxes(view: View) {
        for (item in view.findViewById<RecyclerView>(R.id.rvItems).children) {
            item.findViewById<CheckBox>(R.id.cbItem).isChecked = false
        }
        view.findViewById<TextView>(R.id.packingText).text = "There are 0 items left on packing list";
        itemAdapter.totalCheckedItems = 0;
        itemAdapter.notifyDataSetChanged()
    }

    private fun setText(view: View) {
        Log.d("tag", "sadkfjaksld")
        var count = 0
        for (item in view.findViewById<RecyclerView>(R.id.rvItems).children) {
            if(item.findViewById<CheckBox>(R.id.cbItem).isChecked) {
                count++
            }
//            item.findViewById<CheckBox>(R.id.cbItem).isChecked = false
        }
        view.findViewById<TextView>(R.id.packingText).text = "There are $count items left on packing list";
        itemAdapter.notifyDataSetChanged()
    }
}