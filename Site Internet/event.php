<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
		<script type='text/javascript' src='jquery-3.4.1.min.js'></script>
		<link rel='stylesheet' type='text/css' href='jquery-ui-1.12.1/jquery-ui.min.css'>
		<script src='https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v6.0.1/build/ol.js'></script>
		<link rel="stylesheet" type="text/css" href="star.css">
	</head>

	<body class="flou">
		<div id="fb-root"></div>

<script type="text/javascript">
     function soumettre()
     {
     document.forms['etoiles'].submit();

     }
	</script>
		<?php 
		include('header.php');

	

		 if (isset($_GET["click"])) {
		  $idEvent = $_GET["click"];
		  }
		  else{
		  	header("location:index.php");
		  }

		  $dbh = connexionPDO('event');


		  	if (isset($_POST['participe']))
		  	{
				if (isset($_SESSION['membre'])) 
				{
					$rqt = "select ID_P from personne where PSEUDO='".$_SESSION['membre']."'";
					$res = $dbh->query($rqt);
					foreach ($res as  $value) 
					{
						$rqt = 'insert into participe VALUES('.$value['ID_P'].', '.$idEvent.')';
						$dbh->query($rqt);
					}
					
					if (triggerEstDeclancher()) 
					{
						$err = getMessgaeTriggers();
						deleteAllTrigger();
						/*echo $err;*/
						echo "<script type='text/javascript'>invalidevent("."\"".$err."\")</script>";
					}
				else{
					/*echo "operation reussie";*/

					}

				}
				else{
					header("location:connexion.php?click=".$idEvent);
				}
		}


	if (isset($_POST['no_participe'])) {
			
				if (isset($_SESSION['membre'])) {
					
					$rqt = "select ID_P from personne where PSEUDO='".$_SESSION['membre']."'";
					
					$res = $dbh->query($rqt);
					foreach ($res as  $value) {
						
						$rqt = 'delete from participe where ID_P ='.$value['ID_P'].' and ID_E = '.$idEvent;
					
						$dbh->query($rqt);
					}

				}
				else{
					header("location:connexion.php?click=".$idEvent);
				}
		}


		  $rqt = 'select * from evenement where ID_E ='.$idEvent;

		 
		$rqt2 = 'select avg(NOTE) from notation where ID_E ='.$idEvent;


		$resultat = $dbh->query($rqt);
		$resultat2 = $dbh->query($rqt2, PDO::FETCH_OBJ);
		$val = round($resultat2->fetchColumn());


		foreach ($resultat as $value) {

			 echo "<style>.flou:before{background: url('./IMAGE/".$value['CHEMIN_IMAGE']."')}</style><body class='flou'><div class='cadre mx-auto bg-white shadow mb-5'>
			<div class='container mw-100 border-bottom border-dark'>
			<div class='row'>
			<div class='col-sm-8 p-0' style='height: 450px;'>
			<img src='./IMAGE/".$value['CHEMIN_IMAGE']."' width='100%' height='100%'>
			</div>
			<div class='col-sm-4 p-5'>

			<p>Note Moyenne : ".noteToStars($val)."

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
					<div class='col'>";


					if (isset($_SESSION['membre'])) {
						# code...
						if (donnerNote($idEvent,$_SESSION['membre'])==true) {
						
						echo "<form id='ratingsForm' name='etoiles' method='POST'>
							<div class='stars'>
								<input type='radio' name='star' class='star-1' id='star-1' value='1' onchange='soumettre()'";

								if (isset($_POST['star']) and $_POST['star']==1 or noteUsers($idEvent,statutNumero($_SESSION['membre']))==1) {
									
									echo "checked";
								}
								 echo " />
								<label class='star-1' for='star-1'>1</label>
								<input type='radio' name='star' class='star-2' id='star-2' value='2' onchange='soumettre()'";

								if (isset($_POST['star']) and $_POST['star']==2 or noteUsers($idEvent,statutNumero($_SESSION['membre']))==2) {
									
									echo "checked";
								}
								 echo "/>
								<label class='star-2' for='star-2'>2</label>
								<input type='radio' name='star' class='star-3' id='star-3' value='3' onchange='soumettre()'";

								if (isset($_POST['star']) and $_POST['star']==3 or noteUsers($idEvent,statutNumero($_SESSION['membre']))==3) {
									
									echo "checked";
								}
								 echo "/>
								<label class='star-3' for='star-3'>3</label>
								<input type='radio' name='star' class='star-4' id='star-4' value='4' onchange='soumettre()' ";

								if (isset($_POST['star']) and $_POST['star']==4 or noteUsers($idEvent,statutNumero($_SESSION['membre']))==4) {
									
									echo "checked";
								}
								 echo "/>
								<label class='star-4' for='star-4'>4</label>
								<input type='radio' name='star' class='star-5' id='star-5' value='5'  onchange='soumettre()'";

								if (isset($_POST['star']) and $_POST['star']==5 or noteUsers($idEvent,statutNumero($_SESSION['membre']))==5) {
									
									echo "checked";
								}
								 echo "/>
								<label class='star-5' for='star-5'>5</label>
								<span></span>
							</div>
						  
						</form>";
					}
					}


					if (isset($_POST['star'])) {
						

					$dbh = connexionPDO('event');
					$verif = "select * from notation where ID_P=".statutNumero($_SESSION['membre'])." and ID_E=".$idEvent;
					$resultat = $dbh->query($verif);

					if ($resultat->rowCount() <=0) {
						
						$rqt = "insert into notation VALUES(".statutNumero($_SESSION['membre']).",".$idEvent.",".$_POST['star'].")";
					}
					else{

						$rqt = "UPDATE notation SET NOTE=".$_POST['star']." WHERE ID_P=".statutNumero($_SESSION['membre'])." and ID_E=".$idEvent;
					}

						
						$dbh->query($rqt);

						/*echo "<script type='text/javascript'>document.location.replace('event.php?click=".$idEvent."');</script>";*/


					}

					echo "</div>
					<div class='col-md-auto'>
						<form method='POST'>
						";

						if (isset($_SESSION['membre'])) {


				$verification = "SELECT * FROM participe,personne,evenement WHERE participe.ID_P = personne.ID_P and evenement.ID_E=participe.ID_E  AND personne.PSEUDO ='".$_SESSION['membre']."' and evenement.ID_E =".$idEvent;

				$alors = $dbh->query($verification);

				$dateEvent = new DateTime($value['DATE_F']);
				$dateToday = new DateTime("now");
			

				
					if ($dateEvent < $dateToday) {
						
						echo "<button type='button' disabled id='dejaPasse' class='btn btn-secondary ' style='cursor:default;' name='dejaPasse'>Evenement déja passé</button>";
					}
					else if ($alors->rowCount() == 1) {

						echo "<button type='submit' id='parti' class='btn btn-danger' name='no_participe'>Ne plus participer à l'évenement</button>";

				
					}
					else{

						echo "<button type='submit' id='parti' class='btn btn-success' name='participe'>Participer à l'évenement</button>";
					}
				}
				else{

					echo "<button type='submit' id='parti' class='btn btn-success' name='participe'>Participer à l'évenement</button>";
				}

						echo "

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
			<img id='marker' src='marker3.png' width='50' height='50' onmouseenter='switchMarker()' onmouseleave='switchMarker()'>
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



		 ?>







		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>