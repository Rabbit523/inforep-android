package infopanel.theappchief.com.infopanel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import infopanel.theappchief.com.infopanel.fragment.BedroomFragment;
import infopanel.theappchief.com.infopanel.fragment.CameraPanelFragment;
import infopanel.theappchief.com.infopanel.fragment.DoorFragment;
import infopanel.theappchief.com.infopanel.fragment.GarageFragment;
import infopanel.theappchief.com.infopanel.fragment.NetworkFragment;
import infopanel.theappchief.com.infopanel.fragment.SystemFragment;
import infopanel.theappchief.com.infopanel.fragment.TemperatureFragment;
import infopanel.theappchief.com.infopanel.fragment.VideoFragment;

public class HomePagerAdapter extends FragmentStatePagerAdapter {

    Fragment[] fragments = new Fragment[10];

    public HomePagerAdapter(FragmentManager fm) {
        super(fm);

        fragments[0] = new SystemFragment();
        fragments[1] = new SystemFragment();
        fragments[2] = new BedroomFragment();
        fragments[3] = new DoorFragment();
        fragments[4] = new TemperatureFragment();
        fragments[5] = new GarageFragment();
        fragments[6] = new CameraPanelFragment();
        fragments[7] = new VideoFragment();
        fragments[8] = new NetworkFragment();
        fragments[9] = new NetworkFragment();
    }


    public Fragment getItem(int arg0) {
        return fragments[arg0];
    }

    public int getCount() {
        return fragments.length;
    }

}

