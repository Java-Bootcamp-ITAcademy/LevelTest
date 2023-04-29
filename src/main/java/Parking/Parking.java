package Parking;

import Owner.Owner;
import Vehicle.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

/* This class manages the parking */
public class Parking {
    private final String name;
    private Set<Vehicle> vehicles;
    private Set<Owner> owners;
    private List<Movement> movements;
    public static final String vehiclesPath = "vehicles.csv";
    public static final String movementsPath = "movements.csv";
    public static final DecimalFormat f = new DecimalFormat("##.00");

    public Parking() {
        this.name = "Parking Toni";
        this.vehicles = new HashSet<>();
        this.owners = new HashSet<>();
        this.movements = new ArrayList<>();
    }

    public Set<Vehicle> getVehicles() {
        return vehicles;
    }

    public Set<Owner> getOwners() {
        return owners;
    }

    public List<Movement> getMovements() {
        return movements;
    }
    /* New vehicle is registered */
    public void newVehicleRegistration(String ownerName, String ownerId, String brand, String model, String plate, String type, boolean resident) {
        Owner owner =  new Owner(ownerName,ownerId);
        this.owners.add(owner);
        VehicleType vehicleType=type.equals("L")?VehicleType.L:(type.equals("M")?VehicleType.M:VehicleType.N);
        Vehicle vehicle =  new Vehicle(brand,model,plate,vehicleType,resident,owner);
        vehicle.setVehicleIsIn(true);
        this.vehicles.add(vehicle);
        owner.getVehicles().add(vehicle);
    }
    /* Gets a vehicle from its plate */
    public Vehicle getVehicleFromList(String plate) {
        return this.getVehicles().stream().filter(s->s.getPlate().equals(plate)).findFirst().orElse(null);
    }
    /* Moves a vehicle */
    public void newVehicleMovement(String movement, String plate) {
        try {
            MovementType movementType = movement.equals("IN") ? MovementType.IN : MovementType.OUT;
            Vehicle vehicle = this.getVehicleFromList(plate);
            Movement vehicleMovement;
            if (vehicle.isVehicleIsIn() && movementType.equals(MovementType.OUT)) {
                vehicleMovement = new Movement(movementType, vehicle);
                vehicle.setVehicleIsIn(false);
                this.getMovements().add(vehicleMovement);
            } else if (!vehicle.isVehicleIsIn() && MovementType.IN.equals(movementType)) {
                vehicleMovement = new Movement(movementType, vehicle);
                vehicle.setVehicleIsIn(true);
                this.getMovements().add(vehicleMovement);
            } else {
                throw new InvalidMovementException();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    /* Prints all parking vehicles */
    public void printingVehicles() {
        for(Vehicle vehicle:this.getVehicles()) {
            System.out.println(vehicle);
        }
    }
    /* Prints last vehicle movement given its plate */
    public void printingLastMovement(String plate) {
        long count = this.getMovements().stream().filter(s->s.getVehicle().getPlate().equals(plate)).count();
        Movement auxMovement = this.getMovements().stream()
               .filter(s->s.getVehicle().getPlate().equals(plate))
               .skip(count-1).findFirst()
               .get();
       System.out.println(auxMovement);
    }
    /* Prints the whole day movements */
    public void printMovementsByDay(String date) {
        this.getMovements().stream()
                .filter(s->s.getDateOfMovement()
                        .contains(date))
                .forEach(System.out::println);
    }
    /* Prints the whole month movements */
    public void printMovementsByMonth(String date) {
        this.getMovements().stream()
                .filter(s->s.getDateOfMovement().contains("-"+date+"-"))
                .forEach(System.out::println);
    }
    /* Prints movement given an user id */
    public void printMovementsByUser(String id) {
        this.getMovements().stream()
                .filter(s->s.getVehicle().getOwner().getId().equals(id))
                .forEach(System.out::println);

    }
    /* Prints vehicle registry to file */
    public void printingVehiclesToFile() {
        try {
            File file = new File(vehiclesPath);
            PrintWriter writer = new PrintWriter(file);
            String header = "Plate, Owner ID, Fare, Resident\n";
            writer.write(header);
            this.getVehicles().stream()
                    .map(s->s.getPlate()+","+s.getOwner().getId()+","+f.format(s.getFare())+","+s.isResident()+"\n")
                    .forEach(writer::write);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    /* Prints movements registry to file */
    public void printingMovementsToFile() {
        try {
            File file = new File(movementsPath);
            PrintWriter writer = new PrintWriter(file);
            String header = "Plate, IN/OUT, Date\n";
            writer.write(header);
            this.getMovements().stream()
                    .map(s->s.getVehicle().getPlate()+","+s.getMovementType()+","+s.getDateOfMovement()+"\n")
                    .forEach(writer::write);
            writer.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
