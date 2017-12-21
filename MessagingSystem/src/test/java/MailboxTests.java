//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class MailboxTests {
//
//    Mailbox mailbox;
//    @Before
//    public void setup() {
//        mailbox = new Mailbox();
//    }
//
//    @After
//    public void teardown() {
//        mailbox = null;
//    }
//
//    @Test
//    public void testEmptyMailbox() {
//        setup();
//        assertEquals(false, mailbox.hasMessages());
//        teardown();
//    }
//
//    @Test
//    public void testNonEmptyMailbox() {
//        setup();
//        Message message = new Message("src","trg",0,"msg");
//        mailbox.messages.add(message);
//        assertEquals(true, mailbox.hasMessages());
//        teardown();
//    }
//
//    @Test
//    public void testValidMessageConsumption() {
//        setup();
//        Message message = new Message("src","trg",0,"msg");
//        mailbox.messages.add(message);
//        assertEquals(message, mailbox.consumeNextMessage());
//        teardown();
//    }
//
//    @Test
//    public void testExpiredMessageConsumption() {
//        setup();
//        Message message = new Message("src","trg",31,"msg");
//        mailbox.messages.add(message);
//        assertEquals(null, mailbox.consumeNextMessage());
//        teardown();
//    }
//
//    @Test
//    public void testNonExistentMessageConsumption() {
//        setup();
//        assertEquals(null, mailbox.consumeNextMessage());
//        teardown();
//    }
//}