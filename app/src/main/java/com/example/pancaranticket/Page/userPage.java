package com.example.pancaranticket.Page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.pancaranticket.R;
import com.example.pancaranticket.mainPage;
import com.example.pancaranticket.userInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class userPage extends AppCompatActivity {

    private TextView fullnametv;
    private TextView emailtv;
    private TextView usernametv;
    private TextView roletv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        initialize();
        //

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , helpPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext()
                                , historyPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.book:
                        startActivity(new Intent(getApplicationContext(), mainPage.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), userPage.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }

    private void initialize()
    {
        fullnametv = findViewById(R.id.fullNameTV);
        emailtv = findViewById(R.id.emailTV);
        usernametv = findViewById(R.id.usernameTV);
        roletv = findViewById(R.id.roleTV);

        fullnametv.setText("Full name = "+ userInfo.getFullName());
        emailtv.setText("Email = "+ userInfo.getEmail());
        usernametv.setText("Username = "+ userInfo.getUsername());
        roletv.setText("Role = "+ userInfo.getRole());
    }



}