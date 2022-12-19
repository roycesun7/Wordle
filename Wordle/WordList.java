package org.cis1200.Wordle;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class WordList {

    private static String answer = "HOUSE";
    private ArrayList<String> list;
    private BufferedReader br;



    public WordList(String path) {
        list = new ArrayList<String>();

        try {
            br = new BufferedReader(new FileReader(path));
            String s = br.readLine();

            while (s != null) {
                list.add(s.toLowerCase());
                s = br.readLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        generateWord();
    }


    public void generateWord() {
        if(list.size() > 0) {
            Random random = new Random();
            WordList.answer = list.get(random.nextInt(list.size()));
        }
    }



    public static String getAns() {
        return answer;
    }

    public boolean inWordList(String s) {
        return list.contains(s);
    }


}
