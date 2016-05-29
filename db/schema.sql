create table ss_seo(
  id bigint not null auto_increment,
  create_date datetime not null,
  modify_date datetime not null,
  version bigint not null,
  description varchar(255),
  keywords varchar(255),
  title varchar(255),
  type int not null, primary key(id)
);
