package org.wwss;

import java.io.IOException;

public class Run {
    public static void start(int ID) throws IOException {
        Configs configs = ConfigLoader.load();
        Server target = null;
        for(Server s : configs.getservers()){
            if(s.getServerID() == ID){
                target = s;
            }
        }
        String javaCmd = "java",min = "Xms" + target.getMinMemoryMb() + "M",max = "Xmx" + target.getMaxMemoryMB() + "M",jar = "-jar",Path = tool.jarDir().toString()+ target.getServerID(),file = target.getServerType();
    }

}
