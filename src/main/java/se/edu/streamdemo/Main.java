package se.edu.streamdemo;

import se.edu.streamdemo.data.Datamanager;
import se.edu.streamdemo.task.Deadline;
import se.edu.streamdemo.task.Task;

import java.util.ArrayList;

import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to Task manager (using streams)");
        Datamanager dataManager = new Datamanager("./data/data.txt");
        ArrayList<Task> tasksData = dataManager.loadData();

        System.out.println("Printing all data ...");
        printAllData(tasksData);
        printDataWithStream(tasksData);

        System.out.println("Printing deadlines ...");
        printDeadlines(tasksData);
        printDeadlinesUsingStream(tasksData);

        System.out.println("Total number of deadlines: " + countDeadlines(tasksData));
        System.out.println("Total number of deadlines: " + countDeadlinesUsingStream(tasksData));

        ArrayList<Task> filteredList = filterTaskByString(tasksData, "11");
        printAllData(filteredList);
    }

    private static int countDeadlines(ArrayList<Task> tasksData) {
        int count = 0;
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                count++;
            }
        }
        return count;
    }

    public static void printAllData(ArrayList<Task> tasksData) {
        System.out.println("Printing data with iteration...");
        for (Task t : tasksData) {
            System.out.println(t);
        }
    }

    public static void printDataWithStream(ArrayList<Task> tasks) {
        System.out.println("Printing data with stream...");
        tasks.stream()                         // creating a stream
                .forEach(System.out::println); // terminal operator

    }
    public static void printDeadlines(ArrayList<Task> tasksData) {
        System.out.println("Printing deadlines with iteration...");
        for (Task t : tasksData) {
            if (t instanceof Deadline) {
                System.out.println(t);
            }
        }
    }

    public static void printDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Printing deadlines using stream...");
        tasks.stream()
                .filter((t) -> t instanceof Deadline)   // lambda function
                .sorted((t1, t2) -> t1.getDescription().compareToIgnoreCase(t2.getDescription()))
                .forEach(System.out::println);
    }

    public static int countDeadlinesUsingStream(ArrayList<Task> tasks) {
        System.out.println("Counting deadlines using stream...");
        int count = (int) tasks.stream()
                .filter((t) -> t instanceof Deadline)   // lambda function
                .count();
        return count;
    }

    public static ArrayList<Task> filterTaskByString(ArrayList<Task> tasks, String filterString) {
        System.out.println("Filtering Task By String...");
        ArrayList<Task> result = (ArrayList<Task>) tasks.stream()
                .filter((t) -> t.getDescription().contains(filterString))
                .collect(toList());
        return result;
    }
}
