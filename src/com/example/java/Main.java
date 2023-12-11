package com.example.java;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entry point for the program.
 * <p>This program allows users to <i>add</i>, <i>list</i>, <i>check</i> and <i>delete</i> to-do activities</p>
 * <p>This program contains Objects and methods for parsing arguments to the program</p>
 * <ul>
 *     <li>You can run this in interactive mode, where you do not need to call "com.example.java.Main **argv"</li>
 *     <li>You can parse a file containing valid commands as argument to this program " com.example.java.Main file</li>
 *     <li>You can also parse commands as arguments to the program</li>
 * </ul>
 */
public class Main {
    //file where to-do activities are saved
    public static final String TASK_FILE_PATH = "files/tasks.txt";
    //file where unique ids are saved
    public static final String UNIQUE_ID_PATH = "files/uid.txt";

    /**
     * this is the main calling method of the program
     * @param args - arguments parsed to the program (commands / file)
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            File file =  new File(args[0]);

            //check if argument is a file
            if (file.isFile()) {
                executeCommandsFromFile(file); // execute commands in the file
            }
            else {
                System.out.printf("%s is not a file", args[0]); // error message
            }
        }

        else if (args.length > 1) mapCommands(args); // commands are parsed as arguments instead

        else {
            //interactive mode
            String[] arguments;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.printf("%s ", "$>");
                String line = scanner.nextLine();

                if ("exit".equalsIgnoreCase(line.trim())) {
                    break;
                }
                else {
                    arguments = parseArguments(line);
                    mapCommands(arguments);
                }


            }
        }

    }

    /**
     * This method uses Scanner class to open and read a file line by line
     * Each line in the file is a command for the program
     * This command is parsed to the program
     * The fileScanner pointer then moves to the next line and repeats the process
     * @param file - the file containing a list of valid commands
     */
    private static void executeCommandsFromFile(File file) {
        try(Scanner fileScanner = new Scanner(file)) {
            while(fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] arguments = parseArguments(line);
                    mapCommands(arguments);
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method maps the first command to its respective function
     * @param argv - a tokenized array of string arguments
     */
    private static void mapCommands(String[] argv) {
        switch (argv[0]) {
            case "add":
                Add newTask = new Add(argv);
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
            case "check", "delete":
                if (argv.length != 2) {
                    System.out.println("Usage < check / delete \"ID:***\">");
                }
                else {
                    if (argv[1].contains("ID:")) {
                        DeleteTask.deleteTask(argv[1]);
                    } else if (argv[1].equals("--all")) {
                        DeleteTask.deleteTask("all");
                    } else {
                        System.out.println("Usage < check / delete  \"ID:***\">");
                    }
                }
                break;

            case "list":
                Task_List taskList = new Task_List(argv);
                String[] tasks = taskList.ListTasks();
                for (String todo: tasks) {
                    System.out.println(todo);
                }
                break;
            default:
                System.out.println("Wrong instruction");
        }
    }

    /**
     * This method tokenizes a line feed from scanner.nextLine()
     * @param line - line feed from scanner.nextLine()
     * @return - tokenized array of strings
     */
    private static String[] parseArguments(String line) {
        // Regular expression to match either a quoted string or a non-whitespace sequence
        Pattern pattern = Pattern.compile("\"([^\"]*)\"|\\S+");
        Matcher matcher = pattern.matcher(line);

        // List to store the matched arguments
        List<String> argumentsList = new ArrayList<>();

        // Find all matches and add them to the list
        while (matcher.find()) {
            String match = matcher.group(1); // Quoted string or non-whitespace sequence
            if (match == null) {
                match = matcher.group(); // Non-whitespace sequence
            }
            argumentsList.add(match);
        }

        // Convert the list to an array
        return argumentsList.toArray(new String[0]);
    }

}