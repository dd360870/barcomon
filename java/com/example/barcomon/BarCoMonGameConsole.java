package com.example.barcomon;

import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BarCoMonGameConsole extends AppCompatActivity implements View.OnClickListener, View.OnTouchListener {

    public int AnimationDuration=300;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private  UserInformation userinformation;

    private ImageView BarCoMonImageView;


    private ImageView ChatButton;
    private ImageView PlayButton;
    private ImageView FeedButton;
    private ImageView SleepButton;
    private ImageView TraningAttackButton;
    private ImageView TraningDefenseButton;
    private ImageView InformationButton;

    private ImageView BattleButton;
    private ImageView BattleSetButton;

    private ImageView BarCoEnergyImageView;

    private Button SwitchToBarCoMonBox;

    private TextView AttackTextView;
    private TextView DefenseTextView;
    private TextView LoveLevelTextView;
    private TextView StrengthTextView;
    private TextView DialogBoxTextView;



    private TextView BarCoEnergyTextView;

    private Monster monster;

    private int barcoEnergy;


    protected static final int UI = 100;

    //private TextView text;

    private char[] charArrays;

    private String len = "";

    private String mainmonster="";
    String useremail="安安你好啊...我想要吃飯..喝水...";

    public DialogReply dialogreply=new DialogReply();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bar_co_mon_game_console);


        BarCoMonImageView=(ImageView)findViewById(R.id.gameboy_monsterOnScreen);

        ChatButton=(ImageView) findViewById(R.id.gameboyChat);
        PlayButton=(ImageView) findViewById(R.id.gameboyPlay);
        FeedButton=(ImageView) findViewById(R.id.gameboyFeed);
        SleepButton=(ImageView) findViewById(R.id.gameboySleep);
        TraningAttackButton=(ImageView) findViewById(R.id.gameboyUpgradeAttack);
        TraningDefenseButton=(ImageView) findViewById(R.id.gameboyUpgradeDefense);
        InformationButton=(ImageView) findViewById(R.id.gameboyInfoImageView);

        BattleButton=(ImageView)findViewById(R.id.gameboyBattle);
        BattleSetButton=(ImageView)findViewById(R.id.gameboyBattleSet);

        BarCoEnergyImageView=(ImageView)findViewById(R.id.barcoEnergyImageView);

       // SwitchToBarCoMonBox=(Button)findViewById(R.id.SwitchToBarCoMonBoxButton);

        AttackTextView=(TextView)findViewById(R.id.gameboyAttackTextView);
        DefenseTextView=(TextView)findViewById(R.id.gameboyDefenseTextView);
        LoveLevelTextView=(TextView)findViewById(R.id.gameboyLoveLevelTextView);
        StrengthTextView=(TextView)findViewById(R.id.gameboyStrengthTextView);
        DialogBoxTextView=(TextView)findViewById(R.id.gameboyDialogTextView);


        BarCoEnergyTextView=(TextView)findViewById(R.id.gameboyEnergyTextView);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser firebaseuser = firebaseAuth.getCurrentUser();

        InitialAll();

        //DialogBoxTextView.setText(firebaseuser.getEmail()+"");


        //final String useremail=firebaseuser.getEmail()+"";



        /*
        DialogBoxTextView.setText(firebaseuser.getEmail()+"");

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        alphaAnimation.setDuration(3000);

        animationSet.addAnimation(alphaAnimation);

        DialogBoxTextView.startAnimation(animationSet);
        */







        //ChatButton.setOnTouchListener(this);





        ChatButton.setOnClickListener(this);
        PlayButton.setOnClickListener(this);
        FeedButton.setOnClickListener(this);
        SleepButton.setOnClickListener(this);
        TraningAttackButton.setOnClickListener(this);
        TraningDefenseButton.setOnClickListener(this);
        InformationButton.setOnClickListener(this);
        BattleButton.setOnClickListener(this);
        BattleSetButton.setOnClickListener(this);

    }
/*
    @Override
    protected void onStart() {
        super.onStart();
        InitialAll();
    }*/

    private void getUserInformation(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);

                //AttackTextView.setText("Name:"+u.name+"\nAddress:"+u.MainMonster);

                if(userinformation.MainMonster.equals("000")){
                    mainmonster="000";
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottle));
                }

                else if(userinformation.MainMonster.equals("001")){
                    mainmonster="001";
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.can));

                }
                else if(userinformation.MainMonster.equals("002")){
                    mainmonster="002";
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.cupfairy));
                }
                else if(userinformation.MainMonster.equals("003")){
                    mainmonster="003";
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.bento));
                }
                else if(userinformation.MainMonster.equals("004")){
                    mainmonster="004";
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottle));
                }

                int enegy=userinformation.BarCoMonEnergy;

                //barcoEnergy=userinformation.BarCoMonEnergy;

                if(enegy>=0 && enegy<=20) BarCoEnergyImageView.setImageDrawable(getResources().getDrawable(R.drawable.barcoenergy1));
                else if(enegy>=21 && enegy<=40) BarCoEnergyImageView.setImageDrawable(getResources().getDrawable(R.drawable.barcoenergy2));
                else if(enegy>=41 && enegy<=60) BarCoEnergyImageView.setImageDrawable(getResources().getDrawable(R.drawable.barcoenergy3));
                else if(enegy>=61 && enegy<=80) BarCoEnergyImageView.setImageDrawable(getResources().getDrawable(R.drawable.barcoenergy4));
                else if(enegy>=81 && enegy<=100) BarCoEnergyImageView.setImageDrawable(getResources().getDrawable(R.drawable.barcoenergy5));

                setBarCoEnergyValue(0,userinformation.BarCoMonEnergy,AnimationDuration);
                //BarCoEnergyTextView.setText(userinformation.BarCoMonEnergy+"");


                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("MonsterInformation").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        monster = snapshot.getValue(Monster.class);


                        AttackTextView.setText(monster.UsergetAttack()+"");
                        DefenseTextView.setText(monster.UsergetDefense()+"");
                        LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
                        StrengthTextView.setText(monster.UsergetStrength()+"");


                        //DialogBoxTextView.setText("MainMonster:"+userinformation.MainMonster+"");
                        //AttackTextView.setText(m.UsergetAttack()+"");
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

    private void updateMonsterDatabase() {
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).child(monster.UsergetMonsterID()).child("MonsterInformation").setValue(monster);


        //displaying a success toast
        //Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
    }

    private void updateUserInfoDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").setValue(userinformation);

    }

/*
    public void setDialog(final String test){
        //final String test="我口渴...想喝水...";
        final Handler handler = new Handler(){

            @Override

            public void handleMessage(Message msg) {

                super.handleMessage(msg);

                switch (msg.what) {

                    case UI:

                        DialogBoxTextView.append(len);

                        break;



                    default:

                        break;

                }

            }



        };

        new Thread(){

            public void run() {

                try {

                    charArrays = test.toCharArray();

                    for (int i = 0; i < charArrays.length; i++) {

                        sleep(100);

                        len = charArrays[i]+"";

                        handler.sendEmptyMessage(UI);

                    }

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            };

        }.start();
    }
*/




    public void setDialog(String text){
        DialogBoxTextView.setText(text);

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);

        alphaAnimation.setDuration(3000);

        animationSet.addAnimation(alphaAnimation);

        DialogBoxTextView.startAnimation(animationSet);
    }

    public void onClick(View v){
        DialogBoxTextView.setText("");
        if(v==ChatButton){
            if(mainmonster.equals("000")) setDialog(dialogreply.BottleChat());
            if(mainmonster.equals("001")) setDialog(dialogreply.CanChat());
            if(mainmonster.equals("002")) setDialog(dialogreply.MugChat());
            ChatAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();

        }

        else if(v==PlayButton){
            if(mainmonster.equals("000")) setDialog(dialogreply.BottlePlay());
            if(mainmonster.equals("001")) setDialog(dialogreply.CanPlay());
            if(mainmonster.equals("002")) setDialog(dialogreply.MugPlay());

            //setDialog(dialogreply.BottlePlay());
            PlayAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();
        }

        else if(v==FeedButton){
            //setDialog(dialogreply.BottleFeed());
            if(mainmonster.equals("000")) setDialog(dialogreply.BottleFeed());
            if(mainmonster.equals("001")) setDialog(dialogreply.CanFeed());
            if(mainmonster.equals("002")) setDialog(dialogreply.MugFeed());

            FeedingAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();
        }

        else if(v==SleepButton){
            //setDialog(dialogreply.BottleSleep());
            if(mainmonster.equals("000")) setDialog(dialogreply.BottleSleep());
            if(mainmonster.equals("001")) setDialog(dialogreply.CanSleep());
            if(mainmonster.equals("002")) setDialog(dialogreply.MugSleep());

            SleepAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();
        }

        else if(v==TraningAttackButton){



            setDialog("我要打爆對手~");


            TraningAttackAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();
        }

        else if(v==TraningDefenseButton){
            setDialog("防禦是最好的攻擊...");
            TraningDefenseAction();
            updateMonsterDatabase();
            updateUserInfoDatabase();
        }

        else if(v==InformationButton){
            startActivity(new Intent(getApplicationContext(),BarCoMonBox.class));
            //MonsterInfoAction();
            //updateMonsterDatabase();
        }

        else if(v.getId()==R.id.SwitchToBarCoMonBoxButton){
            //finish();
            startActivity(new Intent(getApplicationContext(),BarCoMonBox.class));
        }
        else if(v==BattleButton){
            startActivity(new Intent(getApplicationContext(),BattleScene.class));
        }
        else if(v==BattleSetButton){
            finish();
            startActivity(new Intent(getApplicationContext(),BattleSetUp.class));
        }

    }

    public void ChatAction(){


        int lovellevelBefore=monster.LoveLevel;

        if(monster.MonsterID.equals("004")){
            monster.Chat();
        }
        userinformation.UserChatWithMonster(5);
        setMyMonsterLoveLevelValue(lovellevelBefore,monster.LoveLevel,AnimationDuration);

        //UpdateAll();
        //DialogBoxTextView.setText("Hi~");
    }
    public void PlayAction(){
        int lovelevelBefore=monster.LoveLevel;

        monster.Play();
        userinformation.UserPlayWithMonster(5);

        setMyMonsterLoveLevelValue(lovelevelBefore,monster.LoveLevel,AnimationDuration);
        //UpdateAll();
        //DialogBoxTextView.setText("Playing");
    }
    public void FeedingAction(){
        int strengthBefore=monster.Strength;
        int lovelevelBefore=monster.LoveLevel;

        if(monster.MonsterID.equals("003")){
            monster.bentoFeeding();
        }
        else {
            monster.Feeding();
        }
        userinformation.UserFeedMonster(5);
        setMyMonsterStrengthValue(strengthBefore,monster.Strength,AnimationDuration);
        setMyMonsterLoveLevelValue(lovelevelBefore,monster.LoveLevel,AnimationDuration);
        //UpdateAll();
        //DialogBoxTextView.setText("Eating");
    }
    public void SleepAction(){
        int strengthBefore=monster.Strength;
        monster.Sleep();
        userinformation.UserSleepWithMonster(5);
        setMyMonsterStrengthValue(strengthBefore,monster.Strength,AnimationDuration);
        //UpdateAll();
        //DialogBoxTextView.setText("Sleeping");
    }
    public void TraningAttackAction(){
        if(monster.Strength>=200) {
            int before = monster.Attack;
            int strengthBefore = monster.Strength;

            if (monster.MonsterID.equals("000")) {
                monster.bottleTrainingAttack();
            } else {
                monster.trainingAttack();
            }
            userinformation.UserTrainingAttack(10);
            setMyMonsterAttackValue(before, monster.Attack, AnimationDuration);
            setMyMonsterStrengthValue(strengthBefore, monster.Strength, AnimationDuration);

        }

        else{
            DialogBoxTextView.setText("體力不足...需要餵食或睡覺...");
        }
        //UpdateAll();
        //DialogBoxTextView.setText("TrainingAtk");
    }
    public void TraningDefenseAction(){
        if(monster.Strength>=200) {
            int defBefore = monster.Defense;
            int strengthBefore = monster.Strength;
            if (monster.MonsterID.equals("000")) {
                monster.bottleTrainingDefense();
            } else {
                monster.traningDefense();
            }
            userinformation.UserTrainingDefense(10);
            setMyMonsterDefenseValue(defBefore, monster.Defense, AnimationDuration);
            setMyMonsterStrengthValue(strengthBefore, monster.Strength, AnimationDuration);
        }
        else{
            DialogBoxTextView.setText("體力不足...需要餵食或睡覺...");
        }
        //UpdateAll();
        //DialogBoxTextView.setText("TraningDef");
    }
    public void MonsterInfoAction() {
        DialogBoxTextView.setText("Info");

    }
    public void BattleSet(){
        userinformation.UserBattleSet(10);
    }
    public void Battle(){
        userinformation.UserBattle(10);
    }

    public void InitialAll(){
        getUserInformation();
        //getUserMonster();
        //SetAllTextView();
    }


    public void UpdateAll(){

        //user.setBarCoEnergy(user.getBarCoEnergy()-1);

        AttackTextView.setText(monster.UsergetAttack() + "");
        DefenseTextView.setText(monster.UsergetDefense()+"");
        LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
        StrengthTextView.setText(monster.UsergetStrength()+"");


        //BarCoEnergyTextView.setText(user.getBarCoEnergy()+"");
    }

    public void setMyMonsterAttackValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                AttackTextView.setText(n+"");
            }
        });
        anim.start();
    }

    public void setMyMonsterDefenseValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                DefenseTextView.setText(n+"");
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
                LoveLevelTextView.setText(n+"");
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
                StrengthTextView.setText(n+"");
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

            }
        });
        anim.start();
    }

    public void setBarCoEnergyValue(float start,float end,int duration){
        ValueAnimator anim = ValueAnimator.ofFloat(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                int n=(int)currentValue;
                BarCoEnergyTextView.setText(n+"");
            }
        });
        anim.start();
    }



    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵

            ConfirmExit(); //呼叫ConfirmExit()函數

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }



    public void ConfirmExit(){

        AlertDialog.Builder ad=new AlertDialog.Builder(BarCoMonGameConsole.this);

        ad.setTitle("離開");

        ad.setMessage("確定離開?");

        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                finish();
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
            }
        });

        ad.setNegativeButton("否",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int i) {

            }

        });

        ad.show();//顯示訊息視窗

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
/*
        if(v==ChatButton) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN: {
                    ChatButton.setImageDrawable(getResources().getDrawable(R.drawable.gameboy_sleepbutton));
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    setDialog("我最喜歡戰鬥了~");
                    ChatAction();
                    updateMonsterDatabase();
                    updateUserInfoDatabase();
                    ChatButton.setImageDrawable(getResources().getDrawable(R.drawable.gameboy_chatbutton));
                    break;
                }
            }
        }*/
        return true;

    }

}
