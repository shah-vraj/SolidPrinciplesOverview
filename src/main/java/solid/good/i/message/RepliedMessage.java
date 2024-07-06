package solid.good.i.message;

public class RepliedMessage extends BaseMessage implements Replied {

    public RepliedMessage(int id, String message) {
        super(id, message);
    }

    @Override
    public String getRepliedMessage() {
        return isDeleted ? "" : message.replaceAll("/r", "");
    }
}
