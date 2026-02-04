package org.wwss;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigLoader {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private static final String FILE_NAME = "configs.conf";

    private static Configs defaultConfig() {
        return new Configs(
                4096,   // maxMemoryMb
                1024,   // minMemoryMb
                "java"  // javaPath
        );
    }

    public static Configs load() throws IOException {
        Path configPath = AppPaths.jarDir().resolve(FILE_NAME);
        System.out.println("configPath: " + configPath);

        if (!Files.exists(configPath)) {
            Configs def = defaultConfig();
            save(configPath, def); // 第一次啟動才會生成
            return def;
        }

        try (Reader reader = Files.newBufferedReader(configPath)) {
            return GSON.fromJson(reader, Configs.class);
        }
    }

    private static void save(Path path, Configs config) throws IOException {
        try (Writer writer = Files.newBufferedWriter(path)) {
            GSON.toJson(config, writer);
        }
    }
}
