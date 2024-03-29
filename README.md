# MUDI.Application-API-Rest
API Java/Spring project made at Alura's Spring MVC courses. It's an application that register products and offers on them in a databank, and follows the MVC pattern. It's view layer is made with thymeleaf as well as Vue.js. 

#New features 
After the course, I expanded the original application with these new features:\
- Register feature that allows the creation of users through a form in the browser\ 
- Offers on orders are now stored on the databank with a order fk linking them\
- The user accessing the "My orders" page can now click on a "Received offers" button at the end of each order to see the offers made on them\ 
- By clicking on "Accept offer", the order status is moved from "WAITING" to "APROVED" and no longer appears on the "Make your offer" page\
- The received offers button now only appears on the cards of orders that have a "WAITING" status. Thus making it impossible to see and accept more offers if the status is "APROVED" (an offer was already accepted) or "DELIVERED"\
- Accepting an offer makes it so that its value and deliver date are set in those respective fields on the order card\
- The username is now displayed at the top right corner, next to the logout button\


#How to use
One can use it by simply downloading the files and run it on any Java IDE after configuring the databank settings on the application.properties file. It receives http requests and has a view so one can interact with it at the browser. The first thing will be to create a user, then login. After that, one can intuitively create orders, make offers, accept them, create more users, view the orders and offers listed and etc.
