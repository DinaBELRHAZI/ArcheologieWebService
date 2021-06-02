<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?key="></script>
    <script src= "https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"> </script>
<script>
var map;
var myCenter = new google.maps.LatLng(41.9, 12.5);
var infoWindow = new google.maps.InfoWindow();

function initialize() {
	
    var mapProp = {
        center: myCenter,
        zoom: 12,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
    
    var myLatlng = new google.maps.LatLng(41.9, 12.5);
    var marker = new google.maps.Marker({
        position: myLatlng,
        map: map,
        title:"centre"
    });
    }
function addinfo( item_info )
{
	var div = document.getElementById('infoMap');
    //alert(item_info)
	div.innerHTML += "<button >"+item_info+"</button>";
}

$(document).ready(function() { 
    alert("jquery ok");
   
    $("#fetch").click(function(event){ 
    alert("jquery click  ok");
	    $.getJSON('http://localhost:8086/JerseyArcheo/webresources/sites/', function(Json) { 
	       
	        alert("retour ok"); 
	        //alert( tabmonument["liste des monuments"])
	        jsonvar=Json["liste des sites archéologiques"];
	        
	        for (var i = 0; i < jsonvar.length; i++) //alert(jsonvar[i].Nom_du_site );
	        afficheMarker(jsonvar);
	
		});
   });
});
function afficheMarker( jsonvar )
{
  
  for (var i = 0; i < jsonvar.length; i++) {
	  
	  var NomDuSite = jsonvar[i].Nom_du_site;
      var num = jsonvar[i].id;
  
      //Création d'un tableau regroupant tous les markers
      var markers = [];
  
      // Déclaration des coordonnées
      newPoint1 = new google.maps.LatLng(46.20, 2.349903);
      //alert(newPoint1);
      // Déclaration des paramètres récupérer du tableau
      var commune = jsonvar[i].Commune;
      var departement = jsonvar[i].Departement;
      var periodes = jsonvar[i].Periodes;
      var region = jsonvar[i].Region;
          // Création d'un marker
          var marker = new google.maps.Marker({
              position: newPoint1,
              title: periodes,
              map: map
          });
          // enregistrement dans le tableau des markers
         markers.push(marker);
    // InfoWindow
      (function (marker, commune1, departement1, periodes1, region1,NomDuSite1,num1, pointrecu) {
          google.maps.event.addListener(marker, "click", function (e) {
              //Contenu de l'infoWindow
              infoWindow.setContent(
                  "<div class='pano1' >"
                  //+"<div id='pano"+commune+"' class='panophoto' > </div>"
                  +'<a><b>Nom du site : </b></a>'
                  +'<a>'+NomDuSite1+'</a>'
                  +'<hr>'
                  +'<a><b>Adresse : </b></a>'
                  +'<a>'+region1+'</a>'
                  +'<a>'+departement1+', '+commune1+'</a>'
                  +'<hr>'
                  +'<a><b>Période : </b></a>'
                  +'<a>'+periodes1+'</a>'
                  +'<hr>'
                  + "<a class='btn btn-outline-primary' id='btn_plus' href='/france/liste/site/"+num1+"' >Détails </a>"
                  +"</div>"
              );
              //ouveture de l'infoWindow avec paramètres : le nom de la carte et le marker
              infoWindow.open(map, marker);
              //appel de la fonction streetView + paramètre pointrecu (newPoint1)
              streetView(pointrecu);
          });
          //Variables déclarées plus haut mis en paramètre
      }) (marker, commune, departement, periodes, region,NomDuSite,num, newPoint1)
	}

  	
}

google.maps.event.addDomListener(window, 'load', initialize);
</script>
<style>
button {
background-color: #555555;
              width: 200px;
              color:lime;
              height:60px;
} /* Black */			 
 
#googleMap {position:absolute;
            top: 10px;
            left:20px; 
             width:650px;
             height: 500px}
 #fetch {position:absolute;
            top: 560px;
            left:20px; 
             width:250px;
             height: 100px}
 
#infoMap{  position:absolute;top: 10px;
             left:700px; 
             width:200px;
             height: 500px  ;background-color: aqua;         }
</style>
</head>
<body>
 
<div id="googleMap"></div>
<div id="infoMap"></div> 
<div>
<input type = "button" id = "fetch" value = "Fetch markers Monmuments" /></div>
</body>
</html>
         

