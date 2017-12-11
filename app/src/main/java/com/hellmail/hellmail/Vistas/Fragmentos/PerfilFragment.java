package com.hellmail.hellmail.Vistas.Fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Presenters.IPerfilPresenter;
import com.hellmail.hellmail.Vistas.Presenters.PerfilPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;


public class PerfilFragment extends Fragment implements IPerfilFrangment {
    private static final int GALLERY_INTENT = 1;
    @BindView(R.id.txtNombres)
    EditText txtNombres;
    @BindView(R.id.EdadTXT)
    EditText EdadTXT;
    @BindView(R.id.CelularTXT)
    EditText CelularTXT;
    @BindView(R.id.PassLoginTXT)
    EditText PassLoginTXT;
    @BindView(R.id.BOTONguardar)
    Button BOTONguardar;
    @BindView(R.id.avatar)
    ImageView avatar;
    private String uid;
    private IPerfilPresenter perfilPresenter;
    private OnPerfilInteractionListener mListener;
    //firebase avatar
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        ButterKnife.bind(this, view);
        storageReference = FirebaseStorage.getInstance().getReference();
        progressDialog = new ProgressDialog(getContext());
        uid = getArguments().getString("uid");
        perfilPresenter = new PerfilPresenter(this);
        perfilPresenter.verPerfil();
        return view;

    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPerfilInteractionListener) {
            mListener = (OnPerfilInteractionListener) context;
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
    public void habilitarControles() {

    }

    @Override
    public void deshabilitarControles() {

    }

    @OnClick(R.id.BOTONguardar)
    @Override
    public void Guardar() {
        perfilPresenter.guardarPerfil(txtNombres.getText().toString(),
                Integer.parseInt(String.valueOf(EdadTXT.getText())),
                Long.parseLong(String.valueOf((CelularTXT.getText()))),
                PassLoginTXT.getText().toString());
    }

    @Override
    public void mostrarError(String mensaje) {
        Snackbar.make(getView(), mensaje, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void irAMsg() {
        if (mListener != null) {
            mListener.irAMsg();
        }
    }


    @Override
    public void modificarPerfil(String nombre, int edad, long celular, String password) {
        txtNombres.setText(nombre);
        EdadTXT.setText(edad + "");
        CelularTXT.setText(celular + "");
        PassLoginTXT.setText(password);
    }

    @OnClick(R.id.avatar)
    @Override
    public void avatar() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {
            progressDialog.setTitle("Subiendo foto");
            progressDialog.setMessage("Subiendo Foto");
            progressDialog.setCancelable(false);
            progressDialog.show();
            Uri uri = data.getData();
            StorageReference filePath = storageReference.child("fotos").child(uri.getLastPathSegment());
            filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    progressDialog.dismiss();
                    Uri descargarFoto = taskSnapshot.getDownloadUrl();
                    Glide.with(getView()).load(descargarFoto).into(avatar);
                }


            });


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
    public interface OnPerfilInteractionListener {
        void irAMsg();
    }
}
