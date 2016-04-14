package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
       // new Frequency().run();
        Scanner scanner = new Scanner(System.in);
        new Decrypt().run(scanner);
       // new Encrypt().run();
    }


}
