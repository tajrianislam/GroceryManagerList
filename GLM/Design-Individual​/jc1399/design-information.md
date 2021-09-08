# Class Diagram - GroceryListManager
1. A grocery list consists of items the users want to buy at a grocery store. The application must allow users to add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from one to two pounds of apples).
> Items contain the attributes that describe the item and they can be added to a list
2. The application must contain a database (DB) of items and corresponding item types.
> The items will be pulled from a DB that separates the item by a broader type and specific name. DB will be handeled outside the system
3. Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding an item, users must be able to specify a quantity for that item.
> The type and item name will be handeled in the DB when added to it, but the specifications will be used in order to oprganize items in the list
4. Users must also be able to specify an item by typing its name. In this case, the
application must look in its DB for items with similar names and ask the users, for each
of them, whether that is the item they intended to add. If a match cannot be found, the
application must ask the user to select a type for the item and then save the new item,
together with its type, in its DB.
> Will be handeled by a search function that organizes search and attempts to match input to DB
5. Lists must be saved automatically and immediately after they are modified.
> Unimportant to initial system
6. Users must be able to check off items in a list (without deleting them).
> Unimportant to initial system
7. Users must also be able to clear all the check-off marks in a list at once.
> Unimportant to initial system
8. Check-off marks for a list are persistent and must also be saved immediately.
9. The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once (i.e., without having to go back and forth
between aisles).
> Item type will carry through from DB and in List there can be organized by type with organize function. If items have the same type then the function will sort them appropriately 
10. The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists.
> Class ListCollection will conation the lists of different names andd hold their own set of items
11. The User Interface (UI) must be intuitive and responsive
> Not considered because it does not affect the design directly.