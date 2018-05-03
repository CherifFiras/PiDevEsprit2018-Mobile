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

    // get a login from memberFamily table
    $result = mysql_query("SELECT * FROM recette r , categorie_Rec cr where r.idCatRec=cr.idCatRec");

    if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["info"] = array();
    
    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $recette = array();
        $recette["nomRec"] = $row["nomRec"];
		$recette["nomCatRec"] = $row["nomCatRec"];
		$recette["idCatRec"] = $row["idCatRec"];
		$recette["imageRec"] = $row["imageRec"];
		$recette["idRec"] = $row["idRec"];
        // push single login into final response array
        array_push($response["info"], $recette);
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
  

?>