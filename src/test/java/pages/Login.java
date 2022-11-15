package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.By;

public class Login {
    //public void Logger LOGGER = LogManager.getLogger(this.getClass());

    private BaseFunctions baseFunctions;

    private final By LOGIN_ICON = By.xpath(".//a[@data-label = 'Navigation']");

    public Login(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public void clickLoginIcon() {
        baseFunctions.click(LOGIN_ICON);
    }





}
