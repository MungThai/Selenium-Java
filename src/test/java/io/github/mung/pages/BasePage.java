package io.github.mung.pages;

import org.openqa.selenium.By;

import static io.github.mung.utils.CommonPageUtils.clickElement;

public class BasePage {

    private By menuAdmin = By.xpath("//a[@href='/web/index.php/admin/viewAdminModule']");
    private By menuPIM = By.xpath("//a[@href='/web/index.php/pim/viewPimModule']");
    private By menuLeave = By.xpath("//a[@href='/web/index.php/leave/viewLeaveModule']");
    private By menuTime = By.xpath("//a[@href='/web/index.php/time/viewTimeModule']");
    private By menuRecruitment = By.xpath("//a[@href='/web/index.php/recruitment/viewRecruitmentModule']");


    private HomePage homePage;
    private LoginPage loginPage;

    public HomePage getHomePage() {
        if(homePage == null) {
            homePage = new HomePage();
        }
        return homePage;
    }

    public LoginPage getLoginPage() {
        if(loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public AdminPage clickAdminPage() {
        clickElement(menuAdmin);
        return new AdminPage();
    }

    public PimPage clickPimPage() {
        clickElement(menuPIM);
        return new PimPage();
    }

    public LeavePage clickLeavePage() {
        clickElement(menuLeave);
        return new LeavePage();
    }

    public TimePage clickTimePage() {
        clickElement(menuTime);
        return new TimePage();
    }

    public RecruitmentPage clickRecruitmentPage() {
        clickElement(menuRecruitment);
        return new RecruitmentPage();
    }
}
