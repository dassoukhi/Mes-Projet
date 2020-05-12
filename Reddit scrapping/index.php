<!DOCTYPE html>
<html>

	<?php
		include("head.php");
	?>
	<script type="text/javascript">
		function reportImage(index) 
	    {
	    	var obj="";
	    	$.getJSON("fusion.json",function(json){
	    		obj=json;
	    		
	    	
	    	
	    	obj[index]["test"]=false; 
	    	console.log(obj[index]["test"]);
	    	$.post("fus.json", {json : JSON.stringify(obj)});
	    	});
	    }
	</script>

	<body>

		<?php
			include("header.php");
		?>

		<div class='container-fluid'>
			<div class='row pt-1' >
				<div class='mx-auto mb-0 col-md-9 px-3'>
					<div id='map' style='width: 100%; height: 600px;'></div>
				</div>

				<div class='col-md-3 px-3 bg-secondary text-light overflow-auto'style='max-height: 600px'>
					<div>
						<a id='img' href='https://www.cartograf.fr/images/map/monde-satellites/carte_monde_satellite_topographie.jpg' target='_blank'>
							<img class='pt-2 mb-3' style='width: 100%; height: auto;' src='https://www.cartograf.fr/images/map/monde-satellites/carte_monde_satellite_topographie.jpg'>
						</a>
					</div>
					<div id='title'>
						<h5><u>Description du post Reddit :</u></h5>
						<p id='t'></p>
					</div>
					<div id='afterClean'>
						<h5><u>Nettoyage de la description :</u></h5>
						<p id='a'></p>
					</div>
					<div  id='location'>
						<h5><u>Combinaison trouvant une solution dans Geoname :</u></h5>
						<p id='l'></p>
					</div>
					<div id='bouton'>
						<p id="b"></p>
					</div>
				</div>
			</div>
		</div>
		
		<?php
			if (isset($_POST['report'])){

				if (isset($_POST['index'])){

				echo "<script>console.log('".$_POST["index"]."')</script>";
				
				$index=$_POST["index"];

				$json = file_get_contents('./fusion.json');

				//Decode JSON
				$data = json_decode($json,true);

				
				$data[$index]["test"]=false;

				$newdata = json_encode($data);
				file_put_contents('fusion.json', $newdata);

				
			}
			else echo "<script>console.log('nope');</script>";	
		}
		?>	


		<?php
		include ('footer.php');
		include ('script.php');
		?>
		
	</body>

	<script type='text/javascript'>
		var map = L.map('map').setView([35.505, -0.09], 2);
		map.options.minZoom = 2;

		L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
			attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
		}).addTo(map);

	    $().ready(function(){
	    	var markers = L.markerClusterGroup(); //on crée le cluster

	    	$.getJSON("fusion.json",function(data){
	    		$.each(data,function(index,objet){
	    			if (objet.test==true){
		    			var m = L.marker([objet.latitude, objet.longitude])
		    			.bindPopup(objet.afterClean)
		    			.on('click', function(){
		    				$('#img').replaceWith("<a id='img' href='"+objet.url+"' target='_blank'><img class='pt-2 mb-3' style='width: 100%; height: 200px;' src='"+objet.url+"'></a>");
		    				$('#t').replaceWith("<p id='t'>"+objet.title+"</p>");
	    					$('#a').replaceWith("<p id='a'>"+objet.afterClean+"</p>");
	    					$('#l').replaceWith("<p id='l'>"+objet.location+"</p>");
	    					$('#b').replaceWith("<form method='POST'> <input type='hidden' name='index' value='"+index+"'><button name='report' type='submit' class='btn btn-warning'>Localisation incorrecte</button></form>");
		    			});
		    				markers.addLayer(m); //on ajoute chaque marqueur au cluster 
	    			}
	    		});
	    		map.addLayer(markers); //on ajoute tout ça à la map
	    	});
	    });

	    /*console.log(document.getElementById('title').value);*/




	</script>


</html>




<!-- onclick='reportImage("+index+") -->