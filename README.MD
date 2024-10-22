# SOLID principles understanding
This repository involves developing 5 different JAVA programs with intentionally violating one
of the SOLID principles and fixing those violations.

## Applications breakdown based on each principle of SOLID

### Single Responsibility Principle
**Online Learning Platform** 

| Bad approach                                                                                               | Good approach                                                                                      |
|:-----------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------|
| Room (class room) handling multiple responsibilities including its own, Teacher's and Student's operations | Room handling only its own responsibilities, Teacher handling its own and Student handling its own |

### Open-Closed Principle
**Event management System**

| Bad approach                                                                | Good approach                                                                                                 |
|:----------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------|
| Having switch to check type of events and then handling event in main class | Create separate interface for event type which provides all necessary operations an event type has to perform |

### Liskov Substitution Principle
**User Profile Management**

| Bad approach                                                                                                                                                                   | Good approach                                                                                                                                                             |
|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| User tries to upload image via AmazonImageUploader and FirebaseImageUploader classes and expects a URL in return but amazon provides nothing and firebase provides string url. | There should be a separate interface as UrlProvidingImageUploader extended by all image uploader classes which will be then fed to function/class where image is uploaded |

### Interface Segregation Principle
**Chat application**

| Bad approach                                                                                                                                                                                 | Good approach                                                                                                                                      |
|:---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------------------------------------------------------------------------------------------------------------------------------------|
| Creating a single interface message which includes all the information about the message, such as id, message, editMessage, repliedMessage, etc. Basically every single detail of a message. | Create separate interfaces for different types of message. For example have a separate interface for Edited, Replied, etc. and extend as per need. |

### Dependency Inversion Principle
**Custom IDE application**

| Bad approach                                                                | Good approach                                                                           |
|:----------------------------------------------------------------------------|:----------------------------------------------------------------------------------------|
| GitManager having direct dependency on GitHubManager as remote Git provider | Having separate interface for RemoteGitProvider and injecting it into GitManager class. |
