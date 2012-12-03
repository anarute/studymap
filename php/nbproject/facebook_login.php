<?php
/*
 * file: facebook_login.php
 * 
 * Description: Make Facebook login with Facebook SDK
 * 
 * Author: Joao Santos
 */

require_once ("facebook-php-sdk/src/facebook.php");

$config = array();

$config['appID'] = "173457916129382";
$config['secret'] = "c6cd4dfffdc3cfd8e7ab17ead66002a8";
$config['fileUpload'] = true;

$facebook = new Facebook($config);

$user = $facebook->getUser();

echo "Get User ID:" . $user;


?>
