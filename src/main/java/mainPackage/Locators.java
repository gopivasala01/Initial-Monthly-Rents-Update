package mainPackage;

import org.openqa.selenium.By;

public class Locators 
{
	public static By userName = By.id("loginEmail");
	public static By password = By.name("password");
	public static By signMeIn = By.xpath("//*[@value='Sign Me In']");
	public static By loginError = By.xpath("//*[@class='toast toast-error']");
	
	public static By searchbox = By.name("eqsSearchText");
	public static By dashboardsTab = By.linkText("Dashboards");
	public static By searchingLoader = By.xpath("//*[@id='eqsResult']/h1");
	public static By noItemsFoundMessagewhenLeaseNotFound = By.xpath("//*[text()='No Items Found']");
	public static By selectSearchedLease = By.xpath("//*[@class='results']/descendant::li/a");
	public static By getLeaseCDEType = By.xpath("//*[@id='summary']/table[1]/tbody/tr[3]/td");
    public static By leasesTab = By.xpath("//*[@class='tabbedSection']/a[4]");	
    public static By RCDetails = By.xpath("//*[@id='customFieldGroupTBody1.ManagementTeam']/tr[2]/td[2]/div");
    public static By APMField = By.xpath("//*[text()='APM']/following::input[1]");
    public static By RC = By.xpath("//*[text()='RC']/following::input[1]");
    public static By leaseStartDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[1]");
    public static By leaseEndDate_PW = By.xpath("//*[@id='infoTable']/tbody/tr[3]/td[2]");
    public static By popUpAfterClickingLeaseName = By.id("viewStickyNoteForm");
    public static By popupClose = By.xpath("//*[@id='editStickyBtnDiv']/input[2]");
    public static By notesAndDocs = By.id("notesAndDocuments");
    public static By NoDocumentText = By.xpath("//*[text()='No Attached Notes']");
    public static By documentsList = By.xpath("//*[@id='documentHolderBody']/tr/td[1]/a"); 
    public static By searchedLeaseCompanyHeadings = By.xpath("//*[@id='eqsResult']/div/div/h1");
    
    public static By advancedSearch = By.linkText("Advanced Search >>");
    public static By advancedSearch_buildingsSection = By.id("searchResultTable_buildings");
    public static By advancedSearch_buildingAddresses = By.xpath("//*[@id='searchResultTable_buildings']/following::table[1]/tbody/tr/td[2]/a");
    
    public static By viewLeaseButton = By.xpath("//*[@value='View Lease']");
    public static By advancedSearch_building = By.xpath("//*[@id='searchResultTable_leases']/following-sibling::table[1]/tbody/tr/td[4]/a");
    
    public static By summaryEditButton = By.xpath("//*[@value='Edit']");
    public static By initialMonthlyRent = By.xpath("//*[text()='Initial Monthly Rent']/following::input[1]");
    public static By initialPetRentAmount = By.xpath("//*[text()='Initial Pet Rent Amount']/following::input[1]");
    
    public static By saveLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[1]");
    public static By cancelLease = By.xpath("(//*[@class='primaryButtons'])[2]/input[2]");
    
    public static By PWSiteDownMessage = By.xpath("//*[text()='This issue has been logged.']");
    public static By signIntoPropertyWare = By.xpath("//*[text()='Sign in to propertyware']");
    public static By leaseIDNumber = By.xpath("//*[text()='ID Number']/following-sibling::td[1]");
    public static By baseRent = By.xpath("//*[text()='Base Rent']/following::input[1]");
    
    public static By thirdPartyUnitID = By.xpath("//*[contains(text(),'Third Party Unit ID')]/following::div[1]");
    public static By leaseExecutionDate = By.xpath("//*[contains(text(),'Lease Execution Date')]/following::div[1]");
    
    public static By thisSiteCantBeReached = By.xpath("//*[text()='This site can’t be reached']");

}
