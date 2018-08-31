package de.h90.pdftext;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessBufferedFileInputStream;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private Arguments arguments;

    private Main(Arguments arguments) {
        this.arguments = arguments;
    }

    public static void main(String[] args) throws IOException {
        new Main(Arguments_Parser.create().parseOrExit(args)).run();
    }

    private void run() throws IOException {
        System.out.println(getText());
    }


    private String getText() throws IOException {
        File file = new File(arguments.file());
        FileInputStream fileInputStream = new FileInputStream(file);
        PDFParser parser = new PDFParser(new RandomAccessBufferedFileInputStream(fileInputStream));
        parser.parse();
        PDFTextStripper pdfStripper = new PDFTextStripper();
        try (COSDocument cosDoc = parser.getDocument();
             PDDocument pdDoc = new PDDocument(cosDoc)) {
            return pdfStripper.getText(pdDoc);
        }
    }
}
