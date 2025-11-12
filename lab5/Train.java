package lab5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * клас, що складається з масиву об'єктів вагонів і реалізує операції над поїздом
 */
public class Train {
    private RailCar<?>[] cars;
    private int size; // фактична кількість вагонів у масиві

    public Train(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be positive");
        this.cars = new RailCar<?>[capacity];
        this.size = 0;
    }

    // додати вагон у кінець поїзда
    public void addCar(RailCar<?> car) throws NullPointerException {
        if (car == null) throw new NullPointerException("car cannot be null");
        if (size >= cars.length) {
            // розширити масив (без колекцій для навчальної мети)
            cars = Arrays.copyOf(cars, cars.length * 2);
        }
        cars[size++] = car;
    }

    // повертає загальну чисельність пасажирів
    public int totalPassengers() {
        int total = 0;
        for (int i = 0; i < size; i++) {
            total += cars[i].getPassengerCount();
        }
        return total;
    }

    // повертає загальну кількість багажу (сума)
    public double totalLuggage() {
        double total = 0;
        for (int i = 0; i < size; i++) {
            total += cars[i].getLuggageAmount();
        }
        return total;
    }

    // сортування вагонів за рівнем комфортності (зростання або спадання)
    public void sortByComfort(boolean ascending) {
        Arrays.sort(cars, 0, size, Comparator.comparingInt(RailCar::getComfortLevel));
        if (!ascending) {
            // розвернути частину
            for (int i = 0, j = size - 1; i < j; i++, j--) {
                RailCar<?> tmp = cars[i];
                cars[i] = cars[j];
                cars[j] = tmp;
            }
        }
    }

    // знайти перший вагон, у якого passengerCount в заданому діапазоні [min,max]
    public RailCar<?> findCarByPassengerRange(int min, int max) throws CarNotFoundException {
        if (min < 0 || max < 0) throw new IllegalArgumentException("min/max cannot be negative");
        if (min > max) throw new IllegalArgumentException("min cannot be greater than max");
        for (int i = 0; i < size; i++) {
            int pc = cars[i].getPassengerCount();
            if (pc >= min && pc <= max) {
                return cars[i];
            }
        }
        throw new CarNotFoundException("no car found with passenger count in range " + min + "-" + max);
    }

    public RailCar<?> getCar(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index out of range");
        return cars[index];
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Train composition:\n");
        for (int i = 0; i < size; i++) {
            sb.append(String.format("%d: %s\n", i, cars[i].toString()));
        }
        return sb.toString();
    }
}
