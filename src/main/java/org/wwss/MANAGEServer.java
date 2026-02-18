package org.wwss;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MANAGEServer {
    public static void main(Configs configs, Scanner sc) {
        System.out.println("hello");
        while (true) {
            switch (sc.nextInt()) {
                case 1:
                    VIEWALL(configs, sc);
                    break;
                case 2:
                    break;
                default:
                    break;
                case 'q':
                case 'Q':
                    break;
            }
        }
    }
    public static void VIEWALL(Configs configs, Scanner sc) {
        List<Server> servers = new ArrayList<Server>(configs.servers());
        for (Server server : servers) {
            System.out.println("ID:" + server.getServerID() + "\nNAME:"
            + server.getServerNAME());
        }
        System.out.println("Above are all servers.");

    }
}
