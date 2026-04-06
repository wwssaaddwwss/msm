package org.wwss;

import java.io.IOException;

public class Run {
    public static void start(int ID) throws IOException {
        Configs configs = ConfigLoader.load();
        Server target = configs.getservers().get(ID);
        String javaCmd = "java -Xms" + target.getMinMemoryMb() + "M -Xmx" + target.getMaxMemoryMB() + "M -jar " + tool.jarDir().toString()  + "Servers\\" + target.getServerID() + "\\server.jar";
        System.out.println(javaCmd + "\n");
    }

}
