 
 package mainPackage;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnerClass 
{
	public static String company = "";
	public static String building = "";
	public static String completeBuildingAbbreviation = "";
	public static String leaseName = "";
	public static String leaseIDNumber = "";
	public static ChromeDriver driver;
	
	public static Actions actions;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	
	public static String failedReason = "";
	public static boolean leaseCompletionStatus = false;
	public static String downloadFilePath;
	public static String valuesUpdateStatus ="";
	public static String leaseAgreementName = "";
	public static String thirdPartyUnitID = "";
	public static String leaseExecutionDate = "";
	public static boolean checkModLeaseAgreementAvailable = false;
	
	public static String[][] completedLeasesList;
	
	public static String[][] pendingLeases; 
	public static void main(String args[]) throws Exception
	{
		
		// Login to PropertyWare
		PropertyWare.login();
		int w =0;
		//Get Leases
		DataBase.getBuildingsList(AppConfig.pendingLeasesQuery);
		while(w<3)
		{

		//for(int i=0;i<pendingLeases.length;i++)  //pendingLeases.length

		for(int i=0;i<pendingLeases.length;i++)  //pendingLeases.length

		{
			  System.out.println("-------------------------------------------------------------");
			  company =  pendingLeases[i][0];
			  building = pendingLeases[i][1];
			  leaseName = pendingLeases[i][2];
			  
			System.out.println(" Record -- "+(i+1)+" -- "+company+" -- "+building+" -- "+leaseName);
			  //Extract Abbreviation from building name
			    completeBuildingAbbreviation = building;  //This will be used when Building not found in first attempt
				building = building.split("-")[0].trim();
			  
				//Delete all the files in the folder 
				try
				{
				FileUtils.cleanDirectory(new File(AppConfig.downloadFilePath));
				}
				catch(Exception e) {}
				failedReason ="";
				valuesUpdateStatus ="";
				leaseIDNumber = "";
				thirdPartyUnitID = "";
				leaseExecutionDate = "";
				
				//Check if Site is logged off or This site cant be reached error message came
				
				try
				{
					PropertyWare.reLoginIntoSiteWhenItIsDownOrLoggedOut();
				}
				catch(Exception e) {}
				try
				{
			  if(PropertyWare.searchBuilding(company, building)==false)
			  {
				  if(PropertyWare.checkIfSiteIsDown()==true)
				  {
					  continue;
					 // MailActivities.sendANoteToDeveloper();
					 // break;
				  }
				  if(PropertyWare.searchBuildingWithLeaseName(company, leaseName)==false)
				  {
					  if(PropertyWare.checkIfSiteIsDown()==true)
					  {
						  continue;
						 // MailActivities.sendANoteToDeveloper();
						 // break;
					  }
					  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = 'Building Not Found' where building like '%"+building+"%' and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
					  DataBase.updateTable(query);
					  continue;
				  }
			  }
			  
			  if(PropertyWare.downloadLeaseAgreement(building, leaseName)==false)
			  {
				  if(PropertyWare.checkIfSiteIsDown()==true)
				  {
					  continue;
					 // MailActivities.sendANoteToDeveloper();
					 // break;
				  }
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = '"+failedReason+"',LeaseIDNumber = '"+RunnerClass.leaseIDNumber+"',thirdPartyUnitID ='"+thirdPartyUnitID+"',leaseExecutionDate='"+leaseExecutionDate+"' where building like '%"+building+"%' and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  
			  if(PDFReader.readPDFPerMarket(company)==false)
			  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = '"+failedReason+"',LeaseIDNumber = '"+RunnerClass.leaseIDNumber+"' ,thirdPartyUnitID ='"+thirdPartyUnitID+"',leaseExecutionDate='"+leaseExecutionDate+"' where building like '%"+building+"%'  and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  
			  if(InsertDataInPropertyWare.updateValuesInPW()==false)
			  {
				  if(PropertyWare.checkIfSiteIsDown()==true)
				  {
					  continue;
					 // MailActivities.sendANoteToDeveloper();
					 // break;
				  }
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = 'Could not Update Values',StartDate='"+PDFReader.startDate+"',EndDate='"+PDFReader.endDate+"',MonthlyRent='"+PDFReader.monthlyRent+"',MonthlyRentFromPW='"+PDFReader.monthlyRentFromPW+"',PetRent='"+PDFReader.petRent+"',PetRentFromPW = '"+PDFReader.petRentFromPW+"',LeaseIDNumber = '"+RunnerClass.leaseIDNumber+"' ,thirdPartyUnitID ='"+thirdPartyUnitID+"',leaseExecutionDate='"+leaseExecutionDate+"' where building like '%"+building+"%'  and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  else
			  {
				  if(RunnerClass.checkModLeaseAgreementAvailable) 
				  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='"+valuesUpdateStatus+"',CompletedDate =GetDate(),Notes = '"+failedReason+"',StartDate='"+PDFReader.startDate+"',EndDate='"+PDFReader.endDate+"',MonthlyRent='"+PDFModReader.monthlyRent+"',MonthlyRentFromPW='"+PDFReader.monthlyRentFromPW+"',PetRent='"+PDFReader.petRent+"',PetRentFromPW = '"+PDFReader.petRentFromPW+"',LeaseIDNumber = '"+RunnerClass.leaseIDNumber+"' ,thirdPartyUnitID ='"+thirdPartyUnitID+"',leaseExecutionDate='"+leaseExecutionDate+"' where building like '%"+building+"%'  and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
				  DataBase.updateTable(query);
				  }
				  else 
				  {
					  String query = "Update Automation.InitialRentsUpdate Set Status='"+valuesUpdateStatus+"',CompletedDate =GetDate(),Notes = 'NULL',StartDate='"+PDFReader.startDate+"',EndDate='"+PDFReader.endDate+"',MonthlyRent='"+PDFReader.monthlyRent+"',MonthlyRentFromPW='"+PDFReader.monthlyRentFromPW+"',PetRent='"+PDFReader.petRent+"',PetRentFromPW = '"+PDFReader.petRentFromPW+"',LeaseIDNumber = '"+RunnerClass.leaseIDNumber+"' ,thirdPartyUnitID ='"+thirdPartyUnitID+"',leaseExecutionDate='"+leaseExecutionDate+"' where building like '%"+building+"%'  and REPLACE(LeaseName,'''','') like '"+leaseName.replace("'", "")+"'";
					  DataBase.updateTable(query);
				  }
			  }            
			  
				}
				catch(Exception e)
				{
					continue;
				}
		}
		DataBase.getBuildingsList(AppConfig.failedLeasesQuery);
		
			System.out.println((w+1)+ " Time");
			if(pendingLeases.length>0)
			w++;
			else break;
		
		}
		
		
		//Create Excel file
		 MailActivities.createExcelFileWithProcessedData();
		
		
	}

}
