CREATE TABLE T_COMPANY
(
	ID 				INT PRIMARY KEY,
	NAME 			VARCHAR(50) NOT NULL,
	DESCRIPTION		VARCHAR(500),
	ACTIVE			BOOLEAN
);

INSERT INTO T_COMPANY(ID, NAME, DESCRIPTION, ACTIVE) VALUES (1, 'Societe Generale', 'Societe Generale S.A., often nicknamed SocGen, is a French multinational investment bank and financial services company headquartered in Paris, France.', true);