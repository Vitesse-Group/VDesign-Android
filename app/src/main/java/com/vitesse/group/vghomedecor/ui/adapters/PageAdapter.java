package com.vitesse.group.vghomedecor.ui.adapters;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.vitesse.group.vghomedecor.app.VGDecorHomeApplication;
import com.vitesse.group.vghomedecor.ui.fragments.KitchenAreaFragment;
import com.vitesse.group.vghomedecor.ui.fragments.LivingAreaFragment;
import com.vitesse.group.vghomedecor.ui.fragments.BedroomAreaFragment;
import com.vitesse.group.vghomedecor.utils.Utility;

/**
 * Created by abdalla on 2/18/18.
 */

public class PageAdapter extends FragmentPagerAdapter {

    private int numOfTabs;

    public PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(Utility.TAB_LIVING_AREA_INDEX);
                return  LivingAreaFragment.newInstance();
            case 1:
               // return new BedroomAreaFragment();
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(Utility.TAB_BEDROOM_AREA_INDEX);
                return  BedroomAreaFragment.newInstance();
            case 2:
                //return new KitchenAreaFragment();
                VGDecorHomeApplication.getInstance().setSelectedTabPosition(Utility.TAB_KITCHEN_AREA_INDEX);
                return  KitchenAreaFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
