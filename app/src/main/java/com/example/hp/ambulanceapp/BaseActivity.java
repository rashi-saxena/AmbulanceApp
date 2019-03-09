package com.example.hp.ambulanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BaseActivity extends AppCompatActivity implements View.OnClickListener{

    TextView baseTw;
    Button logoutBtn;

    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        baseTw = (TextView) findViewById(R.id.baseTextview);
        logoutBtn = (Button) findViewById(R.id.logoutBtn);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        String userEmail = user.getEmail();

        baseTw.setText("Welcome " + userEmail);

        logoutBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == logoutBtn) {
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}
