package org.wwss;


public class Configs {
    private Global global;
    private java.util.List<Object> servers;
    
    public static class Global {
        private int maxMemoryMb;
        private int minMemoryMb;
        private String javaPath;
        
        // Getters
        public int maxMemoryMb() { return maxMemoryMb; }
        public int minMemoryMb() { return minMemoryMb; }
        public String javaPath() { return javaPath; }
        
        // Setters
        public void setMaxMemoryMb(int maxMemoryMb) { this.maxMemoryMb = maxMemoryMb; }
        public void setMinMemoryMb(int minMemoryMb) { this.minMemoryMb = minMemoryMb; }
        public void setJavaPath(String javaPath) { this.javaPath = javaPath; }
    }
    
    // Getters
    public Global global() { return global; }
    public java.util.List<Object> servers() { return servers; }
    
    // Setters
    public void setGlobal(Global global) { this.global = global; }
    public void setServers(java.util.List<Object> servers) { this.servers = servers; }
    
    // Convenience getters that delegate to global
    public int maxMemoryMb() { return global != null ? global.maxMemoryMb : 0; }
    public int minMemoryMb() { return global != null ? global.minMemoryMb : 0; }
    public String javaPath() { return global != null ? global.javaPath : null; }
}

