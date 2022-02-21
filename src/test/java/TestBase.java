import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import AttachmentsList.AttachmentsList;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

//        Configuration.browser = System.getProperty("browser","chrome");
//        Configuration.browserVersion = System.getProperty("browserVersion");
        String user = System.getProperty("user");
        String password = System.getProperty("password");
        String url = System.getProperty("url");

        String mainUrl = "https://" + user + ":" + password + "@" + url;
        Configuration.remote = mainUrl;


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        AttachmentsList.screenshotAs("Last screenshot");
        AttachmentsList.pageSource();
        AttachmentsList.browserConsoleLogs();
        AttachmentsList.addVideo();
        closeWebDriver();
    }

}
