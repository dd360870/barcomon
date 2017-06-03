package com.example.barcomon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by BoAn on 2017/3/12.
 */

public class TrapPage extends Fragment implements View.OnClickListener{
    public String[] provinces = new String[] { "第一張", "第二張", "第三張"};
    ImageView trapCard1,trapCard2,trapCard3;
    LinearLayout brokenHeartLayout,protectingShieldLayout;

    //SkillInformation skillInformation;
    TrapInformation trapInformation;

    UserInformation userinformation;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //defining a database reference
    private DatabaseReference databaseReference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.trap_page,null);

        firebaseAuth = FirebaseAuth.getInstance();
        getUserInformation();

        trapCard1=(ImageView)view.findViewById(R.id.trapCard1);
        trapCard2=(ImageView)view.findViewById(R.id.trapCard2);
        trapCard3=(ImageView)view.findViewById(R.id.trapCard3);

        brokenHeartLayout=(LinearLayout)view.findViewById(R.id.trapPageBrokenHeartLayout);
        protectingShieldLayout=(LinearLayout)view.findViewById(R.id.trapPageProtectingShieldLayout);

        brokenHeartLayout.setOnClickListener(this);
        protectingShieldLayout.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==brokenHeartLayout){
            brokenHeartChooseCardField(v);
        }
        if(v==protectingShieldLayout){
            protectingShieldChooseCardField(v);
        }

    }

    private void brokenHeartChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        updateTrapCard(new TrapInformation("000","BrokenHeart"),1);
                        trapCard1.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        break;
                    case 1:
                        updateTrapCard(new TrapInformation("000","BrokenHeart"),2);
                        trapCard2.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        break;
                    case 2:
                        updateTrapCard(new TrapInformation("000","BrokenHeart"),3);
                        trapCard3.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" +": " + provinces[which]).show();
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ad.dismiss();
                    }

                };
                handler.postDelayed(runnable, 5 * 1000);
            }

        });
        builder.create().show();
    }

    private void protectingShieldChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        updateTrapCard(new TrapInformation("001","ProtectingShield"),1);
                        trapCard1.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        break;
                    case 1:
                        updateTrapCard(new TrapInformation("001","ProtectingShield"),2);
                        trapCard2.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        break;
                    case 2:
                        updateTrapCard(new TrapInformation("001","ProtectingShield"),3);
                        trapCard3.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" +": " + provinces[which]).show();
                Handler handler = new Handler();
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        ad.dismiss();
                    }

                };
                handler.postDelayed(runnable, 5 * 1000);
            }

        });
        builder.create().show();
    }



    private void setEquipmentCard(){


    }

    private void updateTrapCard(final TrapInformation trap,int card){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("TrapInformation").child("Card"+card).setValue(trap);
    }

    private void getUserInformation(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);
                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("TrapInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {/////////////////////////////////////////////////
                        trapInformation = snapshot.getValue(TrapInformation.class);
                        if(trapInformation.Name.equals("BrokenHeart")){
                            trapCard1.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        }
                        if(trapInformation.Name.equals("ProtectingShield")){
                            trapCard1.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        }
                        //else{trapCard1.setImageDrawable(getResources().getDrawable(R.drawable.cardback));}
                        //snapshot.getRef().removeValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                        // Snip, not relevant
                    }
                });

                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("TrapInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        trapInformation = snapshot.getValue(TrapInformation.class);
                        if(trapInformation.Name.equals("BrokenHeart")){
                            trapCard2.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        }
                        if(trapInformation.Name.equals("ProtectingShield")){
                            trapCard2.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        }
                        //snapshot.getRef().removeValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                        // Snip, not relevant
                    }
                });
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("TrapInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        trapInformation = snapshot.getValue(TrapInformation.class);
                        if(trapInformation.Name.equals("BrokenHeart")){
                            trapCard3.setImageDrawable(getResources().getDrawable(R.drawable.brokenheart));
                        }
                        if(trapInformation.Name.equals("ProtectingShield")){
                            trapCard3.setImageDrawable(getResources().getDrawable(R.drawable.protectingshield));
                        }
                        //snapshot.getRef().removeValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                        // Snip, not relevant
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

    }


}
