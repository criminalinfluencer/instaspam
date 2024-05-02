import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

class InstaScript {
    private WebDriver browser;
    private String username;
    private String password;
    private String victimUsername;
    private int numberOfMessages;

    public InstaScript(String username, String password, String victimUsername, int numberOfMessages) {
        this.username = username;
        this.password = password;
        this.victimUsername = victimUsername;
        this.numberOfMessages = numberOfMessages;
        System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
        this.browser = new FirefoxDriver();
    }

    public void login() {
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        browser.get("https://www.instagram.com/");
        
        WebElement usernameInput = browser.findElement(By.name("username"));
        WebElement passwordInput = browser.findElement(By.name("password"));
        WebElement loginButton = browser.findElement(By.xpath("//button[@type='submit']"));
        
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
    }

    public void victimProfile() {
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        WebDriverWait wait = new WebDriverWait(browser, 5);
        
        WebElement notNowButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Not Now']")));
        notNowButton.click();
        
        WebElement searchSpan = browser.findElement(By.xpath("//span[text()='Search']"));
        searchSpan.click();
        
        WebElement searchInput = browser.findElement(By.xpath("//input[@placeholder='Search']"));
        searchInput.sendKeys(victimUsername);
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='" + victimUsername + "']"))).click();
    }

    public void spamming() {
        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        
        WebDriverWait wait = new WebDriverWait(browser, 5);
        
        WebElement messageButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button']")));
        messageButton.click();
        
        WebElement messageArea = browser.findElement(By.xpath("//textarea[@placeholder='Message...']"));
        messageArea.click();
        messageArea.sendKeys("Revenge, the sweetest morsel to the mouth that ever was cooked in hell.", Keys.ENTER);
        
        for (int i = 0; i < numberOfMessages; i++) {
            messageArea.sendKeys("Random spam message", Keys.ENTER);
        }
        
        browser.quit();
    }
}

public class InstagramBot {
    public static void main(String[] args) {
        InstaScript bot = new InstaScript("your_username", "your_password", "victim_username", 10);
        bot.login();
        bot.victimProfile();
        bot.spamming();
    }
}
