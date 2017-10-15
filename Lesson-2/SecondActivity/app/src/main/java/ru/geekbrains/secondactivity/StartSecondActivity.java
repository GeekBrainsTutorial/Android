package ru.geekbrains.secondactivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

public class StartSecondActivity implements View.OnClickListener {

    private Activity sourceActivity;

    public StartSecondActivity(Activity sourceActivity){
        this.sourceActivity = sourceActivity;
    }
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(sourceActivity, SecondActivity.class);
        sourceActivity.startActivity(intent);
    }
}
