<?php

session_start();
?>

<!DOCTYPE html>
<html>
<head>
	<title>
		
	</title>

	<?php
		
		function existe($k, $n)
		{
		foreach ($k as $value) {
		# code...
			if($value == $n)
			{
				
				return true;
			}
		}
		
		return false;
		}

		

		
		

		if (isset($_POST['envoyer'])) {
			# code...

			if($_POST['chiffre'])
			{
				
					$test = unserialize($_SESSION['sauve']);
					
				
				
				if ($test) {
					# code...
					print_r($test);
								
					$tab = unserialize($_SESSION['sauve']);		
				}
				else{
					
					$tab = array();

				}
				
				if (existe($tab,$_POST['chiffre'])) {
					# code...
					echo "vous avez perdu <br>";
					session_destroy();
				}
				else{

					
					$tab[] = $_POST['chiffre'];

					$_SESSION['sauve'] = serialize($tab);

					if (sizeof($tab) == 9) {
						# code...
						echo "vous avez gagnee :) <br>";
						session_destroy();
					}
					
				
				}

			}
			else{
				echo "vous devez entrer un chiffre <br>";
			}
		}


	?>
</head>
<body>
	<div>
		<form action="" method="post">
			<p>
				Entrez un chiffre different du precedent <br>
				<input type="text" name="chiffre" placeholder="chiffre">
			</p>
			<p>
				<input type="submit" name="envoyer" value="envoyer">
			</p>
		</form>
	</div>

	
</body>
</html>