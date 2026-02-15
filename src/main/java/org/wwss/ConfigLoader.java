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

        Configs configs ;

        Path path = AppPaths.jarDir().resolve("configs.conf");
        try (InputStream in = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("configs.conf")) {

            if (in == null) {
                throw new IllegalArgumentException("Resource not found: configs.conf");
            }

            if (Files.notExists(path)) {
                Files.copy(in, path);
                System.out.println("configs.conf created.");
            } else {
                System.out.println("configs.conf already exists.");
            }
        }

        configs = GSON.fromJson(new InputStreamReader(new FileInputStream(path.toFile())), Configs.class);

        return configs;
    }
}
