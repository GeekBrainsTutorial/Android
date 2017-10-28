package ru.geekbrains.geekbrainsview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        GeekBrainsView geekBrainsView = new GeekBrainsView(this);
        layout.addView(geekBrainsView);
        setContentView(layout);
        geekBrainsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Pressed Geek Brain button.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
