import java.util.ArrayList;

public class Mailbox {

    public String ownerID;
    public ArrayList<Message> messages;

    public Message consumeNextMessage(){
        if(this.hasMessages()){
            if(this.messages.get(0).timestamp < 30){
                return this.messages.get(0);
            }
            else messages.remove(0);
        }
        return null;
    }

    public boolean hasMessages(){
        return !this.messages.isEmpty();
    }


}
