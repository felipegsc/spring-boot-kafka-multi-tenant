##Preconditions

It's necessary to edit your `/etc/hosts` file in order to make the hosts `kafka1`, `kafka2`, `kafka3`, `kafka4` and `kafka5` point to `localhost`.

##Steps to run the project

`docker compose up -d`

`./gradlew bootRun`
```shell
curl --location --request POST 'http://localhost:8080/topics/messages/_batch-create' \
--header 'Content-Type: application/json' \
--data-raw '{
    "fromTenant": 1,
    "toTenant": 100,
    "count": 10
}'
```