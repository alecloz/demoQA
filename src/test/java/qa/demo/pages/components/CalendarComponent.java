package qa.demo.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CalendarComponent {
    SelenideElement
            yearSelect = $x("//select[contains(@class, 'year-select')]"),
            monthSelect = $x("//select[contains(@class, 'month-select')]");

    public void setDate(String day, String month, String year) {
        yearSelect.selectOption(year);
        monthSelect.selectOption(month);
        $x("//div[contains(text(), " + day + ")]").click();
    }
}
