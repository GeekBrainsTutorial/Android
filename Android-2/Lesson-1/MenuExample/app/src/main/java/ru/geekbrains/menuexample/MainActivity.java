package ru.geekbrains.menuexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = ArrayAdapter.createFromResource(this,
                R.array.Cities,
                android.R.layout.simple_list_item_activated_1);
        setListAdapter(adapter);
        ListView listView = findViewById(android.R.id.list);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                editElement(info.position);
                return true;
            case R.id.menu_remove:
                deleteElement(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editElement(int position) {
        Toast.makeText(this, String.format("Редактирование элемента %d", position), Toast.LENGTH_SHORT).show();
    }

    private void deleteElement(int position) {
        Toast.makeText(this, String.format("Удаление элемента %d", position), Toast.LENGTH_SHORT).show();
    }


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

    private void clearList() {
        Toast.makeText(this, "Очистка списка", Toast.LENGTH_SHORT).show();
    }
}
