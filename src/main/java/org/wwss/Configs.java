package org.wwss;

import java.util.List;

public record Configs(
        int maxMemoryMb,
        int minMemoryMb,
        String javaPath,
        List server
) {
}

