Before you start: 
The UI of this client is developed on a Windows machine, and is only guaranteed to work fine with windows.
==========================================================================================
To run the client, go inside the "application" folder, and double click on "launchClient(.vbs)".

To view the handling records, go inside the "application\classes" folder, and open "records.txt"
The records are in the following format:
time|caller's description|receptionist's name|the name of the link sent to the caller|the link's address|the message from the receptionist
==========================================================================================
WARNING:
The developer is NOT provided with a payed google account, and the developer is using his trial account to achieve
the google natural language processing service. This means this service shall expire in May. To continue using it,
follow the tutorial here: https://cloud.google.com/natural-language/docs/quickstarts 
Then replace the old json file with new credential json inside "application\classes\JsonCredential" folder.
Notice that the name of the json should still be "credential.json".