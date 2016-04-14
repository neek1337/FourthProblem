package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.StringTokenizer;

public class Encrypt {
    String alph;
    String text;

    public void run() throws IOException {

        String path = "text.txt";

        String local = readFile(path);
        local = cleanText(local);
        local = local.toLowerCase();

        StringTokenizer stringTokenizer = new StringTokenizer(local, " \n\r");
        int keySize = Integer.valueOf(stringTokenizer.nextToken());
        int[] key = new int[keySize];
        for (int i = 0; i < keySize; i++) {
            key[i] = Integer.valueOf(stringTokenizer.nextToken());
        }
        alph = " ";
        alph += stringTokenizer.nextToken();
        text = "";
        while (stringTokenizer.hasMoreTokens()) {
            text += " " + stringTokenizer.nextToken();
        }
        FileWriter fileWriter = new FileWriter(new File("encrypted.txt"));
        fileWriter.write(encrypt(key));
        fileWriter.flush();
        fileWriter.close();
    }

    public String readFile(String name) throws IOException {
        File file = new File(name);
        return new String(Files.readAllBytes(file.toPath()));
    }


    public String encrypt(int[] key) {
        char[] buf = text.toCharArray();
        for (int i = 0; i < buf.length; i++) {
            buf[i] = rotate(buf[i], key[i % key.length]);
        }
        return new String(buf);
    }

    private char rotate(char c, int i) {
        return alph.charAt((alph.indexOf(c) + i) % alph.length());
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
