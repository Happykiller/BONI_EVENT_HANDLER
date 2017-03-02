import org.bonita.eventHandler.Lib;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import java.util.*;

/**
 * Lib Tester.
 *
 * @author <Authors name>
 * @since <pre>nov. 4, 2015</pre>
 * @version 1.0
 */
public class LibTest {
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: sayHelloMessage()
     *
     */
    @Test
    public void testGetProperty() throws Exception {
        String receive;
        String waiting = "true";

        //-Dproperties.path=C:\DATAS\myProperties.properties
        //debug=true

        Properties props = System.getProperties();
        props.setProperty("properties.path", "myProperties.properties");

        receive = Lib.getProperty("debug");
        Assert.assertEquals(receive,waiting);
    }

    /**
     *
     * Method: sayHelloMessage()
     *
     */
    @Test
    public void testSayHelloMessage() throws Exception {
        String receive;
        String waiting = "HelloWorld";
        Lib lib = new Lib();
        receive = lib.sayHelloMessage();
        Assert.assertEquals(receive,waiting);
    }
}