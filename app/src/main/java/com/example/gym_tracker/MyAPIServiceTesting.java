package com.example.gym_tracker;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


// ref from https://square.github.io/retrofit/ and http://tw-hkt.blogspot.com/2020/03/retrofit-java.html
public interface MyAPIServiceTesting {

    // Annotations on the interface methods and its parameters indicate how a request will be handled
    @GET("/posts/1")
    Call<Post> getPost();

    // Annotations on the interface methods and its parameters indicate how a request will be handled
    // to get a list of data
    @GET("/posts")
    Call<List<Post>> getPosts();


    // note that POST Vs GET
    @GET("/testing")
    Call<Testing> getMyData();

    // note that POST Vs GET
    @POST("/testingAgain")
    Call<Testing> getMyDataByPost();
}
