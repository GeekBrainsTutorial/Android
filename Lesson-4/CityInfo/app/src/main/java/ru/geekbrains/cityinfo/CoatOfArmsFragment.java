package ru.geekbrains.cityinfo;

import android.app.Fragment;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

// Фрагмент для вывода герба
public class CoatOfArmsFragment extends Fragment {

    // фабричный метод, создает фрагмент и передадет параметр
    public static CoatOfArmsFragment create(int index) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();    // создание

        // передача параметра
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    // получить индекс из списка (фактически из параметра)
    public int getIndex() {
        int index = getArguments().getInt("index", 0);
        return index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // определить какой герб надо показать, и показать его
        ImageView coatOfArms = new ImageView(getActivity());

        // получить из ресурсов массив указателей на изображения гербов
        TypedArray imgs = getResources().obtainTypedArray(R.array.coatofarms_imgs);
        // выбрать по индексу подходящий
        coatOfArms.setImageResource(imgs.getResourceId(getIndex(), -1));
        return coatOfArms; // Вместо макета используем сразу картинку
    }
}
