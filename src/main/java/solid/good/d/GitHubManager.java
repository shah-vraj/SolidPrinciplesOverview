package solid.good.d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitHubManager implements RemoteGitProvider {

    private final String email;
    private final String password;
    private final List<String> repositories = new ArrayList<>();

    public GitHubManager(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
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

    @Override
    public boolean doesRemoteExists(String remoteUrl) {
        if (remoteUrl == null || remoteUrl.isBlank()) {
            System.out.println("Invalid url!");
        }
        return repositories.contains(remoteUrl);
    }

    @Override
    public String createNewRepository(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("Name cannot be null or empty");
            return null;
        }
        repositories.add(name);
        System.out.println("Created new repository: " + name);
        return "custom/repository/url/" + name;
    }

    @Override
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

    @Override
    public void pushChanges(List<File> changes) {
        System.out.println("Pushed changes (" + changes.size() + " files)");
    }

    @Override
    public List<File> fetchChanges() {
        System.out.println("Fetched changes");
        return List.of();
    }
}
