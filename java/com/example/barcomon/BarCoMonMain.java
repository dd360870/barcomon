package com.example.barcomon;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class BarCoMonMain extends AppCompatActivity implements OnClickListener{

    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private  UserInformation userinformation;

    private ImageView BarCoMonImageView;


    private Button ChatButton;
    private Button PlayButton;
    private Button FeedButton;
    private Button SleepButton;
    private Button TraningAttackButton;
    private Button TraningDefenseButton;
    private Button InformationButton;

    private Button SwitchToBarCoMonBox;

    private TextView AttackTextView;
    private TextView DefenseTextView;
    private TextView LoveLevelTextView;
    private TextView StrengthTextView;
    private TextView DialogBoxTextView;
    private  TextView LevelTextView;
    private TextView BarCoEnergyTextView;

    private Monster monster;
   // private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_bar_co_mon_main);



        BarCoMonImageView=(ImageView)findViewById(R.id.BarCoMonImage);

        ChatButton=(Button)findViewById(R.id.chatButton);
        PlayButton=(Button)findViewById(R.id.playButton);
        FeedButton=(Button)findViewById(R.id.FeedindButton);
        SleepButton=(Button)findViewById(R.id.SleepButton);
        TraningAttackButton=(Button)findViewById(R.id.traningAttackButton);
        TraningDefenseButton=(Button)findViewById(R.id.traningDefenseButton);
        InformationButton=(Button)findViewById(R.id.MonsterInfoButton);

        SwitchToBarCoMonBox=(Button)findViewById(R.id.SwitchToBarCoMonBoxButton);

        AttackTextView=(TextView)findViewById(R.id.AttackTextView);
        DefenseTextView=(TextView)findViewById(R.id.DefenseTextView);
        LoveLevelTextView=(TextView)findViewById(R.id.LoveLevelView);
        StrengthTextView=(TextView)findViewById(R.id.StrengthTextView);
        DialogBoxTextView=(TextView)findViewById(R.id.DialogBoxTextView);
        LevelTextView=(TextView)findViewById(R.id.LevelTextView);
        BarCoEnergyTextView=(TextView)findViewById(R.id.BarCoEnergyTextView);





        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser firebaseuser = firebaseAuth.getCurrentUser();

        InitialAll();

        DialogBoxTextView.setText(firebaseuser.getEmail()+"");

        ChatButton.setOnClickListener(this);
        PlayButton.setOnClickListener(this);
        FeedButton.setOnClickListener(this);
        SleepButton.setOnClickListener(this);
        TraningAttackButton.setOnClickListener(this);
        TraningDefenseButton.setOnClickListener(this);
        InformationButton.setOnClickListener(this);

        SwitchToBarCoMonBox.setOnClickListener(this);




    }

    private void getUserInformation(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);
                //AttackTextView.setText("Name:"+u.name+"\nAddress:"+u.MainMonster);

                if(userinformation.MainMonster.equals("000")){
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.girl));
                }

                else if(userinformation.MainMonster.equals("001")){
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.pikachu));

                }
                else if(userinformation.MainMonster.equals("002")){
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.fox));
                }
                else if(userinformation.MainMonster.equals("003")){
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.book));
                }
                else if(userinformation.MainMonster.equals("004")){
                    BarCoMonImageView.setImageDrawable(getResources().getDrawable(R.drawable.bottle));
                }


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
                        LevelTextView.setText(monster.UsergetLevel()+"");
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

    public void onClick(View v){
        if(v.getId()==R.id.chatButton){
            ChatAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.playButton){
            PlayAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.FeedindButton){
            FeedingAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.SleepButton){
            SleepAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.traningAttackButton){
            TraningAttackAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.traningDefenseButton){
            TraningDefenseAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.MonsterInfoButton){
            MonsterInfoAction();
            updateMonsterDatabase();
        }

        else if(v.getId()==R.id.SwitchToBarCoMonBoxButton){
            //finish();
            startActivity(new Intent(getApplicationContext(),BarCoMonBox.class));
        }
    }

    public void ChatAction(){
        monster.Chat();
        UpdateAll();
        DialogBoxTextView.setText("Hi~");
    }
    public void PlayAction(){
        monster.Play();
        UpdateAll();
        DialogBoxTextView.setText("Playing");
    }
    public void FeedingAction(){
        monster.Feeding();
        UpdateAll();
        DialogBoxTextView.setText("Eating");
    }
    public void SleepAction(){
        monster.Sleep();
        UpdateAll();
        DialogBoxTextView.setText("Sleeping");
    }
    public void TraningAttackAction(){
        monster.trainingAttack();
        UpdateAll();
        DialogBoxTextView.setText("TrainingAtk");
    }
    public void TraningDefenseAction(){
        monster.traningDefense();
        UpdateAll();
        DialogBoxTextView.setText("TraningDef");
    }
    public void MonsterInfoAction() {
        DialogBoxTextView.setText("Info");

    }

    public void InitialAll(){
        getUserInformation();
        //getUserMonster();
        //SetAllTextView();
    }
/*
    public void getUserMonster(){
        //從資料庫取得User目前使用的怪獸

        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("001").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                monster = snapshot.getValue(Monster.class);
                AttackTextView.setText(monster.UsergetAttack()+"");
                DefenseTextView.setText(monster.UsergetDefense()+"");
                LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
                StrengthTextView.setText(monster.UsergetStrength()+"");
                LevelTextView.setText(monster.UsergetLevel()+"");
                DialogBoxTextView.setText("MainMonster:"+userinformation.MainMonster+"");
                //AttackTextView.setText(m.UsergetAttack()+"");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });



        //Monster m=new Monster("002",2,10,2500,2000,50,20,"Food","INFORMATION",5,5,5,5,5,5);

       // return m;
    }
*/
    public void UpdateAll(){

        //user.setBarCoEnergy(user.getBarCoEnergy()-1);

        AttackTextView.setText(monster.UsergetAttack() + "");
        DefenseTextView.setText(monster.UsergetDefense()+"");
        LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
        StrengthTextView.setText(monster.UsergetStrength()+"");
        LevelTextView.setText(monster.UsergetLevel()+"");

        //BarCoEnergyTextView.setText(user.getBarCoEnergy()+"");
    }

    public void UpdateMonsterDatabase(){

    }
    public void UpdateUserDataBase(){

    }
/*
    public void SetAllTextView(){
        AttackTextView.setText(monster.UsergetAttack()+"");
        DefenseTextView.setText(monster.UsergetDefense()+"");
        LoveLevelTextView.setText(monster.UsergetLoveLevel()+"");
        StrengthTextView.setText(monster.UsergetStrength()+"");
        LevelTextView.setText(monster.UsergetLevel()+"");
       // BarCoEnergyTextView.setText(user.getBarCoEnergy()+"");
    }*/
}
