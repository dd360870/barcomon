package com.example.barcomon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Random;

public class DrawBarCoMon extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private TextView TextViewuseremail;
    private Button ButtonDrawMonster;

    public  UserInformation userinformation;

    public String str="";

    private int BarCoMonEnergy=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_draw_bar_co_mon);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();
        ButtonDrawMonster = (Button)findViewById(R.id.drawMonster_button);

        TextViewuseremail = (TextView)findViewById(R.id.UserEmail);

        TextViewuseremail.setText(user.getEmail()+"");

        ButtonDrawMonster.setOnClickListener(this);

        getUserInformation();

    }

    @Override
    public void onClick(View v) {

        if(v==ButtonDrawMonster){
            if(BarCoMonEnergy>=20){
                drawAMonster();
            }
        }

    }

    public void drawAMonster(){
        if(!checkAllOwn()) {
            int num;
            while (true) {
                num = getRandomNum();
                if (checkOwnMonsterCanGet(num)) {
                    break;
                }
            }
        updateUserDatabase();

            str += "\n抽到編號" + "00" + num + "怪獸" + "\n";
            TextViewuseremail.setText(str);
            str = "";
            updateDatabase(MonsterProducer(num));
        }
        else {
            str += "抽完了";
            TextViewuseremail.setText(str);
        }

    }
    /*public Monster(String mosterID,int level,int lovelevel,int attack,int defense,
    int exp,int strength,String monstertype,String monsterinformation,int upgradeAttackValue,
    int upgradeDefenseValue,int upgradePlayLoveLevelValue,int upgradeChatLoveLevelValue,int upgradeEXPValue,
    int upgradeStrengthValue){
     */
    public int getRandomNum(){
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%5);
        return num;
    }

    public boolean checkAllOwn(){
        if(userinformation.ownMonster.equals("11111")){
            return true;
        }
        else return false;
    }

    public boolean checkOwnMonsterCanGet(int num){
        if(userinformation.ownMonster.charAt(num)=='1'){
            return false;
        }
        else if(userinformation.ownMonster.charAt(num)=='0'){
            StringBuffer sb=new StringBuffer(userinformation.ownMonster);
            sb.setCharAt(num,'1');
            userinformation.ownMonster = sb.toString();
            return true;
        }
        else return false;
    }
    public void updateUserDatabase(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").setValue(userinformation);


    }

    public Monster MonsterProducer(int num){
        switch (num){
            case 0:
                Monster m0 =new Monster("00"+num,1,0,500,500,0,50,"Water","INFORMATION",5,5,5,5,5,5);
                return m0;
            case 1:
                Monster m1 =new Monster("00"+num,1,0,600,400,0,50,"Paper","INFORMATION",5,5,5,5,5,5);
                return m1;
            case 2:
                Monster m2 =new Monster("00"+num,1,0,300,700,0,50,"Electricity","INFORMATION",5,5,5,5,5,5);
                return m2;
            case 3:
                Monster m3 =new Monster("00"+num,1,0,700,300,0,50,"Plastic","INFORMATION",5,5,5,5,5,5);
                return m3;
            case 4:
                Monster m4 =new Monster("00"+num,1,0,800,200,0,50,"Plastic","INFORMATION",5,5,5,5,5,5);
                return m4;
            default:
                Monster m5 =new Monster("00"+num,1,0,800,200,0,50,"Plastic","INFORMATION",5,5,5,5,5,5);
                return m5;
        }
    }

    private void getUserInformation(){
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("userinformation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userinformation = snapshot.getValue(UserInformation.class);
                str+="OwnMonster: "+userinformation.ownMonster+"\n"+"Energy: "+userinformation.BarCoMonEnergy;
                TextViewuseremail.setText("OwnMonster: "+userinformation.ownMonster+"\n"+"Energy: "+userinformation.BarCoMonEnergy);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Log.d(getClass(),user.getUid()+"'s bet failed due to a db error (1)");
                // Snip, not relevant
            }
        });

    }

    public void updateDatabase(Monster m){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("MonsterInformation").setValue(m);


        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");


        EquipmentInformation equipment1=new EquipmentInformation("000","Capsule");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment1);

        EquipmentInformation equipment2=new EquipmentInformation("001","Carton");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment2);

        EquipmentInformation equipment3=new EquipmentInformation("000","Capsule");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment3);


        SkillInformation skill1 =new SkillInformation("000","StrongWater");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill1);


        SkillInformation skill2 =new SkillInformation("001","PaperPlane");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill2);


        SkillInformation skill3 =new SkillInformation("000","StrongWater");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill3);


        TrapInformation trap1=new TrapInformation("000","BrokenHeart");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("TrapInformation").child("Card1").setValue(trap1);


        TrapInformation trap2=new TrapInformation("001","ProtectingShield");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("TrapInformation").child("Card2").setValue(trap2);

        TrapInformation trap3=new TrapInformation("000","BrokenHeart");
        databaseReference.child(user.getUid()).child(m.UsergetMonsterID()).child("BattleInformation").child("TrapInformation").child("Card3").setValue(trap3);



    }

}
