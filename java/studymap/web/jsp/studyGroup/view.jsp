<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ctrl" class="hackathom.studymap.jsp.controller.StudyGroupController" scope="request"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
        <link href="../../css/main.css" media="screen" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyC1oxalNtwVwxEgQ1LW5mwE9xRtF2NCwBo&sensor=true"></script>
        <script type="text/javascript" src="../../js/map.js"></script>
    </head>
    <body>
        <div id="map_canvas" style="width:100%; height:100%"></div>
    </body>
    <script type="text/javascript">
var map;
var SaoPaulo = new google.maps.LatLng(-23.5506,-46.6333);

function initialize() {
  var mapOptions = {
    center: SaoPaulo,
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

  <c:forEach var="it" items="${requestScope.ctrl.list}">
    var image = '../../img/books.png';
    var bookMarker = new google.maps.Marker({
      position: new google.maps.LatLng(${it.longitude},${it.latitude}),
      map: map,
      icon: image,
      title: 'Grupo de Estudos'
    });

    google.maps.event.addListener(bookMarker, 'click', function() {
        window.location = "<c:url value="/jsp/studyGroup/detail.jsp?studyGroupId="/>${it.studyGroupId}"
    });
  </c:forEach>

  // Try HTML5 geolocation
  if(navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function(position) {
      var pos = new google.maps.LatLng(position.coords.latitude,
                                       position.coords.longitude);

      var marker = new google.maps.Marker({
        map: map,
        position: pos,
        title: 'Você está aqui'
      });
      map.setCenter(pos);
      map.setZoom(16);

    }, function() {
      handleNoGeolocation(true);
    });
  } else {
    // Browser doesn't support Geolocation
    handleNoGeolocation(false);
  }
}

function handleNoGeolocation(errorFlag) {
  if (errorFlag) {
    //var content = 'Error: The Geolocation service failed.';
    var content = 'Erro: O serviço de geolocalização falhou.';
  } else {
    //var content = 'Error: Your browser doesn\'t support geolocation.';
    var content = 'Erro: Seu navegador não suporta geolocalização.';
  }

  var options = {
    map: map,
    position: SaoPaulo,
    content: content
  };

  alert(content);
  //var infowindow = new google.maps.InfoWindow(options);
  map.setCenter(options.position);
}

google.maps.event.addDomListener(window, 'load', initialize);
    /*Try to put a first group
    var contentString = '<div id="content">'+
      '<div id="siteNotice">'+
      '</div>'+
      '<h1 id="firstHeading" class="firstHeading">Nosso 1º StudyGroup</h1>'+
      '<div id="bodyContent">'+
      '<p>Description</p>'+
      '</div>'+
      '</div>';

    var infowindow = new google.maps.InfoWindow({
        content: contentString,
        maxWidth: 300
    });*/

      var bookMarker = new google.maps.Marker({
      position: SaoPaulo,
      map: map,
      icon: image,
      title: 'Grupo de Estudos'
    });


    /*google.maps.event.addListener(bookMarker, 'click', function() {
      infowindow.open(map,bookMarker);
    });*/
    </script>
</html>