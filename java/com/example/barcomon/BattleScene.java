package com.example.barcomon;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.barcomon.Equipment.*;
import com.example.barcomon.Skill.*;
import com.example.barcomon.battleMonster.BattleMonster;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Iterator;

import me.trojx.dancingnumber.DancingNumberView;

public class BattleScene extends AppCompatActivity implements View.OnClickListener{
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private  UserInformation userinformation;
    private  UserInformation enemyUserInformation;
    private ImageView myMonsterImageView,enemyMonsterImageView;
    private ImageView myMonsterEquipmentCard1ImageView,myMonsterEquipmentCard2ImageView,myMonsterEquipmentCard3ImageView;
    private ImageView myMonsterSkillCard1ImageView,myMonsterSkillCard2ImageView,myMonsterSkillCard3ImageView;
    private ImageView enemyMonsterEquipmentCard1ImageView,enemyMonsterEquipmentCard2ImageView,enemyMonsterEquipmentCard3ImageView;
    private ImageView enemyMonsterSkillCard1ImageView,enemyMonsterSkillCard2ImageView,enemyMonsterSkillCard3ImageView;
    private TextView myMonsterAttackTextView,myMonsterDefenseTextView,myMonsterLevelTextView,myMonsterLoveLevelTextView,myMonsterStrengthTextView;

    private ImageView attackImageView;

    private TextView enemyMonsterAttackTextView,enemyMonsterDefenseTextView,enemyMonsterLevelTextView,enemyMonsterLoveLevelTextView,enemyMonsterStrengthTextView;

    private TextView enemyDialog,myDialog;

    private Monster myMonster;
    private Monster enemyMonster;

    private EquipmentInformation myEquipmentInfo;
    private SkillInformation mySkillInfo;

    private BattleMonster myBattleMonster,enemyBattleMonster;


    public static ObjectAnimator myMonsterEquipmentCard1Animation;

    DialogReply dialogReply;

    private String myMainMonster;


    int round=1;

    int checkEquip=0;
    int checkSkill=0;


    int myMonsterSetEquip=-1;
    int myMonsterSetSkill=-1;

    int setEquipFirst=0;
    int setSkillFirst=0;

    int IsmyEquip1Used=0;
    int IsmyEquip2Used=0;
    int IsmyEquip3Used=0;
    int IsmySkill1Used=0;
    int IsmySkill2Used=0;
    int IsmySkill3Used=0;

    int originalMyMonsterAttack=0;
    int originalMyMonsterDefense=0;
    int originalEnemyMonsterAttack=0;
    int originalEnemyMonsterDefense=0;

    int myMonsterAttack=0;
    int myMonsterDefense=0;
    int enemyMonsterAttack=0;
    int enemyMonsterDefense=0;

    int isNoDamage=1;


    int[] choosedEquipSkill=new int[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_battle_scene);

        firebaseAuth = FirebaseAuth.getInstance();

        myMonsterImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterImageView);
        enemyMonsterImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterImageView);

        myMonsterAttackTextView=(TextView)findViewById(R.id.battleSceneMyMonsterAttackTextView);
        myMonsterDefenseTextView=(TextView)findViewById(R.id.battleSceneMyMonsterDefenseTextView);
        myMonsterLevelTextView=(TextView)findViewById(R.id.battleSceneMyMonsterLevelTextView);
        myMonsterLoveLevelTextView=(TextView)findViewById(R.id.battleSceneMyMonsterLoveLevelTextView);

        myMonsterStrengthTextView=(TextView)findViewById(R.id.battleSceneMyMonsterStrengthTextView);

        enemyMonsterAttackTextView=(TextView)findViewById(R.id.battleSceneEnemyMonsterAttackTextView);
        enemyMonsterDefenseTextView=(TextView)findViewById(R.id.battleSceneEnemyMonsterDefenseTextView);
        enemyMonsterLevelTextView=(TextView)findViewById(R.id.battleSceneEnemyMonsterlevelTextView);
        enemyMonsterLoveLevelTextView=(TextView)findViewById(R.id.battleSceneEnemyMonsterLoveLevelTextView);

        enemyMonsterStrengthTextView=(TextView)findViewById(R.id.battleSceneEnemyMonsterStrengthTextView);

        myMonsterEquipmentCard1ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterEquipmentCard1);
        myMonsterEquipmentCard2ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterEquipmentCard2);
        myMonsterEquipmentCard3ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterEquipmentCard3);
        myMonsterSkillCard1ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterSkillCard1);
        myMonsterSkillCard2ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterSkillCard2);
        myMonsterSkillCard3ImageView=(ImageView)findViewById(R.id.battleSceneMyMonsterSkillCard3);

        enemyMonsterEquipmentCard1ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterEquipmentCard1);
        enemyMonsterEquipmentCard2ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterEquipmentCard2);
        enemyMonsterEquipmentCard3ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterEquipmentCard3);
        enemyMonsterSkillCard1ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterSkillCard1);
        enemyMonsterSkillCard2ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterSkillCard2);
        enemyMonsterSkillCard3ImageView=(ImageView)findViewById(R.id.battleSceneEnemyMonsterSkillCard3);


        enemyDialog=(TextView)findViewById(R.id.battleSceneEnemyDialogTextView);
        myDialog=(TextView)findViewById(R.id.battleSceneMyDialogTextView);

        attackImageView=(ImageView)findViewById(R.id.battleSceneAttackButton);

        attackImageView.setOnClickListener(this);


        myMonsterEquipmentCard1ImageView.setOnClickListener(this);
        myMonsterEquipmentCard2ImageView.setOnClickListener(this);
        myMonsterEquipmentCard3ImageView.setOnClickListener(this);

        myMonsterSkillCard1ImageView.setOnClickListener(this);
        myMonsterSkillCard2ImageView.setOnClickListener(this);
        myMonsterSkillCard3ImageView.setOnClickListener(this);

        myMonsterImageView.setOnClickListener(this);

        choosedEquipSkill[0]=-1;
        choosedEquipSkill[1]=-1;

        dialogReply=new DialogReply();


        getMyMonster();
        getEnemyMonster();
        getenemyMonster();
    }

    public void getUserMonster(){

    }

    private void getMyMonster(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);
                myMainMonster=userinformation.MainMonster;
                if(userinformation.MainMonster.equals("000")){
                    myMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottle));
                }
                else if(userinformation.MainMonster.equals("001")){
                    myMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.can));
                }
                else if(userinformation.MainMonster.equals("002")){
                    myMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupfairy));
                }
                else if(userinformation.MainMonster.equals("003")){
                    myMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.book));
                }
                else if(userinformation.MainMonster.equals("004")){
                    myMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottle));

                }
                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("MonsterInformation").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myMonster = snapshot.getValue(Monster.class);

                        myBattleMonster=new Bottle(myMonster,"Bottle");

                        originalMyMonsterAttack=myMonster.UsergetAttack();
                        myMonsterAttack=originalMyMonsterAttack;
                        originalMyMonsterDefense=myMonster.UsergetDefense();
                        myMonsterDefense=originalMyMonsterDefense;

                        setMyMonsterAttackValue(0.0f,myMonster.UsergetAttack(),3000);
                        setMyMonsterDefenseValue(0.0f,myMonster.UsergetDefense(),3000);
                        setMyMonsterLevelValue(0.0f,myMonster.UsergetLevel(),3000);
                        setMyMonsterLoveLevelValue(0.0f,myMonster.UsergetLoveLevel(),3000);
                        setMyMonsterStrengthValue(0.0f,myMonster.UsergetStrength(),3000);
                        //myMonsterAttackTextView.setText(myMonster.UsergetAttack()+"");
                        //myMonsterDefenseTextView.setText(myMonster.UsergetDefense()+"");
                        //myMonsterLoveLevelTextView.setText(myMonster.UsergetLoveLevel()+"");
                        //myMonsterLevelTextView.setText(myMonster.UsergetLevel()+"");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        myMonsterSetEquipmentCard1ImageView(myEquipmentInfo.Name);

                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        //if(myEquipmentInfo.Name.equals("Carton"))myBattleMonster.equipmentList.add(n)
                        setMyMonsterEquipmentCard(myEquipmentInfo.Name);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        myMonsterSetEquipmentCard2ImageView(myEquipmentInfo.Name);
                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        setMyMonsterEquipmentCard(myEquipmentInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("EquipmentInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        myMonsterSetEquipmentCard3ImageView(myEquipmentInfo.Name);
                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        setMyMonsterEquipmentCard(myEquipmentInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        myMonsterSetSkillCard1ImageView(mySkillInfo.Name);
                        setMyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        myMonsterSetSkillCard2ImageView(mySkillInfo.Name);
                        setMyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        myMonsterSetSkillCard3ImageView(mySkillInfo.Name);
                        setMyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }

    public void myMonsterSetEquipmentCard1ImageView(String equipName){
        if(equipName.equals("Capsule")) myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))myMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));

    }
    public void myMonsterSetEquipmentCard2ImageView(String equipName){
        if(equipName.equals("Capsule")) myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))myMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));


    }
    public void myMonsterSetEquipmentCard3ImageView(String equipName){
        if(equipName.equals("Capsule")) myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))myMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));

    }
    public void myMonsterSetSkillCard1ImageView(String skillName){
        if(skillName.equals("StrongWater")) myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))myMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }
    public void myMonsterSetSkillCard2ImageView(String skillName){
        if(skillName.equals("StrongWater")) myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))myMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }
    public void myMonsterSetSkillCard3ImageView(String skillName){
        if(skillName.equals("StrongWater")) myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))myMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }


    public void getenemyMonster(){
        //questionsRef = FirebaseDatabase.getInstance().getReference().child("questions").child("DE");

        databaseReference = FirebaseDatabase.getInstance().getReference("users");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //int questionCount = (int) dataSnapshot.getChildrenCount();
                //int rand = random.nextInt(questionCount);
                Iterator itr = dataSnapshot.getChildren().iterator();

                for(int i = 0; i < 1; i++) {
                    itr.next();
                }
                //dataSnapshot.child("userinformation");

                enemyUserInformation= (((DataSnapshot) itr.next()).child("userinformation")).getValue(UserInformation.class);

                myDialog.setText(enemyUserInformation.name);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void getEnemyMonster(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);

                enemyMonsterImageView.setImageDrawable(getResources().getDrawable(R.drawable.can));

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child("001").child("MonsterInformation").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        enemyMonster = snapshot.getValue(Monster.class);
                        enemyBattleMonster=new Bottle(enemyMonster,"Bottle");
                        //enemyMonsterAttackTextView.setText(enemyMonster.UsergetAttack()+"");
                        //enemyMonsterDefenseTextView.setText(enemyMonster.UsergetDefense()+"");
                        //enemyMonsterLoveLevelTextView.setText(enemyMonster.UsergetLoveLevel()+"");
                        //enemyMonsterLevelTextView.setText(enemyMonster.UsergetLevel()+"");
                        originalEnemyMonsterAttack=enemyMonster.UsergetAttack();
                        enemyMonsterAttack=originalEnemyMonsterAttack;
                        originalEnemyMonsterDefense=enemyMonster.UsergetDefense();
                        enemyMonsterDefense=originalEnemyMonsterDefense;

                        setEnemyMonsterAttackValue(0.0f,enemyMonster.UsergetAttack(),3000);
                        setEnemyMonsterDefenseValue(0.0f,enemyMonster.UsergetDefense(),3000);
                        setEnemyMonsterLevelValue(0.0f,enemyMonster.UsergetLevel(),3000);
                        setEnemyMonsterLoveLevelValue(0.0f,enemyMonster.UsergetLoveLevel(),3000);
                        setEnemyMonsterStrengthValue(0.0f,enemyMonster.UsergetStrength(),3000);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("EquipmentInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        //enemyMonsterSetEquipmentCard1ImageView(myEquipmentInfo.Name);
                        enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));


                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        //if(myEquipmentInfo.Name.equals("Carton"))myBattleMonster.equipmentList.add(n)
                        setEnemyMonsterEquipmentCard(myEquipmentInfo.Name);

                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("EquipmentInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        //enemyMonsterSetEquipmentCard2ImageView(myEquipmentInfo.Name);
                        enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));


                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        setEnemyMonsterEquipmentCard(myEquipmentInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("EquipmentInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        myEquipmentInfo = snapshot.getValue(EquipmentInformation.class);
                        //enemyMonsterSetEquipmentCard3ImageView(myEquipmentInfo.Name);
                        enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));


                        //if(myEquipmentInfo.Name.equals("Capsule"))myBattleMonster.equipmentList.add(new Capsule());
                        setEnemyMonsterEquipmentCard(myEquipmentInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("SkillInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        //enemyMonsterSetSkillCard1ImageView(mySkillInfo.Name);
                        enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));



                        setEnemyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("SkillInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));


                        //enemyMonsterSetSkillCard2ImageView(mySkillInfo.Name);
                        setEnemyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                databaseReference.child(user.getUid()).child("001").child("BattleInformation").child("SkillInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        mySkillInfo = snapshot.getValue(SkillInformation.class);
                        enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cardback));


                        //enemyMonsterSetSkillCard3ImageView(mySkillInfo.Name);
                        setEnemyMonsterSkillCard(mySkillInfo.Name);
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });






            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }









    public void enemyMonsterSetEquipmentCard1ImageView(String equipName){
        if(equipName.equals("Capsule")) enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))enemyMonsterEquipmentCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));
    }
    public void enemyMonsterSetEquipmentCard2ImageView(String equipName){
        if(equipName.equals("Capsule")) enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))enemyMonsterEquipmentCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));


    }
    public void enemyMonsterSetEquipmentCard3ImageView(String equipName){
        if(equipName.equals("Capsule")) enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.capsule));
        if(equipName.equals("Carton")) enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.carton));
        if(equipName.equals("BottleLogo")) enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottlelogo));
        if(equipName.equals("Bookmark"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookmark));
        if(equipName.equals("BookClothing"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.bookclothing));
        if(equipName.equals("CupSet"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupset));
        if(equipName.equals("PullRing"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.pullring));
        if(equipName.equals("Chopsticks"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chopsticks));
        if(equipName.equals("ChickenLegs"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.chickenlegs));
        if(equipName.equals("Coaster"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coaster));
        if(equipName.equals("InstantDrink"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.instantdrink));
        if(equipName.equals("NoEquip"))enemyMonsterEquipmentCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noequip));

    }
    public void enemyMonsterSetSkillCard1ImageView(String skillName){
        if(skillName.equals("StrongWater")) enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))enemyMonsterSkillCard1ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }
    public void enemyMonsterSetSkillCard2ImageView(String skillName){
        if(skillName.equals("StrongWater")) enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))enemyMonsterSkillCard2ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }
    public void enemyMonsterSetSkillCard3ImageView(String skillName){
        if(skillName.equals("StrongWater")) enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
        if(skillName.equals("H2SO4"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
        if(skillName.equals("PaperPlane")) enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
        if(skillName.equals("Encyclopedia"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
        if(skillName.equals("Compression"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.compression));
        if(skillName.equals("CansScroll"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
        if(skillName.equals("SweetSmell"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
        if(skillName.equals("RottenFood"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
        if(skillName.equals("HotWater"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
        if(skillName.equals("Coffee"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
        if(skillName.equals("NoSkill"))enemyMonsterSkillCard3ImageView.setImageDrawable(getResources().getDrawable(R.drawable.noskill));

    }

    public void setEnemyEquipmentCard(){
        if(round==1){
            Equipment equip=enemyBattleMonster.equipmentList.get(0);
            enemyMonsterSetEquipmentCard1ImageView(equip.equipmentName);


            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterEquipmentCard1ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=enemyMonsterAttack;
            float defenseBefore=enemyMonsterDefense;

            //enemyBattleMonster.SetUpEquipment(enemyBattleMonster.equipmentList.get(0));

            setEnemyDialog(dialogReply.setEnemyEquipment(equip.equipmentName));
            enemyMonsterAttack+=equip.upgradeAttack;
            enemyMonsterDefense+=equip.upgradeDefense;

            setEnemyMonsterAttackValue(attackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(defenseBefore,enemyMonsterDefense,3000);

        }

        else if(round==2){
            Equipment equip=enemyBattleMonster.equipmentList.get(1);
            enemyMonsterSetEquipmentCard2ImageView(equip.equipmentName);

            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterEquipmentCard2ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=enemyMonsterAttack;
            float defenseBefore=enemyMonsterDefense;

            //enemyBattleMonster.SetUpEquipment(enemyBattleMonster.equipmentList.get(0));

            setEnemyDialog(dialogReply.setEnemyEquipment(equip.equipmentName));
            enemyMonsterAttack+=equip.upgradeAttack;
            enemyMonsterDefense+=equip.upgradeDefense;

            setEnemyMonsterAttackValue(attackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(defenseBefore,enemyMonsterDefense,3000);
        }
        else if(round==3){
            Equipment equip=enemyBattleMonster.equipmentList.get(2);
            enemyMonsterSetEquipmentCard3ImageView(equip.equipmentName);

            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterEquipmentCard3ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=enemyMonsterAttack;
            float defenseBefore=enemyMonsterDefense;

            //enemyBattleMonster.SetUpEquipment(enemyBattleMonster.equipmentList.get(0));


            setEnemyDialog(dialogReply.setEnemyEquipment(equip.equipmentName));

            enemyMonsterAttack+=equip.upgradeAttack;
            enemyMonsterDefense+=equip.upgradeDefense;

            setEnemyMonsterAttackValue(attackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(defenseBefore,enemyMonsterDefense,3000);
        }
    }


    public void setEnemySkillCard(int myMonsterAttackBefore,int myMonsterDefenseBefore,int enemyMonsterAttackBefore,int enemyMonsterDefenseBefore){
        if(round==1){
            Skill skill=enemyBattleMonster.skillList.get(0);

            enemyMonsterSetSkillCard1ImageView(skill.skillName);

            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterSkillCard1ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();



            //enemyBattleMonster.SetUpSkill(enemyBattleMonster.skillList.get(0));



            setEnemyDialog(dialogReply.setEnemySkill(skill.skillName));

            enemyMonsterAttack+=(int)(enemyMonsterAttack*skill.upgradeAttack);
            enemyMonsterDefense+=(int)(enemyMonsterDefense*skill.upgradeDefense);

            enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reducedAttack);
            enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceDefense);

            myMonsterAttack-=(int)(myMonsterAttack*skill.reduceEnemyAttack);
            myMonsterDefense-=(int)(myMonsterDefense*skill.reduceEnemyDense);



            setMyMonsterAttackValue(myMonsterAttackBefore,myMonsterAttack,3000);
            setMyMonsterDefenseValue(myMonsterDefenseBefore,myMonsterDefense,3000);
            setEnemyMonsterAttackValue(enemyMonsterAttackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(enemyMonsterDefenseBefore,enemyMonsterDefense,3000);

        }

        else if(round==2){
            Skill skill=enemyBattleMonster.skillList.get(1);
            enemyMonsterSetSkillCard2ImageView(skill.skillName);

            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterSkillCard2ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();


            setEnemyDialog(dialogReply.setEnemySkill(skill.skillName));

            enemyMonsterAttack+=(int)(enemyMonsterAttack*skill.upgradeAttack);
            enemyMonsterDefense+=(int)(enemyMonsterDefense*skill.upgradeDefense);

            enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reducedAttack);
            enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceDefense);

            myMonsterAttack-=(int)(myMonsterAttack*skill.reduceEnemyAttack);
            myMonsterDefense-=(int)(myMonsterDefense*skill.reduceEnemyDense);



            setMyMonsterAttackValue(myMonsterAttackBefore,myMonsterAttack,3000);
            setMyMonsterDefenseValue(myMonsterDefenseBefore,myMonsterDefense,3000);
            setEnemyMonsterAttackValue(enemyMonsterAttackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(enemyMonsterDefenseBefore,enemyMonsterDefense,3000);
        }
        else if(round==3){
            Skill skill=enemyBattleMonster.skillList.get(2);
            enemyMonsterSetSkillCard3ImageView(skill.skillName);
            ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterSkillCard3ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();


            setEnemyDialog(dialogReply.setEnemySkill(skill.skillName));

            enemyMonsterAttack+=(int)(enemyMonsterAttack*skill.upgradeAttack);
            enemyMonsterDefense+=(int)(enemyMonsterDefense*skill.upgradeDefense);

            enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reducedAttack);
            enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceDefense);

            myMonsterAttack-=(int)(myMonsterAttack*skill.reduceEnemyAttack);
            myMonsterDefense-=(int)(myMonsterDefense*skill.reduceEnemyDense);



            setMyMonsterAttackValue(myMonsterAttackBefore,myMonsterAttack,3000);
            setMyMonsterDefenseValue(myMonsterDefenseBefore,myMonsterDefense,3000);
            setEnemyMonsterAttackValue(enemyMonsterAttackBefore,enemyMonsterAttack,3000);
            setEnemyMonsterDefenseValue(enemyMonsterDefenseBefore,enemyMonsterDefense,3000);
        }
    }

    private void confirmSetEqipmentCard1(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetEquip=0;
                        checkEquip=1;
                        IsmyEquip1Used=1;

                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard1ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();
                        float attackBefore=myMonsterAttack;
                        float defenseBefore=myMonsterDefense;



                        //myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(0));

                        Equipment equip=myBattleMonster.equipmentList.get(0);

                        myMonsterAttack+=equip.upgradeAttack;
                        myMonsterDefense+=equip.upgradeDefense;

                        setMyMonsterAttackValue(attackBefore,myMonsterAttack,3000);
                        setMyMonsterDefenseValue(defenseBefore,myMonsterDefense,3000);
                        //myMonsterEquipmentCard1ImageView.setClickable(false);

                        setEnemyEquipmentCard();

                        setMyDialog(dialogReply.setMyEquipment(equip.equipmentName));

                        if(setEquipFirst==0 && setSkillFirst==0){
                            setEquipFirst=1;
                        }


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

    private void confirmSetEqipmentCard2(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetEquip=1;
                        checkEquip=1;
                        IsmyEquip2Used=1;

                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard2ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();
                        //float attackBefore=myBattleMonster.Attack;
                        //float defenseBefore=myBattleMonster.Defense;
                        //myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(1));

                        float attackBefore=myMonsterAttack;
                        float defenseBefore=myMonsterDefense;



                        //myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(0));

                        Equipment equip=myBattleMonster.equipmentList.get(1);

                        myMonsterAttack+=equip.upgradeAttack;
                        myMonsterDefense+=equip.upgradeDefense;

                        setMyMonsterAttackValue(attackBefore,myMonsterAttack,3000);
                        setMyMonsterDefenseValue(defenseBefore,myMonsterDefense,3000);




                        //myMonsterEquipmentCard2ImageView.setClickable(false);

                        setEnemyEquipmentCard();

                        setMyDialog(dialogReply.setMyEquipment(equip.equipmentName));

                        if(setEquipFirst==0 && setSkillFirst==0){
                            setEquipFirst=1;
                        }


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

    private void confirmSetEqipmentCard3(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetEquip=2;
                        checkEquip=1;
                        IsmyEquip3Used=1;


                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard3ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();
                        float attackBefore=myMonsterAttack;
                        float defenseBefore=myMonsterDefense;



                        //myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(0));

                        Equipment equip=myBattleMonster.equipmentList.get(2);

                        myMonsterAttack+=equip.upgradeAttack;
                        myMonsterDefense+=equip.upgradeDefense;

                        setMyMonsterAttackValue(attackBefore,myMonsterAttack,3000);
                        setMyMonsterDefenseValue(defenseBefore,myMonsterDefense,3000);
                        //myMonsterEquipmentCard3ImageView.setClickable(false);

                        setEnemyEquipmentCard();

                        setMyDialog(dialogReply.setMyEquipment(equip.equipmentName));
                        if(setEquipFirst==0 && setSkillFirst==0){
                            setEquipFirst=1;
                        }


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

    private void confirmSetSkillCard1(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetSkill=0;
                        checkSkill=1;
                        IsmySkill1Used=1;

                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard1ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();




                        //myBattleMonster.SetUpSkill(myBattleMonster.skillList.get(0));

                        int myMonsterAttackBefore=myMonsterAttack;
                        int myMonsterDefenseBefore=myMonsterDefense;

                        int enemyMonsterAttackBefore=enemyMonsterAttack;
                        int enemyMonsterDefenseBefore=enemyMonsterDefense;

                        Skill skill=myBattleMonster.skillList.get(0);

                        myMonsterAttack+=(int)(myMonsterAttack*skill.upgradeAttack);
                        myMonsterDefense+=(int)(myMonsterDefense*skill.upgradeDefense);

                        myMonsterAttack-=(int)(myMonsterAttack*skill.reducedAttack);
                        myMonsterDefense-=(int)(myMonsterDefense*skill.reduceDefense);

                        enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reduceEnemyAttack);
                        enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceEnemyDense);



                        setEnemySkillCard(myMonsterAttackBefore,myMonsterDefenseBefore,enemyMonsterAttackBefore,enemyMonsterDefenseBefore);

                        setMyDialog(dialogReply.setMySkill(skill.skillName));

                        if(setEquipFirst==0 && setSkillFirst==0){
                            setSkillFirst=1;
                        }

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


    private void confirmSetSkillCard2(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetSkill=1;
                        checkSkill=1;
                        IsmySkill2Used=1;

                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard2ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();
                        int myMonsterAttackBefore=myMonsterAttack;
                        int myMonsterDefenseBefore=myMonsterDefense;

                        int enemyMonsterAttackBefore=enemyMonsterAttack;
                        int enemyMonsterDefenseBefore=enemyMonsterDefense;

                        Skill skill=myBattleMonster.skillList.get(1);

                        myMonsterAttack+=(int)(myMonsterAttack*skill.upgradeAttack);
                        myMonsterDefense+=(int)(myMonsterDefense*skill.upgradeDefense);

                        myMonsterAttack-=(int)(myMonsterAttack*skill.reducedAttack);
                        myMonsterDefense-=(int)(myMonsterDefense*skill.reduceDefense);

                        enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reduceEnemyAttack);
                        enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceEnemyDense);



                        setEnemySkillCard(myMonsterAttackBefore,myMonsterDefenseBefore,enemyMonsterAttackBefore,enemyMonsterDefenseBefore);


                        setMyDialog(dialogReply.setMySkill(skill.skillName));
                        //setEnemySkillCard();
                        if(setEquipFirst==0 && setSkillFirst==0){
                            setSkillFirst=1;
                        }
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


    private void confirmSetSkillCard3(){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("確定裝備此裝備").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        myMonsterSetSkill=2;
                        checkSkill=1;
                        IsmySkill3Used=1;

                        ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard3ImageView,"rotationY",0,270,0);
                        animator.setDuration(2000);
                        animator.start();
                        int myMonsterAttackBefore=myMonsterAttack;
                        int myMonsterDefenseBefore=myMonsterDefense;

                        int enemyMonsterAttackBefore=enemyMonsterAttack;
                        int enemyMonsterDefenseBefore=enemyMonsterDefense;

                        Skill skill=myBattleMonster.skillList.get(2);

                        myMonsterAttack+=(int)(myMonsterAttack*skill.upgradeAttack);
                        myMonsterDefense+=(int)(myMonsterDefense*skill.upgradeDefense);

                        myMonsterAttack-=(int)(myMonsterAttack*skill.reducedAttack);
                        myMonsterDefense-=(int)(myMonsterDefense*skill.reduceDefense);

                        enemyMonsterAttack-=(int)(enemyMonsterAttack*skill.reduceEnemyAttack);
                        enemyMonsterDefense-=(int)(enemyMonsterDefense*skill.reduceEnemyDense);



                        setEnemySkillCard(myMonsterAttackBefore,myMonsterDefenseBefore,enemyMonsterAttackBefore,enemyMonsterDefenseBefore);

                        setMyDialog(dialogReply.setMySkill(skill.skillName));

                        if(setEquipFirst==0 && setSkillFirst==0){
                            setSkillFirst=1;
                        }

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
        if(v==myMonsterImageView){

            ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterImageView, "scaleY", 1, 3, 1);
            animator.setDuration(400);
            animator.start();
        }
        if(v==myMonsterEquipmentCard1ImageView){
            if(IsmyEquip1Used==0) {
                if (checkEquip == 0) {
                    confirmSetEqipmentCard1();

                } else if (checkEquip == 1) {
                    Toast.makeText(this, "已選擇一張裝備卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmyEquip1Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }

           // confirmSetEqipment();
/*
            ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard1ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();



            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(0));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);
            */
        }
        if(v==myMonsterEquipmentCard2ImageView){
            if(IsmyEquip2Used==0) {
                if (checkEquip == 0) {
                    confirmSetEqipmentCard2();
                } else if (checkEquip == 1) {
                    Toast.makeText(this, "已選擇一張裝備卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmyEquip2Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }
            /*ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard2ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();



            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(1));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);*/
        }
        if(v==myMonsterEquipmentCard3ImageView){
            if(IsmyEquip3Used==0) {
                if (checkEquip == 0) {
                    confirmSetEqipmentCard3();

                } else if (checkEquip == 1) {
                    Toast.makeText(this, "已選擇一張裝備卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmyEquip3Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }
           /* ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterEquipmentCard3ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpEquipment(myBattleMonster.equipmentList.get(2));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);*/
        }
        if(v==myMonsterSkillCard1ImageView){
            if(IsmySkill1Used==0) {
                if (checkSkill == 0) {
                    confirmSetSkillCard1();

                } else if (checkSkill == 1) {
                    Toast.makeText(this, "已選擇一張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmySkill1Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }
            /*ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard1ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpSkill(myBattleMonster.skillList.get(0));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);*/
        }
        if(v==myMonsterSkillCard2ImageView){
            if(IsmySkill2Used==0) {
                if (checkSkill == 0) {
                    confirmSetSkillCard2();

                } else if (checkSkill == 1) {
                    Toast.makeText(this, "已選擇一張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmySkill2Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }
           /* ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard2ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpSkill(myBattleMonster.skillList.get(1));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);*/
        }
        if(v==myMonsterSkillCard3ImageView){
            if(IsmySkill3Used==0) {
                if (checkSkill == 0) {
                    confirmSetSkillCard3();

                } else if (checkSkill == 1) {
                    Toast.makeText(this, "已選擇一張裝備卡", Toast.LENGTH_SHORT).show();
                }
            }
            else if(IsmySkill3Used==1){
                Toast.makeText(this, "已使用過此卡", Toast.LENGTH_SHORT).show();
            }
            /*ObjectAnimator animator = ObjectAnimator.ofFloat(myMonsterSkillCard3ImageView,"rotationY",0,270,0);
            animator.setDuration(2000);
            animator.start();
            float attackBefore=myBattleMonster.Attack;
            float defenseBefore=myBattleMonster.Defense;
            myBattleMonster.SetUpSkill(myBattleMonster.skillList.get(2));
            setMyMonsterAttackValue(attackBefore,myBattleMonster.Attack,3000);
            setMyMonsterDefenseValue(defenseBefore,myBattleMonster.Defense,3000);*/
        }


        if(v==attackImageView){

            if(checkEquip==1 && checkSkill==1) {
                attack();

                round++;

                checkEquip = 0;
                checkSkill = 0;


                myMonsterSetEquip = -1;
                myMonsterSetSkill = -1;

                setEquipFirst=0;
                setSkillFirst=0;
            }
            else{
                if(checkEquip==0) Toast.makeText(this, "請選擇一張裝備卡", Toast.LENGTH_SHORT).show();
                if(checkSkill==0) Toast.makeText(this, "請選擇一張技能卡", Toast.LENGTH_SHORT).show();
            }

        }


    }

    private void battleEnd(final int num){
        // TODO Auto-generated method stub
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("戰鬥結束").setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(num==1){//平手
                            Intent i = new Intent(BattleScene.this, BattleEnd.class);
                            i.putExtra("battleResult", num);
                            i.putExtra("round",round-1);
                            i.putExtra("noDamage",isNoDamage);
                            i.putExtra("myLevel",myBattleMonster.Level);
                            i.putExtra("enemyLevel",enemyBattleMonster.Level);
                            i.putExtra("mainMonster",myMainMonster);
                            i.putExtra("EXP",myMonster.EXP);
                            startActivity(i);
                        }
                        else if(num==2){//對手
                            Intent i = new Intent(BattleScene.this, BattleEnd.class);
                            i.putExtra("battleResult", num);
                            i.putExtra("round",round-1);
                            i.putExtra("noDamage",isNoDamage);
                            i.putExtra("myLevel",myBattleMonster.Level);
                            i.putExtra("enemyLevel",enemyBattleMonster.Level);
                            i.putExtra("mainMonster",myMainMonster);
                            i.putExtra("EXP",myMonster.EXP);
                            startActivity(i);
                        }
                        else if(num==3){//我方
                            Intent i = new Intent(BattleScene.this, BattleEnd.class);
                            i.putExtra("battleResult", num);
                            i.putExtra("round",round-1);
                            i.putExtra("noDamage",isNoDamage);
                            i.putExtra("myLevel",myBattleMonster.Level);
                            i.putExtra("enemyLevel",enemyBattleMonster.Level);
                            i.putExtra("mainMonster",myMainMonster);
                            i.putExtra("EXP",myMonster.EXP);
                            startActivity(i);
                        }

                    }
                });
        AlertDialog about_dialog = builder.create();

        Window window = about_dialog.getWindow();
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams lp = window.getAttributes();

            lp.alpha = 0.6f;
            window.setAttributes(lp);

        about_dialog.setCanceledOnTouchOutside(false);
        about_dialog.show();
    }

    public void attack(){

        ObjectAnimator animator = ObjectAnimator.ofFloat(enemyMonsterImageView, "translationY", 0, 250, 0,0);
        animator.setDuration(500);
        animator.start();


        ObjectAnimator animator2 = ObjectAnimator.ofFloat(myMonsterImageView, "translationY", 0, -250, 0,0);
        animator2.setDuration(500);
        animator2.start();


        int myMonsterHpBefore=myBattleMonster.HP;
        int enemyMonsterHpBefore=enemyBattleMonster.HP;

        int enemyMonsterDamage=myMonsterAttack-enemyMonsterDefense;
        int myMonsterDamage=enemyMonsterAttack-myMonsterDefense;


        if(enemyMonsterDamage<0){
            myBattleMonster.HP+=enemyMonsterDamage;
        }
        else{
            enemyBattleMonster.HP-=enemyMonsterDamage;
        }


        if(myMonsterDamage<0){
            enemyBattleMonster.HP+=myMonsterDamage;
        }
        else{
            myBattleMonster.HP-=myMonsterDamage;
        }



        if((myMonsterHpBefore- myBattleMonster.HP)==0) {
            setMyDialog("我方沒有受到傷害");
        }
        else {
            isNoDamage=0;
            setMyDialog("我方受到"+(myMonsterHpBefore- myBattleMonster.HP)+"點傷害");
        }

        if((enemyMonsterHpBefore-enemyBattleMonster.HP)==0){
            setEnemyDialog("對手沒有受到傷害");
        }
        else setEnemyDialog("對手受到"+(enemyMonsterHpBefore-enemyBattleMonster.HP)+"點傷害");



        if(myBattleMonster.HP<=0) setMyMonsterStrengthValue(myMonsterHpBefore,0,3000);
        else setMyMonsterStrengthValue(myMonsterHpBefore,myBattleMonster.HP,3000);

        if(enemyBattleMonster.HP<=0) setEnemyMonsterStrengthValue(enemyMonsterHpBefore,0,3000);
        else setEnemyMonsterStrengthValue(enemyMonsterHpBefore,enemyBattleMonster.HP,3000);

        removeMonsterEquipSkill();


        if(round!=3) {

            if ((myBattleMonster.HP <= 0) && (enemyBattleMonster.HP <= 0)) {
                setEnemyDialog("雙方怪獸失去戰鬥能力");
                setMyDialog("雙方平手");
                battleEnd(1);
            } else if ((myBattleMonster.HP <= 0) && (enemyBattleMonster.HP > 0)) {
                setEnemyDialog("我方怪獸失去戰鬥能力");
                setMyDialog("由對手贏得勝利");
                battleEnd(2);
            } else if ((myBattleMonster.HP > 0) && (enemyBattleMonster.HP <= 0)) {
                setEnemyDialog("對手失去戰鬥能力");
                setMyDialog("由我方贏得勝利");
                battleEnd(3);
            }
        }
        else if(round==3){
            if(myBattleMonster.HP>enemyBattleMonster.HP){
                setEnemyDialog("我方怪獸剩餘體力高於對手");
                setMyDialog("由我方贏得勝利");
                battleEnd(3);
            }
            else if(myBattleMonster.HP<enemyBattleMonster.HP){
                setEnemyDialog("對手怪獸剩餘體力高於我方");
                setMyDialog("由對手贏得勝利");
                battleEnd(2);
            }
            else if(myBattleMonster.HP==enemyBattleMonster.HP){
                setEnemyDialog("雙方怪獸失去戰鬥能力");
                setMyDialog("雙方平手");
                battleEnd(1);
            }
        }



/*
        ntent i = new Intent(ProductViewActivity.this, AddPriceActivity.class);
        i.putExtra("barCode", barCode);
        startActivity(i);*/


    }

    public void removeMonsterEquipSkill(){




        int myMonsterAttackBefore=myMonsterAttack;
        int myMonsterDenseBefore=myMonsterDefense;
        int enemyMonsterAttackBefore=enemyMonsterAttack;
        int enemyMonsterDenseBefore=enemyMonsterDefense;


        myMonsterAttack=originalMyMonsterAttack;
        myMonsterDefense=originalMyMonsterDefense;
        enemyMonsterAttack=originalEnemyMonsterAttack;
        enemyMonsterDefense=originalEnemyMonsterDefense;

/*
        if(setSkillFirst==1) {
            myBattleMonster.RemoveEquipment(myBattleMonster.equipmentList.get(myMonsterSetEquip));
            myBattleMonster.RemoveSkill(myBattleMonster.skillList.get(myMonsterSetSkill));

        }
        if(setEquipFirst==1){
            myBattleMonster.RemoveSkill(myBattleMonster.skillList.get(myMonsterSetSkill));
            myBattleMonster.RemoveEquipment(myBattleMonster.equipmentList.get(myMonsterSetEquip));
        }

        if(setSkillFirst==1) {
            enemyBattleMonster.RemoveEquipment(enemyBattleMonster.equipmentList.get(round - 1));
            enemyBattleMonster.RemoveSkill(enemyBattleMonster.skillList.get(round - 1));

        }
        if(setEquipFirst==1){
            enemyBattleMonster.RemoveSkill(enemyBattleMonster.skillList.get(round - 1));
            enemyBattleMonster.RemoveEquipment(enemyBattleMonster.equipmentList.get(round - 1));
        }*/

        setMyMonsterAttackValue(myMonsterAttackBefore,myMonsterAttack,3000);
        setMyMonsterDefenseValue(myMonsterDenseBefore,myMonsterDefense,3000);

        setEnemyMonsterAttackValue(enemyMonsterAttackBefore,enemyMonsterAttack,3000);
        setEnemyMonsterDefenseValue(enemyMonsterDenseBefore,enemyMonsterDefense,3000);

    }


    public void setMyDialog(String text){
        myDialog.setText(text);

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        alphaAnimation.setDuration(1000);

        animationSet.addAnimation(alphaAnimation);

        myDialog.startAnimation(animationSet);
    }

    public void setEnemyDialog(String text){
        enemyDialog.setText(text);

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        alphaAnimation.setDuration(500);

        animationSet.addAnimation(alphaAnimation);

        enemyDialog.startAnimation(animationSet);
    }

    public void setMyMonsterDefenseValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myMonsterDefenseTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setEnemyMonsterDefenseValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                enemyMonsterDefenseTextView.setText(n+"");
            }
        });
        anim.start();
    }
    public void setMyMonsterAttackValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myMonsterAttackTextView.setText(n+"");
            }
        });
        anim.start();
    }
    public void setEnemyMonsterAttackValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                enemyMonsterAttackTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setMyMonsterLevelValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myMonsterLevelTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setEnemyMonsterLevelValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                enemyMonsterLevelTextView.setText(n+"");
            }
        });
        anim.start();
    }


    public void setMyMonsterLoveLevelValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myMonsterLoveLevelTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setEnemyMonsterLoveLevelValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                enemyMonsterLoveLevelTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setMyMonsterStrengthValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myMonsterStrengthTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setEnemyMonsterStrengthValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                enemyMonsterStrengthTextView.setText(n+"");
            }
        });
        anim.start();
    }



    public void setEnemyMonsterEquipmentCard(String equipmentName){
        if(equipmentName.equals("Capsule")) enemyBattleMonster.equipmentList.add(new Capsule());
        else if(equipmentName.equals("Carton"))enemyBattleMonster.equipmentList.add(new Carton());
        else if(equipmentName.equals("BottleLogo"))enemyBattleMonster.equipmentList.add(new BottleLogo());
        else if(equipmentName.equals("Bookmark"))enemyBattleMonster.equipmentList.add(new Bookmark());
        else if(equipmentName.equals("BookClothing"))enemyBattleMonster.equipmentList.add(new BookClothing());
        else if(equipmentName.equals("CupSet"))enemyBattleMonster.equipmentList.add(new CupSet());
        else if(equipmentName.equals("PullRing"))enemyBattleMonster.equipmentList.add(new PullRing());
        else if(equipmentName.equals("Chopsticks"))enemyBattleMonster.equipmentList.add(new Chopsticks());
        else if(equipmentName.equals("ChickenLegs"))enemyBattleMonster.equipmentList.add(new ChickenLegs());
        else if(equipmentName.equals("Coaster"))enemyBattleMonster.equipmentList.add(new Coaster());
        else if(equipmentName.equals("InstantDrink"))enemyBattleMonster.equipmentList.add(new InstantDrink());
        else if(equipmentName.equals("NoEquip"))enemyBattleMonster.equipmentList.add(new NoEquip());
    }

    public void setMyMonsterEquipmentCard(String equipmentName){
        if(equipmentName.equals("Capsule")) myBattleMonster.equipmentList.add(new Capsule());
        else if(equipmentName.equals("Carton"))myBattleMonster.equipmentList.add(new Carton());
        else if(equipmentName.equals("BottleLogo"))myBattleMonster.equipmentList.add(new BottleLogo());
        else if(equipmentName.equals("Bookmark"))myBattleMonster.equipmentList.add(new Bookmark());
        else if(equipmentName.equals("BookClothing"))myBattleMonster.equipmentList.add(new BookClothing());
        else if(equipmentName.equals("CupSet"))myBattleMonster.equipmentList.add(new CupSet());
        else if(equipmentName.equals("PullRing"))myBattleMonster.equipmentList.add(new PullRing());
        else if(equipmentName.equals("Chopsticks"))myBattleMonster.equipmentList.add(new Chopsticks());
        else if(equipmentName.equals("ChickenLegs"))myBattleMonster.equipmentList.add(new ChickenLegs());
        else if(equipmentName.equals("Coaster"))myBattleMonster.equipmentList.add(new Coaster());
        else if(equipmentName.equals("InstantDrink"))myBattleMonster.equipmentList.add(new InstantDrink());
        else if(equipmentName.equals("NoEquip"))myBattleMonster.equipmentList.add(new NoEquip());
    }

    public void setMyMonsterSkillCard(String skillName){
        if(skillName.equals("StrongWater"))myBattleMonster.skillList.add(new StrongWater());
        else if(skillName.equals("H2SO4"))myBattleMonster.skillList.add(new Sulfuric());
        else if(skillName.equals("PaperPlane"))myBattleMonster.skillList.add(new PaperPlane());
        else if(skillName.equals("Encyclopedia"))myBattleMonster.skillList.add(new Encyclopedia());
        else if(skillName.equals("Compression"))myBattleMonster.skillList.add(new Compression());
        else if(skillName.equals("CansScroll"))myBattleMonster.skillList.add(new CansScroll());
        else if(skillName.equals("SweetSmell"))myBattleMonster.skillList.add(new SweetSmell());
        else if(skillName.equals("RottenFood"))myBattleMonster.skillList.add(new RottenFood());
        else if(skillName.equals("HotWater"))myBattleMonster.skillList.add(new HotWater());
        else if(skillName.equals("Coffee"))myBattleMonster.skillList.add(new Coffee());
        else if(skillName.equals("NoSkill"))myBattleMonster.skillList.add(new NoSkill());
    }

    public void setEnemyMonsterSkillCard(String skillName){
        if(skillName.equals("StrongWater"))enemyBattleMonster.skillList.add(new StrongWater());
        else if(skillName.equals("H2SO4"))enemyBattleMonster.skillList.add(new Sulfuric());
        else if(skillName.equals("PaperPlane"))enemyBattleMonster.skillList.add(new PaperPlane());
        else if(skillName.equals("Encyclopedia"))enemyBattleMonster.skillList.add(new Encyclopedia());
        else if(skillName.equals("Compression"))enemyBattleMonster.skillList.add(new Compression());
        else if(skillName.equals("CansScroll"))enemyBattleMonster.skillList.add(new CansScroll());
        else if(skillName.equals("SweetSmell"))enemyBattleMonster.skillList.add(new SweetSmell());
        else if(skillName.equals("RottenFood"))enemyBattleMonster.skillList.add(new RottenFood());
        else if(skillName.equals("HotWater"))enemyBattleMonster.skillList.add(new HotWater());
        else if(skillName.equals("Coffee"))enemyBattleMonster.skillList.add(new Coffee());
        else if(skillName.equals("NoSkill"))enemyBattleMonster.skillList.add(new NoSkill());
    }




}
