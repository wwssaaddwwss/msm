package org.wwss;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ADDServer {

    public enum ServerType {
        PAPER,
        FABRIC,
        FORGE,
        VANILLA;

        public static ServerType fromIndex(int index) {
            // index 1 ~ 4 對應 enum ordinal 0 ~ 3
            if (index < 1 || index > values().length)
                throw new IllegalArgumentException("Invalid index for ServerType");
            return values()[index - 1];
        }

        public static void printOptions() {
            ServerType[] types = values();
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + " -> " + types[i]);
            }
        }
    }

    public record ServerConfig(
            int id,
            String name,
            String directory,
            ServerType type,
            int minMemoryMb,
            int maxMemoryMb,
            String jar,
            List<String> jvmArgs
    ) {}

    public static ServerConfig addServer(int defaultMin, int defaultMax) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Server ID:");
        int id = sc.nextInt();
        sc.nextLine(); // 消耗換行符

        System.out.println("Enter Server Name:");
        String name = sc.nextLine();

        System.out.println("Enter Server Directory:");
        String directory = sc.nextLine();

        System.out.println("Select Server Type:");
        ServerType.printOptions();
        int typeIndex = sc.nextInt();
        sc.nextLine(); // 消耗換行符
        ServerType type = ServerType.fromIndex(typeIndex);

        System.out.println("Enter Server Min Memory MB (D/d for default " + defaultMin + "):");
        String minInput = sc.nextLine();
        int minMemoryMb = (minInput.equalsIgnoreCase("D")) ? defaultMin : Integer.parseInt(minInput);

        System.out.println("Enter Server Max Memory MB (D/d for default " + defaultMax + "):");
        String maxInput = sc.nextLine();
        int maxMemoryMb = (maxInput.equalsIgnoreCase("D")) ? defaultMax : Integer.parseInt(maxInput);

        List<String> jvmArgs = new ArrayList<>();
        jvmArgs.add("-Xms" + minMemoryMb + "M");
        jvmArgs.add("-Xmx" + maxMemoryMb + "M");

        String jar = switch (type) {
            case PAPER -> "paper.jar";
            case FABRIC -> "fabric-server.jar";
            case FORGE -> "forge.jar";
            case VANILLA -> "minecraft_server.jar";
        };

        System.out.println("Server created: " + name + " (" + type + ")");
        return new ServerConfig(id, name, directory, type, minMemoryMb, maxMemoryMb, jar, jvmArgs);
    }
}
