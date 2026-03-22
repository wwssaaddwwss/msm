package org.wwss;

import java.io.IOException;

import static org.wwss.Main.sc;

public class ADDServer {
    public static void create(Configs configs)throws IOException {
        Server addServer = new Server(configs.global().serverPoint(), "serverNAME", 0, 0, 0);
        System.out.println("enter server name");
        addServer.setServerNAME(sc.nextLine().trim());
        System.out.println("""
        enter server type
        1.vanilla
        2.fabric""");

        int serverType;

        while (true) {
            String input = sc.nextLine().trim();

            try {
                serverType = Integer.parseInt(input);

                if (serverType == 1 || serverType == 2) {
                    addServer.setServerType(serverType);
                    break;
                } else {
                    System.out.println("please enter 1 or 2");
                }

            } catch (NumberFormatException e) {
                System.out.println("invalid input, enter number 1 or 2");
            }
        }

        boolean check = true;
        while(check){
            System.out.println("enter server memory(mb, min&max)");

            String line = sc.nextLine();
            String[] parts = line.trim().split("\\s+");

            if (parts.length != 2) {
                System.out.println("please enter two numbers");
                continue;
            }

            int min = Integer.parseInt(parts[0]);
            int max = Integer.parseInt(parts[1]);

            System.out.println(min + " " + max);
            if(min==-2 || max == -2) System.out.println("invalid input");
            if (min == -1||max == -1) {check = false;
            }
            else if(max>=min){
                addServer.setMinMemoryMb(min);
                addServer.setMaxMemoryMB(max);
                check = false;
            }else{
                System.out.println("maxMemory must be greater than minMemory");
            }
        }

        configs.updateServer(addServer);
        configs.setServerPoint(configs.global().serverPoint()+1);
        try {
            ConfigSaver.save(configs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
