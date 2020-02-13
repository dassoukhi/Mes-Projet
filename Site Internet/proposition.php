<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
		<script type='text/javascript' src='jquery-3.4.1.min.js'></script>
		<link rel='stylesheet' type='text/css' href='jquery-ui-1.12.1/jquery-ui.min.css'>
		<script src='https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v6.0.1/build/ol.js'></script>
	</head>

	<body class="flou">
		<?php 
		include('header.php');

		 if (isset($_GET["click"])) {
		  $idEvent = $_GET["click"];
		  }
		  else{
		  	header("location:index.php");
		  }

		  $dbh = connexionPDO('event');


		  $rqt = 'select * from evenement where ID_E ='.$idEvent;

		 
			$resultat = $dbh->query($rqt);



	foreach ($resultat as $value) {
		# code...
		
		  echo "<style>.flou:before{background: url('./IMAGE/".$value['CHEMIN_IMAGE']."');}</style>
		  <div class='cadre mx-auto bg-white shadow mb-5'>
			<div class='container mw-100 border-bottom border-dark'>
				<div class='row'>
					<div class='col-sm-8 p-0' style='height: 450px;'>
						<img src='./IMAGE/".$value['CHEMIN_IMAGE']."' width='100%' height='100%'>
					</div>
					<div class='col-sm-4 p-5'>
					".jourDeEvent($value['DATE_D'])." <br/><br/>
						<h3>".$value['NOM_E']."</h3>
						".strtoupper($value['THEME'])."<br/><br/>
						Âge Minimum : ".$value['AGE_MIN']."<br/><br/>
						Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
					</div>
				</div>
			</div>

			<div class='p-2 container mw-100 border-bottom border-dark'>
				<div class='row'>
					<div class='col'>
						&#9825;
					</div>
					<div class='col-md-auto'>
						<form method='POST'>
							<button type='submit' class='btn btn-success' name='accepter'>Accepter l'evenement</button>
							<button type='submit' class='btn btn-danger' name='rejetter'>Rejetter l'evenement</button>
						</form>
					</div>
				</div>
			</div>

			<div class='p-3'>
				<h6>Localisation :</h6> ".$value['ADRESSE']."
			</div>
			<div class='p-3'>
				<h6>Description :</h6>
				".$value['DESCRIPTION']."
				<br/><br/>
			</div>

			<div id='map' class='map' style='width: 100%;'></div>
			<img id='marker' src='marker3.png' width='50' height='50' onClick='switchMarker();'>
			<div class='bg-white ml-5 p-2  border rounded' id='popup' style='display: none; color: green; width: 100px;'>".$value['ADRESSE']."</div>

			

		</div>
		<script type='text/javascript'>
			var map = new ol.Map({
				target: 'map',
				layers: [new ol.layer.Tile({source: new ol.source.OSM()})],
				view: new ol.View({
					center: ol.proj.fromLonLat([".$value['LONGITUDE'].",".$value['LATITUDE']."]),
					zoom: 16
				})
			});

			var marker = document.getElementById('marker');
			map.addOverlay(new ol.Overlay({
				position: ol.proj.fromLonLat([".$value['LONGITUDE'].",".$value['LATITUDE']."]),
				element: marker
			}));

			var popup = document.getElementById('popup');
			map.addOverlay(new ol.Overlay({
				offset: [0,-35],
				position: ol.proj.fromLonLat([".$value['LONGITUDE'].",".$value['LATITUDE']."]),
				element: popup
			}));
			function switchMarker() { (popup.style.display == 'none' ? popup.style.display = 'block' : popup.style.display = 'none') };
		</script>";
	}


		if (isset($_POST['accepter'])) {
			
			$rqt = "UPDATE evenement SET VISIBLE = 1 where ID_E =".$idEvent;

			$dbh->query($rqt);

			echo "<script type='text/javascript'>document.location.replace('listeProposition.php');</script>";
		}


		if (isset($_POST['rejetter'])) {
			
			$rqt = "DELETE FROM evenement where ID_E =".$idEvent;

			$dbh->query($rqt);

			echo "<script type='text/javascript'>document.location.replace('listeProposition.php');</script>";
		}
		?>
		
		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>