package Assignment;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class XLSupport {

    String testname = null;

    Assignment2 obj;

    @BeforeClass
    public void setup()
    {
        obj =new Assignment2();
    }

    @Test
    public void do_all_web_app_test()
    {

        String NameXL =obj.read_And_Print_XL_AsPerTestData(testname,"Name");



    }
}
