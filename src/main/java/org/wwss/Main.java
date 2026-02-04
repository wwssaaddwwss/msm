package org.wwss;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();
    }
    public record Configs(
            int maxMemoryMb,
            int minMemoryMb,
            String javaPath
    ) {
        public static Configs defaultConfig() {
            return new Configs(
                    4096,
                    1024,
                    "java"
            );
        }
    }

}
