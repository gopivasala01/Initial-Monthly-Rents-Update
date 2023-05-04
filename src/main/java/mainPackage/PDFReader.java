package mainPackage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader 
{
	public static String commencementDate ="";
    public static String expirationDate ="";
    public static String monthlyRent="";
    public static String petRent="";
    public static boolean petFlag = false;
    public static String PDFFormatType = "";
    public static String startDate = "";
    public static String endDate = "";
    public static String currentDate = "";
    public static String text = "";
    
    public static boolean readPDFPerMarket(String market) throws Exception  
	{
    	commencementDate ="";
        expirationDate ="";
        monthlyRent="";
        petRent="";
        petFlag = false;
        PDFFormatType = "";
        startDate = "";
        endDate = "";
        currentDate = "";
        text = "";
        
        
        switch(market)
		{
		case "Alabama":
			String pdfFormatType_florida = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_florida);
			if(pdfFormatType_florida=="Format1")
			{
				if(PDFDataExtract.Alabama_format1.format1()==false)
					return false;
				/*
				if(PDFReader.verifyDates()==true)
					return true;
				else 
				{
					RunnerClass.failedReason = "Dates do not match";
					return false;
				}
				*/
						
			}
			
			else 
				if(pdfFormatType_florida=="Format2")
			     {
				if(PDFDataExtract.Alabama_Format2.format2()==false)
					return false;
				/*
				if(PDFReader.verifyDates()==true)
					return true;
				else
					{ 
					RunnerClass.failedReason = "Dates do not match";
					return false;
					}
				*/
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			//break;
		}
        
        return true;
	
	}
    
    public static boolean verifyDates() throws Exception
    {
    	startDate = CommonMethods.convertDate(commencementDate);
    	endDate = CommonMethods.convertDate(expirationDate);
    	currentDate = CommonMethods.getCurrentDate();
    	if(CommonMethods.compareDates(startDate,endDate, currentDate)==true)
    	return true;
    	else return false;
    }
    
    public static String decidePDFFormat(String company) throws Exception
	{
		try
		{
		String format1Text = "";
		String format2Text = "";
		switch(company)
		{
		case "Alabama":
		    format1Text  = PDFAppConfig.PDFFormatDecider.alabama_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.alabama_Format2;
		break;
		
		case "Austin":
		    format1Text  = PDFAppConfig.PDFFormatDecider.austin_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.austin_Format2;
		break;
		
		case "California":
	        format1Text = PDFAppConfig.PDFFormatDecider.california_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.california_Format2;
	        break;
	        
		case "California PFW":
	        format1Text = PDFAppConfig.PDFFormatDecider.californiaPFW_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.californiaPFW_Format2;
	        break;
		case "Chicago PFW":
	        format1Text = PDFAppConfig.PDFFormatDecider.ChicagoPFW_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.ChicagoPFW_Format2;
	        break;
		case "Colorado Springs":
	        format1Text = PDFAppConfig.PDFFormatDecider.ColoradoSprings_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.ColoradoSprings_Format2;
	        break; 
		case "Kansas City":
	        format1Text = PDFAppConfig.PDFFormatDecider.KansasCity_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.KansasCity_Format2;
	        break; 
		case "Houston":
	        format1Text = PDFAppConfig.PDFFormatDecider.Houston_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.Houston_Format2;
	        break; 
		case "Maine":
	        format1Text = PDFAppConfig.PDFFormatDecider.Maine_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.Maine_Format2;
	        break; 
		case "Savannah":
	        format1Text = PDFAppConfig.PDFFormatDecider.Savannah_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.Savannah_Format2;
	        break; 
		}
		
		File file = CommonMethods.getLastModified();
		if(!file.toString().contains(RunnerClass.leaseAgreementName))
			return "Error";
		//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Full_Lease_-_[6128_Creekview_Court]_-_[Wallace_-_Crawford]_-_[02.01.2023]_-_[04.30.2024].PDF_(1).pdf");
		System.out.println(file);
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
		text = new PDFTextStripper().getText(document);
		text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.replaceAll(" +", " ");
	    if(text.contains(format1Text))
	    {
	    	PDFFormatType = "Format1";
	    	System.out.println("PDF Format Type = "+PDFFormatType);
	    	return "Format1";
	    }
	    else if(text.contains(format2Text))
	    {
	    	PDFFormatType = "Format2";
	    	System.out.println("PDF Format Type = "+PDFFormatType);
	    	return "Format2";
	    }
	    else return "Error";
		}
		catch(Exception e)
		{
			return "Error";
		}
	}

}
