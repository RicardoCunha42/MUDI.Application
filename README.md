# MUDI.Application-API-Rest
API Rest java project made at Alura's Spring MVC courses. It's an application that register products and offers on them in a databank, and follows the MVC pattern. It's view layer is made with thymeleaf as well as Vue.js. 

#New features
After the course, I expanded the original application with the new features: 
- Register feature that allows the creation of users through a form in the browser.
- Offers on orders are now stored on the databank with a order fk linking them
- The user accessing the "My orders" page can now click on a "Received offers" button at the end of each order to see the offers made on them
- By clicking on "Accept offer", the order status is moved from "WAITING" to "APROVED"

#How to use it
One can use it by simply downloading the files and running it on any IDE after configuring the databank settings on the application.properties file. It receives http requests and has a view layer so one can interact with it at the browser.

#Next goal:

Show, to each user, the offers made on their products.
