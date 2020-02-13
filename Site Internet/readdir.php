<?php
// Définition du chemin à explorer (adaptez à votre environnement)

function supprimerImageDuDossier($photo)
{

    $homedir = __DIR__.'./IMAGE';

// "ouverture" du répertoire
    $dir = opendir($homedir);
    if (false === $dir) {
        echo 'Echec: Impossible d\'explorer le dossier';
    } 
    else {

        while ($file = readdir($dir)) {
            // Affichage du nom du fichier (ou sous-répertoire)
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
