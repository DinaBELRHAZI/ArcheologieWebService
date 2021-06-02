<!DOCTYPE html>
<html>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js" integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0=" crossorigin="anonymous"></script>
    <meta charset="utf-8">
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <!-- <link href="{{asset('css/maps.css')}}" rel="stylesheet" media="all"> -->
    <link type="text/css" rel="stylesheet" href="css/maps.css"/>
 <!--    <script src="{{asset('js/markerclusterer.js')}}" type="text/javascript"></script> -->
    <script src="js/markerclusterer.js"></script>

</head>

<body>


<!-- Header
    - titre
    - boutton de retour � la liste
    - div streetView
 -->
<div class='header'>
    <p class='text-center'>Carte</p>
</div>


<div id="map">
    <!-- Ici s'affichera la carte -->
</div>
</body>
<!-- <script src="{{asset('js/APIKey.js')}}" type="text/javascript"></script> -->
<script src="js/APIKey.js"></script>


    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>

    <!-- Mettez votre cl� API ici -->
    <script id="KEYApi" ></script>
    <script>
        var extra = localStorage.getItem("maClePerso");
        var url = "https://maps.google.com/maps/api/js?key=";
        document.getElementById('KEYApi').src = url + extra;
    </script>
    
<!-- Carte -->

    <script async type="text/javascript">
        // On initialise la latitude et la longitude de Paris (centre de la carte)
        var lat = 46.20;
        var lon = 2.349903;
        var map = null;
        // Fonction d'initialisation de la carte
        function initMap() {
            // Cr�er l'objet "map" et l'ins�rer dans l'�l�ment HTML qui a l'ID "map"
            map = new google.maps.Map(document.getElementById("map"), {
                // Nous pla�ons le centre de la carte avec les coordonn�es ci-dessus
                center: new google.maps.LatLng(lat, lon),
                // Nous d�finissons le zoom par d�faut
                zoom: 6,
                // Nous d�finissons le type de carte (ici carte routi�re)
                mapTypeId: google.maps.MapTypeId.ROADMAP,
                // Nous activons les options de contr�le de la carte (plan, satellite...)
                mapTypeControl: true,
                // Nous d�sactivons la roulette de souris
                scrollwheel: false,
                mapTypeControlOptions: {
                    // Cette option sert � d�finir comment les options se placent
                    style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR
                },
                // Activation des options de navigation dans la carte (zoom...)
                navigationControl: true,
                navigationControlOptions: {
                    // Comment ces options doivent-elles s'afficher
                    style: google.maps.NavigationControlStyle.ZOOM_PAN
                }
            });
            
            
            // D�claration de la infoWindow
            var infoWindow = new google.maps.InfoWindow();
            
            
            /* Appel du webservice  */
             $(document).ready(function() { 
                
            	    $.getJSON('http://localhost:8086/JerseyArcheo/webresources/sites/', function(Json) { 
            	       
            	        alert("retour ok"); 
            	        jsonvar=Json["liste des sites arch�ologiques"];
            	        
            	        afficheMarker(jsonvar);
            		});
            }); 
            
         	function afficheMarker( jsonvar ) {      
            	  // boucle sur la liste jsonvar
		          for (var i = 0; i < jsonvar.length; i++) {
		            
		        	  var NomDuSite = jsonvar[i].Nom_du_site;
		              var num = jsonvar[i].ID;
		            
			          //Cr�ation d'un tableau regroupant tous les markers
			          var markers = [];
		            
		              // D�claration des coordonn�es
		              newPoint1 = new google.maps.LatLng(jsonvar[i].Lambert_X, jsonvar[i].Lambert_Y);
		              //alert(newPoint1);
		              console.log("Coordonn�es : "+newPoint1);
		              console.log("ID = "+ jsonvar[i].ID)
		              
		              // D�claration des param�tres r�cup�rer du tableau
		              var commune = jsonvar[i].Commune;
					  var departement = jsonvar[i].Departement;
					  var periodes = jsonvar[i].Periodes;
					  var region = jsonvar[i].Region;
					  
		              // Cr�ation d'un marker
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
		                            +'<a><b>P�riode : </b></a>'
		                            +'<a>'+periodes1+'</a>'
		                            +"</div>"
		                        );
		                        //ouveture de l'infoWindow avec param�tres : le nom de la carte et le marker
		                        infoWindow.open(map, marker);
		                    });
		                 //Variables d�clar�es plus haut mis en param�tre
		              }) (marker, commune, departement, periodes, region,NomDuSite,num, newPoint1)
		            
		          }// Fin de la boucle For
		          
		          var markerCluster = new MarkerClusterer(map, markers, {
		              imagePath: "https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/m"
		          });
            
        	}/* Fin afficheMarker */      
        }// FIN initMap
        
        window.onload = function(){
            // Fonction d'initialisation qui s'ex�cute lorsque le DOM est charg�
            initMap();
        };
    </script>

</html>