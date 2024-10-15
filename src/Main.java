//Strategy
import java.util.Scanner;

interface IShippingStrategy {
    double calculateShippingCost(double weight, double distance);
}

class StandardShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 0.5 + distance * 0.1;
    }
}



class ExpressShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return (weight * 0.75 + distance * 0.2) + 10;
    }
}


class InternationalShippingStrategy implements IShippingStrategy {
    @Override
    public double calculateShippingCost(double weight, double distance) {
        return weight * 1.0 + distance * 0.5 + 15;
    }
}


class DeliveryContext {
    private IShippingStrategy shippingStrategy;


    public void setShippingStrategy(IShippingStrategy strategy) {
        this.shippingStrategy = strategy;
    }


    public double calculateCost(double weight, double distance) {
        if (shippingStrategy == null) {
            throw new IllegalStateException("Стратегия доставки не установлена.");
        }
        return shippingStrategy.calculateShippingCost(weight, distance);
    }
}





public class Main {
    public static void main(String[] args) {
        DeliveryContext deliveryContext = new DeliveryContext();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите тип доставки: 1 - Стандартная, 2 - Экспресс, 3 - Международная");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                deliveryContext.setShippingStrategy(new StandardShippingStrategy());
                break;
            case "2":
                deliveryContext.setShippingStrategy(new ExpressShippingStrategy());
                break;
            case "3":
                deliveryContext.setShippingStrategy(new InternationalShippingStrategy());
                break;
            default:
                System.out.println("Неверный выбор.");
                return;
        }


        System.out.println("Введите вес посылки (кг):");
        double weight = scanner.nextDouble();
        if (weight <= 0) {
            System.out.println("Вес должен быть положительным числом.");
            return;
        }

        System.out.println("Введите расстояние доставки (км):");
        double distance = scanner.nextDouble();
        if (distance <= 0) {
            System.out.println("Расстояние должно быть положительным числом.");
            return;
        }


        double cost = deliveryContext.calculateCost(weight, distance);
        System.out.printf("Стоимость доставки: %.2f%n", cost);

    }
}

