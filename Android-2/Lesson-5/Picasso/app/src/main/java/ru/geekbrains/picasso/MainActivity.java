package ru.geekbrains.picasso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView = findViewById(R.id.imageView);
        Picasso
                .with(this)
                .load("https://c1.staticflickr.com/1/186/31520440226_175445c41a_b.jpg")
                .into(imageView);
    }
}
