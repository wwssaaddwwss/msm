package org.wwss;

    public record Configs(
        int maxMemoryMb,
        int minMemoryMb,
        String javaPath
) {
}

