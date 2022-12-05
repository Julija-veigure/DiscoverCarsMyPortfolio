import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.*;

public class DiscoverCarsTest {
    private final Logger LOGGER = (Logger) LogManager.getLogger(this.getClass());

    BaseFunctions baseFunctions = new BaseFunctions();
    MainPage mainPage = new MainPage(baseFunctions);
    Searching searching = new Searching(baseFunctions);
    SearchResultPage searchResultPage = new SearchResultPage(baseFunctions, searching);
    Login login = new Login(baseFunctions);

    @BeforeEach

    public void openPage() {
        baseFunctions.openUrl("https://www.discovercars.com/");

        mainPage.acceptCookies();
    }

    @Test

    public void searching() {
        LOGGER.info("TEST Nr 1: This test will check searching functionality");

        baseFunctions.pleaseAssert(mainPage.TITLE_TEXT, mainPage.giveMainPageTitle());

        mainPage.giveNumbersOfCountries(); // Logging print out 2 times result - why?
        baseFunctions.pleaseAssert(mainPage.NUMBER_OF_COUNTRIES, mainPage.giveNumbersOfCountries());

        mainPage.selectCountryLatvia(); //not always select Latvia - need to investigate!
        baseFunctions.comparePageTitle(mainPage.titleInEnForLv, mainPage.giveMainPageTitle());

        mainPage.selectLatvianLanguage();
        baseFunctions.comparePageTitle(mainPage.titleInLvForLv, mainPage.giveMainPageTitle());

        mainPage.selectEnglishLanguage();
        baseFunctions.comparePageTitle(mainPage.titleInEnForLv, mainPage.giveMainPageTitle());

        searching.search();
        baseFunctions.pleaseAssert(searching.WARNING_MSG_PICK_UP, searching.giveWarningMsg());

        searching.selectPickUpLocation();
        searching.selectPickUpDate();
        searching.selectPickUpTime();

        searching.tickCheckBox();

        searching.search();
        baseFunctions.pleaseAssert(searching.WARNING_MSG_DROP_OFF, searching.giveWarningMsg());

        searching.tickCheckBox();

        searching.selectDropOffDate();
        searching.selectDropOffTime();

        searching.search();
        searching.loadingAllResults();

        searchResultPage.changeSearchRequest();
        searchResultPage.pickUpLocationChanging();
        searchResultPage.pickUpTimeChanging();
        searchResultPage.checkSpecialOffers();
        searchResultPage.specOfferSelect();
        searchResultPage.removeAllFilters();


    }

    @Test
    public void logIn() {
        LOGGER.info("TEST Nr 2: This test will check login functionality");
        login.clickOnLogo();
        login.clickLoginIcon();
        login.closePopUp();
        login.clickLoginIcon();
        login.continueWithGoogle();
        login.typeInEmail();
        login.typeInPassword();
        baseFunctions.pleaseAssert(login.NAME, login.giveAuthorizerName());
    }

    @Test
    public void loginSearching() {
        LOGGER.info("TEST Nr 3: A logged in person is looking for transport");
        logIn();
        searching();
    }

    @AfterEach
    public void closeBrowser() {
        baseFunctions.closeBrowser();
    }

}


