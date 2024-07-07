package solid.good.d;

import java.io.File;
import java.util.List;

public class CustomIdeApplication {

    public static void main(String[] args) {
        String email = "customemail@gmail.com";
        String password = "customPassword123";
        RemoteGitProvider remoteGitProvider = new GitHubManager(email, password);
        GitManager gitManager = new GitManager(remoteGitProvider);

        gitManager.addRemote("https://www.github.com/user/abcdRepository");
        gitManager.addRemote("https://www.github.com/user/deleteLater");

        String loginUserBranch = "feature/login_user";
        gitManager.createBranch(loginUserBranch);
        gitManager.checkoutBranch(loginUserBranch);
        gitManager.createBranch("test");
        gitManager.removeBranch("test");
        System.out.println("Current branch: " + gitManager.getCurrentBranch());

        gitManager.shareProject("CustomProject");
        gitManager.stageChanges(List.of(new File("Login.java"), new File("CredentialValidator.java")));
        gitManager.commitFiles();
        gitManager.pushChanges();

        gitManager.fetchChanges();
        gitManager.updateChanges();

        gitManager.deleteRepository("https://www.github.com/user/deleteLater");
    }
}
