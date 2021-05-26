/*
 * package com.cars24.webdriverinteractions;
 * 
 * import java.io.FileNotFoundException; import java.io.IOException; import
 * java.util.HashMap; import java.util.List; import
 * java.util.concurrent.TimeUnit;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.OutputType; import
 * org.openqa.selenium.TakesScreenshot; import org.openqa.selenium.WebDriver;
 * import org.openqa.selenium.WebElement;
 * 
 * import com.cars24.applicationconstants.ApplicationConstants; import
 * com.cars24.browserfactory.BrowserFactory; import
 * com.cars24.fileoperations.LoadProperties;
 * 
 * import io.cucumber.java.Scenario;
 * 
 * public class WebDriverIntractions { HashMap<String, WebDriver> driverlist =
 * new HashMap<String, WebDriver>(); public static By by;
 * 
 * private String getCurrentThreadName() { try { return
 * Thread.currentThread().getName().isEmpty() ? "default" :
 * Thread.currentThread().getName(); } catch (Exception e) { return "default"; }
 * }
 * 
 * public WebDriver getcurrentDriver() { if
 * (driverlist.get(getCurrentThreadName()) == null) { try {
 * driverlist.put(getCurrentThreadName(),
 * BrowserFactory.getbrowser(LoadProperties.DerivergetProperty(
 * ApplicationConstants.BROWSERNAME))); } catch (Exception e) {
 * e.printStackTrace(); }
 * 
 * } return driverlist.get(getCurrentThreadName()); }
 * 
 * public static By getbyarrgument(String propertry) throws
 * FileNotFoundException, IOException { String bylocator =
 * LoadProperties.getbyproperty(propertry, "bytype"); String byvalue =
 * LoadProperties.getbyproperty(propertry, "byvalue"); switch
 * (bylocator.toUpperCase()) { case "ID": by = By.id(byvalue); break; case
 * "NAME": by = By.name(byvalue); break; case "XPATH": by = By.xpath(byvalue);
 * break; case "CLASSNAME": by = By.className(byvalue); break; case "LINKTEXT":
 * by = By.linkText(byvalue); break; case "PARTIALLINKTEXT": by =
 * By.partialLinkText(byvalue); break; case "CSSSELECTOR": by =
 * By.cssSelector(byvalue); break;
 * 
 * } return by; }
 * 
 * public void get(String URL) { getcurrentDriver().get(URL); }
 * 
 * public void click(String keyvalue) { try {
 * getcurrentDriver().findElement(getbyarrgument(keyvalue)).click(); } catch
 * (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) {
 * e.printStackTrace(); } }
 * 
 * public void SendKeys(String keyvalue, String value) { try {
 * getcurrentDriver().findElement(getbyarrgument(keyvalue)).clear();
 * getcurrentDriver().findElement(getbyarrgument(keyvalue)).sendKeys(value); }
 * catch (IOException e) { e.printStackTrace(); }
 * 
 * }
 * 
 * public WebElement findElement(String keyvalue) { WebElement wbelement = null;
 * try { wbelement = getcurrentDriver().findElement(getbyarrgument(keyvalue)); }
 * catch (IOException e) { e.printStackTrace(); } return wbelement; }
 * 
 * public List<WebElement> findElements(String keyvalue) { List<WebElement>
 * webelements = null; try { webelements =
 * getcurrentDriver().findElements(getbyarrgument(keyvalue)); } catch
 * (IOException e) { e.printStackTrace(); } return webelements; }
 * 
 * public String getTitle() { return getcurrentDriver().getTitle(); }
 * 
 * public void implicitlyWait() {
 * getcurrentDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
 * }
 * 
 * public void close() { getcurrentDriver().close(); }
 * 
 * public void loadEnvironmentProperties(String environment) { if
 * (System.getProperty("env") != null) {
 * LoadProperties.Loadenvironmentvariables(System.getProperty("env")); } else {
 * LoadProperties.Loadenvironmentvariables(environment); }
 * 
 * } public void setScreenshot(String ScreenShotName,Scenario scenario) { byte[]
 * screenshot =
 * ((TakesScreenshot)getcurrentDriver()).getScreenshotAs(OutputType.BYTES);
 * scenario.attach(screenshot, "image/png",ScreenShotName); }
 * 
 * }
 */