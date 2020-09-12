Installations required:

Java 1.8

Maven 10.15.6

Postgresql 11.5

API Curls:

1) Push log

curl --location --request POST 'BASE_URL/log/push' \
--header 'Content-Type: application/json' \
--data-raw '{"type":"ERROR", "message":"at org.apache.tomcat.dbcp.dbcp.BasicDataSource.getConnection(BasicDataSource.java:880) at com.azurian.lce.usuarios.ConnectionManager.", "time":593853959900, "productKey":"YOUR_SERVER_KEY"}'

2) Get logs by product

curl --location --request GET 'BASE_URL/log/get/PRODUCT_ID' \
--header 'Content-Type: text/plain' \
--data-raw 'YOUR_SERVER_KEY'

3) Get list of products

curl --location --request GET 'BASE_URL/log/list-products' \
--header 'Content-Type: text/plain' \
--data-raw 'YOUR_SERVER_KEY'
