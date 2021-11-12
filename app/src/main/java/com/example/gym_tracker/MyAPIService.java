package com.example.gym_tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


// ref from https://square.github.io/retrofit/ and http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
public interface MyAPIService {

    // Annotations on the interface methods and its parameters indicate how a request will be handled
    @GET("/posts/1")
    Call<Post> getPost();

    // Annotations on the interface methods and its parameters indicate how a request will be handled
    // to get a list of data
    @GET("/posts")
    Call<List<Post>> getPosts();


    // note that POST Vs GET
    @GET("/testing")
    Call<ExerciseOnlyName> getMyData();

    // note that POST Vs GET
    @POST("/testingAgain")
    Call<ExerciseOnlyName> getMyDataByPost();

//    @POST("/addEvent")
//    Call<DataTesting>createData(@Body DataTesting data);

    @POST("/addEvent")
    Call<List<Exercise>> createData(@Body Exercise data);

    // @Field("user_name") String user_name
    // getData(//we want to send what kind of field to backend)
    @FormUrlEncoded // important
    @POST("/getEvent")
    Call<List<Exercise>> getData(@Field("user_name") String date);

    @FormUrlEncoded // important
    @POST("/getExercises")
    Call<List<ExerciseOnlyName>> getExercises(@Field("user_name") String date);

    @FormUrlEncoded // important
    @POST("/getSingleEvent")
    Call<Exercise> getSingleEvent(@Field("user_name") String date, @Field("name") String name);

    @POST("/getAllExercises")
    Call<List<ExerciseOnlyName>> getAllExercises();


    ///////////////////////////// The Latest Version ///////////////////////////////////////////
    @POST("/exercises/addExercise")
    Call<CheckSuccess> createTips(@Body Tips data);

    @POST("/exercises/getExerciseList")
    Call<List<Tips>> getTips();

    @FormUrlEncoded // important
    @POST("/exercises/delete")
    Call<CheckSuccess> delTips(@Field("name") String name);

}
