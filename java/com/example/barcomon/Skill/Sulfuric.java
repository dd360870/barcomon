package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/4/24.
 */

public class Sulfuric extends Skill {

    public Sulfuric(){
        this.skillName="Sulfuric";
        this.uniqueMonster="Bottle";

        this.upgradeAttack=0.3;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0.2;

        this.reduceEnemyAttack=0;
        this.reduceEnemyDense=0;

        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
