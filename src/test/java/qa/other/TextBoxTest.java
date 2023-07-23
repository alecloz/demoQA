package qa.other;

import org.junit.jupiter.api.Test;
import qa.demo.pages.TextBoxPage;
import qa.demo.tests.pageobject.BaseTest;

public class TextBoxTest extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void successTest() {
        openPage("/text-box");
        textBoxPage
                .setUserName("Pikachu Eshevich")
                .setUserEmail("cat@mail.ru")
                .setCurrentAddress("www.leningradSPB.ru")
                .setPermanentAddress("Moscow, Lenin street, 1")
                .clickSubmitBtn();

        textBoxPage
                .checkOutputName("Pikachu Eshevich")
                .checkOutputEmail("cat@mail.ru")
                .checkOutputCurrentAddress("www.leningradSPB.ru")
                .checkOutputPermanentAddress("Moscow, Lenin street, 1");
    }

}
