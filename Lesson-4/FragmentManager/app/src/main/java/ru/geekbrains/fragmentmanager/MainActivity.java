package ru.geekbrains.fragmentmanager;

import android.app.Fragment;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Fragment1 fragment1;
    Fragment2 fragment2;
    Fragment3 fragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создадим фрагменты
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        //обработка кнопок
        Button add1 = (Button)findViewById(R.id.add1);
        add1.setOnClickListener(new ListenerOnAdd(fragment1));
        Button add2 = (Button)findViewById(R.id.add2);
        add2.setOnClickListener(new ListenerOnAdd(fragment2));
        Button add3 = (Button)findViewById(R.id.add3);
        add3.setOnClickListener(new ListenerOnAdd(fragment3));
        Button remove1 = (Button)findViewById(R.id.remove1);
        remove1.setOnClickListener(new ListenerOnRemove(fragment1));
        Button remove2 = (Button)findViewById(R.id.remove2);
        remove2.setOnClickListener(new ListenerOnRemove(fragment2));
        Button remove3 = (Button)findViewById(R.id.remove3);
        remove3.setOnClickListener(new ListenerOnRemove(fragment3));
        Button replace1 = (Button)findViewById(R.id.replace1);
        replace1.setOnClickListener(new ListenerOnReplace(fragment1));
        Button replace2 = (Button)findViewById(R.id.replace2);
        replace2.setOnClickListener(new ListenerOnReplace(fragment2));
        Button replace3 = (Button)findViewById(R.id.replace3);
        replace3.setOnClickListener(new ListenerOnReplace(fragment3));
    }

    // при написании анонимного класса слушателя на кнопку, было замечено, что при этом возникает
    // много дублирующего кода, пожтому было решено вытащить этот код в отдельный класс.
    // сравните с:
    // add1.setOnClickListener(new View.OnClickListener(){
    //    @Override
    //    public void onClick(View v) {
    //        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
    //        fragmentTransaction.add(R.id.fragment_container, fragment);
    //        fragmentTransaction.commit();
   //    }
    //  });
    class ListenerOnAdd implements View.OnClickListener{

        Fragment fragment;

        ListenerOnAdd(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            addFragment();
        }
        // Добавить фрагмент
        private void addFragment(){
            // открыть транзакцию
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            // добавить фрагмент
            fragmentTransaction.add(R.id.fragment_container, fragment);
            // закрыть транзакцию
            fragmentTransaction.commit();
        }
    }

    class ListenerOnRemove implements View.OnClickListener{

        Fragment fragment;

        ListenerOnRemove(Fragment fragment){
            this.fragment = fragment;
        }
        @Override
        public void onClick(View v) {
            removeFragment();
        }
        // удалить фрагмент
        private void removeFragment(){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        }
    }

    class ListenerOnReplace implements View.OnClickListener{

        Fragment fragment;

        ListenerOnReplace(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onClick(View v) {
            replaceFragment();
        }
        // заменить фрагмент
        private void replaceFragment(){
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}
