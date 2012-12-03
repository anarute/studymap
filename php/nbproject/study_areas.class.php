<?php
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
require 'database.class.php';

class hsmStudyArea extends hsmDatabase{
    
    public function __construct($host, $database, $user, $password) {
        parent::__construct($host, $database, $user, $password);
    }
    
    public function setStudyArea($valorInserido){
        try{
            $sql = ("INSERT INTO study_area (description) VALUES ".$valorInserido);
            $query = $this->pdo->prepare($sql);
            $query->execute();

            $output = "------------------------------------<br>";
            $output .= "Valor Inserido = " . $valorInserido;
            $output .= "------------------------------------<br>";
            
        }catch (PDOException $e){
            echo $e->getMessage();
        }
    }

    public function getStudyArea(){
        try{
            $query = $this->pdo->query("SELECT * FROM study_area");

            foreach ($query as $row) {
                $output = 'Study Area: '. $row['study_area_id']. "<br>";
                $output .= 'Description: '. $row['description']. "<br>";
                $output .= "----------------------------------<br>";

                echo $output;
            }
        } catch (PDOException $e) {
            echo $e->getMessage();
        }
    }
}

?>
