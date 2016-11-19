package com.rb.xibiu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.rb.xibiu.model.Car;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    private TextView tvNome;
    private TextView tvDesc;
    private ImageView ivImagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getIntent() != null) {
            Car car = getIntent().getParcelableExtra("car");


            tvDesc = (TextView) findViewById(R.id.desc);
            tvDesc.setText(car.getDescricao());
            ivImagem = (ImageView) findViewById(R.id.imagem);

            Picasso.with(this).load(car.getFoto()).placeholder(R.mipmap.ic_launcher).into(ivImagem);

            Toast.makeText(this, "Eita", Toast.LENGTH_SHORT).show();
        }
    }
}
