CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE T_COMPANY
(
	UUID 			uuid default uuid_generate_v4(),
	URL_CONTEXT		VARCHAR(50) NOT NULL UNIQUE, -- should be lower case with hyphen
	NAME 			VARCHAR(50) NOT NULL,
	DESCRIPTION		VARCHAR(500),
	IMG				VARCHAR(2083),
	URL				VARCHAR(2083),
	ACTIVE			BOOLEAN,
	PRIMARY KEY (UUID)
);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES ('629fab9a-0f46-4925-8e25-4037069f7dfd', 'Google, LLC', 'google', 'Google, LLC is an American multinational technology company that specializes in Internet-related services and products, which include online advertising technologies, a search engine, cloud computing, software, and hardware. It is considered one of the Big Four technology companies, alongside Amazon, Apple, and Microsoft.', 'https://media.xconomy.com/wordpress/wp-content/images/2015/08/06160438/Google-HQ-e1518800521672.jpg', 'https://en.wikipedia.org/wiki/Google', true);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES (uuid_generate_v4(), 'ADP, LLC', 'adp', 'Automatic Data Processing, Inc., commonly known as ADP, is an American provider of human resources management software and services. ADP pays more than 36 million workers worldwide and serves more than 860,000 businesses from small business to global enterprise in 140 countries.', 'https://i0.wp.com/www.diversityinc.com/media/2020/06/ADP-Bilding.jpg?w=768&ssl=1', 'https://www.adp.in', false);
INSERT INTO T_COMPANY(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, URL, ACTIVE) VALUES (uuid_generate_v4(), 'Hewlett-Packard', 'hp', 'The Hewlett-Packard Company, commonly shortened to Hewlett-Packard or HP was an American multinational information technology company headquartered in Palo Alto, California, that developed and provided a wide variety of hardware components, as well as software and related services to consumers, small and medium-sized businesses (SMBs) and large enterprises, including customers in the government, health and education sectors.', 'https://upload.wikimedia.org/wikipedia/commons/thumb/0/0a/HP_Headquarters_Palo_Alto.jpg/1024px-HP_Headquarters_Palo_Alto.jpg', 'https://www8.hp.com/in/en/home.html', false);



CREATE TABLE T_USER
(
	UUID 			uuid default uuid_generate_v4(),
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
INSERT INTO T_USER(UUID, NAME, URL_CONTEXT, ROLE, IMG, URL, ACTIVE, COMAPNY_ID) VALUES (uuid_generate_v4(), 'Anshu Gupta', 'anshu', 'USER', 'https://media-exp1.licdn.com/dms/image/C4D03AQFsFNYvN1K1KQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=X2TCVZgxxeQRWNTwKX4_BuexeDYy5ocX5_ZSzi2o2FY', 'https://www.linkedin.com/in/anshu-gupta-91271621', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_USER(UUID, NAME, URL_CONTEXT, ROLE, IMG, URL, ACTIVE, COMAPNY_ID) VALUES (uuid_generate_v4(), 'Anand Singh', 'anand','USER', 'https://media-exp1.licdn.com/dms/image/C5103AQHBE-mur4EoEg/profile-displayphoto-shrink_800_800/0?e=1605744000&v=beta&t=lsWiLZx8R0cEZg8sUjMlWm9F1sUmzPSrxIuQEf1kROM', 'https://www.linkedin.com/in/anand-singh-19a288a1', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');



CREATE TABLE T_GROUP
(
	UUID 			uuid default uuid_generate_v4(),
	NAME 			VARCHAR(50) NOT NULL,
	URL_CONTEXT		VARCHAR(50) NOT NULL UNIQUE, -- should be lower case with hyphen
	DESCRIPTION		VARCHAR(500),
	IMG				VARCHAR(2083), 
	ACTIVE			BOOLEAN,
	COMAPNY_ID		UUID NOT NULL,
	PRIMARY KEY 	(UUID),
	FOREIGN KEY 	(COMAPNY_ID) REFERENCES T_COMPANY(UUID)
);

INSERT INTO T_GROUP(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, ACTIVE, COMAPNY_ID) VALUES ('2cb44d2a-63b1-4aad-8957-44c3203bea76', 'Risk Management', 'rm', 'Risk Management Group.', 'https://reciprocitylabs.com/wp-content/uploads/2019/09/bigstock-Risk-management-in-word-tag-cl-21022796-768x584.jpg', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_GROUP(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, ACTIVE, COMAPNY_ID) VALUES ('6e4cf9e1-8710-4f80-9aa5-3cd3bc0c58a9', 'FireFly', 'firefly', 'A feature team', 'https://cff2.earth.com/uploads/2018/07/16131005/Your-summertime-guide-to-enjoying-the-magic-of-fireflies-768x432.jpg', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');
INSERT INTO T_GROUP(UUID, NAME, URL_CONTEXT, DESCRIPTION, IMG, ACTIVE, COMAPNY_ID) VALUES ('6c440055-7e30-4847-b446-22706895c9df', 'Engineering Stream', 'engineering-stream', 'On going hackathon.', 'https://bookmark-9.herokuapp.com/assets/engg_stream_hackathon.png', true, '629fab9a-0f46-4925-8e25-4037069f7dfd');



CREATE TABLE T_CARD
(
	UUID 			uuid default uuid_generate_v4(),
	NAME 			VARCHAR(50),
	DESCRIPTION		VARCHAR(500),
	TINY_URL		VARCHAR(50) NOT NULL UNIQUE,
	DETAIL_URL		VARCHAR(2083), 
	IMG				VARCHAR(2083),
	TYPE			VARCHAR(10) NOT NULL,
	CREATED_AT		BIGINT NOT NULL,
	EXPIRE_AT		BIGINT,
	ACTIVE			BOOLEAN,
	COMAPNY_ID		UUID NOT NULL,
	USER_ID			UUID NOT NULL,
	PRIMARY KEY 	(UUID),
	FOREIGN KEY 	(COMAPNY_ID) REFERENCES T_COMPANY(UUID),
	FOREIGN KEY 	(USER_ID) REFERENCES T_USER(UUID)
);
--Tiny
INSERT INTO T_CARD(UUID, NAME, DESCRIPTION, TINY_URL, DETAIL_URL, IMG, TYPE, CREATED_AT, EXPIRE_AT, ACTIVE, COMAPNY_ID, USER_ID) 
VALUES (uuid_generate_v4(), null, null, 'ad825c6a', 'https://engineering-stream-hackathon.github.io/challenge', 'https://media-exp1.licdn.com/dms/image/C5103AQHOMMN3dmz8fQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=ieUBHGr4nYtL4i5OrIxxe8fMtNUSwWjcq9qBMJfgURg',
'TINY', 1600166936000, null, true, '629fab9a-0f46-4925-8e25-4037069f7dfd', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');
INSERT INTO T_CARD(UUID, NAME, DESCRIPTION, TINY_URL, DETAIL_URL, IMG, TYPE, CREATED_AT, EXPIRE_AT, ACTIVE, COMAPNY_ID, USER_ID) 
VALUES (uuid_generate_v4(), null, null, 'f46c62f2', 'https://gitter.im/engineering-stream-hackathon/community', 'https://media-exp1.licdn.com/dms/image/C5103AQHOMMN3dmz8fQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=ieUBHGr4nYtL4i5OrIxxe8fMtNUSwWjcq9qBMJfgURg',
'TINY', 1600166936000, null, true, '629fab9a-0f46-4925-8e25-4037069f7dfd', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');
INSERT INTO T_CARD(UUID, NAME, DESCRIPTION, TINY_URL, DETAIL_URL, IMG, TYPE, CREATED_AT, EXPIRE_AT, ACTIVE, COMAPNY_ID, USER_ID) 
VALUES (uuid_generate_v4(), null, null, '329ae1b5', 'https://dashboard.heroku.com/', 'https://media-exp1.licdn.com/dms/image/C5103AQHOMMN3dmz8fQ/profile-displayphoto-shrink_200_200/0?e=1605744000&v=beta&t=ieUBHGr4nYtL4i5OrIxxe8fMtNUSwWjcq9qBMJfgURg',
'TINY', 1600166936000, null, true, '629fab9a-0f46-4925-8e25-4037069f7dfd', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');

--Card
INSERT INTO T_CARD(UUID, NAME, DESCRIPTION, TINY_URL, DETAIL_URL, IMG, TYPE, CREATED_AT, EXPIRE_AT, ACTIVE, COMAPNY_ID, USER_ID) 
VALUES ('29c1a1f4-96c5-4769-b37a-d39e6bff2afe', 'ES hackathon', 'Engineering stream hackathon problem statement', '811ec2f0', 'https://engineering-stream-hackathon.github.io/challenge/#/', 'https://bookmark-9.herokuapp.com/assets/engg_stream_hackathon.png',
'CARD', 1600166936000, null, true, '629fab9a-0f46-4925-8e25-4037069f7dfd', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');
INSERT INTO T_CARD(UUID, NAME, DESCRIPTION, TINY_URL, DETAIL_URL, IMG, TYPE, CREATED_AT, EXPIRE_AT, ACTIVE, COMAPNY_ID, USER_ID) 
VALUES ('417246e5-647e-476b-a850-9a1cd56f2fe4', 'Github', 'All the project related repositories.', '9acaf88f', 'https://github.com/prabal-es?tab=repositories', 'https://media.xconomy.com/wordpress/wp-content/images/2016/06/06161811/github-logo.jpg',
'CARD', 1600166936000, null, true, '629fab9a-0f46-4925-8e25-4037069f7dfd', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');


CREATE TABLE T_USER_GROUP --this table for group admin mapping
(
	GROUP_ID		UUID NOT NULL,
	USER_ID			UUID NOT NULL,
	FOREIGN KEY 	(GROUP_ID) REFERENCES T_GROUP(UUID),
	FOREIGN KEY 	(USER_ID) REFERENCES T_USER(UUID)
);

INSERT INTO T_USER_GROUP(GROUP_ID, USER_ID) VALUES ('2cb44d2a-63b1-4aad-8957-44c3203bea76', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');
INSERT INTO T_USER_GROUP(GROUP_ID, USER_ID) VALUES ('6e4cf9e1-8710-4f80-9aa5-3cd3bc0c58a9', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');
INSERT INTO T_USER_GROUP(GROUP_ID, USER_ID) VALUES ('6c440055-7e30-4847-b446-22706895c9df', 'c17480ef-b7c3-4399-abbd-42b2aba7dfe6');


CREATE TABLE T_CARD_GROUP --this table for group admin mapping
(
	GROUP_ID		UUID NOT NULL,
	CARD_ID			UUID NOT NULL,
	FOREIGN KEY 	(GROUP_ID) REFERENCES T_GROUP(UUID),
	FOREIGN KEY 	(CARD_ID) REFERENCES T_CARD(UUID)
);

INSERT INTO T_CARD_GROUP(GROUP_ID, CARD_ID) VALUES ('2cb44d2a-63b1-4aad-8957-44c3203bea76', '29c1a1f4-96c5-4769-b37a-d39e6bff2afe');
INSERT INTO T_CARD_GROUP(GROUP_ID, CARD_ID) VALUES ('2cb44d2a-63b1-4aad-8957-44c3203bea76', '417246e5-647e-476b-a850-9a1cd56f2fe4');


