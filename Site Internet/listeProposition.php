<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>
	<body>
		<?php 
		include('header.php');
		$dbh = connexionPDO('event');

		$rqt = "select ID_E, NOM_E, DATE_D, AGE_MIN,THEME, EFFECTIF_MAX, CHEMIN_IMAGE  from evenement where VISIBLE=0 order by DATE_D"; 
  
 		 $resultat = $dbh->query($rqt);

 		 foreach ($resultat as $value) {
 		 	
 		 	echo "<a class='lienEvent' href='proposition.php?click=".$value['ID_E']."'><div class='cadre mx-auto bg-white shadow mb-5'>
			<div class='container mw-100'>
				<div class='row'>
					<div class='col-sm-6 p-0' style='height: 400px;'>
						<img src='./IMAGE/".$value['CHEMIN_IMAGE']."'width='100%' height='100%'>
					</div>
					<div class='col-sm-6 p-5'>
						".jourDeEvent($value['DATE_D'])." <br/><br/>
						<h3>".$value['NOM_E']."</h3>
						".strtoupper($value['THEME'])."<br/><br/>
						Âge Minimum : ".$value['AGE_MIN']."<br/><br/>
						Effectif maximum : ".$value['EFFECTIF_MAX']."<br/><br/>
					</div>
				</div>
			</div>
		</div></a>";
 		 }


		?>

		
		

		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>