package seleniumAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SimuladorSolucionInmobiliariaPage {
	WebDriver driver;
	WebElement cmbTipoFinanciacion;
	WebElement cmbDestinoCredito;
	WebElement cmbOpcionSimular;
	WebElement cmbPlanAmortizacion;
	WebElement txtPlazoAnios;
	WebElement cmbFechaNacimiento;
	WebElement cmbDeptoColombia;
	WebElement txtValorBien;
	WebElement txtValorPrestamo;
	WebElement chkSubsidioGobierno;
	WebElement botonSimular;

	public SimuladorSolucionInmobiliariaPage(WebDriver driver) {
		this.driver = driver;
		cmbTipoFinanciacion = driver.findElement(By.name("combotipoFinanciacion"));
		cmbDestinoCredito = driver.findElement(By.name("comboDestinoCredito"));
		cmbOpcionSimular = driver.findElement(By.name("comboOpcionSimular"));
		cmbPlanAmortizacion = driver.findElement(By.name("comboPlanAmortizacion"));
		txtPlazoAnios = driver.findElement(By.name("textPlazoAnios"));
		cmbFechaNacimiento = driver.findElement(By.name("dateFechaNacimiento"));
		cmbDeptoColombia = driver.findElement(By.name("comboDeptoColomnbia"));
		txtValorBien = driver.findElement(By.name("textValorBien"));
		txtValorPrestamo = driver.findElement(By.name("textValorPrestamo"));
		chkSubsidioGobierno = driver.findElement(By.name("checkSubsidioGobierno"));
		botonSimular = driver.findElement(By.tagName("button"));
	}

	public void setTipoFinanciacion(String strTipoFinanciacion) {
		Select tipoFinanciacionSelect = new Select(cmbTipoFinanciacion);
		tipoFinanciacionSelect.selectByVisibleText(strTipoFinanciacion);
	}

	public void setDestinoCredito(String strDestinoCredito) {
		Select destinoCreditoSelect = new Select(cmbDestinoCredito);
		destinoCreditoSelect.selectByVisibleText(strDestinoCredito);
	}

	public void setOpcionSimular(String strOpcionSimular) {
		Select opcionSimularSelect = new Select(cmbOpcionSimular);
		opcionSimularSelect.selectByVisibleText(strOpcionSimular);
	}

	public void setPlanAmortizacion(String strPlanAmortizacion) {
		Select planAmortizacionSelect = new Select(cmbPlanAmortizacion);
		planAmortizacionSelect.selectByVisibleText(strPlanAmortizacion);
	}

	public void setPlazoAnios(String strPlazoAnios) {
		txtPlazoAnios.sendKeys(strPlazoAnios);
	}
	
	public void setFechaNacimiento(String strFechaNacimiento) {
		cmbFechaNacimiento.sendKeys(strFechaNacimiento);
	}
	
	public void setDeptoColombia(String strDeptoColombia) {
		Select deptoColombiaSelect = new Select(cmbDeptoColombia);
		deptoColombiaSelect.selectByVisibleText(strDeptoColombia);
	}

	public void setValorBien(String strValorBien) {
		txtValorBien.sendKeys(strValorBien);
	}
	
	public void setValorPrestamo(String strValorPrestamo) {
		txtValorPrestamo.sendKeys(strValorPrestamo);
	}

	public void setSubsidioGobierno() {
		chkSubsidioGobierno.click();
	}

	public void simularCredito() {
		botonSimular.click();
	}

	public String getValorCuota() {
		String strValorCuota = driver.findElement(By.xpath(".//*[@id='sim-results']/div/table/tbody/tr[18]/td[5]")).getText();
		String strValorSeguroVida = driver.findElement(By.xpath(".//*[@id='sim-results']/div/table/tbody/tr[21]/td[2]")).getText();
		String strValorSeguroIncendio = driver.findElement(By.xpath(".//*[@id='sim-results']/div/table/tbody/tr[22]/td[2]")).getText();
		Double dblValorCuota = Double.parseDouble(strValorCuota.replace("$","").replace(",",""));
		Double dblValorSeguroVida = Double.parseDouble(strValorSeguroVida.replace("$","").replace(",",""));
		Double dblValorSeguroIncendio = Double.parseDouble(strValorSeguroIncendio.replace("$","").replace(",",""));
		Double dblValorTotal = dblValorCuota + dblValorSeguroVida + dblValorSeguroIncendio;
		return dblValorTotal.toString();
	}

}
