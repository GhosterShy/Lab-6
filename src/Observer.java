//Observer
import java.util.ArrayList;
import java.util.List;


interface IObserver {
    void update(float temperature);
}

interface ISubject {
    void registerObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers();
}




class WeatherStation implements ISubject {
    private List<IObserver> observers;
    private float temperature;

    public WeatherStation() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(IObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(IObserver observer) {
        if (observer != null && observers.contains(observer)) {
            observers.remove(observer);
        } else {
            System.out.println("Наблюдатель не найден.");
        }
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer : observers) {
            observer.update(temperature);
        }
    }

    public void setTemperature(float newTemperature) {
        if (newTemperature < -50 || newTemperature > 60) {
            System.out.println("Некорректная температура. Введите значение от -50 до 60.");
            return;
        }

        System.out.println("Изменение температуры: " + newTemperature + "°C");
        temperature = newTemperature;
        notifyObservers();
    }
}


class WeatherDisplay implements IObserver {
    private String name;

    public WeatherDisplay(String name) {
        this.name = name;
    }

    @Override
    public void update(float temperature) {
        System.out.println(name + " показывает новую температуру: " + temperature + "°C");
    }
}





public class Observer {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();


        WeatherDisplay mobileApp = new WeatherDisplay("Мобильное приложение");
        WeatherDisplay digitalBillboard = new WeatherDisplay("Электронное табло");


        weatherStation.registerObserver(mobileApp);
        weatherStation.registerObserver(digitalBillboard);


        weatherStation.setTemperature(25.0f);
        weatherStation.setTemperature(30.0f);


        weatherStation.removeObserver(digitalBillboard);
        weatherStation.setTemperature(28.0f);


        weatherStation.setTemperature(70.0f);
    }
}
