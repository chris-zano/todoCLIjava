package com.example.java;

import java.io.*;
import java.lang.StringBuilder;

public class Add {

    private String command;
    private String startOn;
    private String dueBy;
    private String task;

    private static final String TASK_FILE_PATH = "files/tasks.txt";
    private static final String UNIQUE_ID_PATH = "files/uid.txt";
    public Add(String[] args) {
        if (args.length > 0 && args[0].equals("add") ){
            this.command = args[0];
            for (int i = 1; i < args.length; i++) {
                if (args[i].equals("--today")) {
                    this.startOn = args[i+1];
                    break;
                }
            }
            for (int i = 1; i < args.length; i++) {
                if (args[i].equals("--due")) {
                    this.dueBy = args[i+1];
                    break;
                }
            }
            for (int i = 1; i < args.length; i++) {
                if (args[i].equals("--task")) {
                    this.task = args[i+1];
                    break;
                }
            }
        }
    }

    public void DisplayTaskConfig(StringBuilder sb) {

        if (sb.isEmpty()) {
            System.out.printf("Command => %s\n", this.command);
            System.out.printf("Starts on => %s\n", this.startOn);
            System.out.printf("Due by => %s\n", this.dueBy);
            System.out.printf("For the task => %s\n", this.task);
        }
        else  {
            System.out.printf("%s", sb);
        }
    }

    public StringBuilder StructureTaskLanguage() {
        int uniqueIndex = Add.GetCurrentUniqueIndex();
        StringBuilder sbTask = new StringBuilder();
        sbTask.append("ID:")
                .append(uniqueIndex)
                .append(" ")
                .append("{")
                .append(this.task)
                .append("} ")
                .append("which started on ")
                .append("{")
                .append(this.startOn)
                .append("} ")
                .append("and is due on ")
                .append("{")
                .append(this.dueBy)
                .append("}")
                .append("\n");

        return sbTask;
    }

    public int SaveToFile(StringBuilder task) {
        try(
                FileWriter fWriter = new FileWriter(TASK_FILE_PATH, true);
                BufferedWriter bfWriter = new BufferedWriter(fWriter)
                ){
            bfWriter.append(task);
        }catch(IOException e) {
            e.printStackTrace();
            return -1;
        }

        return 1;
    }

    private static int GetCurrentUniqueIndex() {
        int uniqueIndex = 0;
        try(
                FileReader fReader = new FileReader(UNIQUE_ID_PATH);
                BufferedReader bReader = new BufferedReader(fReader)
                ){
            while (true) {
                String lineFeed = bReader.readLine();

                if (lineFeed == null) {
                    break;
                }
                else {
                    uniqueIndex = Integer.parseInt(lineFeed);
                    uniqueIndex += 1;

                }
            }
        }catch(IOException e) {
            e.printStackTrace();
            return -1;
        }

        try(
                FileWriter fWriter = new FileWriter(UNIQUE_ID_PATH)
                ){
            fWriter.write(String.valueOf(uniqueIndex));
        }catch(IOException e) {
            e.printStackTrace();
        }

        return uniqueIndex;
    }
}
