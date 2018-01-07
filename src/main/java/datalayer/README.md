# Package: Datalayer

## Class: MysqlDAO
The MySQL DAO handles the connection with the database.

### getInstance(): MysqlDAO

```java
    public static MysqlDAO getInstance() {
        if (instance == null) {
            return new MysqlDAO();
        } else {
            return instance;
        }
    }
```

This method checks if there is an instance of the MySQLDAO object, if there isn't it returns a new object of the class. (Singleton)

### connect() : Connection

```java
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/" + dbname, user, pass);
            return connection;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
```

This method tries to connect to the MySQL Database using the JDBC Driver. If it succeeds to connect, it retruns a Connection object.
If it fails to connect, the exception gets caught and logged.

### closeConnection(): void

```java
    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MysqlDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
```

This method closes the connection if one exists.