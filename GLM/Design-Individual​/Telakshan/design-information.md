1. A grocery list consists of items the users want to buy at a grocery store. The application
must allow users to add items to a list, delete items from a list, and change the quantity 
of items in the list (e.g., change from one to two pounds of apples). - The GroceryList class has the methods addItem(), changeQuantity(), deleteItems() which meets the requirement. 
2. The application must contain a database (DB) of items and corresponding item types .
- The database has TableOfLists which contains the lists that will be created by the user. 
3. Users must be able to add items to a list by picking them from a hierarchical list, where
the first level is the item type (e.g., cereal), and the second level is the name of the
actual item (e.g., shredded wheat). After adding an item, users must be able to specify a
quantity for that item. - The user will be able to add from a list of grocery items from a list, the application will have types that the user can choose from. 
4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB. - The tables that are in the database will be useful for this purpose. when the user types the name of the item, there will be search that will determine if there is a match. The application will then suggest that there is a duplicate exists if it exists. If not, the new item will be added.
5. Lists must be saved automatically and immediately after they are modified. - The grocery list manager class will be useful for this purpose. The functions saveList(ListID, modified), deleteList() will responsible for the modifying of the lists. saveList() function will be called everytime there is modification.
6. Users must be able to check off items in a list (without deleting them). - checkItem(checked) will be invoked when the user wants to check off the item in the list.
7. Users must also be able to clear all the check-off marks in a list at once. - The uncheckAll() method will uncheck everything in a list.
8. Check-off marks for a list are persistent and must also be saved immediately. - saveList() function will save the list, there is a boolean value that will monitor if the list is modified or not.
9. The application must present the items in a list grouped by type, so as to allow users to
shop for a specific type of products at once (i.e., without having to go back and forth
between aisles). - Each grocery will have a type that can be picked by the user. The lists will also be displayed in the application under types.
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. - The database will be able to contain multiple lists. The grocery list manager class will be able to create, edit and delete lists.
11. The User Interface (UI) must be intuitive and responsive. - Although it is not necessary, I've included some design approach that can be taken towards an intuitive UI.