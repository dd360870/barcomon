package com.example.barcomon;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.os.Handler;
import android.widget.Toast;

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

public class EquipmentPage extends Fragment implements View.OnClickListener{
    public String[] provinces = new String[] { "第一張", "第二張", "第三張"};
    ImageView equipmentCard1,equipmentCard2,equipmentCard3;


    UserItem useritem;

    ImageView capsuleLayout,bottleLogoLayout,cupSetLayout,pullRingLayout,coasterLayout,instantDrinkLayout
            ,chopsticksLayout,chickenLegsLayout,bookmarkLayout,bookClothingLayout,plasticbagLayout;

    EquipmentInformation equipmentInformation;

    UserInformation userinformation;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //defining a database reference
    private DatabaseReference databaseReference;

    private String equip1,equip2,equip3;

    private static final int SOUND_COUNT = 2;
    private SoundPool soundPool;
    private int CardSoundID;
    private int ClickCardID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.equipment_page,null);

        firebaseAuth = FirebaseAuth.getInstance();
        getUserItem();
        getUserInformation();

        equipmentCard1=(ImageView)view.findViewById(R.id.equipmentCard1);
        equipmentCard2=(ImageView)view.findViewById(R.id.equipmentCard2);
        equipmentCard3=(ImageView)view.findViewById(R.id.equipmentCard3);

        plasticbagLayout=(ImageView)view.findViewById(R.id.equipPagePlasticbagLayout);

        capsuleLayout=(ImageView) view.findViewById(R.id.equipPageCapsuleLayout);
        //cartonLayout=(LinearLayout)view.findViewById(R.id.equipPageCartonLayout);

        bottleLogoLayout=(ImageView) view.findViewById(R.id.equipPageBottleLogoLayout);
        bookmarkLayout=(ImageView) view.findViewById(R.id.equipPageBookmarkLayout);
        bookClothingLayout=(ImageView) view.findViewById(R.id.equipPageBookClothingLayout);

        cupSetLayout=(ImageView) view.findViewById(R.id.equipPageCupSetLayout);
        pullRingLayout=(ImageView) view.findViewById(R.id.equipPagePullRingLayout);


        chopsticksLayout=(ImageView) view.findViewById(R.id.equipPageChopsticksLayout);
        chickenLegsLayout=(ImageView) view.findViewById(R.id.equipPageChickenLegsLayout);
        coasterLayout=(ImageView) view.findViewById(R.id.equipPageCoasterLayout);
        instantDrinkLayout=(ImageView) view.findViewById(R.id.equipPageInstantDrinkLayout);

        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
        CardSoundID= this.soundPool.load(getActivity(), R.raw.cardsound, 1);
        ClickCardID= this.soundPool.load(getActivity(), R.raw.battleset_selectcard, 1);



        plasticbagLayout.setOnClickListener(this);
        capsuleLayout.setOnClickListener(this);
        //cartonLayout.setOnClickListener(this);

        bottleLogoLayout.setOnClickListener(this);
        bookmarkLayout.setOnClickListener(this);
        bookClothingLayout.setOnClickListener(this);
        cupSetLayout.setOnClickListener(this);
        pullRingLayout.setOnClickListener(this);
        chopsticksLayout.setOnClickListener(this);
        chickenLegsLayout.setOnClickListener(this);
        coasterLayout.setOnClickListener(this);
        instantDrinkLayout.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        soundPool.play(ClickCardID, 1, 1, 0, 0, 1);
        if(v==capsuleLayout){
            if(useritem.Capsule==1) {
                capSuleChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }

        if(v==bottleLogoLayout){
            if(useritem.BottleLogo==1) {
                bottleLogoChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bookmarkLayout){
            if(useritem.Bookmark==1) {
                bookmarkChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==bookClothingLayout){
            if(useritem.BookClothing==1) {
                bookClothingChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==cupSetLayout){
            if(useritem.CupSet==1){
            cupSetChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==pullRingLayout){
            if(useritem.PullRing==1) {
                pullRingChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==chopsticksLayout){
            if(useritem.Chopsticks==1) {
                chopsticksChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==chickenLegsLayout){
            if(useritem.ChickenLegs==1) {
                chickenLegsChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==coasterLayout){
            if(useritem.Coaster==1) {
                coasterChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==instantDrinkLayout){
            if(useritem.InstantDrink==1) {
                instantDrinkChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張裝備卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==plasticbagLayout){
            plasticChooseCardField(v);
        }

    }

    private void plasticChooseCardField(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){

                    case 0:

                        updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                        equip1="NoEquip";
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        break;
                    case 1:

                        updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                        equip2="NoEquip";
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        break;
                    case 2:

                        updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                        equip3="NoEquip";
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        break;


                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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


    private void capSuleChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Capsule")){

                        }
                        else if(equip2.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }

                        updateEquipmentCard(new EquipmentInformation("000","Capsule"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                        equip1="Capsule";

                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Capsule")){

                        }
                        else if(equip3.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }

                        updateEquipmentCard(new EquipmentInformation("000","Capsule"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                        equip2="Capsule";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Capsule")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Capsule")){

                        }

                        updateEquipmentCard(new EquipmentInformation("000","Capsule"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                        equip3="Capsule";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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





    private void bottleLogoChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BottleLogo")){

                        }
                        else if(equip2.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("001","BottleLogo"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                        equip1="BottleLogo";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("BottleLogo")){

                        }
                        else if(equip3.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("001","BottleLogo"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                        equip2="BottleLogo";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("BottleLogo")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("BottleLogo")){

                        }
                        updateEquipmentCard(new EquipmentInformation("001","BottleLogo"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                        equip3="BottleLogo";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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


    private void bookmarkChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Bookmark")){

                        }
                        else if(equip2.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("002","Bookmark"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                        equip1="Bookmark";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Bookmark")){

                        }
                        else if(equip3.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("002","Bookmark"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                        equip2="Bookmark";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Bookmark")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Bookmark")){

                        }
                        updateEquipmentCard(new EquipmentInformation("002","Bookmark"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                        equip3="Bookmark";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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





    private void bookClothingChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BookClothing")){

                        }
                        else if(equip2.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("003","BookClothing"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                        equip1="BookClothing";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("BookClothing")){

                        }
                        else if(equip3.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("003","BookClothing"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                        equip2="BookClothing";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("BookClothing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("BookClothing")){

                        }
                        updateEquipmentCard(new EquipmentInformation("003","BookClothing"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                        equip3="BookClothing";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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




    private void cupSetChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("CupSet")){

                        }
                        else if(equip2.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("004","CupSet"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                        equip1="CupSet";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("CupSet")){

                        }
                        else if(equip3.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("004","CupSet"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                        equip2="CupSet";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("CupSet")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("CupSet")){

                        }
                        updateEquipmentCard(new EquipmentInformation("004","CupSet"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                        equip3="CupSet";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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




    private void pullRingChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("PullRing")){

                        }
                        else if(equip2.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("005","PullRing"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                        equip1="PullRing";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("PullRing")){

                        }
                        else if(equip3.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("005","PullRing"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                        equip2="PullRing";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("PullRing")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("PullRing")){

                        }
                        updateEquipmentCard(new EquipmentInformation("005","PullRing"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                        equip3="PullRing";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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



    private void chopsticksChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Chopsticks")){

                        }
                        else if(equip2.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("006","Chopsticks"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                        equip1="Chopsticks";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Chopsticks")){

                        }
                        else if(equip3.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("006","Chopsticks"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                        equip2="Chopsticks";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Chopsticks")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Chopsticks")){

                        }
                        updateEquipmentCard(new EquipmentInformation("006","Chopsticks"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                        equip3="Chopsticks";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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


    private void chickenLegsChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("ChickenLegs")){

                        }
                        else if(equip2.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("007","ChickenLegs"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                        equip1="ChickenLegs";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("ChickenLegs")){

                        }
                        else if(equip3.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("007","ChickenLegs"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                        equip2="ChickenLegs";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("ChickenLegs")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("ChickenLegs")){

                        }
                        updateEquipmentCard(new EquipmentInformation("007","ChickenLegs"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                        equip3="ChickenLegs";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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



    private void coasterChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Coaster")){

                        }
                        else if(equip2.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("008","Coaster"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                        equip1="Coaster";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Coaster")){

                        }
                        else if(equip3.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("008","Coaster"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                        equip2="Coaster";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("Coaster")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("Coaster")){

                        }
                        updateEquipmentCard(new EquipmentInformation("008","Coaster"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                        equip3="Coaster";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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



    private void instantDrinkChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("InstantDrink")){

                        }
                        else if(equip2.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("009","InstantDrink"),1);
                        equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                        equip1="InstantDrink";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("InstantDrink")){

                        }
                        else if(equip3.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),3);
                            equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip3="NoEquip";
                        }
                        updateEquipmentCard(new EquipmentInformation("009","InstantDrink"),2);
                        equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                        equip2="InstantDrink";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(equip1.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),1);
                            equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip1="NoEquip";
                        }
                        else if(equip2.equals("InstantDrink")){
                            updateEquipmentCard(new EquipmentInformation("100","NoEquip"),2);
                            equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                            equip2="NoEquip";
                        }
                        else if(equip3.equals("InstantDrink")){

                        }
                        updateEquipmentCard(new EquipmentInformation("009","InstantDrink"),3);
                        equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                        equip3="InstantDrink";
                        break;
                }
                final AlertDialog ad = new AlertDialog.Builder(
                        v.getContext()).setMessage(
                        "選擇的順序為" + ": " + provinces[which]).show();
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

    private void updateEquipmentCard(final EquipmentInformation equip,int card){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card"+card).setValue(equip);
    }


    private void getUserItem(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("Item").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot){
                useritem=snapshot.getValue(UserItem.class);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

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
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        equipmentInformation = snapshot.getValue(EquipmentInformation.class);
                        if(isAdded()) {
                            if (equipmentInformation.Name.equals("Capsule")) {
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BottleLogo")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Bookmark")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BookClothing")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("CupSet")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("PullRing")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Chopsticks")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("ChickenLegs")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Coaster")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("InstantDrink")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                                equip1=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("NoEquip")){
                                equipmentCard1.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                                equip1=equipmentInformation.Name;
                            }
                        }
                        //snapshot.getRef().removeValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                        // Snip, not relevant
                    }
                });

                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        equipmentInformation = snapshot.getValue(EquipmentInformation.class);
                        if(isAdded()) {
                            if (equipmentInformation.Name.equals("Capsule")) {
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BottleLogo")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Bookmark")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BookClothing")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("CupSet")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("PullRing")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Chopsticks")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("ChickenLegs")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Coaster")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("InstantDrink")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                                equip2=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("NoEquip")){
                                equipmentCard2.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                                equip2=equipmentInformation.Name;
                            }
                        }
                        //snapshot.getRef().removeValue();
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                        // Snip, not relevant
                    }
                });
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        equipmentInformation = snapshot.getValue(EquipmentInformation.class);
                        if(isAdded()) {
                            if (equipmentInformation.Name.equals("Capsule")) {
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BottleLogo")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Bookmark")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("BookClothing")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("CupSet")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("PullRing")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Chopsticks")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("ChickenLegs")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("Coaster")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("InstantDrink")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
                                equip3=equipmentInformation.Name;
                            }
                            if(equipmentInformation.Name.equals("NoEquip")){
                                equipmentCard3.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
                                equip3=equipmentInformation.Name;
                            }
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
