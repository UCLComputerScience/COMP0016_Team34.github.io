## Changing the server running the azure service


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

Firstly create the database to be used. Install the db-up extension

```BASH
az extension add --name db-up
```
then run
```BASH
az postgres up --resource-group <resource-group> --location <location> --sku-name B_Gen5_1 --server-name <postgres-server-name> --database-name <name> --admin-user <admin-username> --admin-password <admin-password> --ssl-enforcement Enabled
```
Where
\<resource-group\> is the name of the resource group to be used, if this does not exist it will be created
\<location\> The location of the server, eg uksouth
\<postgres-server-name\> sets the name of the server
\<name\> Sets the name of the databse
\<admin-username\> usename for the admin account
\<admin-password\> passsword for the admin account

```BASH
az webapp up --resource-group <resource-group> --location <location> --name <app-name>
```
Where
\<resource-group\> is the name of the resource group to be used, use the same as for tyhe database 
\<location\> The location of the server, eg uksouth
\<app-name\> The name of the app to be used. The server address will be https://\<app-name\>.azurewebsites.net

You can specify a sku and a plan using the --sku and --plan options however these are optional

To configure the setting for the application run
```BASH
az webapp config appsettings set --settings DBHOST="<postgres-server-name>" DBNAME="<name>" DBUSER="<username>" DBPASS="<password>"
```
Where
\<postgres-server-name\> is the name of the server
\<name\> is the name of the databse
\<admin-username\> usename for the admin account
\<admin-password\> passsword for the admin account
Use the same values that you created in the ```az postgres up``` command step


After this has succeeded in future to redeploy to the server the command 

```BASH
az webapp up
```
can be run by itself. This will use the settings saved from the first call and will deploy to the server. 



### References
https://docs.microsoft.com/en-us/cli/azure/webapp?view=azure-cli-latest#az_webapp_up <br>
https://docs.microsoft.com/en-us/cli/azure/webapp/config?view=azure-cli-latest