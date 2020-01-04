<?php
require "init.php";
$target_dir = "uploads/";
$target_file_name = $target_dir . basename($_FILES["file"]["name"]);
$response = array();
if (isset($_FILES["file"]))
{
    if (move_uploaded_file($_FILES["file"]["tmp_name"], $target_file_name))
    {
        $url = "https://busy-programmer.000webhostapp.com/".$target_file_name;
        
        $m = $_GET["message"];
        $number = $_GET["number"];
        $sql = "INSERT INTO `posts` (`id`, `message`, `url`, `number`) VALUES (NULL, '$m', '$url', '$number');";
        mysqli_query($con, $sql);
        $success = true;
        $message = "Uploaded!!!";
    }
    else
    {
        $success = false;
        $message = "NOT Uploaded!!! _ Error While Uploading";
    }
}
else
{
    $success = false;
    $message = "missing field";
}
$response["success"] = $success;
$response["message"] = $message;
echo json_encode($response);
?>