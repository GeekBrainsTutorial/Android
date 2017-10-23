package ru.geekbrains.timer;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TimerFragment extends Fragment {

    private static final long DECSECONDS_IN_HOUR = 36000;
    private static final long DECSECONDS_IN_MUNUTE = 600;
    private static final long DECSECONDS_IN_SECOND = 10;
    private static final int DELAY = 100;
    private long decseconds = 0;                         // Счетчик
    private boolean running=false;
    private TextView textHour;
    private TextView textMinute;
    private TextView textSecond;
    private TextView textDecsecond;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timer, container, false);
        // Определение полей вывода
        textHour = (TextView)view.findViewById(R.id.textHour);
        textMinute = (TextView)view.findViewById(R.id.textMinute);
        textSecond = (TextView)view.findViewById(R.id.textSecond);
        textDecsecond = (TextView)view.findViewById(R.id.textDecsecond);
        // Обработка нажатий кнопок
        Button start = (Button) view.findViewById(R.id.buttonStart);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;     // Запустить таймер
            }
        });
        Button stop = (Button) view.findViewById(R.id.buttonStop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;    // Остановить таймер
            }
        });
        Button reset = (Button) view.findViewById(R.id.buttonReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;    // Сброс
                decseconds = 0;
            }
        });
        runTimer();
        return view;
    }

    private void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long hour = decseconds / DECSECONDS_IN_HOUR;
                long minute = (decseconds % DECSECONDS_IN_HOUR) / DECSECONDS_IN_MUNUTE;
                long second = (decseconds % DECSECONDS_IN_MUNUTE)  / DECSECONDS_IN_SECOND;
                long decsecond = decseconds % DECSECONDS_IN_SECOND;
                textHour.setText(String.format("%02d", hour));
                textMinute.setText(String.format("%02d", minute));
                textSecond.setText(String.format("%02d", second));
                textDecsecond.setText(String.format("%01d", decsecond));
                if (running) {
                    decseconds++;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }
}