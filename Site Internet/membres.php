<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>
	<body>
		<?php 
		
		include('header.php');

		if (isset($_POST['deleteUser'])) 
		{
			
			$dbh = connexionPDO('event');


			$idUser=$_POST['deleteUser'];

			$rqt = "DELETE from Notation WHERE ID_P='".$idUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from participe WHERE ID_P='".$idUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from evenement WHERE ID_P='".$idUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from personne WHERE ID_P='".$idUser."'";
			$dbh->query($rqt);

		}

		$dbh = connexionPDO('event');

		$rqt = "select * from personne where ID_R <> 0"; 
  
 		 $resultat = $dbh->query($rqt);
 		 echo "<form method='post'>
 		 		<div class='cadre mx-auto bg-white shadow mb-5'>
					<div class='container mw-100'>
						<div class='row p-3'>";

 		 foreach ($resultat as $value) {
 		 	
 		 	echo "<div class='col-sm-3 col-md-2 border p-2 m-auto' style='height:75%'>
								<h4>".$value['PSEUDO']."</h4>
								<p>". bonneFormatDate($value['DATE_NAISSANCE'])."</p>
								<p>".statutChaine($value['PSEUDO'])."</p>

								<button class='btn-sm btn-outline-danger' type='submit' name='deleteUser' value='".$value['ID_P']."'>Supprimer cet utilisateur</button>
							</div>						
						";
 		 }
 		 echo "</div>
					</div>
				</div></form>";

		?>

		
		

		<?php include('footer.php'); ?>
		<?php include('script.php'); ?>
	</body>
</html>