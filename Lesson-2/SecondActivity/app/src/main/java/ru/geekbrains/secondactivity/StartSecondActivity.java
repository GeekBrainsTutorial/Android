package ru.geekbrains.secondactivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

public class StartSecondActivity implements View.OnClickListener {

    public final static String TEXT = "Text";
    private Activity sourceActivity;

    public StartSecondActivity(Activity sourceActivity){
        this.sourceActivity = sourceActivity;
    }
    @Override
    public void onClick(View v) {
        EditText txt = (EditText) sourceActivity.findViewById(R.id.editText);
        Intent intent = new Intent(sourceActivity, SecondActivity.class);
        intent.putExtra(TEXT, txt.getText().toString());    // Получить значение из EditText
        sourceActivity.startActivity(intent);
    }
}
