<?php
// AWS RDS DB Config
$host = 'dbs.ca72musu828d.us-east-1.rds.amazonaws.com';
$dbname = 'dbs';
$username = 'admin';
$password = 'PeTT5Ulaew7w62zAA5Rc';
$port = 3306;

// Create connection
$conn = new mysqli($host, $username, $password, $dbname, $port);

// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
echo " Connected successfully<br>";

// Create Table (if not exists)
$conn->query("CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50),
    email VARCHAR(50)
)");

//  CREATE (Insert)
if ($conn->query("INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com')") === TRUE) {
    echo " New record created successfully<br>";
} else {
    echo " Error inserting: " . $conn->error . "<br>";
}

// READ (Select)
$result = $conn->query("SELECT * FROM users");
echo "<h3>Users:</h3>";
while ($row = $result->fetch_assoc()) {
    echo " ID: {$row['id']} | Name: {$row['name']} | Email: {$row['email']}<br>";
}

//  UPDATE
if ($conn->query("UPDATE users SET email='alice@updated.com' WHERE name='Alice'") === TRUE) {
    echo " Record updated successfully<br>";
} else {
    echo " Error updating: " . $conn->error . "<br>";
}

//  DELETE
if ($conn->query("DELETE FROM users WHERE name='Alice'") === TRUE) {
    echo " Record deleted successfully<br>";
} else {
    echo " Error deleting: " . $conn->error . "<br>";
}

$conn->close();
?>

