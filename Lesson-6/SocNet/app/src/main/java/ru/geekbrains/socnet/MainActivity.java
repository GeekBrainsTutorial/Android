
package ru.geekbrains.socnet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // установим аниматор по умолчанию
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);

        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // строим источник данных
        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        final List<Soc> dataSource = builder.build();
        // установим адаптер
        final SocnetAdapter adapter = new SocnetAdapter(dataSource);
        recyclerView.setAdapter(adapter);

        final Activity that = this;
        // установить слушателя
        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(that, String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });

        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Добавим элемент в 0-ю позицию
                dataSource.add(0, new Soc("Еще одна осень", R.drawable.nature7, true));
                // Дадим инструкцию адаптеру, что данные изменились
                adapter.notifyDataSetChanged();
            }
        });

    }
}
