package org.wwss;

public class Server {

    private int serverID;
    private String serverNAME;
    private int serverType;
    private int minMemoryMb;
    private int maxMemoryMB;

    public Server(int serverID, String serverNAME, int serverType,
                  int minMemoryMb, int maxMemoryMB) {

        this.serverID = serverID;
        this.serverNAME = serverNAME;
        this.serverType = serverType;
        this.minMemoryMb = minMemoryMb;
        this.maxMemoryMB = maxMemoryMB;
    }

    public int getServerID() {
        return serverID;
    }
    public String getServerNAME() {
        return serverNAME;
    }
    public int getServerType() {
        return serverType;
    }
    public int getMinMemoryMb() {
        return minMemoryMb;
    }
    public int getMaxMemoryMB() {
        return maxMemoryMB;
    }

    public void setServerNAME(String serverNAME) {
        this.serverNAME = serverNAME;
    }
    public void setServerType(int serverType) {
        this.serverType = serverType;
    }
    public void setMinMemoryMb(int minMemoryMb) {this.minMemoryMb = minMemoryMb;}
    public void setMaxMemoryMB(int maxMemoryMB) {this.maxMemoryMB = maxMemoryMB;}
}
