<!DOCTYPE html>
<html>
	<head>
		<?php include('head.php'); ?>
	</head>

	<body>
		<?php include('header.php'); ?>

		<div class="mx-auto border-left border-right p-3" style="width: 87%; margin-top: 5%;">
			<h3>Compléter les informations de votre évènement</h3>
			<form method="post" enctype="multipart/form-data">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="nom">Nom de l'évènement</label>
						<input type="text" class="form-control" id="nom" placeholder="ZEvent 2020" required name="nom" value=<?php if (isset($_POST['nom'])){echo $_POST['nom'];} ?>>
					</div>
					<div class="form-group col-md-4">
						<label for="theme">Catégorie/Thème</label>
						<select id="theme" class="form-control" required="required" name="theme">
							<option selected>--</option>
							<option>Caritatif</option>
							<option>Concert</option>
							<option>Étudiant</option>
							<option>Showcase</option>
							<option>Sport</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label for="adresse">Adresse</label>
					<input type="text" class="form-control" id="adresse" placeholder="1234 Main St" required="required" name="adresse" value=<?php if (isset($_POST['adresse'])){echo $_POST['adresse'];} ?>>
				</div>
				<div class="form-group">
					<label for="inputAddress2">Précision sur le lieu</label>
					<input type="text" class="form-control" id="inputAddress2" placeholder="Lieu-dit, salle..." name="lieu" value=<?php if (isset($_POST['lieu'])){echo $_POST['lieu'];} ?>>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="ville">Ville</label>
						<input type="text" class="form-control" placeholder="La Rivière" id="ville" required name="ville" value=<?php if (isset($_POST['ville'])){echo $_POST['ville'];} ?>>
					</div>
					<div class="form-group col-md-2">
						<label for="code">Code postal</label>
						<input type="text" class="form-control" placeholder="97421" id="CodePostal" name="CodePostal" aria-describedby="aidePostal" required name="CodePostal" value=<?php if (isset($_POST['CodePostal'])){echo $_POST['CodePostal'];} ?>>
						<small id="aidePostal" class="form-text text-muted">
							Un code postal comporte 5 numéros.
						</small>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="debut">Début</label>
						<input type="date" class="form-control" id="debut" required name="debut" value=<?php if (isset($_POST['debut'])){echo $_POST['debut'];} ?>>
					</div>
					<div class="form-group col-md-2">
						<label for="fin">Fin</label>
						<input type="date" class="form-control" id="fin" name="fin" value=<?php if (isset($_POST['fin'])){echo $_POST['fin'];} ?>>

					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="heureDebut">Heure début</label>
						<input type="time" class="form-control" id="heureDebut" required name="heureDebut" value=<?php if (isset($_POST['heureDebut'])){echo $_POST['heureDebut'];} ?>>
					</div>
					<div class="form-group col-md-2">
						<label for="heureFin">Heure fin</label>
						<input type="time" class="form-control" id="heureFin" name="heureFin" value=<?php if (isset($_POST['heureFin'])){echo $_POST['heureFin'];} ?>>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-2">
						<label for="minimum">Effectif minimum</label>
						<input type="number" class="form-control" id="minimum" required name="effectMin" value=<?php if (isset($_POST['effectMin'])){echo $_POST['effectMin'];} ?>>
					</div>
					<div class="form-group col-md-2">
						<label for="maximum">Effectif maximum</label>
						<input type="number" class="form-control" id="maximum" name="effectMax" value=<?php if (isset($_POST['effectMax'])){echo $_POST['effectMax'];} ?>>
					</div>
					<div class="form-group col-md-4">
						<label for="ageMinim">Âge minimum requis</label>
						<select id="ageMinim" class="form-control" required="required" name="ageMinim">
							<option selected>--</option>
							<option>Aucun âge requis</option>
							<option>Mineur (à partir de 16 ans)</option>
							<option>Majeur</option>
						</select>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-8">
						<label for="description">Description</label>
						<textarea class="form-control" placeholder="Il y aura de la bonne meuf" id="description" rows='4' required name="description" value=<?php if (isset($_POST['description'])){echo $_POST['description'];} ?>></textarea>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="exampleFormControlFile1">Ajouter une image</label>
						<img src="" style="display: none;" width='210px' height='150px' id="image"> <br>
						<input type="file" class="form-control-file" accept="image/*" name="photo" onchange="chargement.call(this)" id ="photo">
					</div>
				</div>
				<button class="btn btn-success" type="submit" name="confirmer">Souscrire</button>
			</form>
		</div>

		<?php include('footer.php'); 
		 	include('script.php'); 
		 	

		 	

		 if (isset($_POST['confirmer']) && !empty($_FILES)) {
		# code...
		 	$dbh = connexionPDO('event');
			$extension = strchr($_FILES['photo']['name'], ".");
			$max = maxiIdeEvent($dbh) + 1;
			$nomPhoto = $max.$extension;


			if (move_uploaded_file($_FILES['photo']['tmp_name'],"IMAGE/".$nomPhoto))
			{
				
			}


			

			$adrFinal = $_POST['adresse']." ".$_POST['CodePostal']." ".$_POST['ville'];
			$coord = long_latitude($adrFinal);

			$dd = $_POST['debut']." ".$_POST['heureDebut'];
			$df = $_POST['fin']." ".$_POST['heureFin'];
			
			$ageMinim = "'".$_POST['ageMinim']."'";
			ajoutEvent($dbh,$_POST['nom'] ,$_POST['theme'],$_POST['description'],$_POST['effectMin'],$_POST['effectMax'],$ageMinim, $dd, $df, $adrFinal,$coord['Longitude'],$coord['Latitude'],statutNumero($_SESSION['membre']),$nomPhoto);

 			
			if (triggerEstDeclancher()) {
				
				$err = getMessgaeTriggers();
				/*echo $err;*/
				deleteAllTrigger();
				echo "<script type='text/javascript'>invalidevent("."\"".$err."\")</script>";
			}
			else{
				/*echo "operation reussie";*/
				echo "<script type='text/javascript'>validevent()</script>";
			}
		}
		?>


		 <script>
	function chargement()
	{
		if(this.files && this.files[0])
		{
			var obj = new FileReader();
			obj.onload = function(data)
			{
				var image = document.getElementById("image");
				image.src = data.target.result;
				image.style.display = "block";
			
			
		}
		obj.readAsDataURL(this.files[0]);
	}
}
</script>
	</body>
</html>