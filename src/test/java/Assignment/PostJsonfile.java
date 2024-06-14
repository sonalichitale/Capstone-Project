package Assignment;

public class PostJsonfile {
    public String CreatePostJson(String Fname1,String Lname1,String Aadhar_No1,String Address1,String phone1)
    {
        String body ="{\n" +
                "    \"Fname\":\""+Fname1+"\",\n" +
                "    \"Lname\":\""+Lname1+"\",\n" +
                "    \"Aadhar_No\":\""+Aadhar_No1+"\",\n" +
                "    \"Address\":\""+Address1+"\",\n" +
                "    \"phone\":\""+phone1+"\"\n" +
                   "}";
        return body;
    }

    public String CreatePostJsonfile(String name1,String year1,String Creditcardno1,String Creditlimit1,String EXPDate1,String CardType1)
    {
        String body1 ="{\n" +
                "    \"name\":\""+name1+"\",\n" +
                "    \"data\":{\n" +
                "        \"year\": "+year1+",\n" +
                "        \"Credit Card Number\": \""+Creditcardno1+"\",\n" +
                "        \"CreditLimit\": \""+Creditlimit1+"\",\n" +
                "        \"EXP Date\": \""+EXPDate1+"\",\n" +
                "        \"Card Type\": \""+CardType1+"\"\n" +
                "    }\n" +
                "}";
        return body1;
    }
}


