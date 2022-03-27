# ndm
This is a project to manage network devices.

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
