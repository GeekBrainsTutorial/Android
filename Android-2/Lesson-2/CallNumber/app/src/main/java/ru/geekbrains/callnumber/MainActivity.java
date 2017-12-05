package ru.geekbrains.callnumber;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText phoneNumber = findViewById(R.id.phoneNumber);
        Button callPhone = findViewById(R.id.callPhone);
        callPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + phoneNumber.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
                startActivity(intent);
            }
        });
    }
}
