package com.example.barcomon;


import com.example.barcomon.battleMonster.BattleMonster;

/**
 * Created by andy on 2017/5/30.
 */

public class Cans extends BattleMonster {

    public Cans(Monster m,String monsterclass){
        this.Attack=m.Attack;
        this.Defense=m.Defense;
        this.LoveLevel=m.LoveLevel;
        this.Level=m.Level;
        this.HP=m.Strength;
        this.MonsterClass=monsterclass;
    }

}
