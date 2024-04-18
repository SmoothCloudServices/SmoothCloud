package eu.smoothcloudservices.smoothcloud.wrapper.mojang;

import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerJar {

    private final String path = "C:/Users/Synta/Desktop/SmoothCloud/instances/versions";
    private String getJarPath;
    private String version;
    private String type;
    private final String spigotPath = "https://download.getbukkit.org/spigot/spigot-%s.jar";
    private final String paperPath = "https://api.papermc.io/v2/projects/paper/versions/%s";


    public ServerJar(String version, String type) {
        this.version = version;
        this.type = type;
        if(type.toLowerCase().equals("spigot")) {

            return;
        }
        if(type.toLowerCase().equals("paper")) {
            BuildInfo info = getBuildInfo(paperPath.formatted(version));
            if(info == null) {
                return;
            }
            int[] builds = info.getBuilds();
            int latestBuild = findLatestBuild(builds);
            downloadVersion(paperPath.formatted(STR."\{version}/builds/\{latestBuild}/downloads/paper-\{version}-\{latestBuild}.jar"));
            return;
        }
        if(type.toLowerCase().equals("purpur")) {

        }
    }

    private boolean downloadVersion(String url) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

            if (response.statusCode() != HttpURLConnection.HTTP_OK) {
                System.err.println(STR."HTTP-Fehler: \{response.statusCode()}");
                return false;
            }

            Path downloadDir = Path.of(path);
            if (!Files.exists(downloadDir)) {
                Files.createDirectories(downloadDir);
            }

            Path filePath = downloadDir.resolve(STR."version_\{System.currentTimeMillis()}.json");

            try (InputStream inputStream = response.body();
                 FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                long totalBytesRead = 0;
                long contentLength = response.headers().firstValueAsLong("Content-Length").orElse(-1L);

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                    totalBytesRead += bytesRead;

                    if (contentLength != -1) {
                        float progress = ((float) totalBytesRead / contentLength) * 100;
                        int numBars = (int) (progress / (100.0f / 25));
                        String progressBar = "|".repeat(numBars) + "-".repeat(25 - numBars);
                        System.out.printf("Download Fortschritt: [%s] %.2f%%%n", progressBar, progress);
                    }
                }

                System.out.println(STR."Download abgeschlossen. Gesamtgröße: \{totalBytesRead} Bytes");
                return true;

            } catch (IOException e) {
                System.err.println(STR."Fehler beim Schreiben der Datei: \{e.getMessage()}");
                return false;
            }

        } catch (Exception e) {
            System.err.println(STR."Fehler beim Herunterladen der Version: \{e.getMessage()}");
            return false;
        }
    }

    private int findLatestBuild(int[] builds) {
        int maxBuild = Integer.MIN_VALUE;
        for (int build : builds) {
            if (build > maxBuild) {
                maxBuild = build;
            }
        }
        return maxBuild;
    }

    @SneakyThrows
    private BuildInfo getBuildInfo(String url) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(response.body(), BuildInfo.class);
    }

}
