package org.MarulliGemignani.Parallelo;

import java.util.HashMap;
import java.util.concurrent.Callable;

public class ParallelQgrams implements Callable<HashMap<String, Integer>> {

    private String text;
    private int q;
    private HashMap<String, Integer> qGrams;

    public ParallelQgrams(String text, int q) {
        this.text = text.toLowerCase();
        this.q = q;
        this.qGrams =  new HashMap<String, Integer>();
    }

    public HashMap<String, Integer> call() throws Exception {
        String tuple;
        for (int i = 0; i < text.length() - q + 1; i++) {
            tuple = text.substring(i, i + q);
            if (onlyLetters(tuple)) {
                if (qGrams.containsKey(tuple)) {
                    qGrams.replace(tuple, qGrams.get(tuple) + 1);
                } else {
                    qGrams.put(tuple, 1);
                }
            }
        }

        return qGrams;
    }


    private boolean onlyLetters(String tuple) {
        int i=0;
        while(i<q){
            int symNum = (int) tuple.charAt(i);
            if (symNum == 224 || symNum == 232 || symNum == 233 ||symNum == 236|| symNum == 242)
                i++;
            else if (symNum >= 97 && symNum <= 122)
                i++;
            else
                return false;
        }
        return true;
    }


}
