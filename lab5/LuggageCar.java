package lab5;

/**
 * вагон для багажу — тут пасажирів зазвичай немає, тільки багаж
 */
public class LuggageCar extends RailCar<Void> {
    private double luggageKg;

    public LuggageCar(String id, int comfortLevel, double luggageKg) {
        super(id, comfortLevel);
        if (luggageKg < 0) throw new IllegalArgumentException("luggageKg cannot be negative");
        this.luggageKg = luggageKg;
    }

    @Override
    public int getPassengerCount() {
        return 0;
    }

    @Override
    public double getLuggageAmount() {
        return luggageKg;
    }

    // можливість додати багаж 
    public void addLuggage(double kg) {
        if (kg < 0) throw new IllegalArgumentException("kg cannot be negative");
        this.luggageKg += kg;
    }
}
