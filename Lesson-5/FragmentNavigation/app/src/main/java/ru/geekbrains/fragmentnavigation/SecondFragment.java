package ru.geekbrains.fragmentnavigation;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {

    String shape;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_second, container, false);
        TextView textShape = fragmentView.findViewById(R.id.textShape);
        textShape.setText(shape);
        return fragmentView;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
