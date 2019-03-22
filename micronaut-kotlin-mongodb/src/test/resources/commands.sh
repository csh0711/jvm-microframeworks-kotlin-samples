## Create Micronaut app
mn create-app info.novatec.footballmanager --features=mongo-reactive --lang=kotlin

## CURLs
curl http://localhost:8080/footballmanager/ | json_pp

curl -X POST -H "Content-Type: application/json" -d '{"firstName" : "Timo","lastName" : "Baumgartl","position" : "Defense"}' http://localhost:8080/footballmanager/

curl -X DELETE  http://localhost:8080/footballmanager/5c66d85c4d34bde723de0641

