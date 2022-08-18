import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import java.nio.channels.ConnectionPendingException;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CardTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String planDate = generateDate(3);

    @Test
    void BookCard() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//input[@placeholder='Город']").setValue("Уфа");
        $x("//input[@placeholder='Дата встречи']").setValue("20.08.2022");
        $x("//input[@placeholder='Дата встречи']").setValue(planDate);
        $("[name= 'name']").setValue("Иванова-Петрова Мария");
        $("[name='phone']").setValue("+79174203535");
        $x("// *[@class='checkbox__box']").click();
        $x("// *[text()='Запланировать']").click();
        $("[data-test-id=success-notification]")
                .shouldHave(Condition.text("Успешно! Встреча успешно запланирована на " + planDate), Duration.ofSeconds(10))
                .shouldBe(visible);

    }


}
