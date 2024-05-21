package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import testComponents.BaseTest;

import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {
    String productName = "ZARA COAT 3";
    @Test(dataProvider = "getData",groups = {"Purchase"})
    public void submitOrder(String email, String password , String productName) throws InterruptedException {

        ProductCatalogue productCatalogue = landingPage.loginApplication(email, password);

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match, "Product not found in the cart");

        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("Egypt");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();

        String confirmMsg = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }


    @Test (dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest()
    {
        //"ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("sou@gmail.com", "123App-es");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

    }

    @DataProvider
	  public Object[][] getData()
	  {
	    return new Object[][]  {{"sou@gmail.com","123App-es","ZARA COAT 3"}, {"esraa@gmail.com","123App-esraa","ADIDAS ORIGINAL" } };

  }


}
