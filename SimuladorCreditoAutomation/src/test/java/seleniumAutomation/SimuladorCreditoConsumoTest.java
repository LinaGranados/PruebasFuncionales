package seleniumAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilidades.ManejoArchivos;

public class SimuladorCreditoConsumoTest {
	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumAutomation\\chromedriver.exe");
		driver= new ChromeDriver();
		String url="https://www.grupobancolombia.com/wps/portal/personas/productos-servicios/creditos/consumo/libre-inversion/simulador-credito-consumo/";
		driver.get(url);
	}
	
//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}
	
	@Test
	public void OpenCreditoConsumo() {
		
		SimuladorCreditoConsumoPage simuladorCreditoConsumoPage=new SimuladorCreditoConsumoPage(driver);
		simuladorCreditoConsumoPage.setTipoSimulacion("Simula tu Cuota");
		simuladorCreditoConsumoPage.setFechaNacimiento("1981-11-26");
		simuladorCreditoConsumoPage.setTipoTasa("Tasa Fija");
		simuladorCreditoConsumoPage.setTipoProducto("Crédito de Libre Inversión");
		simuladorCreditoConsumoPage.setSeguroDesempleo();
		simuladorCreditoConsumoPage.setPlazoInversion("60");
		simuladorCreditoConsumoPage.setValorPrestamo("40000000");
		simuladorCreditoConsumoPage.simularCredito();
		String strValorCuota=simuladorCreditoConsumoPage.getValorCuota();
		
		ManejoArchivos manejoArchivos=new ManejoArchivos();
		manejoArchivos.setNombreArchivo("simuladoresCredito.xls");
		manejoArchivos.setNombreHoja("Simulación Créditos Bancolombia");
		manejoArchivos.leerArchivo();
		manejoArchivos.setValorCelda(0, 1, "Consumo");
		manejoArchivos.setValorCelda(1, 1, strValorCuota);
		manejoArchivos.fnvGuardarArchivoExcel();
	}

}
