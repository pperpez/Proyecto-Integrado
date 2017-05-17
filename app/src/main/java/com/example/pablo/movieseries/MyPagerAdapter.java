package com.example.pablo.movieseries;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class MyPagerAdapter extends FragmentPagerAdapter {

    private String[] titulos = new String[]{"PELÍCULAS", "SERIES"};

    public MyPagerAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f = null;

        switch (position){
            case 0:
                f = new MoviesFragment();
                break;
            case 1:
                f = new SeriesFragment();
                break;
        }

        return f;
    }

    @Override
    public int getCount() {
        return titulos.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titulos[position];
    }
}
