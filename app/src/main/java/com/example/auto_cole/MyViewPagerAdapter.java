package com.example.auto_cole;



import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.auto_cole.fragments.DangerFragment;
import com.example.auto_cole.fragments.IndicationFragment;
import com.example.auto_cole.fragments.InterdictionFragment;
import com.example.auto_cole.fragments.ObligationFragement;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new InterdictionFragment();
            case 1:
                return new ObligationFragement();
            case 2:
                return new DangerFragment();
            case 3:
                return new IndicationFragment();
            default:
                return new InterdictionFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
