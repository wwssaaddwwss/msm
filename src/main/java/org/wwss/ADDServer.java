package org.wwss;

import java.io.IOException;
import java.util.Scanner;
//
//public class ADDServer {
//
//
//    public enum ServerType {
//        PAPER,
//        FABRIC,
//        FORGE,
//        VANILLA;
//
//        public static void printOptions() {
//            ServerType[] types = values();
//            for (int i = 0; i < types.length; i++) {
//                System.out.println((i + 1) + ". " + types[i]);
//            }
//        }
//
//        public static ServerType fromIndex(int index) {
//            if (index < 1 || index > values().length) {
//                throw new IllegalArgumentException("Invalid ServerType index");
//            }
//            return values()[index - 1];
//        }
//    }
//
//    /* ---------- Server Config ---------- */
//
//    public record ServerConfig(
//            int id,
//            String name,
//            String directory,
//            ServerType type,
//            int minMemoryMb,
//            int maxMemoryMb,
//            String jar,
//            List<String> jvmArgs
//    ) {}
//
//    /* ---------- Public API ---------- */
//
//    public static void ServerConfig createFromInput(Configs defaults,int serverID) {
//        Scanner sc = new Scanner(System.in);
//
//        int id = serverID;
//
//        System.out.print("Server Name: ");
//        String name = sc.nextLine();
//
////        System.out.print("Server Directory: ");
////        String directory = sc.nextLine();
//
//        System.out.println("Server Type:");
//        ServerType.printOptions();
//        System.out.print("> ");
//        ServerType type = ServerType.fromIndex(sc.nextInt());
//
//        int minMemory = readMemory(
//                sc,
//                "Min Memory MB",
//                defaults.minMemoryMb()
//        );
//
//        int maxMemory = readMemory(
//                sc,
//                "Max Memory MB",
//                defaults.maxMemoryMb()
//        );
//
//        List<String> jvmArgs = buildJvmArgs(minMemory, maxMemory);
//        String jar = defaultJar(type);
//
//        return new ServerConfig(
//                id,
//                name,
//                directory,
//                type,
//                minMemory,
//                maxMemory,
//                jar,
//                jvmArgs
//        );
//    }
//
//    private static int readMemory(Scanner sc, String label, int def) {
//        System.out.print(label + " (D/d = default " + def + "): ");
//        String input = sc.nextLine().trim();
//
//        if (input.equalsIgnoreCase("d")) {
//            return def;
//        }
//    }
//
//    private static int readInt(Scanner sc) {
//        int v = Integer.parseInt(sc.nextLine().trim());
//        return v;
//    }
//
//    private static List<String> buildJvmArgs(int min, int max) {
//        List<String> args = new ArrayList<>();
//        args.add("-Xms" + min + "M");
//        args.add("-Xmx" + max + "M");
//        return args;
//    }
//
//    private static String defaultJar(ServerType type) {
//        return switch (type) {
//            case PAPER -> "paper.jar";
//            case FABRIC -> "fabric-server.jar";
//            case FORGE -> "forge.jar";
//            case VANILLA -> "minecraft_server.jar";
//        };
//    }
//}

public class ADDServer {
    public static void create(Configs configs,Scanner sc)throws IOException {
        int serverID = configs.global().serverPoint();
        System.out.println("enter server name");
        String serverNAME = sc.nextLine().trim();
        System.out.println("""
        enter server type
        1.vanilla
        2.fabric
        """);

        int serverType;

        while (true) {
            String input = sc.nextLine().trim();

            try {
                serverType = Integer.parseInt(input);

                if (serverType == 1 || serverType == 2) {
                    break;
                } else {
                    System.out.println("please enter 1 or 2");
                }

            } catch (NumberFormatException e) {
                System.out.println("invalid input, enter number 1 or 2");
            }
        }

        boolean check = true;
        int minMemory = configs.minMemoryMb(), maxMemory = configs.maxMemoryMb();
        while(check){
            System.out.println("enter server memory(mb, min&max)");
            int min = sc.nextInt(),max = sc.nextInt();
            if (min==-1||max==-1) {break;
            }
            else if(max>=min){
                minMemory = min;
                maxMemory = max;
                check = false;
            }else{
                System.out.println("maxMemory must be greater than minMemory");
            }
        }


        configs.servers().add(new Server(serverID, serverNAME, serverType,minMemory,maxMemory));
        configs.setServerPoint(configs.global().serverPoint()+1);
        try {
            ConfigSaver.save(configs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
