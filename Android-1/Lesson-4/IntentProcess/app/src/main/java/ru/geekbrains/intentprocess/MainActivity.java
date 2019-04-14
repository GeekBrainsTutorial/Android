package ru.geekbrains.intentprocess;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static String TEXT = "PARAM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri == null) {
            return;
        }
        // получить строку из URI, как последний сегмент (то есть после последнего "/")
        String text = uri.getLastPathSegment();
        TextView textView = findViewById(R.id.textEcho);
        textView.setText(text); // Сохранить строку в TextView
    }
}
