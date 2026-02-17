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

        public static void printOptions() {
            ServerType[] types = values();
            for (int i = 0; i < types.length; i++) {
                System.out.println((i + 1) + ". " + types[i]);
            }
        }

        public static ServerType fromIndex(int index) {
            if (index < 1 || index > values().length) {
                throw new IllegalArgumentException("Invalid ServerType index");
            }
            return values()[index - 1];
        }
    }

    /* ---------- Server Config ---------- */

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

    /* ---------- Public API ---------- */

    public static ServerConfig createFromInput(Configs defaults) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Server ID: ");
        int id = readInt(sc);

        System.out.print("Server Name: ");
        String name = sc.nextLine();

        System.out.print("Server Directory: ");
        String directory = sc.nextLine();

        System.out.println("Server Type:");
        ServerType.printOptions();
        System.out.print("> ");
        ServerType type = ServerType.fromIndex(readInt(sc));

        int minMemory = readMemory(
                sc,
                "Min Memory MB",
                defaults.minMemoryMb()
        );

        int maxMemory = readMemory(
                sc,
                "Max Memory MB",
                defaults.maxMemoryMb()
        );

        List<String> jvmArgs = buildJvmArgs(minMemory, maxMemory);
        String jar = defaultJar(type);

        return new ServerConfig(
                id,
                name,
                directory,
                type,
                minMemory,
                maxMemory,
                jar,
                jvmArgs
        );
    }

    /* ---------- Helpers ---------- */

    private static int readMemory(Scanner sc, String label, int def) {
        System.out.print(label + " (D/d = default " + def + "): ");
        String input = sc.nextLine().trim();

        if (input.equalsIgnoreCase("d")) {
            return def;
        }
        return Integer.parseInt(input);
    }

    private static int readInt(Scanner sc) {
        int v = Integer.parseInt(sc.nextLine().trim());
        return v;
    }

    private static List<String> buildJvmArgs(int min, int max) {
        List<String> args = new ArrayList<>();
        args.add("-Xms" + min + "M");
        args.add("-Xmx" + max + "M");
        return args;
    }

    private static String defaultJar(ServerType type) {
        return switch (type) {
            case PAPER -> "paper.jar";
            case FABRIC -> "fabric-server.jar";
            case FORGE -> "forge.jar";
            case VANILLA -> "minecraft_server.jar";
        };
    }
}
