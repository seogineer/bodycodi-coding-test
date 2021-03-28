DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `messages`;

CREATE TABLE `users` (
    `id`  INTEGER PRIMARY KEY AUTOINCREMENT,
    `user_name` varchar(20) NOT NULL,
    `password` varchar(10) NOT NULL
);

CREATE TABLE "messages" (
	"id"	INTEGER,
	"sender"	INTEGER NOT NULL,
	"recipient"	INTEGER NOT NULL,
	"type"	TEXT,
	"url"	TEXT,
	"text"	TEXT,
	"timestamp"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
