package org.MarulliGemignani.Tools;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDataset {

    private List<String> dataset = new ArrayList<String>();
    private int numBook;

    public BookDataset(int numBooks) {
        this.numBook = numBooks;
        this.dataset = new ArrayList<String>(numBooks);
    }

    public List<String> getDataset() throws IOException {
        String path = "/home/matteo/IdeaProjects/Qgram_PC/datasetBook";
        for (int i = 0; i < numBook; i++) {
            FileReader fileBook;
            fileBook = new FileReader(path+"/book_" + i + ".txt");

            BufferedReader readerBook;
            readerBook = new BufferedReader(fileBook);

            StringBuilder book = new StringBuilder();
            String line;
            while (true) {
                line = readerBook.readLine();
                if (line == null)
                    break;
                book.append(line+"\n");
            }
            dataset.add(book.toString());
        }

        return dataset;
    }
}
