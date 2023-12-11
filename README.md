## To-Do List Application

This Java program provides a simple command-line interface for managing to-do activities. Users can add, list, check, and delete tasks. Additionally, the program supports interactive mode and parsing commands from a file.

## Usage

### Interactive Mode

To run the program in interactive mode, simply execute the following command:

```bash
java com.example.java.Main
```

In interactive mode, you can enter commands directly, and the program will process them until you type `exit` to quit.

### Parsing Commands from a File

You can also provide a file containing valid commands as an argument to the program:

```bash
java com.example.java.Main path/to/commands.txt
```

Make sure the file contains one command per line.

### Command-line Arguments

Alternatively, you can pass commands directly as program arguments:

```bash
java com.example.java.Main add --task "Complete project" --today --due "2023-12-31"
```

Supported Commands:
- `add`: Add a new task.
- `list`: List tasks with optional filtering.
- `check`: Mark a task as completed.
- `delete`: Delete a task by ID or delete all tasks.

## File Paths

The program uses the following file paths:

- Task File: `files/tasks.txt`
- Unique ID File: `files/uid.txt`

Make sure these files and directories exist and have appropriate permissions.

## Examples

### Add a Task

```bash
java com.example.java.Main add --task "Complete project" --today --due "2023-12-31"
```

### List Tasks

```bash
java com.example.java.Main list --from 1 --to 5
```

### Check or Delete a Task by ID

```bash
java com.example.java.Main check "ID:123"
java com.example.java.Main delete "ID:456"
```

### Delete All Tasks

```bash
java com.example.java.Main delete --all
```

## Note

- The program stores tasks in the `files/tasks.txt` file and manages unique IDs in the `files/uid.txt` file.
- Tasks are displayed with their IDs, start dates, and due dates.

Feel free to explore and enhance this to-do list application according to your needs!