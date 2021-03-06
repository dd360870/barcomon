package com.example.barcomon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookMonsterCard extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    private TextView LevelTextView,AttackTextView,DefenseTextView,LoveLevelTextView,
            StrengthTextView;

    private Monster monster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_book_monster_card);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }


        LevelTextView=(TextView)findViewById(R.id.bookMonsterCardLevelTextView);
        AttackTextView=(TextView)findViewById(R.id.bookMonsterCardAttackTextView);
        DefenseTextView=(TextView)findViewById(R.id.bookMonsterCardDefenseTextView);
        LoveLevelTextView=(TextView)findViewById(R.id.bookMonsterCardLoveLevelTextView);
        StrengthTextView=(TextView)findViewById(R.id.bookMonsterCardStrength);

        getMonsterInformation();

    }



    public void getMonsterInformation(){

        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        FirebaseUser user = firebaseAuth.getCurrentUser();


        databaseReference.child(user.getUid()).child("004").child("MonsterInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                monster = snapshot.getValue(Monster.class);


                LevelTextView.setText(monster.UsergetLevel()+"");
                AttackTextView.setText(monster.UsergetAttack()+"");
                DefenseTextView.setText(monster.UsergetDefense()+"");
                LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
                StrengthTextView.setText(monster.UsergetStrength()+"");



            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
