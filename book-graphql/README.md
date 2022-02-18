# This is a variation on this example:

    https://www.graphql-java.com/tutorials/getting-started-with-spring-boot/

# You may install Cassandra by following the instructions provided here:

    https://www.how2shout.com/how-to/install-apache-cassandra-on-windows-10-8-7-without-datastax.html

(make sure you download Cassandra 3.11.4)
    http://www.apache.org/dyn/closer.lua/cassandra/3.11.4/apache-cassandra-3.11.4-bin.tar.gz

# Set these to your paths

set JAVA_HOME=C:\Users\Owner\Documents\Java\jdk1.8.0_92

cd C:\Users\Owner\apache-cassandra-3.11.4\bin

# Set up some data - This is for Cassandra - The SQL will need a bit of modification for other databases

C:\Users\Owner\apache-cassandra-3.11.4\bin>cqlsh

# Run these in cql

CREATE TABLE shahriar.book (
   id int,
   title text,
   title2 text,
   authorId int,
   isbn String,
   price double,
   currency text,
   format text
   PRIMARY KEY (id)
   );

Insert into shahriar.book (id, title, title2, authorId, isbn, price, currency, format) values (11111, "Alexandre Dumas", "", 100, "1234567", 9.99, "US Dollar", "Paperback");

Insert into shahriar.book (id, title, title2, authorId, isbn, price, currency, format) values (11112, "Around the World in 80 days", "", 101, "1234568", 9.99, "US Dollar", "Paperback");

Insert into shahriar.book (id, title, title2, authorId, isbn, price, currency, format) values (11113, "Renaissance Art", "", 102, "1234569", 29.99, "US Dollar", "Hard Cover");


CREATE TABLE shahriar.author (
   authorId,
   firstName text,
   lastName text,
   birthDate text,
   deceasedDate text,
   gender text,
   PRIMARY KEY(authorId)
   );

Insert into shahriar.book (authorId, firstName, lastName, birthDate, gender) values (100, "Alexandre", "Dumas", "24-JUL-1802", "05-DEC-1870", "M");

Insert into shahriar.book (authorId, firstName, lastName, birthDate, gender) values (101, "Jules", "Verne", "08-FEB-1828", "24-MAR-1905", "M");

Insert into shahriar.book (authorId, firstName, lastName, birthDate, gender) values (102, "Jane", "Smith", "01-JUL-1970", "", "F"); 


# Invoke the service:

$ curl http://localhost:8097/graphql/books -X POST -H "Content-Type: application/json" -d 'query {bookById(id: "11112") {title, isbn, price, currency, format, author{firstName lastName birthDate deceasedDate gender}}}'

# Expected result:

% Total    % Received % Xferd  Average Speed   Time    Time     Time  Current Dload  Upload   Total   Spent    Left  Speed
100   430    0   304  100   126   7238   3000 --:--:-- --:--:-- --:--:-- 10487{"errors":[],"data":{"bookById":{"title":"Around the World in 80 days","isbn":"1234568","price":"9.99","currency":"US Dollar","format":"Paperback","author":{"firstName":"Jules","lastName":"Verne","birthDate":"08-FEB-1828","deceasedDate":"24-MAR-1905","gender":"M"}}},"extensions":null,"dataPresent":true}



 