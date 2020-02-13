<!DOCTYPE html>
<html>
<head>
	<title>
	</title>
</head>
<body>


	<script type="text/javascript">
     function soumettre()
     {
     document.forms['etoiles'].submit();

     }
	</script>



						<form name='etoiles' id='ratingsForm' method='POST'>
							<div class='stars'>
								<input type='radio' name='star' class='star-1' id='star-1' value='1' onchange="soumettre()" />
								<label class='star-1' for='star-1'>1</label>
								<input type='radio' name='star' class='star-2' id='star-2' value='2' onchange="soumettre()" />
								<label class='star-2' for='star-2'>2</label>
								<input type='radio' name='star' class='star-3' id='star-3' value='3' onchange="soumettre()" />
								<label class='star-3' for='star-3'>3</label>
								<input type='radio' name='star' class='star-4' id='star-4' value='4' onchange="soumettre()" />
								<label class='star-4' for='star-4'>4</label>
								<input type='radio' name='star' class='star-5' id='star-5' value='5' onchange="soumettre()" />
								<label class='star-5' for='star-5'>5</label>
								<input type="hidden" name="okey" value="<?php
								if(isset($_POST['star']))
								{
									echo $_POST['star'];
								}
								?>
								">
								<span></span>
							</div>
						  
						</form>


<?php
								if(isset($_POST['star']))
								{
									echo $_POST['star'];
								}

?>
						

</body>
</html>