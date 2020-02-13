/*
FIchier : Creation_GroupeQ.sql
Auteurs : 
Dassoukhi Saleh 21613755
Fontaine Quentin 21611404
Prud'Homme Gateau Sebastien 21712267
Nom du groupe : Q
*/

/* Tests des triggers */

/* Empêche de créer un noveau rôle, car tous les rôles sont déjà créés 
 Trigger : NON_DANS_ROLE */
INSERT INTO ROLE VALUES (5,"ADMINISTRATEUR");

/* Empêche de créer une personne avec un rôle inexistant 
 Trigger : ROLE_EXIST */
INSERT INTO PERSONNE VALUES (NULL,"ZAÏN","1999-10-29","777",4);


/*Empecher les visiteur d'ajouter un evenement
trigger : ACCEES_PRIORITAIRE */
INSERT INTO EVENEMENT VALUES (NULL,"B","125 AVENUE AUGUSTIN FLICHE",3,43,"SEXE","2019-10-29 03:00:00","2019-11-01 07:00:00",NULL,NULL,2,18,"IMAGE/a.jpg",2);

/*Empecher l'ajout d'un participant si nombre des places dispo atteint
trigger : PAS_DE_PLACE */
INSERT INTO PARTICIPE VALUES (6,3);


/* empecher la participation à un evenement pour une personne qui n'a pas l'age requis
trigger : AGE_REQUIS */
INSERT INTO PARTICIPE VALUES (6,1);

/* empecher l'ajout d'un evenement dont la date est depassée
trigger : DEPASSE_DATE */
INSERT INTO EVENEMENT VALUES (NULL,"vernissage","125 AVENUE AUGUSTIN FLICHE",3,43,"CONFERENCE","2019-10-29 03:00:00","2019-11-01 07:00:00",NULL,NULL,2,15,"IMAGE/a.jpg",1);


/* empecher l'ajout d'un evenement se situant a plus de 30km autour Montpellier
trigger : DISTANCE_AUTORISEE */
INSERT INTO EVENEMENT VALUES  (NULL,"vernissage","125 RUE JEAN MACE",1.4437,43.6043,"CONFERENCE","2019-10-29 03:00:00","2020-12-21 07:00:00",NULL,NULL,2,15,"IMAGE/a.jpg",1);




/* Tests des fonctions */

/* Permet de savoir le nombre de place restant à un évènement */
-- Fonction : NB_PLACE
SELECT NB_PLACE(1);
SELECT NB_PLACE(2);
SELECT NB_PLACE(3);

/* permet de calculer le nombre de jours, de mois, et d'annee restant avant l'evenement */
SELECT JOURS_AVANT_EVENT(1);

/* permet de calculer l'age d'une personne à partir de son ID*/
SELECT CALCUL_AGE_PERSONNE(1);

/* calcule le nombre des personne inscrit à un evenement */
SELECT NB_PERSONNE_INSCRIT(2);

/* calcule de distance entre MONTPELLIER et LUNEL */
SELECT DISTANCE_ENTRE_ADRESSE(4.138053 , 43.675739);

/* calcule la duree d'un evenement */
SELECT DUREE_EVENT(2);
