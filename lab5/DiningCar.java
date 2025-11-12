package lab5;

/**
 * вагон-ресторан — невелика кількість пасажирів (посадкові місця), а також є запаси (багаж/продукти)
 */
public class DiningCar extends RailCar<Integer> {
    private int seated;
    private double inventoryKg;

    public DiningCar(String id, int comfortLevel, int seated, double inventoryKg) {
        super(id, comfortLevel);
        if (seated < 0 || inventoryKg < 0) throw new IllegalArgumentException("values cannot be negative");
        this.seated = seated;
        this.inventoryKg = inventoryKg;
    }

    @Override
    public int getPassengerCount() {
        return seated;
    }

    @Override
    public double getLuggageAmount() {
        return inventoryKg;
    }
}
