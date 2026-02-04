package org.wwss;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AppPaths {

    public static Path jarDir() {
        try {
            return Paths.get(
                    AppPaths.class
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).getParent();
        } catch (Exception e) {
            throw new RuntimeException("can't get path", e);
        }
    }
    
}
