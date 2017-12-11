package com.hellmail.hellmail.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hellmail.hellmail.Modelo.Mensajes;
import com.hellmail.hellmail.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rodolhan on 16/11/2017.
 */

public class MensajeListAdapter extends RecyclerView.Adapter<MensajeListAdapter.ItemMensaje> {
    private List<Mensajes> dataset;

    public MensajeListAdapter(List<Mensajes> dataset) {
        super();
        this.dataset = dataset;
    }

    @Override
    public ItemMensaje onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        return new ItemMensaje(view);
    }

    @Override
    public void onBindViewHolder(ItemMensaje holder, int position) {

        Mensajes mensajes = dataset.get(position);
        holder.ItxMensaje.setText(mensajes.getMensaje());
        holder.ItxRemitente.setText(mensajes.getRemitente());
        holder.Itvfecha.setText(mensajes.getFecha());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public class ItemMensaje extends RecyclerView.ViewHolder {
        @BindView(R.id.ItxMensaje)
        TextView ItxMensaje;
        @BindView(R.id.ItxRemitente)
        TextView ItxRemitente;
        @BindView(R.id.Itxfecha)
        TextView Itvfecha;

        public ItemMensaje(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
