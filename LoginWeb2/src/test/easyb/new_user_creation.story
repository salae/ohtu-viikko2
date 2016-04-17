import ohtu.*
import ohtu.services.*
import ohtu.data_access.*
import ohtu.domain.*
import ohtu.io.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;


description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""

scenario "creation succesfull with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("leena");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sala1nen");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("sala1nen");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka123");
        element = driver.findElement(By.name("password"));
        element.sendKeys("huuhaa55");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("huuhaa55");
        element = driver.findElement(By.name("add"));
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));       
        element.click();
        element = driver.findElement(By.linkText("logout"));       
        element.click();
    }

    then  'new credentials allow logging in to system', {
        element = driver.findElement(By.linkText("login"));       
        element.click();  
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka123");
        element = driver.findElement(By.name("password"));
        element.sendKeys("huuhaa55");
        element = driver.findElement(By.name("login"));
        element.submit();
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe true
    }
}

scenario "creation fails with correct username and too short password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a valid username and too short password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka666");
        element = driver.findElement(By.name("password"));
        element.sendKeys("muu");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with correct username and pasword consisting of letters", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a valid username and password consisting of letters are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("timo");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkepaaa");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains ("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with too short username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a too sort username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pek");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep7890");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "creation fails with already taken username and valid pasword", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a already taken username and valid password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("sala1nen");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then 'new user is not be registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe false
    }
}

scenario "can not login with account that is not succesfully created", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        element = driver.findElement(By.linkText("register new user"));       
        element.click();       
    }
    when 'a invalid username/password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("p");
        element = driver.findElement(By.name("password"));
        element.sendKeys("hgrrr44556");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("hgrrr44556");
        element = driver.findElement(By.name("add"));
        element.submit();
    }
    then  'new credentials do not allow logging in to system', {
        element = driver.findElement(By.linkText("back to home"));       
        element.click();
        element = driver.findElement(By.linkText("login"));       
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("p");
        element = driver.findElement(By.name("password"));
        element.sendKeys("hgrrr44556");
        element = driver.findElement(By.name("login"));
        element.submit();
        driver.getPageSource().contains("Welcome to Ohtu Application!").shouldBe false

    }
}