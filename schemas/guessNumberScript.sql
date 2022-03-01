DROP DATABASE IF EXISTS GuessTheNumber;

CREATE DATABASE GuessTheNumber;

use GuessTheNumber;

DROP TABLE IF EXISTS game;
CREATE TABLE game (
	gameID INT AUTO_INCREMENT PRIMARY KEY,
	answer VARCHAR(4) NOT NULL,
	inProgress boolean default TRUE
);

DROP TABLE IF EXISTS round;
CREATE TABLE round (
	roundID INT AUTO_INCREMENT PRIMARY KEY,
	gameID INT NOT NULL,
    guess VARCHAR(4) NOT NULL,
	guessTime datetime DEFAULT current_timestamp,
	result VARCHAR(7) NOT NULL,
	CONSTRAINT fk_round_game
		FOREIGN KEY (gameID)
		REFERENCES game(gameID)
);


