package com.tugfeivecek.hastanetakipsistemi.service

import com.tugfeivecek.hastanetakipsistemi.model.CityResponse
import com.tugfeivecek.hastanetakipsistemi.model.DistrictResponse
import com.tugfeivecek.hastanetakipsistemi.model.HospitalListResponse
import com.tugfeivecek.hastanetakipsistemi.model.HospitalResponse
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
}