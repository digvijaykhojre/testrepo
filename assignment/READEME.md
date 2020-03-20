**PROJECT BASELINE**
Exposes four REST  APIs each accepting User Json object as below

1. **/getUser**
     Requests for a user. Returns **00/success** if user found else **01/user not found**
- Request:`{
	"userId": 102,
	"userName": "",
	"password": "",
	"status": ""
}`
Response:`{
    "userId": 101,
    "userName": "Active",
    "password": "Digvijay",
    "status": "password@123",
    "errorCode": "00",
    "errorDesc": "Success"
}`


2. **/saveUser**
     Adds user to user table. Returns **00/success** on successful operation else **01/Couldn't Save Failed**
- Request:`{
	"userId": 102,
	"userName": "Ajay",
	"password": "Ajay@123",
	"status": "Inactive"
}`
Response:`{
    "userId": 102,
    "userName": "Ajay",
    "password": "Ajay@123",
    "status": "Inactive",
    "errorCode": "00",
    "errorDesc": "Success"
}`


3. **/updateUser**
     Updates existing user in table. Returns **00/success** on successful operation else **01/User Couln't Be Updated** if user not found
- Request:`{
	"userId": 102,
	"userName": "Ajay",
	"password": "Ajay@123",
	"status": "Active"
}`
Response:`{
    "userId": 102,
    "userName": "Ajay",
    "password": "Ajay@123",
    "status": "Active",
    "errorCode": "00",
    "errorDesc": "Success"
}`


4. **/deleteUser**
     Deletes user from user table. Returns **00/success** on successful operation else **01/ User Not Found** .
Successive getUser request will five user not found error for deleted user
- Request:`{"userId": 102,"userName": "Ajay","password": "Ajay@123" }`
- Response:`{
    "userId": 101,
    "userName": "Ajay",
    "password": "Ajay@123",
    "status": "Active",
    "errorCode": "00",
    "errorDesc": "Success"
}`
