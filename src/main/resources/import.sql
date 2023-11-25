
INSERT INTO circuit (nom_circuit, adresse_circuit, longueur) VALUES ( 'Imertsiatosika, Antananarivo',  'Circuit Imerka ',8.4),( 'Lapantana Fianarantsoa', 'Circuit Lapantanana', 5.4),( 'Iboak,Fianarantsoa', 'Circuit Iboa', 8.4),( 'Dombosco,Fianarantsoa','Circuit Dombosco', 6.4),( 'Point de vue , Ambozotany Fianarantsoa', 'Circuit Point de vue ', 6.4),( 'Ambalapaiso Ambony, Fianarantsoa', 'Circuit d\'Ambalapaiso ', 5.4),( 'Ambatomena, Fianarantsoa','Circuit de CSFX ', 5.4),( 'Mdrawadjou Domoni adjou comores','Chatau',6.7);

INSERT INTO categorie (nom_categorie, description_categorie) VALUES( 'Categorie Catal', 'Dans cette categorie la puissance de la moto ne dois pas depassé les 90cc, le carbe de doit etre d\'origine avec un echappement catal Z');
INSERT INTO pilote (nom_pilote,prenom_pilote,numero_pilote, date_naissance, email_pilote, adresse_pilote, tel_pilote) VALUES ( 'Mlay','Malme be',44,'2023-08-08', 'malay@gmail.com','Mdrawadjou domoni-Adjou, Comores','0343644031'),( 'Zoro','Taks be',35,'2023-08-08', 'tax@gmail.com','Mdrambwani domoni-Adjou, Comores','0343644034');
INSERT INTO role (nom_role) VALUES ('admin');
INSERT INTO role (nom_role) VALUES ('visiteur');
INSERT INTO role (nom_role) VALUES ('ecurie');

INSERT INTO utilisateur(role_id, email, mdp_utilisateur) VALUES (1,'admin@email.com','$2a$10$MJn5SDUCgsm3XEyvELGQI.lcCzCXtxhA8hunr9jX9yvDd6/FkjxYO');
INSERT INTO utilisateur(role_id, email, mdp_utilisateur) VALUES (1,'exemple@email.com','$2a$10$bAa6hny3kv8Ehj6BCmHskuibB6Z/3n5p3eicOTorGiaLd8aRzg6b2');
INSERT INTO `ecurie` (`date_creation`, `id_ecurie`, `adresse_ecurie`, `description`, `email_ecurie`, `nom_ecurie`, `responsable`, `tel_ecurie`) VALUES ('2018-11-01', 1, 'Domoni-Ajoud', 'Garage 35 est specialisé dans les courses de cmoto 2 temps', 'garage35@gmail.com', 'Garage 35', 'Charane', '434343433');
INSERT INTO `ecurie` (`date_creation`, `id_ecurie`, `adresse_ecurie`, `description`, `email_ecurie`, `nom_ecurie`, `responsable`, `tel_ecurie`) VALUES ('2002-10-23', 2, 'Fianarantsoa,Madagascar', 'Garage de course de toute categorie ', 'garagetaxman@gmail.com', 'Tax Man', 'Ularion', '4343434332');