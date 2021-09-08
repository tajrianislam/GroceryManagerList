
# Design Document

**Author**: Weifeng Zhao and Jiahao Chen

## 1 Design Considerations

*The subsections below describe the issues that need to be addressed or resolved prior to or while completing the design, as well as issues that may influence the design process.*

### 1.1 Assumptions

	- There is no bug on the app
	- The app works on 99% of the android devices.
	- Users can use the app right the way (The app is easy use, No tutorial animation)
	
### 1.2 Constraints

	- Screen: 
	The resolution of app shoud be one hand using friendly. Either the android phone is on portrait or landscape, it
	can be use easily by one hand.

	- latencies:
	In order to decrease the latencies, the algorithm should be better.

	- Conrner cases:
	  - Hidden Bugs
	  - App will crash by running on different devices.

### 1.3 System Environment

	Hardware: Android Devices
	Software: Android OS

## 2 Architectural Design

*The architecture provides the high-level design view of a system and provides a basis for more detailed design work. These subsections describe the top-level components of the system you are building and their relationships.*

### 2.1 Component Diagram
[![Component.png](https://i.postimg.cc/2SHZFt3j/Component.png)](https://postimg.cc/HrynTBQF) (v2.0)
The component diagram is mainly used to describe the dependencies between various software components, such as the dependencies between the executable and the source files. In the component diagram, each physical component of the system is represented by a component symbol.The component diagram describes the main functions of a system, such as subsystems, classes, packages, and components, from the perspective of software architecture. For example, User, GroceryList and Item are UI type and independent in the system, and ItemType belongs to Subsystem and is dependent to Item.

### 2.2 Deployment Diagram
[![Deployment.jpg](https://i.postimg.cc/gJZyT4s7/Deployment.jpg)](https://postimg.cc/LYmZqtrk)
Deployment Diagram is used to show the physical architecture of the software and hardware in the system. From deployment diagrams, you can understand the physical relationships between software and hardware components and the distribution of components in the processing nodes. Using deployment diagrams, you can show the structure of the runtime system and also convey how the hardware and software elements that make up the application are configured and deployed. In our deployment diagram, we have a DBServer where our database at, it contains the GroceryListCollection which is response for storing and update user's ItemList in real time. For user, we expect them to install our app "GroceryListManger" on their device. The application "GroceryListManger" contains everything user's need and able to communicate with the DBServer on itself.     

## 3 Low-Level Design

*Describe the low-level design for each of the system components identified in the previous section. For each component, you should provide details in the following UML diagrams to show its internal structure.*

### 3.1 Class Diagram

[![Class-Diagram-370-Group6.jpg](https://i.postimg.cc/N06VpNHK/Class-Diagram-370-Group6.jpg)](https://postimg.cc/d7040jnF)
A class diagram is used for the purposes of mapping out the structure of a system by modeling the relationship betwen its classes, displaying their attributes, and other operations that may be present in the system. Each class is show in a rectangle with its name, the attributes the clas contains, and any operations the class may use. Relationships such as inheritance are shown through physical connections between each of the class rectangles. It is benificial to include in a initial design in order to visually express the needs of the system clearly to existing or possibly onboarded team members and possibly even stakeholders. A well organized class diagram leads to efficient coding and less backtracking when creating the system. 

### 3.2 Other Diagrams

*None*

## 4 User Interface Design
[![UI-Design.png](https://i.postimg.cc/nr9dGZ6P/UI-Design.png)](https://postimg.cc/5Q1qxc9B)


DesignDocumentTemplate.md
