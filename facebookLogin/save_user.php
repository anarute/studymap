<?php
	require 'users.class.php';

	$userid = $_POST['useridPost'];
	$name = $_POST['namePost'];
	$link = $_POST['linkPost'];
	$username = $_POST['usernamePost']

	if (!($userid) || !($name) || !($link) || !($username)){
    	print "Preencha todos os campos!"; exit();
	}

	//Utilizando o  mysql_real_escape_string voce se protege o seu código contra SQL Injection.
	$userid = mysql_real_escape_string($nome);
	$name = mysql_real_escape_string($email);
	$link = mysql_real_escape_string($telefone);
	$username = mysql_real_escape_string($username);

    $banco = new hsmUsers("localhost", "study_map", "root", "");
    $banco->connect();
    $banco->getUsers();
    $valor = $userid . "," . $name . "," . $link . "," . $username;
    $banco->setUsers($valor);
    $banco->close();

?>