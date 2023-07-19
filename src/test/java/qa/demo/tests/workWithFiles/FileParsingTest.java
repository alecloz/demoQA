package qa.demo.tests.workWithFiles;

import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.demo.tests.workWithFiles.model.GlossaryModel;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileParsingTest {

    ClassLoader cl = FileParsingTest.class.getClassLoader();
    Gson gson = new Gson();

    @Test
    void csvFromZipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {
                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().contains(".csv") & !(entry.getName().contains("__MACOSX"))) {
                        try (InputStream streamCSV = cl.getResourceAsStream(entry.getName())) {
                            assert streamCSV != null;
                            try (Reader reader = new InputStreamReader(streamCSV)) {
                                CSVReader csvReader = new CSVReader(reader);
                                List<String[]> content = csvReader.readAll();

                                Assertions.assertEquals(3, content.size());

                                final String[] firstRow = content.get(0);
                                final String[] secondRow = content.get(1);
                                final String[] thirdRow = content.get(2);
                                Assertions.assertArrayEquals(new String[]{"Monday", "Milk", "60.5"}, firstRow);
                                Assertions.assertArrayEquals(new String[]{"Tuesday", "Meat", "500"}, secondRow);
                                Assertions.assertArrayEquals(new String[]{"Wednesday", "Tea", "150"}, thirdRow);
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    void xlsxFromZipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {
                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {
                    System.out.println(entry.getName());
                    if (entry.getName().contains(".xlsx") & !(entry.getName().contains("__MACOSX"))) {
                        try (InputStream streamXLSX = cl.getResourceAsStream(entry.getName())) {
                            assert streamXLSX != null;
                            byte[] xlsxBytes = streamXLSX.readAllBytes();
                            XLS xls = new XLS(xlsxBytes);
                            Assertions.assertEquals("1. Общие положения",
                                    xls.excel.getSheetAt(0).
                                            getRow(1)
                                            .getCell(4)
                                            .getStringCellValue());
                        }
                    }
                }
            }
        }
    }


    @Test
    void zipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            try (ZipInputStream zis = new ZipInputStream(stream)) {
                ZipEntry entry;

                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().contains("__MACOSX") | entry.getName().contains(".xlsx")
                            | entry.getName().contains(".pdf"))
                        continue;
                    try (InputStream stream2 = cl.getResourceAsStream(entry.getName())) {
                        assert stream2 != null;
                        try (Reader reader = new InputStreamReader(stream2)) {
                            CSVReader csvReader = new CSVReader(reader);
                            List<String[]> content = csvReader.readAll();
                            System.out.println(entry.getName());
                            //Assertions.assertEquals(3, content.size());

                            final String[] firstRow = content.get(0);
                            final String[] secondRow = content.get(1);
                            final String[] thirdRow = content.get(2);
                            Assertions.assertArrayEquals(new String[]{"Monday", "Milk", "60.5"}, firstRow);
                            Assertions.assertArrayEquals(new String[]{"Tuesday", "Meat", "500"}, secondRow);
                            Assertions.assertArrayEquals(new String[]{"Wednesday", "Tea", "150"}, thirdRow);
                        }
                    }
                    //Assertions.assertTrue(name.contains("sample.txt"));
                }
            }
        }
    }

    @Test
    void jsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("glossary.json");
             Reader reader = new InputStreamReader(stream)) {
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            Assertions.assertEquals("example glossary", jsonObject.get("title").getAsString());
            Assertions.assertEquals("S", jsonObject.get("gloss_div")
                    .getAsJsonObject()
                    .get("title")
                    .getAsString());

            Assertions.assertTrue(jsonObject.get("gloss_div")
                    .getAsJsonObject()
                    .get("flag")
                    .getAsBoolean());
        }
    }

    @Test
    void improvedJsonTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("glossary.json");
             Reader reader = new InputStreamReader(stream)) {
            GlossaryModel glossary = gson.fromJson(reader, GlossaryModel.class);

            Assertions.assertEquals("example glossary", glossary.getTitle());
            Assertions.assertEquals("S", glossary.getGlossDiv().getTitle());
            Assertions.assertTrue(glossary.getGlossDiv().isFlag());
        }
    }

}
