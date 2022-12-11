package com.automation.qa.actions;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.automation.base.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class ActionEngine extends BaseTest {

	public WebElement getWebElement(By locator) {

		WebElement we = driver.findElement(locator);

		return we;
	}

	public void click(By locator, String locatorName) throws Throwable {
		WebElement we = null;
		boolean flag = false;

		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			scrollPageIntoView(locator);
			we = driver.findElement(locator);
			we.click();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName+extentTest.addScreenCapture(getScreenshot(locatorName)));
			}


		}

	}

	public void type(By locator, String data, String locatorName) throws Throwable {
		WebElement we = null;
		boolean flag = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			we = driver.findElement(locator);
			we.sendKeys(data);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName+extentTest.addScreenCapture(getScreenshot(locatorName)));
			}
		}

	}

	public void selectByVisibleText(By locator, String text, String locatorName) throws Throwable {
		boolean flag = false;

		try {
			WebElement we = getWebElement(locator);
			Select sel = new Select(we);
			sel.selectByVisibleText(text);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName);
			}

		}
		
	}

	public void selectByIndex(By locator, int index, String locatorName) throws Throwable {
		boolean flag = false;

		try {
			WebElement we = getWebElement(locator);
			Select sel = new Select(we);
			sel.selectByIndex(index);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName);
			}

		}
	}

	public void selectByValue(By locator, String value, String locatorName) throws Throwable {
		boolean flag = false;

		try {
			WebElement we = getWebElement(locator);
			Select sel = new Select(we);
			sel.isMultiple();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName);
			}

		}
	}

	public void selectAllOptions(By locator, String locatorName) throws Throwable {
		System.out.println("selectAllOptions");
		boolean flag = false;
		try {
			WebElement we = getWebElement(locator);
			Select sel = new Select(we);
			List<WebElement> listOptions = sel.getOptions();

			System.out.println("Count of options:: " + listOptions.size());
			for (WebElement option : listOptions) {
				option.click();
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				extentTest.log(LogStatus.PASS, "Succefully text is entered into the field " + locatorName);
			} else {
				extentTest.log(LogStatus.FAIL, "Failed to enter text into the field " + locatorName+extentTest.addScreenCapture(getScreenshot(locatorName)));
			}
		}

	}
	
	public String getScreenshot(String locatorName) throws Throwable {
		String screenShotLocation = "";
		try {
			String dateFormate = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			screenShotLocation = System.getProperty("user.dir")+File.separator+"Screenshots"+File.separator+dateFormate+locatorName+".png";
			TakesScreenshot ts = (TakesScreenshot)driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File destination = new File(screenShotLocation);
			FileUtils.copyFile(source, destination);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return screenShotLocation;
		
	}
	
	public boolean acceptAlert() throws Throwable {
		boolean isAlertAccepted = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.accept();
			isAlertAccepted = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(isAlertAccepted) {
				extentTest.log(LogStatus.PASS, " Alert accepted!!");
			}else {
				extentTest.log(LogStatus.FAIL, " Failed to accept Alert!!");
			}
		}

		return isAlertAccepted;
	}
	
	public boolean dismissAlert() throws Throwable {
		boolean isAlertDismissed = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.dismiss();
			isAlertDismissed = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(isAlertDismissed) {
				extentTest.log(LogStatus.PASS, " Alert dismissed!!");
			}else {
				extentTest.log(LogStatus.FAIL, " Failed to dismiss Alert!!");
			}
		}

		return isAlertDismissed;
	}
	
	public String getTextFromAlert() throws Throwable {
		boolean isAlertPresent= false;
		String alertText = "";
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alertText = alert.getText();
			isAlertPresent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(isAlertPresent) {
				extentTest.log(LogStatus.PASS, " Alert text!");
			}else {
				extentTest.log(LogStatus.FAIL, " Failed to get ");
			}
		}

		return alertText;
	}
	
	public void enterTextInAlertBox(String text) throws Throwable {
		boolean isAlertPresent= false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			alert.sendKeys(text);
			isAlertPresent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(isAlertPresent) {
				extentTest.log(LogStatus.PASS, " Enter text!");
			}else {
				extentTest.log(LogStatus.FAIL, " Failed to get ");
			}
		}

	}
	
	public String getText(By locator) throws Throwable {
		String str = "";
		try {
			WebElement we = getWebElement(locator);
			str = we.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public void switchToWindow(int index) throws Throwable {
		try {
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			
			
//			for(String s: tabs) {
//				System.out.println("Window id: "+s);
//			}
			
			driver.switchTo().window(tabs.get(index));
			//driver.switchTo().frame(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void switchToFrame(int index) throws Throwable {
		try {
		//	ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().frame("frame2");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollPageIntoView(By locator) throws Throwable {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    WebElement element = getWebElement(locator);
	    // This will scroll the page Horizontally till the element is found
	    js.executeScript("arguments[0].scrollIntoView();", element);
	  }

	
	public void keyBoardEnter(By locator) throws Throwable{
		WebElement we;
		try {
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			we = driver.findElement(locator);
			we.sendKeys(Keys.ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void mouseHoverOnWebElement(By locator) throws Exception {
		try {
		Actions actions = new Actions(driver);
		WebElement we = driver.findElement(locator);
		actions.moveToElement(we).perform();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public void doubleClickOnWebElement(By locator) throws Exception {
		try {
		Actions actions = new Actions(driver);
		WebElement we = driver.findElement(locator);
		actions.doubleClick(we).perform();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
