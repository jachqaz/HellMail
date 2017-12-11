package com.hellmail.hellmail.Vistas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Fragmentos.MsgEnviadoFragment;
import com.hellmail.hellmail.Vistas.Fragmentos.MsgRecibidoFragment;
import com.hellmail.hellmail.Vistas.Fragmentos.PerfilFragment;

public class Main2Activity extends AppCompatActivity implements PerfilFragment.OnPerfilInteractionListener,
        MsgRecibidoFragment.OnMsgRecInteractionListener,
        MsgEnviadoFragment.OnMsgEnvInteractionListener {

    private Toolbar toolbar;//se puso haciendo la toolbar superior
    private String uid;
    private int aux = 0;
    private BottomNavigationView.OnNavigationItemSelectedListener menuarriba =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.bontonrecibido:
                            irAMsgRec();
                            return true;
                        case R.id.botonenviado:
                            irAMsgEnv();
                            return true;
                    }
                    return false;
                }
            };
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.botonperfil:
                    BottomNavigationView barra = findViewById(R.id.navigationarriba);
                    barra.setVisibility(View.INVISIBLE);
                    barra.setSelectedItemId(R.id.bontonrecibido);
                    initFragment();
                    return true;
                case R.id.botonmensajes:
                    barra = findViewById(R.id.navigationarriba);
                    barra.setVisibility(View.VISIBLE);
                    barra.setOnNavigationItemSelectedListener(menuarriba);
                    irAMsgRec();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar = findViewById(R.id.barrasuperior); //se puso haciendo la toolbar superior
        setSupportActionBar(toolbar);//se puso haciendo la toolbar superior
        Intent intent = getIntent();
        uid = intent.getStringExtra("uid");
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initFragment();
    }


    private void initFragment() {
        Bundle args = new Bundle();
        args.putString("uid", uid);
        PerfilFragment perfilFragment = PerfilFragment.newInstance();
        perfilFragment.setArguments(args);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.Main2Activity, perfilFragment);
        transaction.commit();
    }

    private void irAMsgRec() {
        Bundle args = new Bundle();
        args.putString("uid", uid);
        MsgRecibidoFragment msgRecibidoFragment = new MsgRecibidoFragment();
        msgRecibidoFragment.setArguments(args);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.Main2Activity, msgRecibidoFragment);
        transaction.commit();
    }

    private void irAMsgEnv() {
        Bundle args = new Bundle();
        args.putString("uid", uid);
        MsgEnviadoFragment msgEnviadoFragment = new MsgEnviadoFragment();
        msgEnviadoFragment.setArguments(args);
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.Main2Activity, msgEnviadoFragment);
        transaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu) {//se puso haciendo la toolbar superior
        getMenuInflater().inflate(R.menu.barraarriba, menu);//se puso haciendo la toolbar superior
        return true;//se puso haciendo la toolbar superior
    }//se puso haciendo la toolbar superior

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.salir) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void irAMsg() {

    }
}
