package com.example.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class for handling list operations
 */

public class Task_List {

    private int upperLimit;
    private int lowerLimit;
    private String indexOf;

    public Task_List(String[] args) {
        if (args[0].equals("list") && args.length > 1) {
            for (int i = 1; i < args.length; i++) {
                if (args[i].equals("--from")) {
                    this.upperLimit = Integer.parseInt(args[i+1]); // set upper limit
                }
                if (args[i].equals("--to")) {
                    this.lowerLimit = Integer.parseInt(args[i+1]); // set lower limit
                }
                if (args[i].equals("--indexOf")) {
                    this.indexOf = args[i+1];
                }
                if (args[i].equals("--all")){
                    this.indexOf = "ID";
                }
            }
        }
        else {
            this.upperLimit = 0;
            this.lowerLimit = 0;
            this.indexOf = "ID";
        }

    }

    /**
     * List tasks , accepts flags
     * <p>flags control which tasks to print</p>
     * @return - a string array of tasks
     */
    public  String[] ListTasks() {
        String[] tasksArray = new String[0];
        if (this.upperLimit == 0) {
            try(
                    FileReader fReader = new FileReader(Main.TASK_FILE_PATH);
                    BufferedReader bReader = new BufferedReader(fReader)
            ){
                List<String> tasks = new ArrayList<>();

                String lineFeed;
                while((lineFeed = bReader.readLine()) != null) {
                    tasks.add(lineFeed);
                }

                tasksArray = tasks.toArray(new String[0]);

            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        if (this.upperLimit > 0 ) {
            if (this.lowerLimit == 0) {
                try(
                        FileReader fReader = new FileReader(Main.TASK_FILE_PATH);
                        BufferedReader bReader = new BufferedReader(fReader)
                ){
                    List<String> tasks = new ArrayList<>();

                    String lineFeed;
                    boolean startReading = false;
                    while((lineFeed = bReader.readLine()) != null) {
                        String[] tokens =  lineFeed.split("\\s+");
                        for (String token: tokens) {
                            if (token.contains("ID:"+ upperLimit)) {
                                startReading = true;
                                break;
                            }
                        }
                        if (startReading) {
                            tasks.add(lineFeed);
                        }
                    }

                    tasksArray = tasks.toArray(new String[0]);

                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
            else if (this.lowerLimit > 0) {
                try(
                        FileReader fReader = new FileReader(Main.TASK_FILE_PATH);
                        BufferedReader bReader = new BufferedReader(fReader)
                ){
                    List<String> tasks = new ArrayList<>();

                    String lineFeed;
                    boolean startReading = false;
                    while((lineFeed = bReader.readLine()) != null) {
                        String[] tokens =  lineFeed.split("\\s+");
                        for (String token: tokens) {
                            if (token.contains("ID:"+ this.upperLimit)) {
                                startReading = true;
                                break;
                            }

                        }
                        if (startReading) {
                            tasks.add(lineFeed);
                            if (lineFeed.contains("ID:"+ this.lowerLimit)){
                                startReading = false;
                            }
                        }
                    }

                    tasksArray = tasks.toArray(new String[0]);

                }catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try(
                    FileReader fReader = new FileReader(Main.TASK_FILE_PATH);
                    BufferedReader bReader = new BufferedReader(fReader)
            ){
                List<String> tasks = new ArrayList<>();

                String lineFeed;
                boolean startReading;
                while((lineFeed = bReader.readLine()) != null) {
                    startReading = lineFeed.contains(this.indexOf);
                    if (startReading) {
                        tasks.add(lineFeed);
                    }
                }

                tasksArray = tasks.toArray(new String[0]);

            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        return tasksArray;
    }
}