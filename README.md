# Add Product using Spring Boot 🍃
## Overview 
This project is a Spring Boot application hosted locally that implements a specific use case of a larger group project,   
**Cartel: a B2B e-shop** that you can check out by clicking [here](http://ism.dmst.aueb.gr/ismgroup58/browse-products.jsp)   
The application follows a 3-tier architecture with a Presentation, Application and Database Layer (MVC Framework)

## More about the Use Case «Manage Products» 🔎
### Use Case Diagamm 
A small diagramm illustrating the limits of the specific the application   

<img width="374" alt="ManageProducts" src="https://github.com/user-attachments/assets/d7b2ee8e-efeb-41ac-b635-0dc99428c832" />

### Summary:   
The user sees their products, can edit, delete and add products, as well as see the ratings of each product by customers   

### Basic Flows: 
| Actions | Data |
| :--- | :--- |
| 1.  The user logs into the aplication. | username, password |
| 2.  The user clicks the _Add a new product_ button. | user |
| 3.  A form opens with the information needed to fill in for the new product. | - |
| 4.  After completing, the user clicks _Add Product_ and is redirected to the previous page with a message confirming the addition was successfull. | category, name, price, stock, description |
| 5.  The user can select the _delete_ button (🗑️) to delete a product and a message appears indicationg the deletion was successful. | productID |
| 6.  The user can select the _edit_ button (✏️) to edit a product's data. | productID |
| 7.  A form opens with the existing information for the product and the user can change whatever they want. | productID |
| 8.  The user clicks _Done_ and is redirected to the previous page with a message of success of the addition. | productID, name, price, stock, description |
| 9.  The user can click on a product to see all the reviews for that product. | productID |

### Alternative Flows:
| Actions | Data |
| :--- | :--- |
| - In step 1 the user enters incorrect data in attemting to connect (either incorrect password or non-existent user, a corresponding message will be displayed) | username, password |
| - In steps 4 or 8 the user doesn't choose to proceed and returns to the previous page by clicking _Cancel_ | user |
| - In steps 4, 5 or 8 something goes wrong and the addition, editing or deletion cannot be done, so the user is redirected to their products' page with a failure message | user |

## Setup 🛠️ 
### Prerequisites
* [Maven](https://maven.apache.org/download.cgi)
* Any text editor (e.g. Notepad, Visual Studio Code...)

### Step by step guide 👣
1. Clone the repository:
```sh
git clone https://github.com/AnastasiaLag/addProduct.git
```

2. Open [application.properties](src/main/resources/application.properties) file in a text editor and fill in the database credentials accordingly.   
The file should look something like this:
```sh
spring.application.name=addProduct
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=********
spring.datasource.username==********
spring.datasource.password==********
```

> [!TIP]
> The file is inside the project's resources   
> Path: ```/addProduct/src/main/resources```

3. Navigate inside the project:
```sh
cd addProduct
```

4. Build the project:
```sh
mvn clean install
```
5. Run the application:
```sh
java -jar target/addProduct-0.0.1-SNAPSHOT.jar
```
6. Open [http://localhost:8080/login](http://localhost:8080/login) and start experimenting or follow the [Test Cases](https://github.com/AnastasiaLag/addProduct?tab=readme-ov-file#test-cases-) below 😉

> [!Note]
> Many functions don't work because they don't apply to the specific Use Case.   
> You will definetly find buttons that don't do anything.

## Test Cases 📝 
| | Input Data | Expected Results - Responses |
| --- | --- | :---: |
| 1 | Click ***Register*** and fill in the form: <br> Company name = ```New Company``` <br> Email = ```newcompany@test.com``` <br> Company SSN = ```123456789``` <br> Username = ```newcompany``` <br> Password = ```abc``` <br> The same in the next field for password confirmation, _don't_ check the checkbox and click ***Register*** | Display of error messages under the fields that don't meet the requirements: <br> ❗Password must be at least 8 characters long <br> ❗Confirmation password does not match your password <br> ❗You must agree to terms and conditions <br> These fields become empty. |
| 2 | Now fill in: <br> Password = ```abcd1234``` <br> The same in the next field for password confirmation, check the checkbox and click ***Register*** | Display of error message because the SSN already exists in the database: <br> 🟥 Sorry, username, email or ssn already registered |
| 3 | Finally change the SSN to ```444555666``` and with everything else the same click again ***Register*** | Redirection to /my-products where you will see a message that **you currently have no products** |
| 4 | Click the ***Add a new Product*** button at the bottom of the page. <br> Then select ***Bathroom*** from the drop-down list and fill in: <br> Name = ```New Product``` <br> Price = ```12.50``` <br> Stock = ```80``` <br> Product Details = ```Really good product. Very satisfied with our purchase!``` <br> Finally click ***Add Product***. | Redirection to /addProductForm and then again to /my-products with a success message: <br> 🟩 Product added successfully! <br> unless something goes wrong with the database: <br> 🟥 Something went wrong! Failed to add product! 
| 5 | Click the little **pencil** ✏️ on the picture of the product you just created. <br> Then change the price to ```20.00``` and click ***Done***. | Redirection to /editProduct and then again to /my-products with a success message: <br> 🟩 Product edited successfully! |
| 6 | Click the little **garbage bin** 🗑️ on the picture of the product you created. | Display of deletion success message: <br> 🟩 Product deleted successfully! |
| 7 | Click ***Log Out*** from the navbar | Redirection to /login after desplaying a success message: <br> 🟩 Logout Successful! |
| 8 | Type ```http://localhost:8080/my-products``` as a URL | Redirection to /login with an error message display: <br> 🟥 You have to login to view your products! |
| 9 | In the login form fill in: <br> Username = ```luxeloom``` <br> Password = ```1112``` <br> and click ***Login*** | Error message display: <br> 🟥 Wrong username or password |
| 10 | Now fill in: <br> Username = ```luxeloom``` <br> Password = ```1111``` <br> and click ***Login*** again | Connection is successfull and you are redirected in luxeloom's product page |

## Issues 🚩
* View Product Reviews
* Add Image feature
* Mobile version styling
* Register controller styling
* Error handling page
* Refreshing brings back closed messages

## ChatGPT 🤖
* [Conversation 1](https://chatgpt.com/share/67b6748b-10fc-8013-bf58-a87597d96ca2)
* [Conversation 2](https://chatgpt.com/share/67b674d9-9e38-8013-b341-7ec041e08711)
* [Conversation 3](https://chatgpt.com/share/67b674e6-a8ac-8013-aaee-aaf738b31072)

## License ⚖️
This project is licensed under the [Apache-2.0 License](https://github.com/AnastasiaLag/addProduct?tab=Apache-2.0-1-ov-file).
