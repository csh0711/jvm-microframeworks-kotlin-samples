## Create Micronaut app
mn create-app info.novatec.footballmanager --features=mongo-reactive --lang=kotlin

## CURLs
curl http://localhost:8080/footballers/ | json_pp

curl -X POST -H "Content-Type: application/json" -d '{"firstName" : "Leon","lastName" : "Goretzka","position" : "Midfield"}' http://localhost:8080/footballers/

curl -X DELETE  http://localhost:8080/footballers/[id]
