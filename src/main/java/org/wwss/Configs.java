package org.wwss;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configs {
    private Global global;
    private List<Server> servers = new ArrayList<>();
    
    public static class Global {
        private int maxMemoryMb;
        private int minMemoryMb;
        private String javaPath;
        private int serverPoint;

        public transient Map<Integer, Server> idIndex = new HashMap<>();
        public transient Map<String, List<Server>> nameIndex = new HashMap<>();
        
        // Getters
        public int minMemoryMb() { return minMemoryMb; }
        public int maxMemoryMb() { return maxMemoryMb; }
        public String javaPath() { return javaPath; }
        public int serverPoint() { return serverPoint; }

        //Setters
        public void setminMemoryMb(int minMemoryMb) { this.minMemoryMb = minMemoryMb; }
        public void setMaxMemoryMb(int maxMemoryMb) { this.maxMemoryMb = maxMemoryMb; }
        public void setJavaPath(String javaPath) { this.javaPath = javaPath; }
        public void setServerPoint(int serverPoint) { this.serverPoint = serverPoint; }
        public void updateServer(int ID,Server server) {
            Server s = idIndex.get(ID);
            if(s==null) {
                s = server;
            }
            else {
                System.out.println("not found");
            }
        }




    }

    public Global global() { return global; }
    public java.util.List<Server> servers() { return servers; }

    public int minMemoryMb() { return global != null ? global.minMemoryMb : -1; }
    public int maxMemoryMb() { return global != null ? global.maxMemoryMb : -1; }
    public String javaPath() { return global != null ? global.javaPath : "java"; }
    public int serverPoint() { return global != null ? global.serverPoint : -1; }


    public void setminMemoryMb(int minMemoryMb) { this.global.minMemoryMb = minMemoryMb; }
    public void setMaxMemoryMb(int maxMemoryMb) { this.global.maxMemoryMb = maxMemoryMb; }
    public void setJavaPath(String javaPath) { this.global.javaPath = javaPath; }
    public void setServerPoint(int serverPoint) { this.global.serverPoint = serverPoint; }
    public void updateServer(Server server) {
        this.servers().add(server);
        global.updateServer(server.getServerID(), server);
        global.idIndex.put(server.getServerID(), server);
        global.nameIndex
                .computeIfAbsent(server.getServerNAME(), k -> new ArrayList<>())
                .add(server);
    }

    public void rebuildIndex() {

        if (global.idIndex == null) global.idIndex = new HashMap<>();
        if (global.nameIndex == null) global.nameIndex = new HashMap<>();

        global.idIndex.clear();
        global.nameIndex.clear();

        if (servers == null) return;

        for (Server server : servers) {

            global.idIndex.put(server.getServerID(), server);

            global.nameIndex
                    .computeIfAbsent(server.getServerNAME(), k -> new ArrayList<>())
                    .add(server);
        }
    }
}

