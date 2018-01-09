package seleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SimuladorCreditoConsumoPage {

	WebDriver driver;
	WebElement cmbTipoSimulacion;
	WebElement cmbFechaNacimiento;
	WebElement cmbTipoTasa;
	WebElement cmbTipoProducto;
	WebElement chkSeguroDesempleo;
	WebElement txtPlazoInversion;
	WebElement txtValorPrestamo;
	WebElement botonSimular;

	public SimuladorCreditoConsumoPage(WebDriver driver) {
		this.driver = driver;
		cmbTipoSimulacion = driver.findElement(By.name("comboTipoSimulacion"));
		cmbFechaNacimiento = driver.findElement(By.name("dateFechaNacimiento"));
		cmbTipoTasa = driver.findElement(By.name("comboTipoTasa"));
		cmbTipoProducto = driver.findElement(By.name("comboTipoProducto"));
		chkSeguroDesempleo = driver.findElement(By.name("checkSeguroDesempleo"));
		txtPlazoInversion = driver.findElement(By.name("textPlazoInversion"));
		txtValorPrestamo = driver.findElement(By.name("textValorPrestamo"));
		botonSimular = driver.findElement(By.tagName("button"));
	}

	public void setTipoSimulacion(String strTipoSimulacion) {
		Select tipoSimulacionSelect = new Select(cmbTipoSimulacion);
		tipoSimulacionSelect.selectByVisibleText(strTipoSimulacion);
	}

	public void setFechaNacimiento(String strFechaNacimiento) {
		cmbFechaNacimiento.sendKeys(strFechaNacimiento);
	}

	public void setTipoTasa(String strTipoTasa) {

		Select tipoTasaSelect = new Select(cmbTipoTasa);
		tipoTasaSelect.selectByVisibleText(strTipoTasa);

	}

	public void setTipoProducto(String strTipoProducto) {
		Select tipoProductoSelect = new Select(cmbTipoProducto);
		tipoProductoSelect.selectByVisibleText(strTipoProducto);
	}

	public void setSeguroDesempleo() {
		chkSeguroDesempleo.click();
	}

	public void setPlazoInversion(String strPlazoInversion) {
		txtPlazoInversion.sendKeys(strPlazoInversion);
	}

	public void setValorPrestamo(String strValorPrestamo) {
		txtValorPrestamo.sendKeys(strValorPrestamo);
	}

	public void simularCredito() {
		botonSimular.click();
	}

	public String getValorCuota() {
		String strValorCuota = driver.findElement(By.xpath(".//*[@id='sim-results']/div/table/tbody/tr[6]/td[2]")).getText();
		return strValorCuota.replace("$","").replace(",","");
	}

}
