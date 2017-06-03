package com.example.barcomon.Skill;

/**
 * Created by andy on 2017/5/30.
 */

public class DeathAttack extends Skill {
    public DeathAttack() {
        this.skillName = "DeathAttack";
        this.uniqueMonster = "";
        this.upgradeAttack = 0;
        this.upgradeDefense = 0;
        this.reducedAttack = 0;
        this.reduceDefense = 0;

        this.reduceEnemyAttack = 0;
        this.reduceEnemyDense = 0;

        this.uniqueMonsterExtraAttck = 1.5;
        this.uniqueMonsterExtraDefense = 1.5;
    }
}
