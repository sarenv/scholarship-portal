CREATE TABLE Applicant
(
    ApplicantID     INTEGER,
    firstName       VARCHAR(40),
    lastName        VARCHAR(40),
    applicantEmail  VARCHAR(40),
    applicantSchool VARCHAR(40),
    applicantGPA    FLOAT,
    PRIMARY KEY (ApplicantID)
);


CREATE TABLE Application
(
    ApplicationID INTEGER,
    ApplicantID   INTEGER,
    deadline      DATE,
    PRIMARY KEY (ApplicationID),
    FOREIGN KEY (ApplicantID)
        REFERENCES Applicant (ApplicantID)
        ON DELETE CASCADE
);

CREATE TABLE Donor
(
    donorID INTEGER,
    PRIMARY KEY (donorID)
);


CREATE TABLE  Scholarship
(
    scholarshipID INTEGER,
    amount        INTEGER,
    donorID       INTEGER,
    type          VARCHAR(20),
    PRIMARY KEY (scholarshipID),
    FOREIGN KEY (donorID)
        REFERENCES Donor (donorID)
            ON DELETE  CASCADE
);


CREATE TABLE Renewable
(
    scholarshipID INTEGER NOT NULL,
    amount        INTEGER,
    dateOfRenewal DATE,
    donorID       INTEGER,
    type VARCHAR(20) DEFAULT  'Renewable',
    PRIMARY KEY (scholarshipID),
    FOREIGN KEY (scholarshipID)
        REFERENCES  Scholarship
        ON DELETE CASCADE,
    FOREIGN KEY (donorID)
        REFERENCES  Donor
            ON DELETE CASCADE,
    CONSTRAINT renewable_type_check CHECK (type = 'Renewable')
);

CREATE TABLE OneTime
(
    scholarshipID INTEGER NOT NULL,
    amount        INTEGER,
    donorID       INTEGER,
    type VARCHAR(20) DEFAULT  'OneTime',
    PRIMARY KEY (scholarshipID),
    FOREIGN KEY (scholarshipID)
        REFERENCES  Scholarship
            ON DELETE CASCADE,
    FOREIGN KEY (donorID)
        REFERENCES  Donor
            ON DELETE CASCADE,
    CONSTRAINT onetime_type_check CHECK (type = 'OneTime')
);


CREATE TABLE AppliesTo
(
    ApplicationID INTEGER,
    scholarshipID INTEGER,
    ApplicantID   INTEGER,
    donorID       INTEGER,
    PRIMARY KEY (ApplicationID, scholarshipID, ApplicantID, donorID),
    FOREIGN KEY (ApplicationID)
        REFERENCES Application (ApplicationID)
        ON DELETE CASCADE,
    FOREIGN KEY (scholarshipID)
        REFERENCES Scholarship
        ON DELETE CASCADE,
    FOREIGN KEY (ApplicantID)
        REFERENCES Applicant (ApplicantID)
        ON DELETE CASCADE,
    FOREIGN KEY (donorID)
        REFERENCES Donor (donorID)
        ON DELETE CASCADE
);


CREATE TABLE SelectionCriteria
(
    criteriaID   INTEGER,
    minimumGPA   FLOAT,
    major        VARCHAR(40),
    familyIncome INTEGER,
    PRIMARY KEY (criteriaID)
);

CREATE TABLE ScholarshipCommittee
(
    committeeID INTEGER,
    PRIMARY KEY (committeeID)
);

CREATE TABLE Superintendent
(
    superintendentID INTEGER,
    firstName        VARCHAR(40),
    secondName       VARCHAR(40),
    PRIMARY KEY (superintendentID)
);

CREATE TABLE ReferenceLetter
(
    referenceID       INTEGER,
    applicationID     INTEGER,
    referenceName     VARCHAR(40),
    referenceEmail    VARCHAR(40),
    referenceSchool   VARCHAR(40),
    referencePosition VARCHAR(40),
    PRIMARY KEY (referenceID),
    FOREIGN KEY (applicationID)
        REFERENCES Application (applicationID)
        ON DELETE CASCADE
);

CREATE TABLE Evaluates
(
    ApplicationID INTEGER,
    committeeID   INTEGER,
    status        VARCHAR(40),
    PRIMARY KEY (ApplicationID, committeeID),
    FOREIGN KEY (ApplicationID)
        REFERENCES Application (ApplicationID)
            ON DELETE CASCADE,
    FOREIGN KEY (committeeID)
        REFERENCES ScholarshipCommittee (committeeID)
            ON DELETE CASCADE
);


INSERT INTO Applicant VALUES (12348, 'Jessica', 'Jones', 'jjones@ubc.ca', 'University of British Columbia', 3.69);
INSERT   INTO Applicant VALUES (45636, 'Emily', 'In Paris', 'ouibaguette@parisu.fr', 'Paris University', 4.00);
INSERT   INTO Applicant VALUES (55555, 'Michael', 'Michaels', 'mmichaels@ubc.ca', 'University of British Columbia', 2.41);
INSERT   INTO Applicant VALUES (98452, 'Elizabeth', 'Queen', 'lizzie@ubristol.uk', 'University of Bristol', 4.20);
INSERT   INTO Applicant VALUES (12345, 'Seuss', 'Doctor', 'heisadoctor@uvic.ca', 'University of Victoria', 3.52);

INSERT INTO Application VALUES (69421, 12348, TO_DATE('29/03/2021','dd/mm/yyyy'));
INSERT INTO Application VALUES(11111,12348, TO_DATE('08/09/20','dd/mm/yyyy'));
INSERT INTO Application VALUES(90002,55555, TO_DATE('31/01/22','dd/mm/yyyy'));
INSERT INTO Application VALUES(67492,98452, TO_DATE('02/04/23','dd/mm/yyyy'));
INSERT INTO Application VALUES(96477,45636, TO_DATE('15/11/21','dd/mm/yyyy'));

INSERT   INTO Donor VALUES (1000);
INSERT   INTO Donor VALUES(1001);
INSERT   INTO Donor VALUES(1002);
INSERT   INTO Donor VALUES(1003);
INSERT   INTO Donor VALUES(1004);

INSERT INTO Scholarship VALUES (178904, 1000000,1001,'OneTime');
INSERT INTO Scholarship VALUES(236049, 2000000,1001,'OneTime');
INSERT INTO Scholarship VALUES(306946,  1000000,1003,'OneTime');
INSERT INTO Scholarship VALUES(405069,  500000,1003,'OneTime');
INSERT INTO Scholarship VALUES(507968, 500000,1004,'OneTime');
INSERT INTO Scholarship VALUES (200358, 5000, 1002,'Renewable');
INSERT INTO Scholarship VALUES(200438,10000, 1002,'Renewable');
INSERT INTO Scholarship VALUES(222118, 20000, 1001,'Renewable');
INSERT INTO Scholarship VALUES(500332, 25000,1002,'Renewable');
INSERT INTO Scholarship VALUES(653453, 1000000,1000,'Renewable');


-- INSERT INTO OneTime VALUES (178904, 1000000,1001,'OneTime');
-- INSERT INTO OneTime VALUES(236049, 2000000,1001,'OneTime');
-- INSERT INTO OneTime VALUES(306946,  1000000,1003,'OneTime');
-- INSERT INTO OneTime VALUES(405069,  500000,1003,'OneTime');
-- INSERT INTO OneTime VALUES(507968, 500000,1004,'OneTime');
--
-- INSERT INTO Renewable VALUES (200358, 5000, TO_DATE('09/04','dd/mm'),1002,'Renewable');
-- INSERT INTO Renewable VALUES(200438,10000, TO_DATE('09/04','dd/mm'),1002,'Renewable');
-- INSERT INTO Renewable VALUES(222118, 20000, TO_DATE('17/02','dd/mm'),1001,'Renewable');
-- INSERT INTO Renewable VALUES(500332, 25000, TO_DATE('31/01','dd/mm'),1002,'Renewable');
-- INSERT INTO Renewable VALUES(653453, 1000000, TO_DATE('25/05','dd/mm'),1000,'Renewable');

INSERT INTO AppliesTo VALUES (69421, 200438, 12348, 1002);
INSERT INTO AppliesTo VALUES(11111,405069,12348,1003);
INSERT INTO AppliesTo VALUES(90002,178904,98452,1004);
INSERT INTO AppliesTo VALUES(96477,178904,55555,1001);
INSERT INTO AppliesTo VALUES(67492,222118,45636,1002);


INSERT   INTO SelectionCriteria VALUES (001, 3.20, 'Computer Science', 30000);
INSERT  INTO SelectionCriteria VALUES(002, 4.00, 'Computer Science', NULL);
INSERT INTO SelectionCriteria VALUES(003,  3.00,'Accounting', NULL);
INSERT  INTO SelectionCriteria VALUES(004,  2.40,'History', 10000);
INSERT  INTO SelectionCriteria VALUES(011,  4.0,'Political Science', NULL);

INSERT INTO ScholarshipCommittee VALUES (123);
INSERT INTO ScholarshipCommittee VALUES (234);
INSERT INTO ScholarshipCommittee VALUES (345);
INSERT INTO ScholarshipCommittee VALUES (456);
INSERT  INTO ScholarshipCommittee VALUES (567);


INSERT INTO Superintendent VALUES (02, 'James', 'Jameson');
INSERT INTO Superintendent VALUES(03, 'Jack', 'Johnson');
INSERT INTO Superintendent VALUES (04, 'Jon', 'Jackson');
INSERT INTO Superintendent VALUES(05, 'Jill', 'Jilly');
INSERT  INTO Superintendent VALUES (06, 'Sarah', 'Jones');

INSERT INTO ReferenceLetter VALUES (29568, 69421, 'Bob Wales', 'whales@faculty.uwales.uk', 'University of Wales', 'Professor');
INSERT INTO ReferenceLetter VALUES(40539, 11111, 'Monica Nguyen', 'monican@faculty.britu.uk' , 'Britain University', 'Professor');
INSERT INTO ReferenceLetter VALUES(65043, 90002, 'Tom Cat', 'ihatejerry@cat.hollywoodcol.us', 'Hollywood College', 'Housecat');
INSERT INTO ReferenceLetter VALUES(85968, 67492, 'Sam Lee', 'leevemealone@stu.socal.us', 'University of Southern California', 'Student');
INSERT INTO ReferenceLetter VALUES(92045, 96477, 'George Bay', 'baywatch@dean.georgetown.us', 'Georgetown University', 'Dean');


INSERT INTO Evaluates VALUES (69421, 123, 'accepted');
INSERT INTO Evaluates VALUES (11111, 234, 'accepted');
INSERT  INTO Evaluates VALUES (90002, 345, 'declined');
INSERT  INTO Evaluates VALUES (67492, 456, 'accepted');
INSERT INTO Evaluates VALUES (96477, 567, 'declined');





