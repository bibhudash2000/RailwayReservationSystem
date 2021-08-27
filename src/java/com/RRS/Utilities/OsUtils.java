package com.RRS.Utilities;


public final class OsUtils {

    private static String OS = null;

    public static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }

    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

    public static boolean isUnix() {
        return getOsName().startsWith("Unix");
    }
    
    public static boolean isAndroid() {
        return getOsName().startsWith("Android");
    }
    
    public static void main(String[] args) {
        System.out.println(getOsName());
    }

}
