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
import com.hellmail.hellmail.Vistas.Presenters.ILoginPresenter;
import com.hellmail.hellmail.Vistas.Presenters.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnLoginInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements ILoginFragment {
    @BindView(R.id.Email_loginTXT)
    EditText Email_loginTXT;

    @BindView(R.id.PassLoginTXT)
    EditText PassLoginTXT;

    @BindView(R.id.progress)
    ProgressBar progress;

    @BindView(R.id.BOTONIngresar)
    Button BOTONIngresar;


    private ILoginPresenter loginPresenter;

    private OnLoginInteractionListener mListener;


    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        loginPresenter = new LoginPresenter(this);

        return view;
    }

    @OnClick(R.id.BOTONIngresar)
    public void login() {

        final String email = Email_loginTXT.getText().toString();
        String contrasena = PassLoginTXT.getText().toString();

        loginPresenter.login(email, contrasena);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnLoginInteractionListener) {
            mListener = (OnLoginInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnRegistroInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void habilitarVistas() {
        PassLoginTXT.setEnabled(true);
        Email_loginTXT.setEnabled(true);
        BOTONIngresar.setEnabled(true);
    }

    @Override
    public void deshabilitarVistas() {
        PassLoginTXT.setEnabled(false);
        Email_loginTXT.setEnabled(false);
        BOTONIngresar.setEnabled(false);
    }

    @Override
    public void mostrarProgress() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void ocultarProgress() {
        progress.setVisibility(View.GONE);
    }

    @OnClick(R.id.btnCrearCuenta)
    @Override
    public void irARegistro() {
        if (mListener != null) {
            mListener.irARegistro();
        }
    }

    @Override
    public void irAPerfil(String uid) {
        if (mListener != null) {
            mListener.IrAPerfil(uid);
        }
    }

    @Override
    public void mostrarError(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
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
    public interface OnLoginInteractionListener {
        void IrAPerfil(String uid);

        void irARegistro();
    }
}
