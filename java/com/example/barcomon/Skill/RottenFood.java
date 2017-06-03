package com.example.barcomon.Skill;

/**
 * Created by BoAn on 2017/4/24.
 */

public class RottenFood extends Skill {
    public RottenFood(){
        this.skillName="RottenFood";
        this.uniqueMonster="Bento";
        this.upgradeAttack=0;
        this.upgradeDefense=0;
        this.reducedAttack=0;
        this.reduceDefense=0.4;
        this.reduceEnemyAttack=0.3;
        this.reduceEnemyDense=0.2;
        this.uniqueMonsterExtraAttck=1.5;
        this.uniqueMonsterExtraDefense=1.5;
    }
}
