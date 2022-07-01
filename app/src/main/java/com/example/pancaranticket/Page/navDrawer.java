package com.example.pancaranticket.Page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.pancaranticket.R;

public class navDrawer extends AppCompatActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_toolbar);

        drawerLayout = findViewById(R.id.bot_nav);
    }

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawerLayout);
    }

    private static void closeDrawer(DrawerLayout drawerLayout) {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    /*public void ClickBus(View view){
        redirectActivity(this,);
    }

    public void ClickSchedule(View view){
        redirectActivity(this,);
    }

    public void ClickHistory(View view){
        redirectActivity(this,);
    }

    public void ClickResend(View view){
        redirectActivity(this,);
    }

    public void ClickStaff(View view){
        redirectActivity(this,);
    }

    public void ClickCustomer(View view){
        redirectActivity(this,);
    }

    public void ClickAnalysis(View view){
        redirectActivity(this,);
    }*/

    private static void redirectActivity(Activity activity, Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause(){
        super.onPause();
        closeDrawer(drawerLayout);
    }
}

