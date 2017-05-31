package id.co.imastudio.latihanmultimedia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    PrefManager prefManager;
    ViewPager viewPager;
    Button btnSkip,btnNext;
    LinearLayout layoutDots;

    MyViewPagerAdapter myViewPageadapter;

    int[] layouts={R.layout.welcome_slide_1,
                   R.layout.welcome_slide_2,
                   R.layout.welcome_slide_3,
                   R.layout.welcome_slide_4};
    private int currentpage;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefManager=new PrefManager(this);

        //ini untuk hanya dieksekusi sekali
//        if (!prefManager.isFirstTimeLaunch()){
//            launchHomeScreen();
//            finish();
//        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        setContentView(R.layout.activity_welcome);

        viewPager=(ViewPager) findViewById(R.id.viewPager);
        btnSkip=(Button) findViewById(R.id.btnSkip);
        btnNext=(Button) findViewById(R.id.btnNext);
        layoutDots=(LinearLayout) findViewById(R.id.layoutDots);

        myViewPageadapter=new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPageadapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addButtomDOts(position);
//                currentpage=currentpage+1;
//                viewPager.setCurrentItem(currentpage);
                currentpage=position;

                //cek halaman terakhir
                if(currentpage==layouts.length-1){
                    btnSkip.setVisibility(View.INVISIBLE);
                    btnNext.setText(getString(R.string.start));
                } else{
                    btnSkip.setVisibility(View.VISIBLE);
                    btnNext.setText(getString(R.string.next));
                }



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        changeStatusBarColor();

        addButtomDOts(0);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchHomeScreen();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentpage=currentpage+1;
                if (currentpage<layouts.length){
                    viewPager.setCurrentItem(currentpage);
                } else{
                    launchHomeScreen();
                }

            }
        });
    }

    private void addButtomDOts(int posisi) {
        dots=new TextView[layouts.length];

        int[] colorActive=getResources().getIntArray(R.array.array_dot_active);
        int[] colorInActive=getResources().getIntArray(R.array.array_dot_inactive);

        layoutDots.removeAllViews();
        for(int i=0;i<dots.length;i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorInActive[posisi]);
            layoutDots.addView(dots[i]);
        }

        if (dots.length>0){
            dots[posisi].setTextColor(colorActive[posisi]);
        }
    }
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    private void launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false);
        startActivity(new Intent(WelcomeActivity.this, Radio2Activity.class));
        finish();
    }

    private class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //generate override methode

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater;
            inflater=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View itemview=inflater.inflate(layouts[position],container,false);

            container.addView(itemview);
            return itemview;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }
    }
}
