package seleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SimuladorCreditoLibranzaApp {

	WebDriver driver;
	WebElement cmbDestinoPrestamo;
	WebElement cmbSegmento;
	WebElement txtMonto;
	WebElement txtDuracion;
	WebElement cmbTipoTasa;
	WebElement botonCalcular;

	public SimuladorCreditoLibranzaApp(WebDriver driver) {
		this.driver = driver;
		cmbDestinoPrestamo = driver.findElement(By.id("com.todo1.mobile:id/spinnerDestino"));
		cmbSegmento = driver.findElement(By.id("com.todo1.mobile:id/spinnerSegmento"));
		txtMonto = driver.findElement(By.id("com.todo1.mobile:id/edittextMonto"));
		txtDuracion = driver.findElement(By.id("com.todo1.mobile:id/edittextDuracion"));
		cmbTipoTasa = driver.findElement(By.id("com.todo1.mobile:id/spinnerTasa"));
		botonCalcular = driver.findElement(By.id("com.todo1.mobile:id/btnUser"));
	}

	public void setDestinoPrestamo(String strDestinoPrestamo) {
		Select destinoPrestamoSelect = new Select(cmbDestinoPrestamo);
		destinoPrestamoSelect.selectByVisibleText(strDestinoPrestamo);
	}
	
	public void setSegmento(String strSegmento) {
		Select segmentoSelect = new Select(cmbSegmento);
		segmentoSelect.selectByVisibleText(strSegmento);
	}

	public void setMonto(String strMonto) {
		txtMonto.sendKeys(strMonto);
	}

	public void setDuracion(String strDuracion) {
		txtDuracion.sendKeys(strDuracion);
	}

	public void setTipoTasa(String strTipoTasa) {
		Select tipoTasaSelect = new Select(cmbTipoTasa);
		tipoTasaSelect.selectByVisibleText(strTipoTasa);
	}

	public void simularCredito() {
		botonCalcular.click();
	}

	public String getValorCuota() {
		String strValorCuota = driver.findElement(By.id("com.todo1.mobile:id/textviewMsg")).getText();
		strValorCuota = strValorCuota.substring(strValorCuota.indexOf("$"), strValorCuota.indexOf(". A"));
		return strValorCuota.replace("$","").replace(",","");
	}

}
