
CREATE TABLE Trening (
    id  INTEGER NOT NULL    AUTO_INCREMENT,
    dato DATE NOT NULL,
    tidspunkt TIME NOT NULL,
    varighet FLOAT,
    notat VARCHAR(256),
    personlig_form TINYINT,
    prestasjon TINYINT,
    mal_navn VARCHAR(50),
    
    FOREIGN KEY(mal_navn) REFERENCES Mal (navn)
                          ON UPDATE CASCADE
                          ON DELETE SET NULL,
    PRIMARY KEY (id),
    
    CHECK(personlig_form BETWEEN 1 AND 10),
    CHECK(prestasjon BETWEEN 1 AND 10)
);

CREATE TABLE TreningsOvelse (
    trening_id  INTEGER NOT NULL,
    ovelse_id   INTEGER NOT NULL,
    FOREIGN KEY (trening_id) REFERENCES Trening (id),
    FOREIGN KEY (ovelse_id)  REFERENCES Ovelse (id),
    PRIMARY KEY (trening_id, ovelse_id)
);

CREATE TABLE Utendors (
    trening_id INTEGER NOT NULL,
    temperatur FLOAT,
    vaertype VARCHAR(20),
    
    FOREIGN KEY (trening_id) REFERENCES trening (id),
    PRIMARY KEY (trening_id)
);

CREATE TABLE Innendors(
    trening_id INTEGER NOT NULL,
    ventilasjon VARCHAR(50),
    tilskuere INTEGER,
    
    FOREIGN KEY (trening_id) REFERENCES trening (id),
    PRIMARY KEY (trening_id)
);

CREATE TABLE Ovelse(
    id INT NOT NULL AUTO_INCREMENT,
    navn VARCHAR(50),
    beskrivelse VARCHAR(200),

    PRIMARY KEY (id)
);

CREATE TABLE ligner(
    ovelse_id INTEGER NOT NULL,
    ligner_id INTEGER NOT NULL,
    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    FOREIGN KEY (ligner_id) REFERENCES Ovelse (id),
    PRIMARY KEY (ovelse_id, ligner_id)
);


CREATE TABLE kategori_ovelse(
    kategori_navn VARCHAR(50) NOT NULL,
    ovelse_id INTEGER NOT NULL,
    
    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    FOREIGN KEY (kategori_navn) REFERENCES Kategori (navn),
    PRIMARY KEY (ovelse_id, kategori_navn)
);


CREATE TABLE kategori_kategori(
    kategori_navn VARCHAR(50) NOT NULL,
    kategori_i VARCHAR(50) NOT NULL,
    FOREIGN KEY (kategori_navn) REFERENCES Kategori (navn),
    FOREIGN KEY (kategori_i) REFERENCES Kategori (navn),
    PRIMARY KEY (kategori_navn, kategori_i)
);


CREATE TABLE Kategori(
    navn VARCHAR(50),
    beskrivelse VARCHAR(200),

    PRIMARY KEY (navn)
);

CREATE TABLE Resultatlogg(
    id INTEGER NOT NULL AUTO_INCREMENT
    ovelse_id INTEGER,
    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    PRIMARY KEY (id)

);


CREATE TABLE Mal(
    navn VARCHAR(50),
    trening_id INTEGER NOT NULL,
    FOREIGN KEY (trening_id) REFERENCES Trening (id),
    PRIMARY KEY (navn)
);

CREATE TABLE utholdenhet_goal(
    id INTEGER NOT  NULL AUTO_INCREMENT,
    ovelse_id INTEGER NOT NULL,
    dato_paabegynt DATE NOT NULL,
    lengde FLOAT,
    varighet FLOAT,

    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    PRIMARY KEY (ovelse_id, id)
);

CREATE TABLE goal(
    id INTEGER NOT  NULL AUTO_INCREMENT,
    ovelse_id INTEGER NOT NULL,
    dato_paabegynt DATE NOT NULL,
    belastning INTEGER,
    antall_rep INTEGER,
    antall_set INTEGER,

    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    PRIMARY KEY (id)
);

CREATE TABLE utholdenhet_resultat (
    trening_id INTEGER NOT NULL,
    ovelse_id INTEGER NOT NULL,
    lengde FLOAT,
    varighet FLOAT,
    FOREIGN KEY (trening_id) REFERENCES Trening (id),
    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    PRIMARY KEY (ovelse_id, trening_id)
);

CREATE TABLE resultat (
    trening_id INTEGER NOT NULL,
    ovelse_id INTEGER NOT NULL,
    belastning INTEGER,
    antall_set INTEGER,
    antall_rep INTEGER,
    logg_id INTEGER,
    FOREIGN KEY (trening_id) REFERENCES Trening (id),
    FOREIGN KEY (ovelse_id) REFERENCES Ovelse (id),
    FOREIGN KEY (logg_id) REFERENCES Resultatlogg(id),
    PRIMARY KEY (ovelse_id, trening_id)
);