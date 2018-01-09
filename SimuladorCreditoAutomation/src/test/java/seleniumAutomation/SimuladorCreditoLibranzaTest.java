package seleniumAutomation;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import utilidades.ManejoArchivos;

public class SimuladorCreditoLibranzaTest {
	public AppiumDriver<MobileElement> driver;

	@BeforeMethod
	public void beforeMethod() throws MalformedURLException, InterruptedException {

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", "My Phone");
		caps.setCapability("udid", "9885e6393543444c31");
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "7.0");
		caps.setCapability("appPackage", "com.todo1.mobile");
		caps.setCapability("appActivity", "com.todo1.mobile.ui.contenido.splash");
		caps.setCapability("noReset", "true");
//		caps.setCapability("newCommandTimeout", "1000");
		
		driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		Thread.sleep(10000);
	}
	
//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}
	
	@Test
	public void OpenCreditoLibranza() throws InterruptedException{
		
		driver.findElement(By.id("com.todo1.mobile:id/imbMas")).click();
		
		WebElement parentElement = driver.findElement(By.id("com.todo1.mobile:id/listviewData"));
		List<WebElement> childElements = parentElement.findElements(By.className("android.widget.LinearLayout"));
		WebElement mainElement = childElements.get(6);
		mainElement.findElement(By.className("android.widget.TextView")).click();
//		driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[3]//*[7]")).click();
//		WebElement recomendaciones = driver.findElement(By.xpath("//android.widget.LinearLayout[@bounds ='[0,428][1080,545]']"));
//        recomendaciones.click(); 
		
		selectOption("com.todo1.mobile:id/textviewInfo","Libranza");
//		WebElement creditoPersonalElement = driver.findElement(By.xpath("//*[1]//*[1]//*[1]//*[1]//*[3]//*[2]//*[1]"));
//		creditoPersonalElement.click();
		
//		driver.findElement(By.name("Crédito Personal")).click();
//		MobileElement papa = driver.findElement(By.id("com.todo1.mobile:id/containerList"));
//		MobileElement parentElement2 = papa.findElement(By.id("com.todo1.mobile:id/listviewData"));
//		List<MobileElement> childElements2 = parentElement2.findElements(By.className("android.widget.LinearLayout"));
//		MobileElement childElement = childElements2.get(1);
////		List<MobileElement> otherChildElements = childElement.findElements(By.className("android.widget.LinearLayout"));
////		MobileElement mainElement2 = childElement.findElement(By.className("android.widget.LinearLayout"));//otherChildElements.get(0);
//		childElement.findElement(By.className("android.widget.TextView")).click();
		
//		List<WebElement> layouts = driver.findElements(By.className("android.widget.LinearLayout"));
//		layouts.get(5).click();
		
		SimuladorCreditoLibranzaApp simuladorCreditoLibranzaApp=new SimuladorCreditoLibranzaApp(driver);
		simuladorCreditoLibranzaApp.setDestinoPrestamo("Crédito Personal");
		simuladorCreditoLibranzaApp.setSegmento("Preferencial");
		simuladorCreditoLibranzaApp.setMonto("40000000");
		simuladorCreditoLibranzaApp.setDuracion("60");
		simuladorCreditoLibranzaApp.setTipoTasa("Tasa Fija (Mes Vencida)");
		simuladorCreditoLibranzaApp.simularCredito();
		String strValorCuota=simuladorCreditoLibranzaApp.getValorCuota();
		
		ManejoArchivos manejoArchivos=new ManejoArchivos();
		manejoArchivos.setNombreArchivo("simuladoresCredito.xls");
		manejoArchivos.setNombreHoja("Simulación Créditos Bancolombia");
		manejoArchivos.leerArchivo();
		manejoArchivos.setValorCelda(0, 4, "CreditoLibranzaApp");
		manejoArchivos.setValorCelda(1, 4, strValorCuota);
		manejoArchivos.fnvGuardarArchivoExcel();
	}
	
	private void selectOption(String element, String value) throws InterruptedException{
		Thread.sleep(3000);
		List<MobileElement> lista = driver.findElementsById(element);
		for(MobileElement elem:lista){
			if(elem.getText().equals(value)){
				elem.click();
				break;
			}
		}
	}

}
