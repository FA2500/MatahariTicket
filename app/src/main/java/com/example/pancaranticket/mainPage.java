package com.example.pancaranticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pancaranticket.Page.fromPage;
import com.example.pancaranticket.Page.historyPage;
import com.example.pancaranticket.Page.helpPage;
import com.example.pancaranticket.Page.userPage;
import com.example.pancaranticket.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.pancaranticket.Page.destinationPage;

import java.util.Calendar;

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
    private TextView rdateTv;

    DrawerLayout drawerLayout;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        counter = counter + 1;
        Log.d("TESTCOUNTER","COUNTER = "+counter);

        initDatePicker();
        dateButton = findViewById(R.id.dateButton);
        dateTv.setText(getTodaysDate());
        //dateButton.setText(getTodaysDate());


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.book);

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

    public void ClickMenu(View view){
        openDrawer(drawerLayout);
    }

    private static void openDrawer(DrawerLayout drawerLayout){
        drawerLayout.openDrawer(GravityCompat.START);
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
        rdateTv = findViewById(R.id.dateReturnTV);
        rdateTv.setText("OPTIONAL RETURN");
        drawerLayout = findViewById(R.id.bot_nav);


        if(userInfo.getFrom() != null)
        {
            destTV.setText(userInfo.getFrom());
        }
        if(userInfo.getDestination() != null)
        {
            privTv.setText(userInfo.getDestination());
        }
        if(userInfo.getDate() != null)
        {
            dateTv.setText(userInfo.getDate());
        }
        if(userInfo.getRdate() != null)
        {
            rdateTv.setText(userInfo.getRdate());
        }

    }

    public void goToDestination(View v)
    {
        Intent intent = new Intent(this, destinationPage.class);
        startActivity(intent);
    }

    public void goToFrom(View v)
    {
        if(userInfo.getFrom() == null)
        {
            Toast.makeText(this, "Please select your from first", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(this, fromPage.class);
            startActivity(intent);
        }

    }

    public void goToBus(View v)
    {
        if(userInfo.getDestination() == null)
        {
            Toast.makeText(this,"Destination cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(userInfo.getFrom() == null)
        {
            Toast.makeText(this,"From cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else if(userInfo.getDate() == null)
        {
            Toast.makeText(this,"Date cannot be empty",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Searching Data",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this , trip.class);
            startActivity(intent);
        }
    }

    public void ClickHistory(View view){
        redirectActivity(this,historyPage.class);
    }

    private static void redirectActivity(Activity activity, Class aclass) {
        Intent intent = new Intent(activity,aclass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    private String getTodaysDate()
    {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String date = makeDateString(day, month, year);
        userInfo.setDate(date);
        return makeDateString(day, month, year);
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateTv.setText(date);
                userInfo.setDate(date);
                //dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year)
    {
        return day + " " + getMonthFormat(month) + " "  + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


}