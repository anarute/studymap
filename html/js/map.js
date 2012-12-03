var map;
var SaoPaulo = new google.maps.LatLng(-23.5506,-46.6333);

function initialize() {
  var mapOptions = {
    center: SaoPaulo,
    zoom: 8,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };
  map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);

  //Try to put a first group
  var contentString = '<div id="content">'+
    '<div id="siteNotice">'+
    '</div>'+
    '<h1 id="firstHeading" class="firstHeading">Nosso 1º StudyGroup</h1>'+
    '<div id="bodyContent">'+
    '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
    'sandstone rock formation in the southern part of the '+
    'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
    'south west of the nearest large town, Alice Springs; 450&#160;km '+
    '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
    'features of the Uluru - Kata Tjuta National Park. Uluru is '+
    'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
    'Aboriginal people of the area. It has many springs, waterholes, '+
    'rock caves and ancient paintings. Uluru is listed as a World '+
    'Heritage Site.</p>'+
    '<p>Attribution: Uluru, <a href="http://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
    'http://en.wikipedia.org/w/index.php?title=Uluru</a> '+
    '(last visited June 22, 2009).</p>'+
    '</div>'+
    '</div>';

  var infowindow = new google.maps.InfoWindow({
      content: contentString,
      maxWidth: 300
  });

  var image = './img/books.png';
  var bookMarker = new google.maps.Marker({
    position: SaoPaulo,
    map: map,
    icon: image,
    title: 'First StudyGroup'
  });

  google.maps.event.addListener(bookMarker, 'click', function() {
    infowindow.open(map,bookMarker);
  });

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