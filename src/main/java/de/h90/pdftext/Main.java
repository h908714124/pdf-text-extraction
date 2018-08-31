package de.h90.pdftext;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    private Arguments arguments;

    private Main(Arguments arguments) {
        this.arguments = arguments;
    }

    public static void main(String[] args) throws IOException, TikaException, SAXException {
        new Main(Arguments_Parser.create().parseOrExit(args)).run();
    }

    private void run() throws IOException, TikaException, SAXException {
        System.out.println(getText());
    }


    private String getText() throws IOException, TikaException, SAXException {
        File file = new File(arguments.file());
        FileInputStream fileInputStream = new FileInputStream(file);
        AutoDetectParser parser = new AutoDetectParser();
        ParseContext parseContext = new ParseContext();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        parser.parse(fileInputStream, handler, metadata, parseContext);
        return handler.toString();
    }
}
