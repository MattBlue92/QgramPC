package org.MarulliGemignani.Parallelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Collectors.*;

import javax.security.auth.kerberos.KerberosKey;

public class MasterQgrams {
    private List<ParallelQgrams> taskQGrams;
    private int processors;
    private int q;
    private ExecutorService exec;

    public MasterQgrams() {
        this.taskQGrams = new ArrayList<ParallelQgrams>();
        this.processors = Runtime.getRuntime().availableProcessors();
        this.q = 2;
        this.exec = Executors.newFixedThreadPool(processors);
    }

    public MasterQgrams(int processors, int q) {
        this.taskQGrams = new ArrayList<ParallelQgrams>();
        this.processors = processors;
        this.q = q;
        this.exec = Executors.newFixedThreadPool(processors);
    }

    public Map<String, Integer> qGrams(String text)
            throws InterruptedException, ExecutionException {
        divideText(text);
        List<Future<HashMap<String, Integer>>> resultsQgrams = exec.invokeAll(taskQGrams);

        Map<String, Integer> mergeAll = new ConcurrentHashMap<String, Integer>();
        for (Future<HashMap<String, Integer>> result : resultsQgrams) {
            result.get().forEach((k, v) -> mergeAll.merge(k, v, Integer::sum));
        }
        taskQGrams.clear();
        return mergeAll;
    }

    public void shoutdown() {
        exec.shutdown();
    }


    private void divideText(String text) {
        double textLen = text.length();
        int i, j;
        double work = Math.ceil(textLen / processors);
        i = 0;
        j = (int) work;
        String subText = null;
        while (j < textLen) {
            while (text.charAt(j) != ' ') {
                j++;
            }
            subText = text.substring(i, j);
            i = j;
            j = (int) (i + work);
            taskQGrams.add(new ParallelQgrams(subText, q));
        }
        subText = text.substring(i, (int) textLen);
        taskQGrams.add(new ParallelQgrams(subText, q));
    }

}