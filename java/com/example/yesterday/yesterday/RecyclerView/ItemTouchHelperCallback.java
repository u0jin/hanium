package com.example.yesterday.yesterday.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final RecyclerViewAdapter mAdapter;

    public ItemTouchHelperCallback(RecyclerViewAdapter adapter) {
        mAdapter = adapter;
    }

    //RecyclerView에서 드래그 된지 알기 위해서 오버라이드
    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    //RecyclerView에서 스와이프 된지 알기 위해 오버라이드
    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    //현재 어떤 동작을 취했는 지 알려주는 메소드
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //드래그 = 위,아래
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        //스와이프 = 좌,우
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    //Item을 Long Touch하여 다른 위치로 움직였을 때
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        Log.d("TAG","ItemTouchHelper : onMove");
        return true;
    }

    //스와이프 되었을 때
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDelete(viewHolder.getAdapterPosition());
    }

}