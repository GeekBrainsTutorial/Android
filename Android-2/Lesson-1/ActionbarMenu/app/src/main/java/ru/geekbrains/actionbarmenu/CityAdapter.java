package ru.geekbrains.actionbarmenu;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<String> dataSource;                         // Наш источник данных
    private GetterMenuInflater getterMenuInflater;

    // этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // Один пункт списка.
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public TextView cityName;

        public ViewHolder(View v) {
            super(v);
            cityName = v.findViewById(R.id.city_name);
            v.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            MenuInflater inflater = getterMenuInflater.getContextMenuInflater();
            inflater.inflate(R.menu.context_menu, menu);
        }


    }

    // Надо получить
    public interface GetterMenuInflater{
        MenuInflater getContextMenuInflater();
    }

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    public CityAdapter(List<String> dataSource, GetterMenuInflater getterMenuInflater) {
        this.dataSource = dataSource;
        this.getterMenuInflater = getterMenuInflater;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        // Здесь можно установить всякие параметры
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CityAdapter.ViewHolder holder, int position) {
        // Получить элемент из источника данных (БД, интернет…)
        String item = dataSource.get(position);
        // Вынести на экран используя ViewHolder
        holder.cityName.setText(item);
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
