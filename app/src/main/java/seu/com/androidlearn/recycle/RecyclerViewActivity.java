package seu.com.androidlearn.recycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;
import seu.com.androidlearn.swipe.RecyclerActivity;

/**
 * Created by wuxiangyu on 2017/6/13.
 * 现在暂时问题：不同viewtype的item不能互相换位置：https://github.com/iPaulPro/Android-ItemTouchHelper-Demo/issues/7
 * 解决：https://stackoverflow.com/questions/43154047/android-recyclerview-drag-and-drop-over-multiple-viewtype
 * ：更换不同viewtype位置的item的时候，需要更换对应位置的数据：Collections.swap(adapter.getData(), i, i + 1);
 * 如果不同viewtype之间不想换更换位置，则不呼唤数据就可以啦。
 */

public class RecyclerViewActivity extends AppCompatActivity {
    @BindView(R.id.dragView)
    Button dragView;
    @BindView(R.id.btnGrid)
    Button btnGrid;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, RecyclerViewActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        ButterKnife.bind(this);
    }
    private List<ItemEntity> getTestData() {
        List<ItemEntity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ItemEntity header = new ItemEntity();
            header.setType(RecyAdapter.ITEM_HEADER);
            header.setName("Header: "+ i);
            list.add(header);
            for (int j = 0; j < 5; j++) {
                ItemEntity temp = new ItemEntity();
                temp.setType(RecyAdapter.ITEM_CONTENT);
                temp.setName("Content: "+ j+ " from Header: "+ i);
                list.add(temp);
            }
        }
        return list;
    }
    @OnClick(R.id.btnGrid)
    public void clickGrid(View view) {
        final RecyAdapter adapter = new RecyAdapter(this);
        adapter.setData(getTestData());
        recyclerView.setAdapter(adapter);
        final GridLayoutManager manager = new GridLayoutManager(this, 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getItem(position).getType() == RecyAdapter.ITEM_HEADER) {
                    return manager.getSpanCount();
                }
                return 1;
            }
        });
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
    @OnClick(R.id.dragView)
    public void clickLinear(View view) {

        final RecyAdapter adapter = new RecyAdapter(this);
        adapter.setData(getTestData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int dragFlags;
                int swipeFlags;
                if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
                    swipeFlags = 0;
                } else {
                    dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                    swipeFlags = 1;
                };
                Log.e("Tag", "dragFlags: " + dragFlags + ", swipeFlags: " + swipeFlags);
                return makeMovementFlags(dragFlags, swipeFlags);
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                Log.e("Tag", "onMove");
                int fromPosition = viewHolder.getAdapterPosition();//得到拖动ViewHolder的position
                int toPosition = target.getAdapterPosition();//得到目标ViewHolder的position
                if (fromPosition < toPosition) {
                    for (int i = fromPosition; i < toPosition; i++) {
                        Collections.swap(adapter.getData(), i, i + 1);
                    }
                } else {
                    for (int i = fromPosition; i > toPosition; i--) {
                        Collections.swap(adapter.getData(), i, i - 1);
                    }
                }
                adapter.notifyItemMoved(fromPosition, toPosition);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                Log.e("Tag", "onSwiped");
            }
        });
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }
}
