package qa.demo.tests.workWithFiles;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.demo.tests.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.FileDownloadMode.PROXY;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDownload extends BaseTest {
    /*
    использовать для скачивания файла если нет href
     static {
            Configuration.proxyEnabled = true;
            Configuration.fileDownload = PROXY;
        }
    */
    @Test
    void downloadFileTestWithoutResources() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("[data-testid='raw-button']").download();
        InputStream is = new FileInputStream(file);
        try {
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("Contributions to JUnit 5 are both welcomed"));
        } finally {
            is.close();
        }
    }
    @Test
    void downloadFileTestWithResources() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File file = $("[data-testid='raw-button']").download();
        try (InputStream is = new FileInputStream(file)) {
            byte[] bytes = is.readAllBytes();
            String content = new String(bytes, StandardCharsets.UTF_8);
            Assertions.assertTrue(content.contains("Contributions to JUnit 5 are both welcomed"));
        }
    }
    @Test
    void uploadFileTest() {
        open("https://fineuploader.com/demos.html");
        /* никогда не использовать uploadFile() из-за проблем с локальностью пути, либо из-за того, что будет падать
        при загрузке из .jar. Необходимо использовать uploadFromClasspath()
        File file = $("input[type='file']").uploadFile();
        Корневая папка classpath - resources
        */
        $("input[type='file']").uploadFromClasspath("cat.PNG");
        $(".qq-file-name").shouldHave(Condition.text("cat.PNG"));
    }
}