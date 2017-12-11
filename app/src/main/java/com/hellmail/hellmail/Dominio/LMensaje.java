package com.hellmail.hellmail.Dominio;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hellmail.hellmail.Modelo.Mensajes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rodolhan on 16/11/2017.
 */

public class LMensaje implements ILMensaje {
    private static List<Mensajes> lsMensajeEnv = new ArrayList<>();
    private static List<Mensajes> lsMensajeRec = new ArrayList<>();
    String uidenvp = "";
    String uid;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference refUsuarios;

    public LMensaje() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        uid = firebaseAuth.getCurrentUser().getUid();
    }

    @Override
    public void addMensajeRec(Mensajes mensaje) {
        lsMensajeRec.add(mensaje);
    }

    @Override
    public List<Mensajes> getMensajesRec() {

        if (lsMensajeRec.isEmpty()) {
            firebaseDatabase.getReference("mensajes").child(uid).child("recibidos").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            addMensajeRec(child.getValue(Mensajes.class));
                        }
                    } catch (Exception ex) {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        return lsMensajeRec;
    }

    @Override
    public void addMensajeEnv(Mensajes mensaje) {
        lsMensajeEnv.add(mensaje);
    }

    @Override
    public List<Mensajes> getMensajesEnv() {

        if (lsMensajeEnv.isEmpty()) {
            firebaseDatabase.getReference("mensajes").child(uid).child("enviados").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        for (DataSnapshot child : dataSnapshot.getChildren()) {
                            addMensajeEnv(child.getValue(Mensajes.class));
                        }
                    } catch (Exception ex) {
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        return lsMensajeEnv;
    }

    @Override
    public void Enviar(final Mensajes mensaje, String email, final CallBackInteractor<String> callBackInteractor) {
        refUsuarios = firebaseDatabase.getReference("usuarios-todo");
        Query query = refUsuarios.orderByChild("email").equalTo(email);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() == 0) {
                    callBackInteractor.success("Error Enviando, Porfavor Digitar Un Correo Electronico Valido");
                } else {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        uidenvp = (snapshot.child("uidenvp").getValue(String.class));
                        addMensajeEnv(mensaje);
                        refUsuarios = firebaseDatabase.getReference("mensajes");//todo: Limpiar uidenvp
                        refUsuarios.child(uid).child("enviados").child(String.valueOf(lsMensajeEnv.size())).setValue(mensaje);
                        firebaseDatabase.getReference("mensajes").child(uidenvp).child("recibidos").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                try {
                                    List<Mensajes> lista = new ArrayList<>();
                                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                                        lista.add(child.getValue(Mensajes.class));
                                    }
                                    refUsuarios = firebaseDatabase.getReference("mensajes").child(uidenvp).child("recibidos").child(String.valueOf(lista.size()));
                                    refUsuarios.setValue(mensaje);

                                } catch (Exception ex) {

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}

