package com.example.fuelpass;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterViewPager extends FragmentStateAdapter {
    public AdapterViewPager(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new CustomerLoginFragment();
            case 1:
                return new CustomerRegistrationFragment();
            default:
                return new CustomerLoginFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
