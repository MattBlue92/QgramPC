package org.MarulliGemignani.Sequenziale;

import java.util.HashMap;

public class Qgram {

    private HashMap<String, Integer> grams = new HashMap<String, Integer>();
    private int q;

    public Qgram(int q) {
        this.q = q;
    }

    public HashMap<String, Integer> qGrams(String text) {
        //text = text.toLowerCase();
        String tupla;
        for (int i = 0; i < text.length() - q + 1; i++) {
            tupla = text.substring(i, i + q);
            if (onlyChar(tupla)) {
                if (grams.containsKey(tupla)) {
                    grams.replace(tupla, grams.get(tupla) + 1);
                } else {
                    grams.put(tupla, 1);
                }
            }
        }
        return grams;
    }

    private boolean onlyChar(String tuple) {
        int i = 0;
        while (i < q) {
            int symNum = (int) tuple.charAt(i);
            if (symNum == 224 || symNum == 232 || symNum == 233 || symNum == 236 || symNum == 242)
                i++;
            else if (symNum >= 97 && symNum <= 122)
                i++;
            else
                return false;
        }
        return true;
    }

}