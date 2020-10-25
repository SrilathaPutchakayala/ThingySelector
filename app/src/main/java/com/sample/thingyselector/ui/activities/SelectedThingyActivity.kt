package com.sample.thingyselector.ui.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.thingyselector.R
import com.sample.thingyselector.databinding.ActivitySelectedThingyBinding
import com.sample.thingyselector.databinding.ItemSelectedThingBinding
import com.sample.thingyselector.models.Thing
import com.sample.thingyselector.ui.adapters.ModelBindingAdapter
import com.sample.thingyselector.viewmodels.ThingViewModel
import java.util.*

class SelectedThingyActivity : AppCompatActivity() {

    private lateinit var dataViewBinding: ActivitySelectedThingyBinding
    private lateinit var viewModel: ThingViewModel
    private lateinit var listAdapter: ModelBindingAdapter<Thing, ItemSelectedThingBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_selected_thingy)

        viewModel = ViewModelProvider(this).get(ThingViewModel::class.java)

        viewModel.selectedThingsList?.observe(this, {

            val selectedList = it.toMutableList()
            if (selectedList.size > 1){
                val randomThing = selectedList[Random().nextInt(selectedList.size)]

                dataViewBinding.tvRandomThing.text = randomThing.text
                selectedList.remove(randomThing)

                listAdapter =
                    ModelBindingAdapter(selectedList, R.layout.item_selected_thing) { binding, model ->
                        binding.thing = model
                    }
                dataViewBinding.recyclerView.visibility = View.VISIBLE
                dataViewBinding.recyclerView.adapter = listAdapter
                dataViewBinding.recyclerView.layoutManager =
                    LinearLayoutManager(this@SelectedThingyActivity)
            }else{
                dataViewBinding.recyclerView.visibility = View.GONE
                dataViewBinding.tvRandomThing.text = it.toMutableList().firstOrNull()?.text
            }

        })
    }
}
