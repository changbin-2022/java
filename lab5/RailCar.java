package lab5;

/**
 * узагальнений абстрактний клас, що описує вагон залізничного рухомого складу.
 * @param <T> тип "пасажира/вмісту", який цей вагон може містити
 */
public abstract class RailCar<T> {
    // рівень комфортності: 1 (найнижчий) ... 5 (найвищий)
    protected final int comfortLevel;
    protected final String id;

    public RailCar(String id, int comfortLevel) {
        if (comfortLevel < 1 || comfortLevel > 5) {
            throw new IllegalArgumentException("comfortLevel must be in 1..5");
        }
        this.id = id;
        this.comfortLevel = comfortLevel;
    }

    // повернути кількість пасажирів у вагоні (0 якщо не пасажирський)
    public abstract int getPassengerCount();

    // повернути загальну кількість багажу у вагоні (в одиницях, наприклад, місця або кг)
    public abstract double getLuggageAmount();

    public int getComfortLevel() {
        return comfortLevel;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, comfort=%d, passengers=%d, luggage=%.2f]",
                this.getClass().getSimpleName(), id, comfortLevel, getPassengerCount(), getLuggageAmount());
    }
}
