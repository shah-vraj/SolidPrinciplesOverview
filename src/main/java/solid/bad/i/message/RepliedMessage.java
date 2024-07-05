package solid.bad.i.message;

public class RepliedMessage implements Message {

    private final int id;
    private final String message;
    private boolean isDeleted = false;

    public RepliedMessage(int id, String message) {
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
        return isDeleted ? "" : message.replaceAll("/r", "");
    }

    @Override
    public String getEditedMessage() {
        return "THIS IS REPLIED MESSAGE AND DOES NOT SUPPORT EDITED MESSAGE";
    }

    @Override
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
