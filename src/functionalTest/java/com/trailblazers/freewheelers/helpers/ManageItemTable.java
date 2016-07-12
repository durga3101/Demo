package com.trailblazers.freewheelers.helpers;

import org.openqa.selenium.By;

public class ManageItemTable {

    public static By nameFieldFor(String name) {
        return By.xpath("//tbody/tr/td[2]/input[@value='" + name + "']");
    }

    public static By checkBoxFor(String name) {
        return By.xpath("//tbody/tr/td[2]/input[@value='" + name + "']/parent::*/parent::*/td[1]/input[2]");
    }

    public static By toggleAll() {
        return By.className("toggleAll");
    }

    public static By quantityFieldFor(String quantity) {
        return By.xpath("//tbody/tr/td[6]/input[@value='" + quantity + "']");

    }

    public static By priceFieldFor(String price) {
        return By.xpath("//tbody/tr/td[3]/input[@value='" + price + "']");

    }
}
