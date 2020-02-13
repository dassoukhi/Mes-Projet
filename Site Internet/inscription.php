<!DOCTYPE html>
<html>
<head>
	<?php include('head.php'); ?>
	
	<link rel="stylesheet" type="text/css" href="login.css">

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<?php include('header.php'); ?>

<div class="wrapper fadeInDown">

  <div id="formContent">
    
    <!-- sign in Form -->
    <h5> Inscription </h5>

    <form action="" method="post">
      <input type="text" id="login" class="fadeIn third" name="identi" placeholder="pseudo" value="<?php if (isset($_POST['identi'])){echo $_POST['identi'];} ?>">
     
      <p> Date de naissance </p>

      <input type="date" id="datebirth" class="fadeIn third" name="date" placeholder="password" value="<?php if (isset($_POST['date'])){echo $_POST['date'];} ?>"> 

      <br><p> Mot de passe </p>

      <input type="password" id="password" class="fadeIn third" name="mdp" placeholder="password" value="<?php if (isset($_POST['mdp'])){echo $_POST['mdp'];} ?>">

      <p>Confirmation mot de passe</p>

      <input type="password" id="password-conf" class="fadeIn third" name="confir" placeholder="password" value="<?php if (isset($_POST['confir'])){echo $_POST['confir'];} ?>">

      
      <div class="">
        <p> S'inscrire en tant que : </p>

      <ul class="list-unstyled pl-sm-5 ml-sm-5 text-sm-left text-md-center px-md-0 mx-md-0">
        <li>Contributeur <input type="radio" class="fadeIn third " name="statut" value="contri"></li>
        
        <li>Visiteur <input type="radio"  class="fadeIn third" name="statut" value="visit" checked="checked"></li>
      </ul>
      </div> 
      
      <input type="submit" class="fadeIn fourth" name="inscrire" value="s'inscrire">
    </form>

    
  </div>
</div>


<?php include('footer.php'); ?>

<?php include('script.php');

    $idEvent=null;

    if (isset($_GET['click'])) {
      # code...
      $idEvent = $_GET['click'];

    }
    
    $dbh = connexionPDO('event');

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