<?php
	include "fonction.php";

	session_start();
	
	if (isset($_SESSION['membre'])) 
	{
		$rendu = $_SESSION['membre'];
		$statut = statutChaine($rendu);

	
		echo "
		<header  id='header' class='fixed-top'>
			<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>
				<a class='navbar-brand' href='index.php'>CoolEvent</a>
				<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>
					<span class='navbar-toggler-icon'></span>
				</button>

				<div class='collapse navbar-collapse' id='navbarSupportedContent'>
					<ul class='navbar-nav mr-auto'>
						<li class='nav-item active'>
							<a class='nav-link' href='index.php'>Accueil<span class='sr-only'>(current)</span></a>
						</li>
						<li class='nav-item'>
							<a class='nav-link' href='listeEvent.php'>Évènements</a>
						</li>
						";
						if ($statut =='Contributeur') {
							echo "
							<li class='nav-item'>
							<a class='nav-link' href='ajoutEvent.php'>Ajouter Évènement</a>
						</li>";
						
						}
						else if($statut == 'Administrateur')
						{
				
				
							$dbh = connexionPDO('event');
							$resultat = "select * from evenement where VISIBLE=0";
							$nbProp = $dbh->query($resultat);

							$dbh = connexionPDO('event');
							$resultat2 = "select * from personne where ID_R <> 0";
							$nbMbr = $dbh->query($resultat2);

							echo "
							<li class='nav-item'>
							<a class='nav-link' href='listeProposition.php'>Propositions(".$nbProp->rowCount().")</a>
						</li>
						<li class='nav-item'>
							<a class='nav-link' href='membres.php'>membres(".$nbMbr->rowCount().")</a>
						</li>";
						}
						echo "
					</ul>

					<form class='form-inline my-2 my-lg-0' method='post'>
						
						<a id='profil' class='nav-link text-light' href='profil.php?nom=".$rendu."'>
							
							<svg id='usericon' width='32' height='32' viewBox='0 0 20 20' fill='currentColor' >
								<path fill-rule='evenodd' d='M5 16s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H5zm5-6a3 3 0 100-6 3 3 0 000 6z' clip-rule='evenodd'/>
							</svg>

							<span id='txticon'>".$rendu."</span>

						</a>

						<a class='btn btn-primary btn-success' href='logout.php' role='button'>Deconnexion</a>
					</form>
				</div>
			</nav>
		</header>";
	}

	else{
		echo "
			<header  id='header' class='fixed-top'>
				<nav class='navbar navbar-expand-lg navbar-dark bg-dark'>
					<a class='navbar-brand' href='index.php'>CoolEvent</a>
					<button class='navbar-toggler' type='button' data-toggle='collapse' data-target='#navbarSupportedContent' aria-controls='navbarSupportedContent' aria-expanded='false' aria-label='Toggle navigation'>
					<span class='navbar-toggler-icon'></span>
					</button>

				<div class='collapse navbar-collapse' id='navbarSupportedContent'>
					<ul class='navbar-nav mr-auto'>
						<li class='nav-item active'>
							<a class='nav-link' href='index.php'>Accueil<span class='sr-only'>(current)</span></a>
						</li>
						<li class='nav-item'>
							<a class='nav-link' href='listeEvent.php'>Évènements</a>
						</li>
					</ul>

					<form class='form-inline my-2 my-lg-0' >
						<a class='btn btn-primary btn-info mr-2' href='inscription.php' role='button'>S'inscrire</a>
						<a class='btn btn-primary btn-light' href='connexion.php' role='button'>Se connecter</a>
			 		</form>
				</div>
			</nav>
		</header>
		";
	}
	

	
?>
