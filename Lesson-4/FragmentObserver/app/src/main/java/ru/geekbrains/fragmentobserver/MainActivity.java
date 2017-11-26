package ru.geekbrains.fragmentobserver;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements PublishGetter {

    private Publisher publisher = new Publisher();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Создаем фрагменты
        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        MainFragment mainFragment = new MainFragment();
        // Подписываем фрагменты
        publisher.subscribe(fragment1);
        publisher.subscribe(fragment2);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // добавить фрагменты
        fragmentTransaction.add(R.id.fragment_main, mainFragment);
        fragmentTransaction.add(R.id.fragment_1, fragment1);
        fragmentTransaction.add(R.id.fragment_2, fragment2);
        // закрыть транзакцию
        fragmentTransaction.commit();
    }

    // Снимем с активити обязанность по передаче событий классу Publisher
    // главный фрагмент будет использовать его для  передачи событий
    @Override
    public Publisher getPublisher() {
        return publisher;
    }
}
