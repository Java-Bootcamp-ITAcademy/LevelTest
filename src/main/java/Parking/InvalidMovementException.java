package Parking;

public class InvalidMovementException extends Exception{
    public InvalidMovementException() {
        super("This movement is not allowed!");
    }
}
