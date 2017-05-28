package it.com.ex;

import android.view.View;
import android.widget.ScrollView;

/**
 * Created by tony on 2017/5/28.
 */
public class Scroller {
    public Scroller() {

    }

    /**
     * 通过滚动ScrollView来讲条目targetView滚动的offsetView的下边缘
     * @param scrollView 滚动的ScrollView
     * @param targetView 需要置顶的条目view
     * @param offsetView targetView会被滚动到offsetView的下边缘
     */
    public void scroll(final ScrollView scrollView, final View targetView, final View offsetView) {
        scrollView.postDelayed(new Runnable() {
            @Override
            public void run() {
                int[] location = new int[2];
                targetView.getLocationOnScreen(location);
                int viewPosition = location[1];
                int[] toolBarPoistion = new int[2];
                offsetView.getLocationOnScreen(toolBarPoistion);
                int locationOffsetY = toolBarPoistion[1] + offsetView.getMeasuredHeight();
                int purposeLocation = viewPosition - locationOffsetY;
                scrollView.smoothScrollBy(0, purposeLocation);
            }
        },50);
    }
}
