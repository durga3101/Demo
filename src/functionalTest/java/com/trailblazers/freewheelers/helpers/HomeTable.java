package com.trailblazers.freewheelers.helpers;

import org.openqa.selenium.By;

public class HomeTable {
    public static By nameFieldFor(String name) {
        return By.xpath("//tbody/tr/td[2][text()='" + name + "']");
    }
}
