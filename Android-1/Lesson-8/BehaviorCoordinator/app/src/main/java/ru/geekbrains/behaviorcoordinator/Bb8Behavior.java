package ru.geekbrains.behaviorcoordinator;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

// Поведение изображения
public class Bb8Behavior extends CoordinatorLayout.Behavior<ImageView> {

    private float initialX = 0;         // начальное значение X изображения, от нее и будем отталкиваться
    private boolean firstMove = true;   // Нам надо получить начальное значение X только один раз

    // конструктор должен быть именно такой
    public Bb8Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // этот метод вызывается при изменении каждого элемента материального дизайна,
    // а мы будем отрабатывать только если меняется AppBarLayout.
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, ImageView child, View dependency) {
        return dependency instanceof AppBarLayout;
    }

    // Как только AppBarLayout изменился, сразу же меняется и наш элемент
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, ImageView child, View dependency) {

        // инициализация начального значения X
        if (firstMove){
            firstMove = false;
            initialX = child.getX();
        }

        // изображение будет ездить по низу экрана
        child.setX(initialX - dependency.getY()*1.5f );
        return false;
    }
}
