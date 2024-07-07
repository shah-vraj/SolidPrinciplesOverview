package solid.bad.d;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GitManager {

    private final GitHubManager gitHubManager;
    private final List<String> remotes = new ArrayList<>();
    private List<File> stagedFiles = new ArrayList<>();
    private List<File> committedFiles = new ArrayList<>();
    private List<File> bufferedFiles = new ArrayList<>();
    private final List<String> branches = new ArrayList<>(List.of("main"));
    private String currentBranch = "main";

    public GitManager(String email, String password) {
        gitHubManager = new GitHubManager(email, password);
        gitHubManager.loginUser();
    }

    /**
     * Add remote to local repository
     * @param remoteUrl URL of the remote repository
     */
    public void addRemote(String remoteUrl) {
        if (gitHubManager.doesRemoteExists(remoteUrl)) {
            System.out.println("Remote " + remoteUrl + " already exist");
            return;
        }
        if (remotes.contains(remoteUrl)) {
            System.out.println("Remote \"" + remoteUrl + "\" already added");
            return;
        }
        remotes.add(remoteUrl);
    }

    /**
     * Remove remote from local repository
     * @param remoteUrl URL of the remote repository
     */
    public void removeRemote(String remoteUrl) {
        if (!remotes.contains(remoteUrl)) {
            System.out.println("Remote \"" + remoteUrl + "\" is not added");
            return;
        }
        remotes.remove(remoteUrl);
    }

    /**
     * Create a branch in local repository
     * @param branchName Name of the branch
     */
    public void createBranch(String branchName) {
        if (branchName == null || branchName.isBlank()) {
            System.out.println("Branch name cannot be empty");
            return;
        }
        if (branches.contains(branchName)) {
            System.out.println("Branch \"" + branchName + "\" already exists");
            return;
        }
        branches.add(branchName);
    }

    /**
     * Remove a branch from the local repository
     * @param branchName Name of the branch
     */
    public void removeBranch(String branchName) {
        if (branchName == null || branchName.isBlank()) {
            System.out.println("Branch name cannot be empty");
            return;
        }
        if (!branches.contains(branchName)) {
            System.out.println("Branch \"" + branchName + "\" is not added");
            return;
        }
        branches.remove(branchName);
    }

    /**
     * Checkout a branch from the local repository
     * @param branchName Name of the branch
     */
    public void checkoutBranch(String branchName) {
        if (branchName == null || branchName.isBlank()) {
            System.out.println("Branch name cannot be empty");
            return;
        }
        if (!branches.contains(branchName)) {
            System.out.println("Branch \"" + branchName + "\" is not added");
            return;
        }
        currentBranch = branchName;
    }

    /**
     * Share local repository as project on remote repository
     * @param projectName Name of the project
     */
    public void shareProject(String projectName) {
        String newRepositoryUrl = gitHubManager.createNewRepository(projectName);
        addRemote(newRepositoryUrl);
    }

    /**
     * Delete a remote repository
     * @param repositoryName Name of the project
     */
    public void deleteRepository(String repositoryName) {
        gitHubManager.deleteRepository(repositoryName);
        removeRemote(repositoryName);
    }

    /**
     * Stage all the local changes
     * @param files List of all files that needs to be staged
     */
    public void stageChanges(List<File> files) {
        for (File file : files) {
            // Remove staged file if present
            stagedFiles.stream()
                    .filter(stagedFile -> stagedFile.getName().equals(file.getName()))
                    .findFirst()
                    .ifPresent(value -> stagedFiles.remove(value));
            stagedFiles.add(file);
            System.out.println("File \"" + file.getName() + "\" staged");
        }
    }

    /**
     * Fetch changes from the remote repository
     */
    public void fetchChanges() {
        bufferedFiles = new ArrayList<>(gitHubManager.fetchChanges());
    }

    /**
     * Update changes from remote repository to local repository
     */
    public void updateChanges() {
        if (!stagedFiles.isEmpty()) {
            System.out.println("Commit staged changes and then try to update");
            return;
        }
        if (bufferedFiles.isEmpty()) {
            fetchChanges();
        }
        stagedFiles = new ArrayList<>(bufferedFiles);
        System.out.println("Files updated");
    }

    /**
     * Commit all staged files
     */
    public void commitFiles() {
        if (stagedFiles.isEmpty()) {
            System.out.println("No files to commit");
            return;
        }
        committedFiles = new ArrayList<>(stagedFiles);
        stagedFiles.clear();
    }

    /**
     * Push changes from local repository to remote repository
     */
    public void pushChanges() {
        if (committedFiles.isEmpty()) {
            System.out.println("No files committed to push");
            return;
        }
        gitHubManager.pushChanges(committedFiles);
    }

    /**
     * Provides the name of the current checked out branch
     * @return String representing name of the checked out branch
     */
    public String getCurrentBranch() {
        return currentBranch;
    }
}
