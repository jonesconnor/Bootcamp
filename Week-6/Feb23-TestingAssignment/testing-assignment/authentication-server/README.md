# Authentication Server for Keep

## How to use?

- Clone this repository into your local machine.

- First get into the `Authentication-Server` folder using terminal or command prompt

- Use command `npm install` to install the dependencies.

- Start the server using the command `npm start` from within the server folder.

## Credentials for Login

- The username and password to be used is 

  ```
  username: admin
  password: password
  ```

## Routes available currently

### Auth Routes
- POST request

  `http://localhost:3000/auth/v1`
  
  Request Body will have `username` and `password` fields.

  Returns an object with `token` field and its value.




### API Routes
Note: - `All the below requests need the Bearer Token to be added in the Authorization header.`


- GET request to get all the notes

  `http://localhost:3000/api/v1/employee`

- POST request

  `http://localhost:3000/api/v1/employee`

  Body can be
  ```
  {
    "empName": 'Some Title',
    "role": 'Some Text'
  }
  ```
  `id is generated automatically.`

- PUT request

  `http://localhost:3000/api/v1/employee/:id`

  Body can be
  ```
  {
    "empName": 'Some Title',
    "role": 'Some Text'
  }
  ```

