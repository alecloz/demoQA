package qa.demo.tests.files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import qa.demo.tests.files.model.Person;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithFilesTest {

    ClassLoader cl = WorkWithFilesTest.class.getClassLoader();

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
    void jsonToObjectTest() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        try (InputStream stream = cl.getResourceAsStream("person.json")) {
            assert stream != null;

            // JSON file to Java object
            Person person = objectMapper.readValue(stream, Person.class);
            Assertions.assertEquals("13.03.2003", person.getDate());
            Assertions.assertEquals("Иван", person.getFirstname());
            Assertions.assertEquals("Иванов", person.getLastname());
            Assertions.assertEquals("Москва", person.getCity());
            Assertions.assertEquals("iivanov@mail.com", person.getEmail());
            Assertions.assertEquals("прогулки", person.getHobby().get(2));
            Assertions.assertEquals("Barsik the cat", person.getFriend().get(1).getFriendsName());
        }
    }
}
