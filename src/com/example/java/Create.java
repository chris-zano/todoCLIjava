package com.example.java;

public class Create extends Base{

    public Create(){
        super();
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

    public void display() {
        System.out.printf("The id of the task is: %d%n", this.id);
        System.out.printf("The date the task was created is: %s%n", this.dfDateCreated);
        System.out.printf("The time the task was created is: %s%n", this.dfTimeCreated);
    }
}

