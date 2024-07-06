package solid.good.i.message;

public class EditedMessage extends BaseMessage implements Edited {

    public EditedMessage(int id, String message) {
        super(id, message);
    }

    @Override
    public String getEditedMessage() {
        return isDeleted ? "" : message.replaceAll("/e", "");
    }
}
