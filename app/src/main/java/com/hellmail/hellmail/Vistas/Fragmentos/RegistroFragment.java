package com.hellmail.hellmail.Vistas.Fragmentos;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Presenters.IRegistroPresenter;
import com.hellmail.hellmail.Vistas.Presenters.RegistroPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnRegistroInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroFragment extends Fragment implements IRegistroFragment{
    @BindView(R.id.Email_loginTXT)
    EditText txtEmailLogin;

    @BindView(R.id.NombresTXT)
    EditText txtNombres;

    @BindView(R.id.PassLoginTXT)
    EditText txtPasswordLogin;

    @BindView(R.id.BOTONCrearcuenta)
    Button btnCrearCuenta;

    @BindView(R.id.progress)
    ProgressBar progress;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    // TODO: Rename and change types of parameters
    private IRegistroPresenter registroPresenter;
    private OnRegistroInteractionListener mListener;
    public RegistroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static RegistroFragment newInstance() {
        RegistroFragment fragment = new RegistroFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        ButterKnife.bind(this, view);

        registroPresenter = new RegistroPresenter(this);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnRegistroInteractionListener) {
            mListener = (OnRegistroInteractionListener) context;
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
    public void habilitarControles() {
        txtNombres.setEnabled(true);
        txtEmailLogin.setEnabled(true);
        txtPasswordLogin.setEnabled(true);
        btnCrearCuenta.setEnabled(true);
    }

    @Override
    public void deshabilitarControles() {
        txtNombres.setEnabled(false);
        txtEmailLogin.setEnabled(false);
        txtPasswordLogin.setEnabled(false);
        btnCrearCuenta.setEnabled(false);
    }

    @Override
    public void mostrarProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgress() {
        progress.setVisibility(View.GONE);
    }

    @OnClick(R.id.BOTONCrearcuenta)
    @Override
    public void registrar() {
        String nombres = txtNombres.getText().toString();
        String email = txtEmailLogin.getText().toString();
        String password = txtPasswordLogin.getText().toString();

        registroPresenter.registrar(nombres, email, password);
    }

    @Override
    public void mostrarError(String error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.YatengocuentaTXT)
    @Override
    public void irALogin() {
        if (mListener != null) {
            mListener.irALogin();
        }
    }

    @Override
    public void irAPerfil(String uid) {
        if (mListener != null) {
            mListener.IrAPerfil(uid);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnRegistroInteractionListener {
        // TODO: Update argument type and name
        void irALogin();

        void IrAPerfil(String uid);
    }
}
