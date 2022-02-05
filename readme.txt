I have implemented a Spring boot application. The jar file can be found on \AsaitecTest\julian.test\out

To execute the application you only need to import to your IDE to import a Maven project and just execute the application

There is a folder named "dev-utils" with a postman collection with the requests to use application according the requierements

 -  localhost:8080/upload-products: file must be sent with form-data as body using in the key the txt file with the same structure than was showed in the test requirements.
	Used to store product data
 -  localhost:8080/upload-transactions: file must be sent with form-data as body using in the key the txt file with the same structure than was showed in the test requirements
	Used to generate invoice data according stored products.

Examples (curl):
	
Upload Products to Data Base:

curl -X POST \
  http://localhost:8080/upload-products \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: 503d0142-6c7f-fb2b-4cbf-62bb3042add7' \
  -F file=@file.txt


Generate Invoice from product order:

curl -X POST \
  http://localhost:8080/upload-transactions \
  -H 'cache-control: no-cache' \
  -H 'content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW' \
  -H 'postman-token: d84772a7-6661-32b5-eded-13713acdf101' \
  -F file=@file.txt