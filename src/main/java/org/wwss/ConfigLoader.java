package org.wwss;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    private static Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();


    public static Configs load() throws IOException {

        Configs configs;

        Path path = AppPaths.jarDir().resolve("configs.conf");

        try {
            configs = GSON.fromJson(new InputStreamReader(new FileInputStream(path.toFile())), Configs.class);
        } catch (Exception e) {
            rebuild();
            configs = GSON.fromJson(new InputStreamReader(new FileInputStream(path.toFile())), Configs.class);
        }

        if (configs == null) {System.err.println("Configs file might be broken.Please check on github.");}

        return configs;
    }
    public static void rebuild() throws IOException {
        Path path = AppPaths.jarDir().resolve("configs.conf");

        try (InputStream in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configs.conf")) {

            if (in == null) {
                throw new IllegalArgumentException("Resource not found: configs.conf");
            }
            Files.deleteIfExists(path);
            Files.copy(in, path);
            System.out.println("configs.conf rebuilded.");
        }
    }
}
