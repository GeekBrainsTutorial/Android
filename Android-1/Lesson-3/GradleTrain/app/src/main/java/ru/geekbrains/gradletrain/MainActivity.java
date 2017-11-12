package ru.geekbrains.gradletrain;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;

public class MainActivity extends LifecycleActivity { // LifecycleActivity приехало из внешней библиотеки

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
