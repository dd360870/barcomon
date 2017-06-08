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
                "累積評價10種食品可解鎖強力水柱",
                "累積評價種10種日用品可解鎖硫酸水柱",
                "我想成為寶特瓶之王!!",
                "我的腿好短...我需要鍛鍊!",
                "我的鼻孔可不是裝飾，是用來發射水柱的~",
                "累積建立20種食品可解鎖鐵罐",
                "累積建立20種日用品可解鎖馬克杯",
                "累積建立40種食品可解鎖便當",
                "累積建立20種書籍文具可解鎖書",
                "累積評價20種休閒娛樂可解鎖敬老尊賢",
                "累積評價30種書籍文具可解鎖愛的教育",
                "累積評價30種休閒娛樂可解鎖不甘示弱",
                "累積評價30種日用品可解鎖死亡反擊"
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
                "累積評價20種日用品可解鎖自我壓縮",
                "累積評價10種休閒娛樂可解鎖滾動",
                "我想要戰鬥!",
                "比起冷藏庫我更喜歡冷凍庫...",
                "我的拉環很脆弱的...",
                "累積建立20種食品可解鎖鐵罐",
                "累積建立20種日用品可解鎖馬克杯",
                "累積建立40種食品可解鎖便當",
                "累積建立20種書籍文具可解鎖書",
                "累積評價20種休閒娛樂可解鎖敬老尊賢",
                "累積評價30種書籍文具可解鎖愛的教育",
                "累積評價30種休閒娛樂可解鎖不甘示弱",
                "累積評價30種日用品可解鎖死亡反擊"
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
                "累積評價40種食品可解鎖熱水",
                "累積評價50種食品可解鎖咖啡",
                "需要我泡杯茶嗎~",
                "最喜歡喝咖啡聊是非喲~",
                "要來聊聊寶特瓶和鐵罐的八卦嗎~",
                "累積建立20種食品可解鎖鐵罐",
                "累積建立20種日用品可解鎖馬克杯",
                "累積建立40種食品可解鎖便當",
                "累積建立20種書籍文具可解鎖書",
                "累積評價20種休閒娛樂可解鎖敬老尊賢",
                "累積評價30種書籍文具可解鎖愛的教育",
                "累積評價30種休閒娛樂可解鎖不甘示弱",
                "累積評價30種日用品可解鎖死亡反擊"
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

    public String BentoPlay(){
        String[] str={
                "咬住別人的腳是我的樂趣!!",
                "噓...我正在埋伏敵人...",
                "拿雞腿砸頭好好玩~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String BentoChat(){
        String[] str={
                "使用環保筷可額外提升攻擊力~",
                "啃雞腿可額外提升攻擊力~",
                "可以買雞腿給我吃嗎?",
                "平時該隨身攜帶環保筷，快買!",
                "累積評價20種食品可解鎖香味四溢",
                "累積評價30種食品可解鎖臭酸",
                "請你吃雞腿要不要>0<",
                "我真的是一肚子食物...",
                "我要去散散步順便咬人...",
                "累積建立20種食品可解鎖鐵罐",
                "累積建立20種日用品可解鎖馬克杯",
                "累積建立40種食品可解鎖便當",
                "累積建立20種書籍文具可解鎖書",
                "累積評價20種休閒娛樂可解鎖敬老尊賢",
                "累積評價30種書籍文具可解鎖愛的教育",
                "累積評價30種休閒娛樂可解鎖不甘示弱",
                "累積評價30種日用品可解鎖死亡反擊"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BentoFeed(){
        String[] str={
                "我可以自給自足，少餵我一點",
                "我想要減肥...吃太多了...",
                "不要只餵雞腿...多一點蔬菜"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BentoSleep(){
        String[] str={
                "zzz...zzz...",
                "睡前禁止吃宵夜!!",
                "咬一口雞腿再睡好了..餓死了.."
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String BookPlay(){
        String[] str={
                "咻咻咻~接好我的紙飛機~",
                "書店很多有趣的書...",
                "我在圖書館能待上整整一天~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BookChat(){
        String[] str={
                "使用書籤可額外提升攻擊力",
                "使用書衣可額外提供守備力",
                "書籤是個方便好用的東西呢~",
                "看漫畫用書衣就不怕被老師發現~",
                "累積評價10種書籍文具可解鎖紙飛機",
                "累積評價20種書籍文具可解鎖百科全書",
                "我想多了解人類的世界~",
                "多跟我聊天我就開心了~",
                "我一天可以看完10本書呦~",
                "累積建立20種食品可解鎖鐵罐",
                "累積建立20種日用品可解鎖馬克杯",
                "累積建立40種食品可解鎖便當",
                "累積建立20種書籍文具可解鎖書",
                "累積評價20種休閒娛樂可解鎖敬老尊賢",
                "累積評價30種書籍文具可解鎖愛的教育",
                "累積評價30種休閒娛樂可解鎖不甘示弱",
                "累積評價30種日用品可解鎖死亡反擊"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BookFeed(){
        String[] str={
                "再生紙吃起來乾乾的...",
                "衛生紙跟人類的棉花糖一樣好吃",
                "茶泡紙別有一番風味呢~"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String BookSleep(){
        String[] str={
                "zzz...zzz...",
                "念睡前故事給我聽~",
                "讓我再看完一本書!!"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }

    public String UpgradeAttack(){
        String[] str={
                "我想要變得更強!!",
                "訓練不只要快!還要更快!!",
                "我要打爆對手~",
                "訓練的好累哦~",
                "以攻擊力壓制對手吧!"
        };
        Calendar c=Calendar.getInstance();
        int num = Math.abs((int)c.getTimeInMillis()%str.length);
        return str[num];
    }
    public String UpgradeDefense(){
        String[] str={
                "防禦是最好的攻擊",
                "訓練不只要快!還要更快!!",
                "我要變成銅牆鐵壁!",
                "訓練的好累哦~",
                "絕對防禦就不怕敵人的攻擊!"
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
        else if(skillName.equals("DeathAttack")) return str+"死亡反擊";
        else if(skillName.equals("LoveTeaching")) return str+"愛的教育";
        else if(skillName.equals("NotResigned")) return str+"不甘示弱";
        else if(skillName.equals("RespectOlder")) return str+"敬老尊賢";
        else return str+"硫酸水柱";
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
        else if(skillName.equals("DeathAttack")) return str+"死亡反擊";
        else if(skillName.equals("LoveTeaching")) return str+"愛的教育";
        else if(skillName.equals("NotResigned")) return str+"不甘示弱";
        else if(skillName.equals("RespectOlder")) return str+"敬老尊賢";
        else return str+"硫酸水柱";

    }

}
