package ru.geekbrains.cityheraldry.fragments;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import ru.geekbrains.cityheraldry.CoatOfArmsActivity;
import ru.geekbrains.cityheraldry.Parcel;
import ru.geekbrains.cityheraldry.R;

import static ru.geekbrains.cityheraldry.fragments.CoatOfArmsFragment.PARCEL;

// Фрагмент выбора города из списка
public class CitiesFragment extends Fragment {
    boolean isExistCoatOfArms;  // Можно ли расположить рядом фрагмент с гербом

    //+ Меняем текущую позицию на объект Parcel
    Parcel currentParcel;       // Текущая посылка (номер города и название)

    // При создании фрагмента укажем его макет
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом герб в другом фрагменте
        isExistCoatOfArms = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        // Если это не первое создание, то восстановим текущую позицию
        //+ Здесь также востанавливаем Parcel
        if (savedInstanceState != null) {
            // Восстановление текущей позиции.
            currentParcel = (Parcel) savedInstanceState.getSerializable("CurrentCity");
        }
        else {
            //+ Если востановить не удалось, то сделаем объект с первым индексом
            currentParcel = new Parcel(0, getResources().getStringArray(R.array.cities)[0]);
        }

        // Если можно нарисовать рядом герб, то сделаем это
        if (isExistCoatOfArms) {
            showCoatOfArms(currentParcel);
        }
    }

    // Сохраним текущую позицию (вызывается перед выходом из фрагмента)
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //+ Также меняем текущую позицию на Parcel
        outState.putSerializable("CurrentCity", currentParcel);
    }

    // создаем список городов на экране из массива в ресурсах
    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout)view;
        String[] cities = getResources().getStringArray(R.array.cities);

        // В этом цикле создаем элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаем обработку касания на элемент
        for(int i=0; i < cities.length; i++){
            String city = cities[i];
            TextView tv = new TextView(getContext());
            tv.setText(city);
            tv.setTextSize(30);
            layoutView.addView(tv);
            final int fi = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //+ Теперь опираемся на Parcel, а не на текущую позицию
                    currentParcel = new Parcel(fi, getResources().getStringArray(R.array.cities)[fi]);
                    showCoatOfArms(currentParcel);
                }
            });
        }
    }

    // Показать герб. Ecли возможно, то показать рядом со списком,
    // если нет, то открыть вторую activity
    private void showCoatOfArms(Parcel parcel) {
        if (isExistCoatOfArms) {
            // Проверим, что фрагмент с гербом существует в activity
            CoatOfArmsFragment detail = (CoatOfArmsFragment)
                    getFragmentManager().findFragmentById(R.id.coat_of_arms);
            // Если есть необходимость, то выведем герб
            //+ Здесь также применяем Parcel
            if (detail == null || detail.getParcel().getImageIndex() != parcel.getImageIndex()) {
                // Создаем новый фрагмент с текущей позицией для вывода герба
                detail = CoatOfArmsFragment.create(parcel);

                // Выполняем транзакцию по замене фрагмента
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.coat_of_arms, detail);  // замена фрагмента
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }
        } else {
            // Если нельзя вывести герб рядом, откроем вторую activity
            Intent intent = new Intent();
            intent.setClass(getActivity(), CoatOfArmsActivity.class);
            //+ и передадим туда Parcel
            intent.putExtra(PARCEL, parcel);
            startActivity(intent);
        }
    }
}
