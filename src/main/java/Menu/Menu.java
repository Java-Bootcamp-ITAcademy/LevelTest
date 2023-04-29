package Menu;

import Parking.Parking;

import java.util.*;

public class Menu {
    public static final Scanner sc;
    public static final Parking parking;

    static {
        sc= new Scanner(System.in);
        parking = new Parking();
    }
    public static void menu(){
        boolean keepExecution=true;
        do {
            menuHeader();
            int option = Integer.parseInt(sc.nextLine());

            switch (option) {
                case 1: {
                    registerNewCarMenu();
                    break;
                }
                case 2: {
                    moveVehicle();
                    break;
                }
                case 3: {
                    printLastMovement();
                    break;
                }
                case 4: {
                    printMovementByDateMenu();
                    break;
                }
                case 5: {
                    printMovementByMonthMenu();
                    break;
                }
                case 6: {
                    printMovementByUserMenu();
                    break;
                }
                case 7: {
                    parking.printingVehiclesToFile();
                    break;
                }
                case 8: {
                    parking.printingMovementsToFile();
                    break;
                }
                case 0: {
                    sc.close();
                    keepExecution = false;
                }
            }
        } while(keepExecution);

    }
    public static void menuHeader() {
        System.out.println("Choose an option: ");
        System.out.println("1. Register a new vehicle");
        System.out.println("2. Register new movement");
        System.out.println("3. Print last vehicle movement");
        System.out.println("4. Print movements by date");
        System.out.println("5. Print movements by month");
        System.out.println("6. Print movements by user");
        System.out.println("7. Print vehicles to file");
        System.out.println("8. Print movements to file");
        System.out.println("0. End program");
    }
    public static void registerNewCarMenu() {
        System.out.println("Introduce owner name");
        String name = sc.nextLine();
        System.out.println("Introduce owner id");
        String id = sc.nextLine();
        System.out.println("Introduce vehicle brand");
        String brand = sc.nextLine();
        System.out.println("Introduce vehicle model");
        String model = sc.nextLine();
        System.out.println("Introduce vehicle plate");
        String plate = sc.nextLine();
        System.out.println("Introduce vehicle Type: M, L, N");
        String type = sc.nextLine();
        System.out.println("Is this a resident vehicle? true/false");
        boolean isResident = Boolean.valueOf(sc.nextLine());
        parking.newVehicleRegistration(name,id,brand,model,plate,type,isResident);
    }

    public static void moveVehicle() {
        System.out.println("Type vehicle plate: ");
        String plate = sc.nextLine();
        System.out.println("Is it arriving(IN) or leaving (OUT)?");
        String movement = sc.nextLine();
        parking.newVehicleMovement(movement,plate);
    }

    public static void printLastMovement() {
        System.out.println("Type vehicle plate: ");
        String plate = sc.nextLine();
        parking.printingLastMovement(plate);
    }

    public static void printMovementByDateMenu() {
        System.out.println("Type date: (dd-MM-yyyy)");
        String date = sc.nextLine();
        parking.printMovementsByDay(date);
    }

    public static void printMovementByMonthMenu() {
        System.out.println("Type mont: (MM)");
        String month = sc.nextLine();
        parking.printMovementsByMonth(month);
    }

    public static void printMovementByUserMenu() {
        System.out.println("Type user id");
        String id = sc.nextLine();
        parking.printMovementsByUser(id);
    }
}
