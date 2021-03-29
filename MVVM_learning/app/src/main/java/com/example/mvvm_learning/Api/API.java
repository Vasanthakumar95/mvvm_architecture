package com.example.mvvm_learning.Api;

import com.example.mvvm_learning.models.remote_db_response.*;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface API {

//    @FormUrlEncoded
//    @POST("user_register")
//    Call<default_response> user_register
//            (
//                    @Field("user_id") String username,
//                    @Field("password") String password,
//                    @Field("role") String role,
//                    @Field("user_type") String user_type
//            );

    @FormUrlEncoded
    @POST("userlogin")
    Call<login_response> userlogin
            (
                    @Field("user_id") String user_id,
                    @Field("password") String password
            );

//
//    @FormUrlEncoded
//    @POST("getparentbyloginid")
//    Call<get_parents_response> getparentbyloginid
//            (
//                    @Field("parent_id") String parent_id
//            );
//
//    @FormUrlEncoded
//    @POST("getchildbyparentid")
//    Call<get_total_children_response> getchildbyparentid
//            (
//                    @Field("id") String parent_id
//            );
//
//    @FormUrlEncoded
//    @POST("getattendancebyuserid")
//    Call<get_attendance_by_user_id_response> getattendancebyuserid
//            (
//                    @Field("child_id") String child_id,
//                    @Field("date") String date
//            );
////
//
//    @FormUrlEncoded
//    @POST("getAttendanceByChildId_monthyear")
//    Call<get_attendance_by_user_id_response> getattendancebychildid_monthyear
//            (
//                    @Field("child_id") String child_id,
//                    @Field("month") String month,
//                    @Field("year") String year
//            );
//
//    //getAttendanceByChildId_year
//
//    @FormUrlEncoded
//    @POST("getAttendanceByChildId_year")
//    Call<default_response> getAttendanceByChildId_year
//            (
//                    @Field("child_id") String child_id,
//                    @Field("year") String year
//            );
//
    @FormUrlEncoded
    @POST("getTeacher_Class_info")
    Call<teacher_response> getTeacher_Class_info
            (
                    @Field("teacher_id") String teacher_id
            );

    @FormUrlEncoded
    @POST("getTeacher_Class_Total_Student")
    Call<default_response> getTeacher_Class_Total_Student
            (
                    @Field("class_id") String class_id
            );

    @FormUrlEncoded
    @POST("getTeacher_Class_Att")
    Call<teacher_get_class_att_response> getTeacher_Class_Att
            (
                    @Field("class_id") String class_id,
                    @Field("date") String date
            );

    @FormUrlEncoded
    @POST("getTeacher_absent_student")
    Call<teacher_absence_response> getTeacher_absent_student
            (
                    @Field("class_id") String class_id,
                    @Field("date") String date
            );
//
//    @FormUrlEncoded
//    @POST("getParentNumByStudentId")
//    Call<teacher_contact_response> getParentNumByStudentId
//            (
//                    @Field("user_id") String user_id
//            );
//
//    //--------------------ADMIN---------------------------------------------------
//
//    @FormUrlEncoded
//    @POST("getAdmin_Daily_School_Attendance")
//    Call<default_response> getAdmin_Daily_School_Attendance
//            (
//                    @Field("date") String date
//            );
//
//    @FormUrlEncoded
//    @POST("getAdmin_Total_Student")
//    Call<default_response> getAdmin_Total_Student(
//
//            @Field("date") String date
//    );
//
//    @FormUrlEncoded
//    @POST("getAdmin_Details")
//    Call<admin_details_response> getAdmin_Details(
//
//            @Field("principle_id") String principle_id
//    );
//
//
//    @FormUrlEncoded
//    @POST("getAdmin_Student_attendance_c_date")
//    Call<admin_attendance_byclass_response> getAdmin_Student_attendance_c_date(
//
//            @Field("class_id") String class_id,
//            @Field("date") String date
//    );


}
