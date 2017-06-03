package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/3/19.
 */

public class Skill {

    public String skillName;
    public double upgradeAttack;
    public double upgradeDefense;

    public double reducedAttack;
    public double reduceDefense;

    public double reduceEnemyAttack;
    public double reduceEnemyDense;

    public String uniqueMonster;
    public double uniqueMonsterExtraAttck;
    public double uniqueMonsterExtraDefense;
    public Skill(){

    }
    public Skill(int upgradeattack,int upgradedefense){
        this.upgradeAttack=upgradeattack;
        this.upgradeDefense=upgradedefense;
    }

    public boolean isUniqueMonster(String Mon){
        if(Mon.equals(this.uniqueMonster)){return true;}
        else return false;
    }
}
