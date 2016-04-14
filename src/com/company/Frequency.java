package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;

public class Frequency {
    public void run() throws IOException {
        String path = "sample.txt";
        String text = readFile(path);
        text = cleanText(text);
        text = text.toLowerCase();
        HashSet<Character> alphSet = new HashSet<Character>();
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for (Character character : text.toCharArray()) {
            alphSet.add(character);
            if (!freqMap.containsKey(character)) {
                freqMap.put(character, 0);
            }
            freqMap.put(character, freqMap.get(character) + 1);
        }
        StringBuilder result = new StringBuilder();
        for (Character c : alphSet) {
            result.append(c);
        }
        result.append("\n");
        freqMap = (HashMap<Character, Integer>) MapUtils.sortByValue(freqMap);
        for (Character character : freqMap.keySet()) {
            result.append(character);
            result.append(" " + freqMap.get(character)+"\n");
        }

        FileWriter out = new FileWriter("alph.txt");

        out.write(result.toString());
        out.flush();
        out.close();


    }

    public String readFile(String name) throws IOException {
        File file = new File(name);
        return new String(Files.readAllBytes(file.toPath()));
    }

    public String cleanText(String text) {
        String ans = "";

        for (int i = 0; i < text.length(); i++) {
            if (!Character.isISOControl(text.charAt(i))) {
                ans += text.charAt(i);
            }
        }

        return ans;
    }


}
