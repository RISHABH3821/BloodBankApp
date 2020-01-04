<?php
    require "init.php";
    $city = $_POST["city"];
    $blood_group = $_POST["blood_group"];
    $sql = "Select name, number, city from user_table WHERE blood_group LIKE '$blood_group' AND city LIKE '%$city%'";
    $result = mysqli_query($con, $sql);
    $response = array();
    while($row = mysqli_fetch_assoc($result)){
        array_push($response, $row);
    }
    echo json_encode($response);
    
?>