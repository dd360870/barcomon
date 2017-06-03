package com.example.barcomon;

/**
 * Created by BoAn on 2017/3/1.
 */
import android.media.Image;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Monster{

    protected String MonsterID;
    protected int Level;
    protected int LoveLevel;
    protected int Attack;
    protected int Defense;
    protected int EXP;
    protected int Strength;

    protected int UpgradeAttackValue;
    protected int UpgradeDefenseValue;
    protected int UpgradePlayLoveLevelValue;
    protected int UpgradeChatLoveLevelValue;
    protected int UpgradeEXPValue;
    protected int UpgradeStrength;

    protected String MonsterType;
    protected String MonsterInfomation;

    //屬性


    public Monster(){}
    public Monster(String mosterID,int level,int lovelevel,int attack,int defense,int exp,int strength,String monstertype,String monsterinformation,int upgradeAttackValue,int upgradeDefenseValue,int upgradePlayLoveLevelValue,int upgradeChatLoveLevelValue,int upgradeEXPValue,int upgradeStrengthValue){
        MonsterID=mosterID;
        Level=level;
        LoveLevel=lovelevel;
        Attack=attack;
        Defense=defense;
        EXP=exp;
        Strength=strength;
        MonsterType=monstertype;
        MonsterInfomation=monsterinformation;
        UpgradeAttackValue=upgradeAttackValue;
        UpgradeDefenseValue=upgradeDefenseValue;
        UpgradePlayLoveLevelValue=upgradePlayLoveLevelValue;
        UpgradeChatLoveLevelValue=upgradeChatLoveLevelValue;
        UpgradeEXPValue=upgradeEXPValue;
        UpgradeStrength=upgradeStrengthValue;
    }

    public void UsersetMonsterID(String monsterid){
        MonsterID=monsterid;
    }

    public void UsersetLevel(int level){
        Level=level;
    }
    public void UsersetLoveLevel(int lovelevel){
        LoveLevel=lovelevel;
    }
    public void UsersetAttack(int attack){
        Attack=attack;
    }
    public void UsersetDefense(int defense){
        Defense=defense;
    }
    public void UsersetEXP(int exp){
        EXP=exp;
    }
    public void UsersetStrength(int strength){
        Strength=strength;
    }

    public void UsersetMonsterType(String monstertype){
        MonsterType=monstertype;
    }
    public void UsersetMonsterInformation(String monsterinformation){
        MonsterInfomation=monsterinformation;
    }

    public String UsergetMonsterID(){
        return MonsterID;
    }
    public int UsergetLevel(){
        return Level;
    }
    public int UsergetLoveLevel(){
        return LoveLevel;
    }
    public int UsergetAttack(){
        return Attack;
    }
    public int UsergetDefense(){
        return Defense;
    }
    public int UsergetEXP(){
        return EXP;
    }
    public int UsergetStrength(){
        return Strength;

    }

    public String UsergetMonsterType(){
        return MonsterType;
    }
    public String UsergetMonsterInformation(){
        return MonsterInfomation;
    }

    public void upgradeEXP(){
        EXP+=UpgradeEXPValue;
    }

    public void trainingAttack(){
        int usedStrength=200;

        if((Attack+UpgradeAttackValue)>9999) Attack=9999;
        else Attack+=UpgradeAttackValue;
        Strength-=usedStrength;

    }

    public void traningDefense() {
        int usedStrength=200;
        if((Defense+UpgradeDefenseValue)>9999) Defense=9999;
        else Defense+=UpgradeDefenseValue;
        Strength-=usedStrength;
    }
    public void Feeding(){

        if((Strength+UpgradeStrength/2)>9999) Strength=9999;
        else Strength+=UpgradeStrength/2;

        if((LoveLevel+UpgradePlayLoveLevelValue/2)>9999) LoveLevel=9999;
        else LoveLevel+=UpgradePlayLoveLevelValue/2;
    }
    public void Play(){
        if((LoveLevel+UpgradePlayLoveLevelValue)>9999)LoveLevel=9999;
        else LoveLevel+=UpgradePlayLoveLevelValue;
    }
    public void Chat(){
        if((LoveLevel+UpgradeChatLoveLevelValue)>9999)LoveLevel=9999;
        else LoveLevel+=UpgradeChatLoveLevelValue;
    }
    public void Sleep(){
        if((Strength+UpgradeStrength)>9999) Strength=9999;
        else Strength+=UpgradeStrength;
    }

    public void upgradeLevel(){
        if(EXP>=100){
            Level+=1;
            EXP=0;
        }
    }


    public void bottleTrainingAttack(){
        int usedStrength=200;
        if(LoveLevel>=0 && LoveLevel<100){

            if((Attack+UpgradeAttackValue)>9999) Attack=9999;
            else Attack+=UpgradeAttackValue;

            Strength-=usedStrength;

        }
        else if(LoveLevel>=100){

            if((Attack+UpgradeAttackValue+(LoveLevel/10))>9999) Attack=9999;
            else {
                Attack += UpgradeAttackValue;
                Attack += LoveLevel / 10;
            }

            Strength-=usedStrength;
        }
    }
    public void bottleTrainingDefense(){
        int usedStrength=200;
        if(LoveLevel>=0 && LoveLevel<100){

            if((Defense+UpgradeDefenseValue)>9999) Defense=9999;
            else Defense+=UpgradeDefenseValue;

            Strength-=usedStrength;
        }
        else if(LoveLevel>=100){
            if((Defense+UpgradeDefenseValue+(LoveLevel/10))>9999) Defense=9999;
            else {
                Defense += UpgradeDefenseValue;
                Defense += LoveLevel / 10;
            }
            Strength-=usedStrength;
        }
    }


    public void bentoFeeding(){
        if(Strength+800+(UpgradeStrength/2)>9999) Strength=9999;
        else {
            Strength += 800;
            Strength += UpgradeStrength / 2;
        }
        if(LoveLevel+(UpgradePlayLoveLevelValue/2)>9999) LoveLevel=9999;
        else LoveLevel+=UpgradePlayLoveLevelValue/2;
    }





}
