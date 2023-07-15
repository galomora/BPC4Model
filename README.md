# BPC4Model
BP C4 Model Generator

Source project and generated files for the architecture exercise provided

## Compiling
### Install Requirements:
* Java JDK v 17
* Maven

In the project directory, execute on terminal:

```
mvn clean install
```

The command will generate the executable jar file:
```
target/bp-internet-banking-c4-1.0-SNAPSHOT-jar-with-dependencies.jar
```
## Running
### Install Requirements:
* Java JRE v 17

In a directory containing the generated file bp-internet-banking-c4-1.0-SNAPSHOT-jar-with-dependencies.jar

Execute the jar file from command line:
```
java -jar bp-internet-banking-c4-1.0-SNAPSHOT-jar-with-dependencies.jar
```
The command will execute the jar and generate the output files in the same directory

Sample output:
```
Current directory: D:/projects/java/BPC4Model/target/
Diagram saved to file: D:/projects/java/BPC4Model/target/system.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/internet-banking-container.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/transfer-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/movements-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/user-info-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/notifications-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/notifications-sender-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/mobile-app-component.uml
Diagram saved to file: D:/projects/java/BPC4Model/target/web-spa-component.uml
```

The command will create several uml files that are in the format:
```
C4 PlantUML
```

The diagrams can be opened in PlantUML web-site, or an editor, like Eclipse IDE + PlantUML Plugin

## Diagrams
Generated diagrams as uml files and png images are included in the directory:
```
model
```
### system
Overall system context
### internet-banking-container
Container diagram for the Internet Banking System.
Describes the containers required and options for technologies
### transfer-component
Components diagram for the Transfer app
### movements-component
Components diagram for the Movements app
### user-info-component
Components diagram for the Movements Info app
### notifications-component
Components diagram for the Notifications app
### notifications-sender-component
Components diagram for the Notifications Sender app
### mobile-app-component
Components diagram for the Mobile App
### web-spa-component
Components diagram for the web single page app


