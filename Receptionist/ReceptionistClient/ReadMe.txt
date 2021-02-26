Before running the program, use the credential to enable google api,
Replace the (----) with your own directory, and execute:
IN WINDOWS:
set GOOGLE_APPLICATION_CREDENTIALS=(----)\COMP0016_Team34.github.io\Receptionist\ReceptionistClient\src\main\resources\jsonCredential\credential.json
IN MAC/LINUX:
export GOOGLE_APPLICATION_CREDENTIALS="(----)\COMP0016_Team34.github.io\Receptionist\ReceptionistClient\src\main\resources\jsonCredential\credential.json"

To generate the jar file and other resources, use
mvn package

To run the jar file, go inside the target folder, and execute    
java -cp ReceptionistClient-1.0-SNAPSHOT.jar GUI.App