//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class MessagingSystemTests {
//
//    MessagingSystem messagingSystem;
//    @Before
//    public void setup() {
//        messagingSystem = new MessagingSystem();
//    }
//
//    @After
//    public void teardown() {
//        messagingSystem = null;
//    }
//
//    @Test
//    public void testSetBlockedWords() {
//        setup();
//        assertEquals(0, messagingSystem.blockedWords.size());
//        messagingSystem.setBlockedWords();
//        assertEquals(3, messagingSystem.blockedWords.size());
//        teardown();
//    }
//
//    @Test
//    public void testValidGetIndexWithAgentID() {
//        setup();
//        Agent agent = new Agent();
//        agent.agentID = "a0";
//        messagingSystem.agents.add(agent);
//        assertEquals(0,messagingSystem.getIndexWithAgentId("a0"));
//        teardown();
//    }
//
//    @Test
//    public void testInvalidGetIndexWithAgentID() {
//        setup();
//        Agent agent = new Agent();
//        agent.agentID = "a0";
//        messagingSystem.agents.add(agent);
//        assertEquals(-1,messagingSystem.getIndexWithAgentId("a1"));
//        teardown();
//    }
//
//    @Test
//    public void testFiftyCharacterSessionKey() {
//        setup();
//        assertEquals(50,messagingSystem.getSessionKey().length());
//        teardown();
//    }
//
//    @Test
//    public void testRegistrationWithInvalidLengthLoginKey() {
//        setup();
//        assertEquals(false,messagingSystem.registerLoginKey("abcd","a0"));
//        teardown();
//    }
//
//    @Test
//    public void testRegistrationWithNonUniqueLoginKey() {
//        setup();
//        //add an agent with the same login key to be added
//        Agent agent = new Agent();
//        agent.agentID = "a0";
//        agent.loginKey = "1234567890";
//        messagingSystem.agents.add(agent);
//
//        assertEquals(false,messagingSystem.registerLoginKey("1234567890","a1"));
//        teardown();
//    }
//
//    @Test
//    public void testValidRegistration() {
//        setup();
//        //add an agent with the different login key to be added
//        Agent agent = new Agent();
//        agent.agentID = "a0";
//        agent.loginKey = "1234567890";
//        messagingSystem.agents.add(agent);
//
//        assertEquals(true,messagingSystem.registerLoginKey("0123456789","a1"));
//        teardown();
//    }
//}
