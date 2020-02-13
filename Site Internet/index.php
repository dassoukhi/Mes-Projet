<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>
	

	<body>
		<?php include('header.php'); ?>
		<?php include('carousel.php');?>


		<?php
			echo "
			<div class='container mx-auto px-auto'>
				<div class='row'>";
					$dbh = connexionPDO('event');
					$rqt = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE  from evenement where VISIBLE=1  and EVENT_PASSE(ID_E) = 0 order by DATE_D LIMIT 8";
					$resultat = $dbh->query($rqt);
					foreach ($resultat as $value) 
					{
						echo "
						
						<div class='col-sm-4 col-md-3 p-3 mx-0'>
							<a class='lienEvent' href='event.php?click=".$value['ID_E']."'>

								<div class='w-100' style='height:75%'>
									<img src='./IMAGE/".$value['CHEMIN_IMAGE']."' style='width: 100%; object-fit:cover; height:100%'>
								</div>

								<div class='w-100 p-2'>
									".$value['NOM_E']."
								</div>
							</a>
						</div>
						";
					}
					echo "
				</div>
			</div>";

			if ($resultat->rowCount() >= 9) {
				echo "

			<div class='text-center py-3'>
				<a id='more' class='px-5' href='listeEvent.php'>plus d'evenements
				</a>
			</div>";
			}
		?>

		<div class="cadre mx-auto">
			<div>

				<div id='map' class='map' style='width: 100%;'></div>

			</div>
		</div>

		<script>
			var map = new ol.Map({
				target: 'map',
				layers: [new ol.layer.Tile({source: new ol.source.OSM()})],
				view: new ol.View({
					center: ol.proj.fromLonLat([3.876716,43.610769]),
					zoom: 13
				})
			});
		</script>

	<?php
		$rqt2 = "select ID_E, NOM_E, ADRESSE, LONGITUDE, LATITUDE from evenement where VISIBLE=1  and EVENT_PASSE(ID_E) = 0 order by DATE_D ";
		$resultat = $dbh->query($rqt2);

		foreach ($resultat as $value) 
		{
		echo "
		<img id='marker".$value['ID_E']."' src='marker3.png' width='50' height='50' onmouseenter=\"switchMarker('popup".$value['ID_E']."')\" onmouseleave=\"switchMarker('popup".$value['ID_E']."')\"  onclick=\"goEvent('".$value['ID_E']."')\">
		<div class='bg-white ml-5 p-2  border rounded' id='popup".$value['ID_E']."' style='display: none; color: green; width: 100px;'>".$value['NOM_E']."</div>
		<script>
			var marker".$value['ID_E']." = document.getElementById('marker".$value['ID_E']."');
			map.addOverlay(new ol.Overlay({
				position: ol.proj.fromLonLat([".$value['LONGITUDE'].",".$value['LATITUDE']."]),
				element: marker".$value['ID_E']."
			}));

			var popup".$value['ID_E']." = document.getElementById('popup".$value['ID_E']."');
			map.addOverlay(new ol.Overlay({
				offset: [0,-35],
				position: ol.proj.fromLonLat([".$value['LONGITUDE'].",".$value['LATITUDE']."]),
				element: popup".$value['ID_E']."
			}));
			function switchMarker(id) {
				val = document.getElementById(id);
				console.log(val);
			 (val.style.display == 'none' ? val.style.display = 'block' : val.style.display = 'none') };

			 function goEvent(id) {
			 	var go = 'event.php?click='+id;
				document.location.replace(go);
			  };
		</script>";
		}

	?>

		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>