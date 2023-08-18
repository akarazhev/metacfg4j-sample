curl --insecure -H "Content-Type: application/json" -XPUT "https://localhost:8000/api/metacfg/config" -d '[{"name":"New Config","description":"Description","attributes":{"key_1":"value-1","key_4":"value_4"},"properties":[{"name":"Value","caption":"Caption","description":"Description","attributes":{"key_1":"value-1","key_4":"value_4"},"type":"LONG","value":"1000","updated":100,"properties":[]}]}]'

curl --insecure -H "Content-Type: application/json" -XGET "https://localhost:8000/api/metacfg/config_names"

curl --insecure -H "Content-Type: application/json" -XGET "https://localhost:8000/api/metacfg/config?names=WyJOZXcgQ29uZmlnIl0="

curl --insecure -H "Content-Type: application/json" -XDELETE "https://localhost:8000/api/metacfg/config?names=WyJOZXcgQ29uZmlnIl0="




