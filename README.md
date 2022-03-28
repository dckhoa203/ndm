# ndm
This is a project to manage network devices.\
Technicals: [Java Spring Boot](https://spring.io/projects/spring-boot) + [MySQL](https://www.mysql.com/).

## System architecture:
![architecture](https://user-images.githubusercontent.com/43290383/160320084-2eceddb9-c9c7-4f54-8861-12c45415f961.png)
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
## LDM model:
![LDM model](https://user-images.githubusercontent.com/43290383/160319972-48efb392-44bf-45f6-80fc-9f90529464ef.png)
## Run:
After the build is completed, look for the class *ApiApplication.java* and start to run.\
By default, the application will run on port 8080.\
Look for the class *ApiPathConfig.java*, this class contains all the api endpoints.\
Example: [GET] http://localhost:8080/v1/credentials

    .
    └───src
        ├───main
        │   ├───java
        │   │   └───com
        │   │       └───ndm
        │   │           └───api
        │   │               │   ApiApplication.java
        │   │               ├───config
        │   │               │       ApiPathConfig.java
