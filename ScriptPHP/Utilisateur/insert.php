<?php

require_once('connect.php');
require_once __DIR__ . '/db_connect.php';

$username=$_GET['username'];
$email=$_GET['email'];
$password=$_GET['password'];
$prenom=$_GET['prenom'];
$nom=$_GET['nom'];
$roles=$_GET['roles'];
$pass=$_GET['pass'];

$sql = "INSERT INTO user (username,email,password,prenom,nom,roles,pass)
VALUES ('$username','$email','$password','$prenom','$nom','$roles','$pass')";

if (mysqli_query($conn, $sql)) {
    echo "success";
} else {
    echo "Error: " . $sql . "<br>" . mysqli_error($conn);
}
mysqli_close($conn);
?>