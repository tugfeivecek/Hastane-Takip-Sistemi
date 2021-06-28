package com.tugfeivecek.hastanetakipsistemi.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Hospital(
    @SerializedName("hospitalId")
    var hospitalId: Int?,
    @SerializedName("hospitalName")
    var hospitalName: String?,
    @SerializedName("hospitalAddress")
    var hospitalAddress: String?,
    @SerializedName("capacity")
    var capacity: Int?,
    @SerializedName("patientCount")
    var patientCount: Int?,
    @SerializedName("hasGeneral")
    var hasGeneral: Int?,
    @SerializedName("hasChild")
    var hasChild: Int?,
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

data class Personal(
    @SerializedName("userName")
    @Expose
    var userName: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("Hospital_hospitalId")
    @Expose
    var hospitalId: String?,
)

data class PersonalResponse(
    @SerializedName("user")
    @Expose
    var personal: Personal? = null,
    @SerializedName("error")
    @Expose
    var error: Boolean? = false,
    @SerializedName("error_message")
    @Expose
    var error_message: String? = null,
)

data class PersonalRequest(
    @SerializedName("userName")
    @Expose
    var userName: String? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null
)


data class User(
    @SerializedName("identityNumber")
    @Expose
    var identityNumber: String? = null,
    @SerializedName("userName")
    @Expose
    var userName: String? = null,
)

data class UserResponse(
    @SerializedName("user")
    @Expose
    var user: User? = null,
    @SerializedName("error")
    @Expose
    var error: Boolean? = false,
    @SerializedName("error_message")
    @Expose
    var error_message: String? = null,
)


data class UserRequest(
    @SerializedName("identityNumber")
    @Expose
    var identityNumber: String? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null
)

data class RegisterUser(
    @SerializedName("identityNumber")
    @Expose
    var identityNumber: String? = null,
    @SerializedName("userName")
    @Expose
    var userName: String? = null,
    @SerializedName("date")
    @Expose
    var date: String? = null,
)

data class RegisterResponse(
    @SerializedName("user")
    @Expose
    var user: RegisterUser? = null,
    @SerializedName("uid")
    @Expose
    var uid: String? = null,
    @SerializedName("error")
    @Expose
    var error: Boolean? = false,
    @SerializedName("error_message")
    @Expose
    var error_message: String? = null,
)

data class RegisterRequest(
    @SerializedName("identityNumber")
    @Expose
    var identityNumber: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("userName")
    @Expose
    var userName: String? = null,
    @SerializedName("password")
    @Expose
    var password: String? = null,
    @SerializedName("mail")
    @Expose
    var mail: String? = null,
)


data class Pharmacy(
    @SerializedName("idPharmacy")
    @Expose
    var idPharmacy: String? = null,
    @SerializedName("pharmacyName")
    @Expose
    var pharmacyName: String? = null,
    @SerializedName("pharmacyAddress")
    @Expose
    var pharmacyAddress: String? = null,
    @SerializedName("latitude")
    @Expose
    var latitude: Double?,
    @SerializedName("longitude")
    @Expose
    var longitude: Double?,
)

data class PharmacyResponse(
    @SerializedName("eczaneler")
    var allPharmacy: List<Pharmacy>?,
    @SerializedName("success")
    var success: Int
)

data class EmergencyStatu(
    @SerializedName("hospitalId")
    @Expose
    var hospitalId: String? = null,
    @SerializedName("hospitalName")
    @Expose
    var hospitalName: String? = null,
    @SerializedName("capacity")
    @Expose
    var capacity: String? = null,
    @SerializedName("waiting")
    @Expose
    var waiting: Int?,
)

data class EmergencyResponse(
    @SerializedName("hastaneAcil")
    var allEmergencyStatus: List<EmergencyStatu>?,
    @SerializedName("success")
    var success: Int
)

data class Department(
    @SerializedName("departmentId")
    @Expose
    var departmentId: String? = null,
    @SerializedName("DepartmentName")
    @Expose
    var DepartmentName: String? = null,
    @SerializedName("Hospital_hospitalId")
    @Expose
    var Hospital_hospitalId: String? = null,
    @SerializedName("HospitalName")
    @Expose
    var HospitalName: String? = null,
)

data class DepartmentResponse(
    @SerializedName("bolumler")
    var allDepartments: List<Department>?,
    @SerializedName("success")
    var success: Int
)

data class Device(
    @SerializedName("deviceId")
    @Expose
    var deviceId: String? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("fixtureNo")
    @Expose
    var fixtureNo: String? = null,
    @SerializedName("description")
    @Expose
    var description: String? = null,
    @SerializedName("brands")
    @Expose
    var brands: String? = null,
    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null,
    @SerializedName("activeCount")
    @Expose
    var activeCount: String? = null,
    @SerializedName("stokCount")
    @Expose
    var stokCount: String? = null,
)

data class DeviceResponse(
    @SerializedName("cihazlar")
    var allDevices: List<Device>?,
    @SerializedName("success")
    var success: Int
)
