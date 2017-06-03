package com.example.barcomon.Equipment;

/**
 * Created by BoAn on 2017/3/17.
 */

public class Equipment {
    public String equipmentName;
    public int upgradeAttack;
    public int upgradeDefense;
    public String uniqueMonster;
    public int uniqueMonsterExtraAttck;
    public int uniqueMonsterExtraDefense;
    public Equipment(){

    }
    public Equipment(int upgradeattack,int upgradedefense){
        this.upgradeAttack=upgradeattack;
        this.upgradeDefense=upgradedefense;
    }

    public boolean isUniqueMonster(String Mon){
        if(Mon.equals(this.uniqueMonster)){return true;}
        else return false;
    }

}
