<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
require 'database.class.php';

class hsmUsers extends hsmDatabase{
    
    public function __construct($host, $database, $user, $password) {
        parent::__construct($host, $database, $user, $password);
    }
    
    public function setUsers($valorInserido){
        try{
            $sql = ("INSERT INTO users (user_id,name,link,username) VALUES (?,?,?,?)");
            $query = $this->pdo->prepare($sql);
            $query->bindValue(1,$valorInserido[0] );
            $query->bindValue(2,$valorInserido[1] );
            $query->bindValue(3,$valorInserido[2] );
            $query->bindValue(4,$valorInserido[3] );
            $query->execute();            
        }catch (PDOException $e){
            echo $e->getMessage();
        }
    }

    public function getUsers(){
        try{
            $query = $this->pdo->query("SELECT * FROM users");

            foreach ($query as $row) {
                $output = 'User_id: '. $row['user_id']. "<br>";
                $output .= 'login: '. $row['login']. "<br>";
                $output .= "----------------------------------<br>";

                echo $output;
            }
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }
}

?>
