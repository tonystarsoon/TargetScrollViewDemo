package it.com.ex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ScrollView;

import java.util.Arrays;
import java.util.List;

import it.com.ex.exView.ExpandableRecyclerAdapter;

/**
 * 垂直方向的实例
 */
public class ActivityVertical extends Activity {
    private ScrollView mScrollView;
    private RecyclerView mRecyclerView;
    private RecyclerAdapter mAdapter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_sample);
        List<ItemParent> itemParents = initData();
        mScrollView = (ScrollView) findViewById(R.id.scrollView);
        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        mAdapter = new RecyclerAdapter(this, itemParents);
        mAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @Override
            public void onParentExpanded(final int parentPosition) {
                Scroller scroller = new Scroller();
                View view = mAdapter.getParentItemView(parentPosition);
                scroller.scroll(mScrollView, view , mToolbar);
            }
            @Override
            public void onParentCollapsed(int parentPosition) {

            }
        });

        mRecyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        manager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(mAdapter);
    }



    @NonNull
    public static Intent newIntent(Context context) {
        return new Intent(context, ActivityVertical.class);
    }

    private List<ItemParent> initData() {
        //具体的子条目
        ItemChild beef = new ItemChild("路人甲", true);
        ItemChild cheese = new ItemChild("路人乙", true);
        ItemChild salsa = new ItemChild("路人丙", true);
        ItemChild tortilla = new ItemChild("路人丁", true);
        ItemChild ketchup = new ItemChild("路人戊", true);
        ItemChild bun = new ItemChild("路人己", true);
        ItemChild bun1 = new ItemChild("路人己", true);
        ItemChild bun2 = new ItemChild("路人己", true);
        ItemChild bun3 = new ItemChild("路人己", true);
        ItemChild bun4 = new ItemChild("路人己", true);
        ItemChild bun5 = new ItemChild("路人己", true);

        //最外层的3组
        ItemParent taco1 = new ItemParent("小学", Arrays.asList(beef, cheese, beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun, salsa, tortilla));
        ItemParent taco = new ItemParent("初中", Arrays.asList(beef, cheese, beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun, salsa, tortilla));
        ItemParent quesadilla = new ItemParent("高中", Arrays.asList(cheese, beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun, tortilla));
        ItemParent burger = new ItemParent("大学", Arrays.asList(beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun));
        ItemParent burger1 = new ItemParent("硕士", Arrays.asList(beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun));
        ItemParent burger2 = new ItemParent("博士", Arrays.asList(beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun));
        ItemParent burger3 = new ItemParent("博士后", Arrays.asList(beef, bun1, bun2, bun3, bun4, bun5, bun, cheese, ketchup, bun));

        //Adapter中接收到的
        List<ItemParent> itemParents = Arrays.asList(taco1, taco, quesadilla, burger);//, burger1, burger2, burger3
        return itemParents;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mAdapter.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mAdapter.onRestoreInstanceState(savedInstanceState);
    }
}
