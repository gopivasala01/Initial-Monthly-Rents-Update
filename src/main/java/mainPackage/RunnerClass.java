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
	public static ChromeDriver driver;
	
	public static Actions actions;
	public static JavascriptExecutor js;
	public static WebDriverWait wait;
	
	public static String failedReason = "";
	public static boolean leaseCompletionStatus = false;
	public static String downloadFilePath;
	public static String valuesUpdateStatus ="";
	public static String leaseAgreementName = "";
	
	public static String[][] pendingLeases; 
	public static void main(String args[]) throws Exception
	{
		// Login to PropertyWare
		PropertyWare.login();
		
		//Get Leases
		DataBase.getBuildingsList();
		for(int i=0;i<pendingLeases.length;i++)
		{
			  
			  company = pendingLeases[i][0];
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
			  if(PropertyWare.searchBuilding(company, building)==false)
			  {
				  if(PropertyWare.searchBuildingWithLeaseName(company, leaseName)==false)
				  {
					  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = 'Building Not Found' where building like '%"+building+"%' and LeaseName like '"+leaseName+"'";
					  DataBase.updateTable(query);
					  continue;
				  }
			  }
			  
			  if(PropertyWare.downloadLeaseAgreement(building, leaseName)==false)
			  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = '"+failedReason+"' where building like '%"+building+"%' and LeaseName like '"+leaseName+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  
			  if(PDFReader.readPDFPerMarket(company)==false)
			  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = '"+failedReason+"' where building like '%"+building+"%'  and LeaseName like '"+leaseName+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  
			  if(InsertDataInPropertyWare.updateValuesInPW()==false)
			  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='Failed',CompletedDate =GetDate(),Notes = 'Could not Update Values',StartDate='"+PDFReader.startDate+"',EndDate='"+PDFReader.endDate+"',MonthlyRent='"+PDFReader.monthlyRent+"',PetRent='"+PDFReader.petRent+"' where building like '%"+building+"%'  and LeaseName like '"+leaseName+"'";
				  DataBase.updateTable(query);
				  continue;
			  }
			  else
			  {
				  String query = "Update Automation.InitialRentsUpdate Set Status='"+valuesUpdateStatus+"',CompletedDate =GetDate(),Notes = '"+failedReason+"',StartDate='"+PDFReader.startDate+"',EndDate='"+PDFReader.endDate+"',MonthlyRent='"+PDFReader.monthlyRent+"',PetRent='"+PDFReader.petRent+"' where building like '%"+building+"%'  and LeaseName like '"+leaseName+"'";
				  DataBase.updateTable(query);
			  }
			  
		}
		
	}

}
