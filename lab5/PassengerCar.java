package lab5;

import java.util.Objects;

/**
 * вагон для пасажирів (узагальнений за типом пасажира T)
 * тут T може бути класом Passenger або просто String імені — для задачі використовуємо int як кількість
 */
public class PassengerCar extends RailCar<Integer> {
    // будемо зберігати просто кількість пасажирів та сумарний багаж (в кг або умовних одиницях)
    private int passengerCount;
    private double luggageKg;

    /**
     * створити пасажирський вагон
     * @param id ідентифікатор
     * @param comfortLevel рівень комфорту 1..5
     * @param passengerCount початкова кількість пасажирів (>=0)
     * @param luggageKg загальна вага багажу (>=0)
     */
    public PassengerCar(String id, int comfortLevel, int passengerCount, double luggageKg) {
        super(Objects.requireNonNull(id), comfortLevel);
        if (passengerCount < 0) throw new IllegalArgumentException("passengerCount cannot be negative");
        if (luggageKg < 0) throw new IllegalArgumentException("luggageKg cannot be negative");
        this.passengerCount = passengerCount;
        this.luggageKg = luggageKg;
    }

    @Override
    public int getPassengerCount() {
        return passengerCount;
    }

    @Override
    public double getLuggageAmount() {
        return luggageKg;
    }

    // можливість додати/зняти пасажирів з обробкою помилок
    public void addPassengers(int n) throws InvalidPassengerCountException {
        if (n < 0) throw new InvalidPassengerCountException("cannot add negative passengers");
        this.passengerCount += n;
    }

    public void removePassengers(int n) throws InvalidPassengerCountException {
        if (n < 0) throw new InvalidPassengerCountException("cannot remove negative passengers");
        if (n > passengerCount) throw new InvalidPassengerCountException("not enough passengers to remove");
        this.passengerCount -= n;
    }
}
