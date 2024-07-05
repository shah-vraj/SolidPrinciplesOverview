package solid.bad.i.message;

public class NormalMessage implements Message {

    private final int id;
    private final String message;
    private boolean isDeleted = false;

    public NormalMessage(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return isDeleted ? "" : message;
    }

    @Override
    public boolean getIsDeleted() {
        return isDeleted;
    }

    @Override
    public String getRepliedMessage() {
        return "THIS IS NORMAL MESSAGE AND DOES NOT SUPPORT REPLIED MESSAGES";
    }

    @Override
    public String getEditedMessage() {
        return "THIS IS NORMAL MESSAGE AND DOES NOT SUPPORT EDITED MESSAGES";
    }

    @Override
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
