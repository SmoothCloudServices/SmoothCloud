package eu.smoothservices.smoothcloud.node.terminal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaColor {

    public static String colored(String hex, String message) {
        return hexToAnsi(hex) + message;
    }

    public static String apply(String message) {
        return applyColors(message);
    }

    private static String hexToAnsi(String hexColor) {
        int r = Integer.valueOf(hexColor.substring(1, 3), 16);
        int g = Integer.valueOf(hexColor.substring(3, 5), 16);
        int b = Integer.valueOf(hexColor.substring(5, 7), 16);

        int ansiCode = ((r / 51) * 36) + ((g / 51) * 6) + (b / 51) + 16;

        return StringTemplate.STR."\u001B[38;5;\{ansiCode}m";
    }

    private static final Map<Character, String> colorMap = createColorMap();

    private static String applyColors(String input) {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            if (currentChar == '&' && i + 1 < input.length()) {
                char colorChar = input.charAt(i + 1);
                String colorCode = colorMap.get(colorChar);

                if (colorCode != null) {
                    output.append(colorCode);
                    i++; // Skip the color character
                    continue;
                }
            }

            output.append(currentChar);
        }

        return output.toString();
    }

    private static Map<Character, String> createColorMap() {
        Map<Character, String> colorMap = new HashMap<>();
        colorMap.put('0', "\u001B[30m");
        colorMap.put('1', "\u001B[34m");
        colorMap.put('2', "\u001B[32m");
        colorMap.put('3', "\u001B[36m");
        colorMap.put('4', "\u001B[31m");
        colorMap.put('5', "\u001B[35m");
        colorMap.put('6', "\u001B[33m");
        colorMap.put('7', "\u001B[37m");
        colorMap.put('8', "\u001B[90m");
        colorMap.put('9', "\u001B[94m");
        colorMap.put('a', "\u001B[92m");
        colorMap.put('b', "\u001B[96m");
        colorMap.put('c', "\u001B[91m");
        colorMap.put('d', "\u001B[95m");
        colorMap.put('e', "\u001B[93m");
        colorMap.put('f', "\u001B[97m");
        colorMap.put('r', "\u001B[0m");
        return colorMap;
    }

    public static String transition(String startHex, String endHex, String message) {
        int transitionSteps = message.length();
        List<String> transitionColors = generateTransitionColors(startHex, endHex, transitionSteps);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String colorCode = transitionColors.get(i);
            output.append(colorCode).append(message.charAt(i));
        }

        return output.toString();
    }

    public static String ansiTransition(String startAnsi, String endAnsi, String message) {
        int transitionSteps = message.length();
        List<String> transitionColors = generateAnsiTransitionColors(startAnsi, endAnsi, transitionSteps);

        StringBuilder output = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            String colorCode = transitionColors.get(i);
            output.append(colorCode).append(message.charAt(i));
        }

        return output.toString();
    }

    private static List<String> generateTransitionColors(String startHex, String endHex, int steps) {
        int startR = Integer.valueOf(startHex.substring(1, 3), 16);
        int startG = Integer.valueOf(startHex.substring(3, 5), 16);
        int startB = Integer.valueOf(startHex.substring(5, 7), 16);

        int endR = Integer.valueOf(endHex.substring(1, 3), 16);
        int endG = Integer.valueOf(endHex.substring(3, 5), 16);
        int endB = Integer.valueOf(endHex.substring(5, 7), 16);

        List<String> transitionColors = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            int currentR = interpolate(startR, endR, i, steps);
            int currentG = interpolate(startG, endG, i, steps);
            int currentB = interpolate(startB, endB, i, steps);

            int ansiCode = ((currentR / 51) * 36) + ((currentG / 51) * 6) + (currentB / 51) + 16;
            String colorCode = StringTemplate.STR."\u001B[38;5;\{ansiCode}m";
            transitionColors.add(colorCode);
        }

        return transitionColors;
    }

    private static int interpolate(int start, int end, int currentStep, int totalSteps) {
        float ratio = (float) currentStep / (float) totalSteps;
        return (int) (start + (end - start) * ratio);
    }

    private static List<String> generateAnsiTransitionColors(String startAnsi, String endAnsi, int steps) {
        int startCode = extractAnsiCode(startAnsi);
        int endCode = extractAnsiCode(endAnsi);

        List<String> transitionColors = new ArrayList<>();
        for (int i = 0; i < steps; i++) {
            int currentCode = interpolate(startCode, endCode, i, steps);
            String colorCode = StringTemplate.STR."\u001B[\{currentCode}m";
            transitionColors.add(colorCode);
        }

        return transitionColors;
    }

    private static int extractAnsiCode(String ansiColor) {
        String codeString = ansiColor.substring(ansiColor.indexOf('[') + 1, ansiColor.indexOf('m'));
        return Integer.parseInt(codeString);
    }
}
