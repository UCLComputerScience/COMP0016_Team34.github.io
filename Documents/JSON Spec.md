The Data will be sent as a list of entries with "callers" being the top level  

Within this will be a list of caller data - represented as the changes since the last time the data was requested

```json
{
   "callers":[
      {
         "id":"100",
       	 "name":"John Doe",
         "dob":"01//01/2000",
      },
      {
         "id":"473",
         "description":"ive hurt my leg"
      },
      {
         "id":"738",
         "active":false
      }
   ]
}
```

The fields possible are

```jso
"name": the name of the caller
"dob": the callers Date of Borth
"description": the description of the callers issue
"id": the unique id of the caller
"active": true if the caller is still connected, false otherwise (assumed true if not stated)
```

 When a caller is created their data is send as in the first element of the above example - send the created id, the name and the dob. When a caller is updated the second element's format is used - send only the ID and the fields to be changed. When a user leaves the queue their id is send as well as setting their active field to false

