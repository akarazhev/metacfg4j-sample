# metacfg4j-sample

The `metacfg4j-sample` project is a demo how to use the `metacfg4j` project.

The `metacfg4j` project (that stands for the `meta configuration for java`) is a library that can be used as the solution 
by creating a business abstraction or may extend an existed implementation to provide such software solutions as: 
various configuration (application, user's and etc.), CRUD services, DSL, MVP.

## Use case

Create a new config:
```shell
curl --insecure -H "Content-Type: application/json" -XPUT "https://localhost:8000/api/metacfg/config" -d '[{"name":"New Config","description":"Description","attributes":{"key_1":"value-1","key_4":"value_4"},"properties":[{"name":"Value","caption":"Caption","description":"Description","attributes":{"key_1":"value-1","key_4":"value_4"},"type":"LONG","value":"1000","updated":100,"properties":[]}]}]'
```

Get a list of config names:
```shell
curl --insecure -H "Content-Type: application/json" -XGET "https://localhost:8000/api/metacfg/config_names"
```

Get configs by names:
```shell
curl --insecure -H "Content-Type: application/json" -XGET "https://localhost:8000/api/metacfg/config?names=WyJOZXcgQ29uZmlnIl0="
```

Delete configs by names:
```shell
curl --insecure -H "Content-Type: application/json" -XDELETE "https://localhost:8000/api/metacfg/config?names=WyJOZXcgQ29uZmlnIl0="
```

## Build Requirements

&#8658; Docker <br/>
&#8658; Java 17+ <br/>
&#8658; Gradle <br/>