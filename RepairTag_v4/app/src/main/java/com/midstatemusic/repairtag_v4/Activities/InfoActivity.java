package com.midstatemusic.repairtag_v4.Activities;

import android.app.Activity;
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

    private View focusedViewOnActionDown;
    private boolean touchWasInsideFocusedView;

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
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
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
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        assert inputMethodManager != null;
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //https://stackoverflow.com/questions/4165414/how-to-hide-soft-keyboard-on-android-after-clicking-outside-edittext
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                focusedViewOnActionDown = getCurrentFocus();
                if (focusedViewOnActionDown != null) {
                    final Rect rect = new Rect();
                    final int[] coordinates = new int[2];

                    focusedViewOnActionDown.getLocationOnScreen(coordinates);

                    rect.set(coordinates[0], coordinates[1],
                            coordinates[0] + focusedViewOnActionDown.getWidth(),
                            coordinates[1] + focusedViewOnActionDown.getHeight());

                    final int x = (int) ev.getX();
                    final int y = (int) ev.getY();

                    touchWasInsideFocusedView = rect.contains(x, y);
                }
                break;

            case MotionEvent.ACTION_UP:
                if (focusedViewOnActionDown != null) {
                    final boolean consumed = super.dispatchTouchEvent(ev);

                    final View currentFocus = getCurrentFocus();

                    assert currentFocus != null;
                    if (currentFocus.equals(focusedViewOnActionDown)) {
                        if (touchWasInsideFocusedView) {
                            return consumed;
                        }
                    } else if (currentFocus instanceof EditText) {
                        return consumed;
                    }
                     hideKeyboard(viewPager);
                    focusedViewOnActionDown.clearFocus();

                    return consumed;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }
}