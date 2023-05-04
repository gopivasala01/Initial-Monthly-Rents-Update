package mainPackage;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CommonMethods 
{
	public static String currentTime;
	public static File getLastModified() throws Exception
	{
		
	    File directory = new File(AppConfig.downloadFilePath);
	    File[] files = directory.listFiles(File::isFile);
	    long lastModifiedTime = Long.MIN_VALUE;
	    File chosenFile = null;

	    if (files != null)
	    { 
	        for (File file : files)
	        {
	            if (file.lastModified() > lastModifiedTime)
	            {
	                chosenFile = file;
	                lastModifiedTime = file.lastModified();
	            }
	        }
	    }

	    return chosenFile;
	}
	
	
	public static String isFileDownloaded(String fileText, String fileExtension, int timeOut) 
	{
		try
		{
        String folderName = AppConfig.downloadFilePath;
        File[] listOfFiles;
        int waitTillSeconds = timeOut;
        boolean fileDownloaded = false;
        String filePath = null; 

        long waitTillTime = Instant.now().getEpochSecond() + waitTillSeconds;
        while (Instant.now().getEpochSecond() < waitTillTime) {
            listOfFiles = new File(folderName).listFiles();
            for (File file : listOfFiles) {
                String fileName = file.getName().toLowerCase();
                if (fileName.contains(fileText.toLowerCase())&& fileName.contains(fileExtension.toLowerCase())) {
                    fileDownloaded = true;
                    filePath = file.getAbsolutePath();
                    break;
                }
            }
            if (fileDownloaded) {
                break;
            }
        }
        return filePath;
		}
		catch(Exception e)
		{
			return "";
		}
    }
	
	public static String convertDate(String dateRaw) throws Exception
	{
		try
		{
		SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd, yyyy");
	    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
	    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
	    System.out.println(format2.format(date));
		return format2.format(date).toString();
		}
		catch(Exception e)
		{
			try
			{
			SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
		    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
		    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
		    System.out.println(format2.format(date));
			return format2.format(date).toString();
			}
			catch(Exception e2)
			{
			  if(dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("st")||dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("nd")||dateRaw.trim().replaceAll(" +", " ").split(" ")[1].contains("th"))
				  dateRaw = dateRaw.trim().replaceAll(" +", " ").replace("st", "").replace("nd", "").replace("th", "");
			  try
				{
				SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd yyyy");
			    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
			    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
			    System.out.println(format2.format(date));
				return format2.format(date).toString();
				}
				catch(Exception e3)
				{
					try
					{
					SimpleDateFormat format1 = new SimpleDateFormat("MMMM dd,yyyy");
				    SimpleDateFormat format2 = new SimpleDateFormat("MM/dd/yyyy");
				    Date date = format1.parse(dateRaw.trim().replaceAll(" +", " "));
				    System.out.println(format2.format(date));
					return format2.format(date).toString();
					}
					catch(Exception e4)
					{
					return "";
					}
				}
			}
		}
	}
	
	    public static String firstDayOfMonth(String date,int month) throws Exception 
	    {
	    	//String string = "02/05/2014"; //assuming input
	        DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	        Date dt = sdf .parse(date);
	        Calendar c = Calendar.getInstance();
	        c.setTime(dt);
	        //if(portfolioType=="MCH")
	        c.add(Calendar.MONTH, month);  //adding a month directly - gives the start of next month.
	        //else c.add(Calendar.MONTH, 2);
	        c.set(Calendar.DAY_OF_MONTH, 01);
	        String firstDate = sdf.format(c.getTime());
	        System.out.println(firstDate);
	        return firstDate;
	    }
	    public static String getCurrentDateTime()
	    {
	    	currentTime ="";
	    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
			 LocalDateTime now = LocalDateTime.now();  
			// System.out.println(dtf.format(now));
			 currentTime = dtf.format(now);
			 return currentTime;
	    }
	    public static String lastDateOfTheMonth(String date) throws Exception
	    {
	    	//String date =RunnerClass.convertDate("January 1, 2023");
	    	LocalDate lastDayOfMonth = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"))
	    	       .with(TemporalAdjusters.lastDayOfMonth());
	    	String newDate = new SimpleDateFormat("MM/dd/yyyy").format(new SimpleDateFormat("yyyy-MM-dd").parse(lastDayOfMonth.toString()));
	    	return newDate;
	    }
	    public static String monthDifference(String date1, String date2) throws Exception
	    {
	    	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
	        Date firstDate = sdf.parse(date1);
	        Date secondDate = sdf.parse(date2);

	        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
	                .appendPattern("MM/dd/yyyy")
	                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
	                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
	               // .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
	                .toFormatter();
	        
           String x =  Duration.between( LocalDate.parse(date1,formatter),  LocalDate.parse(date2,formatter)).toString();
			return "";
	    }
	    public static String getCurrentDate()
	    {
	    	PDFReader.currentDate ="";
	    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");  
			 LocalDateTime now = LocalDateTime.now();  
			// System.out.println(dtf.format(now));
			 PDFReader.currentDate = dtf.format(now);
			 return PDFReader.currentDate;
	    }
	    public static boolean onlyDigits(String str)
	    {
			str = str.replace(",", "").replace(".", "").trim();
			if(str=="")
				return false;
			int numberCount =0;
	        for (int i = 0; i < str.length(); i++) 
	        {
	            if (Character.isDigit(str.charAt(i))) 
	            {
	            	numberCount++;
	            	//return true;
	            }
	        }
	        if(numberCount==str.length())
	        return true;
	        else
	        return false;
	    }

	    public static int nthOccurrence(String str1, String str2, int n) 
	    {
	    	    
	            String tempStr = str1;
	            int tempIndex = -1;
	            int finalIndex = 0;
	            for(int occurrence = 0; occurrence < n ; ++occurrence)
	            {
	                tempIndex = tempStr.indexOf(str2);
	                if(tempIndex==-1){
	                    finalIndex = 0;
	                    break;
	                }
	                tempStr = tempStr.substring(++tempIndex);
	                finalIndex+=tempIndex;
	            }
	            return --finalIndex;
	      }
	    public static String extractNumber(String str) 
		{
		    //String str = "26.23,for";
		    StringBuilder myNumbers = new StringBuilder();
		    for (int i = 0; i < str.length(); i++) 
		    {
		    	char c = str.charAt(i);
		    	
		        if (Character.isDigit(str.charAt(i))||(String.valueOf(c).equals(".")&&i!=str.length()-1)) 
		        {
		            myNumbers.append(str.charAt(i));
		            //System.out.println(str.charAt(i) + " is a digit.");
		        } else {
		            //System.out.println(str.charAt(i) + " not a digit.");
		        }
		    }
		   // System.out.println("Your numbers: " + myNumbers.toString());
		    return myNumbers.toString();
		}

	    public static boolean compareDates(String startDate, String endate, String currentDate)
	    {
	    	try
	    	{
	    		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	    		//System.out.println(sdf.parse(endate).before(sdf.parse(currentDate)));
	    		boolean startDateCheck = sdf.parse(startDate).before(sdf.parse(currentDate));
	    		boolean endDateCheck =  sdf.parse(currentDate).before(sdf.parse(endate));
	    		if(startDateCheck==true&&endDateCheck==true)
	    		return true;
	    		else return false;
	    	}
	    	catch(Exception e)
	    	{
	    		return false;
	    	}
	    }
}
