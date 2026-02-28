package org.wwss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MANAGEServer {
    public static void main(Configs configs) {
        boolean keep = true;
        while (keep) {
            System.out.println("1.view all servers\n" +
                    "2.search by ID\n" +
                    "3.search by name\n" +
                    "Q/q.leave");
            switch (Main.sc.nextLine()) {
                case "1":
                    VIEWALL(configs);
                    break;
                case "2":
                    String ID = Main.sc.next();
                    if (tool.isNumeric4(ID)) {
                        PRINT(findById(configs, Integer.parseInt(ID)));
                    }

                    break;
                case "3":
                    String NAME = Main.sc.next();
                    findByName(configs, NAME);
                default:
                    break;
                case "q":
                case "Q":
                    keep = false;
                    break;
            }
        }
    }

    public static void VIEWALL(Configs configs) {
        List<Server> servers = new ArrayList<Server>(configs.servers());
        for (Server server : servers) {
            System.out.println("ID:" + server.getServerID() + "\nNAME:"
                    + server.getServerNAME());
        }
        if (servers.size() > 0) {
            System.out.println("Above are all servers.");
        } else System.out.println("No server");
    }

    public static void PRINT(Server server) {
        if (server == null) System.out.println("Server is null");
        else {
            System.out.println("ID:" + server.getServerID() + "\nNAME:"
                    + server.getServerNAME() + "\nminMemory:"
                    + server.getMinMemoryMb() + "\nmaxMemory:"
                    + server.getMaxMemoryMB());
        }
    }

    public static Server findById(Configs configs, int id) {
        return configs.global().idIndex.get(id);
    }

    public static List<Server> findByName(Configs configs, String name) {
        return configs.global().nameIndex.getOrDefault(name, Collections.emptyList());
    }

    public static void edit(Configs configs, Server server, Scanner sc) {
        if(server == null) System.out.println("Server is null");
        else {
            System.out.println("ID:" + server.getServerID());
            System.out.println("NAME:(enter to pass)");
            String name = sc.next();
            if (name.equals("\n") == false) {
                server.setServerNAME(name);
            }
            System.out.println("Type:(1/2)");
            int Type = Integer.parseInt(sc.next());

            if (Type == 1 || Type == 2) {
                server.setServerType(Type);
            } else {
                System.out.println("no change");
            }
            configs.global().updateServer(server.getServerID(), server);
        }
    }
}
