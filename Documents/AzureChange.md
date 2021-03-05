#### Changing the server running the azure service


When in a directory containing the code to be deployed to the server (eg after it has been ```git pull```-ed from a repository)
```
az login
```
This will direct you to a login page - log in with your azure credentials
```BASH
az webapp up --resource-group <resource-group> --location <location> --name <app-name>
```
Where
\<resource-group\> is the name of the resource group to be used, if this does not exist it will be created
\<location\> The location of the server, eg uksouth
\<app-name\> The name of the app to be used. The server address will be https://<app-name>.azurewebsites.net

You can optionally specify a sku and a plan using the --sku and --plan options however these are optional

After this has succeeded in future to redeploy to the server the command 

```BASH
az webapp up
```
can be run by itself. This will use the settings saved from the first call and will deploy to the server. 
