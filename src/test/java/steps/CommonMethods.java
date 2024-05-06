package steps;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

public class CommonMethods {
    private static final WebDriver driver = new ChromeDriver();

    public CommonMethods() {
        StepDefinitions.setDriver(driver);
    }

    public static void go_to_site(){
        driver.get("http://localhost:3000/play");
    }

    public WebElement findWebElementByXpath(String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public int getCurrentLevel() {
        String xpath = "/html/body/div/main/div/div[2]/div/div[2]/div/div[1]/div/div[3]/div/div/span";
        WebElement element = new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));

        return Integer.parseInt(element.getText());
    }

    public ArrayList<Integer> getStats() {
        ArrayList<Integer> stats = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            String xpath = "/html/body/div/main/div/div[2]/div/div[2]/div/div/div[1]/div[2]/section/div[" + i + "]/div/span";
            WebElement element = new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            int amount = Integer.parseInt(element.getText());
            stats.add(amount);
        }
        return stats;
    }

    public void verifyConfirmationMessage(String xpath, String message){
        WebElement element = findWebElementByXpath(xpath);
        Assert.assertEquals(message, element.getText());
    }

    public void verifyDisabled(WebElement element, String attribute){
        String disabled = element.getAttribute(attribute);
        Assert.assertNotNull(disabled);
    }

    public static void makeScreenshot(String fileName) throws InterruptedException {
        Thread.sleep(1000);
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        srcFile.renameTo(new File("C:/Users/marti/IdeaProjects/TestCodersRPG/screenshots/endScreen" + fileName + ".png"));
    }

    public static void closeBrowser(){
        driver.close();
    }

}
