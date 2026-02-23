# Cafeteria Management System 

A backend application that manages daily menus, fooditems and order for a cafeteria system.

## Description


This project is developed as a part of backend learning to implement real-world concepts such as  layered architecture, and database integration using Java and Sqlite with the help of sqlite-jdbc driver . 
The application simulates a cafeteria system where menus, food items and orders  are managed and exposed.


## Getting Started

### Dependencies And Requirements

*  Sqlite3 Database
  
*  IDE Setup

*  Sqlite - Jdbc Driver

*  Maven Compiler Plugin

### Installing

*  Clone the repository to your local machine using :
  
   ```bash
       git clone https://github.com/sivasbk140/Cafeteria_COS.git
   ```


### Program Execution:
<br>

  ### ADMIN MENU MANAGE EXECUTION :
 
  
 * Open your preferred IDE and navigate to the folder where the Cafeteria Management System repository has been cloned.
   

* Once the project loads and all dependencies are resolved, run the application.
  

* On startup, you will be presented with three role options: **ADMIN**, **CUSTOMER**, and **STAFF**. Select the role you want to proceed with.
  

* You will then be prompted to either **log in** or **register**. If you already have login credentials, choose the login option; otherwise, proceed with registration.
  

* During registration, provide a valid **username**, **password**, and **email address**.
  

* After successful registration, you will be redirected to the login screen. Enter your credentials, and if the email and password are valid, you will be directed to the appropriate console
     based on the selected role.
  
    <img width="427" height="358" alt="image" src="https://github.com/user-attachments/assets/948cda7f-1ec0-411d-a1b3-6d79d247e53a" />




* In the case of **ADMIN** login, the following **ADMIN MENU** options are available to manage the cafeteria application:

    1. View all food items  
    2. View food items by menu  
    3. Add a food item  
    4. Update a food item  
    5. Delete a food item  
    6. Manage menus  
    7. Exit

      

     
*  Select your desired option from the menu to access the corresponding functionality.

* If option **1** is selected, all available food items stored in the database will be displayed.

    <img width="884" height="617" alt="image" src="https://github.com/user-attachments/assets/a481ec7f-3fa5-4594-927f-291ae96b02a1" />

* If option **2** is selected, food items for a specific menu day will be shown. After entering your desired day (for example, Monday), the system will display the corresponding breakfast,     lunch, and dinner items. The same flow applies to other days as well.

    <img width="884" height="617" alt="image" src="https://github.com/user-attachments/assets/00c6388f-858b-4610-bdf2-aca9cd66a480" />

* If option **3** is selected, you can add a new food item to the database as shown below.

    <img width="586" height="436" alt="Screenshot from 2026-02-23 12-53-34" src="https://github.com/user-attachments/assets/61a34b95-7c84-40d1-9880-d023242fdf34" />
    
*  By selecting **option 4**, you can update the food item name, description, or other related details using the corresponding **Food Item ID**.

    <img width="704" height="375" alt="image" src="https://github.com/user-attachments/assets/c9bb3b6c-2085-4db2-b440-4390693f633f" />

  
*   By selecting **option 5**, you can delete a food item from the database by entering the corresponding **Food Item ID**, as shown below.

    <img width="536" height="261" alt="image" src="https://github.com/user-attachments/assets/40167fda-56d4-4261-b8d2-cc7bb6cbe36e" />


*  By selecting **option 6**, you can manage menus by assigning food items to specific days, removing items from a menu, creating new menus, and deleting existing menus.

     <img width="329" height="414" alt="Screenshot from 2026-02-23 14-00-07" src="https://github.com/user-attachments/assets/ab2f12df-66d5-4bf0-bb65-e7849faafc1d" />
  

  <br><br>

 ### CUSTOMER MENU MANAGE EXECUTION :
   
   
*  In case you choose the **CUSTOMER** role, you can either log in or register using a **username**, **email**, and **password**. After successful authentication, you will have access to
    the customer-specific operations listed below.

    <img width="403" height="249" alt="image" src="https://github.com/user-attachments/assets/d2012e01-7dcb-465f-8500-d261be4e711a" />

*  After logging in, select your desired option. If **option 1 (View Menu and Order)** is selected, you will be presented with the following two options:

      <img width="292" height="299" alt="Menu Options" src="https://github.com/user-attachments/assets/42b9757f-dcd5-412a-b752-6cb3d4f22354" />

*  You can choose to either view all available menus for different days or view the menu for a specific day. 


      <img width="777" height="623" alt="View Menu" src="https://github.com/user-attachments/assets/22116aac-f644-46e0-b090-6fa924cad2d7" />

*  After viewing the menu, you can place an order by entering the **food item name** and the **required quantity**.

      <img width="777" height="662" alt="Place Order" src="https://github.com/user-attachments/assets/5ee8c324-7e21-4d57-a83f-3943ebb35a5d" />

*  Once the order is placed, you can choose to either **checkout** or **cancel** the order. During checkout, you must provide the delivery details and confirm the order by typing **yes**,
     as shown below.

      <img width="777" height="655" alt="Checkout" src="https://github.com/user-attachments/assets/898f4c1b-6602-43a6-9268-71fc40ee71b8" />
 
*   If **option 3** is selected, you can view all the orders you have placed so far.

      <img width="622" height="369" alt="View Orders" src="https://github.com/user-attachments/assets/ba672538-7c4c-4946-986b-88f6a1faefb5" />

*   If **option 4** is selected, you can view the detailed information of a specific order, as shown below.

       <img width="533" height="610" alt="image" src="https://github.com/user-attachments/assets/6a22815b-aa25-4af6-9676-b4cf90446a05" />
       
*  **Option 5** allows you to cancel an order if the order status is still **PLACED**. Orders with the status **WAITING_FOR_DELIVERY** or
    **PENDING_DELIVERY** cannot be canceled.

  <br><br>

### STAFF MENU MANAGE EXECUTION :

*  In case you choose the **STAFF** role, you will see the following options after successful authentication.

      <img width="379" height="260" alt="Screenshot from 2026-02-23 14-57-29" src="https://github.com/user-attachments/assets/188ef8cd-173e-4b3f-9680-12f027f6f130" />
 * By selecting **option 1**, you can view the food menu for the appropriate days, similar to the customer view.

*  By selecting **option 2**, you can view all active orders, as shown below.

     <img width="624" height="406" alt="Active Orders" src="https://github.com/user-attachments/assets/488ca5f5-67b8-440d-a394-1b343e4d79ca" />

*  By selecting **option 3**, you can view detailed information for a specific order, as shown below.

     <img width="624" height="645" alt="Order Details" src="https://github.com/user-attachments/assets/5f2cde1e-5bcf-41bf-ab2e-d1d40ba3c88c" />

*  By selecting **option 4**, you can update the order status from **PLACED** to **WAITING_FOR_DELIVERY**, as shown below.

     <img width="624" height="516" alt="Update Order Status" src="https://github.com/user-attachments/assets/848025f4-0f0a-4d86-afd8-c6cf368f7651" />

*  By selecting **option 5**, you can view all canceled orders.

*  By selecting **option 6**, you can view all completed orders.

  <br> <br>
## Author

 SIVABALAKRISHNAN B

 [ @SIVABALAKRISHNAN ]: (https://github.com/sivasbk140)



