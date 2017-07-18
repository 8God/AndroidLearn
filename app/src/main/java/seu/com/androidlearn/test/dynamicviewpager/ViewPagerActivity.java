package seu.com.androidlearn.test.dynamicviewpager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * FragmentStatePagerAdapter 和 FragmentPagerAdapter 的区别：
 * 后者会缓存所有的fragment，前者只缓存几个。
 * 使用FragmentStatePagerAdapter的问题是：每次都会重新创建fragment。
 * Created by wuxiangyu on 2017/6/22.
 */

public class ViewPagerActivity extends AppCompatActivity {
    @BindView(R.id.vpDynamic)
    ViewPager vpDynamic;
    @BindView(R.id.btnData1)
    Button btnData1;
    @BindView(R.id.btnData2)
    Button btnData2;
    CustomAdapter mAdapter;


    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ViewPagerActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        ButterKnife.bind(this);
        mAdapter = new CustomAdapter(getSupportFragmentManager());
        vpDynamic.setAdapter(mAdapter);
    }

    @OnClick(R.id.btnData1)
    public void clickData1(View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            list.add("Data1: " + i);
        }
        mAdapter.setData(list);
    }

    @OnClick(R.id.btnData2)
    public void clickData2(View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            list.add("Data2: " + i);
        }
        mAdapter.setData(list);
    }



}

class CustomAdapter extends MyFragmentPagerAdapter {
    private List<String> titles;

    public CustomAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container, position);
        String fragmentTag = fragment.getTag();
        if (!TextUtils.isEmpty(fragmentTag) && !TextUtils.isEmpty(getContentHash(position))) {
            if (fragmentTag.contains(getContentHash(position))) {
                //刷新
                if (fragment instanceof MyFragment) {
                    //其实这时候fragment还没有实例化。
                    ((MyFragment)fragment).refresh(titles.get(position));
                }
            }
        }
        Log.e("Tag", "instantiateItem： fragment: " + fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        //如果未调用，则会调用getItem，如果调用过了，就不会再调用了额。
        Log.e("Tag", "getItem(): " + position);
        if (titles == null || titles.isEmpty()) {
            return null;
        }
        Fragment fragment = MyFragment.getInstance(titles.get(position));
        return MyFragment.getInstance(titles.get(position));
    }

    @Override
    public int getCount() {
//            Log.e("Tag", "getCount() " + (titles == null ? 0 : titles.size()));
        return titles == null ? 0 : titles.size();
    }

    @Override
    public int getItemPosition(Object object) {
        //解决数据切换以后，页面显示的数据不更新的问题。需要配合FragmentStatePagerAdapter
        return POSITION_NONE;
    }

    @Override
    public String getContentHash(int position) {
        Log.e("Tag", "getContentHash: " + (titles == null ? "" : titles.get(position)));
        return titles == null ? "" : titles.get(position);
    }
}
/**
 * 存在的缺点：虽然数据源修改了，但是每次切换页面的时候，每次都会触发新建fragment
 *
 */
class MyAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;

    public MyAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment)super.instantiateItem(container, position);
        fragment.getTag();
        Log.e("Tag", "instantiateItem： fragment: " + fragment);
        return super.instantiateItem(container, position);
    }

    @Override
    public Fragment getItem(int position) {
        //如果未调用，则会调用getItem，如果调用过了，就不会再调用了额。
        Log.e("Tag", "getItem(): " + position);
        if (titles == null || titles.isEmpty()) {
            return null;
        }
        Fragment fragment = MyFragment.getInstance(titles.get(position));

        return MyFragment.getInstance(titles.get(position));
    }

    @Override
    public int getCount() {
//            Log.e("Tag", "getCount() " + (titles == null ? 0 : titles.size()));
        return titles == null ? 0 : titles.size();
    }

    @Override
    public int getItemPosition(Object object) {
        //解决数据切换以后，页面显示的数据不更新的问题。需要配合FragmentStatePagerAdapter
        return POSITION_NONE;
    }

}