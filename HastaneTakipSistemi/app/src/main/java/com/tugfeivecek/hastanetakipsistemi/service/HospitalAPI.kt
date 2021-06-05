package com.tugfeivecek.hastanetakipsistemi.service

import com.tugfeivecek.hastanetakipsistemi.model.*
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HospitalAPI {
    @GET("hastane/allHospitals.php")
    fun getLocationHospital(): Single<HospitalResponse>

    @GET("hastane/allHospitals.php")
    fun getHospital(): Single<HospitalResponse>

    @GET("hastane/allCities.php")
    fun getCity(): Single<CityResponse>

    @GET("hastane/getDistrict.php")
    fun getDistricts(
        @Query("ilId") ilId: String = "",
    ): Single<DistrictResponse>

    @GET("hastane/allHospitals.php")
    fun getHospitalList(
        @Query("ilceId") ilceId: String = "",
    ): Single<HospitalListResponse>

    @GET("hastane/getEmergencyInfo.php")
    fun getLocationEmergency(
        @Query("hastaneId") hastaneId: String = "",
    ): Single<EmergecnyFullResponse>

    @GET("hastane/getIllness.php")
    fun getSemptom(): Single<SemptomResponse>


    @GET("hastane/getillnessHospital.php")
    fun getSemptomDetail(
        @Query("type_id") type_id: String = "",
    ): Single<SemptomDetailResponse>

    @GET("hastane/searchHospital.php")
    fun getSearch(
        @Query("kelime") kelime: String = "",
    ): Single<SearchResponse>

}