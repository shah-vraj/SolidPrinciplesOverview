package solid.good.d;

import java.io.File;
import java.util.List;

public interface RemoteGitProvider {
    /**
     * Log in the user with the provided credentials
     */
    void loginUser();

    /**
     * Checks if the provided remote URL exist or not
     * @param remoteUrl URL of the repository
     * @return True if remote repository exists, otherwise false
     */
    boolean doesRemoteExists(String remoteUrl);

    /**
     * Create a new repository on remote
     * @param name Name of the repository
     * @return Boolean representing the URL of the remote repository
     */
    String createNewRepository(String name);

    /**
     * Delete a repository from the remote
     * @param name Name of the repository
     */
    void deleteRepository(String name);

    /**
     * Push provided changes to remote repository
     * @param changes List of file to push changes for
     */
    void pushChanges(List<File> changes);

    /**
     * Fetch changes from the remote repository
     * @return List of all changed files
     */
    List<File> fetchChanges();
}
