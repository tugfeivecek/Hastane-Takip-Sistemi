package com.tugfeivecek.hastanetakipsistemi.service

import com.google.gson.GsonBuilder
import com.tugfeivecek.hastanetakipsistemi.model.*
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "http://kutuphanemnerede.com/"

class HospitalAPIService {

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(HospitalAPI::class.java)

    fun getHospitalLocationData(): Single<HospitalResponse> {
        return api.getHospital()
    }

    fun getPharmacyLocationData(): Single<PharmacyResponse> {
        return api.getLocationPharmacy()
    }

    fun getEmergencyStatuData(): Single<EmergencyResponse> {
        return api.getEmergencyStatu()
    }

    fun getHospitalData(): Single<HospitalResponse> {
        return api.getLocationHospital()
    }

    fun getCityData(): Single<CityResponse> {
        return api.getCity()
    }

    fun getDistrictData(ilId: String): Single<DistrictResponse> {
        return api.getDistricts(ilId)
    }

    fun getHospitalListData(ilceId: String): Single<HospitalListResponse> {
        return api.getHospitalList(ilceId)
    }

    fun getLocationEmergencyFullData(hastaneId: String): Single<EmergecnyFullResponse> {
        return api.getLocationEmergency(hastaneId)
    }

    fun getSemptomData(): Single<SemptomResponse> {
        return api.getSemptom()
    }

    fun getSemptomDetailData(type_id: String): Single<SemptomDetailResponse> {
        return api.getSemptomDetail(type_id)
    }

    fun getSearchData(kelime: String): Single<SearchResponse> {
        return api.getSearch(kelime)
    }

    fun getDepartmentData(hospitalId: String): Single<DepartmentResponse> {
        return api.getDepartments(hospitalId)
    }

    fun checkUser(request: UserRequest): Single<UserResponse> {
        return api.loginUser(request.identityNumber.toString(), request.password.toString())
    }

    fun registerUser(registerRequest: RegisterRequest): Single<RegisterResponse> {
        return api.registerUser(
            registerRequest.identityNumber.toString(),
            registerRequest.name.toString(),
            registerRequest.userName.toString(),
            registerRequest.password.toString(),
            registerRequest.mail.toString()
        )
    }

    fun checkPersonal(request: PersonalRequest): Single<PersonalResponse> {
        return api.loginPersonal(request.userName.toString(), request.password.toString())
    }

    fun getDeviceDataByDeviceId(deviceId: String): Single<DeviceResponse> {
        return api.getDeviceByDeviceId(deviceId)
    }

    fun getDeviceDataByDepartmentId(departmentId: String): Single<DeviceResponse> {
        return api.getDeviceByDepartmentId(departmentId)
    }

}