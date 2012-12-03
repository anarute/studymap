<?php
/*
 *file: database.class.php
 * 
 *Description: Class for database conection and operation
 * 
 * Author: Joao Santos
 */

abstract class hsmDatabase{
    
    public $host;
    public $database;
    public $user;
    public $password;
    public $dsn;
    public $pdo;
    
    public function __construct($host,$database,$user,$password) {
        $this->host = $host;
        $this->database = $database;
        $this->user = $user;
        $this->password = $password;
        $this->dsn = 'mysql:host='.$host.';dbname='.$database;
        $this->connect();
    }
    
    public function connect(){
    
        try {
            $this->pdo = new PDO($this->dsn, $this->user, $this->password);
        } catch (PDOException $e){
            echo $e->getMessage();
        }
    }
    
    public function close(){
        $this->pdo = null;
    }
}
 
?>
