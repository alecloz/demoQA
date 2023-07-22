package qa.demo.tests.workWithFiles;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.demo.tests.workWithFiles.model.GlossaryModel;
import qa.demo.tests.workWithFiles.model.Person;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FilesFromZipTest {

    ClassLoader cl = FilesFromZipTest.class.getClassLoader();
    Gson gson = new Gson();

    @Test
    void csvFromZipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            ZipInputStream zis = new ZipInputStream(stream);
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".csv") & !(entry.getName().contains("__MACOSX"))) {
                    Reader reader = new InputStreamReader(zis);
                    CSVReader csvReader = new CSVReader(reader);
                    List<String[]> content = csvReader.readAll();
                    Assertions.assertEquals(4, content.size());
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


    @Test
    void xlsxFromZipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            ZipInputStream zis = new ZipInputStream(stream);
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx") & !(entry.getName().contains("__MACOSX"))) {
                    byte[] xlsxBytes = zis.readAllBytes();
                    XLS xls = new XLS(xlsxBytes);
                    //первый лист, третья строка, первый столбец
                    Assertions.assertEquals("Monday", xls.excel
                            .getSheetAt(1)
                            .getRow(2)
                            .getCell(0)
                            .getStringCellValue());
                    //первый лист, четвертая строка, второй столбец
                    Assertions.assertEquals("Meat", xls.excel
                            .getSheetAt(0)
                            .getRow(3)
                            .getCell(1)
                            .getStringCellValue());
                    //второй лист, пятая строка, третий столбец. Без приведения к строке падает, кастую ибо дабл
                    Assertions.assertEquals("1500", String.valueOf((int) xls.excel
                            .getSheetAt(1)
                            .getRow(4)
                            .getCell(2)
                            .getNumericCellValue()));
                }
            }
        }
    }

    @Test
    void pdfFromZipTest() throws Exception {
        try (InputStream stream = cl.getResourceAsStream("archive.zip")) {
            assert stream != null;
            ZipInputStream zis = new ZipInputStream(stream);
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf") & !(entry.getName().contains("__MACOSX"))) {
                    PDF pdf = new PDF(zis);
                    Assertions.assertEquals("Shopping_pdf", pdf.title);
                    Assertions.assertTrue(pdf.text.contains("Tuesday Meat 5000"),
                            "В файле Shopping_pdf отсутствует строка 'Tuesday Meat 5000'");
                }
            }
        }
    }

    @Test
    void jsonTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        try (InputStream stream = cl.getResourceAsStream("person.json")) {
            assert stream != null;

            // JSON file to Java object
            Person person = objectMapper.readValue(stream, Person.class);
            System.out.println(person.city);
            System.out.println(person.getFriend().get(0).getId());
            System.out.println(person.getFriend().get(0).getFriendsName());
            System.out.println(person.getHobby().get(0));

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
