Keycloak Sample
===

```
export access_token=$(\
    curl -X POST http://localhost:8180/auth/realms/master/protocol/openid-connect/token \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=pedro&password=123&grant_type=password&client_id=quarkus-app' | jq --raw-output '.access_token' \
 )
 
curl -v -X PUT -H "Content-Type: application/json" -H "Authorization: Bearer "$access_token -d '{"nome":"Melhor Evento", "local":"Turco Gordo"}' http://localhost:8080/evento 
 
```