package com.tugfeivecek.hastanetakipsistemi.model

import com.google.gson.annotations.SerializedName


data class Hospital(
    @SerializedName("hospitalId")
    var hospitalId: Int?,
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

data class Records(
    var hospitalName: String,
    var date: String,
    var alan: String,
    var hour: String,
)

data class Notice(
    var baslik: String,
    var content: String,
    var date: String,
)

data class EmergencyOccuracy(
    var hospitalName: String,
    var wait: String,
    var capacity: String,
)

data class EmergecnyFull(
    @SerializedName("hospitalName")
    var hospitalName: String?,
    @SerializedName("greenArea")
    var greenArea: Int?,
    @SerializedName("yellowArea")
    var yellowArea: Int?,
    @SerializedName("redArea")
    var redArea: Int?,
    @SerializedName("waiting")
    var waiting: Int?,
)

data class EmergecnyFullResponse(
    @SerializedName("hastane")
    var allEmergency: List<EmergecnyFull>?,
    @SerializedName("success")
    var successEmergency: Int
)

data class Semptom(
    @SerializedName("name")
    var name: String?,
    @SerializedName("type_id")
    var type_id: Int?,
    @SerializedName("imageUrl")
    var imageUrl: String?,

    )

data class SemptomResponse(
    @SerializedName("hastalik")
    var allSemptom: List<Semptom>?,
    @SerializedName("success")
    var successSemptom: Int
)


data class SemptomDetail(
    @SerializedName("name")
    var name: String?,
    @SerializedName("capacity")
    var capacity: Int?,
    @SerializedName("illness.illId")
    var illness: Int?,

    )

data class SemptomDetailResponse(
    @SerializedName("hastane")
    var allSemptomDetail: List<SemptomDetail>?,
    @SerializedName("success")
    var successSemptomDetail: Int
)

data class Search(
    @SerializedName("name")
    var name: String?,
    @SerializedName("address")
    var address: String?,
    @SerializedName("capacity")
    var capacity: Int?,
    @SerializedName("patientCount")
    var patientCount: Int?,

    )

data class SearchResponse(
    @SerializedName("aranan")
    var allAranan: List<Search>?,
    @SerializedName("success")
    var successAranan: Int
)


