package org.wwss;

import java.io.IOException;

public class ADDServer {
    public static void create(Configs configs)throws IOException {
        Server addServer = new Server(configs.global().serverPoint(), "serverNAME", 0, 0, 0);
        System.out.println("enter server name");
        addServer.setServerNAME(Main.sc.nextLine().trim());
        System.out.println("""
        enter server type
        1.vanilla
        2.fabric""");

        int serverType;

        while (true) {
            String input = Main.sc.nextLine().trim();

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
            String minn = Main.sc.next(),maxx = Main.sc.next();
            if(tool.isNumeric4(minn) && tool.isNumeric4(maxx)){
                int min = Integer.parseInt(minn),max = Integer.parseInt(maxx);
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
            else System.out.println("invalid input");
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
