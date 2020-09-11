CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE TABLE T_COMPANY
(
	UUID 			uuid default uuid_generate_v4(),
	NAME 			VARCHAR(50) NOT NULL,
	DESCRIPTION		VARCHAR(500),
	ACTIVE			BOOLEAN,
	PRIMARY KEY (UUID)
);
INSERT INTO T_COMPANY(UUID, NAME, DESCRIPTION, ACTIVE) VALUES ('629fab9a-0f46-4925-8e25-4037069f7dfd', 'Societe Generale', 'Societe Generale S.A., often nicknamed SocGen, is a French multinational investment bank and financial services company headquartered in Paris, France.', true);
INSERT INTO T_COMPANY(UUID, NAME, DESCRIPTION, ACTIVE) VALUES (uuid_generate_v4(), 'ADP, LLC', 'Automatic Data Processing, Inc., commonly known as ADP, is an American provider of human resources management software and services. ADP pays more than 36 million workers worldwide and serves more than 860,000 businesses from small business to global enterprise in 140 countries.', false);