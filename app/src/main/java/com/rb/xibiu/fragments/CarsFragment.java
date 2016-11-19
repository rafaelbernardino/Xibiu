package com.rb.xibiu.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rb.xibiu.DetailActivity;
import com.rb.xibiu.R;
import com.rb.xibiu.adapter.CarListAdapter;
import com.rb.xibiu.api.CarAPI;
import com.rb.xibiu.listener.OnClickListener;
import com.rb.xibiu.model.Car;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarsFragment extends Fragment implements Callback<List<Car>> {
    private String tipo;
    private RecyclerView rvCarros;
    private CarListAdapter adapter;

    public CarsFragment() {
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        if (getArguments() != null) {
            tipo = getArguments().getString("tipo");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cars, container, false);
        rvCarros = (RecyclerView) v.findViewById(R.id.rvCarros);
        rvCarros.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvCarros.setItemAnimator(new DefaultItemAnimator());
        rvCarros.setHasFixedSize(true);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadCars();
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        loadCars();
//    }

    private void loadCars() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.heiderlopes.com.br/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CarAPI api = retrofit.create(CarAPI.class);
        Call<List<Car>> call = api.findBy(tipo);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
        adapter = new CarListAdapter(getContext(), response.body(), onClickLister());
        rvCarros.setAdapter(adapter);
    }

    @Override
    public void onFailure(Call<List<Car>> call, Throwable t) {
        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }

    private OnClickListener onClickLister() {
        return new OnClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent i = new Intent(getContext(), DetailActivity.class);
                i.putExtra("car", adapter.getItem(position));
                startActivity(i);
            }
        };
    }
}
