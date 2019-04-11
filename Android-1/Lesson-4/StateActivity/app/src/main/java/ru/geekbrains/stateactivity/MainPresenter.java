package ru.geekbrains.stateactivity;

// Это Presenter, делаем его на основе паттерна «одиночка».
// Этот паттерн обладает свойством хранить один экземпляр объекта на все приложение.
// Для реализации паттерна «одиночка» надо добавить статическое приватное поле (instance),
// сделать конструктор приватным,
// добавить статический метод, который проверяет, существует ли этот объект в нашем поле, если нет, то создает его и возвращает это поле в качестве результата.
// Таким образом, в приложении всегда существует только один объект.
// Класс, реализующий паттерн «одиночка» нельзя наследовать.
public final class MainPresenter {

    //Внутреннее поле, будет хранить единственный экземпляр
    private static MainPresenter instance = null;

    // Поле для синхронизации
    private static final Object syncObj = new Object();

    // Это наш счетчик
    private int counter;

    // Конструктор (вызывать извне его нельзя, поэтому он приватный)
    private MainPresenter(){
        counter = 0;
    }

    // Увеличение счетчика
    public void incrementCounter(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }

    // Метод, который возвращает экземпляр объекта.
    // Если объекта нет, то создаем его.
    public static MainPresenter getInstance(){
        // Здесь реализована «ленивая» инициализация объекта,
        // то есть, пока объект не нужен, не создаем его.
        synchronized (syncObj) {
            if (instance == null) {
                instance = new MainPresenter();
            }
            return instance;
        }
    }
}
