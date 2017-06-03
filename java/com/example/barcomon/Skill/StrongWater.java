package com.example.barcomon.Skill;



/**
 * Created by BoAn on 2017/3/19.
 */

public class StrongWater extends Skill {

    public StrongWater(){
        this.skillName="StrongWater";
        this.uniqueMonster="Bottle";

        this.upgradeAttack=0;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0;
        this.reduceEnemyAttack=0;
        this.reduceEnemyDense=0.3;

        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
