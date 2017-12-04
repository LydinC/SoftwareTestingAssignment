import java.util.Random;

public class Agent implements Supervisor {

    public String agentID;
    public String loginKey = "";
    public String name;
    public MessagingSystem ms;
    public Mailbox mailbox;
    public boolean isSupervisor = false;
    public int messageCount = 0;

    public String supervisorID; // the supervisor of the agent

    public boolean login(){
        if(this.getSupervisor()!= null) {
            this.loginKey = this.getSupervisor().getLoginKey(this.agentID);
            this.ms.login(this.agentID, this.loginKey);
            return true;
        }
        else{
            return false;
        }
    }

    public boolean sendMessage(String receiverID, String messageContent){
        if(loginKey.length() != 0) {
            if (messageContent.length() < 140) {
                for (String blockedWord : ms.blockedWords) {
                    if (messageContent.toLowerCase().contains(blockedWord)) {
                        System.out.println("Blocked word is in message : " + blockedWord);
                        return false;
                    }
                }
            } else {
                System.out.println("Message exceeds maximum 140 characters.");
            }
        }
        else{
                System.out.println("Login Time Exceeded");
        }

        //need to check : login key not expired (10mins) and content <140 chars and no blocked words)
        return true;
    }

    public String getLoginKey(String AgentID) {
        String alphabet = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!%^&*()$£";
        int max = alphabet.length();
        Random r = new Random();
        String keyGenerator  = "";
        for (int i = 0; i < 10; i++) {
            keyGenerator = keyGenerator + alphabet.charAt(r.nextInt(max));
        }
        return keyGenerator;
    }


    public Agent getSupervisor(){
        for(Agent a : this.ms.agents) {
            if (a.agentID == supervisorID && a.isSupervisor) {
                return a;
            }
        }
        System.out.println("No supervisor found with id : " + supervisorID);
        return null;
    }
}


