/*
 * A simple Todo List Manager using basic Java constructs.
 * This program allows users to add new todos and show the list of todos using commands.
 */

 import java.util.*;
 import java.io.*;
 
 public class Todo {
     
     private static List<String> todos = new ArrayList<>();
     private static final String FILE_NAME = "todos.txt";
     private static final String ANSI_RESET = "\u001B[0m";
     private static final String ANSI_RED = "\u001B[31m";
     private static final String ANSI_GREEN = "\u001B[32m";
     private static final String ANSI_YELLOW = "\u001B[33m";
     private static final String ANSI_BLUE = "\u001B[34m";
     private static final String ANSI_CYAN = "\u001B[36m";
     
     public static void main(String[] args) {
         loadTodosFromFile();
         Scanner scanner = new Scanner(System.in);
         String command;
         
         while (true) {
             System.out.println(ANSI_CYAN + "Enter a command (add/show/exit): " + ANSI_RESET);
             command = scanner.nextLine().trim().toLowerCase();
             System.out.println(command);
             switch (command) {
                 case "add":
                     addTodo(scanner);
                     break;
                 case "show":
                     System.out.println("calling showTodos");
                     showTodos();
                     break;
                 case "exit":
                     System.out.println(ANSI_RED + "Exiting the Todo List Manager." + ANSI_RESET);
                     saveTodosToFile();
                     scanner.close();
                     return;
                 default:
                     System.out.println(ANSI_YELLOW + "Invalid command. Please enter 'add', 'show', or 'exit'." + ANSI_RESET);
             }
         }
     }
     
     private static void addTodo (Scanner scanner) {
         clearScreen();
         System.out.println(ANSI_BLUE + "Enter a new todo: " + ANSI_RESET);
         String todo = scanner.nextLine().trim();
         if (!todo.isEmpty()) {
             todos.add(todo);
             System.out.println(ANSI_GREEN + "Todo added: " + todo + ANSI_RESET);
         } else {
             System.out.println(ANSI_RED + "Todo cannot be empty." + ANSI_RESET);
         }
     }
     
     private static void showTodos() {
         clearScreen();
         System.out.println("Calling showTodos method");
         if (todos.isEmpty()) {
             System.out.println(ANSI_RED + "No todos available." + ANSI_RESET);
         } else {
             System.out.println(ANSI_CYAN + "Your todos:" + ANSI_RESET);
             for (int i = 0; i < todos.size(); i++) {
                 System.out.println(ANSI_GREEN + (i + 1) + ". " + todos.get(i) + ANSI_RESET);
             }
             System.out.println(ANSI_CYAN + "End of list." + ANSI_RESET);
         }
     }
     
     private static void clearScreen() {
         System.out.print("\033[H\033[2J");
         System.out.flush();
     }
 
     private static void loadTodosFromFile() {
         try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
             String line;
             while ((line = reader.readLine()) != null) {
                 todos.add(line);
             }
         } catch (IOException e) {
             System.out.println(ANSI_RED + "Error loading todos from file." + ANSI_RESET);
         }
     }
 
     private static void saveTodosToFile() {
         try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) { // Open file in append mode
             for (String todo : todos) {
                 writer.write(todo);
                 writer.newLine();
             }
         } catch (IOException e) {
             System.out.println(ANSI_RED + "Error saving todos to file." + ANSI_RESET);
         }
     }
 }
