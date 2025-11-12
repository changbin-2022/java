package lab5;

/**
 * вагон-купе/спальний — від PassengerCar наслідується (має додаткові місця для багажу або поділ)
 */
public class SleeperCar extends PassengerCar {
    private int couchettes; // кількість спальних місць

    public SleeperCar(String id, int comfortLevel, int passengerCount, double luggageKg, int couchettes) {
        super(id, comfortLevel, passengerCount, luggageKg);
        if (couchettes < 0) throw new IllegalArgumentException("couchettes cannot be negative");
        this.couchettes = couchettes;
    }

    public int getCouchettes() {
        return couchettes;
    }

    @Override
    public String toString() {
        return super.toString().replace("]", String.format(", couchettes=%d]", couchettes));
    }
}
