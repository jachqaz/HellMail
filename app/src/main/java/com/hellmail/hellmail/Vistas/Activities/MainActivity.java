package com.hellmail.hellmail.Vistas.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.hellmail.hellmail.R;
import com.hellmail.hellmail.Vistas.Fragmentos.LoginFragment;
import com.hellmail.hellmail.Vistas.Fragmentos.RegistroFragment;

public class MainActivity extends AppCompatActivity
        implements LoginFragment.OnLoginInteractionListener, RegistroFragment.OnRegistroInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }


    private void initFragment() {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.MainActivity, LoginFragment.newInstance());
        transaction.commit();
    }


    @Override
    public void irARegistro() {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.MainActivity, RegistroFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void irALogin() {
        FragmentTransaction transaction =
                getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.MainActivity, LoginFragment.newInstance());
        transaction.commit();
    }

    @Override
    public void IrAPerfil(String uid) {
        Intent intent = new Intent(this, Main2Activity.class);
        intent.putExtra("uid", uid);
        startActivity(intent);
        finish();
    }


}
