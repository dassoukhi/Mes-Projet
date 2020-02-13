<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>

	<script type="text/javascript">
    
	</script>
	<body>
		<?php include('header.php'); ?>

		<div class="container" style="margin-top: 5%;">
			<div class="row justify-content-md-center">
				<div class="btn-group mx-auto" role="group" >
					<button id="btnGroupDrop1" type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Trier par
					</button>
					<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">

						<form  name="Trier">
							<div class="dropdown-item">
								<div>
									<span>Thème</span>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="caritatif" value="CARITATIF" onchange="autoSubmit()">
										<label class="form-check-label" for="caritatif">
											Caritatif
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="concert" value="CONCERT" onchange="autoSubmit()">
										<label class="form-check-label" for="concert">
											Concert
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="etudiant" value="ÉTUDIANT" onchange="autoSubmit()">
										<label class="form-check-label" for="etudiant">
											Étudiant
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="familial" value="FAMILIAL" onchange="autoSubmit()">
										<label class="form-check-label" for="familial">
											Familial
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="showcase" value="SHOWCASE" onchange="autoSubmit()">
										<label class="form-check-label" for="showcase">
											Showcase
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="theme[]" id="sport" value="SPORT" onchange="autoSubmit()">
										<label class="form-check-label" for="sport">
											Sport
										</label>
									</div>
								</div>
							</div>

<!-- 							<div class="dropdown-item">
								<div>
									<span>Restriction d'âge</span>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="age[]" id="aucunAge" value="Aucun âge requis">
										<label class="form-check-label" for="aucunAge">
											Aucun âge requis
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="age[]" id="mineur" value="Mineur (à partir de 16 ans)">
										<label class="form-check-label" for="mineur">
											Mineur (à partir de 16 ans)
										</label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="checkbox" name="age[]" id="majeur" value="Majeur">
										<label class="form-check-label" for="majeur">
											Majeur
										</label>
									</div>
								</div>
							</div> -->
							<div class="form-check dropdown-item">
								<input class="form-check-input" type="radio" name="passe" id="passe" value="" onchange="autoSubmit()">
								<label class="form-check-label" for="passe">
									Évènement terminé
								</label>
							</div>
							
						</form>

					</div>
				</div>
			</div>
		</div>

		<?php

		$dbh = connexionPDO('event');

		if (isset($_GET['passe']) and isset($_GET['theme'])) {
			foreach ($_GET['theme'] as $r1) {
				$rqt1 = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE  from evenement where THEME='$r1' AND VISIBLE=1 AND EVENT_PASSE(ID_E)=1 order by DATE_D";
				$resultat1 = $dbh->query($rqt1);
				foreach ($resultat1 as $value) {

					echo "<div class='cadre mx-auto bg-white shadow mb-5'>
		 		 	<a class='lienEvent' href='event.php?click=".$value['ID_E']."'>
						<div class='container mw-100'>
							<div class='row'>
								<div class='col-sm-6 p-0' style='height: 400px;'>
									<img src='./IMAGE/".$value['CHEMIN_IMAGE']."'width='100%' height='100%'>
								</div>
								<div class='col-sm-6 p-5'>
									".jourDeEvent($value['DATE_D'])." <br/><br/>
									<h3>".$value['NOM_E']."</h3>
									".strtoupper($value['THEME'])."<br/><br/>
									Âge minimum : ".$value['AGE_MIN']."<br/><br/>
									Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
								</div>
							</div>
						</div>
					</a>
				</div>";
	 			}
			}
		}

		elseif (isset($_GET['theme'])) {
			foreach ($_GET['theme'] as $r2) {
				$rqt2 = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE  from evenement where THEME='$r2' AND VISIBLE=1 AND EVENT_PASSE(ID_E)=0 order by DATE_D";
				$resultat2 = $dbh->query($rqt2);
				foreach ($resultat2 as $value) {

					echo "<div class='cadre mx-auto bg-white shadow mb-5'>
		 		 	<a class='lienEvent' href='event.php?click=".$value['ID_E']."'>
						<div class='container mw-100'>
							<div class='row'>
								<div class='col-sm-6 p-0' style='height: 400px;'>
									<img src='./IMAGE/".$value['CHEMIN_IMAGE']."'width='100%' height='100%'>
								</div>
								<div class='col-sm-6 p-5'>
									".jourDeEvent($value['DATE_D'])." <br/><br/>
									<h3>".$value['NOM_E']."</h3>
									".strtoupper($value['THEME'])."<br/><br/>
									Âge minimum : ".$value['AGE_MIN']."<br/><br/>
									Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
								</div>
							</div>
						</div>
					</a>
				</div>";
	 			}
			}
		}



		elseif (isset($_GET['passe'])){

		$rqt3 = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE from evenement where VISIBLE=1 AND EVENT_PASSE(ID_E)=1  order by DATE_D"; 
  
 		 $resultat3 = $dbh->query($rqt3);

 		 foreach ($resultat3 as $value) {
 		 	
 		 	echo "<div class='cadre mx-auto bg-white shadow mb-5'>
 		 	<a class='lienEvent' href='event.php?click=".$value['ID_E']."'>
				<div class='container mw-100'>
					<div class='row'>
						<div class='col-sm-6 p-0' style='height: 400px;'>
							<img src='./IMAGE/".$value['CHEMIN_IMAGE']."'width='100%' height='100%'>
						</div>
						<div class='col-sm-6 p-5'>
							".jourDeEvent($value['DATE_D'])." <br/><br/>
							<h3>".$value['NOM_E']."</h3>
							".strtoupper($value['THEME'])."<br/><br/>
							Âge minimum : ".$value['AGE_MIN']."<br/><br/>
							Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
						</div>
					</div>
				</div>
			</a>
		</div>";
 		 }
 		}


		else{

		$rqt4 = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE  from evenement where VISIBLE=1 AND EVENT_PASSE(ID_E)=0  order by DATE_D"; 
  
 		 $resultat4 = $dbh->query($rqt4);

 		 foreach ($resultat4 as $value) {
 		 	
 		 	echo "<div class='cadre mx-auto bg-white shadow mb-5'>
 		 	<a class='lienEvent' href='event.php?click=".$value['ID_E']."'>
				<div class='container mw-100'>
					<div class='row'>
						<div class='col-sm-6 p-0' style='height: 400px;'>
							<img src='./IMAGE/".$value['CHEMIN_IMAGE']."'width='100%' height='100%'>
						</div>
						<div class='col-sm-6 p-5'>
							".jourDeEvent($value['DATE_D'])." <br/><br/>
							<h3>".$value['NOM_E']."</h3>
							".strtoupper($value['THEME'])."<br/><br/>
							Âge minimum : ".$value['AGE_MIN']."<br/><br/>
							Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
						</div>
					</div>
				</div>
			</a>
		</div>";
 		 }
 		}


		?>

		
		

		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>