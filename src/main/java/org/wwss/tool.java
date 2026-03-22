package org.wwss;

import java.nio.file.Path;
import java.nio.file.Paths;

public class tool {

    //    https://blog.csdn.net/mryang125/article/details/113146057
    public static boolean isNumeric4(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }
    public static Path jarDir() {
        try {
            return Paths.get(
                    tool.class
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
