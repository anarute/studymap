<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    require 'users.class.php';

    $banco = new hsmUsers("localhost", "study_map", "root", "");
    $banco->connect();
    $banco->getUsers();
    $valor = "12,login,email";
    $banco->setUsers($valor);
    
    
?>
