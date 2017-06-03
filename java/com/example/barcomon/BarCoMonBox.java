package com.example.barcomon;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BarCoMonBox extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;


    private ImageView monsterBottle,monsterCan,monsterMug,monsterBento,
    monsterBook;


    public UserInformation userinformation;

    private static final int SOUND_COUNT = 2;
    private SoundPool soundPool;
    private int ClickSoundID;
    private int ChangeMonsterSoundID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bar_co_mon_box);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
        ClickSoundID= this.soundPool.load(this, R.raw.consloebutton, 1);
        ChangeMonsterSoundID=this.soundPool.load(this, R.raw.changemonster, 1);

        getUserInformation();
        //monster0 = (Button)findViewById(R.id.monster0_button);


        //monster0.setOnClickListener(this);

        monsterBottle=(ImageView)findViewById(R.id.monsterBoxBottle);
        monsterCan=(ImageView)findViewById(R.id.monsterBoxCans);
        monsterMug=(ImageView)findViewById(R.id.monsterBoxMug);
        monsterBento=(ImageView)findViewById(R.id.monsterBoxBento);
        monsterBook=(ImageView)findViewById(R.id.monsterBoxBook);


        monsterBottle.setOnClickListener(this);
        monsterCan.setOnClickListener(this);
        monsterMug.setOnClickListener(this);
        monsterBento.setOnClickListener(this);
        monsterBook.setOnClickListener(this);


        monsterBottle.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                if(userinformation.ownMonster.charAt(0)=='1') {
                    confirmChangeMainMonster("000");
                }
                return true;
            }
        });

        monsterCan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                if(userinformation.ownMonster.charAt(1)=='1') {
                    confirmChangeMainMonster("001");
                }
                return true;
            }
        });

        monsterMug.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                if(userinformation.ownMonster.charAt(2)=='1') {
                    confirmChangeMainMonster("002");
                }
                return true;
            }
        });

        monsterBento.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                if(userinformation.ownMonster.charAt(3)=='1') {
                    confirmChangeMainMonster("003");
                }
                return true;
            }
        });

        monsterBook.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                if(userinformation.ownMonster.charAt(4)=='1') {
                    confirmChangeMainMonster("004");
                }
                return true;
            }
        });




    }
    private void confirmChangeMainMonster(final String mainMonster){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("更換怪獸0.0?").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        soundPool.play(ChangeMonsterSoundID, 1, 1, 0, 0, 1);
                        updateUserDatabase(mainMonster);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                //關閉AlertDialog視窗
                dialog.cancel();
            }
        });
        AlertDialog about_dialog = builder.create();
        about_dialog.show();
    }

    @Override
    public void onClick(View v) {
        soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
        if(v==monsterBottle){
            startActivity(new Intent(getApplicationContext(),BottleMonsterCard.class));
            //updateUserDatabase("000");
        }

        if(v==monsterCan){
            startActivity(new Intent(getApplicationContext(),CanMonsterCard.class));
        }
        if(v==monsterMug){
            startActivity(new Intent(getApplicationContext(),MugMonsterCard.class));
        }
        if(v==monsterBento){
            startActivity(new Intent(getApplicationContext(),BentoMonsterCard.class));
        }
        if(v==monsterBook){
            startActivity(new Intent(getApplicationContext(),BookMonsterCard.class));
        }
    }

    public void updateUserDatabase(String main){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        UserInformation userinfo = new UserInformation(userinformation.name,main,userinformation.BarCoMonEnergy,userinformation.ownMonster);
        databaseReference.child(user.getUid()).child("userinformation").setValue(userinfo);
    }

    public void getUserInformation(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);
                //str+="OwnMonster: "+userinformation.ownMonster+"\n"+"Energy: "+userinformation.BarCoMonEnergy;
                //TextViewuseremail.setText("OwnMonster: "+userinformation.ownMonster+"\n"+"Energy: "+userinformation.BarCoMonEnergy);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

    }
}
