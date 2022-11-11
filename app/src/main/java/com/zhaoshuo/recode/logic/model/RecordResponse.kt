package com.zhaoshuo.recode.logic.model

import com.google.gson.annotations.SerializedName

data class RecordResponse(
    @SerializedName("c") val code: String?,
    @SerializedName("m") val message: String?,
    @SerializedName("d") val data: Data?
) : java.io.Serializable {

    data class Data(
        @SerializedName("Count") val count: String?,
        @SerializedName("Items") val items: List<Item>?
    )

    data class Item(
        val status: String?,
        val patient: Patient?,
        val payment: Payment?,
        val practitioner: Practitioner?,
        val start: String?
    ) : java.io.Serializable

    data class Patient(
        val fullName: String?,
        val gender: String?,
        val birthday: String?,
        val comment: String?
    )

    data class Payment(val method: String?, val paymentId: String?, val price: Price?)
    data class Price(
        val consultationFee: String?,
        val platformFee: String?,
        val VATFee: String?,
        val total: String?
    )

    data class Practitioner(
        val priceType: String?,
        val hospitalName: String?,
        val name: String?,
        val avatar: String?
    )
}
