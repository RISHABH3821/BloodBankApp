<?php
    require "init.php";
    
    $number = $_POST["number"];
    $password = $_POST["password"];
    
    $sql = "SELECT * FROM user_table WHERE number = '$number' and password = '$password'";
    
    $result = mysqli_query($con,$sql);
    
    if(mysqli_num_rows($result)>0){
        $rows = mysqli_fetch_assoc($result);
        echo $rows["city"];
    }else{
        echo "Invalid Credentials";
    }
    
?>