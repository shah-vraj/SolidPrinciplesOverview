package solid.bad.i.message;

public interface Message {

    /**
     * Provides ID of the message
     * @return Integer representing ID of the message
     */
    int getId();

    /**
     * Provides the actual message
     * @return String representing the message
     */
    String getMessage();

    /**
     * Provides if the message is deleted or not
     * @return True if message is deleted, otherwise false
     */
    boolean getIsDeleted();

    /**
     * Provides the replied string of the message
     * @return String representing replied message
     */
    String getRepliedMessage();

    /**
     * Provides the edited string of the message
     * @return String representing edited message
     */
    String getEditedMessage();

    /**
     * Set if the message is deleted or not
     * @param isDeleted True if message is deleted, otherwise false
     */
    void setIsDeleted(boolean isDeleted);
}
