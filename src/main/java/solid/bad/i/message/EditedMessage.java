package solid.bad.i.message;

public class EditedMessage implements Message {

    private final int id;
    private final String message;
    private boolean isDeleted = false;

    public EditedMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean getIsDeleted() {
        return isDeleted;
    }

    @Override
    public String getRepliedMessage() {
        return "THIS IS EDITED MESSAGE AND DOES NOT SUPPORT REPLIED MESSAGES";
    }

    @Override
    public String getEditedMessage() {
        return isDeleted ? "" : message.replaceAll("/e", "");
    }

    @Override
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
