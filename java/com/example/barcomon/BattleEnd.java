package com.example.barcomon;

import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BattleEnd extends AppCompatActivity implements View.OnClickListener{

    private TextView battleResultTextView,fastBattleTextView,noDamageTextView,levelBonusTextView,extraBonusTextView,
            myEXPTextView,totalEXPTextView;
    private int battleResult, round,noDamage,myLevel,enemyLevel,myEXP;
    private String mainMonster;

    private int totalEXP=10000;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    private Monster monster;
    private Button levelUpButton,exitButton;

    private static final int SOUND_COUNT = 2;
    private SoundPool soundPool;
    private int ClickSoundID;
    private int LevelUpSoundID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_battle_end);


        firebaseAuth = FirebaseAuth.getInstance();
        battleResultTextView=(TextView)findViewById(R.id.battleEndBattleResultValue);
        fastBattleTextView=(TextView)findViewById(R.id.battleEndFastBattleValue);
        noDamageTextView=(TextView)findViewById(R.id.battleEndNoDamageValue);
        levelBonusTextView=(TextView)findViewById(R.id.battleEndLevelValue);
        extraBonusTextView=(TextView)findViewById(R.id.battleEndBonusValue);
        myEXPTextView=(TextView)findViewById(R.id.battleEndMyXEP);
        totalEXPTextView=(TextView)findViewById(R.id.battleEndTotalEXP);

        levelUpButton=(Button)findViewById(R.id.battleEndLevelUpButton);
        exitButton=(Button)findViewById(R.id.battleEndExitButton);

        levelUpButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);

        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
        ClickSoundID= this.soundPool.load(this, R.raw.consloebutton, 1);
        LevelUpSoundID=this.soundPool.load(this, R.raw.levelup, 1);


        //Intent intent = this.getIntent();


        Bundle bundle = getIntent().getExtras();

        battleResult = bundle.getInt("battleResult");
        round=bundle.getInt("round");
        noDamage=bundle.getInt("noDamage");
        myLevel=bundle.getInt("myLevel");
        enemyLevel=bundle.getInt("enemyLevel");
        //mainMonster=bundle.getInt("mainMonster");
        mainMonster=bundle.getString("mainMonster");
        myEXP=bundle.getInt("EXP");


        setValue();
        updataEXP();
        getMonster();

    }


    public void setValue(){

        //battleResultTextView.setText(battleResult+"");

        totalEXP*=myLevel;
        //totalEXPTextView.setText(totalEXP+"");
        settotalEXPValue(0,totalEXP,3000);

        if(battleResult==1){//平手
            //battleResultTextView.setText("500");
            setbattleResultValue(0,500,3000);
            myEXP+=500;

            if(round==1|| round==2){
                //fastBattleTextView.setText("250");
                setfastBattleValue(0,250,3000);
                myEXP+=250;
            }
            else{
                fastBattleTextView.setText(round+"");
            }
            if(noDamage==1){
                //noDamageTextView.setText("250");
                setnoDamageValue(0,250,3000);
                myEXP+=250;
            }
            else if(noDamage==0){
                noDamageTextView.setText("0");
            }

            if(myLevel>enemyLevel){
                levelBonusTextView.setText("0");
            }
            else if(myLevel<enemyLevel){
                //levelBonusTextView.setText("250");
                setlevelBonusValue(0,250,3000);
                myEXP+=250;
            }
            else if(myLevel==enemyLevel){
                levelBonusTextView.setText("0");
            }

            if(mainMonster.equals("001")){
                //extraBonusTextView.setText("500");
                setextraBonusValue(0,500,3000);
                myEXP+=500;
            }
            else{
                extraBonusTextView.setText("0");
            }


        }
        else if(battleResult==2){//對手

            //battleResultTextView.setText("250");
            setbattleResultValue(0,250,3000);
            myEXP+=250;

            if(round==1|| round==2){
                fastBattleTextView.setText("0");
            }
            else{
                fastBattleTextView.setText("0");
            }
            if(noDamage==1){
                noDamageTextView.setText("0");
            }
            else if(noDamage==0){
                noDamageTextView.setText("0");
            }

            if(myLevel>enemyLevel){
                levelBonusTextView.setText("0");
            }
            else if(myLevel<enemyLevel){
                //levelBonusTextView.setText("250");
                setlevelBonusValue(0,250,3000);
                myEXP+=250;
            }
            else if(myLevel==enemyLevel){
                levelBonusTextView.setText("0");
            }

            if(mainMonster.equals("001")){
                //extraBonusTextView.setText("500");
                setextraBonusValue(0,500,3000);
                myEXP+=500;
            }
            else{
                extraBonusTextView.setText("0");
            }



        }
        else if(battleResult==3) {//我方
            setbattleResultValue(0,1000,3000);
            //battleResultTextView.setText("1000");
            myEXP+=1000;
            if(round==1|| round==2){
                //fastBattleTextView.setText("500");
                setfastBattleValue(0,500,3000);
                myEXP+=500;
            }
            else{
                fastBattleTextView.setText(round+"");
            }

            if(noDamage==1){
                //noDamageTextView.setText("500");
                setnoDamageValue(0,500,1000);
                myEXP+=500;
            }
            else if(noDamage==0){
                noDamageTextView.setText("0");
            }


            if(myLevel>enemyLevel){
                levelBonusTextView.setText("0");
            }
            else if(myLevel<enemyLevel){
                //levelBonusTextView.setText("500");
                setlevelBonusValue(0,500,3000);
                myEXP+=500;
            }
            else if(myLevel==enemyLevel){
                //levelBonusTextView.setText("250");
                setlevelBonusValue(0,250,3000);
                myEXP+=250;
            }
            if(mainMonster.equals("001")){
                //extraBonusTextView.setText("500");
                setextraBonusValue(0,500,3000);
                myEXP+=500;
            }
            else{
                extraBonusTextView.setText("0");
            }

        }

        //myEXPTextView.setText(myEXP+"");
        setmyEXPValue(0,myEXP,6000);
    }

    public void setbattleResultValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                battleResultTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setfastBattleValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                fastBattleTextView.setText(n+"");
            }
        });
        anim.start();
    }
    public void setnoDamageValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                noDamageTextView.setText(n+"");
            }
        });
        anim.start();
    }
    public void setlevelBonusValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                levelBonusTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setextraBonusValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                extraBonusTextView.setText(n+"");
            }
        });
        anim.start();
    }


    public void setmyEXPValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                myEXPTextView.setText(n+"");
            }
        });
        anim.start();
    }
    public void settotalEXPValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                totalEXPTextView.setText(n+"");
            }
        });
        anim.start();
    }


    @Override
    public void onClick(View v) {
        if(v==levelUpButton){
            levelUp();
        }
        if(v==exitButton){
            soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
            finish();
            startActivity(new Intent(getApplicationContext(),BarCoMonGameConsole.class));
        }
    }
    public void levelUp(){
        if(myEXP>=totalEXP){
            soundPool.play(LevelUpSoundID, 1, 1, 0, 0, 1);
            int myEXPbefore=myEXP;
            int  totalEXPbefore=totalEXP;

            myLevel++;
            myEXP-=totalEXP;

            totalEXP=myLevel*10000;

            setmyEXPValue(myEXPbefore,myEXP,3000);
            settotalEXPValue(totalEXPbefore,totalEXP,3000);
            //updataEXP();
            //updateLevel();

            if(mainMonster.equals("002")){
                monster.UpgradeAttackValue += 200;
                monster.UpgradeChatLoveLevelValue += 200;
                monster.UpgradeDefenseValue += 200;
                monster.UpgradePlayLoveLevelValue += 200;
                monster.UpgradeStrength += 200;
            }
            else {
                monster.UpgradeAttackValue += 100;
                monster.UpgradeChatLoveLevelValue += 100;
                monster.UpgradeDefenseValue += 100;
                monster.UpgradePlayLoveLevelValue += 100;
                monster.UpgradeStrength += 100;
            }

            monster.Level++;
            monster.EXP=myEXP;


            updateMonster();
            Toast.makeText(this, "等級提升", Toast.LENGTH_SHORT).show();
        }
        else {
            soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
            Toast.makeText(this, "經驗值不足", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateLevel(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).child(mainMonster).child("MonsterInformation").child("Level").setValue(myLevel);

    }
    public void updateMonster(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child(mainMonster).child("MonsterInformation").setValue(monster);

    }
    public void updataEXP(){
            databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");

            FirebaseUser user = firebaseAuth.getCurrentUser();

            databaseReference.child(user.getUid()).child(mainMonster).child("MonsterInformation").child("EXP").setValue(myEXP);

    }
    public void getMonster(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child(mainMonster).child("MonsterInformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                monster = snapshot.getValue(Monster.class);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵

            //ConfirmExit(); //呼叫ConfirmExit()函數
            Toast.makeText(this, "無法返回", Toast.LENGTH_SHORT).show();

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }


}
