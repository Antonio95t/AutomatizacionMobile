package com.nttdata.steps;

import org.junit.Assert;

public class demoSteps {
    com.nttdata.screens.demoScreen demoScreen;
    public void validateAllproducts(){
        demoScreen.Home_demo();
    }
    public void agregateCart(int unidades, String producto) {
        demoScreen.seleccionar_producto(unidades,producto);
    }
    public void validateCart() {
        Assert.assertEquals(demoScreen.store_product_local(), demoScreen.obtene_item());
    }
}
