package com.github.noi8e;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import net.lingala.zip4j.ZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Scanner;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static org.apache.commons.io.FileUtils.getFile;


public class FileTest {


    @Test
    void checkPdfFileExample() throws Exception {
        PDF pdfResult;

        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("pdfSample.pdf")) {
            pdfResult = new PDF(stream);
            assertThat(pdfResult.title).contains("PDF Form Example");
            assertThat(pdfResult.text).contains("Given Name:");
        } catch (Exception e) {
            e.printStackTrace();
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
    @Test
    void checkXlsFileExample() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("xlsExample.xls");
        try {
            assert stream != null;
            XLS parsed = new XLS(stream);
            assertThat(parsed.excel.getSheetAt(0).getRow(3).getCell(4).getStringCellValue()).
                    isEqualTo("February");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void checkZipEncryptedExample() throws Exception {
        String zipPassword = "qaguru";
        ZipFile zipFile = new ZipFile("./src/test/resources/zipExample.zip");
        if (zipFile.isEncrypted())
                zipFile.setPassword(zipPassword.toCharArray());
                zipFile.extractAll("./src/test/resources/");
                assertThat(zipFile.getFileHeaders().get(1).toString()).contains("qa1.png");
    }
    @Test
    void checkDocExample() throws Exception {
        InputStream stream = getClass().getClassLoader().getResourceAsStream("docxExample.docx");
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(stream);
        String text = wordMLPackage.getMainDocumentPart().getContent().toString();
        assertThat(text).contains("Sample Document");
    }
}

