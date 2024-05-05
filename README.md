# Tour Planner Application

## Overview
This project involves developing a desktop application using a GUI framework (either C# / WPF or Java / JavaFX) that allows users to create and manage tours (bike, hike, running, or vacation). The user can also manage logs and statistical data of the tours they have accomplished. 

## Team
Form a team of two students to design and implement the project.

## Goals
1. **Graphical User Interface**: Implement the GUI using WPF or JavaFX.
2. **Architecture**: Follow a layered architecture with UI, business, and data layers.
3. **Design Patterns**: Implement appropriate design patterns.
4. **MVVM Pattern**: Apply the MVVM pattern (C#) or Presentation-Model pattern (Java).
5. **Custom Components**: Design reusable UI components.
6. **ORM**: Use an O/R Mapper to store data in a PostgreSQL database.
7. **Logging**: Implement logging using log4net or log4j.
8. **Reports**: Generate reports using an appropriate library.
9. **Unit Testing**: Create unit tests using JUnit (Java) or NUnit (C#).
10. **Configuration Management**: Store configuration data in separate config files.
11. **Documentation**: Document the architecture and development process with UML and wireframes.

## Features
1. **Tour Management**: Create, modify, and delete tours.
    - **Tour Details**: Name, description, start and end points, transport type, distance, estimated time, and route map.
    - **REST API**: Retrieve distance, time, and map image using OpenRouteService and OpenStreetMap Tile Server.
    - **List Management**: View and manage tours in a list.
2. **Tour Logs**:
    - **Log Details**: Date/time, comments, difficulty, total distance, total time, and rating.
    - **CRUD Operations**: Create, modify, and delete tour logs.
3. **Validation**: Validate all user input.
4. **Search**:
    - Full-text search in tour and tour log data.
    - Include computed tour attributes such as popularity and child-friendliness in searches.
5. **Computed Attributes**:
    - **Popularity**: Derived from the number of tour logs.
    - **Child-Friendliness**: Derived from difficulty, time, and distance.
6. **Import/Export**: Import and export tour data in a custom file format.
7. **Reports**:
    - **Tour Report**: All information of a single tour and its logs.
    - **Summary Report**: Average time, distance, and rating across all logs.
8. **Unique Feature**: Add a unique feature to distinguish the application.

## Mandatory Technologies
1. **Programming Language**: C# (WPF) or Java (JavaFX).
2. **ORM**: .NET Entity Framework (C#) or Java JPA + Hibernate.
3. **HTTP**: REST API integration.
4. **JSON**: JSON serialization and deserialization.
5. **Database**: PostgreSQL.
6. **Logging**: log4net (C#) or log4j (Java).
7. **Report Library**: Choose an appropriate report-generation library.
8. **Unit Testing**: NUnit (C#) or JUnit (Java).


