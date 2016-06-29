package com.trailblazers.freewheelers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class HumansTxtTest {

    static WebDriver driver;

    @BeforeClass
    public static void before() {
        driver = new FirefoxDriver();
    }

    @AfterClass
    public static void after()
        driver.close();
    }

    @Test
    public void shouldDisplayAllTheTeamMembers() {
        driver.get("http://localhost:8080/humans.txt");
        WebElement element = driver.findElement(By.tagName("pre"));
        String[] expectedTeamMembers = {
                "Ella Holmes",
                "Archanaa Ravikumar",
                "Steve Liu",
                "Durga",
                "Raju",
                "Rufus Raghunath",
                "Sruti Sridhar",
"Siq Liu"
                };
        String[] split = element.getText().split("\n");
        assertThat(split, is(expectedTeamMembers));
        assertThat(split.length, is(7));
    }

}
