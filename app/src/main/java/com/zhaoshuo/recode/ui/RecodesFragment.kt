package com.zhaoshuo.recode.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.zhaoshuo.recode.R
import com.zhaoshuo.recode.logic.model.RecordResponse

class RecodesFragment : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var list: RecyclerView

    private val viewModel: RecodesViewModel by viewModels()
    private var adapter: RecordAdapter = RecordAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recodes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        val layoutManager = LinearLayoutManager(activity)
        list.layoutManager = layoutManager
        list.adapter = adapter
        viewModel.getRecodeList()
        viewModel.list.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                initTabLayout(data.items)
                adapter.recordList = data.items as MutableList<RecordResponse.Item>
                adapter.notifyDataSetChanged()
            }
        }
    }

    private fun init(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout)
        list = view.findViewById<RecyclerView>(R.id.recodeList)
    }

    private fun initTabLayout(items: List<RecordResponse.Item>?) {
        val groupRecords = items?.groupBy { it.status }
        if (groupRecords != null) {
            tabLayout.addTab(tabLayout.newTab().setText("ALL"))
            for (key in groupRecords.keys) {
                tabLayout.addTab(tabLayout.newTab().setText(key))
            }
            tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val status = tab?.text
                    if (status == "ALL") {
                        adapter.recordList =
                            viewModel.list.value?.items as MutableList<RecordResponse.Item>
                    } else {
                        adapter.recordList =
                            groupRecords.get(status) as MutableList<RecordResponse.Item>
                    }
                    adapter.notifyDataSetChanged()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}