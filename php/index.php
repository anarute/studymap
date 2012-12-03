<?php
/*
 * file: facebook_login.php
 * 
 * Description: Make Facebook login with Facebook SDK
 * 
 * Author: Joao Santos
 */

require_once ("nbproject/facebook-php-sdk/src/facebook.php");

$config = array();

$config['appID'] = "173457916129382";
$config['secret'] = "c6cd4dfffdc3cfd8e7ab17ead66002a8";
$config['fileUpload'] = true;

$facebook = new Facebook($config);

$user_id = $facebook->getUser();

if($user_id) {

      try {

        $user_profile = $facebook->api('/me','GET');
        echo "Get User ID:" . $user_id;
        echo "Name: " . $user_profile['name'];

      } catch(FacebookApiException $e) {
        // If the user is logged out, you can have a 
        // user ID even though the access token is invalid.
        // In this case, we'll get an exception, so we'll
        // just ask the user to login again here.
        $login_url = $facebook->getLoginUrl(); 
        echo 'Please <a href="' . $login_url . '">login.</a>';
        error_log($e->getType());
        error_log($e->getMessage());
      }   
    } else {

      // No user, print a link for the user to login
      $login_url = $facebook->getLoginUrl();
      echo 'Please <a href="' . $login_url . '">login.</a>';

    }




?>

