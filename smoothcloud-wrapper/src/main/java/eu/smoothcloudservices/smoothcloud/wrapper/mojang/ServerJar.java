package eu.smoothcloudservices.smoothcloud.wrapper.mojang;

import com.google.gson.Gson;
import eu.smoothcloudservices.smoothcloud.api.group.ServerType;
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
    private ServerType type;
    private final String spigotPath = "https://download.getbukkit.org/spigot/spigot-%s.jar";
    private final String paperPath = "https://api.papermc.io/v2/projects/velocity/versions/%s";
    private final String velocityPath = "https://api.papermc.io/v2/projects/velocity/versions/%s";


    public ServerJar(String version, ServerType type) {
        this.version = version;
        this.type = type;
        if (type.equals(ServerType.SPIGOT)) {

            return;
        }
        if (type.equals(ServerType.PAPER)) {
            BuildInfo info = getBuildInfo(paperPath.formatted(version));
            if(info == null) {
                return;
            }
            int[] builds = info.getBuilds();
            int latestBuild = findLatestBuild(builds);
            downloadVersion(paperPath.formatted(STR."\{version}/builds/\{latestBuild}/downloads/paper-\{version}-\{latestBuild}.jar"));
            return;
        }
        if (type.equals(ServerType.PURPUR)) {

        }
        if (type.equals(ServerType.BUNGEECORD)) {

        }
        if (type.equals(ServerType.VELOCITY)) {
            BuildInfo info = getBuildInfo(velocityPath.formatted(version));
            if(info == null) {
                return;
            }
            int[] builds = info.getBuilds();
            int latestBuild = findLatestBuild(builds);
            downloadVersion(velocityPath.formatted(STR."\{version}/builds/\{latestBuild}/downloads/velocity-\{version}-\{latestBuild}.jar"));
        }
        if (type.equals(ServerType.FLUX)) {

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

            Path filePath = downloadDir.resolve("/service/versions");

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
