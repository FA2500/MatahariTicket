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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class destinationPage extends AppCompatActivity implements View.OnClickListener {

    private ConstraintLayout cl;
    private ConstraintLayout.LayoutParams lp;
    private LinearLayout layout ;
    private LinearLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_page);
        cl = (ConstraintLayout) findViewById(R.id.main_layout);
        lp = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        layout = (LinearLayout) findViewById(R.id.linearLayout) ;
        layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Location")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                createButton(document.getId());
                                Log.d("DOC",document.getId());
                            }
                        } else {
                            Log.w("ERROR", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    @Override
    public void onClick(View v)
    {

    }


    private void createButton(String place) {
        Button myButton = new Button(this);
        myButton.setText(place);
        myButton.setOnClickListener(this);


        layout.addView(myButton,layoutParams);
    }

}