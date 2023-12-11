package com.example.java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteTask {

    public static void deleteTask(String id) {
        List<String> tasks = new ArrayList<>();
        if (id.equals("all")) {
            try(
                    FileWriter fWriter = new FileWriter(Main.TASK_FILE_PATH);
                    BufferedWriter bWriter = new BufferedWriter(fWriter)
            ) {
                bWriter.write("");
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try(
                    FileReader fReader = new FileReader(Main.TASK_FILE_PATH);
                    BufferedReader bReader = new BufferedReader(fReader)
            ) {
                String line;
                while((line = bReader.readLine()) != null){
                    tasks.add(line);
                }

                tasks.removeIf(task -> task.contains(id));

            }catch(IOException e) {
                e.printStackTrace();
            }

            try(
                    FileWriter fWriter = new FileWriter(Main.TASK_FILE_PATH);
                    BufferedWriter bWriter = new BufferedWriter(fWriter)
            ) {
                for (String task: tasks) {
                    bWriter.write(task);
                    bWriter.newLine();
                }
            }catch(IOException e) {
                e.printStackTrace();
            }

        }
    }


}