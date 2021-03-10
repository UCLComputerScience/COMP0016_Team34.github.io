To run the client, go inside the target folder and click on "Project.exe". 
Running the client does not require any further actions, but it must be run in the target folder.
If you want to move the executable file to other directories, please move the whole target folder.
----------------------------------------------------------------------------------------------------------------------------------
To get the records for caller handling, go to the target folder,then go to the classes folder. All the records are recorded in the "record.txt".
----------------------------------------------------------------------------------------------------------------------------------
Please notice that the current client has disabled the google nlp processing feature.
Without this feature, the client does not set the keywords in callers descriptions in bold.

This is because the developer is NOT provided with a payed google cloud account, and the developer is not responsible for paying for this service. However the developer has achieved this feature in development using the developer's own google account.

To enable this feature, follow the steps below:
1. visit https://cloud.google.com/natural-language/docs/quickstarts
follow the tutorial and set a credential.
2. go to src\main\java\GUI folder, and make following amendments to the code:
    2.1 go to the Pages folder, open Mainpage.java ,uncomment line 7, uncomment line 421 to 441.
    2.2 go to the Widges folder, open Caller.java, comment line 135, uncomment line 136.
3. go back to the "ReceptionistClient" folder, open cmd in this folder, enter
mvn clean
    Then enter
mvn package
4. copy the jre folder and paste it into target folder
5. go to target folder, open a cmd, and enter:
java -cp ReceptionistClient-1.0-SNAPSHOT.jar GUI.App
This runs the client as a jar file
6.pack the jar into an exe with any tool 