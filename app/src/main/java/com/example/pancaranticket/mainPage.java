package com.example.pancaranticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pancaranticket.Page.historyPage;
import com.example.pancaranticket.Page.homePage;
import com.example.pancaranticket.Page.userPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.api.Page;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.pancaranticket.databinding.ActivityMainBinding;
import com.example.pancaranticket.Page.destinationPage;

public class mainPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private int counter = 0;

    //from XML
    private TextView WelcomeText;
    private NavigationBarView botNav;

    //BUtton
    private Button destButton;
    private Button privButton;
    private Button dateButton;

    //TextView
    private TextView destTV;
    private TextView privTv;
    private TextView dateTv;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        counter = counter + 1;
        Log.d("TESTCOUNTER","COUNTER = "+counter);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.book);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , homePage.class));
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
        WelcomeText = findViewById(R.id.textView4);
        WelcomeText.setText("Welcome, "+ userInfo.getUsername()+"!");
        destButton = findViewById(R.id.destinationButton);
        privButton = findViewById(R.id.fromButton);
        dateButton = findViewById(R.id.dateButton);
        destTV = findViewById(R.id.destinationTV);
        privTv = findViewById(R.id.fromTV);
        dateTv = findViewById(R.id.dateTV);

        if(userInfo.getDestination() != null)
        {
            destTV.setText(userInfo.getDestination());
        }

    }

    public void goToDestination(View v)
    {
        Intent intent = new Intent(this, destinationPage.class);
        startActivity(intent);
    }

    public void goToFrom(View v)
    {
        Intent intent = new Intent(this, destinationPage.class);
        startActivity(intent);
    }





}