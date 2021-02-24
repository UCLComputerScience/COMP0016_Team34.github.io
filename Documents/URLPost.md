Sending a URl from the receptionist to a caller

The url and id of the caller will be send using a POST request sent with two parameters one named "id" followed by the id of the caller and then "url" followed by the url to send them.

using curl:
```BASH
curl -X post https://team34-comp0016-2020.azurewebsites.net/addURLID/ -F "id=320493688610939527597112467216073650780" -F "url=google.com" -F "description=google|
```