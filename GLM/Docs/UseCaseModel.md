# Use Case Model

**Eli Szafranski**: \<Team 6\>

## 1 Use Case Diagram

*This section should contain a use case diagram with all the actors and use cases for the system, suitably connected.*
[![Use-Case-Model-1.png](https://i.postimg.cc/SNmwptG9/Use-Case-Model-1.png)](https://postimg.cc/G9SMFKV3)
## 2 Use Case Descriptions

*For each use case in the use case diagram, this section should contain a description, with the following elements:*

- *Requirements: High-level description of what the use case must allow the user to do.*
- *Pre-conditions: Conditions that must be true before the use case is run.*
- *Post-conditions Conditions that must be true once the use case is run.*
- *Scenarios: Sequence of events that characterize the use case. This part may include multiple scenarios, for normal, alternate, and exceptional event sequences. These scenarios may be expressed as a list of steps in natural language or as sequence diagrams.*
### 1: 
- Requirements: The User must be able to create a new list
- Pre-conditions: The app started up properly and opened to the home screen
- Post-conditions: A new list will be created
- Scenarios:
  - User opens the app and home screen appears
  - User clicks "Create"  button and is prompted to give the list a name

### 2:
- Requirements: The User must be able to edit the name of a new list
- Pre-conditions: The app started up properly and opened to the home screen
- Post-conditions: A list will be renamed
- Scenarios:
  - User opens the app and home screen appears
  - User clicks the ImageButton in the shape of wrench to the right side of the list name
  - User is brought to new Activity and prompted to enter a new name and click Update. If no name is entered and a button press is registered, the User is prompted to enter a new name

### 3: 
- Requirements: The User must be able to add Item by ItemType to a list
- Pre-conditions: The app started up properly and the User must have chosen a specific List
- Post-conditions: An Item of a particluar quantity will be added to the list 
- Scenarios: 
  - User selects specific list from the home page
  - User clicks Add Item By Type 
  - A list of types are shown and User selects one of them
  - A list of Items of said Type are then shown and User selects one of them
  - User is then prompted for a quantity of said Item and clicks "Confirm"
  
### 4:
- Requirements: The User must be able to add Item by name to a list
- Pre-conditions: The app started up properly and the User must have chosen a specific List
- Post-conditions: An Item of a particluar quantity will be added to the list 
- Scenarios: 
  - User selects a list from the home page
  - User clicks Add Item By Name
  - The user does one of two things:
    1. Manually scrolls through the list of Items searching for a particular one
    2. In the search bar, types in the name of a specific Item. The listview returns all suitable matches
  - The User clicks their chosen choice and are prompted to add a quantity of said item, and clicks "Confirm"

### 5: 
- Requirements: The User must be able to add a new Item to the database
- Pre-conditions: The app started up properly, the User chose a specific List, and clicked Add By Name
- Post-conditions: An new Item of a particluar ItemType will be added to the database 
- Scenarios: 
  - User selects a list from the home page
  - User clicks Add Item By Name
  - The User clicks Add New Item To Database
  - The User is prompted to enter a name for the new Item and select an ItemType. If no name is given or no ItemType is selected, the user will be prompted to try again

### 6:
- Requirements: The User must be able to add a new ItemType to the database
- Pre-conditions: The app started up properly, the User chose a specific List, and clicked Add Item By Type
- Post-conditions: An new ItemType will be added to the database 
- Scenarios: 
  - User selects a list from the home page
  - User clicks Add Item By Type
  - The User clicks Add Create New Item Type
  - The User is prompted to enter a name for the new ItemType. If no name is given, or no ItemType is selected, the user will be prompted to try again

### 7: 
- Requirements: The User must be able to delete an Item from their list
- Pre-conditions: The app started up properly, the User chose a specific List
- Post-conditions: The Item will be removed from the list and will no longer be displayed 
- Scenarios: 
  - User selects a list from the homepage by clicking it
  - User LongClicks an Item
  - Dialog box appears asking if User wants to delete item from list
  - User clicks Yes to confirm

### 8: 
- Requirements: The User must be able to delete an entire List
- Pre-conditions: The app started up properly, the User chose a specific List
- Post-conditions: The List will be removed and will no longer show up on HomePage
- Scenarios: 
  - User selects a list from the homepage by clicking it
  - User clicks Delete List button
  - Dialog box appears asking if User wants to delete the List
  - User clicks Yes to confirm

### 9: 
- Requirements: The User must be able to checkoff Items on the list
- Pre-conditions: The app started up properly, the User chose a specific List which contains Items
- Post-conditions: The List will show a checkmark next to the Item until such case as the User unchecks the Item
- Scenarios: 
  - User selects a list from the homepage by clicking it
  - User clicks the checkbox to the left of the Item name

### 10: 
- Requirements: The User must be able to check and uncheck all the Items on the list at once
- Pre-conditions: The app started up properly, the User chose a specific List which contains Items
- Post-conditions: The List will show either all boxes checked or all boxes unchecked
- Scenarios: 
  - User selects a list from the homepage by clicking it and clicks one of two buttons:
    - User clicks Check All
      - All the checkboxes become checked and stay that way until manually unchecked by the User, even if User closes App
    - User clicks Uncheck All
      - All the boxes that were checked become unchecked and stay that way until manually rechecked by the User
      
### 11:
- Requirements: The User must be able update the quantity of an Item on a List
- Pre-conditions: The app started up properly, the User chose a specific List which contains Items
- Post-conditions: The List will show and preserve the new updated quantity of said Item
- Scenarios: 
  - User selects a list from the homepage by clicking
  - User then clicks the ImmageButton of a rench to the right of the item
  - A dialog box appears asking the User to enter a new quantity
  - The User clicks okay and is brought back to the list with the updated quantity showing
