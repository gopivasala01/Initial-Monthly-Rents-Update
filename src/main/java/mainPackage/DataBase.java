package mainPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase 
{
	public static boolean getBuildingsList()
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = AppConfig.leaseFetchQuery;
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) 
		            {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("No of Rows = "+rows);
		            RunnerClass.pendingLeases = new String[rows][3];
		           int  i=0;
		            while(rs.next())
		            {
		            	
		            	String 	company =  (String) rs.getObject(1);
		                String  buildingAbbreviation = (String) rs.getObject(2);
		                String  ownerName = (String) rs.getObject(3);
		                System.out.println(company +" |  "+buildingAbbreviation+"  --> "+ownerName);
		    				//Company
		    				RunnerClass.pendingLeases[i][0] = company;
		    				//Building Abbreviation
		    				RunnerClass.pendingLeases[i][1] = buildingAbbreviation;
		    				//Owner Name
		    				RunnerClass.pendingLeases[i][2] = ownerName;
		    				i++;
		            }	
		            System.out.println("Total Pending Buildings  = " +RunnerClass.pendingLeases.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}
	
	public static void updateTable(String query)
	 {
		    try (Connection conn = DriverManager.getConnection(AppConfig.connectionUrl);
		        Statement stmt = conn.createStatement();) 
		    {
		      stmt.executeUpdate(query);
		      System.out.println("Record Updated");
		      stmt.close();
	            conn.close();
		    } catch (SQLException e) 
		    {
		      e.printStackTrace();
		    }
	 }
	
	public static boolean getCompletedBuildingsList()
	{
		try
		{
		        Connection con = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		            //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		            con = DriverManager.getConnection(AppConfig.connectionUrl);
		            String SQL = AppConfig.getLeasesWithStatusforCurrentDay;
		            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		           // stmt = con.createStatement();
		            rs = stmt.executeQuery(SQL);
		            int rows =0;
		            if (rs.last()) {
		            	rows = rs.getRow();
		            	// Move to beginning
		            	rs.beforeFirst();
		            }
		            System.out.println("No of buildings with status = "+rows);
		            RunnerClass.completedLeasesList = new String[rows][14];
		           int  i=0;
		            while(rs.next())
		            {
		            	
		            	String 	company =  (String) rs.getObject(1);
		                String  building = (String) rs.getObject(2);
		                String  thirdPartyUnitID = (String) rs.getObject(3);
		                String  leaseIDNumber = (String) rs.getObject(4);
		                String  leaseName = (String) rs.getObject(5);
		                String  leaseExecutionDate = (String) rs.getObject(6);
		                String  startDate = (String) rs.getObject(7);
		                String  endDate = (String) rs.getObject(8);
		                String  monthlyRentFromLeaseAgreement = (String) rs.getObject(9);
		                String  monthlyRentFromPW = (String) rs.getObject(10);
		                String  petRentRentFromLeaseAgreement = (String) rs.getObject(11);
		                String  petRentFromPW = (String) rs.getObject(12);
		                String  Status = (String) rs.getObject(13);
		                String  Notes = (String) rs.getObject(14);
		                
		               // System.out.println(company +" | "+building+" | "+targetRent+" | "+targetDeposit+" | "+listingAgent+" | "+status+" | "+notes);
		    				//Company
		    				RunnerClass.completedLeasesList[i][0] = company;
		    				//Port folio
		    				RunnerClass.completedLeasesList[i][1] = building;
		    				//Third Party Unit ID
		    				RunnerClass.completedLeasesList[i][2] = thirdPartyUnitID;
		    				//Third Party Unit ID
		    				RunnerClass.completedLeasesList[i][3] = leaseIDNumber;
		    				//Lease Name
		    				RunnerClass.completedLeasesList[i][4] = leaseName;
		    				//Lease Name
		    				RunnerClass.completedLeasesList[i][5] = leaseExecutionDate;
		    				//Target Deposit
		    				RunnerClass.completedLeasesList[i][6] = startDate;
		    				//Listing Agent
		    				RunnerClass.completedLeasesList[i][7] = endDate;
		    				//Status
		    				RunnerClass.completedLeasesList[i][8] = monthlyRentFromLeaseAgreement;
		    				//Notes
		    				RunnerClass.completedLeasesList[i][9] = monthlyRentFromPW;
		    				//Notes
		    				RunnerClass.completedLeasesList[i][10] = petRentRentFromLeaseAgreement;
		    				//Notes
		    				RunnerClass.completedLeasesList[i][11] = petRentFromPW;
		    				//Notes
		    				RunnerClass.completedLeasesList[i][12] = Status;
		    				//Notes
		    				RunnerClass.completedLeasesList[i][13] = Notes;
		    				i++;
		            }	
		           // System.out.println("Total Pending Buildings  = " +RunnerClass.pendingBuildingList.length);
		            //for(int j=0;j<RunnerClass.pendingBuildingList.length;j++)
		            //{
		            //	System.out.println(RunnerClass.pendingBuildingList[j][j]);
		           // }
		            rs.close();
		            stmt.close();
		            con.close();
		 return true;
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		 return false;
		}
	}

}
