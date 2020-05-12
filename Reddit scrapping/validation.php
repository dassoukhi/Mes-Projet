<!DOCTYPE html>
<html>

	<?php
		include("head.php");
	?>
	<?php
			if (isset($_POST['Restore'])){

				if (isset($_POST['index'])){

				$index=$_POST["index"];

				echo "<script>console.log('".$index."')</script>";
				
				$json = file_get_contents('./fusion.json');

				//Decode JSON
				$data = json_decode($json,true);

				
				$data[$index]["test"]=true;

				$newdata = json_encode($data);
				file_put_contents('fusion.json', $newdata);

					
				}
			}
			else echo "<script>console.log('nope');</script>";

			if (isset($_POST['correct'])){

				if (isset($_POST['locUser'])){

					$index=$_POST["index"];
					$loc=$_POST["locUser"];

					$json = file_get_contents('./corrections.json');
					$data = json_decode($json,true);

					$data[$index]=$loc;
					$newdata = json_encode($data);
					file_put_contents('corrections.json', $newdata);
				}
			}
		?>

	<body>

		<?php
			include("header.php");
		?>

		<div class="compteur" style="text-align: center;"> <h5>Posts en attente de verification : <p id='cpt'></p> </h5> </div>

		<br>
		<div class='container mx-auto px-auto'>
			<div id="aVerif" class='row'>
						
			</div>
		</div>;
		


		<?php
		include ('footer.php');
		include ('script.php');
		?>


			
	</body>

	<script type="text/javascript">

		$().ready(function() {
			var cpt=0;
			$.getJSON('fusion.json',function(data){
				$.each(data, function(index,objet) {
					if (objet.test==false)
					{
						cpt++;
						var html="<div class='col-sm-5 col-md-4 p-3'><div class='w-100' style='height:30%'><img src='"+objet.url+"' style='width: 100%; object-fit:fill; height:100%'></div><div class='w-100 p-2'><h5>Description :</h5> <p>"+objet.afterClean+"</p><h5>localisation originale :</h5> <p>"+objet.location+"</p><form method='POST' class='border border-dark rounded px-2 my-3'><input type='hidden' name='index' value='"+index+"'><p>vous pouvez nous indiquer une localisation plus précise via le champ ci-dessous</p> <input type='text' class='form-control' name='locUser' placeholder='entrez ici votre correction'> <button name='correct' type='submit' class='mt-1 btn btn-info btn-sm'>Envoyer correction</button><p class='mt-2'>si la localisation était juste cliquez ici :<button name='Restore' type='submit' class='btn btn-success btn-sm'>Restaurer</button></p></form></div></div>";

						$("#aVerif").append(html);
						/*console.log("done?");*/

					}
					

				});
				console.log("cpt = "+cpt);
			$("#cpt").append(cpt);
			});
		
		});



		

	</script>

</html>