package com.jnu.student.GameView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class View extends SurfaceView implements SurfaceHolder.Callback {

    private float touchedX;
    private float touchedY;
    private boolean isTouched=false;

    public View(Context context) {
        super(context);
        initView();
    }

    public View(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public View(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    public View(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(MotionEvent.ACTION_UP==event.getAction())
        {
            touchedX = event.getRawX();
            touchedY = event.getRawY();
            Log.i("test_touched",""+touchedX);
            isTouched = true;
        }
        Log.i("test","move action");
        return true;
    }

    private void initView()
    {
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
    }

    private SurfaceHolder surfaceHolder;
    private DrawThread drawThread=null;

    private ArrayList<Spriter> spriterArrayList=new ArrayList<>();
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        for(int i=0;i<5;++i)
        {
            Spriter spriter=new Spriter(this.getContext());
            spriter.setX(i*50);
            spriter.setY(i*50);
            spriter.setDirection((float) (Math.random()*2*Math.PI));
            spriterArrayList.add(spriter);
        }

        drawThread=new DrawThread();
        drawThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
        drawThread.stopThread();
    }


    class DrawThread extends Thread {
        private boolean isDrawing=true;

        public void stopThread()
        {
            isDrawing=false;

            try {
                join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void run() {
            int hitCount=0;
            while(isDrawing)
            {
                Canvas canvas =null;
                try {
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawRGB(135,206,235);

                    if(isTouched) {
                        float tempX = touchedX;
                        float tempY = touchedY;
                        isTouched = false;
                        for (Spriter spriter : spriterArrayList) {
                            if(spriter.isTouched(tempX, tempY))hitCount++;
                        }
                    }
                    Paint textPaint = new Paint();
                    textPaint.setColor(Color.BLACK);
                    textPaint.setTextSize(100);
                    canvas.drawText("目前得分 "+hitCount,0,100,textPaint);

                    for (Spriter spriter: spriterArrayList) {
                        spriter.move(canvas.getHeight(), canvas.getWidth());
                    }
                    for (Spriter spriter: spriterArrayList) {
                        spriter.draw(canvas);
                    }
                }
                catch(Exception e)
                {

                }
                finally {
                    if(null!=canvas)surfaceHolder.unlockCanvasAndPost(canvas);
                }


                try {
                    sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //drawing
            }
        }
    }
}
