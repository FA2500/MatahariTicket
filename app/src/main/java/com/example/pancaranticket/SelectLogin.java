package com.example.pancaranticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.pancaranticket.ARDependencies.helloar.HelloArActivity;

public class SelectLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_login);
    }

    public void testAR(View v)
    {
        Intent intent = new Intent(this, HelloArActivity.class);
        startActivity(intent);
    }

    public void testBillplz(View v)
    {
        Intent intent = new Intent(this, BillPlz.class);
        startActivity(intent);
    }

    public void testRegister(View v)
    {
        Intent intent = new Intent(this, signUpActivity.class);
        startActivity(intent);
    }

    public void testLogin(View v)
    {
        Intent intent = new Intent(this, loginActivity.class);
        startActivity(intent);
    }
}