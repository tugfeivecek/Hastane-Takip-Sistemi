package com.tugfeivecek.hastanetakipsistemi.model

import com.google.gson.annotations.SerializedName


data class Hospital(
    @SerializedName("hospitalName")
    var hospitalName: String?,
    @SerializedName("campusName")
    var campusName: String?,
    @SerializedName("hospitalAddress")
    var hospitalAddress: String?,
    @SerializedName("campusAddress")
    var campusAddress: String?,
    @SerializedName("phone")
    var phone: String?,
    @SerializedName("mail")
    var mail: String?,
    @SerializedName("capacity")
    var capacity: Int?,
    @SerializedName("patientCount")
    var patientCount: Int?,
    @SerializedName("latitude")
    var latitude: Double?,
    @SerializedName("longitude")
    var longitude: Double?,
)

data class HospitalResponse(
    @SerializedName("hastane")
    var allHospital: List<Hospital>?,
    @SerializedName("success")
    var success: Int
)

data class City(
    @SerializedName("ilId")
    var ilId: Int?,
    @SerializedName("isim")
    var isim: String?,
)

data class CityResponse(
    @SerializedName("il")
    var allCity: List<City>?,
    @SerializedName("success")
    var successCity: Int
)

data class District(
    @SerializedName("ilceId")
    var ilceId: Int?,
    @SerializedName("ilceIsim")
    var ilceIsim: String?,
    @SerializedName("ilIsim")
    var ilIsim: String?,
    @SerializedName("ilId")
    var ilId: Int?,
)

data class DistrictResponse(
    @SerializedName("ilce")
    var allDistrict: List<District>?,
    @SerializedName("success")
    var successDistrict: Int
)

data class HospitalList(
    @SerializedName("hospitalName")
    var hospitalName: String?,
    @SerializedName("hospitalAddress")
    var hospitalAddress: String?,
    @SerializedName("capacity")
    var capacity: Int?,
    @SerializedName("patientCount")
    var patientCount: Int?,
    @SerializedName("latitude")
    var latitude: Double?,
    @SerializedName("longitude")
    var longitude: Double?,
    @SerializedName("ilce_ilceId")
    var ilce_ilceId: Int?,
    @SerializedName("ilce_il_ilId")
    var ilce_il_ilId: Int?,
)

data class HospitalListResponse(
    @SerializedName("hastane")
    var allHospitalList: List<HospitalList>?,
    @SerializedName("success")
    var successHospitalList: Int
)