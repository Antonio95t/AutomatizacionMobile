package com.nttdata.screens;
import io.appium.java_client.pagefactory.AndroidFindBy;
import net.serenitybdd.core.pages.PageObject;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class demoScreen extends PageObject{

    @AndroidFindBy(xpath = "//android.widget.ImageView[@content-desc=\"Increase item quantity\"]")
    private WebElement bn_agr_producto;

    @AndroidFindBy(xpath = "//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")
    private WebElement bn_agr_carrito;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/cartTV\"]")
    private WebElement btn_img_car;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/noTV\"]")
    private WebElement select_cant_car;

    public void Home_demo(){
        List<WebElement> productNames = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/titleTV\"]"));
        List<WebElement> productImages = getDriver().findElements(By.xpath("//android.widget.ImageView"));
        List<WebElement> productPrices = getDriver().findElements(By.xpath("//android.widget.TextView[@resource-id=\"com.saucelabs.mydemoapp.android:id/priceTV\"]"));
        Assert.assertFalse("No se cargaron los nombres de los productos", productNames.isEmpty());
        Assert.assertFalse("No se cargaron las imágenes de los productos", productImages.isEmpty());
        Assert.assertFalse("No se cargaron los precios de los productos", productPrices.isEmpty());
    }

    public void seleccionar_producto(int producto_u, String producto) {
        String productXpath = "//android.widget.ImageView[@content-desc='"+producto+"']";
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(4));
        try {
            WebElement productbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productXpath)));
            Assert.assertTrue(productbtn.isDisplayed());
            try {
                productbtn.click();
            } catch (Exception e) {
                System.out.println("Finalizó la prueba de manera esperada por seleccionar_producto");
            }
        } catch (TimeoutException e) {
            Assert.fail("No se validó el producto en seleccionar_producto");
        }
        val_producto_u(producto_u);
    }

    public void val_producto_u(int producto_u){
        for (int i = 0; i < producto_u; i++) {
            bn_agr_producto.click();
            WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(3));
            wait.until(ExpectedConditions.elementToBeClickable(bn_agr_producto));
        }
    }

    public String store_product_local(){
        bn_agr_carrito.click(); waitFor(ExpectedConditions.visibilityOf(btn_img_car));
        return btn_img_car.getText();
    }
    public String obtene_item(){
        return select_cant_car.getText();
    }
}
