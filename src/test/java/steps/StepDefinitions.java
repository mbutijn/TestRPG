package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class StepDefinitions {
    private static WebDriver driver;
    private final CommonMethods commonMethods = new CommonMethods();
    private ArrayList<Integer> stats;
    private int currentLevel;
    private WebElement button, fileUpload, inputField, slider;

    public static void setDriver(WebDriver driver) {
        StepDefinitions.driver = driver;
    }

    @Given("the stats for the current build type")
    public void statsForTheCurrentBuildType() {
        stats = commonMethods.getStats();
    }

    @Given("I have a current level")
    public void readCurrentLevel() {
        currentLevel = commonMethods.getCurrentLevel();
    }

    @Given("I have a character name of {int} letters")
    public void VerifyCharacterNameOfLetters(int nameLength) {
        String name = new String(new char[nameLength]).replace('\0', 'a'); // not finished
    }

    @When("I type a character name of {int} letters")
    public void typCharacterName(int nameLength) {
        String nameFieldXpath = "//*[@id=\":r0:-form-item\"]";

        // Find the element
        WebElement nameField = new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.presenceOfElementLocated(By.xpath(nameFieldXpath)));
        String name = new String(new char[nameLength]).replace('\0', 'a');

        // enter the name
        nameField.clear();
        nameField.sendKeys(name);
    }

    @When("I click on the startButton")
    public void clickStart() {
        WebElement startButton = commonMethods.findWebElementByXpath("/html/body/div/main/div/div[2]/div/div[2]/div/div/div[2]/div[2]/form/button");
        startButton.click();
    }

    @When("I change the build type to {}")
    public void changeBuildType(String type) {
        WebElement dropdown = commonMethods.findWebElementByXpath("//*[@id=\":r1:-form-item\"]");
        dropdown.sendKeys(type);
    }

    @When("I click on the button {int} times")
    public void clickOnButtonTimes(int number) {
        button = commonMethods.findWebElementByXpath("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[1]/div[2]/button");

        for (int i = 0; i < number; i++) {
            button.click();
        }
    }

    @When("I click on choose file")
    public void clickOnChooseFile() {
        fileUpload = new WebDriverWait(driver, Duration.ofMillis(2000)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='file']")));
        fileUpload.sendKeys("C:/Users/marti/IdeaProjects/TestCodersRPG/pom.xml");
    }

    @When("I type {} in the Type it! text-field")
    public void enterTypeItTextfield(String input) {
        inputField = commonMethods.findWebElementByXpath("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[3]/div[2]/input");
        inputField.sendKeys(input);
    }

    @When("I move the slider all the way to the right")
    public void moveSliderToTheRight() {
        // Find the slider element
        slider = driver.findElement(By.cssSelector(".flex.items-center.justify-between [role='slider']"));

        // Determine the width of the slider track
        int sliderTrackWidth = slider.getSize().getWidth();

        // Move the slider to the rightmost position
        Actions action = new Actions(driver);
        action.clickAndHold(slider).moveByOffset(sliderTrackWidth + 360, 0).release().perform();

    }

    @Then("I should see a validation message {}")
    public void verifyValidationMessage(String message) {
        WebElement shortNameValidation = commonMethods.findWebElementByXpath("//*[@id=\":r0:-form-item-message\"]");
        String shortNameValidationText = shortNameValidation.getText();

        Assert.assertEquals(message, shortNameValidationText);
    }

    @Then("I should see the stats changed")
    public void verifyStatsChanged() {
        // make a local copy of stats as taken from previous step
        ArrayList<Integer> statsBeforeClicking = stats;

        // update stats
        stats = commonMethods.getStats();

        Assert.assertNotEquals(stats, statsBeforeClicking);
    }

    @Then("I should see a higher level")
    public void verifyLevelUpdated() {
        int levelBeforeClicking = currentLevel; // save currentlevel temporarily
        currentLevel = commonMethods.getCurrentLevel(); // get the update after the clicking
        Assert.assertNotEquals(levelBeforeClicking, currentLevel);
    }

    @Then("I should see a first confirmation message {}")
    public void verifyFirstConfirmationMessage(String message) {
        commonMethods.verifyConfirmationMessage("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[1]/div[2]/span", message);
    }

    @Then("I should see second confirmation message {}")
    public void verifySecondConfirmationMessage(String message) {
        commonMethods.verifyConfirmationMessage("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[2]/div[2]/span", message);
    }

    @Then("I should see a third confirmation message {}")
    public void verifyThirdConfirmationMessage(String message) {
        commonMethods.verifyConfirmationMessage("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[3]/div[2]/span", message);
    }

    @Then("I should see a fourth confirmation message {}")
    public void verifyFourthConfirmationMessage(String message) {
        commonMethods.verifyConfirmationMessage("/html/body/div/main/div/div[2]/div/div[2]/div/div[2]/div[2]/section[4]/div[2]/span[2]", message);
    }

    @Then("I should see the button becomes disabled")
    public void verifyButtonDisabled() {
        commonMethods.verifyDisabled(button, "disabled");
    }

    @Then("I should see the file upload section becomes disabled")
    public void verifyFileUploadDisabled() {
        commonMethods.verifyDisabled(fileUpload, "disabled");
    }

    @Then("I should see the input field becomes disabled")
    public void verifyInputFieldDisabled() {
        commonMethods.verifyDisabled(inputField, "disabled");
    }

    @Then("I should see the slider becomes disabled")
    public void verifySliderDisabled() {
        commonMethods.verifyDisabled(slider, "data-disabled");
    }

}