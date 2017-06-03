package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/3/22.
 */

public class PaperPlane extends Skill {
    public PaperPlane(){
        this.skillName="PaperPlane";
        this.uniqueMonster="Bottle";
        this.upgradeAttack=0.2;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0;

        this.reduceEnemyAttack=0;
        this.reduceEnemyDense=0.1;

        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
