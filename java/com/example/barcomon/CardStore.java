package com.example.barcomon;


import android.content.DialogInterface;
import android.content.Intent;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CardStore extends AppCompatActivity implements View.OnClickListener{


    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    //public LinearLayout capsuleLayout,cartonLayout;

    private ImageView capsule,bottleLogo,bookmark,bookclothing,cupset,pullring,chopsticks,chickenLeg
            ,coaster,instantDrink;

    public UserItem userItem;

    private UserInformation userInformation;

    private  int usedEnegy=20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_card_store);



        firebaseAuth = FirebaseAuth.getInstance();


        //if the user is not logged in
        //that means current user will return null
        if (firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting the database reference
        getUserInformation();

        getUserItemInformation();

        capsule=(ImageView)findViewById(R.id.cardStoreCapsule);
        bottleLogo=(ImageView)findViewById(R.id.cardStoreBottleLogo);
        bookmark=(ImageView)findViewById(R.id.cardStoreBookmark);
        bookclothing=(ImageView)findViewById(R.id.cardStoreBookclothing);
        cupset=(ImageView)findViewById(R.id.cardStoreCupset);
        pullring=(ImageView)findViewById(R.id.cardStorePullring);
        chopsticks=(ImageView)findViewById(R.id.cardStoreChopsticks);
        chickenLeg=(ImageView)findViewById(R.id.cardStoreChickenlegs);
        coaster=(ImageView)findViewById(R.id.cardStoreCoaster);
        instantDrink=(ImageView)findViewById(R.id.cardStoreInstantdrink);

        capsule.setOnClickListener(this);
        bottleLogo.setOnClickListener(this);
        bookmark.setOnClickListener(this);
        bookclothing.setOnClickListener(this);
        cupset.setOnClickListener(this);
        pullring.setOnClickListener(this);
        chopsticks.setOnClickListener(this);
        chickenLeg.setOnClickListener(this);
        coaster.setOnClickListener(this);
        instantDrink.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        if(v==capsule){
            if(userItem.Capsule==0) {
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("Capsule");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bottleLogo){
            if(userItem.BottleLogo==0) {
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("BottleLogo");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bookmark){
            if(userItem.Bookmark==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("Bookmark");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bookclothing){
            if(userItem.BookClothing==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("BookClothing");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==cupset){
            if(userItem.CupSet==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("CupSet");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==pullring){
            if(userItem.PullRing==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("PullRing");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==chopsticks){
            if(userItem.Chopsticks==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("Chopsticks");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==chickenLeg){
            if(userItem.ChickenLegs==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("ChickenLegs");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==coaster){
            if(userItem.Coaster==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("Coaster");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==instantDrink){
            if(userItem.InstantDrink==0){
                if(userInformation.BarCoMonEnergy>=usedEnegy) {
                    confirmBuyEqipment("InstantDrink");
                }
                else{
                    Toast.makeText(this, "能量不足，無法購買", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "你已擁有此裝備", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void updateEquipment() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("Item").setValue(userItem);
        Toast.makeText(this, "謝謝惠顧~", Toast.LENGTH_LONG).show();
    }


    private void getUserInformation(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userInformation = snapshot.getValue(UserInformation.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });
    }

    private void getUserItemInformation() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.child(user.getUid()).child("Item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userItem = snapshot.getValue(UserItem.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

    }


    private void updateUserInformation(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").setValue(userInformation);
    }

    private void confirmBuyEqipment(final String item){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確認購買此商品").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userInformation.BarCoMonEnergy-=usedEnegy;

                        if(item.equals("Capsule")) userItem.Capsule++;
                        if(item.equals("BottleLogo"))userItem.BottleLogo++;
                        if(item.equals("Bookmark"))userItem.Bookmark++;
                        if(item.equals("BookClothing"))userItem.BookClothing++;
                        if(item.equals("CupSet"))userItem.CupSet++;
                        if(item.equals("PullRing"))userItem.PullRing++;
                        if(item.equals("Chopsticks"))userItem.Chopsticks++;
                        if(item.equals("ChickenLegs"))userItem.ChickenLegs++;
                        if(item.equals("Coaster"))userItem.Coaster++;
                        if(item.equals("InstantDrink"))userItem.InstantDrink++;

                        updateUserInformation();
                        updateEquipment();
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

    private void Leave() {
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exit application?").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        //關閉程式
                        CardStore.this.finish();
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

}
