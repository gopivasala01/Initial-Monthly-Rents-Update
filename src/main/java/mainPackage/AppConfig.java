package mainPackage;

public class AppConfig 
{
	   public static boolean saveButtonOnAndOff= false;
	
	   public static String URL ="https://app.propertyware.com/pw/login.jsp";
	   public static String username ="mds0418@gmail.com";
	   public static String password ="HomeRiver1#";
	   
	   public static String excelFileLocation = "E:\\Automation\\Target Rent Data";
	   public static String downloadFilePath = "C:\\SantoshMurthyP\\Lease Audit Automation";
	   //Mail credentials
	   public static String fromEmail = "bireports@beetlerim.com";
	   public static String fromEmailPassword = "Welcome@123";
	   
	   public static String toEmail = "amesa@homeriver.com,kespinoza@homeriver.com,mjackson@homeriver.com";
	   public static String CCEmail = "gopi.v@beetlerim.com";
	   
	   public static String mailSubject = "Target Rent Update for  ";
	   
	   public static String[] LeaseAgreementFileNames = {"REVISED_Lease_","Lease_"};
	   
	   public static String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
	   
	   public static String leaseFetchQuery  = "Select company, building,leaseName from  Automation.InitialRentsUpdate where Status='Pending'";
	   

}
