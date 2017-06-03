package com.example.barcomon;

/**
 * Created by BoAn on 2017/3/3.
 */

public class UserInformation {

    protected String name;
    protected String MainMonster;
    protected int BarCoMonEnergy;
    protected String ownMonster;
    protected String UID;
    //protected int[] ownMonster = new int[5];

    //protected int[] ownMonster = {12, 33, 65, 32, 11, 76, 98, 23, 55, 34, 76, 81, 54};
    public UserInformation(){

    }

    public UserInformation(String name, String mainmonster,int barcomonenergy) {
        this.name = name;
        this.MainMonster = mainmonster;
        this.BarCoMonEnergy = barcomonenergy;
        ownMonster="10000";
        //this.ownMonster = new int[5];
        //for(int i=0;i<5;i++) this.ownMonster[i]=0;
        //System.arraycopy(this.ownMonster, 0, ownmonster, 0, this.ownMonster.length);
    }
    public UserInformation(String name, String mainmonster,int barcomonenergy,String ownmonster) {
        this.name = name;
        this.MainMonster = mainmonster;
        this.BarCoMonEnergy = barcomonenergy;
        ownMonster=ownmonster;
        //this.ownMonster = new int[5];
        //for(int i=0;i<5;i++) this.ownMonster[i]=0;
        //System.arraycopy(this.ownMonster, 0, ownmonster, 0, this.ownMonster.length);
    }

    public void UserPlayWithMonster(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
    public void UserChatWithMonster(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }

    public void UserFeedMonster(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
    public void UserSleepWithMonster(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }

    public void UserTrainingAttack(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
    public void UserTrainingDefense(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
    public void UserBattleSet(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
    public void UserBattle(int barCoEnergyConsume){
        this.BarCoMonEnergy-=barCoEnergyConsume;
    }
}