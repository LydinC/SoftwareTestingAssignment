import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class AgentTests {

    Agent agent;
    @Before
    public void setup() {
        agent = new Agent();
        agent.agentID = "a0";
        agent.loginKey = "";
        agent.sessionKey = "";
    }

    @After
    public void teardown() {
        agent = null;
    }

    @Test
    public void testNoSupervisorAvailable() {
        setup();
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        when(messagingSystem.getAgents()).thenReturn(agents);
        assertEquals(null, agent.getSupervisor());
        teardown();
    }

    @Test
    public void testSupervisorAvailable() {
        setup();
        //set up a supervisor so that a supervisor will be available
        Agent supervisor = Mockito.mock(SupervisorAgent.class);
        when(supervisor.getAgentID()).thenReturn("s0");
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        agents.add(supervisor);
        when(messagingSystem.getAgents()).thenReturn(agents);
        agent.supervisorID = "s0";
        assertEquals(supervisor, agent.getSupervisor());
        teardown();
    }

    @Test
    public void testLoginWithNoSupervisor() {
        setup();
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        when(messagingSystem.getAgents()).thenReturn(agents);
        assertEquals(false, agent.login());
        teardown();
    }

    @Test
    public void testLoginWithSupervisor() {
        setup();
        //set up a supervisor for the agent so he can validly login
        SupervisorAgent supervisor = Mockito.mock(SupervisorAgent.class);
        when(supervisor.getAgentID()).thenReturn("s0");
        when(supervisor.requestLoginKey("a0")).thenReturn("0123456789");
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        agents.add(supervisor);
        when(messagingSystem.getAgents()).thenReturn(agents);
        when(messagingSystem.registerLoginKey("0123456789","a0")).thenReturn(true);
        String sessionKey = "qazwsxedcrfvtgbyhnujmikloppolikujmnhytgbvfredsewar";
        when(messagingSystem.login("a0","0123456789")).thenReturn(sessionKey);

        agent.supervisorID = "s0";

        assertEquals(true, agent.login());
        teardown();
    }

    @Test
    public void testLoginWithUnregisteredLoginKey() {
        setup();
        SupervisorAgent supervisor = Mockito.mock(SupervisorAgent.class);
        when(supervisor.getAgentID()).thenReturn("s0");
        when(supervisor.requestLoginKey("a0")).thenReturn("0123456789");
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        agents.add(supervisor);
        when(messagingSystem.getAgents()).thenReturn(agents);
        when(messagingSystem.registerLoginKey("0123456789","a0")).thenReturn(false);

        agent.supervisorID = "s0";

        assertEquals(false, agent.login());
        teardown();
    }

    @Test
        public void testLoginWithExpiredLoginKey() {
        setup();
        SupervisorAgent supervisor = Mockito.mock(SupervisorAgent.class);
        when(supervisor.getAgentID()).thenReturn("s0");
        when(supervisor.requestLoginKey("a0")).thenReturn("0123456789");
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        ArrayList<Agent> agents = new ArrayList<Agent>();
        agents.add(agent);
        agents.add(supervisor);
        when(messagingSystem.getAgents()).thenReturn(agents);
        when(messagingSystem.registerLoginKey("0123456789","a0")).thenReturn(true);
        when(messagingSystem.login("a0","0123456789")).thenReturn(null);


        agent.supervisorID = "s0";

        assertEquals(false, agent.login());
        teardown();
    }

    @Test
    public void testExceededMessageCountWhenSendingMessage() {
        setup();
        //set message count over the limit
        agent.messageCount = 25;
        assertEquals(false, agent.sendMessage("a0","msg"));
        teardown();
    }

    @Test
    public void testValidSendingMessage() {
        setup();
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        when(messagingSystem.sendMessage("","a0","s0","hello")).thenReturn("OK");
        //set agent login time to current time so as to not use the login method in this test
        agent.loginTime = System.currentTimeMillis();
        assertEquals(true, agent.sendMessage("s0","hello"));
        teardown();
    }

    @Test
    public void testInvalidSendingMessage() {
        setup();
        MessagingSystem messagingSystem = Mockito.mock(MessagingSystem.class);
        agent.ms = messagingSystem;
        when(messagingSystem.sendMessage("","a0","s0","")).thenReturn("Message exceeds maximum 140 characters. Message not sent.");
        //set agent login time to current time so as to not use the login method in this test
        agent.loginTime = System.currentTimeMillis();
        assertEquals(false, agent.sendMessage("s0",""));
        teardown();
    }

    @Test
    public void testForceLogoutWhileSendingMessage() {
        setup();
        //set agent login time to current time - 11 minutes
        agent.loginTime = System.currentTimeMillis() - 11*60*1000;
        assertEquals(false, agent.sendMessage("",""));
        teardown();
    }
}
