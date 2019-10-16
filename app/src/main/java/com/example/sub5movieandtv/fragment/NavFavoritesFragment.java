package com.example.sub5movieandtv.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sub5movieandtv.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFavoritesFragment extends Fragment {
    Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    public NavFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nav_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = view.findViewById(R.id.view_pager_favorite);
        setupViewPager();
        tabLayout = view.findViewById(R.id.tab_layout_favorite);
        tabLayout.setupWithViewPager(viewPager);
        setTabIcon();
    }

    private void setTabIcon() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        Adapter(FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }

    private void setupViewPager() {
        String movie = getResources().getString(R.string.title_menu_movies);
        String tvshow = getResources().getString(R.string.title_menu_tvshow);
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new FavMoviesFragment(), movie);
        adapter.addFragment(new FavTvShowFragment(), tvshow);
        viewPager.setAdapter(adapter);
    }

    private int[] tabIcons = { //set icon to tab
            R.drawable.ic_movie_filter_black_24dp,
            R.drawable.ic_local_movies_black_24dp
    };
}
