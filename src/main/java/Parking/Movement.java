package Parking;

import Vehicle.Vehicle;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/* This class registers movement: what and when */
public class Movement {
    private Vehicle vehicle;
    private MovementType movementType;
    private String dateOfMovement;
    /* Initializing date stuff */
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfMovement = df.format(Timestamp.valueOf(now));
    }

    public Movement(MovementType movementType, Vehicle vehicle) {
        this.movementType = movementType;
        this.vehicle = vehicle;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public String getDateOfMovement() {
        return dateOfMovement;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    @Override
    public String toString() {
        return "Car plate: "+vehicle.getPlate()+" | IN/OUT: "+this.getMovementType()+" | Date:"+this.dateOfMovement;
    }

}
