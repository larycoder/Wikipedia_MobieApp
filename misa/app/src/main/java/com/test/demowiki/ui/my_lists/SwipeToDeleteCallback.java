package com.test.demowiki.ui.my_lists;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.test.demowiki.R;

abstract public class SwipeToDeleteCallback extends ItemTouchHelper.SimpleCallback {
    Context context;
    private Paint mClearPaint;
    private ColorDrawable mBackground;
    private int backgroundColor;
    private Drawable deleteDrawable;
    private int intrinsicwidth;
    private int intrinsicHeight;


    public SwipeToDeleteCallback(int dragDirs, int swipeDirs, Context context) {
        super(dragDirs, swipeDirs);
        this.context = context;
        mBackground = new ColorDrawable();
        backgroundColor = Color.parseColor("#b80f0a");
        mClearPaint = new Paint();
        mClearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        deleteDrawable = ContextCompat.getDrawable(context, R.drawable.ic_delete_white);
        intrinsicwidth = deleteDrawable.getIntrinsicWidth();
        intrinsicHeight = deleteDrawable.getIntrinsicHeight();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        View itemView=viewHolder.itemView;
        int itemHeight= itemView.getHeight();
        boolean isCancelled=dX==0 && !isCurrentlyActive;
        if(isCancelled){
            clearCanvas(c,itemView.getRight()+dX,(float)itemView.getTop(),(float)itemView.getRight(),(float)itemView.getBottom());
            super.onChildDraw(c,recyclerView,viewHolder,dX,dY,actionState,isCurrentlyActive);
            return;
        }
        mBackground.setColor(backgroundColor);
        mBackground.setBounds(itemView.getRight()+(int)dX,itemView.getTop(),itemView.getRight(),itemView.getBottom());
        mBackground.draw(c);
        int deleteIconTop=itemView.getTop()+(itemHeight-intrinsicHeight)/2;
        int deleteIconMargin=(itemHeight-intrinsicHeight)/2;
        int deleteIconLeft=itemView.getRight()-deleteIconMargin-intrinsicwidth;
        int deleteIconRight=itemView.getRight()-deleteIconMargin;
        int deleteIconBottom=deleteIconTop+intrinsicHeight;
        deleteDrawable.setBounds(deleteIconLeft,deleteIconTop,deleteIconRight,deleteIconBottom);
        deleteDrawable.draw(c);
        super.onChildDraw(c,recyclerView, viewHolder,dX,dY,actionState,isCurrentlyActive);
    }
    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom){
        c.drawRect(left, top, right, bottom,mClearPaint);
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}
