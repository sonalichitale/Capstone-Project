package Assignment;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.plaf.IconUIResource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicReference;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

public class Assignment1 {

    Connection connection;
    String response_body;
    String user_id;
    //String key = null;
    PostJsonfile RJB = new PostJsonfile();;



    public static void main(String[] args) {
        Assignment1 dbconnect = new Assignment1();
        dbconnect.CreateNewTable();
        dbconnect.read_Properties_file("key");

    }


    public void createdatabasenew() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            if (connection != null) {
                System.out.println("Database server is connected");
            }

            Statement statement = connection.createStatement();
            statement.execute("Create Database aadhar");
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
            statement.execute("use aadhar");
            statement.execute("Drop table if exists aadhardata");
            statement.execute("create table aadhardata \n" +
                    " (Fname varchar (255),\n" +
                    " Lname varchar (255),\n" +
                    " Aadhar_No varchar (255),\n" +
                    " Address varchar (255),\n" +
                    " phone varchar (10) \n" +
                    " )");
            System.out.println("Database table is created");


            statement.execute("Insert into aadhardata(Fname,Lname,Aadhar_No,Address,phone) values ('Baharti','Parab','PMJ6712345','Hadapsar','769878767') ,\n" +
                    "('Testi','Rai','ABC12345','Mysore','97886610'),\n" +
                    "('Sonali','Chitale','CBD12345','Coorg','978866116'),\n" +
                    "('Madhuri','Suryawanshi','NMB2345','Mumbai','98224455'),\n" +
                    "('Saee','Chitale','HJM574545','Pune','875463782'),\n" +
                    "('Komal','Savant','JKD12345','Thane','873565442'),\n" +
                    "('Chitra','Patil','MNK12345','Chennai','9758566756'),\n" +
                    "('Shweta','Kakade','AMN8672345','Banglore','78989889'),\n" +
                    "('Tina','Kune','RSR782345','Kalyan','877565456'),\n" +
                    "('Mona','Patil','YUD6756578','Bangalore','877565697');");

            System.out.println("Table data is inserted");

        } catch (Exception e) {
            System.out.println(e);

        }
    }


    public String read_Properties_file(String key) {
        Properties prop = new Properties();
        String value = null;
        try {
            prop.load(new FileInputStream(System.getProperty("user.dir") + "/src/aadhardata.properties"));
            value = prop.getProperty(key);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        System.out.println(value);
       // return value;


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            Statement statement = connection.createStatement();
            statement.execute("use aadhar");
            //statement.execute("Drop table if exists aadhardata");

           String query = "Select * from aadhardata where Aadhar_No = 'JKD12345'";
           ResultSet resultSet = statement.executeQuery(query);


            // Assert the result
            if (resultSet.next()) {
                String retrievedValue = resultSet.getString("Aadhar_No");
                System.out.println(retrievedValue);
                Assert.assertEquals(retrievedValue, value);
            } else {
                Assert.fail("No data found in the database for the given condition.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return value;
    }


    @Parameters ({"url","Fname1","Lname1","Aadhar_No1","Address1","phone1"})
    @Test
    public void Create_Post_API(String url,String Fname1,String Lname1,String Aadhar_No1,String Address1,String phone1) {
        Response res = given().
                contentType(ContentType.JSON).
                body(RJB.CreatePostJson(Fname1, Lname1, Aadhar_No1, Address1, phone1)).
                when().
                post(url);
        int status_code = res.statusCode();
        response_body = res.getBody().asString();
        System.out.println(status_code);
        System.out.println(response_body);
        user_id = res.getBody().jsonPath().getString("id");
        System.out.println("Account ID is: " + user_id);
        System.out.println("ID is numeric: " + user_id);
        String Createdata = res.getBody().jsonPath().getString("createdAt");
        System.out.println("Created Date is current Date: " + Createdata);
        String Aadhardata = res.getBody().jsonPath().getString("Aadhar_No");
        System.out.println("Addhar number is: " + Aadhardata);
        String Fname = res.getBody().jsonPath().getString("Fname");
        System.out.println("Fname is: " + Fname);
        String Lname = res.getBody().jsonPath().getString("Lname");
        System.out.println("Lname is: " + Lname);
        String Address = res.getBody().jsonPath().getString("Address");
        System.out.println("Address is: " + Address);
        String Phone = res.getBody().jsonPath().getString("phone");
        System.out.println("Phone is: " + Phone);


        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306", "sonali", "maveric@123");
            Statement statement = connection.createStatement();
            statement.execute("use aadhar");
            //statement.execute("Drop table if exists aadhardata");

            String query = "Select * from aadhardata where Aadhar_No = 'JKD12345'";
            ResultSet resultSet = statement.executeQuery(query);


            // Assert the result
            if (resultSet.next()) {
                String retrievedValue = resultSet.getString("Aadhar_No");
                String Fnameretrive = resultSet.getString("Fname");
                String Lnameretrive = resultSet.getString("Lname");
                String Addressretrive = resultSet.getString("Address");
                String Phoneretrive = resultSet.getString("phone");
                System.out.println("Addhar_No from DB: "+retrievedValue);
                System.out.println("Fname from DB: "+Fnameretrive);
                System.out.println("Lname from DB: "+Lnameretrive);
                System.out.println("Address from DB: "+Addressretrive);
                System.out.println("Phone from DB: "+Phoneretrive);
               Assert.assertEquals(retrievedValue, Aadhardata);
                Assert.assertEquals(Fnameretrive, Fname);
                Assert.assertEquals(Lnameretrive, Lname);
                Assert.assertEquals(Addressretrive, Address);
                Assert.assertEquals(Phoneretrive, Phone);
            } else {
                Assert.fail("No data found in the database for the given condition.");
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}






