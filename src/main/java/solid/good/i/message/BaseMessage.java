package solid.good.i.message;

public abstract class BaseMessage implements Message {

    private final int id;
    protected final String message;
    protected boolean isDeleted = false;

    public BaseMessage(int id, String message) {
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
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}
