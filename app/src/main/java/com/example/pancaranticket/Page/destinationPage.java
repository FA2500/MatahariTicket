package com.example.pancaranticket.Page;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.pancaranticket.R;
import com.example.pancaranticket.loginActivity;
import com.example.pancaranticket.mainPage;
import com.example.pancaranticket.userInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class destinationPage extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout layout ;
    private LinearLayout.LayoutParams layoutParams;

    //private LinearLayout layout2 ;
    //private LinearLayout.LayoutParams layoutParams2;

    private String[] StateM = new String[12];
    private int counter = 0;

    private int buttonid = 0;
    private int buttonid2 = 100;

    private Button buttonBack;
    private int buttonState = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_page);
        layout = (LinearLayout) findViewById(R.id.linearLayout) ;
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //layout2 = (LinearLayout) findViewById(R.id.linearLayout2);
        //layoutParams2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //layout2.setVisibility(View.INVISIBLE);


        buttonBack = (Button) findViewById(R.id.backButton);
        buttonBack.setOnClickListener(this);

        createStateButton();

    }

    //ID NEGERI =

    @Override
    public void onClick(View v)
    {
        if(v.getId() == buttonBack.getId()) //Back Button
        {
            switch (buttonState)
            {
                case 0:
                    Intent intent = new Intent(destinationPage.this, mainPage.class);
                    startActivity(intent);
                    break;
                case 1:
                    buttonState = 0;
                    layout.removeAllViews();
                    counter = 0;
                    buttonid = 0;
                    createStateButton();

                    break;
            }
        }
        for(int i = 0 ; i <12 ; i++)
        {
            if(v.getId() == i)
            {
                Log.d("TEST ID","ID = "+v.getId());
                createDistrictButton(StateM[i]);
            }
        }
        for(int i = 100; i < 112 ; i++)
        {
            if(v.getId() == i)
            {
                Button b = (Button)v;
                Log.d("TEST DISTRICT","DISTRICT = "+b.getText().toString());
                userInfo.setDestination(b.getText().toString());
                Intent intent = new Intent(destinationPage.this , mainPage.class);
                startActivity(intent);
            }
        }
    }

    private void createStateButton()
    {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Location")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            //state
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                StateM[counter] = document.getId().toString();
                                counter = counter + 1;
                                createButton(document.getId());
                                Log.d("DOC",document.getId());
                                buttonid = buttonid + 1;
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
    }



    private void createDistrictButton(String name)
    {
        buttonState = 1;
        layout.removeAllViews();
        buttonid2 = 100;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("Location").document(name);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    List<String> test = (List<String>) document.get("name");
                    if(test == null)
                    {
                        createButton2(document.getData().get("name").toString());
                        Log.d("DISTRICT"," "+document.getData().get("name").toString());
                    }
                    else
                    {
                        String[] array = test.toArray(new String[0]);

                        for(int i = 0 ; i < array.length ; i++)
                        {
                            createButton2(array[i]);
                            buttonid2 = buttonid2 + 1;
                        }

                        Log.d("DISTRICT"," "+array);
                    }
                } else {
                    Log.d("ERROR", "get failed with ", task.getException());
                }
            }
        });
    }

    private void createButton(String place) {
        Button myButton = new Button(this);
        myButton.setText(place);
        myButton.setOnClickListener(this);
        myButton.setId(buttonid);


        layout.addView(myButton,layoutParams);
    }

    private void createButton2(String place) {
        Button myButton = new Button(this);
        myButton.setText(place);
        myButton.setOnClickListener(this);
        myButton.setId(buttonid2);
        Log.d("BUTTON2",place+" "+buttonid2);

        layout.addView(myButton,layoutParams);
    }

}