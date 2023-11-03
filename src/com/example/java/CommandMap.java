package com.example.java;


public class CommandMap {

    public static void main(String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "add":
                    Add newTask = new Add(args);
//                    newTask.DisplayTaskConfig();
                    StringBuilder task =  newTask.StructureTaskLanguage();
                    newTask.DisplayTaskConfig(task);
                    int isSuccess = newTask.SaveToFile(task);
                    if (isSuccess == 1) {
                        System.out.println("File saved Successfully!!");
                    }
                    else {
                        System.out.println("File save Failed!");
                    }
                    break;
                case "check":
                    System.out.println("check");
                    for (String arg : args) {
                        System.out.println(arg);
                    }
                    break;
                case "delete":
                    System.out.println("delete");
                    for (String arg : args) {
                        System.out.println(arg);
                    }
                    break;
                case "list":
                    System.out.println("list");
                    for (String arg : args) {
                        System.out.println(arg);
                    }
                    break;
                default:
                    System.out.println("Wrong instruction");
            }
        } else {
            System.out.println("No arguments passed with this program");
        }

    }
}
