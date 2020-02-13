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

 <!-- Login Form -->

  <div id="formContent">
   
     <h5> Connexion </h5>
    <form action="" method="post">
      <input type="text" id="login" class="fadeIn third" name="Pseudo" placeholder="login" value="<?php if (isset($_POST['Pseudo'])){echo $_POST['Pseudo'];} ?>">
      <input type="password" id="password" class="fadeIn third" name="password" placeholder="password" value="<?php if (isset($_POST['password'])){echo $_POST['password'];} ?>">
      <?php 
       $idEvent=null;

    if (isset($_GET['click'])) {
      # code...
      $idEvent = $_GET['click'];

    }
      if (isset($_POST['connecter'])) {
      $dbh = connexionPDO('event');
      connexionMembre($_POST['Pseudo'],$_POST['password'],$dbh,$idEvent);
    }
?>
     
      <input type="submit" class="fadeIn fourth" value="Log In" name="connecter">
    </form>
  
  <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">Forgot Password?</a>
    </div>
  </div>
</div>


<?php include('footer.php'); ?>


  <?php include('script.php');

   

    
    ?>

    

</html>