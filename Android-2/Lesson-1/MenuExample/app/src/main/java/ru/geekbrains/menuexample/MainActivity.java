package ru.geekbrains.menuexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.AbsListView;
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
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                                                  long id, boolean checked) {
                // обработчик выделения пунктов списка ActionMode
                choiceElement(position);
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // обработка нажатия на пункт ActionMode
                // в данном случае просто закрываем меню
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        editElement();
                        break;
                    case R.id.menu_remove:
                        deleteElement();
                        break;
                }
                mode.finish();
                return false;
            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Устанавливаем для ActionMode меню
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.context_menu, menu);
                return true;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // вызывается при закрытии ActionMode
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // вызывается при обновлении ActionMode
                // true, если меню или ActionMode обновлено, иначе false
                return false;
            }
        });
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
    }

    private void choiceElement(int position) {
        Toast.makeText(this, String.format("Выбран элемент %d", position), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void editElement() {
        Toast.makeText(this, String.format("Редактирование элементов"), Toast.LENGTH_SHORT).show();
    }

    private void deleteElement() {
        Toast.makeText(this, String.format("Удаление элементов"), Toast.LENGTH_SHORT).show();
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
