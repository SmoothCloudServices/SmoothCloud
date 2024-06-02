package eu.smoothservices.smoothcloud.node.messages;

import eu.smoothservices.smoothcloud.node.terminal.JavaColor;

public class SetupMessages {
    public static final String PREFIX = JavaColor.apply("&9Smooth&bCloud&8-&2Setup &8» &7");

    public static final String EULA_ACCEPT = JavaColor.apply("&7Do you agree to the Mojang EULA (https://www.minecraft.net/en-us/eula)? Possible answers: yes, no");
    public static final String EULA_ACCEPTED = JavaColor.apply("&aYou have accepted the Mojang EULA.");
    public static final String EULA_NOT_ACCEPTED = JavaColor.apply("&cPlease answer with yes or no!");

    public static final String CHOOSE_IP = JavaColor.apply("&7Which IP should we use?");
    public static final String NO_CHOOSE_IP = JavaColor.apply("&cNo IP addresses available. Aborting setup...");
    public static final String CHOOSE_IP_AVAILABLE = JavaColor.apply("&7Available IPs: ");
    public static final String CHOOSE_IP_NOT_EXISTS = JavaColor.apply("&cPlease choose an IP address from above!");

    public static final String CHOOSE_PORT = JavaColor.apply("&7Which port should we use?");
    public static final String CHOOSE_PORT_NOT_EXISTS = JavaColor.apply("&cPort not available. Please choose another port!");

    public static final String CHOOSE_MEMORY = JavaColor.apply("&7How much memory should use the cloud?");
    public static final String WRONG_MEMORY = JavaColor.apply("&cYou have to write a number or you need to add equals or more than 2gb");

    public static final String SAVED = JavaColor.apply("&aIP address, port and memory has been successfully saved.");

    public static final String ERROR = JavaColor.apply("&cSetup error. Please reinstall your cloud!");
    public static final String HALF_NUMBER = JavaColor.apply("&cHalf numbers are not allowed here.");
    public static final String WRONG_INPUT = JavaColor.apply("&cPlease check your input!");

    public static final String COMPLETED = JavaColor.apply("&aSetup has been completed.");
}
