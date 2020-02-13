<!DOCTYPE>
<html>
<head>
	<title></title>
</head>
<body>

<div align="center">
	
	<form action="" method="post">
		<p> Se connecter<br><br>
			<input type="text" name="pseudo" placeholder="pseudo">
		</p>
		<p>
			<input type="password" name="mdpass" placeholder="mot de passe">
		</p>
		<p>
			<input type="submit" name="connexion" value="connexion">
		</p>
	</form>
</div>

<div align="center">
	<form action="" method="post">
		<br>
		S'inscrire :
		<p>
			 Pseudo<br>
			<input type="text" name="identi" placeholder="pseudo">
		</p>
		<p>
			Date de naissance<br>
			<input type="date" name="date" value="2019-10-30">
		</p>
		<p>
			En tant que : <br>
			Contributeur <input type="radio" name="statut" value="contri">
			Visiteur <input type="radio" name="statut" value="visit" checked="checked">
		</p>
		<p>
			Mot de passe<br>
			<input type="password" name="mdp" placeholder="password">
		</p>
		<p>
			Confirmation mot de pass<br>
			<input type="password" name="confir" placeholder="password">
		</p>
		<p>
			<input type="submit" name="inscrire" value="s'inscrire">
		</p>
	</form>

</div>
</body>


<?php
	
	include "connexion.php";

		
		
		$dbh = connexionPDO('event');

	if (isset($_POST['connexion'])) {
		# code...

		connexionMembre($_POST['pseudo'], $_POST['mdpass'],$dbh);

	}


	if (isset($_POST['inscrire'])) {
		# code...

		if ($_POST['statut'] =="visit" ) {
			# code...
			$statut = 2;
		}
		else if ($_POST['statut'] == "contri") {
			# code...
			$statut = 1;
		}
		
	inscriptionMembre($_POST['identi'],$_POST['date'],$_POST['mdp'],$_POST['confir'],$statut,$dbh);

	}

?>
</html>