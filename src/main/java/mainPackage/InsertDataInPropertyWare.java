package mainPackage;

import org.openqa.selenium.Keys;

public class InsertDataInPropertyWare 
{
	public static boolean updateValuesInPW()
	{
		RunnerClass.driver.navigate().refresh();
		PropertyWare.popUpHandling();
		RunnerClass.js.executeScript("window.scrollBy(0,-document.body.scrollHeight)");
		try
		{
		
		RunnerClass.driver.findElement(Locators.summaryEditButton).click();
		
		//Base Rent
		try
		{
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.baseRent)).build().perform();
		    RunnerClass.driver.findElement(Locators.baseRent).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		    RunnerClass.driver.findElement(Locators.baseRent).sendKeys(PDFReader.monthlyRent.replaceAll("[^0-9.]", ""));
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Issue -Base Rent";
			RunnerClass.valuesUpdateStatus = "Review";
		}
		
		//Initial Monthly rent from PW
		try
		{
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.initialMonthlyRent)).build().perform();
		    PDFReader.monthlyRentFromPW= RunnerClass.driver.findElement(Locators.initialMonthlyRent).getAttribute("Value").replace("$", "").trim();
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Issue -Initial Monthly Rent";
			PDFReader.monthlyRentFromPW= "Error";
		}
		//Initial Monthly rent from PW
		try
		{
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.initialPetRentAmount)).build().perform();
		    PDFReader.petRentFromPW= RunnerClass.driver.findElement(Locators.initialPetRentAmount).getAttribute("Value").replace("$", "").trim();
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Issue -Initial Pet Rent";
			PDFReader.petRentFromPW= "Error";
		}
		
		/*
		
		 //Initial Monthly Rent
		try
		{
			if(PDFReader.monthlyRent.equals("Error"))
			{
				RunnerClass.failedReason = "Issue -Initial Mothly Rent";
			    RunnerClass.valuesUpdateStatus = "Review";
			}
			else
			{
		    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.initialMonthlyRent)).build().perform();
		    RunnerClass.driver.findElement(Locators.initialMonthlyRent).clear();
		    RunnerClass.driver.findElement(Locators.initialMonthlyRent).sendKeys(PDFReader.monthlyRent.replaceAll("[^0-9.]", ""));
			}
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Issue -Initial Mothly Rent";
			RunnerClass.valuesUpdateStatus = "Review";
		}
		
		//Initial Pet Rent
		
		if(!PDFReader.petRent.equals(""))
		{
			try
			{
				if(PDFReader.petRent.equals("Error"))
				{
					RunnerClass.failedReason = "Issue -Initial Pet Rent";
				    RunnerClass.valuesUpdateStatus = "Review";
				}
				else
				{
			    RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.initialPetRentAmount)).build().perform();
			    RunnerClass.driver.findElement(Locators.initialPetRentAmount).clear();
			    RunnerClass.driver.findElement(Locators.initialPetRentAmount).sendKeys(PDFReader.petRent.replaceAll("[^0-9.]", ""));
				}
			}
			catch(Exception e)
			{
				RunnerClass.failedReason = "Issue -Initial Pet Rent";
				RunnerClass.valuesUpdateStatus = "Review";
			}
		}
		
		*/
		//Save info
		if(AppConfig.saveButtonOnAndOff==false)
		{
			 RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.cancelLease)).build().perform();
			 RunnerClass.driver.findElement(Locators.cancelLease).click();
		}
		else 
		{
			RunnerClass.actions.moveToElement(RunnerClass.driver.findElement(Locators.saveLease)).build().perform();
			 RunnerClass.driver.findElement(Locators.saveLease).click();
			 Thread.sleep(2000);
		}
		RunnerClass.valuesUpdateStatus = "Completed";
		return true;
		
		}
		catch(Exception e)
		{
			RunnerClass.failedReason = "Could not update rents";
			return false;
		}
	}

}
