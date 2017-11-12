package ru.geekbrains.elements;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.elements2);

        ViewGroup elements2 = findViewById(R.id.layout);

       /*     <TextView
        *         android:id="@+id/textView2"
        *         android:textSize="42dp"
        *         android:textColor="@color/colorAccent"
        *         android:fontFamily="cursive"
        *         android:layout_width="match_parent"
        *         android:layout_height="wrap_content"
        *         android:text="TextView" />
        */
        TextView textViewManual = new TextView(this);

        // Получить цвет из ресурсов при помощи ContextCompat.getColor
        int colorAccent = ContextCompat.getColor(this, R.color.colorAccent);
        textViewManual.setTextColor(colorAccent); // установить цвет

        // Получить фонт курсив
        Typeface typeFace = Typeface.create("cursive", Typeface.NORMAL);
        textViewManual.setTypeface(typeFace); // установить фонт

        textViewManual.setText("TextView");
        textViewManual.setTextSize(42);
        elements2.addView(textViewManual);

        /*  <EditText
         *      android:id="@+id/editText2"
         *      android:textSize="10mm"
         *      android:layout_width="match_parent"
         *      android:layout_height="wrap_content"
         *      android:ems="10"
         *      android:inputType="number"
         *      android:digits="0123456789."
         *      android:text="42" />
         */
        EditText editTextManual = new EditText(this);
        editTextManual.setTextSize(TypedValue.COMPLEX_UNIT_MM, 10);
        editTextManual.setText("42");
        editTextManual.setInputType(InputType.TYPE_CLASS_NUMBER);
        elements2.addView(editTextManual);

        /*   <Button
        *       android:id="@+id/button51"
        *       android:textSize="42dp"
        *       android:layout_width="match_parent"
        *       android:layout_height="wrap_content"
        *       android:onClick="onClick"
        *       android:text="Button" />
        */
        Button buttonManual = new Button(this);
        buttonManual.setTextSize(42);
        buttonManual.setText("Button");
        elements2.addView(buttonManual);

        /*  <CheckBox
         *      android:textSize="50px"
         *      android:id="@+id/checkBox"
         *      android:checked="true"
         *      android:layout_width="match_parent"
         *      android:layout_height="wrap_content"
         *      android:text="CheckBox" />
         */
        CheckBox checkBoxManual = new CheckBox(this);
        checkBoxManual.setTextSize(TypedValue.COMPLEX_UNIT_PX, 50);
        checkBoxManual.setText("CheckBox");
        checkBoxManual.setChecked(true);
        elements2.addView(checkBoxManual);

        /*  <RadioGroup
         *      android:layout_width="match_parent"
         *      android:layout_height="wrap_content" >
         *      <RadioButton
         *          android:id="@+id/radioButton2"
         *          android:checked="true"
         *          android:layout_width="wrap_content"
         *          android:layout_height="wrap_content"
         *          android:layout_weight="1"
         *          android:text="RadioButton" />
         *      <RadioButton
         *          android:id="@+id/radioButton3"
         *          android:layout_width="wrap_content"
         *          android:layout_height="wrap_content"
         *          android:layout_weight="1"
         *          android:text="RadioButton" />
         *  </RadioGroup>
         */
        RadioGroup radioGroupManual = new RadioGroup(this);
        RadioButton radioButton1 = new RadioButton(this);
        radioButton1.setChecked(true);
        radioButton1.setText("RadioButton");
        RadioButton radioButton2 = new RadioButton(this);
        radioButton2.setText("RadioButton");
        radioGroupManual.addView(radioButton1);
        radioGroupManual.addView(radioButton2);
        elements2.addView(radioGroupManual);
        radioGroupManual.check(radioButton1.getId());

        /*  <ImageView
         *      android:id="@+id/imageView"
         *      android:layout_width="match_parent"
         *      android:layout_height="match_parent"
         *      app:srcCompat="@android:drawable/btn_star_big_on" />
         */
        ImageView imageViewManual = new ImageView(this);
        imageViewManual.setImageResource(android.R.drawable.btn_star_big_on);

        // Получить параметры элемента
        ViewGroup.LayoutParams params = elements2.getLayoutParams();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT; // задать свои параметры
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        imageViewManual.setLayoutParams(params); // установить параметры
        elements2.addView(imageViewManual);
    }
}
