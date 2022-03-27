# ndm
This is a project to manage network devices.\
Technicals: Java Spring Boot + MySQL.

## System architecture:
![image](https://user-images.githubusercontent.com/43290383/160295589-bae7ce70-4ace-4db2-9210-9096ee8e96f7.png)
## Source code structure:
    .
    │   .gitignore
    │   HELP.md
    │   mvnw
    │   mvnw.cmd
    │   pom.xml
    │   README.md
    ├───.mvn                                        # Maven
    ├───database                                    # Database script file
    └───src
        ├───main                                    # Contains the main source code
        │   ├───java
        │   │   └───com
        │   │       └───ndm
        │   │           └───api
        │   │               ├───common              # Contains shared files
        │   │               ├───config              # Contains config files
        │   │               ├───controller          # Presentation Layer
        │   │               │   └───error           # Presentation class for custom errors
        │   │               ├───dto                 # Data transfer object
        │   │               ├───entity              # Entity model
        │   │               ├───exception           # Contains custom exceptions
        │   │               ├───repository          # Data Access Layer
        │   │               ├───service             # Business Logic Layer
        │   │               └───validation          # Contains custom validations       
        │   │                   └───validator
        │   └───resources                    
        └───test                                    # Contains test source code
## LDM. Model:
## Run:
