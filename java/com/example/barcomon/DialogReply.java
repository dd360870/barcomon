package com.example.barcomon;


import java.util.Calendar;

/**
 * Created by BoAn on 2017/5/10.
 */

public class DialogReply {

    public DialogReply(){

    }

    public String BottlePlay(){
        String[] str={"來玩拋接瓶蓋遊戲吧~",
                "看我的前滾翻~",
                "可以帶我去游泳嗎?"};

        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);

        return str[num];
    }

    public String BottleChat(){
        String[] str={"瓶蓋可以讓我額外提升攻擊力~",
                "標籤可以額外提升我的守備力~",
                "瓶蓋可從商店購買取得~",
                "標籤可從商店購買取得~",
                "同時裝備瓶蓋和強力水柱可觸發隱藏能力",
                "同時裝備瓶蓋和硫酸水柱可觸發隱藏能力",
                "需評價5種飲料類商品才可解鎖強力水柱",
                "需評價10種飲料類商品才可解鎖硫酸水柱",
                "我想成為寶特瓶之王!!",
                "我的腿好短...我需要鍛鍊!",
                "我的鼻孔可不是裝飾，是用來發射水柱的~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];

    }
    public String BottleFeed(){
        String[] str={
                "今天的水真好喝~",
                "想喝點別的，比方說汽水之類的...",
                "水喝膩了QAQ..."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BottleSleep(){
        String[] str={
                "zzz...",
                "好睏哦~",
                "上床前要刷牙~"
        };

        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String CanPlay(){
        String[] str={
                "我的樂趣就是戰鬥!!",
                "待在冰箱裡最舒服了...",
        "不要把我從溜滑梯上滾下來QAQ",
        "別拉我的拉環..."
        };

        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String CanChat(){
        String[] str={
                "杯套可額外提升守備力~",
                "拉環可額外提升攻擊力~",
                "快點去商店買杯套給我啦",
                "我的拉環掉了，幫我去商店買",
                "同時裝備杯套和滾動可觸發隱藏能力",
                "同時裝備拉環和自我壓縮可觸發隱藏能力",
                "需評價10種飲料類商品才可解鎖自我壓縮",
                "需評價20種飲料類商品才可解鎖滾動",
                "我想要戰鬥!",
                "比起冷藏庫我更喜歡冷凍庫...",
                "我的拉環很脆弱的..."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String CanFeed(){
        String[] str={
                "我最喜歡的食物是冰塊~",
                "汽水總是讓我的肚子發脹",
                "可以換個橘子口味的汽水嗎..."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];

    }
    public String CanSleep(){
        String[] str={
                "zzz...",
                "我想睡在冷凍庫...",
                "我想先洗完頭(拉環)再睡覺..."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String MugPlay(){
        String[] str={
                "我只想待在杯子裡玩...",
                "100度的杯子溫泉最舒服喲~",
                "我不想出去玩，會流汗..."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String MugChat(){
        String[] str={
                "茶包可額外提升攻擊力喲~",
                "杯墊可額外提升守備力喲~",
                "我想喝茶，去商店買茶包給我~",
                "擁有杯墊，桌上才不會濕濕的喲~",
                "同時裝備茶包和咖啡醒腦可觸發隱藏能力",
                "同時裝備杯墊和沸水攻擊可觸發隱藏能力",
                "需評價15種日用品類商品才可解鎖沸水攻擊",
                "需評價20種日用品類商品才可解鎖咖啡醒腦",
                "需要我泡杯茶嗎~",
                "最喜歡喝咖啡聊是非喲~",
                "要來聊聊寶特瓶和鐵罐的八卦嗎~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];

    }
    public String MugFeed(){
        String[] str={
                "咖啡豆還不錯吃...但有點苦...",
                "牛奶最好喝了~",
                "軟軟的珍珠配上奶茶就完美了..."

        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String MugSleep(){
        String[] str={
                "zzz...zzz...:",
                "想睡覺了...幫我換杯乾淨的水...",
                "洗澡睡覺囉~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];

    }


    public String setMyEquipment(String equipmentName){
        String str="我方怪獸裝備了";
        if(equipmentName.equals("Capsule")) return str+"瓶蓋";
        else if(equipmentName.equals("BottleLogo")) return str+"標籤";
        else if(equipmentName.equals("Bookmark")) return str+"書籤";
        else if(equipmentName.equals("BookClothing")) return str+"書衣";
        else if(equipmentName.equals("CupSet")) return str+"杯套";
        else if(equipmentName.equals("PullRing")) return str+"拉環";
        else if(equipmentName.equals("Chopsticks")) return str+"環保筷";
        else if(equipmentName.equals("ChickenLegs")) return str+"雞腿";
        else if(equipmentName.equals("Coaster")) return str+"杯墊";
        else if(equipmentName.equals("InstantDrink")) return str+"沖泡包";
        else if(equipmentName.equals("NoEquip"))return str+"塑膠袋";
        else return str+"???";
    }

    public String setEnemyEquipment(String equipmentName){
        String str="對手怪獸裝備了";
        if(equipmentName.equals("Capsule")) return str+"瓶蓋";
        else if(equipmentName.equals("BottleLogo")) return str+"標籤";
        else if(equipmentName.equals("Bookmark")) return str+"書籤";
        else if(equipmentName.equals("BookClothing")) return str+"書衣";
        else if(equipmentName.equals("CupSet")) return str+"杯套";
        else if(equipmentName.equals("PullRing")) return str+"拉環";
        else if(equipmentName.equals("Chopsticks")) return str+"環保筷";
        else if(equipmentName.equals("ChickenLegs")) return str+"雞腿";
        else if(equipmentName.equals("Coaster")) return str+"杯墊";
        else if(equipmentName.equals("InstantDrink")) return str+"沖泡包";
        else if(equipmentName.equals("NoEquip"))return str+"塑膠袋";
        else return str+"???";
    }

    public String setMySkill(String skillName){
        String str="我方怪獸使用了";
        if(skillName.equals("StrongWater"))return str+"強力水柱";
        else if(skillName.equals("H2SO4"))return str+"硫酸水柱";
        else if(skillName.equals("PaperPlane"))return str+"紙飛機攻擊";
        else if(skillName.equals("Encyclopedia"))return str+"百科全書";
        else if(skillName.equals("Compression"))return str+"自我壓縮";
        else if(skillName.equals("CansScroll"))return str+"滾動";
        else if(skillName.equals("SweetSmell"))return str+"香氣四溢";
        else if(skillName.equals("RottenFood"))return str+"臭酸攻擊";
        else if(skillName.equals("HotWater"))return str+"熱水攻擊";
        else if(skillName.equals("Coffee"))return str+"咖啡醒腦";
        else if(skillName.equals("NoSkill")) return str+"衝撞";
        else return str+"???";
    }

    public String setEnemySkill(String skillName){
        String str="對手怪獸使用了";
        if(skillName.equals("StrongWater"))return str+"強力水柱";
        else if(skillName.equals("H2SO4"))return str+"硫酸水柱";
        else if(skillName.equals("PaperPlane"))return str+"紙飛機攻擊";
        else if(skillName.equals("Encyclopedia"))return str+"百科全書";
        else if(skillName.equals("Compression"))return str+"自我壓縮";
        else if(skillName.equals("CansScroll"))return str+"滾動";
        else if(skillName.equals("SweetSmell"))return str+"香氣四溢";
        else if(skillName.equals("RottenFood"))return str+"臭酸攻擊";
        else if(skillName.equals("HotWater"))return str+"熱水攻擊";
        else if(skillName.equals("Coffee"))return str+"咖啡醒腦";
        else if(skillName.equals("NoSkill"))return str+"衝撞";
        else return str+"???";

    }

}
