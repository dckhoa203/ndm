# ndm
This is a project to manage network devices.\
Technicals: [Java Spring Boot](https://spring.io/projects/spring-boot) + [MySQL](https://www.mysql.com/).

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
        │   │               ├───controller          # Presentation layer
        │   │               │   └───error           # Presentation layer for custom errors
        │   │               ├───dto                 # Data transfer object
        │   │               ├───entity              # Entity model
        │   │               ├───exception           # Contains custom exceptions
        │   │               ├───repository          # Data Access layer
        │   │               ├───service             # Business logic layer
        │   │               └───validation          # Contains custom validations       
        │   │                   └───validator
        │   └───resources                    
        └───test                                    # Contains test source code
## LDM. Model:
## Run:
After the build is completed. Look for the class *ApiApplication.java* and start to run.

    .
    └───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───ndm
    │   │           └───api
    │   │               │   ApiApplication.java
    
Look for the class *ApiPathConfig.java*, this class contains all the api endpoints.

    .
    └───src
    ├───main
    │   ├───java
    │   │   └───com
    │   │       └───ndm
    │   │           └───api
    │   │               ├───config
    │   │               │       ApiPathConfig.java
