package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.ProductCatalogue;
import testComponents.BaseTest;

import java.util.List;

public class ErrorValidationTest extends BaseTest {
    @Test(groups = {"ErrorHandling"})
    public void loginErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
         landingPage.loginApplication("sou@gmail.com", "123*qaAPP");
          landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
    }

    @Test
    public void productErrorValidation() throws InterruptedException {
        String productName = "ZARA COAT 3";
        ProductCatalogue productCatalogue = landingPage.loginApplication("es@gmail.com", "123*qaAPP");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay("Zara cart 33");
        Assert.assertFalse(match);

    }

}

