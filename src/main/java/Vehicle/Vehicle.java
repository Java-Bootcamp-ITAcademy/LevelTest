package Vehicle;

import Owner.Owner;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Vehicle {
    private final static double MONTHLYFARE = 95.50;
    private final static double TYPEL = 1;
    private final static double TYPEM = 1.5;
    private final static double TYPEN = 2;
    private final static double RESIDENTDISCOUNT = 0.5;
    private final static double NONRESIDENTCHARGE = 0.25;
    private final String brand;
    private final String model;
    private final String plate;
    private final VehicleType type;
    private final boolean isResident;
    private final String dateOfRegistration;
    private final Owner owner;
    private boolean vehicleIsIn;
    private double fare;
    public static final DecimalFormat f = new DecimalFormat("##.00");
    {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        LocalDateTime now = LocalDateTime.now();
        this.dateOfRegistration = df.format(Timestamp.valueOf(now));
    }

    public Vehicle(String brand, String model, String plate, VehicleType type, boolean resident, Owner owner) {
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.type = type;
        this.isResident = resident;
        this.owner = owner;
        this.vehicleIsIn = true;
        this.setFare();
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getPlate() {
        return plate;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean isResident() {
        return isResident;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public Owner getOwner() {
        return owner;
    }

    public boolean isVehicleIsIn() {
        return vehicleIsIn;
    }

    public void setVehicleIsIn(boolean vehicleIsIn) {
        this.vehicleIsIn = vehicleIsIn;
    }

    public void setFare() {
        if(this.getType()==VehicleType.L) {
            this.fare = MONTHLYFARE*(1+TYPEL/100);
        }
        else if(this.getType()==VehicleType.M) {
            this.fare = MONTHLYFARE*(1+TYPEM/100);
        }
        else {
            this.fare = MONTHLYFARE*(1+TYPEN/100);
        }
        if(this.isResident) {
            this.fare = this.fare*(1-RESIDENTDISCOUNT/100);
        }
        else {
            this.fare = this.fare*(1+NONRESIDENTCHARGE/100);
        }
    }

    public double getFare() {
        return fare;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(plate, vehicle.plate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plate);
    }

    @Override
    public String toString() {
        return "Brand: "+this.getBrand()+ " | Model: "+this.getModel()+" | Plate:"+this.getPlate()+" | Type: "+this.getType()+" Is Resident: "+this.isResident+" | Date of registration: "+this.getDateOfRegistration()+" | Monthly fare: "+f.format(this.fare);
    }
}
