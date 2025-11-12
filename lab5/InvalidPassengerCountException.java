package lab5;

/**
 * виняток для операцій з пасажирами
 */
public class InvalidPassengerCountException extends Exception {
    public InvalidPassengerCountException(String message) {
        super(message);
    }
}
