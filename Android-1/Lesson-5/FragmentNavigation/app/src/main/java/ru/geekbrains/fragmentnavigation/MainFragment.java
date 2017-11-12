package ru.geekbrains.fragmentnavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class MainFragment extends Fragment {

    private String shape;   // здесь будем хранить название изображения

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
        shape = getString(R.string.ball);
        final ImageView imageView = fragmentView.findViewById(R.id.imageView);
        RadioButton radioBall = fragmentView.findViewById(R.id.radioBall);
        radioBall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // показываем шар, если он выбран
                imageView.setImageResource(R.drawable.ball);
                shape = getString(R.string.ball);
            }
        });
        RadioButton radioStar = fragmentView.findViewById(R.id.radioStar);
        radioStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // показываем звезду, если она выбрана
                imageView.setImageResource(R.drawable.star);
                shape = getString(R.string.star);
            }
        });
        Button button = fragmentView.findViewById(R.id.button);
        final Activity that = getActivity();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // переход на второй фрагмент
                MainNavigator mainNavigator = (MainNavigator) that;
                mainNavigator.startSecondFragment(shape);
            }
        });
        return fragmentView;
    }

}
