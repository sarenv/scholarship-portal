CREATE TABLE Applicant (
    ApplicantID	INTEGER,
                           firstName		VARCHAR(40),
                           lastName		VARCHAR(40),
                           applicantSchool	VARCHAR(40),
                           applicantGPA	DECIMAL(4,2),
                           applicantEmail	VARCHAR(40),
                           PRIMARY KEY (ApplicantID)
)


CREATE TABLE Application (
                             ApplicationID 	INTEGER,
                             deadline		DATE,
                             ApplicantID	INTEGER,
                             PRIMARY KEY (ApplicationID),
                             FOREIGN KEY (ApplicantID)
                                 REFERENCES Applicant(ApplicantID)
                                 ON DELETE CASCADE
)

CREATE TABLE AppliesTo (
                           ApplicationID 	INTEGER,
                           ScholarshipID	INTEGER,
                           ApplicantID	INTEGER,
                           donorID	      INTEGER,
                           PRIMARY KEY (ApplicationID, ScholarshipID, ApplicantID,       donorID),
                           FOREIGN KEY (ApplicationID)
                               REFERENCES Application(ApplicationID)
                               ON DELETE CASCADE,
                           FOREIGN KEY (ScholarshipID)
                               REFERENCES OneTime(ScholarshipID)
                               ON DELETE CASCADE,
                           FOREIGN KEY (ScholarshipID)
                               REFERENCES Renewable(ScholarshipID)
                               ON DELETE CASCADE,
                           FOREIGN KEY (ApplicantID)
                               REFERENCES Applicant(ApplicantID)
                               ON DELETE CASCADE,
                           FOREIGN KEY (donorID)
                               REFERENCES Donor(donorID)
                               ON DELETE CASCADE
)

CREATE TABLE Evaluates (
                           ApplicationID 	INTEGER,
                           committeeID 	INTEGER,
                           status 	      VARCHAR(40),
                           PRIMARY KEY (ApplicationID, committeeID),
                           FOREIGN KEY (ApplicationID)
                               REFERENCES Application(ApplicationID)
                               ON DELETE CASCADE,
                           FOREIGN KEY (committeeID)
                               REFERENCES ScholarshipCommittee(committeeID)
                               ON DELETE CASCADE
)

CREATE TABLE SelectionCriteria (
                                   criteriaID 	INTEGER,
                                   minimumGPA		DECIMAL(4,2),
                                   major           VARCHAR(40),
                                   familyIncome	VARCHAR(40),
                                   PRIMARY KEY (criteriaID)
)

CREATE TABLE ScholarshipCommittee (
                                      committeeID 	INTEGER,
                                      PRIMARY KEY (committeeID)
)

CREATE TABLE Superintendent (
                                superintendentID	INTEGER,
                                firstName		VARCHAR(40),
                                secondName		VARCHAR(40),
                                PRIMARY KEY (superintendentID)
)

CREATE TABLE Renewable (
                           scholarshipID	INTEGER,
                           amount 		INTEGER,
                           dateOfRenewal	DATE,
                           donorID		INTEGER NOT NULL,
                           PRIMARY KEY (scholarshipID),
                           FOREIGN KEY (donorID)
                               REFERENCES Donor(donorID)
                               ON DELETE CASCADE
)

CREATE TABLE OneTime (
                         scholarshipID	INTEGER,
                         amount 		INTEGER,
                         donorID		INTEGER NOT NULL,
                         PRIMARY KEY (scholarshipID),
                         FOREIGN KEY (donorID)
                             REFERENCES Donor(donorID)
                             ON DELETE CASCADE
)

CREATE TABLE Donor (
                       donorID 		INTEGER,
                       PRIMARY KEY (donorID)
)

CREATE TABLE ReferenceLetter (
                                 referenceID		INTEGER,
                                 applicationID		INTEGER,
                                 referenceName         VARCHAR(40),
                                 referenceEmail        VARCHAR(40),
                                 referenceSchool       VARCHAR(40),
                                 referencePosition     VARCHAR(40),
                                 PRIMARY KEY (referenceID),
                                 FOREIGN KEY (applicationID)
                                     REFERENCES Application (applicationID)
                                     ON DELETE CASCADE
)


    INSERT INTO Applicant
VALUES (12348, 'Jessica', 'Jones', 'jjones@ubc.ca', 'University of British Columbia', 3.69);
(45636, 'Emily', 'In Paris', 'ouibaguette@parisu.fr', 'Paris University', 4.00);
(55555, 'Michael', 'Michaels', 'mmichaels@ubc.ca', 'University of British Columbia', 2.41);
(98452, 'Elizabeth', 'Queen', 'lizzie@ubristol.uk', 'University of Bristol', 4.20);
(12345, 'seuss', 'doctor', 'heisadoctor@uvic.ca', 'University of Victoria', 3.52);

INSERT INTO Application
VALUES (69421, 29/03/2021);
(11111, 08/09/2023);
(90002, 31/01/2024);
(67492, 02/04/2023);
(96477, 15/11/2021);

INSERT INTO ReferenceLetter
VALUES (29568, 69421, 'Bob Wales', 'whales@faculty.uwales.uk', 'University of Wales', 'Professor');
(40539, 11111, 'Monica Nguyen', 'monican@faculty.britu.uk' , 'Britain University', 'Professor');
(65043, 90002, 'Tom Cat', 'ihatejerry@cat.hollywoodcol.us', 'Hollywood College', 'Housecat');
(85968, 67492, 'Sam Lee', 'leevemealone@stu.socal.us', 'University of Southern California', 'Student');
(92045, 96477, 'George Bay', 'baywatch@dean.georgetown.us', 'Georgetown University', 'Dean');

INSERT INTO SelectionCriteria
VALUES (001, ‘Computer Science’, 3.20, 30000);
(002, ‘Computer Science’, 4.00, NULL);
(003, ‘Accounting’, 3.00, NULL);
(004, ‘History’, 2.40, 10000);
(011, ‘Political Science’, 4.0, NULL);

INSERT INTO ScholarshipCommittee
VALUES (123);
(234);
(345);
(456);
(567);

INSERT INTO Evaluates
VALUES (69421, 123, 'accepted');
(11111, 234, 'accepted');
(90002, 345, 'declined');
(67492, 456, 'accepted');
(96477, 567, 'declined');

INSERT INTO OneTime
VALUES (178904, 1001, 1000000);
(236049, 1001, 2000000);
(306946, 1003, 1000000);
(405069, 1003, 500000);
(507968, 1004, 500000);

INSERT INTO Renewable
VALUES (200358,1002, 5000, 09/04);
(200438,1002, 10000, 09/04);
(222118,1002, 20000, 17/02);
(500332,1001, 25000, 31/01);
(653453,1000, 1000000, 25/05);

INSERT INTO Donor
VALUES (1000);
(1001);
(1002);
(1003);
(1004);


INSERT INTO Superintendent
VALUES (02, 'James', 'Jameson');
(03, 'Jack', 'Johnson');
(04, 'Jon', 'Jackson');
(05, 'Jill', 'Jilly');
(06, 'Sarah', 'Sarahon');

INSERT INTO AppliesTo
VALUES (69421,200438,12348,1002);
(11111,405069,12348,1003);
(90002,178904,98452,1004);
(96477,178904,55555,1001);
(67492,222118,45636,1002);