package com.example.android11.mvc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class DotView extends View {
    private final Dots dots;
    private final Paint paint = new Paint();

    public DotView(Context context, Dots dots) {
        super(context);
        this.dots = dots;
        setMinimumWidth(180);
        setMinimumHeight(200);
        setFocusable(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                getSuggestedMinimumWidth(),
                getSuggestedMinimumHeight()
        );
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(hasFocus() ? Color.BLUE : Color.GRAY);
        canvas.drawRect(0,0, getWidth() - 1, getHeight() -1, paint);

        paint.setStyle(Paint.Style.FILL);
        for(Dot dot : dots.getDots()) {
            paint.setColor(dot.getColor());
            canvas.drawCircle(
                    dot.getX(),
                    dot.getY(),
                    dot.getDiameter(),
                    paint
            );
        }
    }
}
