# Add Product using Spring Boot 🍃
## Overview 💡
This project is a Spring Boot application hosted locally that implements a specific 
use case of a larger group project: **Cartel: a B2B e-shop**
## More about the Use Case «Manage Products» 🧐
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
| 9.  The user can click ON  product to see all the reviews for that product. | productID |

### Alternative Flows:
| Actions | Data |
| :--- | :--- |
| - In step 1 the user enters incorrect data in attemting to connect (either incorrect password or non-existent user, a corresponding message will be displayed) | username, password |
| - In steps 4 or 8 the user doesn't choose to proceed and returns to the previous page by clicking _Cancel_ | user |
| - In steps 4, 5 or 8 something goes wrong and the addition, editing or deletion cannot be done, so the user is redirected to their products' page with a failure message | user |

## Setup 🛠️ 

## Test Cases 📝 
| | Input Data | Expected Results - Responses |
| --- | --- | --- |
| 1 | In the login form fill in: <br> Username = ```luxeloom``` <br> Password = ```1112``` | Error message display: <br> 🟥 Wrong username or password |
| 2 | Now fill in: <br> Username = ```luxeloom``` <br> Password = ```1111``` | Connection is successfull and you are redirected in luxeloom's product page |
| 3 | Click the ***Add a new Product*** button at the bottom of the page. <br> Then select ***Bathroom*** from the drop-down list and fill in: <br> Name = ```New Product``` <br> Price = ```12.50``` <br> Stock = ```80``` <br> Product Details = ```Really good product. Very satisfied with our purchase!``` <br> Finally click ***Add Product***. | Redirection to /addProductForm and then again to /my-products with a success message: <br> 🟩 Product added successfully! <br> unless something goes wrong with the database: <br> 🟥 Something went wrong! Failed to add product! 
| 4 | Click the little **pencil** ✏️ on the picture of the product you just created. <br> Then change the price to ```20.00``` and click ***Done***. | Redirection to /editProduct and then again to /my-products with a success message: <br> 🟩 Product edited successfully! |
| 5 | Click the little **garbage bin** 🗑️ on the picture of the product you created. | Display of deletion success message: <br> 🟩 Product deleted successfully! |
