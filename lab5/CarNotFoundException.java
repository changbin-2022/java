package lab5;

/**
 * якщо вагон за діапазоном не знайдено
 */
public class CarNotFoundException extends Exception {
    public CarNotFoundException(String message) {
        super(message);
    }
}
