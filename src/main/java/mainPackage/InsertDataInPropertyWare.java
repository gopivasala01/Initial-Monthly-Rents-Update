package mainPackage;

public class InsertDataInPropertyWare 
{
	public static boolean updateValuesInPW()
	{
		RunnerClass.driver.navigate().refresh();
		RunnerClass.js.executeScript("window.scrollBy(document.body.scrollHeight,0)");
		try
		{
		RunnerClass.driver.findElement(Locators.summaryEditButton).click();
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
