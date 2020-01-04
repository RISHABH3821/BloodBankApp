<?php
    require 'init.php';
    $name = $_POST["name"];
    $city = $_POST["city"];
    $blood_group = $_POST["blood_group"];
    $password = $_POST["password"];
    $number = $_POST["number"];
  
   
    
    $sql = "INSERT INTO user_table (name, city, blood_group, password, number) VALUES('$name', '$city', '$blood_group', '$password', '$number')";
    
    $result = mysqli_query($con, $sql);
    if($result){
        echo "Success";
    }else{
        echo "Error: ".mysqli_error($con);
    }
    mysqli_close($con);
?>