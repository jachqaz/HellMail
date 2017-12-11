package com.hellmail.hellmail.Dominio;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hellmail.hellmail.Modelo.Perfil;
import com.hellmail.hellmail.Modelo.Usuario;


/**
 * Created by Hellmail on 11-Nov-17.
 */

public class LUsuario implements ILUsuario {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference refUsuarios;
    public LUsuario() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        refUsuarios = firebaseDatabase.getReference("usuarios-todo");

    }

    @Override
    public void crearUsuario(final String password, final Usuario usuario,
                             final CallBackInteractor<String> callBackInteractor) {
//todo: Limpiar uidenvp

        firebaseAuth.createUserWithEmailAndPassword(usuario.getEmail(), password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            usuario.setUid(task.getResult().getUser().getUid());
                            refUsuarios.child(usuario.getUid())
                                    .setValue(usuario);
                            refUsuarios = firebaseDatabase.getReference("perfil");
                            Perfil perfil = new Perfil();
                            perfil.setNombres(usuario.getNombres());
                            perfil.setPassword(password);
                            refUsuarios.child(usuario.getUid())
                                    .setValue(perfil);
                            callBackInteractor.success(usuario.getUid());
                        } else {
                            callBackInteractor.error(task.getException().getMessage());
                        }

                    }
                });

    }

    @Override
    public void authUsuario(String email, String password,
                            final CallBackInteractor<String> callBackInteractor) {
        //todo: Limpiar uidenvp

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            refUsuarios.child(task.getResult().getUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    Usuario usuario = dataSnapshot.getValue(Usuario.class);
                                    callBackInteractor.success(usuario.getUid());
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    callBackInteractor.error(databaseError.getMessage());
                                }
                            });
                        } else {
                            callBackInteractor.error(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void verperfil(final CallBackInteractor<Perfil> callBackInteractor) {
        //todo: Limpiar uidenvp
        String uid = firebaseAuth.getCurrentUser().getUid();
        refUsuarios = firebaseDatabase.getReference("perfil").child(uid);
        refUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Perfil perfil = dataSnapshot.getValue(Perfil.class);
                callBackInteractor.success(perfil);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void guardarPerfil(String nombre, int edad, long celular, String password) {
        //todo: Limpiar uidenvp
        String uid = firebaseAuth.getCurrentUser().getUid();
        Perfil perfil = new Perfil();
        perfil.setNombres(nombre);
        perfil.setEdad(edad);
        perfil.setCelular(celular);
        perfil.setPassword(password);
        firebaseAuth.getCurrentUser().updatePassword(password);
        refUsuarios = firebaseDatabase.getReference("perfil").child(uid);
        refUsuarios.setValue(perfil);

    }



}
