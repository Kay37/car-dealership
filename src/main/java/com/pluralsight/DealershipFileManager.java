package com.pluralsight;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigDecimal;

//! DealershipFileManager will be responsible for reading the dealership file. It will parse the data, create a
//! Dealership object, and create each Vehicle to be added to the Dealership's inventory. It will ALSO be responsible
//! for saving the dealership & its vehicles in the file in the same pipe-delimited format.
public class DealershipFileManager {

    //! Storing the name of the inventory file in a String for convenience
    private String csvFile = "inventory.csv";

    //! Defining the getDealership method
    public Dealership getDealership() {

        Dealership dealership = null;

        //! try-with-resources (auto closes at end)
        try (BufferedReader inventoryReader = new BufferedReader(new FileReader(csvFile))) {

            //! dealershipInfo is the inventory file's first line
            String dealershipInfo = inventoryReader.readLine();
            //! if the first line is not missing,
            if (dealershipInfo != null) {
                //! split that first line into parts at the |
                String[] dealershipParts = dealershipInfo.split("\\|");
                //! the dealership's name, address, and phone are at indexes 0, 1, and 2 respectively
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                //! Update our null dealership to hold needed values given the inventory file's first line information
                dealership = new Dealership(name, address, phone);

            }
            //! String vehicleLine instantiated
            String vehicleLine;
            //! Read through the inventory file line by line until we reach null (no more lines)
            while ((vehicleLine = inventoryReader.readLine()) != null) {
                //! Split the lines into pieces at the | and store it in a String array
                String[] vehicleParts = vehicleLine.split("\\|");
                //! vin, year, make, model, type, color, odometer, and price are indexes 0-7, respectively.
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String type = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                BigDecimal price = new BigDecimal(vehicleParts[7]);
                //! create a new car from those pieces we disected from the inventory.csv
                Vehicle newCar = new Vehicle(vin, year, make, model, type, color, odometer, price);
                //! add that new car to our dealership ArrayList, using the addVehicle derived getter
                dealership.addVehicle(newCar);
            }
            //! handling an exception
        } catch (Exception e) {

            System.out.println("Error reading the file: " + e.getMessage() + ".");

        }
        //! hand back the dealership that we assembled from the inventory file. We built the dealership object from
        //! the first line, then populated that object with vehicles from each line thereafter. We return this
        //! assembled dealership to wherever it was called from
        return dealership;
    }
    //! method called saveDealership that runs its contents when called and provided a dealership object
    public void saveDealership(Dealership dealership) {
        //! try-with-resources (BufferedWriter & fileWriter)
        try (BufferedWriter inventoryWriter = new BufferedWriter(new FileWriter(csvFile))) {
            //! Write the dealership info line
            inventoryWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            //! Create a new line for the vehicles to be placed on
            inventoryWriter.newLine();
            //! for every vehicle in our dealership, using the getAllVehicles() derived getter
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                //! Write the vehicle's information using our Override toString
                inventoryWriter.write(vehicle.toString());
                //! Then write a new line for the next vehicle to go onto
                inventoryWriter.newLine();
            }
            //! handling our exceptions
        } catch (Exception e) {
            //! Nice little error message
            System.out.println("Error writing the file: " + e.getMessage() + ".");
        }
    }

}
