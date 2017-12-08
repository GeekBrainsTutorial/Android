package ru.geekbrains.thread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.button);
        final TextView textView = findViewById(R.id.textView);
        final TextView textIndicator = findViewById(R.id.textIndicator);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String result = Integer.toString(calculate());
                textIndicator.setText("В главном потоке");
                textView.setText(result);
            }
        });
    }

    private int calculate() {

        double r = 1;
        for (int j = 0; j < 1000000000; j++) {
            r = j*0.01+r/0.01;
        }

        return 42;
    }
}
