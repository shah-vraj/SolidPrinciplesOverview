package solid.bad.d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitHubManager {

    private final String email;
    private final String password;
    private final List<String> repositories = new ArrayList<>();

    public GitHubManager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Log in the user with the provided credentials
     */
    public void loginUser() {
        if (email == null || email.isBlank()) {
            System.out.println("Email cannot be null or empty");
            return;
        }
        if (password == null || password.isBlank()) {
            System.out.println("Password cannot be null or empty");
            return;
        }
        System.out.println("Logged in!");
    }

    /**
     * Checks if the provided remote URL exist or not
     * @param remoteUrl URL of the repository
     * @return True if remote repository exists, otherwise false
     */
    public boolean doesRemoteExists(String remoteUrl) {
        if (remoteUrl == null || remoteUrl.isBlank()) {
            System.out.println("Invalid url!");
        }
        return repositories.contains(remoteUrl);
    }

    /**
     * Create a new repository on remote
     * @param name Name of the repository
     * @return Boolean representing the URL of the remote repository
     */
    public String createNewRepository(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be null or empty");
            return null;
        }
        repositories.add(name);
        System.out.println("Created new repository: " + name);
        return "custom/repository/url/" + name;
    }

    /**
     * Delete a repository from the remote
     * @param name Name of the repository
     */
    public void deleteRepository(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be null or empty");
            return;
        }
        if (doesRemoteExists(name)) {
            System.out.println("Repository does not exists!");
            return;
        }
        repositories.remove(name);
    }

    /**
     * Push provided changes to remote repository
     * @param changes List of file to push changes for
     */
    public void pushChanges(List<File> changes) {
        System.out.println("Pushed changes (" + changes.size() + " files)");
    }

    /**
     * Fetch changes from the remote repository
     * @return List of all changed files
     */
    public List<File> fetchChanges() {
        System.out.println("Fetched changes");
        return List.of();
    }
}
