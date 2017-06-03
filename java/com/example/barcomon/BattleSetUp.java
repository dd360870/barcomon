package com.example.barcomon;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class BattleSetUp extends  FragmentActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {

    FloatingActionButton /*fab_plus*/fab_store;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen =false;

    private ViewPager myViewPager;
    private View page1,page2,page3;
    private List<View> pageList;
    private MyPagerAdapter myPagerAdapter;
    private TextView eqipmentTabTextView,skillTabTextView,trapTabTextView;
    private ImageView line_tab;
    private int moveOne = 0;
    private boolean isScrolling=false;
    private boolean isBackScrolling=false;
    private long startTime=0;
    private long currentTime=0;

    private static final int SOUND_COUNT = 2;
    private SoundPool soundPool;
    private int ClickSoundID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle_set_up);

        //fab_plus = (FloatingActionButton)findViewById(R.id.fab_plus_battleSetUp);
        fab_store=(FloatingActionButton)findViewById(R.id.fab_store);


        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        this.soundPool = new SoundPool(SOUND_COUNT, AudioManager.STREAM_MUSIC, 0);
        ClickSoundID= this.soundPool.load(this, R.raw.consloebutton, 1);
/*
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpen){
                    fab_store.startAnimation(FabClose);

                    fab_plus.startAnimation(FabRanticlockwise);

                    fab_store.setClickable(false);
                    isOpen = false;
                }
                else{
                    fab_store.startAnimation(FabOpen);

                    fab_plus.startAnimation(FabRClockwise);

                    fab_store.setClickable(true);
                    isOpen = true;
                }
            }
        });*/

        fab_store.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                soundPool.play(ClickSoundID, 1, 1, 0, 0, 1);
                startActivity(new Intent(getApplicationContext(),CardStore.class));
            }
        });
        //initView();
        //initLineImage();

    }

    @Override
    protected void onStart() {
        super.onStart();
        initView();
        initLineImage();

    }

    private void initLineImage(){
        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW=dm.widthPixels;
        ViewGroup.LayoutParams lp =line_tab.getLayoutParams();
        lp.width=screenW/2;
        line_tab.setLayoutParams(lp);
        moveOne = lp.width;
    }
    private void initView(){
        myViewPager = (ViewPager)findViewById(R.id.BattleSetUpViewPager);

        //PricePlacePage pricePlacePage=new PricePlacePage();
        //CommentStartPage commentStartPage=new CommentStartPage();
        EquipmentPage equipmentPage=new EquipmentPage();
        SkillPage skillPage=new SkillPage();
        //TrapPage trapPage=new TrapPage();

        List<Fragment>fragmentList=new ArrayList<Fragment>();

        fragmentList.add(equipmentPage);
        fragmentList.add(skillPage);
        //fragmentList.add(trapPage);
        //fragmentList.add(pricePlacePage);
        //fragmentList.add(commentStartPage);

        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);

        eqipmentTabTextView=(TextView)findViewById(R.id.equipmentTextView);
        skillTabTextView=(TextView)findViewById(R.id.skillTextView);
        //trapTabTextView=(TextView)findViewById(R.id.trapTextView);


        myViewPager.setCurrentItem(0);

        eqipmentTabTextView.setTextColor(Color.RED);
        skillTabTextView.setTextColor(Color.BLACK);
        //trapTabTextView.setTextColor(Color.BLUE);

        eqipmentTabTextView.setOnClickListener(this);
        skillTabTextView.setOnClickListener(this);
        //trapTabTextView.setOnClickListener(this);

        myViewPager.setOnPageChangeListener(this);
        line_tab=(ImageView)findViewById(R.id.BattleSetUpline_tab);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        currentTime=System.currentTimeMillis();
        if(isScrolling &&(currentTime-startTime>200)){
            movePositionX(position,moveOne*positionOffset);
            startTime=currentTime;
        }
        if(isBackScrolling){
            movePositionX(position);
        }

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                eqipmentTabTextView.setTextColor(Color.RED);
                skillTabTextView.setTextColor(Color.BLACK);
                //trapTabTextView.setTextColor(Color.BLACK);
                movePositionX(0);
                break;
            case 1:
                eqipmentTabTextView.setTextColor(Color.BLACK);
                skillTabTextView.setTextColor(Color.BLUE);
                //trapTabTextView.setTextColor(Color.BLACK);
                break;
            case 2:
                eqipmentTabTextView.setTextColor(Color.BLACK);
                skillTabTextView.setTextColor(Color.BLACK);
                //trapTabTextView.setTextColor(Color.GREEN);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch(state){
            case 1:
                isScrolling=true;
                isBackScrolling=false;
                break;
            case 2:
                isScrolling=false;
                isBackScrolling=true;
                break;
            default:
                isScrolling=false;
                isBackScrolling=false;
                break;

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.equipmentTextView:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.skillTextView:
                myViewPager.setCurrentItem(1);
                break;
            //case R.id.trapTextView:
                //myViewPager.setCurrentItem(2);
                //break;
            default:
                break;


        }


    }

    private void movePositionX(int toPosition,float positionOffsetPixels){
        float curTranslationX=line_tab.getTranslationX();
        float toPositionX=moveOne*toPosition+positionOffsetPixels;
        ObjectAnimator animator =ObjectAnimator.ofFloat(line_tab,"translationX",curTranslationX,toPositionX);
        animator.setDuration(500);
        animator.start();
    }

    private void movePositionX(int toPosition){
        movePositionX(toPosition,0);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if ((keyCode == KeyEvent.KEYCODE_BACK)) {   //確定按下退出鍵

            ConfirmExit(); //呼叫ConfirmExit()函數

            return true;

        }

        return super.onKeyDown(keyCode, event);

    }



    public void ConfirmExit(){

        AlertDialog.Builder ad=new AlertDialog.Builder(BattleSetUp.this);

        ad.setTitle("離開");

        ad.setMessage("確定離開?");

        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                finish();
                startActivity(new Intent(getApplicationContext(),BarCoMonGameConsole.class));
            }
        });

        ad.setNegativeButton("否",new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int i) {

            }

        });

        ad.show();//顯示訊息視窗

    }




}
