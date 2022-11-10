package com.zhaoshuo.recode.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zhaoshuo.recode.R
import com.zhaoshuo.recode.databinding.FragmentRecodesBinding
import com.zhaoshuo.recode.logic.model.RecordResponse

class RecodesFragment : Fragment() {

    private val viewModel: RecodesViewModel by viewModels()
    private  var adapter: RecordAdapter =RecordAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_recodes,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        val list = view.findViewById<RecyclerView>(R.id.recodeList)
        list.layoutManager = layoutManager
        list?.adapter = adapter
        viewModel.getRecodeList()
        viewModel.list.observe(viewLifecycleOwner) {
            adapter.recordList = it.items as MutableList<RecordResponse.Item>
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}