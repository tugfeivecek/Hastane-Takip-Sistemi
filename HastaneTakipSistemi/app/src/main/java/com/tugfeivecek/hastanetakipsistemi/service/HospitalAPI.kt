package com.tugfeivecek.hastanetakipsistemi.service

import com.tugfeivecek.hastanetakipsistemi.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface HospitalAPI {
    @GET("hastane/allHospitals.php")
    fun getLocationHospital(): Single<HospitalResponse>

    @GET("hastane/allHospitals.php")
    fun getHospital(): Single<HospitalResponse>

    @GET("hastane/allPharmacies.php")
    fun getLocationPharmacy(): Single<PharmacyResponse>

    @GET("hastane/getEmergencyStatu.php")
    fun getEmergencyStatu(): Single<EmergencyResponse>

    @GET("hastane/hospitalDepartments.php")
    fun getDepartments(
        @Query("hospitalId") hospitalId: String = ""
    ): Single<DepartmentResponse>

    @GET("hastane/getDevice.php")
    fun getDeviceByDeviceId(
        @Query("deviceId") deviceId: String = ""
    ): Single<DeviceResponse>

    @GET("hastane/getDevice.php")
    fun getDeviceByDepartmentId(
        @Query("departmentId") departmentId: String = ""
    ): Single<DeviceResponse>

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

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("hastane/auth/register.php")
    fun registerUser(
        @Field("identityNumber") identityNumber: String,
        @Field("name") name: String,
        @Field("userName") userName: String,
        @Field("password") password: String,
        @Field("mail") mail: String,
    ): Single<RegisterResponse>

    @Headers("Content-Type: application/x-www-form-urlencoded", "Accept: application/json")
    @FormUrlEncoded
    @POST("hastane/auth/login.php")
    fun loginUser(
        @Field("identityNumber") identityNumber: String,
        @Field("password") password: String,
    ): Single<UserResponse>


    @Headers("Content-Type: application/x-www-form-urlencoded", "Accept: application/json")
    @FormUrlEncoded
    @POST("hastane/auth/personalLogin.php")
    fun loginPersonal(
        @Field("userName") userName: String,
        @Field("password") password: String,
    ): Single<PersonalResponse>
}