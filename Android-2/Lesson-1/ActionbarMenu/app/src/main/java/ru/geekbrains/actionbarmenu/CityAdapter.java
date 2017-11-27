package ru.geekbrains.actionbarmenu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<String> dataSource;                         // Наш источник данных
    private OnItemClickListener itemClickListener;  // Слушатель, будет устанавливаться извне

    // этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // Один пункт списка.
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView cityName;

        public ViewHolder(View v) {
            super(v);
            cityName = v.findViewById(R.id.city_name);

            // обработчик нажатий на этом ViewHolder
            cityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });
        }
    }

    // интерфейс для обработки нажатий как в ListView
    public interface OnItemClickListener {
        void onItemClick(View view , int position);
    }

    // сеттер слушателя нажатий
    public void SetOnItemClickListener(OnItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    // Передаем в конструктор источник данных
    // В нашем случае это массив, но может быть и запросом к БД
    public CityAdapter(List<String> dataSource) {
        this.dataSource = dataSource;
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
