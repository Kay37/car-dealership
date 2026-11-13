package com.pluralsight;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//! UserInterface will be responsible for all output to the screen, reading of user input, and using the
//! Dealership's search, add, and remove as needed. (ex. when the user selects "List all Vehicles", UI would call the
//! Dealership method and then display the vehicles it returns.)
public class UserInterface {

    //! Creating a field/variable that holds the dealership this menu will be working with.
    private Dealership dealership;

    //! UserInterface constructor.
    public UserInterface() {

    }

    //! Method to display.
    //! It immediately calls the init method, which creates a DealershipFileManager and grabs the dealership.
    public void display() {
        init();

        //* Creating variable for looping our app
        boolean appRunning = true;
        //* Looping the app
        while (appRunning) {
            System.out.println("\n=== Car Dealership Menu ===");
            System.out.println("1 - Find vehicles by price");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find all vehicles by mileage");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List all vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("10 - Sell/Lease a Vehicle");
            System.out.println("99 - Quit");

            //! Prompting the user for a menu selection and storing their answer
            int menuSelection = InputToolbox.promptInt(
                    "Please select an action from the menu (e.g. 1, 10, 99): ", 1, 99);
            //! Switch statement for the different possible menu actions
            switch (menuSelection) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRangeRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    System.out.println("Sorry! This is not a functioning option at this time.");
                    break;
                case 99:
                    System.out.println("Thanks for using our application! Goodbye.");
                    appRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose a valid number from the menu: ");
                    break;
            }
        }
    }
    //! The init method creates a new DealershipFileManager object & provides that file manager with the dealership
    private void init() {

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();

    }
    //! Ask user for price range, convert it into BigDecimal, and displayVehicles method displays all vehicles
    //! from the dealership that match that price range
    public void processGetByPriceRequest() {
        int getByPriceRequestMin = InputToolbox.promptInt(
                "Please enter the minimum price: ", 0);
        int getByPriceRequestMax = InputToolbox.promptInt(
                "Please enter the maximum price: ", getByPriceRequestMin);
        BigDecimal priceRequestMinAsBigDecimal = new BigDecimal(getByPriceRequestMin);
        BigDecimal priceRequestMaxAsBigDecimal = new BigDecimal(getByPriceRequestMax);
        displayVehicles(dealership.getVehiclesByPrice(priceRequestMinAsBigDecimal, priceRequestMaxAsBigDecimal));
    }
    //! Pretty much the same logic as GetByPriceRequest
    public void processGetByMakeModelRequest() {
        String getByMakeRequest = InputToolbox.promptString(
                "Please enter the make you'd like to search for: ");
        String getByModelRequest = InputToolbox.promptString(
                "Please enter the model you'd like to search for: ");
        displayVehicles(dealership.getVehiclesByMakeModel(getByMakeRequest, getByModelRequest));
    }
    //! Pretty much the same logic as GetByPriceRequest
    public void processGetByYearRangeRequest() {
        int getByYearRangeRequestStart = InputToolbox.promptInt(
                "Please enter the start of the year range: ", 0);
        int getByYearRangeRequestEnd = InputToolbox.promptInt(
                "Please enter the end of the year range: ", getByYearRangeRequestStart);
        displayVehicles(dealership.getVehiclesByYear(getByYearRangeRequestStart, getByYearRangeRequestEnd));
    }
    //! Pretty much the same logic as GetByPriceRequest
    public void processGetByMileageRequest() {
        int getByMileageRequestMin = InputToolbox.promptInt(
                "Please enter the minimum mileage of the mileage range: ", 0);
        int getByMileageRequestMax = InputToolbox.promptInt(
                "Please enter the maximum mileage of the mileage range: ", getByMileageRequestMin);
        displayVehicles(dealership.getVehiclesByMileage(getByMileageRequestMin, getByMileageRequestMax));
    }
    //! Pretty much the same logic as GetByPriceRequest
    public void processGetByVehicleTypeRequest() {
        String getByVehicleTypeRequest = InputToolbox.promptString(
                "Please enter the vehicle type you'd like to search for (e.g. car, truck): ");
        displayVehicles(dealership.getVehiclesByType(getByVehicleTypeRequest));
    }
    //! Prompt the user for every field that a vehicle object needs.
    public void processAddVehicleRequest() {
        int vin = InputToolbox.promptInt("Please enter the new vehicle's VIN: ", 0, Integer.MAX_VALUE);
        int year = InputToolbox.promptInt("Please enter the new vehicle's year: ", 1900, 2027);
        String make = InputToolbox.promptString("Please enter the new vehicle's make: ");
        String model = InputToolbox.promptString("Please enter the new vehicle's model: ");
        String vehicleType = InputToolbox.promptString("Please enter the new vehicle's type (e.g. Car, Truck): ");
        String color = InputToolbox.promptString("Please enter the new vehicle's color: ");
        int odometer = InputToolbox.promptInt("Please enter the new vehicle's odometer reading: ", 0, 1000000);
        int price = InputToolbox.promptInt("Please enter the new vehicle's price: ", 0, 1000000);
        //! convert the price to a big decimal (up to 2 decimal points included with .setScale)
        BigDecimal priceAsBigDecimal = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
        //! create a new vehicle with the user's answers
        Vehicle newVehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, priceAsBigDecimal);
        //! add the vehicle to the dealership
        dealership.addVehicle(newVehicle);
        //! create a new file manager object
        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        //! call the saveDealership method with that file manager to rebuild the inventory.csv with the new addition
        dealershipFileManager.saveDealership(dealership);
    }

    public void processRemoveVehicleRequest() {
        int vin = InputToolbox.promptInt(
                "Please enter the VIN of the vehicle you'd like to remove: ", 0, Integer.MAX_VALUE);

        Vehicle found = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                found = vehicle;
                break;
            }
        }
        if (found == null) {
            System.out.println("No vehicle found with that VIN.");
        } else {
            dealership.removeVehicle(found);
            System.out.println("Removed: " + found.getVin() + ", " + found.getYear() + " " + found.getMake() + " " + found.getModel());
            DealershipFileManager dealershipFileManager = new DealershipFileManager();
            dealershipFileManager.saveDealership(dealership);
        }
    }

    public void processGetByColorRequest() {
        String getByColorRequest = InputToolbox.promptString(
                "Please enter the color of vehicle(s) you would like to search for: ");
        displayVehicles(dealership.getVehiclesByColor(getByColorRequest));
    }

    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        //! Loop over the list of vehicles and print to the screen
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found. ");
        } else {
            System.out.printf("%-7s %-6s %-10s %-10s %-7s %-8s %9s %10s%n",
                    "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
            System.out.println("-".repeat(74));
            for (Vehicle vehicle : vehicles) {
                String formattedPrice = String.format("$%,.2f", vehicle.getPrice());
                System.out.printf("%-7d %-6d %-10s %-10s %-7s %-8s %,9d %10s%n",
                        vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                        vehicle.getVehicleType(), vehicle.getColor(), vehicle.getOdometer(), formattedPrice);
            }
        }

    }

}
