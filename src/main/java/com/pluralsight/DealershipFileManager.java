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

    private String csvFile = "inventory.csv";

    public Dealership getDealership() {

        Dealership dealership = null;

        try (BufferedReader inventoryReader = new BufferedReader(new FileReader(csvFile))) {

            String dealershipInfo = inventoryReader.readLine();

            if (dealershipInfo != null) {

                String[] dealershipParts = dealershipInfo.split("\\|");
                String name = dealershipParts[0];
                String address = dealershipParts[1];
                String phone = dealershipParts[2];

                dealership = new Dealership(name, address, phone);

            }
            String vehicleLine;
            while ((vehicleLine = inventoryReader.readLine()) != null) {

                String[] vehicleParts = vehicleLine.split("\\|");
                int vin = Integer.parseInt(vehicleParts[0]);
                int year = Integer.parseInt(vehicleParts[1]);
                String make = vehicleParts[2];
                String model = vehicleParts[3];
                String type = vehicleParts[4];
                String color = vehicleParts[5];
                int odometer = Integer.parseInt(vehicleParts[6]);
                BigDecimal price = new BigDecimal(vehicleParts[7]);

                Vehicle newCar = new Vehicle(vin, year, make, model, type, color, odometer, price);

                dealership.addVehicle(newCar);
            }
        } catch (Exception e) {

            System.out.println("Error reading the file: " + e.getMessage() + ".");

        }
        return dealership;
    }

    public void saveDealership(Dealership dealership) {
//        try {
//            BufferedWriter inventoryWriter = new BufferedWriter(new FileWriter(csvFile));
//            inventoryWriter.write(dealership.toString());
//            inventoryWriter.close();
//        } catch (Exception e) {
//            System.out.println("Error writing the file" + e.getMessage() + ".");
//        }
    }

}
