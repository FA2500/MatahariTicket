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
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.example.pancaranticket.databinding.ActivityMainBinding;

public class mainPage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    //from XML
    private TextView WelcomeText;
    private NavigationBarView botNav;

    //final
    /*final Fragment HomeFrag = new FirstFragment();
    final Fragment HistFrag = new SecondFragment();
    final Fragment UserFrag = new ThirdFragment();
    final Fragment TickFrag = new ForthFragment();
    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = TickFrag;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main_page);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*fm.beginTransaction().add(R.id.fragmentContainerView,HomeFrag).hide(HomeFrag).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView,HistFrag).hide(HistFrag).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView,UserFrag).hide(UserFrag).commit();
        fm.beginTransaction().add(R.id.fragmentContainerView,TickFrag).commit();*/



        FirebaseFirestore db = FirebaseFirestore.getInstance();



        db.collection("User")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                if(document.getData().get("email").toString().equals(userInfo.getEmail()))
                                {
                                    setUserInfo(document);
                                    initialize();
                                }
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.firstFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.firstFragment:
                        return true;
                    case R.id.secondfragment:
                        startActivity(new Intent(getApplicationContext()
                                , historytest.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.thirdFragment:
                        startActivity(new Intent(getApplicationContext()
                                , ThirdFragment.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.forthFragment:
                        startActivity(new Intent(getApplicationContext()
                                , ForthFragment.class));
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

        botNav = findViewById(R.id.bottomNavigationView);
        botNav.setSelectedItemId(R.id.forthFragment);




    }

    private void setUserInfo(QueryDocumentSnapshot doc)
    {
        userInfo.setFullname(doc.getData().get("fullname").toString());
        userInfo.setUsername(doc.getData().get("username").toString());
        userInfo.setRole(doc.getData().get("role").toString());

        Log.d("TESTINFO",userInfo.getEmail()+" "+userInfo.getUsername()+" "+userInfo.getFullName());
    }

}