//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class MessageTests {
//
//    Message message;
//    @Before
//    public void setup() {
//        message = new Message("src","trg",0,"msg");
//    }
//
//    @After
//    public void teardown() {
//        message = null;
//    }
//
//    @Test
//    public void testOverloadedConstructor() {
//
//        setup();
//
//        //Verify
//        assertEquals("src", message.sourceAgentID);
//        assertEquals("trg", message.targetAgentID);
//        assertEquals(0, message.timestamp);
//        assertEquals("msg", message.messageContent);
//        teardown();
//    }
//}
