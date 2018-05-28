package com.nayana.bhoj.apps.custombottombar;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager viewpager;

    private int[] navIcons = {
            R.drawable.home_red,
            R.drawable.home_red,
            R.drawable.home_red,
            R.drawable.home_red
    };
    private int[] navLabels = {
            R.string.tab_title1,
            R.string.tab_title2,
            R.string.tab_title3,
            R.string.tab_title4
    };
    // another resouces array for active state for the icon
    private int[] navIconsActive = {
            R.drawable.home_blue,
            R.drawable.home_blue,
            R.drawable.home_blue,
            R.drawable.home_blue
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
        setupViewPager(viewpager);

        tabs.setupWithViewPager(viewpager);
        setupTabIcons();

        tabs.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewpager) {

                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);

                        // 1. get the custom View you've added
                        View tabView = tab.getCustomView();

                        // get inflated children Views the icon and the label by their id
                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        // change the label color, by getting the color resource value
                        tab_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                        // change the image Resource
                        // i defined all icons in an array ordered in order of tabs appearances
                        // call tab.getPosition() to get active tab index.
                        tab_icon.setImageResource(navIconsActive[tab.getPosition()]);
                    }

                    // do as the above the opposite way to reset tab when state is changed
                    // as it not the active one any more
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        View tabView = tab.getCustomView();
                        TextView tab_label = (TextView) tabView.findViewById(R.id.nav_label);
                        ImageView tab_icon = (ImageView) tabView.findViewById(R.id.nav_icon);

                        // back to the black color
                        tab_label.setTextColor(getResources().getColor(R.color.red));
                        // and the icon resouce to the old black image
                        // also via array that holds the icon resources in order
                        // and get the one of this tab's position
                        tab_icon.setImageResource(navIcons[tab.getPosition()]);
                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );
    }

    /**
     * Adding custom view to tab
     */
    private void setupTabIcons() {

        // loop through all navigation tabs
        for (int i = 0; i < tabs.getTabCount(); i++) {
            // inflate the Parent LinearLayout Container for the tab
            // from the layout nav_tab.xml file that we created 'R.layout.nav_tab
            LinearLayout tab = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.tab_dashboard, null);

            // get child TextView and ImageView from this layout for the icon and label
            TextView tab_label = (TextView) tab.findViewById(R.id.nav_label);
            ImageView tab_icon = (ImageView) tab.findViewById(R.id.nav_icon);

            // set the label text by getting the actual string value by its id
            // by getting the actual resource value `getResources().getString(string_id)`
            tab_label.setText(getResources().getString(navLabels[i]));

            // set the home to be active at first
            if (i == 0) {
                tab_label.setTextColor(getResources().getColor(R.color.colorPrimary));
                tab_icon.setImageResource(navIconsActive[i]);
            } else {
                tab_icon.setImageResource(navIcons[i]);
            }

            // finally publish this custom view to navigation tab
            tabs.getTabAt(i).setCustomView(tab);
        }
    }

    /**
     * Adding fragments to ViewPager
     *
     * @param viewPager
     */
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "ONE");
        adapter.addFrag(new OneFragment(), "TWO");
        adapter.addFrag(new OneFragment(), "THREE");
        adapter.addFrag(new OneFragment(), "FOUR");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}

