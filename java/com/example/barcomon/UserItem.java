package com.example.barcomon;

/**
 * Created by BoAn on 2017/3/14.
 */

public class UserItem {
    public int Capsule,BottleLogo,Bookmark,BookClothing,CupSet,PullRing,Chopsticks,ChickenLegs
            ,Coaster,InstantDrink,NoEquip;
    public int StrongWater,H2SO4,PaperPlane,Encyclopedia,Compression,CansScroll,SweetSmell
            ,RottenFood,HotWater,Coffee,NoSkill;


    public UserItem(){}
    public UserItem(int capsule,int bottleLogo,int bookmark,int bookClothing,int cupSet
            ,int pullRing,int chopsticks,int chickenLegs,int coaster,int instantDrink
            ,int strongWater,int h2SO4,int paperPlane,int encyclopedia,int compression
            ,int cansScroll,int sweetSmell,int rottenFood,int hotWater,int coffee){

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
        NoEquip=3;
        NoSkill=3;

    }
}
