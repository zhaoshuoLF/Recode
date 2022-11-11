package com.zhaoshuo.recode.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
    private var adapter: RecordAdapter = RecordAdapter { item -> adapterOnClick(item) }

    private fun adapterOnClick(item: RecordResponse.Item) {
        val bundle = Bundle()
        bundle.putSerializable("item", item)
        findNavController().navigate(R.id.action_blankFragment_to_recordInfoFragment, bundle)
    }

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

        viewModel.recordList.observe(viewLifecycleOwner) {
            it?.let {
                adapter.recordList.clear()
                adapter.recordList.addAll(it)
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.groupList.observe(viewLifecycleOwner) {
            initTabLayout(it)
            viewModel.setCStatus(tabLayout.getTabAt(0)?.text.toString())
        }
    }

    private fun init(view: View) {
        tabLayout = view.findViewById(R.id.tabLayout)
        list = view.findViewById<RecyclerView>(R.id.recodeList)
    }

    private fun initTabLayout(items: Map<String?, List<RecordResponse.Item>>) {
        for (key in items.keys) {
            tabLayout.addTab(tabLayout.newTab().setText(key))
        }

        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.setCStatus(tab?.text.toString())
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }
}