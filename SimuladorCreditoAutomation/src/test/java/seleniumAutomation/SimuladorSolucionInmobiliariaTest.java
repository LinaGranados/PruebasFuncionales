package seleniumAutomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilidades.ManejoArchivos;

public class SimuladorSolucionInmobiliariaTest {
	public WebDriver driver;

	@BeforeMethod
	public void beforeMethod() {
		System.setProperty("webdriver.chrome.driver", "D:\\seleniumAutomation\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		String url="https://www.grupobancolombia.com/wps/portal/personas/necesidades/casa/simulador-solucion-inmobiliaria/";
		driver.get(url);
	}
	
//	@AfterMethod
//	public void afterMethod() {
//		driver.quit();
//	}
	
	@Test
	public void OpenSolucionInmobiliaria() {
				
		SimuladorSolucionInmobiliariaPage simuladorSolucionInmobiliariaPage=new SimuladorSolucionInmobiliariaPage(driver);
		simuladorSolucionInmobiliariaPage.setTipoFinanciacion("Crédito Hipotecario");
		simuladorSolucionInmobiliariaPage.setDestinoCredito("Apartamento");
		simuladorSolucionInmobiliariaPage.setOpcionSimular("Dependiendo del monto que quiero prestar");
		simuladorSolucionInmobiliariaPage.setPlanAmortizacion("Cuota fija-Tasa fija en pesos");
		simuladorSolucionInmobiliariaPage.setPlazoAnios("5");
		simuladorSolucionInmobiliariaPage.setFechaNacimiento("1981-11-26");
		simuladorSolucionInmobiliariaPage.setDeptoColombia("Antioquia");
		simuladorSolucionInmobiliariaPage.setValorBien("100000000");
		simuladorSolucionInmobiliariaPage.setValorPrestamo("40000000");
		simuladorSolucionInmobiliariaPage.setSubsidioGobierno();
		simuladorSolucionInmobiliariaPage.simularCredito();
		String strValorCuota=simuladorSolucionInmobiliariaPage.getValorCuota();
		
		ManejoArchivos manejoArchivos=new ManejoArchivos();
		manejoArchivos.setNombreArchivo("simuladoresCredito.xls");
		manejoArchivos.setNombreHoja("Simulación Créditos Bancolombia");
		manejoArchivos.leerArchivo();
		manejoArchivos.setValorCelda(0, 2, "Inmobiliaria");
		manejoArchivos.setValorCelda(1, 2, strValorCuota);
		manejoArchivos.fnvGuardarArchivoExcel();
	}
    
}
