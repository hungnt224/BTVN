package BTVN123;

import net.bytebuddy.asm.Advice;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class btvn123 {
    WebDriver driver;

    @BeforeMethod
    public void setDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
        //Login
        driver.get("https://cms.anhtester.com/login");
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("admin@example.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        //Mở category
        driver.findElement(By.xpath("//span[normalize-space()='Products']")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Category']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//h1[@class='h3']")).getText(),"All categories");
    }

    @AfterMethod
    public void closeDriver() throws InterruptedException {
        Thread.sleep(1000);
        driver.quit();
    }

    @Test(priority = 0)
    public void Add_new_Category() throws InterruptedException {
        //Add new Category
        driver.findElement(By.xpath("//span[normalize-space()='Add New category']")).click();
        //Input name
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("Hungnt-Viettel");
        //Select Parent category
        Select select1 = new Select(driver.findElement(By.xpath("//select[@name='parent_id']")));
        select1.selectByIndex(1);
        //Input ordering number
        driver.findElement(By.xpath("//input[@id='order_level']")).sendKeys("1");
        //Select Type
        Select select2 = new Select(driver.findElement(By.xpath("//select[@name='digital']")));
        select2.selectByIndex(0);
        //Banner
        driver.findElement(By.xpath("(//div[@class='input-group-text bg-soft-secondary font-weight-medium'][normalize-space()='Browse'])[1]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("@");
        driver.findElement(By.xpath("//div[@class='aiz-file-box-wrap']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(500);
        //Icon
        driver.findElement(By.xpath("(//div[@class='input-group-text bg-soft-secondary font-weight-medium'][normalize-space()='Browse'])[2]")).click();
        driver.findElement(By.xpath("//input[@placeholder='Search your files']")).sendKeys("@");
        driver.findElement(By.xpath("//div[@class='aiz-file-box-wrap']")).click();
        driver.findElement(By.xpath("//button[normalize-space()='Add Files']")).click();
        Thread.sleep(500);
        //Meta title
        driver.findElement(By.xpath("//input[@placeholder='Meta Title']")).sendKeys("Viettel Automation");
        //Filtering
        driver.findElement(By.xpath("//div[contains(text(),'Nothing selected')]")).click();
        driver.findElement(By.xpath("//span[normalize-space()='Size']")).click();
        driver.findElement(By.xpath("(//div[@class='form-group mb-0 text-right'])[1]")).click();
        //Save
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Verify Message
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message']")).getText(),"Category has been inserted successfully");
    }
    @Test(priority = 1)
    public void Find_added_category() throws InterruptedException {
        //Search Category
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Hungnt" + Keys.ENTER);
        //Verify added category
        Assert.assertTrue(driver.findElement(By.xpath("//td[normalize-space()='Hungnt-Viettel']")).isDisplayed());
    }
    @Test(priority = 2)
    public void Edit_added_category() throws InterruptedException {
        //Search Category
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("Hungnt" + Keys.ENTER);
        Thread.sleep(500);
        //Edit
        driver.findElement(By.xpath("//a[@title='Edit']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//input[@id='name']")).clear();
        driver.findElement(By.xpath("//input[@id='name']")).sendKeys("NguyenTuanHung");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Get message
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message']")).getText(),"Category has been updated successfully");
    }
    @Test(priority = 3)
    public void Delete_category() throws InterruptedException {
        //Search Category
        driver.findElement(By.xpath("//input[@id='search']")).sendKeys("NguyenTuanHung" + Keys.ENTER);
        Thread.sleep(500);
        //Delete
        driver.findElement(By.xpath("//a[@title='Delete']")).click();
        driver.findElement(By.xpath("//a[@id='delete-link']")).click();
        //Get message
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-notify='message']")).getText(),"Category has been deleted successfully");
    }
}
