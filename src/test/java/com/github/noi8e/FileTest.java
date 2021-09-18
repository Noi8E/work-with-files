package com.github.noi8e;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileTest {

    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/selenide/selenide/blob/master/README.md");
       File download = $("#raw-url").download();
        System.out.println();
        String result;
        try(InputStream is = new FileInputStream(download)) {
            result = new String(is.readAllBytes(), "UTF-8");
            assertThat(result).contains("Selenide");
        }
    }

    @Test
    void uploadFileTest() {

    }
}
