package lab5;

// Варіант 7 
// Визначити ієрархію рухомого складу залізничного транспорту. Створити пасажирський потяг. 
//Порахувати загальну чисельність пасажирів і багажу в потязі. Провести сортування вагонів потягу за рівнем комфортності. 
//Знайти вагон в потязі, що відповідає заданому діапазону кількості пасажирів.

// демонстрація використання класів
public class Main {
    public static void main(String[] args) {
        try {
            Train train = new Train(4);

            // створюємо вагони
            PassengerCar pc1 = new PassengerCar("PC-001", 3, 48, 120.5);
            SleeperCar sc1 = new SleeperCar("SL-001", 5, 20, 80.0, 10);
            LuggageCar lc1 = new LuggageCar("LG-001", 1, 200.0);
            DiningCar dc1 = new DiningCar("DN-001", 4, 12, 50.0);

            train.addCar(pc1);
            train.addCar(sc1);
            train.addCar(lc1);
            train.addCar(dc1);

            System.out.println("Initial train:");
            System.out.println(train);

            System.out.printf("Total passengers: %d%n", train.totalPassengers());
            System.out.printf("Total luggage (kg): %.2f%n", train.totalLuggage());

            // сортуємо за комфортом спадаюче 
            train.sortByComfort(false);
            System.out.println("\nTrain sorted by comfort (descending):");
            System.out.println(train);

            // пошук вагона з пасажирами в діапазоні
            int min = 10, max = 25;
            try {
                RailCar<?> found = train.findCarByPassengerRange(min, max);
                System.out.printf("Found car with passengers in range %d-%d: %s%n", min, max, found);
            } catch (CarNotFoundException e) {
                System.out.println("Search: " + e.getMessage());
            }

            // приклад обробки виключення при видаленні пасажирів
            try {
                pc1.removePassengers(100); // хибна операція
            } catch (InvalidPassengerCountException e) {
                System.out.println("Passenger operation error: " + e.getMessage());
            }

        } catch (Exception e) {
            // загальна обробка несподіваних помилок
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
