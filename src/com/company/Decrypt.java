package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class Decrypt {
    String alph;
    String text;
    ArrayList<int[]> keys = new ArrayList<int[]>();

    public void run(Scanner scanner) throws IOException {

        String path = "alph.txt";
        text = readFile(path);
        StringTokenizer stringTokenizer = new StringTokenizer(text, "\n");
        alph = stringTokenizer.nextToken();
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
        char[] freq = new char[alph.length()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            String s = stringTokenizer.nextToken();

            freqMap.put(s.charAt(0), Integer.valueOf(s.substring(2)));
            freq[i] = s.charAt(0);
            i++;
        }
        text = readFile("encrypted.txt");
        System.out.println("Введите длину ключа");
        int keySize = scanner.nextInt();

        HashMap<Integer, HashMap<Character, Integer>> map = new HashMap<>();
        String[] texts = new String[keySize];
        for (int j = 0; j < keySize; j++) {
            texts[j] = "";
        }
        for (int j = 0; j < text.length(); j++) {
            texts[j % keySize] += text.charAt(j);
        }

        for (int j = 0; j < keySize; j++) {
            map.put(j, calc(texts[j]));
        }

        ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();

        for (int j = 0; j < keySize; j++) {
            list.add(new HashSet<Integer>());
            for (int k = 0; k < 3; k++) {
                list.get(j).add(-alph.indexOf(freq[k]) + alph.indexOf(map.get(j).entrySet().iterator().next().getKey()));
            }
        }

        findPwd(new int[keySize], keySize - 1, list);
        FileWriter fileWriter = new FileWriter(new File("decrypted.txt"));
        for (int[] key : keys) {
            for (int i1 : key) {
                fileWriter.write(i1 + " ");
            }
            fileWriter.write("\n");
            fileWriter.write(decrypt(key));
            fileWriter.write("\n");
        }
        fileWriter.flush();
        fileWriter.close();
    }

    void findPwd(int[] pw, int pos, ArrayList<HashSet<Integer>> list) {
        if (pos < 0) {
            keys.add(Arrays.copyOf(pw, pw.length));
            return;
        }
        for (Integer c : list.get(pos)) {
            pw[pos] = c;
            findPwd(pw, pos - 1, list);
        }

    }

    public HashMap<Character, Integer> calc(String text) {
        HashMap<Character, Integer> freqMap = new HashMap<Character, Integer>();
        for (Character character : text.toCharArray()) {
            if (!freqMap.containsKey(character)) {
                freqMap.put(character, 0);
            }
            freqMap.put(character, freqMap.get(character) + 1);
        }
        freqMap = (HashMap<Character, Integer>) MapUtils.sortByValue(freqMap);
        return freqMap;
    }

    public String decrypt(int[] key) {
        char[] buf = text.toCharArray();
        for (int i = 0; i < buf.length; i++) {
            int shift = -key[i % key.length];
            if (shift < 0) {
                shift += alph.length();
            } else if (shift > alph.length()) {
                shift -= alph.length();
            }
            buf[i] = rotate(buf[i], shift);
        }
        return new String(buf);
    }

    private char rotate(char c, int i) {
        return alph.charAt((alph.indexOf(c) + i) % alph.length());
    }


    public String readFile(String name) throws IOException {
        File file = new File(name);
        return new String(Files.readAllBytes(file.toPath()));
    }
}
