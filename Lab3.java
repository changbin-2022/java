import java.util.Arrays;

/**
 * Main class that demonstrates working with the Furniture class.
 * Tasks performed:
 * - Create an array of Furniture objects
 * - Sort the array: first field ascending, second field descending
 * - Search for an identical object in the array after sorting
 * - Display results in console
 *
 * @author Varvara Kalenichenko
 * @version 1.0
 */
public class Lab3 {

    /**
     * Main method that launches the program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Create an array of Furniture objects with predefined values
        Furniture[] furnitureArray = new Furniture[]{
                new Furniture("Chair", "Wood", 50.0, 7.5, 150.0),
                new Furniture("Table", "Metal", 120.0, 15.0, 300.0),
                new Furniture("Sofa", "Leather", 200.0, 25.0, 600.0),
                new Furniture("Bed", "Wood", 180.0, 22.0, 500.0),
                new Furniture("Chair", "Wood", 50.0, 7.0, 160.0)
        };

        System.out.println("Original array:");
        printArray(furnitureArray);

        // Sort: by price ascending, by weight descending when price is equal
        Arrays.sort(furnitureArray);

        System.out.println("\nSorted array:");
        printArray(furnitureArray);

        // Find object identical to the sample
        Furniture sample = new Furniture("Chair", "Wood", 50.0, 7.5, 150.0);
        boolean found = false;

        for (Furniture item : furnitureArray) {
            if (item.equals(sample)) {
                found = true;
                break;
            }
        }

        System.out.println("\nSearch result: " + (found ? "Object found!" : "Object NOT found!"));
    }

    /**
     * Prints all elements of furniture array.
     *
     * @param array array of Furniture objects to print
     */
    private static void printArray(Furniture[] array) {
        for (Furniture furniture : array) {
            System.out.println(furniture);
        }
    }
}

/**
 * Represents a piece of furniture with 5 main characteristics.
 * Implements Comparable for sorting logic.
 */
class Furniture implements Comparable<Furniture> {

    private String name;
    private String material;
    private double price;
    private double weight;
    private double height;

    /**
     * Constructor for Furniture class.
     *
     * @param name     furniture name
     * @param material furniture material
     * @param price    furniture price
     * @param weight   furniture weight
     * @param height   furniture height
     */
    public Furniture(String name, String material, double price, double weight, double height) {
        this.name = name;
        this.material = material;
        this.price = price;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Compares objects by price ascending, then by weight descending.
     *
     * @param other other Furniture object
     * @return comparison result integer
     */
    @Override
    public int compareTo(Furniture other) {
        int priceCompare = Double.compare(this.price, other.price);

        if (priceCompare == 0) {
            return Double.compare(other.weight, this.weight);
        }
        return priceCompare;
    }

    /**
     * Checks full equality of furniture objects.
     *
     * @param obj compared object
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Furniture other = (Furniture) obj;
        return name.equals(other.name)
                && material.equals(other.material)
                && price == other.price
                && weight == other.weight
                && height == other.height;
    }

    /**
     * Returns furniture details as a string.
     *
     * @return formatted object info
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
