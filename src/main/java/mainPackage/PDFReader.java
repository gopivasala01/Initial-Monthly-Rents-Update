package mainPackage;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import PDFAppConfig.PDFFormatDecider;

public class PDFReader 
{
	public static String commencementDate ="";
    public static String expirationDate ="";
    public static String monthlyRent="";
    public static String monthlyRentFromPW="";
    public static String petRent="";
    public static String petRentFromPW="";
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
        monthlyRentFromPW="";
        petRentFromPW="";
        
        switch(market)
		{
		case "Alabama":
			String pdfFormatType_florida = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_florida);
			if(pdfFormatType_florida=="Format1")
			{
				if(PDFDataExtract.Alabama_format1.format1()==false)
					return false;
				PDFReader.verifyDates();
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
				PDFReader.verifyDates();
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
			    
			break;
			
		case "Arkansas":
			String pdfFormatType_Arkansas = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Arkansas);
			if(pdfFormatType_Arkansas=="Format1")
			{
				if(PDFDataExtract.Arkansas_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			else 
				if(pdfFormatType_Arkansas=="Format2")
			     {
				if(PDFDataExtract.Arkansas_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			break;
			
		case "Austin":
			String pdfFormatType_Austin = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Austin);
			if(pdfFormatType_Austin=="Format1")
			{
				if(PDFDataExtract.Austin_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			else 
				if(pdfFormatType_Austin=="Format2")
			     {
				if(PDFDataExtract.Austin_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			break;
			
		case "Florida":
			String pdfFormatType_Florida = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Florida);
			if(pdfFormatType_Florida=="Format1")
			{
				if(PDFDataExtract.Florida_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
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
				if(pdfFormatType_Florida=="Format2")
			     {
				if(PDFDataExtract.Florida_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
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
			    
			break;
			
		case "Georgia":
			String pdfFormatType_Georgia = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Georgia);
			if(pdfFormatType_Georgia=="Format1")
			{
				if(PDFDataExtract.Georgia_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_Georgia=="Format2")
			     {
				if(PDFDataExtract.Georgia_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
			
		case "Indiana":
			String pdfFormatType_Indiana = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Indiana);
			if(pdfFormatType_Indiana=="Format1")
			{
				if(PDFDataExtract.Indiana_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_Indiana=="Format2")
			     {
				if(PDFDataExtract.Indiana_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
			
		case "North Carolina":
			String pdfFormatType_NorthCarolina = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_NorthCarolina);
			if(pdfFormatType_NorthCarolina=="Format1")
			{
				if(PDFDataExtract.NorthCarolina_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_NorthCarolina=="Format2")
			     {
				if(PDFDataExtract.NorthCarolina_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
			
		case "Savannah":
			String pdfFormatType_Savannah = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Savannah);
			if(pdfFormatType_Savannah=="Format1")
			{
				if(PDFDataExtract.Savannah_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_Savannah=="Format2")
			     {
				if(PDFDataExtract.Savannah_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
			
		case "South Carolina":
			String pdfFormatType_SouthCarolina = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_SouthCarolina);
			if(pdfFormatType_SouthCarolina=="Format1")
			{
				if(PDFDataExtract.SouthCarolina_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_SouthCarolina=="Format2")
			     {
				if(PDFDataExtract.SouthCarolina_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
			
		case "Tennessee":
			String pdfFormatType_Tennessee = PDFReader.decidePDFFormat(market);
			System.out.println("PDF Format Type = "+pdfFormatType_Tennessee);
			if(pdfFormatType_Tennessee=="Format1")
			{
				if(PDFDataExtract.Tennessee_Format1.format1()==false)
					return false;
				PDFReader.verifyDates();
						
			}
			
			else 
				if(pdfFormatType_Tennessee=="Format2")
			     {
				if(PDFDataExtract.Tennessee_Format2.format2()==false)
					return false;
				PDFReader.verifyDates();
		        }
			    else 
			   {
				RunnerClass.failedReason = RunnerClass.failedReason+","+ "Wrong PDF Format";
				return false;
			    }
			    
			break;
		}
        
        return true;
        
        
	
	}
    
    public static boolean verifyDates() throws Exception
    {
    	try
    	{
    	startDate = CommonMethods.convertDate(commencementDate);
    	endDate = CommonMethods.convertDate(expirationDate);
    	return true;
    	}
    	catch(Exception e)
    	{
    		startDate = "";
    		endDate = "";
    	return false;
    	}
    	//currentDate = CommonMethods.getCurrentDate();
    	//if(CommonMethods.compareDates(startDate,endDate, currentDate)==true)
    	//return true;
    	//else return false;
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
		    format1Text  = PDFAppConfig.PDFFormatDecider.Alabama_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.Alabama_Format2;
		break;
		
		case "Arkansas":
		    format1Text  = PDFAppConfig.PDFFormatDecider.Arkansas_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.Arkansas_Format2;
		break;
		
		case "Austin":
		    format1Text  = PDFAppConfig.PDFFormatDecider.austin_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.austin_Format2;
		break;
		
		case "Florida":
		    format1Text  = PDFAppConfig.PDFFormatDecider.Florida_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.Florida_Format2;
		break;
		
		case "Georgia":
		    format1Text  = PDFAppConfig.PDFFormatDecider.Georgia_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.Georgia_Format2;
		break;
		
		case "Indiana":
		    format1Text  = PDFAppConfig.PDFFormatDecider.Indiana_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.Indiana_Format2;
		break;
		
		case "North Carolina":
		    format1Text  = PDFAppConfig.PDFFormatDecider.NorthCarolina_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.NorthCarolina_Format2;
		break;
		
		case "South Carolina":
		    format1Text  = PDFAppConfig.PDFFormatDecider.SouthCarolina_Format1;
		    format2Text  = PDFAppConfig.PDFFormatDecider.SouthCarolina_Format2;
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
		case "Tennessee":
	        format1Text = PDFAppConfig.PDFFormatDecider.Tennessee_Format1;
	        format2Text = PDFAppConfig.PDFFormatDecider.Tennessee_Format2;
	        break;
		}
		
		File file = CommonMethods.getLastModified();
		//if(!file.toString().contains(RunnerClass.leaseAgreementName))
			//return "Error";
		//File file = new File("C:\\SantoshMurthyP\\Lease Audit Automation\\Full_Lease_-_[6128_Creekview_Court]_-_[Wallace_-_Crawford]_-_[02.01.2023]_-_[04.30.2024].PDF_(1).pdf");
		System.out.println(file);
		FileInputStream fis = new FileInputStream(file);
		PDDocument document = PDDocument.load(fis);
		text = new PDFTextStripper().getText(document);
		text = text.replaceAll(System.lineSeparator(), " ");
	    text = text.replaceAll(" +", " ");
	    text = text.toLowerCase();
	    if(text.contains(format1Text.toLowerCase())||text.contains(PDFFormatDecider.format1.toLowerCase())||text.contains(PDFFormatDecider.format1_2.toLowerCase()))
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
