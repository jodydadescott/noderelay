# noderelay

## Overview
This API holds arbitray data for all you need is a secret of your own making. You insert an object or objects into the database using a secret. To get objects you use a secret. Hence you should make up a random secret and then store it. Objects are not permanent and will be ejected after a period of time if they are not installed again.

## Usage example
Create (POST Only)
```
curl -d '{"secret": "secret", "userdata": "userdata", "namespace":"namespace", "pod":"pod"}' $URL/put
```

Search by Pattern (GET or POST) using attributes to filter
```
curl -d '{"secret": "secret", "userdata": "userdata", "namespace":"namespace", "pod":"pod"}' $URL/search
```

Search by Pattern (GET or POST) using attributes to filter and negate. Negate by prepending negate to the attribute.
```
curl -d '{"secret": "secret", "userdata": "userdata", "namespace":"namespace", "negatepod":"negatepod"}' $URL/search
```

Get All (GET or POST)
```
curl -d '{"secret": "secret"}' $URL/search
curl -d '{"secret": "secret"}' -X POST $URL/search
```

Delete (POST Only)
```
curl -d '{"secret": "secret", "userdata": "userdata"}' -X POST $URL/delete
```

## Node Object
userdata, metadata, namespace, pod

## Node Object
