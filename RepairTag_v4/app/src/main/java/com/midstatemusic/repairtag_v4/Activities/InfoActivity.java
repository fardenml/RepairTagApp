package com.midstatemusic.repairtag_v4.Activities;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.midstatemusic.repairtag_v4.Fragments.CustomerFragment;
import com.midstatemusic.repairtag_v4.Fragments.DescriptionFragment;
import com.midstatemusic.repairtag_v4.Fragments.InstrumentFragment;
import com.midstatemusic.repairtag_v4.Fragments.SchoolFragment;
import com.midstatemusic.repairtag_v4.Helpers.BottomNavigationViewHelper;
import com.midstatemusic.repairtag_v4.Helpers.ViewPagerAdapter;
import com.midstatemusic.repairtag_v4.R;

public class InfoActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;

    CustomerFragment customerFragment;
    SchoolFragment schoolFragment;
    InstrumentFragment instrumentFragment;
    DescriptionFragment descriptionFragment;
    MenuItem prevMenuItem;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Initializing viewPager
        viewPager = findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = findViewById(R.id.navigation);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_customer:
                                viewPager.setCurrentItem(0);
                                return true;
                            case R.id.navigation_school:
                                viewPager.setCurrentItem(1);
                                return true;
                            case R.id.navigation_instrument:
                                viewPager.setCurrentItem(2);
                                return true;
                            case R.id.navigation_description:
                                viewPager.setCurrentItem(3);
                                return true;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: "+position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
                hideKeyboard(viewPager);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        customerFragment = new CustomerFragment();
        schoolFragment = new SchoolFragment();
        instrumentFragment = new InstrumentFragment();
        descriptionFragment = new DescriptionFragment();
        adapter.addFragment(customerFragment);
        adapter.addFragment(schoolFragment);
        adapter.addFragment(instrumentFragment);
        adapter.addFragment(descriptionFragment);
        viewPager.setAdapter(adapter);
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    hideKeyboard(v);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}