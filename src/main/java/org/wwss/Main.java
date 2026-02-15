package org.wwss;


import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
            Scanner sc = new Scanner(System.in);

            Configs configs = ConfigLoader.load();
            System.out.println("config loaded");




            while (true) {
                System.out.println("1.add a server\n" +
                        "Q/q.leave");
                switch (sc.nextLine()){
                    case "1":
                        break;


                    case "Q":
                    case "q":
                        System.exit(0);
                }

            }


    }
}

