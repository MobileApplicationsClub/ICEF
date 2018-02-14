package com.macbitsgoa.icef_2018;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class SepaeratorDecoration extends RecyclerView.ItemDecoration {

    int selectedItem;

    public SepaeratorDecoration(Context context, int selectedItem) {
        this.selectedItem = selectedItem;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        View child = parent.getLayoutManager().findViewByPosition(selectedItem);

        if (view == child) {
            outRect.set(8, 0, 8, 80);

        } else {

            outRect.set(8, 80, 8, 0);
        }


    }

}


