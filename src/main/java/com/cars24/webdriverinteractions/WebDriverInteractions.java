package com.cars24.webdriverinteractions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cars24.applicationconstants.ApplicationConstants;
import com.cars24.browserfactory.BrowserFactory;
import com.cars24.fileoperations.LoadProperties;
import com.google.common.base.Function;

import io.cucumber.java.Scenario;

public class WebDriverInteractions {
	private HashMap<String, WebDriver> driverList = new HashMap<String, WebDriver>();
	private HashMap<String, ArrayList<String>> windowHandlesList = new HashMap<String, ArrayList<String>>();

	public WebDriverInteractions() {

	}

	public boolean isCurrentDriverNull() {
		if (driverList.get(Thread.currentThread().getName()) == null) {
			return true;
		} else {
			return false;
		}
	}

	private String getCurrentThreadName() {
		try {
			return Thread.currentThread().getName().isEmpty() ? "default" : Thread.currentThread().getName();
		} catch (Exception e) {
			return "default";
		}
	}

	public synchronized WebDriver getCurrentDriver() {

		if (driverList.get(getCurrentThreadName()) == null) {
			windowHandlesList.put(getCurrentThreadName(), new ArrayList<String>());
			windowHandlesList.get(getCurrentThreadName()).clear();
			try {
				driverList.put(getCurrentThreadName(),
						BrowserFactory.getbrowser(LoadProperties.DerivergetProperty(ApplicationConstants.BROWSERNAME)));
			} catch (UnreachableBrowserException e) {
				driverList.put(getCurrentThreadName(),
						BrowserFactory.getbrowser(LoadProperties.DerivergetProperty(ApplicationConstants.BROWSERNAME)));
			} catch (WebDriverException e) {
				driverList.put(getCurrentThreadName(),
						BrowserFactory.getbrowser(LoadProperties.DerivergetProperty(ApplicationConstants.BROWSERNAME)));
			}
			addWindowHandleToArrayList();
		}
		return driverList.get(getCurrentThreadName());
	}

	public void switchtoframe(int num) {
		getCurrentDriver().switchTo().defaultContent();
		getCurrentDriver().switchTo().frame(num);
	}

	public void switchtoframe(String framename) {
		waitForPageLoad();
		try {
			getCurrentDriver().switchTo().frame(getCurrentDriver().findElement(By.className(framename)));
		} catch (NoSuchFrameException | NoSuchElementException e) {
			getCurrentDriver().switchTo().frame(getCurrentDriver().findElement(By.id(framename)));

		}
	}

	public Actions getActionsInstance() {
		Actions seriesofAction = new Actions(getCurrentDriver());
		return seriesofAction;

	}

	public void quitAllBrowsers() throws UnreachableBrowserException {
		try {
			if (driverList.get(getCurrentThreadName()) != null) {
				getCurrentDriver().quit();
				driverList.remove(getCurrentThreadName());
				windowHandlesList.remove(getCurrentThreadName());
			}
		} catch (Exception e) {
		}
	}

	public String captureText(String propName) throws IOException {
		waitForPageLoad();
		String elementValue = "";
		elementValue = findElement(LoadProperties.DerivergetProperty(propName)).getText();
		return elementValue;
	}

	public FluentWait<WebDriver> getFluentWait() {
		List<Class<? extends Throwable>> ignoreExceptionClasses = new ArrayList<Class<? extends Throwable>>();
		ignoreExceptionClasses.add(NoSuchElementException.class);
		ignoreExceptionClasses.add(ElementNotVisibleException.class);
		ignoreExceptionClasses.add(InvalidElementStateException.class);
		ignoreExceptionClasses.add(StaleElementReferenceException.class);
		try {
			FluentWait<WebDriver> wait = new FluentWait<WebDriver>(getCurrentDriver())
					.ignoreAll(ignoreExceptionClasses);
			return wait;
		} catch (Exception e) {
		}
		return null;

	}

	public void uploadFile_enterfilepathname(String propName, String filePath) throws IOException {
		WebElement fileUploadWebElement = findElement(propName);
		fileUploadWebElement.sendKeys(filePath);
	}

	public void Javascript_sendkeys(String propName, String strValue) throws IOException {
		waitForPageLoad();
		WebElement webElement = findElement(propName);
		webElement.clear();
		((JavascriptExecutor) getCurrentDriver()).executeScript("arguments[0].value = arguments[1];", webElement,
				strValue);
	}

	public void sendKeys(String propName, String strValue) throws IOException {
		waitForPageLoad();
		WebElement webElement = findElement(propName);
		webElement.clear();
		webElement.sendKeys(strValue);
	}

	public List<WebElement> findElements(String propName) throws IOException {
		return findElements(propName, false);
	}

	public List<WebElement> findElements(final By locator) {
		return findElements(locator, false);
	}

	public List<WebElement> findElements(String propName, boolean waitForElementsVisibility) throws IOException {
		return findElements(LoadProperties.getbyarrgument(propName), waitForElementsVisibility);
	}

	public String getEditboxDefaultValue(String propName) throws IOException {
		return findElement(propName).getAttribute("value");
	}

	public List<WebElement> findElements(final By locator, boolean waitForElementsVisibility) {
		FluentWait<WebDriver> wait = getFluentWait();
		if (waitForElementsVisibility) {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} else {
			return wait.until(new Function<WebDriver, List<WebElement>>() {
				public List<WebElement> apply(WebDriver webDriver) {
					return webDriver.findElements(locator);
				}
			});
		}
	}

	public WebElement getParent(final By locator) {
		return findElement(locator).findElement(By.xpath(".."));
	}

	public WebElement getParent(String propName) throws IOException {
		return findElement(propName).findElement(By.xpath(".."));
	}

	public WebElement findElement(final By locator) {
		FluentWait<WebDriver> wait = getFluentWait();
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver webDriver) {
				return webDriver.findElement(locator);
			}
		});
	}

	public WebElement findElement(String propName) throws IOException {
		waitForPageLoad();
		return findElement(LoadProperties.getbyarrgument(propName));
	}

	public void select(String propName, String strValue) throws IOException {
		waitForPageLoad();
		Select select = new Select(findElement(propName));
		select.selectByVisibleText(strValue);
	}

	public void selectByValue(String propName, String strValue) throws IOException {
		waitForPageLoad();
		Select select = new Select(findElement(propName));
		select.selectByValue(strValue);
	}

	public void selectByIndex(String propName, String strValue) throws IOException {
		waitForPageLoad();
		int index = Integer.parseInt(strValue);
		Select select = new Select(findElement(propName));
		select.selectByIndex(index);

	}

	public String getSelectedItemInList(String propName) throws IOException {
		waitForPageLoad();
		Select select = new Select(findElement(propName));
		return select.getFirstSelectedOption().getText();

	}

	public void click(String propName) {
		try {
			waitForPageLoad();
			click(LoadProperties.getbyarrgument(propName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForPageLoad() {
		FluentWait<WebDriver> wait = getFluentWait();
		try {
			Thread.sleep(1000);
			wait.until(new Function<WebDriver, Boolean>() {

				public Boolean apply(WebDriver webDriver) {
					return String.valueOf(((JavascriptExecutor) webDriver).executeScript("return document.readyState"))
							.equals("complete");
				}
			});
		} catch (Exception e) {
		}
	}

	public void waitforAjaxload() {
		try {
			Thread.sleep(1000);
			for (int i = 0; i <= 30; i++) {
				if (getCurrentDriver().findElement(By.cssSelector(".processing-modal-content")).isDisplayed()) {
					Thread.sleep(2000);
				} else {
					break;
				}
			}
		} catch (Exception e) {

		}
	}

	public void clickOnNonHiddenElement(String propName) throws Throwable {
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						link.get(i).click();
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
	}

	public void click(By locator) throws IOException {
		FluentWait<WebDriver> wait = getFluentWait();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		} catch (WebDriverException webDriverException) {
			if (webDriverException.getMessage().contains("Element is not clickable at point")) {
				throw webDriverException;
			} else {
				throw webDriverException;
			}
		}
	}

	public void maximize() {
		getCurrentDriver().manage().window().maximize();
	}

	public void clickUsingJSExecutor(WebElement webElement) throws IOException {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) getCurrentDriver();
		jsExecutor.executeScript("arguments[0].click();", webElement);
		waitForPageLoad();
	}

	public void clickUsingJSExecutor(String locator) throws IOException {
		clickUsingJSExecutor(findElement(locator));
	}

	public void check(String propName) throws IOException {
		if (!findElement(propName).isSelected())
			click(propName);
	}

	public void uncheck(String propName) throws IOException {
		if (findElement(propName).isSelected())
			click(propName);
	}

	public String capturePageTitle() {
		return getCurrentDriver().getTitle();
	}

	public void closeCurrentActiveBrowser() {
		windowHandlesList.get(getCurrentThreadName()).remove(getCurrentDriver().getWindowHandle());
		try {
			getCurrentDriver().close();
		} catch (Exception e) {

		}
	}

	public String acceptAlert() {
		String alertText = "";
		FluentWait<WebDriver> fluentWait = getFluentWait();
		fluentWait.until(ExpectedConditions.alertIsPresent());
		alertText = getCurrentDriver().switchTo().alert().getText();
		getCurrentDriver().switchTo().alert().accept();
		return alertText;
	}

	public String dismissAlert() {
		String alertText = "";
		FluentWait<WebDriver> fluentWait = getFluentWait();
		fluentWait.until(ExpectedConditions.alertIsPresent());
		alertText = getCurrentDriver().switchTo().alert().getText();
		getCurrentDriver().switchTo().alert().dismiss();
		return alertText;
	}

	public boolean isAlertPresent() {
		try {
			getCurrentDriver().switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/*
	 * public String handleproxydialog(String username, String password) { String
	 * alertText = ""; FluentWait<WebDriver> fluentWait = getFluentWait();
	 * fluentWait.until(ExpectedConditions.alertIsPresent()); alertText =
	 * getCurrentDriver().switchTo().alert().getText(); Credentials user = new
	 * UserAndPassword(username, password);
	 * getCurrentDriver().switchTo().alert().authenticateUsing(user); return
	 * alertText; }
	 */

	public void get(String strURL) {
		getCurrentDriver().get(strURL);
		waitForPageLoad();
	}

	public boolean isDisplayed(String propName) throws IOException {
		if (findElements(LoadProperties.DerivergetProperty(propName)).size() > 0) {
			return true;
		}
		return false;
	}

	public boolean isEnabled(String propName) throws IOException {
		if (findElement(propName).isEnabled())
			return true;
		return false;
	}

	public boolean isSelected(String propName) throws IOException {
		if (findElement(propName).isSelected())
			return true;
		return false;
	}

	public void pressKeyEvent(String Key) throws Exception {
		// Thread.sleep(3000);
		Robot rb = new Robot();
		if (Key.equals("ESCAPE")) {
			rb.keyPress(KeyEvent.VK_ESCAPE);
			rb.keyRelease(KeyEvent.VK_ESCAPE);
		} else if (Key.equals("TAB")) {
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
		}
	}

	public String captureToolTipText(String propName) throws IOException {
		Actions seriesofAction = new Actions(getCurrentDriver());
		WebElement NewButton = findElement(propName);
		seriesofAction.moveToElement(NewButton).build().perform();
		String Tooltip = NewButton.getAttribute("title");
		seriesofAction.release();
		return Tooltip;
	}

	public void mouseHover(String propName) throws IOException {
		Actions seriesofAction = new Actions(getCurrentDriver());
		WebElement NewButton = findElement(propName);
		seriesofAction.moveToElement(NewButton).build().perform();
	}

	private void addWindowHandleToArrayList() {
		for (String winHandle : driverList.get(getCurrentThreadName()).getWindowHandles()) {
			if (!windowHandlesList.get(getCurrentThreadName()).contains(winHandle)) {
				windowHandlesList.get(getCurrentThreadName()).add(winHandle);
			}
		}

	}

	public void switchtonewwindow() throws Exception {
		addWindowHandleToArrayList();
		try {
			getCurrentDriver().switchTo().window(windowHandlesList.get(getCurrentThreadName())
					.get(windowHandlesList.get(getCurrentThreadName()).size() - 1));
		} catch (Exception e) {

		}
	}

	public void uncheckAllCheckboxes() throws Exception {
		List<WebElement> checkboxs = findElements(By.xpath("//input[@type='checkbox']"));

		for (int i = 0; i < checkboxs.size(); i++)
			if (checkboxs.get(i).isDisplayed()) {
				if (checkboxs.get(i).isSelected()) {
					click(checkboxs.get(i));
				}
			}
	}

	public void click(WebElement webElement) {
		try {
			webElement.click();
		} catch (WebDriverException webDriverException) {
			if (webDriverException.getMessage().contains("Element is not clickable at point")) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) getCurrentDriver();
				jsExecutor.executeScript("arguments[0].click();", webElement);
			} else {
				throw webDriverException;
			}
		}
	}

	public void checkAllCheckboxes() throws Exception {
		List<WebElement> checkboxs = findElements(By.xpath("//input[@type='checkbox']"));
		for (int i = 0; i < checkboxs.size(); i++)
			if (checkboxs.get(i).isDisplayed()) {
				if (!checkboxs.get(i).isSelected()) {
					click(checkboxs.get(i));
				}
			}
	}

	public void back() {
		getCurrentDriver().navigate().back();
	}

	public void refresh() {
		getCurrentDriver().navigate().refresh();
	}

	public String getCurrentUrl() throws IOException {
		return this.getCurrentDriver().getCurrentUrl();
	}

	public String getAttributeValue(String propName, String AttributeName) throws IOException {
		return findElement(propName).getAttribute(AttributeName);
	}

	public String getSelectedItemValueInList(String propName) throws Exception {
		Select select = new Select(findElement(propName));
		return select.getFirstSelectedOption().getAttribute("value");
	}

	public void switchControlToHTML(String frameName) throws IOException {
		getCurrentDriver().switchTo().defaultContent();
		getCurrentDriver().switchTo().frame(findElement(frameName));
	}

	public void switchtodefaultContent() {
		getCurrentDriver().switchTo().defaultContent();
	}

	public List<WebElement> getAllDropdownListOptions(String propName) throws Exception {
		WebElement elem = findElement(propName);
		Select select = new Select(elem);
		List<WebElement> options = select.getOptions();
		return options;
	}

	public void selectDropdownListOptionWithPartialText(String propName, String text) throws Exception {

		List<WebElement> optionsList = getAllDropdownListOptions(propName);
		for (WebElement option : optionsList) {
			if (option.getText().contains(text)) {
				option.click();
				break;
			}
		}
	}

	public boolean isOptionPresentInDropDownListOptions(String propName, String optionValue) throws Exception {
		boolean returnValue = false;
		List<WebElement> optionsList = getAllDropdownListOptions(propName);
		for (WebElement option : optionsList) {
			if (option.getText().equals(optionValue)) {
				returnValue = true;
				break;
			}
		}
		return returnValue;
	}

	public void selectAllItemsFromListbox(String propName) throws IOException {

		WebElement list = findElement(propName);
		List<WebElement> lstOptions = list.findElements(By.tagName("option"));
		list.sendKeys(Keys.CONTROL);
		for (int i = 0; i < lstOptions.size(); i++) {
			if (!lstOptions.get(i).isSelected()) {
				lstOptions.get(i).click();
			}
		}

	}

	public void deSelectAllItemsFromListbox(String propName) throws IOException {

		WebElement list = findElement(propName);
		List<WebElement> lstOptions = list.findElements(By.tagName("option"));
		list.sendKeys(Keys.CONTROL);
		for (int i = 0; i < lstOptions.size(); i++) {
			if (lstOptions.get(i).isSelected()) {
				lstOptions.get(i).click();
			}
		}

	}

	@SuppressWarnings("unused")
	public List<WebElement> getAllSelectedItemsFromListbox(String propName) throws IOException {
		List<WebElement> allselectedItems = new ArrayList<WebElement>();
		WebElement list = findElement(propName);
		Select sel = new Select(list);
		return allselectedItems = sel.getAllSelectedOptions();
	}

	public String getNonHiddenElementText(String propName) throws Throwable {
		String element = null;
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						element = link.get(i).getAttribute("value");
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
		return element;
	}

	public boolean getNonHiddenElementEnable(String propName) throws Throwable {
		boolean flag = false;
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						flag = link.get(i).isEnabled();
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
		return flag;
	}

	public void clickNonHiddenElement(String propName) throws Throwable {
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						link.get(i).click();
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
	}

	public void clickNonHiddenWebElement(List<WebElement> wb) throws Throwable {
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {

				for (int i = 0; i < wb.size(); i++) {
					if (wb.get(i).isDisplayed()) {
						wb.get(i).click();
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
	}

	public void selectonHiddenElement(String propName, String value) throws Throwable {
		boolean blnContinueWhile = true;
		while (blnContinueWhile) {
			blnContinueWhile = false;
			try {
				List<WebElement> link = findElements(propName);
				for (int i = 0; i < link.size(); i++) {
					if (link.get(i).isDisplayed()) {
						Select sel = new Select(link.get(i));
						sel.selectByVisibleText(value);
						break;
					}
				}
			} catch (StaleElementReferenceException staleElementReferenceException) {
				blnContinueWhile = true;
			}
		}
	}

	public static int getrandomnumber(int high, int low) throws IOException {
		Random rnum = new Random();
		return rnum.nextInt(high - low) + low;

	}

	public void explicitwait(String property) throws IOException {
		WebDriverWait wait = new WebDriverWait(getCurrentDriver(), 60);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(LoadProperties.getbyarrgument(property)));
	}

	public void explicitWaitWithStaleElement(String property) throws IOException {
		WebDriverWait wait = new WebDriverWait(getCurrentDriver(), 500);
		wait.until(ExpectedConditions.visibilityOf(findElement(LoadProperties.getbyarrgument(property))));
	}

	public void sendvalue(String propName, String strValue) throws IOException {
		WebElement webElement = getCurrentDriver().findElement(LoadProperties.getbyarrgument(propName));
		Actions act = new Actions(getCurrentDriver());
		act.sendKeys(webElement, strValue).build().perform();

	}

	public void clearvalue(String propName) throws IOException, InterruptedException {
		WebElement webElement = getCurrentDriver().findElement(LoadProperties.getbyarrgument(propName));
		Actions act = new Actions(getCurrentDriver());
		act.sendKeys(webElement, Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys("\u0008").build().perform();

	}

	public String getCurrentWindowHandle() {
		return getCurrentDriver().getWindowHandle();

	}

	public void switchToWindow(String parentWindow) {
		getCurrentDriver().switchTo().window(parentWindow);
	}

	public void pressCONTROLKeyEvent() throws AWTException {
		Robot rb = new Robot();
		rb.keyPress(KeyEvent.VK_CONTROL);
	}

	public void rleaseCONTROLKeyEvent() throws AWTException {
		Robot rb = new Robot();
		rb.keyRelease(KeyEvent.VK_CONTROL);
	}

	public void scrollwindow() {
		try {
			((JavascriptExecutor) getCurrentDriver()).executeScript("window.scrollBy(0,250)", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setScreenshot(Scenario scenario, String screenshotName) {
		byte[] screenshot = ((TakesScreenshot) getCurrentDriver()).getScreenshotAs(OutputType.BYTES);
		scenario.attach(screenshot, "image/png", screenshotName);
	}

	public void loadEnvironmentProperties(String environment) {
		if (System.getProperty("env") != null) {
			LoadProperties.Loadenvironmentvariables(System.getProperty("env"));
		} else {
			LoadProperties.Loadenvironmentvariables(environment);
		}
	}

}
