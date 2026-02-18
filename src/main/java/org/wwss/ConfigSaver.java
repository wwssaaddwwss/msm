package org.wwss;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class ConfigSaver {
    
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    

    public static void save(Configs configs) throws IOException {
        Path path = AppPaths.jarDir().resolve("configs.conf");
        
        try (FileWriter writer = new FileWriter(path.toFile())) {
            GSON.toJson(configs, writer);
            System.out.println("Configs saved to " + path.toAbsolutePath());
        }
    }
    


}