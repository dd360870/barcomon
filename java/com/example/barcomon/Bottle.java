package com.example.barcomon;


import com.example.barcomon.battleMonster.BattleMonster;

/**
 * Created by BoAn on 2017/3/16.
 */

public class Bottle extends BattleMonster {


    public Bottle(Monster m,String monsterclass){
        this.Attack=m.Attack;
        this.Defense=m.Defense;
        this.LoveLevel=m.LoveLevel;
        this.Level=m.Level;
        this.HP=m.Strength;
        this.MonsterClass=monsterclass;
        //this.equipmentList.add(new Capsule());
        //this.equipmentList.add(new Capsule());
        //this.equipmentList.add(new Capsule());
        //this.skillList.add(new StrongWater());
        //this.skillList.add(new StrongWater());
        //this.skillList.add(new StrongWater());
    }
/*
    public void SetUpEquipment(Equipment equip){
        equip.isUniqueMonster("Bottle");
        this.Attack+=equip.upgradeAttack;
        this.Defense+=equip.upgradeDefense;
        equipmentList.add(equip);
    }



    public void SetUpSkill(){

    }

    public void SetUpTrap(){

    }*/



}
