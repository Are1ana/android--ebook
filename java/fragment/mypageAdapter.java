package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class mypageAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment>  lists;
    public mypageAdapter(@NonNull FragmentManager fm,ArrayList<Fragment> list) {
        super(fm);
        this.lists=list;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return lists.get(position);
    }

    @Override
    public int getCount() {
        return lists.size();
    }
}
