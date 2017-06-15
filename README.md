Installation
============

Installation dependencies:
------------------------

- Install PostgreSQL. Download last version from [official site](https://www.postgresql.org/download/).
- Install Maven. Download last version from [official site](http://maven.apache.org/download.cgi)
- Copy project to disk with command:
```git
	git clone https://github.com/Tamapw/AdministrateUsers.git
```

Installation database:
----------------------
Note: All command for creating the database, user and table are in file createTables.sql in root project.

- Create database administrateuser in postgresql database with command:
```sql
	CREATE DATABASE administrateuser;
```

  - Create user root and grant him ALL privilages with command:
```sql
	CREATE USER root WITH password 'root';
	GRANT ALL ON DATABASE administrateuser TO root;
```

  - Log in in database administrateuser using rooе login and create users table with command:
    - WARN: You NEED log in with created user 'root' if you want use this servlet without changing config. If you create table not in 'root' user, then you can't work with database.
```sql
	CREATE TABLE users (
		id            serial PRIMARY KEY,
		firstname     varchar(40) NOT NULL CHECK (firstName <> ''),
		lastname      varchar(40) NOT NULL CHECK (lastName <> ''),
		datebirthday  date ,
		login         varchar(40) NOT NULL CHECK (login <> ''),
		password      varchar(40) NOT NULL CHECK (password <> ''),
		aboutuser     text,
		residence     text
	);
```

Run servlet:
------------
- Go to the folder with this project and execute command:
```
	mvn clean install tomcat7:run
```


Configuration
=============

If you want use custom user then you need change hibernate property in hibernate.cfg.xml files on path: /src/main/resources/hibernate.cfg.xml
If you create table not in user 'root' then you need change this property on your user.
```xml
		<property name="hibernate.connection.username">YOUR_USERNAME</property>
        <property name="hibernate.connection.password">YOUR_PASSWORD</property>
```
