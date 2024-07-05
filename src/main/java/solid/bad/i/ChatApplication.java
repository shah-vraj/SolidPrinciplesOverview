package solid.bad.i;

import solid.bad.i.message.EditedMessage;
import solid.bad.i.message.Message;
import solid.bad.i.message.RepliedMessage;

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
        System.out.println("Trying to get edited message for message (1): " +
                chatManager.getMessages().getLast().second().getEditedMessage());
        chatManager.sendMessage("Hello", shrey);
        System.out.println("Trying to get replied message for message (2): " +
                chatManager.getMessages().getLast().second().getRepliedMessage());
        chatManager.sendMessage("How are you", vraj);
        chatManager.sendMessage("Fine! How are you", shrey);

        System.out.println("Edit message (1)");
        chatManager.editMessage(1, "Hi (edited)");
        System.out.println("Reply message (2)");
        chatManager.replyMessage(2, "Hello (replied)");
        System.out.println("Delete message (4)");
        chatManager.deleteMessage(4);

        System.out.println("\nCurrent messages: ");
        chatManager.getMessages().forEach(pair -> {
            Participant participant = pair.first();
            Message chatMessage = pair.second();
            String message = chatMessage.getMessage();

            if (chatMessage instanceof EditedMessage) {
                message = chatMessage.getEditedMessage();
            } else if (chatMessage instanceof RepliedMessage) {
                message = chatMessage.getRepliedMessage();
            } else if (chatMessage.getIsDeleted()) {
                message = "This message was deleted";
            }

            System.out.println("[" + chatMessage.getId() + "]" + "\"" + participant.name() + "\" -> " + message);
        });

        chatManager.removeParticipant(vraj);
        chatManager.removeParticipant(shrey);
    }
}
