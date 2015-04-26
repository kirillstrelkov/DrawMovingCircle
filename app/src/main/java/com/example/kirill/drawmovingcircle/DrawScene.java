package com.example.kirill.drawmovingcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.View;

public class DrawScene extends View {
    private final int FRAME_RATE = 30;
    private int x;
    private int y;
    private int dx = 5;
    private int dy = 5;
    private int circleRadius = 50;
    private int width;
    private int height;

    private Paint canvasPaint;
    private Paint circlePaint;

    private Handler h;
    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    public DrawScene(Context context, int startX, int startY) {
        super(context);
        x = startX;
        y = startY;

        canvasPaint = new Paint();
        canvasPaint.setStyle(Paint.Style.FILL);
        canvasPaint.setColor(Color.YELLOW);

        circlePaint = new Paint();
        circlePaint.setColor(Color.GREEN);

        h = new Handler();
    }

    public int getCurrentX() {
        return x;
    }

    public int getCurrentY() {
        return y;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawPaint(canvasPaint);

        if (width == 0) {
            width = this.getWidth();
        }
        if (height == 0) {
            height = this.getHeight();
        }

        canvas.drawCircle(x, y, circleRadius, circlePaint);

        x += dx;
        y += dy;

        if ((x > width - circleRadius) || (x < circleRadius)) {
            dx = dx * -1;
        }

        if ((y > height - circleRadius) || (y < circleRadius)) {
            dy = dy * -1;
        }

        h.postDelayed(r, FRAME_RATE);
    }
}
