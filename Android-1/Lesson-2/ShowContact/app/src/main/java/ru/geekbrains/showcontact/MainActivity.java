package ru.geekbrains.showcontact;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 7561;
    private EditText editText;  // Сюда пишем имя контакта

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        Button showContact = findViewById(R.id.button);
        showContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("content://contacts/people/");
                Intent intent = new Intent(Intent.ACTION_PICK, uri);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    // Обработка результата выбора контакта
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        // Если код не равен, значит это не та активити
        if (requestCode != REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        // Контакт выбран удачно
        if (resultCode == Activity.RESULT_OK){
            Uri contactUri = data.getData(); // Получим выбранные данные
            editText.setText(getNameOfContact(contactUri));
        }
    }

    // Получение контакта.
    // На первый взгляд тут происходит магия,
    // эту магию мы рассмотрим на следующем курсе.
    private String getNameOfContact(Uri contactUri){
        // Здесь идет выборка контакта.
        // Приемы используемые в этом коде мы будем разбирать на следующем курсе
        // Вкратце, здесь использует контент провайдер от списка контактов
        // Передавая номер контакта через contactUri получаем данные по этому контакту
        String[] projection = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME};
        Cursor cursor = getContentResolver()
                .query(contactUri, projection, null, null, null);
        cursor.moveToFirst();
        int column = cursor.getColumnIndex(
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
        return cursor.getString(column);
    }
}
