package org.wwss;


public class Configs {
    private Global global;
    private java.util.List<Server> servers;
    
    public static class Global {
        private int maxMemoryMb;
        private int minMemoryMb;
        private String javaPath;
        private int serverPoint;
        
        // Getters
        public int minMemoryMb() { return minMemoryMb; }
        public int maxMemoryMb() { return maxMemoryMb; }
        public String javaPath() { return javaPath; }
        public int serverPoint() { return serverPoint; }

        //atters
        public void setminMemoryMb(int minMemoryMb) { this.minMemoryMb = minMemoryMb; }
        public void setMaxMemoryMb(int maxMemoryMb) { this.maxMemoryMb = maxMemoryMb; }
        public void setJavaPath(String javaPath) { this.javaPath = javaPath; }
        public void setServerPoint(int serverPoint) { this.serverPoint = serverPoint; }


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
    

}

