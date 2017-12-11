package com.hellmail.hellmail.Vistas.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hellmail.hellmail.Modelo.Mensajes;
import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Presenters.IMsgEnviadoPresenter;
import com.hellmail.hellmail.Vistas.Presenters.MsgEnviadoPresenter;
import com.hellmail.hellmail.adaptadores.MensajeListAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MsgEnviadoFragment extends Fragment implements IMsgEnviadoFragment {
    @BindView(R.id.recicler)
    RecyclerView recicler;
    int dayOfMonth = 0, month = 0, year = 0;
    private String uid;
    private IMsgEnviadoPresenter msgEnviadoPresenter;
    private OnMsgEnvInteractionListener mListener;

    public MsgEnviadoFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MsgEnviadoFragment newInstance() {
        MsgEnviadoFragment fragment = new MsgEnviadoFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_enviado, container, false);
        ButterKnife.bind(this, view);
        uid = getArguments().getString("uid");
        msgEnviadoPresenter = new MsgEnviadoPresenter(this);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recicler.setLayoutManager(llm);
        List<Mensajes> lsMensaje = msgEnviadoPresenter.obtenerMensajesEnviados();
        recicler.setAdapter(new MensajeListAdapter(lsMensaje));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMsgEnvInteractionListener) {
            mListener = (OnMsgEnvInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnLoginInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @OnClick(R.id.btnAdd)
    @Override
    public void AddMensajeEnviados(View view) {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getContext());
        final View mView = getLayoutInflater().inflate(R.layout.modal, null);
        mBuilder.setView(mView);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();
        Button mbtarea = mView.findViewById(R.id.MGuardar);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date date = new Date();
        final String fecha = dateFormat.format(date);
        mbtarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText mensaje = mView.findViewById(R.id.MMensaje);
                EditText destinatario = mView.findViewById(R.id.MDestinatario);
                msgEnviadoPresenter.addMensaje(mensaje.getText().toString(), destinatario.getText().toString(), fecha);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void errorenviar(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void refrescarListaMensajesEnviados() {
        recicler.getAdapter().notifyDataSetChanged();
        recicler.scrollToPosition(recicler.getAdapter().getItemCount() - 1);
    }

    public interface OnMsgEnvInteractionListener {
        // TODO: Update argument type and name
    }
}
