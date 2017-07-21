package seu.com.modulea.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.modulea.R;
import seu.com.modulea.R2;


/**
 * Created by wuxiangyu on 2017/7/21.
 */
@Route(path = "/modulea/GankActivity")
public class GankActivity extends AppCompatActivity {
    @BindView(R2.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R2.id.viewPager)
    ViewPager mViewPager;
    GankAdapter mGankAdapter;
    private String[] mTabTitle = new String[] {"all", "android"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulea_activity_gank);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        mGankAdapter = new GankAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mGankAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private class GankAdapter extends FragmentPagerAdapter {
        public GankAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            GankFragment fragment = GankFragment.getInstance();
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitle[position];
        }
    }
}
