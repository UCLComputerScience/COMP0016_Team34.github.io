To login send a POST request to /login/. The POST request will be sent with two parameters. One will be "username" and the other "password"
if the user is verified aa random string will be sent back. When a restricted url is accessed this long string will be sent in a POST request and if this is valid the data will be sent. if not a 403 response will be sent. 

using curl
```BASH
curl -X POST https://team34-comp0016-2020.azurewebsites.net/login/ -F "username=<Username>" -F "password=<Password>"
```