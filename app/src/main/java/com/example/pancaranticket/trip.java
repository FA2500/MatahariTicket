package com.example.pancaranticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class trip extends AppCompatActivity {
    //TV
    private TextView DestTV;
    private TextView FromTV;
    private TextView DateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);

        Initialization();
    }

    void Initialization()
    {
        DestTV = findViewById(R.id.textView8);
        FromTV = findViewById(R.id.textView7);
        DateTV = findViewById(R.id.textView9);

        DestTV.setText(userInfo.getDestination());
        FromTV.setText(userInfo.getFrom());
        DateTV.setText(userInfo.getDate());
    }

    public void goBack(View view)
    {
        Intent intent = new Intent(this,mainPage.class);
        startActivity(intent);
    }
}
