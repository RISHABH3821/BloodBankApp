<?php
    $host = "localhost"; //on same server we keep localhost
    $user = "YOUR_USER_ID_HERE";  //username of the database
    $pass = "YOUR_PASSWORD_HERE";   //password of the database
    $db = "id10533884_blood_bank";  //name of database
    
    $con = mysqli_connect($host,$user,$pass,$db);
    
    if($con){
        //echo "Connected to Database";
    }else{
        //echo "Failed to connect ".mysqli_connect_error();
    }
?>