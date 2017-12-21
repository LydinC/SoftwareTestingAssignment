public class main {
    public static void main(String args[]) {
        MessagingSystem messagingSystem = new MessagingSystem();
        messagingSystem.setBlockedWords();

        Agent agent1 = new SupervisorAgent();
        Agent agent2 = new Agent();
        Agent agent3 = new Agent();

        agent1.ms = messagingSystem;
        agent2.ms = messagingSystem;
        agent3.ms = messagingSystem;

        agent1.name = "Joe";
        agent1.agentID = "as00";
        agent1.supervisorID = "as00";

        agent2.name = "John";
        agent2.agentID = "a01";
        agent2.supervisorID = "as00";

        agent3.name = "George";
        agent3.agentID = "a02";
        agent3.supervisorID = "as00";

        messagingSystem.agents.add(agent1);
        messagingSystem.agents.add(agent2);
        messagingSystem.agents.add(agent3);

        agent1.login();
        agent2.login();
        agent3.login();

        try {
            Thread.sleep(10*60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(agent1.readNextMessage());
    }
}
