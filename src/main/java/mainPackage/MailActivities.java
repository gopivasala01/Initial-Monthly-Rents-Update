package mainPackage;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MailActivities 
{
	public static void sendANoteToDeveloper() 
	   {
	     
	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtpout.asia.secureserver.net";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      //props.put("mail.smtp.starttls.enable", "true");
	     props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "80");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(AppConfig.fromEmail, AppConfig.fromEmailPassword);
	            }
	         });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(AppConfig.fromEmail));

	         InternetAddress[] toAddresses = InternetAddress.parse(AppConfig.toEmail);
	         // Set To: header field of the header.
	        message.setRecipients(Message.RecipientType.TO,
	        		toAddresses);

	        
	        InternetAddress[] CCAddresses = InternetAddress.parse(AppConfig.CCEmail);
	         // Set CC: header field of the header.
	         message.setRecipients(Message.RecipientType.CC,
	        		 CCAddresses);
	         
	         /*
	         // Set CC: header field of the header.
	         message.setRecipients(Message.RecipientType.BCC,
	            InternetAddress.parse("sujana.t@beetlerim.com"));
	         */
	         // Set Subject: header field
	        String subject = "Initial Rents Update - Automation Process stopped due to site down";
	        message.setSubject(subject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         String messageInBody = "Hi All,\n PropertyWare is down, please review and start the process once it is up\n\n Regards,\n HomeRiver Group.";
	         messageBodyPart.setText(messageInBody);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         /*
	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	        // String filename = "C:\\PropertyWare\\externalFiles\\downloadFiles\\"+"Operations-Marketing.xlsx";
	         System.out.println("FileName sending in mail"+fileName);
	         messageBodyPart.setFileName(new File(fileName).getName());
	         DataSource source = new FileDataSource(fileName);

	         messageBodyPart.setDataHandler(new DataHandler(source));
	         // messageBodyPart.setFileName(filename);
	         messageBodyPart.setFileName(new File(fileName).getName());
	          */
	         multipart.addBodyPart(messageBodyPart);
	         // Send the complete message parts
	         message.setContent(multipart);
	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	         //wait until file is downloaded
	         /*
	         File dir = new File("DownloadPath");
	         //String partialName = downloaded_report.split("_")[0].concat("_"); //get cancelled and add underscore
	        // FluentWait<WebDriver> wait = new FluentWait<WebDriver>(RunnerClass.driver);
	                 //wait.pollingEvery(1, TimeUnit.SECONDS);
	                 //wait.withTimeout(15, TimeUnit.SECONDS);
	                 RunnerClass.wait.until(x -> {
	                     File[] filesInDir = dir.listFiles();
	                     for (File fileInDir : filesInDir) {
	                         if (fileInDir.getName().startsWith("Marketing")) {
	                             return true;
	                         }
	                     }
	                     return false;
	                 });
	         */
	         //delete the current file
	      } catch (MessagingException e) 
	      {
	    	  e.printStackTrace();
	         throw new RuntimeException(e);
	      }
	   }
	
	//Create Excel File with processed data
		public static void createExcelFileWithProcessedData()
		{
			//Get Today's date in MMddyyyy format
			LocalDate dateObj = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
	        String date = dateObj.format(formatter);
	        System.out.println(date);
	        String filename ;
			try   
			{  
			filename = AppConfig.excelFileLocation+"\\InitialRentsUpdate_"+date+".xlsx";  
			File file = new File(filename);
			if(!file.exists())
				file.mkdirs();
			//if file exists, delete and re create it
			if(file.exists())
			{
				file.delete();
			}
			Workbook wb = new XSSFWorkbook();
			Sheet sheet1 = wb.createSheet("Sheet 1");
			Row header = sheet1.createRow(0);
			header.createCell(0).setCellValue("Company");
			header.createCell(1).setCellValue("Building");
			header.createCell(2).setCellValue("Third Party UnitID");
			header.createCell(3).setCellValue("Lease ID Number");
			header.createCell(4).setCellValue("Lease Name");
			header.createCell(5).setCellValue("Lease Status");
			header.createCell(6).setCellValue("Lease Execution Date");
			header.createCell(7).setCellValue("Start Date");
			header.createCell(8).setCellValue("End Date");
			header.createCell(9).setCellValue("Monthly Rent From Lease Agreement");
			header.createCell(10).setCellValue("Monthly Rent From Property Ware");
			header.createCell(11).setCellValue("Pet Rent From Lease Agreement");
			header.createCell(12).setCellValue("Pet Rent From Property Ware");
			header.createCell(13).setCellValue("Status");
			header.createCell(14).setCellValue("Notes");
			//int totalCurrentDayBuildings = RunnerClass.successBuildings.size()+RunnerClass.failedBuildings.size();
			//sheet1.createRow(sheet1.getLastRowNum()+totalCurrentDayBuildings);
			boolean getBuildings =  DataBase.getCompletedBuildingsList();
			if(getBuildings==true&&RunnerClass.completedLeasesList!=null)
			{
				for(int i=0;i<RunnerClass.completedLeasesList.length;i++)
				{
					String company = RunnerClass.completedLeasesList[i][0];
					String building = RunnerClass.completedLeasesList[i][1];
					String thirdPartyUnitID = RunnerClass.completedLeasesList[i][2];
					String leaseIDNumber = RunnerClass.completedLeasesList[i][3];
					String leaseName = RunnerClass.completedLeasesList[i][4];
					String leaseStatus = RunnerClass.completedLeasesList[i][5];
					String leaseExecutionDate = RunnerClass.completedLeasesList[i][6];
					String startDate = RunnerClass.completedLeasesList[i][7];
					String endDate = RunnerClass.completedLeasesList[i][8];
					String monthlyRentFromLeaseAgreement = RunnerClass.completedLeasesList[i][9];
					String monthlyRentFromPW = RunnerClass.completedLeasesList[i][10];
					String petRentFromLeaseAgreement = RunnerClass.completedLeasesList[i][11];
					String petRentFromPW = RunnerClass.completedLeasesList[i][12];
					String status = RunnerClass.completedLeasesList[i][13];
					String notes = RunnerClass.completedLeasesList[i][14];
					
					Row row = sheet1.createRow(1+i);
					try
					{
					row.createCell(0).setCellValue(company);
					}
					catch(Exception e) 
					{company ="";}
					try
					{
					row.createCell(1).setCellValue(building);
					}
					catch(Exception e) 
					{building ="";};
					try
					{
						row.createCell(2).setCellValue(thirdPartyUnitID);
					}
					catch(Exception e)
					{
						thirdPartyUnitID = "";
					}
					
					try
					{
					row.createCell(3).setCellValue(leaseIDNumber);
					}catch(Exception e) 
					{leaseIDNumber = "";}
					try
					{
					row.createCell(4).setCellValue(leaseName);
					}catch(Exception e)
					{
						leaseName = "";
					}
					try
					{
					row.createCell(5).setCellValue(leaseStatus);
					}catch(Exception e)
					{
						leaseStatus = "";
					}
					
					try
					{
						row.createCell(6).setCellValue(leaseExecutionDate);
					}
					catch(Exception e)
					{
						leaseExecutionDate = "";
					}
					try
					{
					row.createCell(7).setCellValue(startDate);
					}
					catch(Exception e)
					{
						startDate = "";
					}
					try
					{
					row.createCell(8).setCellValue(endDate);
					}
					catch(Exception e)
					{
						endDate = "";
					}
					try
					{
					row.createCell(9).setCellValue(monthlyRentFromLeaseAgreement);
					}
					catch(Exception e)
					{
						monthlyRentFromLeaseAgreement = "";
					}
					try
					{
					row.createCell(10).setCellValue(monthlyRentFromPW);
					}
					catch(Exception e)
					{
						monthlyRentFromPW = "";
					}
					try
					{
					row.createCell(11).setCellValue(petRentFromLeaseAgreement);
					}
					catch(Exception e)
					{
						petRentFromLeaseAgreement = "";
					}
					try
					{
					row.createCell(12).setCellValue(petRentFromPW);
					}
					catch(Exception e)
					{
						petRentFromPW = "";
					}
					try
					{
					row.createCell(13).setCellValue(status);
					}
					catch(Exception e)
					{
						status = "";
					}
					try
					{
					row.createCell(14).setCellValue(notes);
					}
					catch(Exception e)
					{
						notes = "";
					}
					
				}
			
			}
			
			System.out.println("Last row in the sheet = "+sheet1.getLastRowNum());
			FileOutputStream fileOut = new FileOutputStream(filename);  
			wb.write(fileOut);
			wb.close();
			fileOut.close();  
			System.out.println("Excel file has been generated successfully.");  
			MailActivities.sendFileToMail(filename);
			}   
			catch (Exception e)   
			{  
			e.printStackTrace();  
			}  
			
		}
		
		
		public static void sendFileToMail(String fileName) 
		   {
		     
		      // Assuming you are sending email through relay.jangosmtp.net
		      String host = "smtpout.asia.secureserver.net";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      //props.put("mail.smtp.starttls.enable", "true");
		     props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "80");

		      // Get the Session object.
		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(AppConfig.fromEmail, AppConfig.fromEmailPassword);
		            }
		         });

		      try {
		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(AppConfig.fromEmail));

		         InternetAddress[] toAddresses = InternetAddress.parse(AppConfig.toEmail);
		         // Set To: header field of the header.
		        message.setRecipients(Message.RecipientType.TO,
		        		toAddresses);

		        
		        InternetAddress[] CCAddresses = InternetAddress.parse(AppConfig.CCEmail);
		         // Set CC: header field of the header.
		         message.setRecipients(Message.RecipientType.CC,
		        		 CCAddresses);
		         
		         /*
		         // Set CC: header field of the header.
		         message.setRecipients(Message.RecipientType.BCC,
		            InternetAddress.parse("sujana.t@beetlerim.com"));
		         */
		         // Set Subject: header field
		        String subject = AppConfig.mailSubject+CommonMethods.getCurrentDate();
		        message.setSubject(subject);

		         // Create the message part
		         BodyPart messageBodyPart = new MimeBodyPart();

		         // Now set the actual message
		         String messageInBody = "Hi All,\n Please find the attachment.\n\n Regards,\n HomeRiver Group.";
		         messageBodyPart.setText(messageInBody);

		         // Create a multipar message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);

		         // Part two is attachment
		         messageBodyPart = new MimeBodyPart();
		        // String filename = "C:\\PropertyWare\\externalFiles\\downloadFiles\\"+"Operations-Marketing.xlsx";
		         System.out.println("FileName sending in mail"+fileName);
		         messageBodyPart.setFileName(new File(fileName).getName());
		         DataSource source = new FileDataSource(fileName);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		        // messageBodyPart.setFileName(filename);
		         messageBodyPart.setFileName(new File(fileName).getName());
		         multipart.addBodyPart(messageBodyPart);

		         // Send the complete message parts
		         message.setContent(multipart);

		         // Send message
		         Transport.send(message);

		         System.out.println("Sent message successfully....");
		  
		         //wait until file is downloaded
		         /*
		         File dir = new File("DownloadPath");
		         //String partialName = downloaded_report.split("_")[0].concat("_"); //get cancelled and add underscore
		        // FluentWait<WebDriver> wait = new FluentWait<WebDriver>(RunnerClass.driver);
		                 //wait.pollingEvery(1, TimeUnit.SECONDS);
		                 //wait.withTimeout(15, TimeUnit.SECONDS);
		                 RunnerClass.wait.until(x -> {
		                     File[] filesInDir = dir.listFiles();
		                     for (File fileInDir : filesInDir) {
		                         if (fileInDir.getName().startsWith("Marketing")) {
		                             return true;
		                         }
		                     }
		                     return false;
		                 });
		         */
		         //delete the current file
		         File file = new File(fileName);
		         file.delete();
		      } catch (MessagingException e) 
		      {
		    	  e.printStackTrace();
		         throw new RuntimeException(e);
		      }
		   }
	

}
