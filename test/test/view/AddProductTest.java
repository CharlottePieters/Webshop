package test.view;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddProductTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Labo_1_war_exploded/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/Charlotte_Pieters/Documents/Informatica/Stuffs/chromedriver");
        driver = new ChromeDriver();
        driver.get(path+"?action=addProduct");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_Register_AllFieldsFilledInCorrectly_ProductIsAdded() {
        submitForm("Viooltjes", "kleine, paarse bloemen", "1.23");

        String title = driver.getTitle();
        assertEquals("Products",title);

        driver.get(path+"?action=products");

        ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found=false;
        for (WebElement listItem:listItems) {
            if (listItem.getText().contains("Viooltjes") &&  listItem.getText().contains("kleine, paarse bloemen")) {
                found=true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test_AddProduct_NameNotFilledIn_ErrorMessageGivenForNameAndOtherFieldsValueKept(){
        submitForm("", "kleine, paarse bloemen", "1.23");

        String title = driver.getTitle();
        assertEquals("Add Product",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No name given", errorMsg.getText());

        WebElement fieldFirstName=driver.findElement(By.id("name"));
        assertEquals("",fieldFirstName.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("description"));
        assertEquals("kleine, paarse bloemen",fieldLastName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("price"));
        assertEquals("1.23",fieldEmail.getAttribute("value"));
    }

    @Test
    public void test_Register_DescriptionNotFilledIn_ErrorMessageGivenForDescriptionAndOtherFieldsValueKept(){
        submitForm("Viooltjes", "", "1.23");

        String title = driver.getTitle();
        assertEquals("Add Product",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No description given", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("name"));
        assertEquals("Viooltjes",fieldUserid.getAttribute("value"));

        WebElement fieldLastName=driver.findElement(By.id("description"));
        assertEquals("",fieldLastName.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("price"));
        assertEquals("1.23",fieldFirstName.getAttribute("value"));
    }

    @Test
    public void test_Register_PriceNegative_ErrorMessageGivenForPriceAndOtherFieldsValueKept(){
        submitForm("Viooltjes", "kleine, paarse bloemen", "-1.23");

        String title = driver.getTitle();
        assertEquals("Add Product",title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Give a valid price", errorMsg.getText());

        WebElement fieldUserid=driver.findElement(By.id("name"));
        assertEquals("Viooltjes",fieldUserid.getAttribute("value"));

        WebElement fieldFirstName=driver.findElement(By.id("description"));
        assertEquals("kleine, paarse bloemen",fieldFirstName.getAttribute("value"));

        WebElement fieldEmail=driver.findElement(By.id("price"));
        assertEquals("",fieldEmail.getAttribute("value"));
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String name, String description, String price) {
        fillOutField("name", name);
        fillOutField("description", description);
        fillOutField("price", price + "");

        WebElement button=driver.findElement(By.id("addProduct"));
        button.click();
    }

}
