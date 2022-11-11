package com.zhaoshuo.recode.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.zhaoshuo.recode.R
import com.zhaoshuo.recode.logic.model.RecordResponse

class RecordInfoFragment : Fragment() {
    private lateinit var statusInfo: TextView
    private lateinit var hospitalInfo: TextView
    private lateinit var specialityInfo: TextView
    private lateinit var doctorInfo: TextView
    private lateinit var patientInfo: TextView
    private lateinit var genderInfo: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_record_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        val item: RecordResponse.Item = arguments?.get("item") as RecordResponse.Item
        statusInfo.text = item.status
        hospitalInfo.text = item.practitioner?.hospitalName ?: ""
        specialityInfo.text = item.practitioner?.priceType ?: ""
        doctorInfo.text = item.practitioner?.name ?: ""
        patientInfo.text = item.patient?.fullName ?: ""
        genderInfo.text = item.patient?.gender ?: ""
    }

    private fun init(view: View) {
        statusInfo = view.findViewById(R.id.status_info)
        hospitalInfo = view.findViewById(R.id.hospital_info)
        specialityInfo = view.findViewById(R.id.speciality_info)
        doctorInfo = view.findViewById(R.id.doctor_info)
        patientInfo = view.findViewById(R.id.patient_info)
        genderInfo = view.findViewById(R.id.gender_info)
    }
}