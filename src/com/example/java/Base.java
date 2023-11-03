package com.example.java;

/**
 * <p>This serves as the base class. With properties
 * other classes will inherit from</p>
 */
public class Base {
    public int id;
    public String dfDateCreated;
    public String dfTimeCreated;
    public String dfExpiryDate;
    public String dfExpiryTime;

    public Base() {
        System.out.println("Instance created successfully!!!");
    }

    public static void main(String[] args) {
        //do nothing
        if (args.length > 0) {
            for (int i = 0; i <args.length; i++) {
                System.out.printf("Argument[%d]: %s ", i, args[i]);
            }
            System.out.printf("%n");
        }
        else  {
            System.out.println("No arguments were passed with this program");
        }
    }
}
