package com.example.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class newt {
    public static void main(String[] args) {
        //do nothing
        if (args.length > 0) {
            Create cTask = new Create();
            LocalDateTime ldtNow = LocalDateTime.now();
            cTask.id = Integer.parseInt(args[0]);
            cTask.dfDateCreated = ldtNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            cTask.dfTimeCreated = ldtNow.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

            cTask.display();
        }
        else  {
            System.out.println("No arguments were passed with this program");
        }

    }
}
