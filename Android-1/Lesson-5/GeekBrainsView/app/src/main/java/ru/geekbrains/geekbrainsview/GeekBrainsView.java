package ru.geekbrains.geekbrainsview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

public class GeekBrainsView extends View {

    private Bitmap geekBrains;          // изображение кнопки
    private Bitmap geekBrains_press;    // изображение нажатой кнопки
    private Paint paint;
    private boolean pressed = false;    // признак нажатости
    View.OnClickListener listener;      // Слушатель

    public GeekBrainsView(Context context) {
        super(context);

        // Подготовка к отрисовыванию изображений
        geekBrains = BitmapFactory.decodeResource(getResources(),R.drawable.geekbrains);
        geekBrains_press = BitmapFactory.decodeResource(getResources(),R.drawable.geekbrains_press);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
    }

    // Отрисовка элемента
    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        canvas.drawRect(0,0, 208, 48, paint);
        if(pressed){
            canvas.drawBitmap(geekBrains_press, 0, 0, null);
        }
        else {
            canvas.drawBitmap(geekBrains, 0, 0, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int Action = event.getAction();
        if(Action == MotionEvent.ACTION_DOWN){ // нажали
            pressed = true;
            invalidate();           // Перерисовка элемента
            listener.onClick(this);
        }
        else if(Action == MotionEvent.ACTION_UP){ // отпустили
            pressed = false;
            invalidate();           // Перерисовка элемента
        }
        return true;
    }

    // установка слушателя
    @Override
    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }
}
