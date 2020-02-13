<?php

function connexionPDO($nomBD)
{

	$user='root';
		$pass='';

		try {
			
			$dbh = new PDO("mysql:host=localhost;dbname=".$nomBD.";charset=UTF8",$user,$pass);

			return $dbh;
		} catch (PDOException $e) {

			echo $e->getMessage();
			die("connexion impossible");
		}
}



	function connexionMembre($n,$p,$dbh,$click)
	{
		
		$identi = $n;
		$pw= $p;
		if ($identi && $pw) {
			# code...
		
		$motCrypte =sha1($pw);


		$verification = "select * from personne where pseudo='".$identi."' and MDP='".$motCrypte."'";
		//echo $verification, "<br>";
		$alors = $dbh->query($verification);

		if ($alors->rowCount() == 1) {
			$_SESSION['membre'] = $identi;

			if ($click!=null) {
				# code...
				header('location:event.php?click='.$click);
			}
			else{
				
				header('location:listeEvent.php');	
			}
						

			# code...
		}else {
			echo "
			<div id='alerte' style='color:red; display: block;' >
             <p>Pseudo ou mot de passe incorrect</p>
           </div>";
				}

	}else echo "tous les champs doivent etre remplis <br>";
}

function inscriptionMembre($n,$d,$mp,$cmp,$statut,$dbh)
{
	$nom = $n;
	$date = $d;
	$mdp  = $mp;
	$conMdp = $cmp;

	if ($nom && $mdp && $conMdp && $date)
	{
		if ($mdp == $conMdp) 
		{
			$resultat = "select * from personne where PSEUDO='".$nom."'";
			$valeur = $dbh->query($resultat);
			//echo "nombre de ligne : ", $valeur->rowCount(),"<br>";

			if ($valeur->rowCount() == 0) 
			{	
				$crypte = sha1($mdp);
				$rqt = "INSERT INTO personne VALUES (null,'".$nom."','".$date."','".$crypte."',".$statut.")";
					echo "inscription reussie <br>";
					echo "<script type='text/javascript'>document.location.replace('connexion.php');</script>";
				$dbh->query($rqt);
			}
			else
			{
				echo "<script type='text/javascript'>invalidevent('ce pseudo a deja été utilisé')</script>";
			}
		}
		else 
		{
			echo "<script type='text/javascript'>invalidevent('les deux mots de passe doivent etre identiques' )</script>";
		}
	}
	else 
		echo "<script type='text/javascript'>invalidevent('tous les champs doivent être remplis')</script>";
}



function desincrireMembre($rendu)
{
	$dbh = connexionPDO('event');

	$rqt = "DELETE FROM personne WHERE PSEUDO='".$rendu."'";
	

	$dbh->query($rqt);
 
	echo "vous êtes bien desinscrit";
	echo header("location:test.php");

}

function statutChaine($nom)
{
	$dbh = connexionPDO('event');



	$rqt = "select ID_R from personne where PSEUDO='".$nom."'";
	$resultat = $dbh->query($rqt, PDO::FETCH_OBJ);
	

	
	foreach($resultat as $ligne)
	{
		

	    if( $ligne->ID_R == 0)
	    {
	    	return "Administrateur";
	    }
	    else if($ligne->ID_R == 1)
	    {

	    	return "Contributeur";
	    }
	    else{
	    	return "Visiteur";
	    }
	}


}

function statutNumero($nom)
{
	$dbh = connexionPDO('event');

	$rqt = "select ID_P from personne where PSEUDO='".$nom."'";

	
	$resultat = $dbh->query($rqt, PDO::FETCH_OBJ);
	
	foreach($resultat as $ligne)
	{
		return $ligne->ID_P;
	}


}


function ajoutEvent($dbh, $nom, $theme, $descrip, $min,$max, $ageMin, $dateD, $dateF, $adress,$longi,$lat,$role, $chemin)
{

	$rqt = "INSERT into evenement VALUES (null,\"".$nom."\",\"".$adress."\",".$longi.','.$lat.",\"".$theme."\",'".$dateD."','".$dateF."',\"".$descrip."\",".$min.",".$max.",".$ageMin.",\"".$chemin."\",0,".$role.")";


	$maxi = maxiIdeEvent($dbh);
	$maxi=$maxi + 1;
	$dbh->query($rqt);

	
}




	function long_latitude($adresse)
	{


		
	$url_geocodeurban = 'https://api-adresse.data.gouv.fr/search/?q=%s&autocomplete=0';
        //On intègre dans l'url l'adresse concaténée
        $url = vsprintf($url_geocodeurban, urlencode($adresse));
        // On récupère le résultat dans la variable $response
        $response = json_decode(file_get_contents($url));
       
        $longitude =  $response->features[0]->geometry->coordinates[0];
        $latitude = $response->features[0]->geometry->coordinates[1];
        
        // On prévoit une sortie
        
    
       
       $coordonnee = array(2);
       $coordonnee['Longitude']=$longitude;
       $coordonnee['Latitude']=$latitude;

       return $coordonnee;
	}

	function maxiIdeEvent($dbh)
	{

		$requete = "SELECT MAX(ID_E) as idmax from evenement ";

		$resultat = $dbh->query($requete, PDO::FETCH_OBJ);

		return $resultat->fetchColumn();


		
	}

	function envoiePhotoVersDossier($nom, $tmp)
	{
		
		if (move_uploaded_file($tmp,'.IMAGE/'.$nom )) {
			# code...
			echo "
			envoie reussi";
		}
	}


	





function inscrireAevent($dbh,$idp, $ide)
{

	$rqt ="INSERT into participe VALUES(".$idp.", ".$ide.")";

	$dbh->query($rqt);

}
function desinscrireDeEvent($dbh,$idp, $ide)
{

	$rqt = "DELETE FROM participe WHERE ID_P=".$idp." and ID_E=".$ide;

	$dbh->query($rqt);
	
}

function jourDeEvent($date)
{
	$i = date('w',strtotime($date));
	$d = date('d',strtotime($date));
	$m = date('M',strtotime($date));
	$y = date('Y',strtotime($date));
	$resultat;

		switch ($i) {
			case 0:
				$resultat = 'Dimanche, le '.$d.' '.$m.' '.$y;
				break;
			case 1:
				# code...
					$resultat = 'Lundi, le '.$d.' '.$m.' '.$y;
				break;
			case 2:
				# code...
					$resultat = 'Mardi, le '.$d.' '.$m.' '.$y;
				break;
			case 3:
				# code...
					$resultat = 'Mercredi, le '.$d.' '.$m.' '.$y;
				break;
			case 4:
				# code...
					$resultat = 'Jeudi, le '.$d.' '.$m.' '.$y;
				break;
			case 5:
				# code...
					$resultat = 'Vendredi, le '.$d.' '.$m.' '.$y;
				break;
			case 6:
				# code...
					$resultat = 'Samedi, le '.$d.' '.$m.' '.$y;
				break;
			
		}

		return $resultat;

}

function bonneFormatDate($date)
{
	$jour  = date('d',strtotime($date));
	$mois  = date('m',strtotime($date));
	$anne  = date('Y',strtotime($date));

	return $jour.'/'.$mois.'/'.$anne;
}


function donnerNote($idEvent,$PSEUDO)
{
	$dbh = connexionPDO('event');
	$rqt = "select TIMESTAMPDIFF(DAY,NOW(),DATE_F) from evenement WHERE ID_E=".$idEvent;



	$resultat= $dbh->query($rqt, PDO::FETCH_OBJ);

		$alors =  $resultat->fetchColumn();

		$rqt = "select * from participe,personne where participe.ID_P=personne.ID_P and personne.PSEUDO='".$PSEUDO."' and participe.ID_E=".$idEvent;



		$resultat = $dbh->query($rqt);

		$verdict = $resultat->rowCount();



		if ($alors < 0 and $verdict == 1) {
			
			return true;
		}
		else{
			return false;
		}
}



function noteEvenement($idEvent)
{
	$dbh = connexionPDO('event'); 

	$rqt = "SELECT AVG(NOTE) FROM `notation` WHERE ID_E =".$idEvent;

	$resultat= $dbh->query($requete, PDO::FETCH_OBJ);

		$note =  $resultat->fetchColumn();


		return round($note);


}



function noteToStars($note)
{
	$html;
	switch ($note) {

			case 1:
				$html ="<span class='fa fa-star checked'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span></p>";
			break;
			case 2:
				$html ="<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span></p>";
			break;
			case 3:
				$html ="<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span></p>";
			break;
			case 4:
				$html ="<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star'></span></p>";
			break;
			case 5:
				$html ="<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span>
				<span class='fa fa-star checked'></span></p>";
			break;

			default:
				$html ="<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span>
				<span class='fa fa-star'></span></p>";
			break;
			}
return $html;
}



function noteUsers($idEvent,$ID_P)
{
	$dbh=connexionPDO('event');
	$rqt1 = 'select * from notation WHERE ID_P='.$ID_P.' and ID_E='.$idEvent;
	$rqt2 = 'select NOTE from notation WHERE ID_P='.$ID_P.' and ID_E='.$idEvent;

	$resultat = $dbh->query($rqt1);


	if ($resultat->rowCount()==1) {

		$resultat = $dbh->query($rqt2, PDO::FETCH_OBJ);

		return $resultat->fetchColumn();
		
	}
	else{
		return 0;
	}

}

function triggerEstDeclancher()
{
	$rqt ="select * from logerror";
	$dbh = connexionPDO('event');
	$resultat = $dbh->query($rqt);

	if ($resultat->rowCount()==1) {
		
		return true;
	}

	return false;
}

function deleteAllTrigger()
{

	$rqt ="delete from logerror";
	$dbh = connexionPDO('event');
	$resultat = $dbh->query($rqt);

}


function getMessgaeTriggers()
{

	$rqt ="select MESSAGE from logerror";
	$dbh = connexionPDO('event');
	$resultat = $dbh->query($rqt);

	foreach ($resultat as  $value) {
		# code...
		return mb_strtolower($value['MESSAGE']);
	}
}

function suppevent($dbh, $ide)
{
	$rqt = "DELETE from Evennements where Evennements.ID_E=$ide";
	$dbh->query($rqt);
	$rqt = "DELETE from participe where participe.ID_E=$ide";
	$dbh->query($rqt);
	$rqt = "DELETE from Notation where Notation.ID_E=$ide";
	$dbh->query($rqt);
}



function supprimerImageDuDossier($photo)
{

    $homedir = __DIR__.'./IMAGE';

    $dir = opendir($homedir);
    if (false === $dir) {
        echo 'Echec: Impossible d\'explorer le dossier';
    } 
    else {

        while ($file = readdir($dir)) {

            if ($file!='.' and $file!='..') {
                # code...
                if ($file==$photo) {
                    # code...
                    $chemin=$homedir.'/'.$photo;
                    unlink($chemin);
                }
              

            }
           
        }
        closedir($dir);
    }
}

?>