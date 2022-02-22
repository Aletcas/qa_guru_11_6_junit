package aletca.parameterized;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedSearchTest {
    @BeforeEach
    void precondition() {
        open("https://www.ozon.ru");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @ValueSource(strings = {"куртка женская", "куртка мужская"})
    @ParameterizedTest(name = "Проверка отображения поисковых результатов в магазине")
    void commonSearchTest(String testData) {
        $("[placeholder='Искать на Ozon']").setValue(testData).pressEnter();
        $("[data-widget='fulltextResultsHeader']").shouldBe(visible);
    }
}
