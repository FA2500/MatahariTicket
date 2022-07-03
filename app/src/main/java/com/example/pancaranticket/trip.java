package com.example.pancaranticket;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.res.ResourcesCompat;

import com.example.pancaranticket.Page.destinationPage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class trip extends AppCompatActivity implements View.OnClickListener  {

    //ImageButton
    private ImageView bg;
    //ImageButton

    //TV
    private TextView DestTV;
    private TextView FromTV;
    private TextView DateTV;
    //TV

    //CL
    private ConstraintLayout cl;
    //CL

    //LP
    private LinearLayout.LayoutParams caparam;
    private LinearLayout.LayoutParams llparam;
    private TableLayout.LayoutParams tlparam;
    private TableRow.LayoutParams trparam;
    private TableRow.LayoutParams trparamW;
    private LinearLayout.LayoutParams txparam;
    private LinearLayout.LayoutParams txparamM;
    private LinearLayout.LayoutParams btnparam;
    //LP

    //font
    private Typeface face ;
    //font

    //index
    private int buttonid = 10;
    private int cardID = 100;
    //index

    //Map
    private Map[] map;
    private int counter = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trips);

        //Remove for production
        FakeData();

        Initialization();
        initUI();

        getData();
    }

    @Override
    public void onClick(View v)
    {
        if(v.getId() != R.id.imageButton) //Back Button
        {
            int sum = counter - v.getId();
            Intent intent = new Intent(this,seats.class);
            //intent.putExtra("map", (Parcelable) map[v.getId()]);
            startActivity(intent);
        }
    }

    //for testing purpose only
    void FakeData()
    {
        userInfo.setUsername("FA250");
        userInfo.setRole("user");
        userInfo.setFullname("Faris");
        userInfo.setEmail("mfarisammar@gmail.com");
        userInfo.setFromState("KEDAH");
        userInfo.setFrom("ALOR STAR");
        userInfo.setDestinationState("TERENGGANU");
        userInfo.setDestination("DUNGUN");
        userInfo.setDate("10 JUL 2022");
    }

    void Initialization()
    {
        map = new Map[1010];

        DestTV = findViewById(R.id.textView8);
        FromTV = findViewById(R.id.textView7);
        DateTV = findViewById(R.id.textView9);

        DestTV.setText(userInfo.getDestination());
        FromTV.setText(userInfo.getFrom());
        DateTV.setText(userInfo.getDate());

        bg = findViewById(R.id.background);

        cl = findViewById(R.id.constraint);

        face = ResourcesCompat.getFont(this,R.font.calibri);
    }

    void initUI()
    {
        //LinearLayout layout = new LinearLayout();
        caparam = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        llparam = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tlparam = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,TableLayout.LayoutParams.WRAP_CONTENT );
        trparam = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT , 1);
        trparamW = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT , 1);
        txparam = new LinearLayout.LayoutParams(150, LinearLayout.LayoutParams.WRAP_CONTENT);
        txparamM = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        btnparam = new LinearLayout.LayoutParams(200, LinearLayout.LayoutParams.MATCH_PARENT);
    }

    void createCard(Map<String, Object> data)
    {
        //Log.d("CARD", data.get("DEPARTING").toString());
        CardView cv = new CardView(this);
        cv.setId(cardID);
        cv.setClickable(true);
        cv.setFocusable(true);
        cv.setBackgroundColor(Color.DKGRAY);
        cv.setContentPadding(10,10,10,10);
        ConstraintSet set = new ConstraintSet();
        //new
        //set.connect( Startview.getId() , TRBL , Endview.getId() , TRBL , margin
        //Constraint.TOP / Constraint.RIGHT / Constraint.BOTTOM / Constraint.LEFT

        //constraint
        if(cardID == 100)
        {
            cl.addView(cv,caparam);
            set.clone(cl);
            set.connect(cv.getId() , ConstraintSet.TOP , bg.getId() , ConstraintSet.BOTTOM);
            set.applyTo(cl);
        }
        else
        {
            cl.addView(cv,caparam);
            set.clone(cl);
            set.connect(cv.getId() , ConstraintSet.TOP , (cardID - 1) , ConstraintSet.BOTTOM);
            set.applyTo(cl);
        }
        Log.d("CARD", String.valueOf(cardID));
        cardID++;

        //constraint

        /*Button btn = new Button(this);
        btn.setText("TEST");
        btn.setBackgroundColor(Color.GREEN);
        cv.addView(btn, caparam);*/

        LinearLayout ll1 = new LinearLayout(this);
        ll1.setLayoutParams(llparam);
        ll1.setOrientation(LinearLayout.VERTICAL);
        cv.addView(ll1);

            LinearLayout ll2 = new LinearLayout(this);
            ll2.setLayoutParams(llparam);
            ll2.setOrientation(LinearLayout.VERTICAL);
            ll1.addView(ll2);

                TableLayout tl1 = new TableLayout(this);
                tl1.setLayoutParams(tlparam);
                ll2.addView(tl1);

                    /*TableRow tr1 = new TableRow(this);
                    tr1.setLayoutParams(trparam);
                    tl1.addView(tr1);*/
                    LinearLayout tr1 = new LinearLayout(this);
                    tr1.setLayoutParams(trparam);
                    tr1.setOrientation(LinearLayout.HORIZONTAL);
                    tr1.setPadding(8,8,8,8);
                    tl1.addView(tr1);

                        TextView tv1 = new TextView(this);
                        tv1.setText(data.get("DEPARTING").toString());
                        tv1.setTypeface(face, Typeface.BOLD);
                        tv1.setTextSize(TypedValue.COMPLEX_UNIT_SP , 20);
                        tv1.setTextColor(Color.BLACK);
                        tr1.addView(tv1, trparam);

                        TextView tv2 = new TextView(this);
                        tv2.setText(data.get("ETA").toString());
                        tr1.addView(tv2, trparam);

                        TextView tv3 = new TextView(this);
                        tv3.setText(data.get("VACANT").toString() + " SEATS");
                        tr1.addView(tv3, trparam);

            LinearLayout ll3 = new LinearLayout(this);
            ll3.setLayoutParams(llparam);
            ll3.setOrientation(LinearLayout.VERTICAL);
            ll1.addView(ll3);

                TableLayout tl2 = new TableLayout(this);
                tl2.setLayoutParams(tlparam);
                ll3.addView(tl2);

                    LinearLayout tr2 = new LinearLayout(this);
                    tr2.setLayoutParams(trparam);
                    tr2.setOrientation(LinearLayout.HORIZONTAL);
                    tr2.setPadding(8,8,8,8);
                    tl2.addView(tr2);

                        TextView tv4 = new TextView(this);
                        tv4.setText(data.get("FROM").toString());
                        tr2.addView(tv4, trparam);

                        ImageView img1 = new ImageView(this);
                        Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_swap_horiz_24);
                        img1.setImageDrawable(drawable);
                        tr2.addView(img1, trparam);

                        TextView tv5 = new TextView(this);
                        tv5.setText(data.get("DESTINATION").toString());
                        tr2.addView(tv5, trparam);

            LinearLayout ll4 = new LinearLayout(this);
            ll4.setLayoutParams(llparam);
            ll4.setOrientation(LinearLayout.VERTICAL);
            ll1.addView(ll4);

                TableLayout tl3 = new TableLayout(this);
                tl3.setLayoutParams(tlparam);
                ll4.addView(tl3);

                    LinearLayout tr3 = new LinearLayout(this);
                    tr3.setLayoutParams(trparam);
                    tr3.setOrientation(LinearLayout.HORIZONTAL);
                    tr3.setGravity(View.TEXT_ALIGNMENT_CENTER);
                    tr3.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);
                    tr3.setPadding(8,8,8,8);
                    tl3.addView(tr3);

                        TextView tv7 = new TextView(this);
                        tv7.setText("RM"+data.get("PRICE").toString());
                        tv7.setGravity(View.TEXT_ALIGNMENT_CENTER);
                        tr3.addView(tv7, txparam);

                        Button btn1 = new Button(this);
                        btn1.setText("BOOK");
                        btn1.setTextColor(Color.BLACK);
                        btn1.setOnClickListener(this);
                        btn1.setId(buttonid);
                        buttonid++;
                        tr3.addView(btn1, trparam);

            /*LinearLayout ll5 = new LinearLayout(this);
            ll5.setLayoutParams(llparam);
            ll5.setOrientation(LinearLayout.VERTICAL);
            ll1.addView(ll5);

                TableLayout tl4 = new TableLayout(this);
                tl4.setLayoutParams(tlparam);
                ll5.addView(tl4);

                    LinearLayout tr4 = new LinearLayout(this);
                    tr4.setLayoutParams(trparam);
                    tr4.setOrientation(LinearLayout.HORIZONTAL);
                    tr4.setPadding(8,8,8,8);
                    tl4.addView(tr4);

                        TextView tv8 = new TextView(this);
                        tv8.setText("CHILDREN");
                        tr4.addView(tv8, txparam);

                        TextView tv9 = new TextView(this);
                        tv9.setText("RM30.00");
                        tr4.addView(tv9, trparam);

                        Button btn1 = new Button(this);
                        btn1.setText("SELECT");
                        btn1.setTextColor(Color.BLACK);
                        tr4.addView(btn1, trparam);

                View v = new View(this);
                v.setBackgroundColor(Color.BLACK);
                ll5.addView(v);

            /*LinearLayout ll6 = new LinearLayout(this);
            ll6.setLayoutParams(llparam);
            ll6.setOrientation(LinearLayout.VERTICAL);
            ll1.addView(ll6);

                TableLayout tl5 = new TableLayout(this);
                tl5.setLayoutParams(tlparam);
                ll6.addView(tl5);

                    LinearLayout tr5 = new LinearLayout(this);
                    tr5.setLayoutParams(trparam);
                    tr5.setOrientation(LinearLayout.HORIZONTAL);
                    tr5.setPadding(8,8,8,8);
                    tl5.addView(tr5);

                        Button btn2 = new Button(this);
                        btn2.setText("3D VIEW");
                        btn2.setTextColor(Color.BLACK);
                        tr5.addView(btn2, trparam);

                        TextView tv10 = new TextView(this);
                        tv10.setText("ABC 1234");
                        tv10.setGravity(View.TEXT_ALIGNMENT_CENTER);
                        tr5.addView(tv10, trparam);*/


    }

    void getData()
    {
        //ONLY VALID FOR ONE INSTANCE
        /*FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("FROM/"+userInfo.getFrom()+"/"+userInfo.getDate()).document(userInfo.getDestination());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot document = task.getResult();
                    if(document != null)
                    {
                        Log.d("Ticket"," "+ Objects.requireNonNull(document.getData()));
                    }
                    else
                    {
                        Log.d("Ticket","EMPTY");
                    }
                }
                else
                {
                    Log.d("ERROR", "get failed with ", task.getException());
                }
            }
        });*/

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("FROM/"+userInfo.getFrom()+"/"+userInfo.getDate())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //state
                            for (QueryDocumentSnapshot document : task.getResult())
                            {
                                if(document.getId().contains(userInfo.getDestination()))
                                {
                                    createCard(document.getData());
                                    map[counter] = document.getData();
                                    counter++;
                                }
                                //Log.d("DOC", String.valueOf(document.getData()));
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void goBack(View view)
    {
        Intent intent = new Intent(this,mainPage.class);
        startActivity(intent);
    }


}
