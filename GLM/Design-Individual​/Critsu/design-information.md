1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).

	To realize this requirement, I added to the design a class user, Item and grocery list. In grocery list, I added additem, deleteItem and changeQuantity methods.

2. The application must contain a database (DB) of ​items​ and corresponding ​item types​.
	
	To realize this requirement, I added DataBase class that connect to the grocery list class. In DataBase class, I added createItem that corresponds to the methods in the grocery list class.

3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.

	To realize this requirement, I added createList method in User. And, attributes which are ItemType, ItemName and Quantity in Item class.

4. Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match cannot be found, the application must ask the user to select a type for the item and then save the new item, together with its type, in its DB.

	To realize this requirement, I added similarItem method and autoSaveList method in Grocery List, and updateItem in DB class that the data will be saved and updated in DB.

5. Lists must be saved automatically and immediately after they are modified.
	
	To realize this requirement, I added autoSaveList method in grocery list class.


6. Users must be able to check off items in a list (without deleting them).
	
	To realize this requirement, I added checkOff method in grocery list class.

7. Users must also be able to clear all the check-off marks in a list at once.

	To realize this requirement, I added clearCheckOff method in grocery list class.

8. Check-off marks for a list are persistent and must also be saved immediately.
	
	To realize this requirement, I added autoSaveList method in grocery list class.

9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).

	To realize this requirement, I added getItemByType method and groupItemByType method in grocerylist


10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly
farmer’s market list”). Therefore, the application must provide the users with the ability to
create, (re)name, select, and delete lists.

	To realize this requirement, I added createList method, reNameList method and deleteList method in User class.


11. The User Interface (UI) must be intuitive and responsive.

	Not considered because it does not affect the design directly.
	
