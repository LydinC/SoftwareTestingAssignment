import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by User on 30/11/2017.
 */
public class MessagingSystem {

    public ArrayList<Agent> agents = new ArrayList<Agent>();
    public ArrayList<String> blockedWords = new ArrayList<String>();

    public String login(String agentID, String loginKey){
        return null;
    }

    public boolean registerLoginKey(String loginKey, String agentID){
        return true;
    }

    public String sendMessage(String sessionKey, String sourceAgentID, String targetAgentID, String message){
        return null;
    }

    public void setBlockedWords(){
        blockedWords.add("recipe");
        blockedWords.add("ginger");
        blockedWords.add("nuclear");
    }

}
