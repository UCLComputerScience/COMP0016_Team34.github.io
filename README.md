# Q-Vu
### Queue Management System

> *"A full webservice that enables any GP practice or NHS clinic with a laptop and a webcam to generate a virtualised call queue with real receptionists engaging from their practice or at home, like waiting to be seen in an actual practice. The service will be activated by a twillio SMS message while you are in a waiting call to activate a link. It will present a visualisation of the patient queue and an estimate as to how long the patient will wait to be seen, as well as the ability for patients to provide a quick summary of their query for receptionists to answer them faster. It may also provide possible FHIR interaction for simulating patient records access for even greater efficiency and patient attention."*

##### Receptionist Client

Before you start: 
The UI of this client is developed on a Windows machine, and is only guaranteed to work fine with windows.

To run the client, go inside the "application" folder, and double click on "launchClient(.vbs)".

To view the handling records, go inside the "application\classes" folder, and open "records.txt"
The records are in the following format:
time|caller's description|receptionist's name|the name of the link sent to the caller

The developer is not provided with a payed google account, and the developer is using his trial account to achieve
the google natural language processing service. This means this service shall expire in May. To continue using it,
follow the tutorial here: https://cloud.google.com/natural-language/docs/quickstarts 
Then replace the old json file with new credential json inside "application\classes\JsonCredential" folder.
Notice that the name of the json should still be "credential.json".

##### Azure server

Firstly install the azure CLI tools
On a mac:
```BASH
brew update && brew install azure-cli
```
On Linux:
```BASH
curl -sL https://aka.ms/InstallAzureCLIDeb | sudo bash
```
On Windows follow this url: https://docs.microsoft.com/en-us/cli/azure/install-azure-cli-windows?tabs=azure-cli

To deploy the Django code to an Azure Server

When in a directory containing the code to be deployed to the server (eg after it has been ```git pull```-ed from a repository). Firstly you will need to log in.
```BASH
az login
```
This will direct you to a login page - log in with your azure credentials. When you have logged in return to the terminal window


Set the subscription to be used
```BASH
az account set --subscription <sub>
```
Where
\<sub\> is the subscription id found in the azure portal


```BASH
az webapp up --resource-group <resource-group> --location <location> --name <app-name>
```
Where
\<resource-group\> is the name of the resource group to be used, if this does not exist it will be created
\<location\> The location of the server, eg uksouth
\<app-name\> The name of the app to be used. The server address will be https://\<app-name\>.azurewebsites.net

You can specify a sku and a plan using the --sku and --plan options however these are optional

After this has succeeded in future to redeploy to the server the command 

```BASH
az webapp up
```
can be run by itself. This will use the settings saved from the first call and will deploy to the server. 



### Useful links
https://docs.microsoft.com/en-us/cli/azure/webapp?view=azure-cli-latest#az_webapp_up <br>
https://docs.microsoft.com/en-us/cli/azure/webapp/config?view=azure-cli-latest

Q-Vu Queue Manager
Copyright (C) 2021 Joshua Mukherjee, Tansheng Geng, Shaheer Ahmed