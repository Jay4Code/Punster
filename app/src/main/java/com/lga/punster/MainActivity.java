package com.lga.punster;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lga.punster.image.ImageFragment;
import com.lga.punster.joke.JokeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mSections;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("kelly", "MainActivity onCreate");
        setContentView(R.layout.activity_main);

        preprocess();

        initView();
    }

    private void preprocess() {

    }

    private void initView() {
        mSections = (RadioGroup) findViewById(R.id.rg_sections);
        mSections.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                mViewPager.setCurrentItem(group.indexOfChild(group.findViewById(checkedId)));
            }
        });

        List<Fragment> fragmentList = new ArrayList<>();
//        fragmentList.add(RecoFragment.newInstance(0));
//        fragmentList.add(VideoFragment.newInstance(1));
//        fragmentList.add(ShowFragment.newInstance(2));
        fragmentList.add(ImageFragment.newInstance(3));
        fragmentList.add(JokeFragment.newInstance(4));
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragmentList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {}

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == ViewPager.SCROLL_STATE_SETTLING) {
                    ((RadioButton) mSections.getChildAt(mViewPager.getCurrentItem())).setChecked(true);
                }
            }
        });
    }
}
