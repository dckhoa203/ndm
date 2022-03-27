# ndm
This is a project to manage network devices.

## Source code structure:
    .
    ├───.mvn
    │   └───wrapper
    ├───database
    └───src
        ├───main
        │   ├───java
        │   │   └───com
        │   │       └───ndm
        │   │           └───api
        │   │               ├───common
        │   │               ├───config
        │   │               ├───controller
        │   │               │   └───error
        │   │               ├───dto
        │   │               ├───entity
        │   │               ├───exception
        │   │               ├───repository
        │   │               ├───service
        │   │               └───validation
        │   │                   └───validator
        │   └───resources
        │       ├───static
        │       └───templates
        └───test

    .
    ├── database                # database script file
    ├── src                     # Source files (alternatively `lib` or `app`)
    ├── test                    # Automated tests (alternatively `spec` or `tests`)
    ├── tools                   # Tools and utilities
    ├── LICENSE
    └── README.md
