package com.sample.thingyselector.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.thingyselector.R
import com.sample.thingyselector.databinding.ActivityThingsBinding
import com.sample.thingyselector.databinding.ItemThingBinding
import com.sample.thingyselector.models.Thing
import com.sample.thingyselector.ui.adapters.ModelBindingAdapter
import com.sample.thingyselector.ui.handlers.OnModelClickListener
import com.sample.thingyselector.viewmodels.ThingViewModel
import kotlinx.android.synthetic.main.activity_things.*


class ThingsActivity : AppCompatActivity() {

    private lateinit var dataViewBinding: ActivityThingsBinding
    private lateinit var viewModel: ThingViewModel
    private lateinit var listAdapter: ModelBindingAdapter<Thing, ItemThingBinding>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_things)


        viewModel = ViewModelProvider(this).get(ThingViewModel::class.java)
            title = "Thingy Selector"

            val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this@ThingsActivity)
            recyclerView.layoutManager = layoutManager

        viewModel.addSampleData()
        viewModel.thingsList?.observe(this, {

            listAdapter = ModelBindingAdapter(it.toMutableList(), R.layout.item_thing){ binding, model ->
                binding.thing = model
                binding.listener = object : OnModelClickListener<Thing> {
                    override fun onClick(model: Thing) {
                        model.selected = !model.selected
                        viewModel.updateThing(model)
                    }
                }
            }
            dataViewBinding.recyclerView.adapter = listAdapter
            dataViewBinding.recyclerView.layoutManager = LinearLayoutManager(this@ThingsActivity)
        })

        dataViewBinding.tvNext.setOnClickListener {
            val intent = Intent(this@ThingsActivity, SelectedThingyActivity::class.java)
            startActivity(intent)
        }
    }
}