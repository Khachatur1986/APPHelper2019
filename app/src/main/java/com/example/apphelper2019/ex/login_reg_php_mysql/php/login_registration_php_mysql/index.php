<?php

require_once 'user.php';
require_once 'Logger.php';

$username = "";

$password = "";

$email = "";

if (isset($_POST['username'])) {
    $username = $_POST['username'];
}

if (isset($_POST['password'])) {
    $password = $_POST['password'];
}

if (isset($_POST['email'])) {
    $email = $_POST['email'];
}
//$loger = new Logger();
//$loger->logAll();

$userObject = new User();

//$jsonData = file_get_contents('php://input');
//if ($jsonData != null) {
//    $jsonArray = json_decode($jsonData, true); //Use the second parameter of json_decode to make it return an array:
//    if (count($jsonArray) > 0) {
//        if (isset($jsonArray['username'])) {
//            $username = $jsonArray['username'];
//        }
//
//        if (isset($jsonArray['password'])) {
//            $password = $jsonArray['password'];
//        }
//
//        if (isset($jsonArray['email'])) {
//            $email = $jsonArray['email'];
//        }
//    }
//}

// Registration

if (!empty($username) && !empty($password) && !empty($email)) {
    $hashed_password = md5($password);

    $json_registration = $userObject->createNewRegisterUser($username, $hashed_password, $email);

    echo json_encode($json_registration);
}

// Login

if (!empty($username) && !empty($password) && empty($email)) {
    $hashed_password = md5($password);

    $json_array = $userObject->loginUsers($username, $hashed_password);

    echo json_encode($json_array);
}
?>