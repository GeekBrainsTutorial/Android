package ru.geekbrains.socnet;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

// адаптер
public class SocnetAdapter extends RecyclerView.Adapter<SocnetAdapter.ViewHolder> {
    private List<Soc> dataSource;                         // Наш источник данных
    private OnItemClickListener itemClickListener;  // Слушатель, будет устанавливаться извне

    // этот класс хранит связь между данными и элементами View
    // Сложные данные могут потребовать несколько View на
    // Один пункт списка.
    public class ViewHolder extends RecyclerView.ViewHolder {
        
        public TextView description;
        public ImageView picture;
        public CheckBox like;

        public ViewHolder(View v) {
            super(v);
            description = v.findViewById(R.id.description);
            picture = v.findViewById(R.id.picture);
            like = v.findViewById(R.id.like);

            // обработчик нажатий на этом ViewHolder
            picture.setOnClickListener(new View.OnClickListener() {
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
    public SocnetAdapter(List<Soc> dataSource) {
        this.dataSource = dataSource;
    }

    // Создать новый элемент пользовательского интерфейса
    // Запускается менеджером
    @Override
    public SocnetAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // Создаём новый элемент пользовательского интерфейса
        // Через Inflater
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);
        // Здесь можно установить всякие параметры
        ViewHolder vh = new ViewHolder(v);

        // на каком-то этапе будет переиспользование карточки, и в лог эта строка не попадет
        // а строка onBindViewHolder попадет, это будет означать, что старая карточка
        // переоткрыта с новыми данными
        Log.d("SocnetAdapter", "onCreateViewHolder");
        return vh;
    }

    // Заменить данные в пользовательском интерфейсе
    // Вызывается менеджером
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Получить элемент из источника данных (БД, интернет…)
        Soc item = dataSource.get(position);
        // Вынести на экран используя ViewHolder
        holder.description.setText(item.getDescription());
        holder.picture.setImageResource(item.getPicture());
        holder.like.setChecked(item.getLike());

        // отрабатывает при необходимости нарисовать карточку
        Log.d("SocnetAdapter", "onBindViewHolder");
    }

    // Вернуть размер данных, вызывается менеджером
    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
