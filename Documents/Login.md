To login send a POST request to /login/. The POST request will be sent with two parameters. One will be "username" and the other "password"

using curl
```BASH
curl -X POST https://team34-comp0016-2020.azurewebsites.net/login/ -F "username=<Username>" -F "password=<Password>"
```