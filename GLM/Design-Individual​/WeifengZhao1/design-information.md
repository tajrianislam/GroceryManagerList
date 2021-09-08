1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
	- A class name "GroceryList" is created that contains attributes of  ItemType, ItemListName,      ItemQuantity and get existing customer itemlist if we found the customer name in our         
         database.
	- It contains methods to add or delete the type of the item. Also, it contains methods to add       or delete into our item list. Last, we can also update the quantity of the item.

2. The application must contain a database (DB) of ​items​ and corresponding ​item types​.
	- A class name "Database" is created that contains attributes of CList that contains        
         CustomerList and CListItem that contains customer's item list.
    
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
	- A class name "ItemList" is created that contains attributes of ItemType, ItemName and             ItemQuantity. 
        - It contains methods to add or delete the type of the item. Also, it contains methods to add 
          or delete into our item list. Last, we can also update the quantity of the item.
       
4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.
	- "Database" is connected to "ItemList", we will know what the user select and it will save 
          the item into our database.

5. Lists must be saved automatically and immediately after they are modified.
	- "Database" is connected to ItemList and GroceryList and it will automtically save the
          changes.

6. Users must be able to check off items in a list (without deleting them).
	- Added a method called "CheckOff_item()" that will check off items in our item list without        deleting them.

7. Users must also be able to clear all the check-off marks in a list at once.	
	- Added a method called "ClearCheckOff_item()" that will clear check off items in our item 
          list.
	
8. Check-off marks for a list are persistent and must also be saved immediately.
	- Since, the database is connected to ItemList,The method Save_Cusomter_ItemList() in  
        "Database", and it will save any 	changes  that user made.

9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth between aisles).
	- Non-related to software designing.

10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.

	- A class name "Customer" is created that contains attributes of CustomerList that stores all 
          register customers into our CList.
	- It contains methods to get, add, (re)name, select and delete the  customer name from our 
          lists.

11. The User Interface (UI) must be intuitive and responsive.
	- Non-related to software designing.
