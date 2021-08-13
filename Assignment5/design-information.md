##Grocery Design Information##
#############
1.1 A grocery list consists of items the users want to buy at a grocery store. 

To realize this requirement, the class Grocery List has been created where items to be bought are added with
item name, quantity,type and farmer details.
#################
1.2 The application must allow users to 
add items to a list, delete items from a list, and change the quantity of items in the list (e.g., change from 
one to two pounds of apples). 

User Creates a User Item List from the Grocery List by selecting the item and quantity.

##################
2.The application must contain a database (DB) of items	 and corresponding item types.

This requirement has been realized by creating classes item and item type. Once the user selects an item type, the 
item names under that type are going to be displayed to the user.

##################
3.Users must be able to add items to a list by picking them from a hierarchical list, where the first level is the
 item type (e.g., cereal), and the second level is the name of the actual item (e.g., shredded wheat). After adding
an item, users must be able to specify a quantity for that item. 

Once the user selects an item type, the item names under that type are going to be displayed to the user. They can now 
add that item to the user list.

####################
4.Users must also be able to specify an item by typing its name. In this case, the application must look in its DB for
items with similar names and ask the users, for each of them, whether that is the item they intended to add. If a match 
cannot be found, the application must ask the user to select a type for the item and then save the new item, together 
with its type, in its DB. 

This requirement has been realized by adding methods type name, select type of item & save item to the item class.

#####################
5.Lists must be saved automatically and immediately after they are modified. 

To achieve this design requirement, i added a save list method to the user item list class.

####################

6.Users must be able to check off items in a list (without deleting them). 

This has been realized by adding the check off method to the user item list class & omitting the delete operation from the class.

#######################
7.Users must also be able to clear all the check-off marks in a list at once. 

This has been realized by adding the clear check-offs method in the user item list class.
########################
8.Check-off marks for a list are persistent and must also be saved immediately. 

This has been realized by adding the save check-offs method in the user item list class.
########################

9.The application must present the items in a list grouped by type, so as to allow users to shop for a specific type of products at once
(i.e., without having to go back and forth between aisles). 

This has been achieved by having the item and item type classes in the design. Once the user selects any type, a list of all items in that type
 will be displayed.
The user now adds them to their list.

#########################
10.The application must support multiple lists at a time (e.g., “weekly grocery list”, “monthly farmer’s market list”). 
Therefore, the application must provide the users with the ability to create, (re)name, select, and delete lists. 

This has been achieved by creating sample "list 1" & "list 2" classes with operations create, (re)name, select & delete. 
The sample lists can be renamed to suit the user list preffered name.

########################

11.The User Interface (UI) must be intuitive and responsive. 
This requirement has not been considered since it does not directly affect the design.











