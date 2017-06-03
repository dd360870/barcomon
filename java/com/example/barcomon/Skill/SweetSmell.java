package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/4/24.
 */

public class SweetSmell extends Skill {
    public SweetSmell(){
        this.skillName="SweetSmell";
        this.uniqueMonster="Bento";
        this.upgradeAttack=0;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0;

        this.reduceEnemyAttack=0.2;
        this.reduceEnemyDense=0.2;

        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
