# Design Discussion - Team 6

## Design 1:

[![Tarjan-Design.jpg](https://i.postimg.cc/wjzcgXJx/design-1.png)](https://postimg.cc/9rxqBqJs)

Pros:
1. Flows well, easy to understand

Cons:
1. Reduncancy. List 1 and 2 seem unnecessary
2. Confusion with multiplicity, a user should be able to have mulitple item lists


## Design 2: 

[![Jiahao-DBDesign.jpg](https://i.postimg.cc/1t3z962M/Jiahao-DBDesign.jpg)](https://postimg.cc/jWBTcWsJ)

Pros:
1. Easy to read. Direct paths between classes
2. Descriptive methods with useful fuctions

Cons:
1. Database connection to item instead of GoceryList makes more sense 
2. Does not include clear multiplicities between relationships
3. Improper use of inheritance 


## Design 3:
[![Screen-Shot-2020-10-15-at-12-52-47-PM.png](https://i.postimg.cc/ZnfmSm8r/Screen-Shot-2020-10-15-at-12-52-47-PM.png)](https://postimg.cc/rRt31vYm)

Pros: 
1. Descriptive classes
2. Useful methods
3. Correct use of multiplicity 
4. Abundance of methods makes design flexible for users
5. Proper use of indictors (+ -)

Cons: 
1. Dotted relation is unclear
2. Unsure where database is being pulled from
3. Unsure how GroeceryList is stored 


## Design 4:

[![Screen-Shot-2020-10-15-at-5-30-38-PM.png](https://i.postimg.cc/DzCLqqV4/Screen-Shot-2020-10-15-at-5-30-38-PM.png)](https://postimg.cc/wtm1zRj9)

Pros:
1. Detailed design.
2. Clear method and attribute names.

Cons:
1. Connection to database shown but be unnecessary.
2. Lack of multiplicity.
3. Grocery class should pull from database.
4. More accurate connections (all connections are inheritance).


## Design 5:

[![weifing.png](https://i.postimg.cc/tgqKxR0Q/weifing.png)](https://postimg.cc/18d7xQ5J)

Pros:
1. Clear layout that is easy to read and understand
2. Clear methods and attributes
3. Proper use of indicators that describe encapsulation (+ -)

Cons:
1. There is no class or methods to handle the collection of the grocery lists.
2. Missing multiplicities that specify cardinality of elements.
3. Misuse of graphical representation as the connections in the design are all inheritance.


## Design 6:
[![1602780867791.jpg](https://i.postimg.cc/SsDyCpMv/1602780867791.jpg)](https://postimg.cc/9Rw5HK1d)

Pros: 
1. Aesthetically pleasing

Cons: 
1. Missing attribute/ method indicators (+ -)
2. Missing unCheckAll method


## Team Design
[![Class-Diagram-370-Group6.png](https://i.postimg.cc/Bbb8fbwR/Class-Diagram-370-Group6.png)](https://postimg.cc/kRrMxJCy)
(v1.0)

* Made inheritance relationship between Item and ItemType
* addNewItemToDatabase was not included in each individual design, but we made sure to add it to the team design
* Added GroceryListCollection class, relationship with grocery list. 1 collection can content many (*) Grocery lists
* Commonalities: Each member had roughly the same classes. Methods were quite similar as well
* Differences: Dotted relationship included with ListItem, ListCollection which is a holding place, no actual functionality. groupByItemType is private because this is done by the grocery list itself, not the user. Quantity type was specified to avoid confusion about unit of measurement (gallons, oz, lbs, liters, etc.)
* Clearly stated the parameter types for every attribute
* This design was detailed enough to envision how we would create the app based on it. It clearly laid out every noun as a class and gave every class very detailed methods of use. This diagram was also able to connect every class together to bridge all of the functionalities.

[![design-team-v2-0.png](https://i.postimg.cc/QdsnVQPs/design-team-v2-0.png)](https://postimg.cc/BXYCVK4w)
(v2.0)

## Summary

The biggest takeaway we shared during our deliberation was how many ways there are to implement the same design, with none necessarily being more correct than the other. Communicating without being confrontational allowed us to take the criticisim of our designs constructively and helped us understand why our designs would not work in the long run, which saved us time in the future. If we continued with a design that was not clear from the start then we would end up having to go back to alter our original ideas. Having the different points of views and interpretations of the instructions allowed us to discuss various plans of attack and filter out only the best ideas. 