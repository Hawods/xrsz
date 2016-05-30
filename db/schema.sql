DROP TABLE IF EXISTS ss_seo;

CREATE TABLE ss_seo (
  id BIGINT NOT NULL auto_increment,
  create_date datetime NOT NULL,
  modify_date datetime NOT NULL,
  version BIGINT NOT NULL,
  description VARCHAR (255),
  keywords VARCHAR (255),
  title VARCHAR (255),
  type VARCHAR (255) NOT NULL,
  PRIMARY KEY (id)
);
