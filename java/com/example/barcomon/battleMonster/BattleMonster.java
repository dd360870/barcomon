package com.example.barcomon.battleMonster;



import com.example.barcomon.Equipment.Equipment;
import com.example.barcomon.Skill.Skill;

import java.util.ArrayList;

/**
 * Created by BoAn on 2017/3/19.
 */

public class BattleMonster {
    public int Attack;
    public int Defense;
    public int Level;
    public int LoveLevel;
    public int HP;
    public String MonsterClass;
    public ArrayList<Equipment> equipmentList =new ArrayList<Equipment>();
    public ArrayList<Skill> skillList=new ArrayList<Skill>();
    public BattleMonster(){
    }

    public BattleMonster(int attack,int defense,int level,int lovelevel,int hp,String monsterclass){
        Attack=attack;
        Defense=defense;
        Level=level;
        LoveLevel=lovelevel;
        HP=hp;
        MonsterClass=monsterclass;
    }

    public void SetUpEquipment(Equipment equip){

        if(equip.isUniqueMonster(MonsterClass)){
            this.Attack+=equip.uniqueMonsterExtraAttck;
            this.Defense+=equip.uniqueMonsterExtraDefense;
        }
        this.Attack+=equip.upgradeAttack;
        this.Defense+=equip.upgradeDefense;
        //equipmentList.add(equip);
    }

    public void SetUpSkill(Skill skill){
        skill.isUniqueMonster(MonsterClass);
        this.Attack*=skill.upgradeAttack;
        this.Defense*=skill.upgradeDefense;
    }

    public void RemoveEquipment(Equipment equip){
        if(equip.isUniqueMonster(MonsterClass)){
            this.Attack-=equip.uniqueMonsterExtraAttck;
            this.Defense-=equip.uniqueMonsterExtraDefense;
        }
        this.Attack-=equip.upgradeAttack;
        this.Defense-=equip.upgradeDefense;
    }

    public void RemoveSkill(Skill skill){
        skill.isUniqueMonster(MonsterClass);
        this.Attack/=skill.upgradeAttack;
        this.Defense/=skill.upgradeDefense;
    }


}
