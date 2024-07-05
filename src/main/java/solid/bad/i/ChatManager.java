package solid.bad.i;

import solid.bad.i.message.EditedMessage;
import solid.bad.i.message.Message;
import solid.bad.i.message.NormalMessage;
import solid.bad.i.message.RepliedMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChatManager {

    private final List<Pair<Participant, Message>> messages = new ArrayList<>();
    private final List<Participant> participants = new ArrayList<>();

    /**
     * Provides all messages in the room(chat room)
     * @return List of a Pair of Participant to Message representing overall chat bubble shown in chat
     */
    public List<Pair<Participant, Message>> getMessages() {
        return messages;
    }

    /**
     * Add a participant in the chat room
     * @param participant Participant object to add
     */
    public void addParticipant(Participant participant) {
        if (participant == null) {
            System.out.println("Participant is null!");
            return;
        }
        if (participants.contains(participant)) {
            System.out.println("Participant is already in chat!");
            return;
        }
        participants.add(participant);
    }

    /**
     * Remove a participant from the chat room
     * @param participant Participant object to remove
     */
    public void removeParticipant(Participant participant) {
        if (participant == null) {
            System.out.println("Participant is null!");
            return;
        }
        if (!participants.contains(participant)) {
            System.out.println("Participant is not in chat!");
            return;
        }
        participants.remove(participant);
    }

    /**
     * Send a message in the chat room
     * @param message String message to send
     * @param participant Participant that is sending the message
     */
    public void sendMessage(String message, Participant participant) {
        if (message == null || participant == null) {
            System.out.println("Message or Participant is null!");
            return;
        }
        messages.add(new Pair<>(participant, new NormalMessage(messages.size() + 1, message)));
    }

    /**
     * Edit a message that is already present in chat room
     * @param messageId ID of the message
     * @param editedMessage Newly edited message
     */
    public void editMessage(int messageId, String editedMessage) {
        Pair<Participant, Message> chatMessage = getChatMessage(messageId);
        if (chatMessage == null) {
            System.out.println("Cannot find message with id " + messageId);
            return;
        }
        String newMessage = "/e" + editedMessage + "/e";
        int index = messages.indexOf(chatMessage);
        messages.set(index, new Pair<>(chatMessage.first(), new EditedMessage(messageId, newMessage)));
    }

    /**
     * Reply to a message that is already present in chat room
     * @param messageId ID of the message
     * @param repliedMessage Newly replied message
     */
    public void replyMessage(int messageId, String repliedMessage) {
        Pair<Participant, Message> chatMessage = getChatMessage(messageId);
        if (chatMessage == null) {
            System.out.println("Cannot find message with id " + messageId);
            return;
        }
        String newMessage = "/r" + repliedMessage + "/r";
        int index = messages.indexOf(chatMessage);
        messages.set(index, new Pair<>(chatMessage.first(), new RepliedMessage(messageId, newMessage)));
    }

    /**
     * Delete a message that is already present in chat room
     * @param messageId ID of the message
     */
    public void deleteMessage(int messageId) {
        Pair<Participant, Message> chatMessage = getChatMessage(messageId);
        if (chatMessage == null) {
            System.out.println("Cannot find message with id " + messageId + " to delete!");
            return;
        }
        Message message = chatMessage.second();
        message.setIsDeleted(true);
    }

    /**
     * Provides the pair of Participant to Message for the requesting id
     * @param messageId ID of the message
     * @return Pair of Participant to Message object
     */
    private Pair<Participant, Message> getChatMessage(int messageId) {
        Pair<Participant, Message> participantChatMessagePair = messages.stream()
                .filter(chatMessage -> Objects.equals(chatMessage.second().getId(), messageId))
                .findFirst()
                .orElse(null);
        if (participantChatMessagePair == null) {
            System.out.println("Cannot find message with id " + messageId);
            return null;
        }
        return participantChatMessagePair;
    }
}
