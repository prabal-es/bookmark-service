CREATE TABLE T_COMPANY
(
	UUID 			uuid default random_uuid(),
	NAME 			VARCHAR(50) NOT NULL,
	DESCRIPTION		VARCHAR(500),
	IMG				VARCHAR(2083),
	URL				VARCHAR(2083),
	ACTIVE			BOOLEAN,
	PRIMARY KEY (UUID)
);
INSERT INTO T_COMPANY(UUID, NAME, DESCRIPTION, IMG, URL, ACTIVE) VALUES ('629fab9a-0f46-4925-8e25-4037069f7dfd', 'Societe Generale', 'Societe Generale S.A., often nicknamed SocGen, is a French multinational investment bank and financial services company headquartered in Paris, France.', 'https://arizent.brightspotcdn.com/1e/0d/50f4d8ae48c2bf67891ffd56fe05/societe-generale-bl-080415.jpg', 'https://www.societegenerale.com/en/home', true);
INSERT INTO T_COMPANY(UUID, NAME, DESCRIPTION, IMG, URL, ACTIVE) VALUES (random_uuid(), 'ADP, LLC', 'Automatic Data Processing, Inc., commonly known as ADP, is an American provider of human resources management software and services. ADP pays more than 36 million workers worldwide and serves more than 860,000 businesses from small business to global enterprise in 140 countries.', 'https://i0.wp.com/www.diversityinc.com/media/2020/06/ADP-Bilding.jpg?w=768&ssl=1', 'https://www.adp.in', false);



CREATE TABLE T_USER
(
	UUID 			uuid default uuid_generate_v4(),
	NAME 			VARCHAR(50) NOT NULL,
	ROLE			VARCHAR(10) NOT NULL,
	ACTIVE			BOOLEAN,
	COMAPNY_ID		uuid NOT NULL,
	PRIMARY KEY (UUID),
	FOREIGN KEY (COMAPNY_ID) REFERENCES T_COMPANY(UUID)
);

INSERT INTO T_USER(UUID, NAME, ROLE, ACTIVE, COMAPNY_ID) VALUES (uuid_generate_v4(), 'Prabal Srivastava', 'ADMIN', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_USER(UUID, NAME, ROLE, ACTIVE, COMAPNY_ID) VALUES (uuid_generate_v4(), 'Anshu Gupta', 'USER', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_USER(UUID, NAME, ROLE, ACTIVE, COMAPNY_ID) VALUES (uuid_generate_v4(), 'Anand Singh', 'USER', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
