Tour Planner
Form a team of two students to develop an application based on the GUI frameworks C# / WPF or
Java / JavaFX. The user creates (bike-, hike-, running- or vacation-) tours in advance and manages
the logs and statistical data of accomplished tours.
Requirements
Goals
• implement a graphical-user-interface based on WPF or JavaFX
• apply the MVVM-pattern in C# / Presentation-Model in Java
• implement a layer-based architecture with a UI Layer, a business layer (BL), and a data
access layer (DAL)
• implement design-patterns in your project
• define your own reusable UI-component
• store the tour-data and tour-logs via O/R-mapper in a PostgreSQL database; images should
be stored externally on the filesystem
• use a logging framework like log4net or log4j
• generate a report by using an appropriate library of your choice
• generate your own unit-tests with JUnit or NUnit
• keep your configuration (DB connection, base directory) in a separate config-file - not in the
compiled source code
• document your application architecture and structure as well as the development process
and key decisions using UML and wireframes
Features
• the user can create new tours (no user management, login, registration... everybody sees all
tours)
• every tour consists of name, tour description, from, to, transport type, tour distance,
estimated time, route information (an image with the tour map)
o the image, the distance, and the time should be retrieved by a REST request using the
OpenRouteservice.org APIs and OpenStreetMap Tile Server
(https://openrouteservice.org/dev , https://tile.openstreetmap.org/ )
• tours are managed in a list, and can be created, modified, deleted (CRUD)
• for every tour the user can create new tour logs of the accomplished tour statistics
o multiple tour logs are assigned to one tour
o a tour-log consists of date/time, comment, difficulty, total distance, total time, and
rating taken on the tour
• tour logs are managed in a list, and can be created, modified, deleted (CRUD)
• validated user-input
• full-text search in tour- and tour-log data
• automatically computed tour attributes
o popularity (derived from number of logs)
o child-friendliness (derived from recorded difficulty values, total times and distance)
o full-text-search also considers the computed values
• import and export of tour data (file format of your choice)
• the user can generate two types of reports
o a tour-report which contains all information of a single tour and all its associated tour
logs
o a summarize-report for statistical analysis, which for each tour provides the average
time, -distance and -rating over all associated tour-logs
• add a unique feature
Mandatory Technologies
• C# / Java as desktop application
• GUI-framework WPF (for C#) or JavaFX (for Java) or another Markup-Language-based UI
Framework
• OR-Mapper (like .Net/Entity-Framework or Java/JPA+Hibernate)
• HTTP for communication
• JSON serialization & deserialization
• Database Engine PostgreSQL used by the OR-Mapper
• Logging with log4j (Java) or log4net (C#) or another .NET Microsoft.Extensions-Solution.
• A report-generation library of your choice
• NUnit / JUnit
