package com.example.barcomon;

import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class SkillPage extends Fragment implements View.OnClickListener{
    public String[] provinces = new String[] { "第一張", "第二張", "第三張"};
    ImageView skillCard1,skillCard2,skillCard3;
    ImageView strongWaterLayout,paperPlaneLayout,h2so4Layout,encyclopediaLayout,
            compressionLayout,cansScrollLayout,sweetSmellLayout,rottenFoodLayout,hotWaterLayout,
            coffeeLayout,noSkillLayout,deathAttackLayout,
            loveTeachingLayout,notResignedLayout,respectOlderLayout;

    SkillInformation skillInformation;

    UserInformation userinformation;
    //firebase auth object
    private FirebaseAuth firebaseAuth;
    //defining a database reference
    private DatabaseReference databaseReference;

    private String UserMainMonster="-1";

    private UserItem useritem;

    private String skill1,skill2,skill3;

    private static final int SOUND_COUNT = 2;
    private SoundPool soundPool;
    private int CardSoundID;
    private int ClickCardID;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.skill_page,null);

        firebaseAuth = FirebaseAuth.getInstance();
        getUserInformation();
        getUserItem();

        skillCard1=(ImageView)view.findViewById(R.id.skillCard1);
        skillCard2=(ImageView)view.findViewById(R.id.skillCard2);
        skillCard3=(ImageView)view.findViewById(R.id.skillCard3);

        strongWaterLayout=(ImageView) view.findViewById(R.id.skillPageStrongWaterLayout);
        paperPlaneLayout=(ImageView)view.findViewById(R.id.skillPagePaperPlaneLayout);

        h2so4Layout=(ImageView)view.findViewById(R.id.skillPageH2SO4Layout);
        encyclopediaLayout=(ImageView)view.findViewById(R.id.skillPageEncyclopediaLayout);
        compressionLayout=(ImageView)view.findViewById(R.id.skillPageCompressionLayout);
        cansScrollLayout=(ImageView)view.findViewById(R.id.skillPageCansScrollLayout);
        sweetSmellLayout=(ImageView)view.findViewById(R.id.skillPageSweetSmellLayout);
        rottenFoodLayout=(ImageView)view.findViewById(R.id.skillPageRottenFoodLayout);
        hotWaterLayout=(ImageView)view.findViewById(R.id.skillPageHotWaterLayout);
        coffeeLayout=(ImageView)view.findViewById(R.id.skillPageCoffeeLayout);

        noSkillLayout=(ImageView)view.findViewById(R.id.skillPageNoSkillLayout);

        deathAttackLayout=(ImageView)view.findViewById(R.id.skillPageDeathAttack);
        loveTeachingLayout=(ImageView)view.findViewById(R.id.skillPageLoveTeaching);
        notResignedLayout=(ImageView)view.findViewById(R.id.skillPageNotResigned);
        respectOlderLayout=(ImageView)view.findViewById(R.id.skillPageRespectOlder);

        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
        CardSoundID= this.soundPool.load(getActivity(), R.raw.cardsound, 1);
        ClickCardID= this.soundPool.load(getActivity(), R.raw.battleset_selectcard, 1);


        deathAttackLayout.setOnClickListener(this);
        loveTeachingLayout.setOnClickListener(this);
        notResignedLayout.setOnClickListener(this);
        respectOlderLayout.setOnClickListener(this);

        strongWaterLayout.setOnClickListener(this);
        paperPlaneLayout.setOnClickListener(this);
        h2so4Layout.setOnClickListener(this);
        encyclopediaLayout.setOnClickListener(this);
        compressionLayout.setOnClickListener(this);
        cansScrollLayout.setOnClickListener(this);
        sweetSmellLayout.setOnClickListener(this);
        rottenFoodLayout.setOnClickListener(this);
        hotWaterLayout.setOnClickListener(this);
        coffeeLayout.setOnClickListener(this);

        noSkillLayout.setOnClickListener(this);

        return view;
    }






    @Override
    public void onClick(View v) {
        soundPool.play(ClickCardID, 1, 1, 0, 0, 1);
        if(v==strongWaterLayout){
            if(checkMonsterAndSkill("StrongWater")) {
                if(useritem.StrongWater==1) {
                    strongWaterChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==paperPlaneLayout){
            if(checkMonsterAndSkill("PaperPlane")) {
                if(useritem.PaperPlane==1) {
                    paperPlaneChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==h2so4Layout){
            if(checkMonsterAndSkill("H2SO4")) {
                if(useritem.H2SO4==1) {
                    h2so4ChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==encyclopediaLayout){
            if(checkMonsterAndSkill("Encyclopedia")) {
                if(useritem.Encyclopedia==1) {
                    encyclopediaChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==compressionLayout){
            if(checkMonsterAndSkill("Compression")) {
                if(useritem.Compression==1) {
                    compressionChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==cansScrollLayout){
            if(checkMonsterAndSkill("CansScroll")) {
                if(useritem.CansScroll==1) {
                    cansScrollChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==sweetSmellLayout){
            if(checkMonsterAndSkill("SweetSmell")) {
                if(useritem.SweetSmell==1) {
                    sweetSmellChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==rottenFoodLayout){
            if(checkMonsterAndSkill("RottenFood")) {
                if(useritem.RottenFood==1) {
                    rottenFoodChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==hotWaterLayout){
            if(checkMonsterAndSkill("HotWater")) {
                if(useritem.HotWater==1) {
                    hotWaterChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==coffeeLayout){
            if(checkMonsterAndSkill("Coffee")) {
                if(useritem.Coffee==1) {
                    coffeeChooseCardField(v);
                }
                else{
                    Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(getActivity(), "此卡無法裝備在怪獸上", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==noSkillLayout){
            noSkillChooseCardField(v);
        }
        if(v==deathAttackLayout){
            if(useritem.DeathAttack==1) {
                deathAttackChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==loveTeachingLayout){
            if(useritem.LoveTeaching==1) {
                loveTeachingChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==notResignedLayout){
            if(useritem.NotResigned==1) {
                notResignedChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
            }
        }
        if(v==respectOlderLayout){
            if(useritem.RespectOlder==1) {
                respectOlderChooseCardField(v);
            }
            else{
                Toast.makeText(getActivity(), "你沒有這張技能卡", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void deathAttackChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("DeathAttack")){

                        }
                        else if(skill2.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("010","DeathAttack"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                        skill1="DeathAttack";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("DeathAttack")){

                        }
                        else if(skill3.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("010","DeathAttack"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                        skill2="DeathAttack";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("DeathAttack")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("DeathAttack")){

                        }
                        updateSkillCard(new SkillInformation("010","DeathAttack"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                        skill3="DeathAttack";
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

    private void loveTeachingChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("LoveTeaching")){

                        }
                        else if(skill2.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("011","LoveTeaching"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                        skill1="LoveTeaching";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("LoveTeaching")){

                        }
                        else if(skill3.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("011","LoveTeaching"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                        skill2="LoveTeaching";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("LoveTeaching")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("LoveTeaching")){

                        }
                        updateSkillCard(new SkillInformation("011","LoveTeaching"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                        skill3="LoveTeaching";
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

    private void notResignedChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("NotResigned")){

                        }
                        else if(skill2.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("012","NotResigned"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                        skill1="NotResigned";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("NotResigned")){

                        }
                        else if(skill3.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("012","NotResigned"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                        skill2="NotResigned";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("NotResigned")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("NotResigned")){

                        }
                        updateSkillCard(new SkillInformation("012","NotResigned"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                        skill3="NotResigned";
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


    private void respectOlderChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RespectOlder")){

                        }
                        else if(skill2.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("013","RespectOlder"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                        skill1="RespectOlder";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("RespectOlder")){

                        }
                        else if(skill3.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("013","RespectOlder"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                        skill2="RespectOlder";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("RespectOlder")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("RespectOlder")){

                        }
                        updateSkillCard(new SkillInformation("013","RespectOlder"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                        skill3="RespectOlder";
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


    private  boolean checkMonsterAndSkill(String skill){

        if(UserMainMonster.equals("000")&&skill.equals("StrongWater")) return true;
        else if(UserMainMonster.equals("000")&&skill.equals("H2SO4")) return true;
        else if(UserMainMonster.equals("001")&&skill.equals("Compression")) return true;
        else if(UserMainMonster.equals("001")&&skill.equals("CansScroll")) return true;
        else if(UserMainMonster.equals("002")&&skill.equals("HotWater"))return true;
        else if(UserMainMonster.equals("002")&&skill.equals("Coffee")) return true;
        else if(UserMainMonster.equals("003")&&skill.equals("SweetSmell"))return true;
        else if(UserMainMonster.equals("003")&&skill.equals("RottenFood"))return true;
        else if(UserMainMonster.equals("004")&&skill.equals("PaperPlane")) return true;
        else if(UserMainMonster.equals("004")&&skill.equals("Encyclopedia")) return true;
        else return false;
    }

    private void noSkillChooseCardField(final View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        updateSkillCard(new SkillInformation("100","NoSkill"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                        skill1="NoSkill";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        updateSkillCard(new SkillInformation("100","NoSkill"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                        skill2="NoSkill";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        updateSkillCard(new SkillInformation("100","NoSkill"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                        skill3="NoSkill";
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

    private void strongWaterChooseCardField(final View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("StrongWater")){

                        }
                        else if(skill2.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("000","StrongWater"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                        skill1="StrongWater";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("StrongWater")){

                        }
                        else if(skill3.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("000","StrongWater"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                        skill2="StrongWater";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("StrongWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("StrongWater")){

                        }
                        updateSkillCard(new SkillInformation("000","StrongWater"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                        skill3="StrongWater";
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

    private void h2so4ChooseCardField(final View v) {//H2SO4
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("H2SO4")){

                        }
                        else if(skill2.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("001","H2SO4"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                        skill1="H2SO4";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("H2SO4")){

                        }
                        else if(skill3.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("001","H2SO4"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                        skill2="H2SO4";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("H2SO4")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("H2SO4")){

                        }
                        updateSkillCard(new SkillInformation("001","H2SO4"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                        skill3="H2SO4";
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

    private void paperPlaneChooseCardField(final View v) {//PaperPlane
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("PaperPlane")){

                        }
                        else if(skill2.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("002","PaperPlane"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                        skill1="PaperPlane";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("PaperPlane")){

                        }
                        else if(skill3.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("002","PaperPlane"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                        skill2="PaperPlane";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("PaperPlane")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("PaperPlane")){

                        }
                        updateSkillCard(new SkillInformation("002","PaperPlane"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                        skill3="PaperPlane";
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


    private void encyclopediaChooseCardField(final View v) {//Encyclopedia
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Encyclopedia")){

                        }
                        else if(skill2.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("003","Encyclopedia"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                        skill1="Encyclopedia";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Encyclopedia")){

                        }
                        else if(skill3.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("003","Encyclopedia"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                        skill2="Encyclopedia";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Encyclopedia")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Encyclopedia")){

                        }
                        updateSkillCard(new SkillInformation("003","Encyclopedia"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                        skill3="Encyclopedia";
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


    private void compressionChooseCardField(final View v) {//Compression
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Compression")){

                        }
                        else if(skill2.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("004","Compression"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                        skill1="Compression";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Compression")){

                        }
                        else if(skill3.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("004","Compression"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                        skill2="Compression";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Compression")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Compression")){

                        }
                        updateSkillCard(new SkillInformation("004","Compression"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                        skill3="Compression";
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

    private void cansScrollChooseCardField(final View v) {//CansScroll
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("CansScroll")){

                        }
                        else if(skill2.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("005","CansScroll"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                        skill1="CansScroll";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("CansScroll")){

                        }
                        else if(skill3.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("005","CansScroll"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                        skill2="CansScroll";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("CansScroll")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("CansScroll")){

                        }
                        updateSkillCard(new SkillInformation("005","CansScroll"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                        skill3="CansScroll";
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


    private void sweetSmellChooseCardField(final View v) {//SweetSmell
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("SweetSmell")){

                        }
                        else if(skill2.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("006","SweetSmell"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                        skill1="SweetSmell";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("SweetSmell")){

                        }
                        else if(skill3.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("006","SweetSmell"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                        skill2="SweetSmell";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("SweetSmell")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("SweetSmell")){

                        }
                        updateSkillCard(new SkillInformation("006","SweetSmell"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                        skill3="SweetSmell";
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



    private void rottenFoodChooseCardField(final View v) {//RottenFood
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RottenFood")){

                        }
                        else if(skill2.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("007","RottenFood"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                        skill1="RottenFood";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("RottenFood")){

                        }
                        else if(skill3.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("007","RottenFood"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                        skill2="RottenFood";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("RottenFood")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("RottenFood")){

                        }
                        updateSkillCard(new SkillInformation("007","RottenFood"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                        skill3="RottenFood";
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


    private void hotWaterChooseCardField(final View v) {//HotWater
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("HotWater")){

                        }
                        else if(skill2.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("008","HotWater"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                        skill1="HotWater";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("HotWater")){

                        }
                        else if(skill3.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("008","HotWater"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                        skill2="HotWater";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("HotWater")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("HotWater")){

                        }
                        updateSkillCard(new SkillInformation("008","HotWater"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                        skill3="HotWater";
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



    private void coffeeChooseCardField(final View v) {//Coffee
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("選擇卡片順序");
        builder.setItems(provinces, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Coffee")){

                        }
                        else if(skill2.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("009","Coffee"),1);
                        skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                        skill1="Coffee";
                        break;
                    case 1:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Coffee")){

                        }
                        else if(skill3.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),3);
                            skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill3="NoSkill";
                        }
                        updateSkillCard(new SkillInformation("009","Coffee"),2);
                        skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                        skill2="Coffee";
                        break;
                    case 2:
                        soundPool.play(CardSoundID, 1, 1, 0, 0, 1);
                        if(skill1.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),1);
                            skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill1="NoSkill";
                        }
                        else if(skill2.equals("Coffee")){
                            updateSkillCard(new SkillInformation("100","NoSkill"),2);
                            skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                            skill2="NoSkill";
                        }
                        else if(skill3.equals("Coffee")){

                        }
                        updateSkillCard(new SkillInformation("009","Coffee"),3);
                        skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                        skill3="Coffee";
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

    private void updateSkillCard(final SkillInformation skill,int card){
        databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
        final FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card"+card).setValue(skill);
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
                UserMainMonster=userinformation.MainMonster;
                databaseReference = FirebaseDatabase.getInstance().getReference("Monsters");
                FirebaseUser user = firebaseAuth.getCurrentUser();
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card1").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {/////////////////////////////////////////////////
                        skillInformation = snapshot.getValue(SkillInformation.class);
                        if(isAdded()) {
                            if (skillInformation.Name.equals("StrongWater")) {
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("H2SO4")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                                skill1=skillInformation.Name;
                            }
                            if (skillInformation.Name.equals("PaperPlane")) {
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Encyclopedia")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Compression")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("CansScroll")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("SweetSmell")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RottenFood")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("HotWater")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Coffee")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NoSkill")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("DeathAttack")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("LoveTeaching")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NotResigned")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                                skill1=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RespectOlder")){
                                skillCard1.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                                skill1=skillInformation.Name;
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

                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card2").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        skillInformation = snapshot.getValue(SkillInformation.class);
                        if(isAdded()) {
                            if (skillInformation.Name.equals("StrongWater")) {
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("H2SO4")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                                skill2=skillInformation.Name;
                            }
                            if (skillInformation.Name.equals("PaperPlane")) {
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Encyclopedia")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Compression")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("CansScroll")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("SweetSmell")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RottenFood")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("HotWater")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Coffee")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NoSkill")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("DeathAttack")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("LoveTeaching")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NotResigned")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                                skill2=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RespectOlder")){
                                skillCard2.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                                skill2=skillInformation.Name;
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
                databaseReference.child(user.getUid()).child(userinformation.MainMonster).child("BattleInformation").child("SkillInformation").child("Card3").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        skillInformation = snapshot.getValue(SkillInformation.class);
                        if(isAdded()) {
                            if (skillInformation.Name.equals("StrongWater")) {
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.strongwater));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("H2SO4")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.sulfuric));
                                skill3=skillInformation.Name;
                            }
                            if (skillInformation.Name.equals("PaperPlane")) {
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.paperplane));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Encyclopedia")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.encyclopedia));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Compression")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.compression));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("CansScroll")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.cansscroll));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("SweetSmell")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.sweetsmell));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RottenFood")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.rottenfood));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("HotWater")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.hotwater));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("Coffee")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.coffee));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NoSkill")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.noskill));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("DeathAttack")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.deathattack));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("LoveTeaching")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.loveteaching));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("NotResigned")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.notresigned));
                                skill3=skillInformation.Name;
                            }
                            if(skillInformation.Name.equals("RespectOlder")){
                                skillCard3.setImageDrawable(getResources().getDrawable(R.drawable.respectolder));
                                skill3=skillInformation.Name;
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
