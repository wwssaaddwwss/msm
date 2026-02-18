package org.wwss;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            Configs configs = ConfigLoader.load();
                    
            if (configs == null) {
                System.err.println("Error: Failed to load configuration. Please check your configs.conf file.");
                System.exit(1);
            }
                    
            System.out.println(configs.javaPath().toString());
            System.out.println("config loaded");




            while (true) {
                System.out.println(
                        "1.add a server\n" +
                        "2.view all servers\n" +
                        "Q/q.leave");
                switch (sc.nextLine()){
                    case "1":
                        ADDServer.create(configs,sc);
                        break;
                    case "2":
                        MANAGEServer.main(configs,sc);
                        break;
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

