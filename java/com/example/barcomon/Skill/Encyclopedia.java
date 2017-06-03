package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/4/24.
 */

public class Encyclopedia extends Skill {
    public Encyclopedia(){
        this.skillName="Encyclopedia";
        this.uniqueMonster="Book";
        this.upgradeAttack=0;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0;

        this.reduceEnemyAttack=0;
        this.reduceEnemyDense=0.4;

        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
