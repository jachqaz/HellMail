package com.hellmail.hellmail.Vistas.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hellmail.hellmail.Modelo.Mensajes;
import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Presenters.IMsgRecibidoPresenter;
import com.hellmail.hellmail.Vistas.Presenters.MsgRecibidoPresenter;
import com.hellmail.hellmail.adaptadores.MensajeListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MsgRecibidoFragment extends Fragment implements IMsgRecibidoFragment {
    @BindView(R.id.recicler)
    RecyclerView recicler;
    private String uid;
    private IMsgRecibidoPresenter msgRecibidoPresenter;
    private OnMsgRecInteractionListener mListener;

    public MsgRecibidoFragment() {
        // Required empty public constructor
    }

    public static MsgRecibidoFragment newInstance() {
        MsgRecibidoFragment fragment = new MsgRecibidoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_msg_recibido, container, false);
        ButterKnife.bind(this, view);
        uid = getArguments().getString("uid");
        msgRecibidoPresenter = new MsgRecibidoPresenter(this);
        //msgRecibidoPresenter.verMensagesRec(uid);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recicler.setLayoutManager(llm);
        List<Mensajes> lsMensaje = msgRecibidoPresenter.obtenerMensajesRecibidos();
        recicler.setAdapter(new MensajeListAdapter(lsMensaje));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMsgRecInteractionListener) {
            mListener = (OnMsgRecInteractionListener) context;
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


    @Override
    public void AddMensajeRecibidos(View view) {

    }

    //    @OnClick(R.id.button2)
//    public void crear() {
//        msgRecibidoPresenter.addMensajeRec("hola", "jach", "1/1/10");
//    }
    @Override
    public void refrescarListaMensajesRecibidos() {
        recicler.getAdapter().notifyDataSetChanged();
        recicler.scrollToPosition(recicler.getAdapter().getItemCount() - 1);
    }

    public interface OnMsgRecInteractionListener {

    }
}
