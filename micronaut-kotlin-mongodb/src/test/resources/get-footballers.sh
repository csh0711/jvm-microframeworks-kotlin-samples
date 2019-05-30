#!/usr/bin/env bash
for ((i=1;i<=1000;i++)); do
    curl http://localhost:8080/footballers/ | json_pp;
    sleep .25
    curl http://localhost:8080/footballers/5cc2d46f8735870692375404 | json_pp;
    sleep .1
    curl http://localhost:8080/footballers/5cc2d4a08735870692375405 | json_pp;
    sleep .1
    curl http://localhost:8080/footballers/5cc2d4c98735870692375406 | json_pp;
done
