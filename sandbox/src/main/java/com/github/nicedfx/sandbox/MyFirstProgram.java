package com.github.nicedfx.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        String[] strArray = {"String1", "String2"};

        for (String s : strArray) {
            s = "1";
        }
        for (String s : strArray) {
            System.out.println(s);
        }

        for (int i = 0; i < strArray.length; i++) {
            strArray[i] = "1";
        }
        for (String s : strArray) {
            System.out.println(s);
        }


    }
}
