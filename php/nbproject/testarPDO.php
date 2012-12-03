<?php

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    require 'study_areas.class.php';

    $banco = new hsmStudyArea("localhost", "study_map", "root", "");
    $banco->connect();
    $banco->getStudyArea();
    $banco->setStudyArea("Yoga");
    
    
?>
