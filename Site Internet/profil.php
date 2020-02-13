<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>

	<body>
		<?php include('header.php'); 

		//suppression du compte
		if (isset($_POST['Suppcompte'])) 
		{
			
			$dbh = connexionPDO('event');

			$IdUser=statutNumero($_POST['Suppcompte']);
			echo $IdUser;

			$rqt = "DELETE from evenement WHERE ID_P='".$IdUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from participe WHERE ID_P='".$IdUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from Notation WHERE ID_P='".$IdUser."'";
			$dbh->query($rqt);

			$rqt = "DELETE from personne WHERE ID_P='".$IdUser."'";
			$dbh->query($rqt);

			session_destroy();
			header('location:index.php');
		}



		//devenir contributeur
		if (isset($_POST['devenir'])) {

			$rqt = "UPDATE personne SET ID_R= 1 WHERE PSEUDO = '".$_SESSION['membre']."'";

			
			$dbh = connexionPDO('event');

			$dbh->query($rqt);
			
			echo "<script type='text/javascript'>document.location.replace('header.php?nom=".$_SESSION['membre']."');</');</script>";

		}
		


		//pour qu'un contributeur supprime son evenement
		if (isset($_POST['suppevent'])) 
		{
			
			$dbh = connexionPDO('event');


			$idevent=$_POST['suppevent'];
			echo $idevent;

			$rqt = "DELETE from evenement WHERE ID_E='".$idevent."'";
			$dbh->query($rqt);

			$rqt = "DELETE from participe WHERE ID_E='".$idevent."'";
			$dbh->query($rqt);

			$rqt = "DELETE from Notation WHERE ID_E='".$idevent."'";
			$dbh->query($rqt);

		}

		if (isset($_GET['nom'])) {
			
			$nom = $_GET['nom'];

			$dbh = connexionPDO('event');
			$rqt = "select * from personne where pseudo = '".$nom."'";
			
			
			$resultat = $dbh->query($rqt);

			foreach ($resultat as $value) {
				
				echo "<div class='cadre mx-auto border-left border-right p-3'>
			<div class='container'>
				<div class='row'>
					<div class='col-sm-6'>
						<h3>Vos informations personnels</h3>
						<form method='post'>
							<div class='form-row'>
								<div class='form-group col-md-6'>
									<label for='pseudo'>Pseudo</label>
									<input type='text' class='form-control' id='pseudo' placeholder='".$value['PSEUDO']."' readonly>
								</div>
					
							</div>
							<div class='form-row'>
								<div class='form-group col-md-6'>
									<label for='dateNaissance'>Date de naissance</label>
									<input type='text' class='form-control' id='dateNaissance' placeholder='".bonneFormatDate($value['DATE_NAISSANCE'])."' readonly>
								</div>
							</div>
							<div class='form-row'>
								<div class='form-group col-md-6'>
									<label for='role'>Statut</label>
									<input type='text' class='form-control' id='role' placeholder='".statutChaine($value['PSEUDO'])."' readonly>
								</div>
							</div>
							";

				if (statutChaine($value['PSEUDO']) == 'Visiteur') {
					# code...
					echo "<div class='form-group col-md-6'>
						<label for='etreContributeur'>Voulez-vous devenir contributeur</label>
						<button type='submit' class='btn btn-success' aria-describedby='aideContributeur' name='devenir'>Devenir Contributeur</button>
						<small id='aideContributeur' class='form-text text-muted'>
							Un contributeur peut présenter son évènement.
						</small>
					</div>";
				}

				


				echo "
			</form>
		</div>

		<div class='col-sm-6'>
						<h3>Changer votre mot de passe</h3>
						<form method='post'>
							<div class='form-group col-md-6'>
								<label for='newMdp'>Nouveau mot de passe</label>
								<input type='password' class='form-control' id='newMdp' placeholder='Votre mot de passe'  name = 'mdp'>
							</div>
							<div class='form-group col-md-6'>
								<label for='confirmeMdp'>Confirmer mot de passe</label>
								<input type='password' class='form-control' id='confirmeMdp' placeholder='Entrer à nouveau le mot de passe'  name = 'confirm'>
							</div>
							<div class='form-group col-md-6'>
								<button type='submit' class='btn btn-success' name ='changer'>Changer</button>
							</div>

							<div class='mt-5 form-group col-md-6'>
								<button type='submit' class='btn btn-outline-danger' value='".$value['PSEUDO']."'name ='Suppcompte'>Supprimer mon compte</button>
							</div>
						</form>
					</div>
				</div>
			</div>

			<div>";

					//pour les evenements auquel il participe 
					$rqt = "SELECT * FROM participe, evenement, personne WHERE participe.ID_P = personne.ID_P and participe.ID_E=evenement.ID_E and personne.PSEUDO='".$_GET['nom']."'";


					$resultat = $dbh->query($rqt);

				if (statutChaine($value['PSEUDO']) != 'Administrateur' and $resultat->rowCount() > 0) {

					echo "<h3>Liste d'întérêt</h3>";
				}



				
				echo "
				<div class='container'>
					<div class='row'>

					";


					foreach ($resultat as $value) {
						

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
				
			

				<h4>Vos Evenements</h4>
					<div id='contribEvent'class='row'>
						";

						//pour les evenements qu'il a lui-même soumis 
						$rqt2 = "SELECT * FROM evenement WHERE evenement.ID_P = (SELECT ID_P FROM personne Where personne.PSEUDO='".$_GET['nom']."')";

						$eveneContrib = $dbh->query($rqt2);

						if ($eveneContrib -> rowCount() > 0)
						{
							foreach($eveneContrib as $value2) 
							{
								echo "
								<div class='col-sm-4 col-md-3 p-3 mx-0'style='height:25%'>
									<a class='lienEvent' href='event.php?click=".$value2['ID_E']."'>
										<div class='w-100'>
											<img class='img-thumbnail' src='./IMAGE/".$value2['CHEMIN_IMAGE']."' style='width: 100%; object-fit:cover;'>
										</div>

										<div class='w-100 p-2'>
											".$value2['NOM_E']."
										</div>
									</a>
									<form method='post'>
										<button type='submit' class='btn btn-outline-warning btn-sm' name ='suppevent' value= ".$value2['ID_E'].">Supprimer cet évènement</button>
									</form>
								</div>";
							}
						}
					echo "
					</div>
				</div>
			</div>
		</div>
		";
			}


		}

		?>

		

		<?php include('footer.php'); ?>
		<?php include('script.php'); 



		if (isset($_POST['changer'])) {
			
			if ($_POST['mdp'] !== $_POST['confirm']) {
				
				echo "les deux mot de passe doivent etre itendique";
			}
			else{

				if (isset($_SESSION['membre'])) {
					
				$motCrypte =sha1($_POST['mdp']);

					$rqt = "UPDATE personne SET MDP ='".$motCrypte."' WHERE PSEUDO = '".$_SESSION['membre']."'";

					$dbh->query($rqt);
				}
			}
		}


		?>
	</body>
</html>