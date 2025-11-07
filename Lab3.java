import java.util.Arrays;

//Варіант 7
// Визначити клас меблі, який складається як мінімум з 5-и полів

public class Lab3 {

    /**
     * Основний метод програми
     *
     * @param args аргументи командного рядка
     */
    public static void main(String[] args) {

        // створюємо масив об’єктів Furniture із наперед заданими значеннями
        Furniture[] furnitureArray = new Furniture[]{
                new Furniture("Chair", "Wood", 50.0, 7.5, 150.0),
                new Furniture("Table", "Metal", 120.0, 15.0, 300.0),
                new Furniture("Sofa", "Leather", 200.0, 25.0, 600.0),
                new Furniture("Bed", "Wood", 180.0, 22.0, 500.0),
                new Furniture("Chair", "Wood", 50.0, 7.0, 160.0)
        };

        // виводимо початковий масив
        System.out.println("Original array:");
        printArray(furnitureArray);

        // сортуємо масив за ціною за зростанням, а при рівних цінах - за вагою за спаданням
        Arrays.sort(furnitureArray);

        // виводимо відсортований масив
        System.out.println("\nSorted array:");
        printArray(furnitureArray);

        // створюємо об’єкт, який будемо шукати у масиві
        Furniture sample = new Furniture("Chair", "Wood", 50.0, 7.5, 150.0);
        boolean found = false;

        // перебираємо всі об’єкти масиву та перевіряємо, чи є ідентичний елемент
        for (Furniture item : furnitureArray) {
            if (item.equals(sample)) {
                found = true;
                break; // якщо знайдено - припиняємо пошук
            }
        }

        // виводимо результат пошуку
        System.out.println("\nSearch result: " + (found ? "Object found!" : "Object NOT found!"));
    }

    /**
     * Метод для виведення всіх елементів масиву меблів у консоль
     *
     * @param array масив об’єктів Furniture
     */
    private static void printArray(Furniture[] array) {
        for (Furniture furniture : array) {
            System.out.println(furniture);
        }
    }
}

/**
 * Клас Furniture (Меблі) - описує об’єкт меблів із п’ятьма характеристиками
 * Реалізує інтерфейс Comparable для можливості сортування
 */
class Furniture implements Comparable<Furniture> {

    // поля класу
    private String name; // назва меблів
    private String material; // матеріал
    private double price; // ціна
    private double weight; // вага
    private double height; // висота

    /**
     * Конструктор класу Furniture
     *
     * @param name     назва меблів
     * @param material матеріал виготовлення
     * @param price    ціна
     * @param weight   вага
     * @param height   висота
     */
    public Furniture(String name, String material, double price, double weight, double height) {
        this.name = name;
        this.material = material;
        this.price = price;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Реалізація методу compareTo() для стандартного сортування
     *
     * @param other інший об’єкт Furniture для порівняння
     * @return результат порівняння (від’ємне, нуль або додатне число)
     */
    @Override
    public int compareTo(Furniture other) {
        int priceCompare = Double.compare(this.price, other.price);

        if (priceCompare == 0) {
            // якщо ціни рівні — порівнюємо вагу за спаданням
            return Double.compare(other.weight, this.weight);
        }
        return priceCompare; // інакше повертаємо результат порівняння цін
    }

     /**
     * Перевизначений метод equals() — перевіряє повну рівність двох об’єктів
     *
     * @param obj об’єкт для порівняння
     * @return true — якщо об’єкти рівні за всіма полями, false — інакше
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // якщо це той самий об’єкт
        if (obj == null || getClass() != obj.getClass()) return false; // якщо різні класи — не рівні

        Furniture other = (Furniture) obj;
        // порівнюємо всі поля
        return name.equals(other.name)
                && material.equals(other.material)
                && price == other.price
                && weight == other.weight
                && height == other.height;
    }

    /**
     * Перевизначений метод toString() для зручного виведення інформації про меблі
     *
     * @return текстове представлення об’єкта
     */
    @Override
    public String toString() {
        return "Furniture: name=" + name
                + ", material=" + material
                + ", price=" + price
                + ", weight=" + weight
                + ", height=" + height;
    }
}
