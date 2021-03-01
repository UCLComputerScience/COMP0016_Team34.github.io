To login send a POST request to /login/. The POST request will be sent with two parameters. One will be "username" and the other "password"
if the user is verified aa random string will be sent back. When a restricted url is accessed this long string will be sent in a POST request and if this is valid the data will be sent. if not a 403 response will be sent. 

The cookies sent from the server must be stored and then sent back to the server to ensure the server knows which session to use. These cookies shall be sent with all future requests to sensitive data in these urls:
* getJSON/
* staticInfo/
* clear_data/
* get_All_JSON/
* get_changes/
* add_url/

using curl
```BASH
curl -X POST https://team34-comp0016-2020.azurewebsites.net/login/ -F "username=<Username>" -F "password=<Password>" -c cookies.txt
```
Then we can test the login
```BASH
curl -X GET https://team34-comp0016-2020.azurewebsites.net/loginTest/ -b cookies.txt         
```