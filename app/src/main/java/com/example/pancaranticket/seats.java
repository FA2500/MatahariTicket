package com.example.pancaranticket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.res.ResourcesCompat;

import java.util.Map;


public class seats extends AppCompatActivity implements View.OnClickListener {

    private Map map;

    //ly
    private LinearLayout ll;

    //ll
    private LinearLayout.LayoutParams llparam;
    private LinearLayout.LayoutParams llparam2;
    private LinearLayout.LayoutParams imgparam;
    private LinearLayout.LayoutParams imgparam2;
    private LinearLayout.LayoutParams tvparam;

    private Boolean init = false;
    private int counter = 1;
    private int btncounter = 1;

    //font
    private Typeface face;

    //image
    private Drawable unavail;
    private Drawable avail;
    private Drawable you;

    //String
    private String[] status = {"Unavailable","Available","You"};

    //int
    private int usercounter = 0;

    //array
    private int[] userseats = new int[150];

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2d_seats_main);

        Intent intent = getIntent();
        map = (Map) intent.getSerializableExtra("map");
        //Log.d("MAP", map.toString());

        initial();

        for(int i = 0 ; i < 9 ; i++)
        {
            createButton();
        }
        createButtonLast();

    }

    @Override
    public void onClick(View v)
    {
        Log.d("TEST IMG", String.valueOf(v.getId()));
        ImageView btn = (ImageView)v;

        if( v.getContentDescription() == status[1] )
        {
            v.setBackground(you);
            v.setContentDescription(status[2]);
            userseats[usercounter] = v.getId() ;
            usercounter++;
        }
        else if( v.getContentDescription() == status[0] )
        {
            Toast.makeText(this, "Seats already occupied", Toast.LENGTH_SHORT).show();
        }
        else
        {
            v.setBackground(avail);
            v.setContentDescription(status[1]);
            usercounter--;
        }
    }

    void initial()
    {
        ll = findViewById(R.id.llview);

        llparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT , LinearLayout.LayoutParams.WRAP_CONTENT , 100 );
        llparam.setMargins(0,0,0,8);
        llparam2 = new LinearLayout.LayoutParams(0 , LinearLayout.LayoutParams.WRAP_CONTENT , 50 );
        imgparam = new LinearLayout.LayoutParams(75 , 75 );
        imgparam.setMarginEnd(4);
        imgparam2 = new LinearLayout.LayoutParams(75 , 75 );
        imgparam2.setMarginStart(4);
        tvparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        ll.setGravity(Gravity.CENTER);
        face = ResourcesCompat.getFont(this,R.font.print_clearly);

        avail = AppCompatResources.getDrawable(this , R.drawable.available_img);
        unavail = AppCompatResources.getDrawable(this , R.drawable.booked_img2);
        you = AppCompatResources.getDrawable(this , R.drawable.your_seat_img);
    }

    void createButtonLast()
    {
        LinearLayout lil = new LinearLayout(this);
        lil.setOrientation(LinearLayout.HORIZONTAL);
        lil.setLayoutParams(llparam);;
        ll.addView(lil);

            LinearLayout lilr1 = new LinearLayout(this);
            lilr1.setOrientation(LinearLayout.HORIZONTAL);
            lilr1.setLayoutParams(llparam2);
            lilr1.setGravity(Gravity.CENTER);
            lil.addView(lilr1);

                TextView tv1 = new TextView(this);
                tvparam.setMarginEnd(16);
                tv1.setTypeface(face, Typeface.BOLD);
                tv1.setText(String.valueOf(0));
                tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP , 18);
                lilr1.addView(tv1,tvparam);
                counter++;

                ImageView btn1 = new ImageView(this);
                btn1.setBackground(avail);
                btn1.setId(btncounter);
                btn1.setOnClickListener(this);
                btn1.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn1,imgparam);

                ImageView btn2 = new ImageView(this);
                btn2.setBackground(avail);
                btn2.setId(btncounter);
                btn2.setOnClickListener(this);
                btn2.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn2,imgparam2);

                ImageView btn3 = new ImageView(this);
                btn3.setBackground(avail);
                btn3.setId(btncounter);
                btn3.setOnClickListener(this);
                btn3.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn3,imgparam2);

                ImageView btn4 = new ImageView(this);
                btn4.setBackground(avail);
                btn4.setId(btncounter);
                btn4.setOnClickListener(this);
                btn4.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn4,imgparam2);

                ImageView btn5 = new ImageView(this);
                btn5.setBackground(avail);
                btn5.setId(btncounter);
                btn5.setOnClickListener(this);
                btn5.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn5,imgparam2);

                ImageView btn6 = new ImageView(this);
                btn6.setBackground(avail);
                btn6.setId(btncounter);
                btn6.setOnClickListener(this);
                btn6.setContentDescription(status[1]);
                btncounter++;
                lilr1.addView(btn6,imgparam2);



    }

    void createButton()
    {
        LinearLayout lil = new LinearLayout(this);
        lil.setOrientation(LinearLayout.HORIZONTAL);
        lil.setLayoutParams(llparam);;
        ll.addView(lil);

        LinearLayout lilr1 = new LinearLayout(this);
        lilr1.setOrientation(LinearLayout.HORIZONTAL);
        lilr1.setLayoutParams(llparam2);
        lilr1.setGravity(Gravity.CENTER);
        lil.addView(lilr1);

        TextView tv1 = new TextView(this);
        tvparam.setMarginEnd(16);
        tv1.setTypeface(face, Typeface.BOLD);
        tv1.setText(String.valueOf(counter));
        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP , 18);
        lilr1.addView(tv1,tvparam);
        counter++;

        ImageView btn1 = new ImageView(this);
        btn1.setBackground(avail);
        btn1.setId(btncounter);
        btn1.setOnClickListener(this);
        btn1.setContentDescription(status[1]);
        btncounter++;
        lilr1.addView(btn1,imgparam);

        ImageView btn2 = new ImageView(this);
        btn2.setBackground(avail);
        btn2.setId(btncounter);
        btn2.setOnClickListener(this);
        btn2.setContentDescription(status[1]);
        btncounter++;
        lilr1.addView(btn2,imgparam2);

        LinearLayout lilr2 = new LinearLayout(this);
        lilr2.setOrientation(LinearLayout.HORIZONTAL);
        lilr2.setLayoutParams(llparam2);
        lilr2.setGravity(Gravity.CENTER);
        lil.addView(lilr2);

        ImageView btn3 = new ImageView(this);
        btn3.setBackground(avail);
        btn3.setId(btncounter);
        btn3.setOnClickListener(this);
        btn3.setContentDescription(status[1]);
        btncounter++;
        lilr2.addView(btn3,imgparam);

        ImageView btn4 = new ImageView(this);
        btn4.setBackground(avail);
        btn4.setId(btncounter);
        btn4.setOnClickListener(this);
        btn4.setContentDescription(status[1]);
        btncounter++;
        lilr2.addView(btn4,imgparam2);

    }
}
