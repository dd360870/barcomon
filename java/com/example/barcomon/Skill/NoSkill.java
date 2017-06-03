package com.example.barcomon.Skill;

/**
 * Created by andy on 2017/5/23.
 */

public class NoSkill extends Skill{
    public NoSkill(){
        this.skillName="NoSkill";
        this.uniqueMonster="";
        this.upgradeAttack=0.1;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0;

        this.reduceEnemyAttack=0;
        this.reduceEnemyDense=0;

        this.uniqueMonsterExtraAttck=0;
        this.uniqueMonsterExtraDefense=0;
    }
}
