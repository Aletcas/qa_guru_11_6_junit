package aletca.parameterized;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParameterizedWithTextTests {
    @BeforeEach
    void precondition() {
        open("http://pizzeria.skillbox.cc/register");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @CsvSource(value = {
            "lina, lina@mail.ru, hytr15iu, Учетная запись с такой почтой уже зарегистировавана",
            " , margosha@mail.ru,  , Пожалуйста введите корректное имя пользователя."
    })

    @ParameterizedTest(name = "Проверка формы регистрации на сайте")
    void registeringOnASiteError(String name, String emailname, String password, String expectedWithText) {
        $("#reg_username").setValue(name);
        $("#reg_email").setValue(emailname);
        $("#reg_password").setValue(password);
        $("[name='register']").click();
        $(".woocommerce-error").shouldHave(text(expectedWithText));
    }
}
