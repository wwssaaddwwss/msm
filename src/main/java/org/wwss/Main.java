package org.wwss;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        Configs configs = ConfigLoader.load();

        if (configs == null) {
            System.err.println("Error: Failed to load configuration. Please check your configs.conf file.");
            System.exit(1);
        }

        System.out.println("config loaded");




        while (true) {
            System.out.println(
                    "1.add a server\n" +
                    "2.manage all servers\n" +
                    "3.run a server\n" +
                    "Q/q.leave");
            switch (sc.nextLine()){
                case "1":
                    ADDServer.create(configs);
                    break;
                case "2":
                    MANAGEServer.manage(configs,sc);
                    break;
                case "3":
                    System.out.println("enter the server ID");
                    Run.start(sc.nextInt());
                default:
                    break;
                case "Q":
                case "q":
                    System.exit(0);
                    break;
            }
        }
    }
}

