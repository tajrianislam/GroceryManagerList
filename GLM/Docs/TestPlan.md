# Test Plan

## 1 Testing Strategy

### 1.1 Overall strategy

The testing for this project will include black-box and white-box techniques. 

Black-box technique doesn't require the code to be ready in order for us to start thinking about the test cases. This technique can help us ensure that there are no logic defects in our code. 

White-box technique is based on the code, and can be measured objectively and automatically. This technique can help us ensure all the internal components have been adequately realized. Junit can be used to write individual test cases for the project. The software will be tested for a number of inputs and the expected results. 


### 1.2 Test Selection

System testing (Falls under black-box testing)

    a. Create a list with a name (i.e. "Weekend groceries")
    b. Add some items to the list from a heirarchical list, by first picking the types of items and then selecting the item itself and then specify the quantity.
    c. Search for an item that is not in the database, and add it to database when the item search returns negative.
    d. Check off some items from the list and assess if the item is simply checked off and not deleted.
    e. Check if the items are neatly organized under types. 
    f. Exit the list and open the list again to ensure that all the activities have been saved and not lost.
    g. Create a new list with a new name. Delete the old list to ensure that we are able to do so. 
    h. Ensure that user experience is seamless and intuitive. 

Functionality testing (Falls under white-box testing)

The test cases shown below tests the individual functionalities. For the purpose of integrated testing and to ensure that all the dependencies are met, the tests can be run in one instance. 

    a. createList()
    b. addItemToList()
    c. addNewItemToDB()
    d. addItemByItemType()
    e. removeItemFromList()
    f. checkItem()
    g. clearAllCheckMarks()
    h. updateListName()
    i. deleteList()
    j. setQuantity()
    k. groupByItemType()

### 1.3 Adequacy Criterion

### 1.4 Bug tracking

JUnit has an adequate bug tracking system that will tell us of functionalities that are causing an issue. Dependency errors may occur when we connect the functionalities, we will be vary of this if our project passes the unit tests but fails the integration tests. Any other arising bugs can be tested for manually. 

### 1.5 Technology

We will be using JUnit api for testing purposes. We chose this because of ease of usability and the our familiarity of it.

## 2 Test cases

| Test Case                            | Steps                                                                                                                                                                 | Input        | Expected result                                 | Actual result                                   | Pass/Fail |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------|-------------------------------------------------|-------------------------------------------------|-----------|
| Create a list with a new name        | 1\. Click CREATE LIST button <br> 2\. Enter the name "List1"                                                                                                          | List1        | New List "List1 is created                      | List1 created                                   | Pass      |
| Rename a list with a new name        | 1\. Click the tool button <br> 2\. Enter in "List2"                                                                                                                   | List2        | List1 is now renamed to List2                   | List1 is renamed to List2                       | Pass      |
| Add item by type                     | 1\. Open the list <br> 2\. Click ADD ITEM BY TYPE button <br> 3\. Click chicken and enter 2lbs                                                                        | N/A          | Item chicken, Type meat, Quantity 2 lbs created | Item chicken, Type meat, Quantity 2 lbs created | Pass      |
| Create new item type                 | 1\.Open the list <br> 2\. Click ADD ITEM BY TYPE button<br> 3\. Click CREATE NEW ITEM TYPE <br> 4\. Type "Stationaries"                                               | Stationaries | New type "Stationaries" is created              | Type "Stationaries" created                     | Pass      |
| Create new item in type Stationaries | 1\. Open the list <br> 2\. Click ADD ITEM BY NAME <br> 3\. Click ADD NEW ITEM TO DATABASE <br> 4\. Type in "Notebook" and Pick type stationaries\. <br> 5\. Click ADD | Notebook     | New item Notebook is created under stationaries | New item Notebook is created                    | Pass      |
| Check all items                      | 1\. Open the list 2\. Click CHECK ALL button                                                                                                                          | N/A          | All items are checked                           | All items are checked                           | Pass      |
| Uncheck all items                    | Click UNCHECK ALL button                                                                                                                                              | N/A          | All items in the list are unchecked             | All items in the list are unchecked             | Pass      |
| Delete list                          | Click delete list                                                                                                                                                     | N/A          | The list is deleted                             | The list is deleted                             | Pass      |
| Return to Homepage                   | Click the home image                                                                                                                                                  | N/A          | Return to main page                             | Return to main page                             | Pass      |
