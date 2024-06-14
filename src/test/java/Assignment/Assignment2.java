package Assignment;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static io.restassured.RestAssured.given;

public class Assignment2 {

    String data = null;
    String value1 = null;
    PostJsonfile RJB = new PostJsonfile();
    String response_body;
    XLSupport obj;
    private ExtentSparkReporter spark;
    private ExtentReports extent;
    private ExtentTest logger;

    public void setUp() {

        obj = new XLSupport();

        extent = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/ExtentReport/Workshop_API.html"); //current path of the project using System.getProperty
        //System.getProperty("user.dir") == C:\Users\sunilk\IdeaProjects\TestNG_RestAssurd\ExtentReport
        spark.config().setDocumentTitle("https://api.restful-api.dev/objects GET API call");
        spark.config().setReportName("get call to \"https://api.restful-api.dev/objects\" and validate response");
        spark.config().setTheme(Theme.STANDARD);
        extent.attachReporter(spark);
        extent.setSystemInfo("QA Name", "Sonali");
        extent.setSystemInfo("Build name", "16.3.2");
        extent.setSystemInfo("ENV Name", "NAM UAT-2");
        logger = extent.createTest("Validate the Response");

    }

    public String read_And_Print_XL_AsPerTestData(String testcasename, String columname) {

        //String data = null;
        try {

            //String XLFilePath = System.getProperty("user.dir")+"/GETWEB.xlsx";
            String XLFilePath = "C:\\Users\\sonalik\\IdeaProjects\\CapstoneProject\\src\\Creditcard.xlsx";
            FileInputStream myxlfile = new FileInputStream(XLFilePath);
            Workbook workbook = new XSSFWorkbook(myxlfile);
            Sheet sheet = workbook.getSheet("Sheet1");
            int lastRow = sheet.getLastRowNum();
            System.out.println("The last row which has data ==" + lastRow);

            for (int i = 1; i < lastRow; i++) {
                Row row = sheet.getRow(i);
                int lastcell = row.getLastCellNum();
                Cell cell1 = row.getCell(0);
                // String value1 =cell1.getStringCellValue();
                System.out.println("Column Value " + cell1);
                String XLdataCreditCarddata = testexceldatainDB(cell1.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    Connection connection;
    //String key = null;

    public static void main(String[] args) {
        Assignment2 dbconnect = new Assignment2();
        dbconnect.CreateNewTable();


    }

    public void createdatabasenew() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            if (connection != null) {
                System.out.println("Database server is connected");
            }

            Statement statement = connection.createStatement();
            statement.execute("Create Database Credit");
            System.out.println("Database created successfully");


        } catch (Exception e) {
            System.out.println("SQL Error");
        }
    }

    public void CreateNewTable() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            if (connection != null) {
                System.out.println("database server is connected");
            }
            Statement statement = connection.createStatement();
            statement.execute("use Credit");
            statement.execute("Drop table if exists Creditdata");
            statement.execute("create table Creditdata \n" +
                    " (Name varchar (255),\n" +
                    " Year varchar (255),\n" +
                    " Creditcardno varchar (255),\n" +
                    " CreditLimit varchar (255),\n" +
                    " EXPDate varchar (255),\n" +
                    " CardType varchar (255) \n" +
                    " )");
            System.out.println("Database table is created");


            statement.execute("Insert into Creditdata(Name,Year,Creditcardno,CreditLimit,EXPDate,CardType) values ('Baharti','1986','2868676786','5L','05-05-2025','MasterCard') ,\n" +
                    "('Testi','2004','4568676786','6L','06-05-2025','MasterCard'),\n" +
                    "('Sonali','2006','2863526786','7L','10-05-2025','VISA'),\n" +
                    "('Madhuri','2007','1238676786','5L','11-05-2025','MasterCard'),\n" +
                    "('Saee','1987','3645676786','5L','05-06-2025','VISA'),\n" +
                    "('Komal','1968','2868657346','5L','05-05-2025','MasterCard'),\n" +
                    "('Chitra','1987','8564676786','5L','07-07-2025','VISA'),\n" +
                    "('Shweta','1990','1325676786','5L','08-06-2025','MasterCard'),\n" +
                    "('Tina','2007','3425689560','5L','09-05-2025','VISA'),\n" +
                    "('Mona','2006','1325676123','5L','03-05-2025','MASTER');");

            System.out.println("Table data is inserted");

            statement.execute("use Credit");
            statement.execute("Drop table if exists Creditcard");
            statement.execute("create table Creditcard \n" +
                    " (Creditcardno varchar (255),\n" +
                    " Pancardno varchar (255) \n" +
                    " )");
            System.out.println("Database table is created");


            statement.execute("Insert into Creditcard(Creditcardno,Pancardno) values ('2868676786','LM65657') ,\n" +
                    "('4568676786','XL65657878'),\n" +
                    "('2863526786','MM6565767'),\n" +
                    "('1238676786','KJ87565767'),\n" +
                    "('3645676786','JH6565798'),\n" +
                    "('2868657346','SE8565776'),\n" +
                    "('8564676786','LD6765767'),\n" +
                    "('1325676786','KN8565767'),\n" +
                    "('3425689560','DF8565787'),\n" +
                    "('1325676123','FD6798980');");

            System.out.println("Table data is inserted");


        } catch (Exception e) {
            System.out.println(e);

        }
    }

   // @Parameters({"url"})
  //  @Test
    public String testexceldatainDB(String Creditcardno) {

        String Value = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            Statement statement = connection.createStatement();
            statement.execute("use Credit");
            //statement.execute("Drop table if exists aadhardata");

            String query = "select * from Creditdata inner join Creditcard on Creditdata.Creditcardno = Creditcard.Creditcardno";
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String name1 = resultSet.getString("Name");
                String year1 = resultSet.getString("Year");
                String Creditcardno1 = resultSet.getString("Creditcardno");
                String Creditlimit1 = resultSet.getString("CreditLimit");
                String EXPDate1 = resultSet.getString("EXPDate");
                String CardType1 = resultSet.getString("CardType");
                String Pancardno1 = resultSet.getString("Pancardno");
                System.out.println("Name from DB: " + name1);
                System.out.println("Year from DB: " + year1);
                System.out.println("Credit Card No from DB: " + Creditcardno1);
                System.out.println("Credit Limit from DB: " + Creditlimit1);
                System.out.println("Expiry Date from DB: " + EXPDate1);
                System.out.println("Card Type from DB: " + CardType1);
                System.out.println("PanCard no from DB: "+Pancardno1);

                Response res = given().
                        contentType(ContentType.JSON).
                        body(RJB.CreatePostJsonfile(name1, year1, Creditcardno1, Creditlimit1, EXPDate1, CardType1)).
                        when().
                        post("https://api.restful-api.dev/objects");
                int status_code = res.statusCode();
                response_body = res.getBody().asString();
                System.out.println(status_code);
                System.out.println(response_body);
                String ID = res.getBody().jsonPath().getString("id");
                System.out.println("ID is : " + ID);
                String Createdata = res.getBody().jsonPath().getString("createdAt");
                System.out.println("Created Date is current Date: " + Createdata);
                String Name = res.getBody().jsonPath().getString("name");
                System.out.println("Name is: " + Name);
                Assert.assertEquals(name1, Name);
                String Year = res.getBody().jsonPath().getString("data.year");
                System.out.println("Year is: " + Year);
                Assert.assertEquals(year1, Year);
                String CreditCard = res.getBody().jsonPath().getString("data[\"Credit Card Number\"]");
                System.out.println("Credit Card number is: " + CreditCard);
                Assert.assertEquals(Creditcardno1, CreditCard);
                String CreditLimit = res.getBody().jsonPath().getString("data.CreditLimit");
                System.out.println("Credit Limit is: " + CreditLimit);
                Assert.assertEquals(Creditlimit1, CreditLimit);
                String ExptDate = res.getBody().jsonPath().getString("data[\"EXP Date\"]");
                System.out.println("Expiry Date is: " + ExptDate);
                Assert.assertEquals(EXPDate1, ExptDate);
                String CardType = res.getBody().jsonPath().getString("data[\"Card Type\"]");
                System.out.println("Expiry Date is: " + CardType);
                Assert.assertEquals(CardType1, CardType);



            } else {
                Assert.fail("No data found in the database for the given condition.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return Value;
    }






}