package solid.good.i;

import solid.good.i.message.Edited;
import solid.good.i.message.Message;
import solid.good.i.message.Replied;

public class ChatApplication {

    public static void main(String[] args) {
        ChatManager chatManager = new ChatManager();
        Participant vraj = new Participant(1, "Vraj", "");
        Participant shrey = new Participant(1, "Shrey", "");

        // Participant
        chatManager.addParticipant(vraj);
        chatManager.addParticipant(shrey);

        // Send message
        chatManager.sendMessage("Hi", vraj);
        chatManager.sendMessage("Hello", shrey);
        chatManager.sendMessage("How are you", vraj);
        chatManager.sendMessage("Fine! How are you", shrey);

        System.out.println("\nEdit message (1)");
        chatManager.editMessage(1, "Hi (edited)");
        System.out.println("Message after being edited: " + ((Edited) chatManager.getMessage(1)).getEditedMessage());
        System.out.println("\nReply message (2)");
        chatManager.replyMessage(2, "Hello (replied)");
        System.out.println("Message after replying: " + ((Replied) chatManager.getMessage(2)).getRepliedMessage());
        System.out.println("\nDelete message (4)");
        chatManager.deleteMessage(4);

        System.out.println("\nCurrent messages: ");
        chatManager.getMessages().forEach(pair -> {
            Participant participant = pair.first();
            Message chatMessage = pair.second();
            String message = chatMessage.getMessage();

            if (chatMessage instanceof Edited) {
                message = ((Edited) chatMessage).getEditedMessage();
            } else if (chatMessage instanceof Replied) {
                message = ((Replied) chatMessage).getRepliedMessage();
            } else if (chatMessage.getIsDeleted()) {
                message = "This message was deleted";
            }

            System.out.println("[" + chatMessage.getId() + "]" + "\"" + participant.name() + "\" -> " + message);
        });

        chatManager.removeParticipant(vraj);
        chatManager.removeParticipant(shrey);
    }
}
