Book_Management
===============

Java-Programm zum Verwalten von B&uuml;chern


------------------------------------------------------------------

SQL DATENBANK ERSTELLEN

Datenbank "book_database" und User "book_user" mit dem Passwort "book_password" erstellen ->

    mysql> CREATE DATABASE book_database;  
    mysql> GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP, INDEX, ALTER, LOCK TABLES, CREATE TEMPORARY TABLES ON `book_database`.* TO 'book_user'@'localhost' IDENTIFIED BY 'book_password';  

Mit dem book_user einloggen ->  

    mysql> --user  book_user --database book_database --password book_password;  

In die Datenbank "book_database" wechseln ->  

    mysql> use book_database;
 
Tabelle "books" erstellen ->

    mysql> CREATE TABLE books (id INT NOT NULL AUTO_INCREMENT, isbn VARCHAR(20), title VARCHAR(30), author VARCHAR(50), publicationDate VARCHAR(20), formatb VARCHAR(20), shortDescription VARCHAR(100), commentb VARCHAR(100), readb VARCHAR(10), PRIMARY KEY(id));

Einen Test-Datensatz anlegen ->

    mysql>INSERT INTO books VALUES (default, '978-3-8362-1802-3', 'Java ist auch eine Insel' , 'Ullenboom, Christian', 2012, 'Gebunden', 'Java-Lehrbuch', 'gut zu lesen', 'ja');


Eclipse Treiber Info einbinden ->

    neuen Folder im Projekt erstellen (z.B. TreiberLib)
    mysql-connector-java-xxx-bin.jar in diesen Ordner kopieren

Verweis erstellen ->

    Project - Properties - Java Build Path - Add JARs - mysql-connector-java-xxx-bin.jar ausw&auml;hlen
 
Wenn erfolgreich, ist mysql-connector-java-xxx-bin.jar dann im Ordner "Referenced Libraries" sichbar

-------------------------------------------------------------------

GIT REPOSITORY ERSTELLLEN UND DAMIT ARBEITEN

Auf der github-Website Repository erstellen
URL kopieren: https://github.com/Bergsocke/Book_Management.git


Git-Bash &ouml;ffnen 
zum Projektverzeichnis wechseln ->

    cd documents/java/wifi_projekt

Ordner initialisieren -> 

    git init;

.gitignor erstellen bzw. reinkopieren (im Windows-Explorer)
JavaProjektVerzeichnisse reinkopieren

in Git-Bash zum Java-Projekt wechseln -> 

    cd Book_Management

Status abfragen ->  

    git status;

Branches abfragen ->  

    git branch;

Website Repository clonen ->  

    git clone https://github.com/Bergsocke/Book_Management.git

Neue Branch "developer_branch" erstellen ->

    >git checkout -b developer_branch;

&Auml;nderungen kommentieren ->  

    git status;
    git add .;  (alle Dokumente im Ordner)
    git add <Dateiname>;
    git commit -m 'Hier wird Kommentartext eingetragen';

&Auml;nderungen im developer_branch mit master mergen ->   

    git checkout master;
    git merge developer_branch;

&Auml;nderungen zum Git Repository Website Developer_Branch pushen ->  

    git checkout developer_branch;
    git push origin developer_branch;

Auf der Website die �nderungen mit dem Master mergen  

&Auml;nderungen vom WebsiteMaster in den Master pullen ->  

    git pull origin master;

&Auml;nderungen dann mit der Developer_branch mergen ->  

    git checkout developer_branch;
    git merge master;

