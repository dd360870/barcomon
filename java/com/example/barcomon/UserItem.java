package com.example.barcomon;


/**
 * Created by BoAn on 2017/3/14.
 */

public class UserItem {
    public int Capsule,BottleLogo,Bookmark,BookClothing,CupSet,PullRing,Chopsticks,ChickenLegs
            ,Coaster,InstantDrink,NoEquip;
    public int StrongWater,H2SO4,PaperPlane,Encyclopedia,Compression,CansScroll,SweetSmell
            ,RottenFood,HotWater,Coffee,NoSkill;

    public int DeathAttack,LoveTeaching,NotResigned,RespectOlder;


    public UserItem(){}
    public UserItem(int capsule,int bottleLogo,int bookmark,int bookClothing,int cupSet
            ,int pullRing,int chopsticks,int chickenLegs,int coaster,int instantDrink
            ,int strongWater,int h2SO4,int paperPlane,int encyclopedia,int compression
            ,int cansScroll,int sweetSmell,int rottenFood,int hotWater,int coffee,int deathAttack,int loveTeaching,int notresigned,int respectOlder){

        Capsule=capsule;
        BottleLogo=bottleLogo;
        Bookmark=bookmark;
        BookClothing=bookClothing;
        CupSet=cupSet;
        PullRing=pullRing;
        Chopsticks=chopsticks;
        ChickenLegs=chickenLegs;
        Coaster=coaster;
        InstantDrink=instantDrink;
        StrongWater=strongWater;
        H2SO4=h2SO4;
        PaperPlane=paperPlane;
        Encyclopedia=encyclopedia;
        Compression=compression;
        CansScroll=cansScroll;
        SweetSmell=sweetSmell;
        RottenFood=rottenFood;
        HotWater=hotWater;
        Coffee=coffee;
        DeathAttack=deathAttack;
        LoveTeaching=loveTeaching;
        NotResigned=notresigned;
        RespectOlder=respectOlder;

        NoEquip=3;
        NoSkill=3;

    }
}
