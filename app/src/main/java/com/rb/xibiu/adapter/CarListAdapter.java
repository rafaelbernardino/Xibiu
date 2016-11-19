package com.rb.xibiu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rb.xibiu.R;

import com.rb.xibiu.listener.OnClickListener;
import com.rb.xibiu.model.Car;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rm31243 on 19/11/2016.
 */
public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarrosViewHolder> {

    private Context context;
    private List<Car> listCarro;
    private OnClickListener clickListener;

    public CarListAdapter(Context context, List<Car> listCarro, OnClickListener clickListener) {
        this.context = context;
        this.listCarro = listCarro;
        this.clickListener = clickListener;
    }

    @Override
    public CarrosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.carro_row, parent, false);
        return new CarrosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final CarrosViewHolder holder, final int position) {
        holder.tvNome.setText(listCarro.get(position).getNome());
        holder.tvDescricao.setText(listCarro.get(position).getDescricao());
        Picasso.with(context).load(listCarro.get(position).getFoto()).placeholder(R.mipmap.ic_launcher).into(holder.ivCarro);

        if (clickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listCarro.size();
    }

    public Car getItem(int position) {
        return listCarro.get(position);
    }

    public static class CarrosViewHolder extends RecyclerView.ViewHolder {
        TextView tvNome;
        TextView tvDescricao;
        ImageView ivCarro;

        public CarrosViewHolder(View itemView) {
            super(itemView);
            tvNome = (TextView) itemView.findViewById(R.id.tvNome);
            tvDescricao = (TextView) itemView.findViewById(R.id.tvDescricao);
            ivCarro = (ImageView) itemView.findViewById(R.id.ivCarro);
        }
    }
}
