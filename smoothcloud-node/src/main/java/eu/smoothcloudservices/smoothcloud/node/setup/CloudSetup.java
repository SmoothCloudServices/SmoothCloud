package eu.smoothcloudservices.smoothcloud.node.setup;

import eu.smoothcloudservices.smoothcloud.node.config.CloudConfig;
import com.github.lalyos.jfiglet.FigletFont;

import java.io.IOException;
import java.util.Scanner;

public class CloudSetup {

    public CloudSetup() {}


    public boolean setup(CloudConfig config) {

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println(FigletFont.convertOneLine("SmoothCloud setup"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean eulaAccepted = false;

        switch (config.get("cloud.language").toLowerCase()) {
            case "de" -> {
                presentEula("https://www.minecraft.net/de-de/eula");
                eulaAccepted = getEULAagreement(scanner);
            }
            case "en" -> {
                presentEula("https://www.minecraft.net/en-us/eula");
                eulaAccepted = getEULAagreement(scanner);
            }
        }
    }

    private boolean selectLanguage(Scanner scanner, CloudConfig config) {

        boolean languageSelected = false;

        while(!languageSelected) {
            System.out.println("Select your language / Wähle deine Sprache (EN/DE):");

            String languageCode = scanner.nextLine().toLowerCase();

            switch (languageCode) {
                case "en" -> {
                    languageSelected = true;
                    config.set("cloud.language", "en");
                }
                case "de" -> {
                    languageSelected = true;
                    config.set("cloud.language", "de");
                }
                default -> {
                    System.out.println("Invalid language code. Please select EN or DE");
                    return false;
                }
            }
        }
        return true;
    }

    private void presentEula(String eulaUrl) {
        System.out.println("Do you agree to the Mojang EULA (" + eulaUrl + ")? Possible answers: yes/no");
    }

    private boolean getEULAagreement(Scanner scanner) {
        while (true) {
            String answer = scanner.nextLine().toLowerCase();
            if(answer.equals("yes") || answer.equals("y") || answer.equals("ja") || answer.equals("j")) {
                return true;
            }
            if(answer.equals("no") || answer.equals("n") || answer.equals("nein")) {
                return false;
            }
            System.out.println("Please answer yes or no!");
        }
    }

    /*public boolean setup(CloudConfig config) {

        if(config.isEmpty()) {
            Scanner scanner = new Scanner(System.in);

            String lang = config.get("cloud.language");
            config.contains("cloud.language");

            System.out.println("Choose the language for the cloud. Possible answers: EN, DE");
            while (scanner.hasNextLine()) {
                if(!config.contains("cloud.language")) {
                    if (scanner.nextLine().equals("EN") || scanner.nextLine().equals("en")) {
                        System.out.println("Do you agree to the Mojang EULA (https://www.minecraft.net/en-us/eula)? Possible answers: yes, no");
                        if (scanner.nextLine().equals("yes") || scanner.nextLine().equals("YES") || scanner.nextLine().equals("Yes") || scanner.nextLine().equals("Y") || scanner.nextLine().equals("y")) {

                        } else if (scanner.nextLine().equals("no") || scanner.nextLine().equals("NO") || scanner.nextLine().equals("No") || scanner.nextLine().equals("N") || scanner.nextLine().equals("n")) {
                            System.out.println("Not accepting EULA. Aborting process!");
                            return false;
                        } else  {
                            System.out.println("Please answer yes or no!");
                            // Möglichkeit zum wiederholen der frage?
                        }
                    } else if (scanner.nextLine().equals("DE") || scanner.nextLine().equals("de")) {
                        System.out.println("Akzeptierst du die Minecraft EULA (https://www.minecraft.net/de-de/eula)? Mögliche antworten: ja, nein");
                        if (scanner.nextLine().equals("ja") || scanner.nextLine().equals("JA") || scanner.nextLine().equals("Ja") || scanner.nextLine().equals("J") || scanner.nextLine().equals("j")) {

                        } else if (scanner.nextLine().equals("nein") || scanner.nextLine().equals("NEIN") || scanner.nextLine().equals("Nein") || scanner.nextLine().equals("N") || scanner.nextLine().equals("n")){
                            System.out.println("EULA nicht angenommen. Prozess wird abgebrochen!");
                            return false;
                        } else {
                            System.out.println("Bitte antworte mit ja oder nein");
                            // Möglichkeit zum wiederholen der frage?
                        }
                    } else {
                        System.out.println("Please answer yes or no!");
                        // Möglichkeit zum wiederholen der frage?
                    }
                }
            }
        }
        return true;
    }*/
}
