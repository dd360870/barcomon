package com.example.barcomon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        buttonRegister=(Button)findViewById(R.id.buttonRegister);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        textViewSignin=(TextView)findViewById(R.id.textViewSignin);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }
    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;

        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Register User...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            InitialDataBase();
                            //註冊成功並登入
                            //Toast.makeText(MainActivity.this,"Register Successlly",Toast.LENGTH_SHORT).show();
                            if(firebaseAuth.getCurrentUser()!=null){
                                finish();
                                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Could not register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });




    }

    public void InitialDataBase(){
        databaseReference= FirebaseDatabase.getInstance().getReference("users");
        FirebaseUser user = firebaseAuth.getCurrentUser();

        UserItem item=new UserItem(0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);

        databaseReference.child(user.getUid()).child("Item").setValue(item);

        UserInformation userinformation=new UserInformation("user","000",0);
        databaseReference.child(user.getUid()).child("userinformation").setValue(userinformation);
//public Monster(String mosterID,int level,int lovelevel,int attack,int defense,int exp,int strength,String monstertype,String monsterinformation,int upgradeAttackValue,int upgradeDefenseValue,int upgradePlayLoveLevelValue,int upgradeChatLoveLevelValue,int upgradeEXPValue,int upgradeStrengthValue){
        Monster m0 =new Monster("000",1,0,1000,1000,0,500,"Drink","INFORMATION",200,200,200,200,200,400);
        Monster m1 =new Monster("001",1,0,500,1500,0,500,"Drink","INFORMATION",200,200,200,200,200,400);
        Monster m2 =new Monster("002",1,0,1500,500,0,500,"Drink","INFORMATION",200,200,200,200,200,400);
        Monster m3 =new Monster("003",1,0,1300,700,0,500,"Food","INFORMATION",200,200,200,200,200,400);
        Monster m4 =new Monster("004",1,0,200,1800,0,500,"Book","INFORMATION",200,200,200,200,200,400);


        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");

        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("MonsterInformation").setValue(m0);
        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("MonsterInformation").setValue(m1);
        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("MonsterInformation").setValue(m2);
        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("MonsterInformation").setValue(m3);
        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("MonsterInformation").setValue(m4);


        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");

        EquipmentInformation equipment=new EquipmentInformation("100","NoEquip");
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment);
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment);
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment);

        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment);
        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment);
        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment);

        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment);
        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment);
        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment);

        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment);
        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment);
        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment);

        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card1").setValue(equipment);
        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card2").setValue(equipment);
        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("EquipmentInformation").child("Card3").setValue(equipment);

        SkillInformation skill =new SkillInformation("100","NoSkill");
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill);
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill);
        databaseReference.child(user.getUid()).child(m0.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill);

        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill);
        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill);
        databaseReference.child(user.getUid()).child(m1.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill);

        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill);
        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill);
        databaseReference.child(user.getUid()).child(m2.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill);

        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill);
        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill);
        databaseReference.child(user.getUid()).child(m3.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill);

        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card1").setValue(skill);
        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card2").setValue(skill);
        databaseReference.child(user.getUid()).child(m4.UsergetMonsterID()).child("BattleInformation").child("SkillInformation").child("Card3").setValue(skill);


    }

    @Override
    public void onClick(View v) {
        if(v==buttonRegister){
            registerUser();
        }
        if(v==textViewSignin){
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
