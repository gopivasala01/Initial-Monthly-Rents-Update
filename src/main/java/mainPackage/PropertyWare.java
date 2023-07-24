package mainPackage;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyWare 
{
	public static boolean login()
	{
		try
		{
		RunnerClass.downloadFilePath = AppConfig.downloadFilePath;
		Map<String, Object> prefs = new HashMap<String, Object>();
	    // Use File.separator as it will work on any OS
	    prefs.put("download.default_directory",
	    		RunnerClass.downloadFilePath);
        // Adding cpabilities to ChromeOptions
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--remote-allow-origins=*");
		WebDriverManager.chromedriver().setup();
        RunnerClass.driver= new ChromeDriver(options);
        RunnerClass.driver.manage().window().maximize();
        
        if(PropertyWare.signIn()==true)
        	return true;
        else return false;
		}
		catch(Exception e)
		{
			System.out.println("Login failed");
		    RunnerClass.failedReason = RunnerClass.failedReason+","+ "Login failed";
			return false;
		}
	}
	
	public static boolean signIn()
	{
		try
		{
		RunnerClass.driver.get(AppConfig.URL);
        RunnerClass.driver.findElement(Locators.userName).sendKeys(AppConfig.username); 
        RunnerClass.driver.findElement(Locators.password).sendKeys(AppConfig.password);
        RunnerClass.driver.findElement(Locators.signMeIn).click();
        RunnerClass.actions = new Actions(RunnerClass.driver);
        RunnerClass.js = (JavascriptExecutor)RunnerClass.driver;
        RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(2));
        try
        {
        if(RunnerClass.driver.findElement(Locators.loginError).isDisplayed())
        {
        	System.out.println("Login failed");
		    RunnerClass.failedReason = RunnerClass.failedReason+","+ "Login failed";
			return false;
        }
        }
        catch(Exception e) {}
        RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(100));
        return true;
		}
		catch(Exception e)
		{
			System.out.println("Login failed");
		    RunnerClass.failedReason = RunnerClass.failedReason+","+ "Login failed";
			return false;
		}
	}
	
	public static boolean searchBuilding(String company, String building) throws Exception
	{
		RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(10));
		RunnerClass.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//Thread.sleep(3000);
		PropertyWare.popUpHandling();
		RunnerClass.js.executeScript("window.scrollBy(0, -document.body.scrollHeight)");
		RunnerClass.driver.navigate().refresh();
		try
		{
	    //RunnerClass.driver.findElement(Locators.dashboardsTab).click();
		RunnerClass.driver.findElement(Locators.searchbox).clear();
		RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
		RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(300));
			try
			{
				if(RunnerClass.driver.findElement(Locators.searchingLoader).isDisplayed())
			RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
				else {}
			}
			catch(Exception e)
			{
				try
				{
				RunnerClass.driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
				RunnerClass.driver.navigate().refresh();
				PropertyWare.popUpHandling();
				RunnerClass.driver.findElement(Locators.dashboardsTab).click();
				RunnerClass.driver.findElement(Locators.searchbox).clear();
				RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
				RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
				}
				catch(Exception e2) {}
			}
			try
			{
			RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			if(RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed())
			{
				long count = building.chars().filter(ch -> ch == '.').count();
				if(building.chars().filter(ch -> ch == '.').count()>=2)
				{
					building = building.substring(building.lastIndexOf(".")+1);
					RunnerClass.driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
					RunnerClass.driver.navigate().refresh();
					PropertyWare.popUpHandling();
					RunnerClass.driver.findElement(Locators.dashboardsTab).click();
					RunnerClass.driver.findElement(Locators.searchbox).clear();
					RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
					RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
					try
					{
					RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
					if(RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed())
					{
						System.out.println("Building Not Found");
					    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
						return false;
					}
					}
					catch(Exception e3) {}
				}
				else
				{
				try
				 {
					if(building.contains("."))
						building = building.substring(building.indexOf(".")+1,building.length());
					else 
					if(building.contains("_"))
				  building = building.split("_")[1];
					else 
						building = RunnerClass.completeBuildingAbbreviation.substring(RunnerClass.completeBuildingAbbreviation.indexOf("(")+1,RunnerClass.completeBuildingAbbreviation.indexOf(")"));
					
				 if( PropertyWare.searchingBuildingWithDifferentText(building)==false)
				 {
					 System.out.println("Building Not Found");
			         RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
				     return false;
				 }
				 }
				 catch(Exception e)
				 {
			     System.out.println("Building Not Found");
		         RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
			     return false;
				 }
				}
			}
			}
			catch(Exception e2)
			{
			}
			RunnerClass.driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
			Thread.sleep(1000);
			System.out.println(building);
		// Select Lease from multiple leases
			List<WebElement> displayedCompanies =null;
			try
			{
				displayedCompanies = RunnerClass.driver.findElements(Locators.searchedLeaseCompanyHeadings);
			}
			catch(Exception e)
			{
				
			}
				boolean leaseSelected = false;
				for(int i =0;i<displayedCompanies.size();i++)
				{
					String companyName = displayedCompanies.get(i).getText();
					if(companyName.toLowerCase().contains(company.toLowerCase())&&!companyName.contains("Legacy"))
					{
						
						List<WebElement> leaseList = RunnerClass.driver.findElements(By.xpath("(//*[@class='section'])["+(i+1)+"]/ul/li/a"));
						//System.out.println(leaseList.size());
						for(int j=0;j<leaseList.size();j++)
						{
							String lease = leaseList.get(j).getText();
							if(lease.toLowerCase().contains(building.toLowerCase())&&lease.contains(":"))
							{
								RunnerClass.driver.findElement(By.xpath("(//*[@class='section'])["+(i+1)+"]/ul/li["+(j+1)+"]/a")).click();
								leaseSelected = true;
								break;
							}
						}
					}
					if(leaseSelected==true)
					{
						break;
					}
				}
				if(leaseSelected==false)
				{
				    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
					return false;
				}
	         } catch(Exception e) 
		     {
	         RunnerClass.failedReason = RunnerClass.failedReason+","+  "Issue in selecting Building";
		     return false;
		     }
		return true;
	}
	
	public static boolean downloadLeaseAgreement(String building, String ownerName) throws Exception {
	    RunnerClass.failedReason = "";
	    RunnerClass.leaseAgreementName = "";
	    RunnerClass.thirdPartyUnitID = "";

	    List<WebElement> documents;

	    try {
	        RunnerClass.thirdPartyUnitID = RunnerClass.driver.findElement(Locators.thirdPartyUnitID).getText();
	    } catch (Exception e) {
	        RunnerClass.thirdPartyUnitID = "";
	    }
	    System.out.println("Third Party Unitid = " + RunnerClass.thirdPartyUnitID);

	    try {
	        RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	        Thread.sleep(2000);
	        RunnerClass.driver.findElement(Locators.leasesTab).click();
	        RunnerClass.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(5));

	        try {
	            RunnerClass.driver.findElement(By.partialLinkText(ownerName.trim())).click();
	        } catch (Exception e) {
	            System.out.println("Unable to Click Lease Owner Name");
	            RunnerClass.failedReason += "," + "Unable to Click Lease Owner Name";
	            return false;
	        }

	        // pop up handling
	        PropertyWare.popUpHandling();

	        RunnerClass.leaseIDNumber = RunnerClass.driver.findElement(Locators.leaseIDNumber).getText();
	        System.out.println("Lease ID Number = " + RunnerClass.leaseIDNumber);

	        RunnerClass.leaseExecutionDate = RunnerClass.driver.findElement(Locators.leaseExecutionDate).getText();
	        System.out.println("Lease Execution Date = " + RunnerClass.leaseExecutionDate);

	        RunnerClass.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(15));
	        RunnerClass.js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	        RunnerClass.driver.findElement(Locators.notesAndDocs).click();

	        RunnerClass.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	        RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(3));

	        documents = RunnerClass.driver.findElements(Locators.documentsList);
	        boolean checkModLeaseAgreementAvailable = false;
	        String fileName2 = "";

	        for (int i = 0; i < documents.size(); i++)
	        {
	            for (int j = 0; j < AppConfig.LeaseAgreementFileNamesMOD.length; j++) 
	            {
	                if (documents.get(i).getText().startsWith(AppConfig.LeaseAgreementFileNamesMOD[j])) 
	                {
	                    documents.get(i).click();
	                    fileName2 = documents.get(i).getText();
	                    RunnerClass.leaseAgreementName = fileName2;
	                    checkModLeaseAgreementAvailable = true;
	                    break;
	                }
	            }
	            if (checkModLeaseAgreementAvailable) 
	            {
	            	if (CommonMethods.getLastModified() != null)
		            {
			            Thread.sleep(10000);
			        }
	                break;
	            }
	             
	        }
	       
	        PDFModReader.PDFModReader1(checkModLeaseAgreementAvailable);

	        if (!checkModLeaseAgreementAvailable) 
	        {
	            System.out.println("MOD Lease Agreement is not available");
	            RunnerClass.failedReason = RunnerClass.failedReason + "," + "Lease Agreement is not available";
	        }

	        Thread.sleep(5000);

	        boolean checkLeaseAgreementAvailable = false;
	        String fileName = "";

	        for (int i = 0; i < documents.size(); i++) 
	        {
	            for (int j = 0; j < AppConfig.LeaseAgreementFileNames.length; j++) 
	            {
	                if (documents.get(i).getText().startsWith(AppConfig.LeaseAgreementFileNames[j]) && 
	                    !documents.get(i).getText().contains("Termination") && 
	                    !documents.get(i).getText().contains("_Mod") && 
	                    !documents.get(i).getText().contains("_MOD")) 
	                {
	                    documents.get(i).click();
	                    fileName = documents.get(i).getText();
	                    RunnerClass.leaseAgreementName = fileName;
	                    checkLeaseAgreementAvailable = true;
	                    break;
	                }
	            }
	            if (checkLeaseAgreementAvailable) 
	            {
	                break;
	            }
	        }

	        if (!checkLeaseAgreementAvailable)
	        {
	            System.out.println("Lease Agreement is not available");
	            RunnerClass.failedReason = RunnerClass.failedReason + "," + "Lease Agreement is not available";
	            return false;
	        }

	        Thread.sleep(5000);

	        if (CommonMethods.isFileDownloaded(fileName, ".pdf", 30).equals("")) 
	        {
	            Thread.sleep(10000);
	        }

	        return true;

	    } catch (Exception e) {
	        e.printStackTrace();
	        System.out.println("Unable to download Lease Agreement");
	        RunnerClass.failedReason = RunnerClass.failedReason + "," + "Unable to download Lease Agreement";
	        return false;
	    }
	}


	public static void popUpHandling()
	{
		RunnerClass.driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
		try
		{
			if(RunnerClass.driver.findElement(Locators.popUpAfterClickingLeaseName).isDisplayed())
			{
				RunnerClass.driver.findElement(Locators.popupClose).click();
			}
		}
		catch(Exception e) {};
		RunnerClass.driver.manage().timeouts().implicitlyWait(150,TimeUnit.SECONDS);
	}

	public static boolean searchingBuildingWithDifferentText(String building)
	{
		try
		{
		RunnerClass.driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
		 RunnerClass.driver.navigate().refresh();
		 PropertyWare.popUpHandling();
		 RunnerClass.driver.findElement(Locators.dashboardsTab).click();
		 RunnerClass.driver.findElement(Locators.searchbox).clear();
		 RunnerClass.driver.findElement(Locators.searchbox).sendKeys(building);
		 RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
		 try
		 {
		 RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		 if(RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed())
		 { 
			System.out.println("Building Not Found");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
			return false;
	     }
		 }
		 catch(Exception e3) {}
		 }
		 catch(Exception e)
		 {
	     System.out.println("Building Not Found");
        RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
	     return false;
		 }
		return true;
	}
	
	public static boolean searchBuildingWithLeaseName(String company, String leaseName)
	{
		try
		{
		RunnerClass.driver.manage().timeouts().implicitlyWait(200,TimeUnit.SECONDS);
		 RunnerClass.driver.navigate().refresh();
		 PropertyWare.popUpHandling();
		 RunnerClass.driver.findElement(Locators.dashboardsTab).click();
		 RunnerClass.driver.findElement(Locators.searchbox).clear();
		 RunnerClass.driver.findElement(Locators.searchbox).sendKeys(leaseName);
		 RunnerClass.wait.until(ExpectedConditions.invisibilityOf(RunnerClass.driver.findElement(Locators.searchingLoader)));
		 try
		 {
		 RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		 if(RunnerClass.driver.findElement(Locators.noItemsFoundMessagewhenLeaseNotFound).isDisplayed())
		 { 
			System.out.println("Building Not Found");
		    RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
			return false;
	     }
			 
		 }
		 catch(Exception e3) {}
		 
		 RunnerClass.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			Thread.sleep(1000);
		// Select Lease from multiple leases
			List<WebElement> displayedCompanies =null;
			try
			{
				displayedCompanies = RunnerClass.driver.findElements(Locators.searchedLeaseCompanyHeadings);
			}
			catch(Exception e)
			{
				
			}
			boolean checkCompanyAvailability = false;
				boolean leaseSelected = false;
				for(int i =0;i<displayedCompanies.size();i++)
				{
					String companyName = displayedCompanies.get(i).getText();
					if(companyName.toLowerCase().contains(company.toLowerCase())&&!companyName.contains("Legacy"))
					{
						RunnerClass.driver.findElement(Locators.advancedSearch).click();
						RunnerClass.driver.findElement(Locators.advancedSearch_building).click();
						checkCompanyAvailability = true;
						break;
					}
				}
      			if(checkCompanyAvailability ==false)
      			{
      				System.out.println("Building Not Found");
      		        RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
      			    return false;
      			}
		 
		 }
		 catch(Exception e)
		 {
	     System.out.println("Building Not Found");
        RunnerClass.failedReason =  RunnerClass.failedReason+","+ "Building Not Found";
	     return false;
		 }
		return true;
	}
	
	public static boolean checkIfSiteIsDown()
	{
		
		try
		{
		RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
		if(RunnerClass.driver.findElement(Locators.PWSiteDownMessage).isDisplayed())
		{
			PropertyWare.signIn();
			return true;
		}
		else
			return false;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static void reLoginIntoSiteWhenItIsDownOrLoggedOut()
	{
		try
		{
			RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(2));
			RunnerClass.driver.manage().timeouts().implicitlyWait(2,TimeUnit.SECONDS);
			if(RunnerClass.driver.findElement(Locators.PWSiteDownMessage).isDisplayed()||RunnerClass.driver.findElement(Locators.thisSiteCantBeReached).isDisplayed()||RunnerClass.driver.findElement(Locators.signIntoPropertyWare).isDisplayed())
			{
				RunnerClass.driver.navigate().to(AppConfig.URL);
		        RunnerClass.driver.findElement(Locators.userName).sendKeys(AppConfig.username); 
		        RunnerClass.driver.findElement(Locators.password).sendKeys(AppConfig.password);
		        RunnerClass.driver.findElement(Locators.signMeIn).click();
			}
			RunnerClass.wait = new WebDriverWait(RunnerClass.driver, Duration.ofSeconds(10));
			RunnerClass.driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		}
		catch(Exception e)
		{
			
		}
	}
	


}
