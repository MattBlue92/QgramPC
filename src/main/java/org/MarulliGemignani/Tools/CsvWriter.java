package org.MarulliGemignani.Tools;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class CsvWriter {
    public static void writeCsv(List<Long> parTime,List<Long> seqTime,List<Integer> listOfIter){

        String filePath = (new File("").getAbsolutePath())+"/csv/speedUP.csv";

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter (filePath);
            fileWriter.append("NumThread, NumLibri, TempoParallelo, TempoSequenziale, SpeedUp\n");
            Iterator<Long> iter= seqTime.iterator();
            Iterator<Integer> iter2= listOfIter.iterator();

            int nThread=0,rip;
            long sTime;
            for(long pTime: parTime) {
                rip= iter2.next();
                sTime=iter.next();
                if(rip==1)
                    nThread++;



                fileWriter.append(String.valueOf(nThread));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(rip));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(pTime));
                fileWriter.append(",");
                fileWriter.append(String.valueOf(sTime));
                fileWriter.append(",");
                fileWriter.append(String.valueOf((double)sTime/pTime));
                fileWriter.append("\n");
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
    public static void qGrammsWriter(ArrayList<HashMap<String, Integer>> allQgrams) {
        String filePath = (new File("").getAbsolutePath())+"/csv/3Gram.csv";

        FileWriter fileWriter = null;
        try {
            int books=0;
            fileWriter = new FileWriter (filePath);
            fileWriter.append("Libro,Gramma,Frequenza\n");
            for(HashMap<String, Integer> book :allQgrams) {
                for(String kay: book.keySet()) {
                    fileWriter.append(String.valueOf(books));
                    fileWriter.append(",");
                    fileWriter.append(kay);
                    fileWriter.append(",");
                    fileWriter.append(String.valueOf(book.get(kay)));
                    fileWriter.append("\n");
                }
                books++;
            }
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
