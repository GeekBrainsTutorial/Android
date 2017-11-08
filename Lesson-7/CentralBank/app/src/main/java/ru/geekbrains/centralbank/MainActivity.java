package ru.geekbrains.centralbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText page = findViewById(R.id.editText);
        OkHttpRequesterService requester = new OkHttpRequesterService(new OkHttpRequesterService.OnCompletedRequest() {
            @Override
            public void onCompletedRequest(String string) {
                page.setText(string);
            }
        });
        requester.run();
    }
}
