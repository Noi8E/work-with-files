package com.github.noi8e;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Scanner;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;


public class FileTest {


//    @Test
//    void downloadFileTest() throws Exception {
//        open("https://github.com/selenide/selenide/blob/master/README.md");
//       File download = $("#raw-url").download();
//        System.out.println();
//        String result;
//        try(InputStream is = new FileInputStream(download)) {
//            result = new String(is.readAllBytes(), "UTF-8");
//            assertThat(result).contains("Selenide = UI Testing Framework powered by Selenium WebDriver");
//        }
//    }

    @Test
    void checkPdfFileExample() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("pdfSample.pdf");
        try {
            PDF pdfExample = new PDF(stream);
            assertThat(pdfExample.title).contains("PDF Form Example");
            assertThat(pdfExample.text).contains("Given Name:");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
        @Test
        void checkTxtFileExample() throws Exception {
            String expectedValue = "Lorem ipsum dolor sit amet";

            try {
                InputStream stream = getClass().getClassLoader().getResourceAsStream("txtSample.pdf");
                Scanner s = null;
                if (stream != null) {
                    s = new Scanner(stream).useDelimiter("\\A");
                }
                String data = s.hasNext() ? s.next() : "";
                assertThat(data).contains(expectedValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

