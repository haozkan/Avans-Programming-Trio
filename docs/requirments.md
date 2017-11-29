# Introduction
Netflix Statistics is a Java application which enables you to translate database information to usefull graphs and views.

# Functional Requirements
- The user must be able to manage accounts, profiles and watched series/movies. (CRUD)
- The user must be able to see the average watch time (in %) per series.
- The user must be able to see (on selected series) the average percentage of watched episodes, and for every episode the user can see the next episode in line.
- The user must be able to inspect an account, and see which series it has viewed.
- The user must be able to sort movies with longest duration, and requirements of an age such as; below 16years old.
- The user has to view accounts with only one profile.
- The user must be able see how many other users have seen selected movie.

# Technical Requirments
- All Classes and Methods include a short description.
- The GUI is designed with labels, textfields an buttons.
- Database Tables always have a Primary Key.
- Database Relations are indicated using a Foreign Key Construction.
- Datatypes match the use case of the data.
- Code must be in English and must follow the Java Guidelines.
- Minimum of two Unit Tests to test the applications functionality.

# Database Requirments
- If an account gets deleted, profiles belonging tot that account must be deleted too. 
- If a profile gets deleted, information about watched programs belonging to that profile must be deleted too.
- All information other than accounts, profiles and viewing behavior is read-only
- All tables in the database must be normalized to level 3NV.

# Documentational Requirments
- Correct and corresponding ERD.
- Carrect and corresponding Class Diagram (UML).

# UI Requirments
- The Application has a polished GUI.
- The Application has a static Toolbar which contains all views.
- The Application scales when changing dimentions.
  - The components in the toolbar are static and do not scale.
- The statusbar contains the name of the facutility, klas and names with student ID's.
