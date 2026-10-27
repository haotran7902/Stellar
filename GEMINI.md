1. Project structure
```
my-spring-boot/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── example/
    │   │           └── demo/
    │   │               ├── DemoApplication.java
    │   │               ├── controller/
    │   │               ├── service/
    │   │               ├── repository/
    │   │               ├── entity/
    │   │               ├── dto/
    │   │               │   ├── request/
    │   │               │   └── response/
    │   │               ├── config/
    │   │               ├── exception/
    │   │               └── mapper/
    │   └── resources/
    │       ├── application.properties
    │       ├── static/
    │       └── templates/
    └── test/
        └── java/
            └── com/
                └── example/
                    └── demo/
```

2. Folder feature

- `controller/`: Handles HTTP requests and returns responses.
- `service/`: Handles business logic.
- `repository/`: Queries and interacts with the database.
- `entity/`: Contains classes mapped to database tables.
- `dto/`: Contains classes that define input and output data for APIs.
- `config/`: Contains configurations such as Security, CORS, and Beans.
- `exception/`: Handles exceptions and centralized error handling.
- `mapper/`: Converts data between Entities and DTOs.
- `resources/`: Contains configuration files and application resources.
- `static/`: Contains static resources such as CSS, JavaScript, and images.
- `templates/`: Contains HTML templates, commonly used with Thymeleaf.
- `test/`: Contains unit tests and integration tests.
- `pom.xml`: Manages dependencies and Maven build configuration.
- `DemoApplication.java`: The main class used to start the Spring Boot application.

<!-- CODEGRAPH_START -->
## CodeGraph

In repositories indexed by CodeGraph (a `.codegraph/` directory exists at the repo root), reach for it BEFORE grep/find or reading files when you need to understand or locate code:

- **MCP tool** (when available): `codegraph_explore` answers most code questions in one call — the relevant symbols' verbatim source plus the call paths between them, including dynamic-dispatch hops grep can't follow. Name a file or symbol in the query to read its current line-numbered source. If it's listed but deferred, load it by name via tool search.
- **Shell** (always works): `codegraph explore "<symbol names or question>"` prints the same output.

If there is no `.codegraph/` directory, skip CodeGraph entirely — indexing is the user's decision.
<!-- CODEGRAPH_END -->
