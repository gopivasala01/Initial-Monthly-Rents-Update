package mainPackage;

public class AppConfig 
{
	   public static boolean saveButtonOnAndOff= false;
	
	   public static String URL ="https://app.propertyware.com/pw/login.jsp";
	   public static String username ="mds0418@gmail.com";
	   public static String password ="KRm#V39fecMDGg#";
	   
	   public static String excelFileLocation = "E:\\Automation\\Initial Rents Update";
	   public static String downloadFilePath = "C:\\SantoshMurthyP\\Initial Rents Update";
	   //Mail credentials
	   public static String fromEmail = "bireports@beetlerim.com";
	   public static String fromEmailPassword = "Welcome@123";
	   
	   public static String toEmail = "gopi.v@beetlerim.com";
	   public static String CCEmail = "gopi.v@beetlerim.com";
	   
	   public static String mailSubject = "Initial Rents Update for  ";
	   
	   public static String[] LeaseAgreementFileNames = {"REVISED_Lease_","Lease_","Leases_"};
	   public static String [] LeaseAgreementFileNamesMOD = {"Lease_Modification", "Lease_MOD"};
	   
	   public static String connectionUrl = "jdbc:sqlserver://azrsrv001.database.windows.net;databaseName=HomeRiverDB;user=service_sql02;password=xzqcoK7T;encrypt=true;trustServerCertificate=true;";
	   
	  // public static String leaseFetchQuery  = "Select Company, Building,leaseName from Automation.InitialRentsUpdate where Status ='Pending' and Company ='Georgia'";
	   

	   public static String pendingLeasesQuery = "WITH CTE AS\r\n"
	   		+ "(\r\n"
	   		+ "Select *, CAST(REPLACE(MonthlyRent,',','') as Decimal(18,2)) \r\n"
	   		+ "- CAST(REPLACE(MonthlyRentFromPW,',','') as Decimal(18,2)) as Differene  from  [automation].[initialrentsupdate] \r\n"
	   		+ "where  MonthlyRent <> '' and MonthlyRent is not null and MonthlyRentFromPW is not null and MonthlyRentFromPW <>'' and MonthlyRentFromPW <>'Error' and\r\n"
	   		+ "CAST(REPLACE(MonthlyRent,',','') as Decimal(18,2)) >0.00 and CAST(REPLACE(MonthlyRentFromPW,',','') as Decimal(18,2))>0.00\r\n"
	   		+ ")\r\n"
	   		+ "Select Company, Building, LeaseName from CTE where Differene>100 and Status='Completed' and LeaseStatus Not in ('Dead Application','Terminated')  and company in ('Alabama')";
	   		

	   
	  
	   
	   public static String failedLeasesQuery = "Select Company, Building,leaseName from Automation.InitialRentsUpdate where Status ='Failed'  and (Notes ='Building Not Found' or  Notes = ',Unable to Click Lease Onwer Name') and company in ('Alabama')";
	    
	   public static String getLeasesWithStatusforCurrentDay = "Select Company, Building,ThirdPartyUnitID, Leaseidnumber, LeaseName,LeaseStatus,leaseExecutionDate, StartDate, EndDate, MonthlyRent, MonthlyRentFromPW, PetRent, PetRentFromPW,Status, Notes from Automation.InitialRentsUpdate ";//where Format(convert(datetime, CompletedDate, 101),'dd MM yyyy') = format(getdate(),'dd MM yyyy') ";//and company in ('Florida','North Carolina')";
	   

}
