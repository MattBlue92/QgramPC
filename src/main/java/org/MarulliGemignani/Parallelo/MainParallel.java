package org.MarulliGemignani.Parallelo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.MarulliGemignani.Sequenziale.MainSequential;
import org.MarulliGemignani.Tools.BookDataset;
import org.MarulliGemignani.Tools.CsvWriter;

public class MainParallel {
    public static void main(String[] args) {
        ArrayList<Long> parallelTime = new ArrayList<Long>();
        ArrayList<Long> sequentialTime = new ArrayList<Long>();
        ArrayList<Integer> listOfIterations = new ArrayList<Integer>();

        BookDataset bookDataset = new BookDataset(1);
        List<String> dataset = null;
        try {
            dataset = bookDataset.getDataset();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        ArrayList<Map<String, Integer>> allGrams = new ArrayList<Map<String, Integer>>();

        for (int threads = 1; threads <=12; threads++) {
            for (int iterations = 1; iterations < 102; iterations = iterations + 10) {
                long startingTime, finishingTime = 0;
                ArrayList<Long> timeExecutionQgram = new ArrayList<Long>();

                Map<String, Integer> qGrams = null;

                int q = 2;

                try {

                    MasterQgrams masterQgrammi = new MasterQgrams(threads, q);
                    for (int i = 0; i < iterations; i++) {
                        for (String text : dataset) {
                            startingTime = System.currentTimeMillis();
                            qGrams = masterQgrammi.qGrams(text);
                            finishingTime = System.currentTimeMillis();
                            timeExecutionQgram.add(finishingTime - startingTime);

                            allGrams.add(qGrams);
                            qGrams = null;

                        }
                    }
                    masterQgrammi.shoutdown();

                } catch (Exception e) {
                    // TODO: handle exception
                    System.out.println(e);
                }
                long finalTimeQgramma = 0;
                for (long time : timeExecutionQgram)
                    finalTimeQgramma += time;

                parallelTime.add(finalTimeQgramma);
                sequentialTime.add(MainSequential.sequential(dataset, iterations));
                listOfIterations.add(iterations);

                System.out.println(threads + ">" + iterations);
            }

        }
        CsvWriter.writeCsv(parallelTime, sequentialTime, listOfIterations);

    }
}