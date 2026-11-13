package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;

//! Dealership will hold information about the dealership and maintain a list of vehicles. It will also have the
//! methods to search, add, and remove vehicles from the list.
public class Dealership {

    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        this.inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public ArrayList<Vehicle> getAllVehicles() {
        return this.inventory;
    }

    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }

    //! public: other classes can call this method
    //! ArrayList<Vehicle>: what this method hands back - a list of vehicles, called getVehiclesByPrice
    //! BigDecimal min, BigDecimal max: what the caller must provide the method - the bottom & top of the price range
    //! NOT static. Belongs to ONE dealership object & searches that dealership's inventory (not all dealerships)
    public ArrayList<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        //* Creating a new empty list for the search results. Starts empty & gets filled with the matches.
        ArrayList<Vehicle> vehiclesByPrice = new ArrayList<>();

        //* Look at every vehicle in this dealership's inventory, one at a time.
        for (Vehicle vehicle : this.inventory) {
            //* Is this car's price inside the range provided when method was called?
            //* This line is two checks, and both must be true (&&).
            //* vehicle.getPrice().compareTo(min) >= 0 -> this means the price is at or above the minimum.
            //* vehicle.getPrice().compareTo(max) <= 0 -> this means the price is at or below the maximum.
            //* compareTo is required because BigDecimal can't use >= or <= directly. compareTo is the new comparison
            //* sign.
            if (vehicle.getPrice().compareTo(min) >= 0 && vehicle.getPrice().compareTo(max) <= 0) {
                //* If it passed the two checks above, simultaneously, add the vehicle to our new list.
                vehiclesByPrice.add(vehicle);
            }
        }
        //* Hand the results back to whoever called the method.
        return vehiclesByPrice;
    }

    public ArrayList<Vehicle> getVehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> vehiclesByMakeModel = new ArrayList<>();

        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehiclesByMakeModel.add(vehicle);
            }
        }
        return vehiclesByMakeModel;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        ArrayList<Vehicle> vehiclesByYear = new ArrayList<>();

        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                vehiclesByYear.add(vehicle);
            }
        }
        return vehiclesByYear;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        ArrayList<Vehicle> vehiclesByColor = new ArrayList<>();

        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehiclesByColor.add(vehicle);
            }
        }
        return vehiclesByColor;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        ArrayList<Vehicle> vehiclesByMileage = new ArrayList<>();

        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                vehiclesByMileage.add(vehicle);
            }
        }
        return vehiclesByMileage;
    }

    public ArrayList<Vehicle> getVehiclesByType(String type) {
        ArrayList<Vehicle> vehiclesByType = new ArrayList<>();

        for (Vehicle vehicle : this.inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(type)) {
                vehiclesByType.add(vehicle);
            }
        }
        return vehiclesByType;
    }

}
