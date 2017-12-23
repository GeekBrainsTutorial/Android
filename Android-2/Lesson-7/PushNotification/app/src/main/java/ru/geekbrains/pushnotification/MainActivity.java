package ru.geekbrains.pushnotification;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Активити пересоздается, когда мы тапаем по нотификации
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textTitle = findViewById(R.id.textTitle);
        TextView textBody = findViewById(R.id.textBody);

        // Заполняем данными из уведомления
        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            String title = intent.getStringExtra("title");
            String body = intent.getStringExtra("body");
            textTitle.setText(title);
            textBody.setText(body);
        }

    }
}
