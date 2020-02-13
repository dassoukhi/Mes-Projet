<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	
	function validevent() 
	{
		let html="";
		html+="<div id='anim' class='valid'><p>Votre demande est en attente de validation par un administrateur</p></div>";

		document.getElementById("header").innerHTML +=html;
		
		setTimeout(function() {
			document.getElementById('anim').innerHTML='';
		},5000);

		
	}

	function invalidevent(txt) 
	{
		console.log(txt);
		let html="\
		<div id='anim' class='invalid'><p>Votre demande n'a pu aboutir<br/> Raison: "+txt+"</p></div>";

		document.getElementById("header").innerHTML +=html;
		
		setTimeout(function() {
			document.getElementById('anim').innerHTML='';}
			,5000);	
	}

</script>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="utf-8">
<title>CoolEvent</title>
<script type='text/javascript' src='jquery-3.4.1.min.js'></script>
<link rel='stylesheet' type='text/css' href='jquery-ui-1.12.1/jquery-ui.min.css'>
<script src='https://cdn.rawgit.com/openlayers/openlayers.github.io/master/en/v6.0.1/build/ol.js'></script>