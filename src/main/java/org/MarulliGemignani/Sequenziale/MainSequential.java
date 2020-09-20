package org.MarulliGemignani.Sequenziale;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.MarulliGemignani.Tools.CsvWriter;

public class MainSequential {

    public static long sequential(List<String> dataset,int iterations) {
        long startingTime, finishingTime  = 0;
        ArrayList<HashMap<String, Integer>> allQgrams = new ArrayList<HashMap<String, Integer>> ();
        ArrayList<Long> timeExecutionQgram = new ArrayList<Long>();

        int q=2;
        try {
            for(int i =0; i<iterations;i++) {
                for(String text: dataset) {
                    startingTime = System.currentTimeMillis();
                    Qgram book = new Qgram(q);
                    HashMap<String, Integer> qgramma = book.qGrams(text.toLowerCase());
                    finishingTime = System.currentTimeMillis();
                    timeExecutionQgram.add(finishingTime-startingTime);
                    allQgrams.add(qgramma);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        long finalTimeQgramma = 0;
        for(long time: timeExecutionQgram)
            finalTimeQgramma+= time;
//		CsvWriter.qGrammsWriter(allQgrams);
        return finalTimeQgramma;
    }

}