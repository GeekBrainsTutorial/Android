package ru.geekbrains.actionbarmenu;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecyclerView();
        List<String> dataSource = buildDataSource();
        initCityAdapter(dataSource);
    }

    // Меню Action bar - установка
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Меню Action bar - выбор пункта
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add:
                addElement();
                return true;
            case R.id.menu_clear:
                clearList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void addElement() {
        Toast.makeText(this, "Добавление элемента", Toast.LENGTH_SHORT).show();
    }

    private void clearList(){
        Toast.makeText(this, "Очистка списка", Toast.LENGTH_SHORT).show();
    }

    // Инициализация RecyclerView
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycler_view);
        // установим аниматор по умолчанию
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        // эта установка служит для повышения производительности системы.
        recyclerView.setHasFixedSize(true);
        // будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    // Построение данных
    private List<String> buildDataSource(){
        // строим источник данных
        DataSourceBuilder builder = new DataSourceBuilder(getResources());
        return builder.build();
    }

    // игнициализация адаптера
    private void initCityAdapter(List<String> dataSource){
        CityAdapter adapter = new CityAdapter(dataSource);
        recyclerView.setAdapter(adapter);
        final Context that = this;
        // установить слушателя
        adapter.SetOnItemClickListener(new CityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(that, String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
