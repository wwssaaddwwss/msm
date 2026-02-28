package org.wwss;

public class tool {

    //    https://blog.csdn.net/mryang125/article/details/113146057
    public static boolean isNumeric4(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }

}
