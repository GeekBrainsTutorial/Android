
package ru.geekbrains.socnet;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String[] data = {"One", "Two", "Three", "Four"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);

        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // строим источник данных
        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        // установим адаптер
        SocnetAdapter adapter = new SocnetAdapter(builder.build());
        recyclerView.setAdapter(adapter);

        final Activity that = this;
        // установить слушателя
        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(that, String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
