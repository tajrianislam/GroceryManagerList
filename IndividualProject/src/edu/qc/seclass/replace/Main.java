package edu.qc.seclass.replace;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;



public class Main {

    public static void main(String[] args) {

        ArgsOpt argsOption = new ArgsOpt(args);

        boolean backup = argsOption.optionPresent("-b");
        boolean first = argsOption.optionPresent("-f");
        boolean last = argsOption.optionPresent("-l");
        boolean insensitiveCase = argsOption.optionPresent("-i");
        int ArgIndex = argsOption.optsIndex("--");

        if (!argsOption.isValid()) {
            usage();
            return;
        }

        if (ArgIndex == -1) {
            usage();
            return;
        }

        String from = args[ArgIndex - 2];
        String to = args[ArgIndex - 1];

        for (int i = ArgIndex + 1; i < args.length; i++) {
            File file = new File(args[i]);
            if(!file.exists()) {
                System.err.println("File " + file.getName() + " not found");
                continue;
            }

            if (backup) {
                backup(file);
            }

            String text = readFile(file);

            if (text == null) {
                continue;
            }

            else if (!first && !last) {
                if (insensitiveCase) {
                    text = text.replaceAll("(?i)" + from, to);
                }
                else {
                    text = text.replaceAll(from, to);
                }
            }
            else {
                if (first) {
                    if (insensitiveCase) {
                        text = text.replaceFirst("(?i)" + from, to);
                    }
                    else {
                        text = text.replaceFirst(from, to);
                    }
                }

                if (last) {
                    text = replaceLast(text, from, to, insensitiveCase);
                }
            }

            writeFile(text, file);

        }
    }

    private static void writeFile(String text, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(text);

            fileWriter.close();
        } catch (IOException e) {
            System.err.println(e);
        }

    }

    private static String readFile(File file) {
        try {
            return new String(Files.readAllBytes(Paths.get(file.toURI())), StandardCharsets.UTF_8);
        }
        catch (IOException e) {
            return null;
        }
    }

    private static void backup(File file) {
        Path copy = Paths.get(file.getPath() + ".bck");
        Path orig = file.toPath();
        try {
            Files.copy(orig, copy, StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static String replaceLast(String opt, String from, String to, boolean ignoreCase) {
        int start = ignoreCase ? opt.toLowerCase().lastIndexOf(from.toLowerCase()) : opt.lastIndexOf(from);
        if (start == -1) {
            return opt;
        }

        StringBuilder builder = new StringBuilder(opt);
        builder.replace(start,from.length() + start, to);

        return builder.toString();
    }

    private static void usage() {
        System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*" );
    }
}

class ArgsOpt {

    int indexOfOpt;
    private int inputArgIndex = -1;

    private String[] input;
    private Set<String> option;
    private HashMap<String, Integer> optIndex = new HashMap<>();
    private TreeSet<Integer> inputOptIndex = new TreeSet<>();


    ArgsOpt(String[] args) {
        option = new HashSet<>();
        option.addAll(Arrays.asList("-i", "-f", "-l", "-b", "--"));
        parse(args);
    }

    private void parse(String[] inputString) {
        this.input = inputString;
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals("--")) {
                optIndex.put(input[i], i);
            }

            if (option.contains(input[i]) && !optIndex.containsKey(input[i])) {
                inputOptIndex.add(i);
                optIndex.put(input[i], i);
            }

        }

        if (optIndex.containsKey("--")) {
            inputArgIndex = optIndex.get("--");
        }

        indexOfOpt = inputOptIndex.size();
    }

    public boolean optionPresent(String opt) {
        return optIndex.containsKey(opt) && optIndex.get(opt) != inputArgIndex - 2 &&
                optIndex.get(opt) != inputArgIndex -1;
    }

    public int optsIndex(String opt) {
        return optIndex.getOrDefault(opt, -1);
    }

    public boolean isValid() {

        if (optIndex.get("--") == input.length - 1) {
            return false;
        }

        if (inputArgIndex - 2 < 0 || input[inputArgIndex-2].isEmpty()) {
            return false;
        }

        if (inputArgIndex - 2 == 0 && option.contains(input[inputArgIndex - 2])) {
            return false;
        }

        for (int i = 0; i < inputArgIndex - 2; i++) {
            if (!input[i].startsWith("-")) {
                return false;
            }
        }
        return true;
    }

}