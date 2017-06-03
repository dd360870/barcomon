package com.example.barcomon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;

    private TextView textViewUserEmail;
    private Button buttonLogout;

    private Button buttonReview;
    private Button buttonBarCoMon;

    private Button buttonSearch;
    private Button buttonDrawBarCoMon;

    private Button battleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();



        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        textViewUserEmail.setText(""+user.getEmail());

        buttonLogout = (Button)findViewById(R.id.buttonLogout);
        buttonBarCoMon=(Button)findViewById(R.id.barcomon_button);
        buttonReview=(Button)findViewById(R.id.review_button);
        buttonSearch=(Button)findViewById(R.id.search_button);

        buttonDrawBarCoMon = (Button)findViewById(R.id.drawBarCoMon_button);

        battleButton=(Button)findViewById(R.id.battle_button);

        battleButton.setOnClickListener(this);


        buttonLogout.setOnClickListener(this);
        buttonBarCoMon.setOnClickListener(this);
        buttonReview.setOnClickListener(this);

        buttonDrawBarCoMon.setOnClickListener(this);

        buttonSearch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==buttonLogout){
            firebaseAuth.signOut();
            //finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==buttonBarCoMon){
            finish();
            startActivity(new Intent(getApplicationContext(),BarCoMonGameConsole.class));
        }
        if(v==buttonReview){
            //finish();
            startActivity(new Intent(getApplicationContext(),BarCoReview.class));
        }
        if(v==buttonDrawBarCoMon){
            //finish();
            startActivity(new Intent(getApplicationContext(),DrawBarCoMon.class));
        }
        if(v==buttonSearch){
            startActivity(new Intent(getApplicationContext(),BattleSetUp.class));
        }
        if(v==battleButton){
            startActivity(new Intent(getApplicationContext(),BattleScene.class));
        }


    }
}
