CREATE TABLE T_COMPANY
(
	UUID 			uuid default random_uuid(),
	URL_CONTEXT		VARCHAR(50) NOT NULL UNIQUE, -- should be lower case with hyphen
	NAME 			VARCHAR(50) NOT NULL,
	DESCRIPTION		VARCHAR(500),
	IMG				VARCHAR(2083),
	URL				VARCHAR(2083),
	ACTIVE			BOOLEAN,
	PRIMARY KEY (UUID)
);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES ('629fab9a-0f46-4925-8e25-4037069f7dfd', 'Societe Generale', 'soc-gen', 'Société Générale S.A., often nicknamed "SocGen", is a French multinational investment bank and financial services company headquartered in Paris, France. The company is a universal bank and has divisions supporting French Networks, Global Transaction Banking, International Retail Banking, Financial Services, Corporate and Investment Banking, Private Banking, Asset Management and Securities Services.', 'https://arizent.brightspotcdn.com/1e/0d/50f4d8ae48c2bf67891ffd56fe05/societe-generale-bl-080415.jpg', 'https://www.societegenerale.com/en/home', true);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES (random_uuid(), 'ADP, LLC', 'adp', 'Automatic Data Processing, Inc., commonly known as ADP, is an American provider of human resources management software and services. ADP pays more than 36 million workers worldwide and serves more than 860,000 businesses from small business to global enterprise in 140 countries.', 'https://i0.wp.com/www.diversityinc.com/media/2020/06/ADP-Bilding.jpg?w=768&ssl=1', 'https://www.adp.in', false);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES (random_uuid(), 'Hewlett-Packard', 'hp', 'The Hewlett-Packard Company, commonly shortened to Hewlett-Packard or HP was an American multinational information technology company headquartered in Palo Alto, California, that developed and provided a wide variety of hardware components, as well as software and related services to consumers, small and medium-sized businesses (SMBs) and large enterprises, including customers in the government, health and education sectors.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/HP_Headquarters_Palo_Alto.jpg/1024px-HP_Headquarters_Palo_Alto.jpg', 'https://www8.hp.com/in/en/home.html', false);

CREATE TABLE T_USER
(
	UUID 			uuid default random_uuid(),
	NAME 			VARCHAR(50) NOT NULL,
	URL_CONTEXT		VARCHAR(50) NOT NULL UNIQUE, -- should be lower case with hyphen
	ROLE			VARCHAR(10) NOT NULL,
	IMG				VARCHAR(2083),
	URL				VARCHAR(2083),
	ACTIVE			BOOLEAN,
	COMAPNY_ID		UUID NOT NULL,
	PRIMARY KEY 	(UUID),
	FOREIGN KEY 	(COMAPNY_ID) REFERENCES T_COMPANY(UUID)
);

INSERT INTO T_USER(UUID, NAME, URL_CONTEXT, ROLE, IMG, URL, ACTIVE, COMAPNY_ID) VALUES ('c17480ef-b7c3-4399-abbd-42b2aba7dfe6', 'Prabal Srivastava', 'prabal9', 'ADMIN', 'https://media-exp1.licdn.com/dms/image/C5103AQHOMMN3dmz8fQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=ieUBHGr4nYtL4i5OrIxxe8fMtNUSwWjcq9qBMJfgURg', 'https://www.linkedin.com/in/prabal9', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_USER(UUID, NAME, URL_CONTEXT, ROLE, IMG, URL, ACTIVE, COMAPNY_ID) VALUES (random_uuid(), 'Anshu Gupta', 'anshu', 'USER', 'https://media-exp1.licdn.com/dms/image/C4D03AQFsFNYvN1K1KQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=X2TCVZgxxeQRWNTwKX4_BuexeDYy5ocX5_ZSzi2o2FY', 'https://www.linkedin.com/in/anshu-gupta-91271621', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_USER(UUID, NAME, URL_CONTEXT, ROLE, IMG, URL, ACTIVE, COMAPNY_ID) VALUES (random_uuid(), 'Anand Singh', 'anand','USER', 'https://media-exp1.licdn.com/dms/image/C5103AQHBE-mur4EoEg/profile-displayphoto-shrink_800_800/0?e=1605744000&v=beta&t=lsWiLZx8R0cEZg8sUjMlWm9F1sUmzPSrxIuQEf1kROM', 'https://www.linkedin.com/in/anand-singh-19a288a1', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
