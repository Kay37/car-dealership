package com.pluralsight;

import java.math.BigDecimal;
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
    //! It immediatelly calls the init method, which creates a DealershipFileManager and grabs the dealership.
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

            int menuSelection = InputToolbox.promptInt(
                    "Please select an action from the menu (e.g. 1, 10, 99): ", 1, 99);
            switch (menuSelection) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 99:
                    appRunning = false;
                    break;
                default:
                    System.out.println("Invalid selection. Please choose a valid number from the menu: ");
                    break;
            }
        }
    }

    private void init() {

        DealershipFileManager dealershipFileManager = new DealershipFileManager();
        this.dealership = dealershipFileManager.getDealership();

    }

    public void processGetByPriceRequest() {
        int getByPriceRequestMin = InputToolbox.promptInt(
                "Please enter the minimum price: ", 0);
        int getByPriceRequestMax = InputToolbox.promptInt(
                "Please enter the maximum price: ", 0);
        BigDecimal priceRequestMinAsBigDecimal = new BigDecimal(getByPriceRequestMin);
        BigDecimal priceRequestMaxAsBigDecimal = new BigDecimal(getByPriceRequestMax);
        displayVehicles(dealership.getVehiclesByPrice(priceRequestMinAsBigDecimal, priceRequestMaxAsBigDecimal));
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
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle.toString());
            }
        }

    }

}
