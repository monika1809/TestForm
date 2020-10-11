import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestForm extends BaseTest {
    Faker faker = new Faker();

    String fakeEmail = faker.internet().emailAddress();
    By name = By.cssSelector("#id_imie-i-nazwisko");
    By email = By.cssSelector("#id_e-mail");
    By contents = By.cssSelector("#id_tresc");
    By settlement = By.cssSelector("li[id*='-Rozliczenie']");
    By consent = By.cssSelector(".custom-checkbox");
    By topicArrow = By.cssSelector(".select2-selection__rendered");
    By sendButton = By.cssSelector("button.btn-primary");

    @Test
    public void fillOutForm() {
        wait.until(ExpectedConditions.elementToBeClickable(name));
        wait.until(ExpectedConditions.elementToBeClickable(name)).sendKeys("CloudServices Test");
        wait.until(ExpectedConditions.elementToBeClickable(email));
        wait.until(ExpectedConditions.elementToBeClickable(email)).sendKeys(fakeEmail);
        wait.until(ExpectedConditions.elementToBeClickable(topicArrow)).click();
        wait.until(ExpectedConditions.elementToBeClickable(settlement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(contents));
        wait.until(ExpectedConditions.elementToBeClickable(contents)).sendKeys("automat test CloudServices");
        wait.until(ExpectedConditions.elementToBeClickable(consent));
        wait.until(ExpectedConditions.elementToBeClickable(consent)).click();
        wait.until(ExpectedConditions.elementToBeClickable(sendButton)).click();

        waitForAlert();
        assertEquals(getInvalidText(), "To pole jest wymagane.", "Alert message was not what expected");
    }

    private void waitForAlert() {
        By alert = By.cssSelector(".alert");
        wait.until(ExpectedConditions.numberOfElementsToBe(alert, 1));
    }

    private String getInvalidText() {
        By invalidFeedback = By.cssSelector("div.input-group+span");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(invalidFeedback)).getText();
    }
}
