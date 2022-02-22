package aletca.parameterized;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParameterizedWithFakerTest {

    @BeforeEach
    void precondition() {
        Configuration.browserSize = "1920x1080";
        open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String email = faker.internet().emailAddress();
    String address = faker.address().streetAddress();

    Stream<Arguments> mixedArgumentsTestDataProvider() {
        return Stream.of(
                Arguments.of(firstName, email, address),
                Arguments.of(firstName, email, address)
        );
    }

    @MethodSource(value = "mixedArgumentsTestDataProvider")
    @ParameterizedTest(name = "использование рандомных значений")
    void mixedArgumentsTest() {
        $("#userName").val(firstName);
        $("#userEmail").val(email);
        $("#currentAddress").val(address);
        $("#submit").click();
        $("#output").shouldBe(visible);
    }
}
