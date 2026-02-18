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

    // getter（Gson 不一定需要，但未來你一定會用到）
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
}
