<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once __DIR__ . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET['uid'])) {
    $pid = $_GET['uid'];

    // get a login from memberFamily table
    $result = mysql_query("SELECT * FROM user WHERE username = $pid ");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $login = array();
        $login["username"] = $row["username"];
        $login["password"] = $row["password"];
        $login["email"] = $row["email"];
		$login["nom"] = $row["nom"];
		$login["prenom"] = $row["prenom"];
		$login["id"] = $row["id"];

        // push single login into final response array
        array_push($response["info"], $login);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
        } 
		else {
            // no user found
			$response["info"] = array();
            $response["success"] = 0;
            $response["message"] = "Aucun utilisateur trouvé";

            // echo no users JSON
            echo json_encode($response);
        }
  
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Champs vide";

    // echoing JSON response
    echo json_encode($response);
}
?>