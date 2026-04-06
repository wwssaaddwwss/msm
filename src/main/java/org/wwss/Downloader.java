package org.wwss;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.*;
import java.lang.reflect.Type;
import java.util.List;

public class Downloader {

    public static void download(int ID, int type) throws IOException {
        InputStream s = null;

        switch (type) {
            case 1:
                s = Downloader.class.getClassLoader()
                        .getResourceAsStream("v-servers.json");
                break;
        }

        if (s == null) {
            throw new RuntimeException("can't get list");
        }

        Gson gson = new Gson();

        Type listType = new TypeToken<List<Server>>() {}.getType();

        List<Server> list = gson.fromJson(
                new InputStreamReader(s),
                listType
        );

        Server target = list.get(0);

        String url = target.getUrl();
        String version = target.getVersion();

        System.out.println("version: " + version);
        System.out.println("URL: " + url);

        Path savePath = Paths.get("servers", String.valueOf(ID), "server.jar");

        Files.createDirectories(savePath.getParent());

        downloadFile(url, savePath);

        System.out.println("complete: " + savePath);
    }

    private static void downloadFile(String urlStr, Path path) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestProperty("User-Agent", "Mozilla/5.0");

        try (InputStream in = conn.getInputStream()) {
            Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
        }
    }

    private static class Server {
        private String version;
        private String url;

        public String getVersion() {
            return version;
        }

        public String getUrl() {
            return url;
        }
    }
}