package com.example.barcomon;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**********************************/


/************************************/

public class ProductPage extends FragmentActivity implements ViewPager.OnPageChangeListener,View.OnClickListener {

    FloatingActionButton fab_plus,fab_comment,fab_price;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen =false;

    private ViewPager myViewPager;
    private View page1,page2,page3;
    private List<View> pageList;
    private MyPagerAdapter myPagerAdapter;
    private TextView tv_tab0,tv_tab1,tv_tab2;
    private ImageView line_tab;
    private int moveOne = 0;
    private boolean isScrolling=false;
    private boolean isBackScrolling=false;
    private long startTime=0;
    private long currentTime=0;

    @Override
    protected  void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.product_page);



        fab_plus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fab_comment =(FloatingActionButton)findViewById(R.id.fab_comment);
        fab_price =(FloatingActionButton)findViewById(R.id.fab_price);

        fab_plus.bringToFront();
        fab_comment.bringToFront();
        fab_price.bringToFront();

        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose =AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);

        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isOpen){
                    fab_price.startAnimation(FabClose);
                    fab_comment.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRanticlockwise);
                    fab_comment.setClickable(false);
                    fab_price.setClickable(false);
                    isOpen = false;
                }
                else{
                    fab_price.startAnimation(FabOpen);
                    fab_comment.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRClockwise);
                    fab_comment.setClickable(true);
                    fab_price.setClickable(true);
                    isOpen = true;
                }
            }
        });


        initView();
        initLineImage();
        fab_plus.bringToFront();
        fab_comment.bringToFront();
        fab_price.bringToFront();

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
        myViewPager = (ViewPager)findViewById(R.id.myViewPager);

        PricePlacePage pricePlacePage=new PricePlacePage();
        CommentStartPage commentStartPage=new CommentStartPage();
        //MyFragment1 myFragment1=new MyFragment1();
        //MyFragment2 myFragment2=new MyFragment2();
        //MyFragment3 myFragment3=new MyFragment3();

        List<Fragment>fragmentList=new ArrayList<Fragment>();

        fragmentList.add(pricePlacePage);
        fragmentList.add(commentStartPage);
        //fragmentList.add(myFragment1);
        //fragmentList.add(myFragment2);
        //fragmentList.add(myFragment3);

        MyFragmentAdapter myFragmentAdapter=new MyFragmentAdapter(getSupportFragmentManager(),fragmentList);
        myViewPager.setAdapter(myFragmentAdapter);

        tv_tab0=(TextView)findViewById(R.id.tv_tab0);
        tv_tab1=(TextView)findViewById(R.id.tv_tab1);
        //tv_tab2=(TextView)findViewById(R.id.tv_tab2);
        myViewPager.setCurrentItem(0);
        tv_tab0.setTextColor(Color.RED);
        tv_tab1.setTextColor(Color.BLACK);
        //tv_tab2.setTextColor(Color.BLACK);

        tv_tab0.setOnClickListener(this);
        tv_tab1.setOnClickListener(this);
        //tv_tab2.setOnClickListener(this);
        myViewPager.setOnPageChangeListener(this);
        line_tab=(ImageView)findViewById(R.id.line_tab);
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
                tv_tab0.setTextColor(Color.RED);
                tv_tab1.setTextColor(Color.BLACK);
                //tv_tab2.setTextColor(Color.BLACK);
                movePositionX(0);
                break;
            case 1:
                tv_tab0.setTextColor(Color.BLACK);
                tv_tab1.setTextColor(Color.BLUE);
                //tv_tab2.setTextColor(Color.BLACK);
                break;
            case 2:
                tv_tab0.setTextColor(Color.BLACK);
                tv_tab1.setTextColor(Color.BLACK);
                //tv_tab2.setTextColor(Color.GREEN);
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
        if(v.getId()==R.id.tv_tab0) {
            myViewPager.setCurrentItem(0);
        } else if(v.getId()==R.id.tv_tab1) {
            myViewPager.setCurrentItem(1);
        }
        /*switch (v.getId()){

            case R.id.tv_tab0:
                myViewPager.setCurrentItem(0);
                break;
            case R.id.tv_tab1:
                myViewPager.setCurrentItem(1);
                break;
            //case R.id.tv_tab2:
            //    myViewPager.setCurrentItem(2);
            //    break;
            default:
                break;


        }*/


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




}