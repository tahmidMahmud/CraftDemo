INSERT INTO vet VALUES (1, 'James', 'Carter');
INSERT INTO vet VALUES (2, 'Helen', 'Leary');
INSERT INTO vet VALUES (3, 'Linda', 'Douglas');
INSERT INTO vet VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vet VALUES (5, 'Henry', 'Stevens');
INSERT INTO vet VALUES (6, 'Sharon', 'Jenkins');
INSERT INTO vet VALUES (7, 'Jerry', 'Phillips');

INSERT INTO vet_specialties VALUES (1, 'radiology');
INSERT INTO vet_specialties VALUES (1, 'surgery');
INSERT INTO vet_specialties VALUES (1, 'dentistry');
INSERT INTO vet_specialties VALUES (2, 'radiology');
INSERT INTO vet_specialties VALUES (2, 'surgery');
INSERT INTO vet_specialties VALUES (3, 'dentistry');

INSERT INTO owner VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', 'George.Franklin@gmail.com', '6085551023');
INSERT INTO owner VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', 'Betty.Davis@gmail.com', '6085551749');
INSERT INTO owner VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', 'Eduardo.Rodriquez@gmail.com', '6085558763');
INSERT INTO owner VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', 'Harold.Davis@gmail.com', '6085553198');
INSERT INTO owner VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', 'Peter.McTavish@gmail.com', '6085552765');
INSERT INTO owner VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', 'Jean.Coleman@gmail.com', '6085552654');
INSERT INTO owner VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', 'Jeff.Black@gmail.com', '6085555387');
INSERT INTO owner VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', 'Maria.Escobito@gmail.com', '6085557683');
INSERT INTO owner VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', 'David.Schroeder@gmail.com', '6085559435');
INSERT INTO owner VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee',  'Carlos.Estaban@gmail.com', '6085555487');

INSERT INTO pet VALUES (1, 'Leo', '2010-09-07', 'cat', 1);
INSERT INTO pet VALUES (2, 'Basil', '2012-08-06', 'cat', 2);
INSERT INTO pet VALUES (3, 'Rosy', '2011-04-17', 'cat', 3);
INSERT INTO pet VALUES (4, 'Jewel', '2010-03-07', 'cat', 3);
INSERT INTO pet VALUES (5, 'Iggy', '2010-11-30', 'cat', 4);
INSERT INTO pet VALUES (6, 'George', '2010-01-20', 'dog', 5);
INSERT INTO pet VALUES (7, 'Samantha', '2012-09-04', 'dog', 6);
INSERT INTO pet VALUES (8, 'Max', '2012-09-04', 'dog', 6);
INSERT INTO pet VALUES (9, 'Lucky', '2011-08-06', 'dog', 7);
INSERT INTO pet VALUES (10, 'Mulligan', '2007-02-24', 'lizard', 8);
INSERT INTO pet VALUES (11, 'Freddy', '2010-03-09', 'lizard', 9);
INSERT INTO pet VALUES (12, 'Lucky', '2010-06-24', 'lizard', 10);
INSERT INTO pet VALUES (13, 'Sly', '2012-06-08', 'lizard', 10);

INSERT INTO visit VALUES (1, 7, 2, '2013-01-01 08:30:00', '2013-01-01 09:45:00', 'rabiesshot' );
INSERT INTO visit VALUES (2, 3, 1, '2013-01-02 08:30:00', '2013-01-02 09:45:00', 'rabiesshot' );
INSERT INTO visit VALUES (3, 5, 2, '2013-01-03 08:30:00', '2013-01-03 09:45:00', 'neutered' );
INSERT INTO visit VALUES (4, 7, 3, '2013-01-04 08:30:00', '2013-01-04 09:45:00', 'spayed' );