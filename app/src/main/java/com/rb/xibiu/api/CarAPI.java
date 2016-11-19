package com.rb.xibiu.api;

import com.rb.xibiu.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by rm31243 on 19/11/2016.
 */
public interface CarAPI {
    @GET("/carros/tipo/{tipo}")
    Call<List<Car>> findBy(@Path("tipo") String tipo);
}
