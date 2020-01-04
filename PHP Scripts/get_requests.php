<?php
    require "init.php";
    $city = $_POST["city"];
    $sql = "SELECT * from posts where number in (SELECT number from user_table WHERE city = '$city')";
    $result = mysqli_query($con,$sql);
    $response = array();
    while($row = mysqli_fetch_assoc($result)){
        array_push($response, $row);
    }
    echo json_encode($response);
?>