-- Tabela: usuario

--drop table usuario;

create table usuario
(
  nr_matricula integer not null,
  tx_nome character varying(200) not null,
  tx_email character varying(200) not null,
  tx_telefone character varying(50) not null,
  constraint pk_usuario primary key (nr_matricula)
)
with (
  oids=false
);
alter table usuario
  owner to postgres;


-- Tabela: grupo

--drop table grupo;

create table grupo
(
  cd_grupo serial not null,
  tx_nome character varying(200) not null,
  constraint pk_grupo primary key (cd_grupo)
)
with (
  oids=false
);
alter table grupo
  owner to postgres;


-- Tabela: chamado

--drop table chamado;

create table chamado
(
  nr_chamado bigserial not null,
  tx_titulo character varying(200) not null,
  tx_descricao text not null,
  dh_criacao timestamp not null,
  fl_status character(1) not null,
  nr_matricula_criador integer not null,
  cd_grupo_atual integer not null,
  constraint pk_chamado primary key (nr_chamado),
  constraint fk_criador foreign key (nr_matricula_criador)
      references usuario (nr_matricula)
      on update no action on delete no action,
  constraint fk_grupo_atual foreign key (cd_grupo_atual)
      references grupo (cd_grupo)
      on update no action on delete no action)
with (
  oids=false
);
alter table chamado
  owner to postgres;

-- Tabela: mensagem

--drop table mensagem;

create table mensagem
(
  nr_chamado bigint not null,
  sq_mensagem serial not null,
  dh_mensagem timestamp not null,
  nr_matricula integer not null,
  tx_mensagem text,
  constraint pk_mensagem primary key (nr_chamado,sq_mensagem),
  constraint fk_chamado foreign key (nr_chamado)
    references chamado (nr_chamado)
    on update cascade on delete cascade,
  constraint fk_usuario foreign key (nr_matricula)
    references usuario (nr_matricula)
    on update no action on delete no action
)
with (
  oids=false
);
alter table mensagem
  owner to postgres;

-- Tabela: usuario_grupo

--drop table usuario_grupo;

create table usuario_grupo
(
  nr_matricula integer not null,
  cd_grupo integer not null,
  constraint pk_usuario_grupo primary key (nr_matricula,cd_grupo),
  constraint fk_usuario foreign key (nr_matricula)
    references usuario (nr_matricula)
    on update cascade on delete cascade,
  constraint fk_grupo foreign key (cd_grupo)
    references grupo (cd_grupo)
    on update cascade on delete cascade
)
with (
  oids=false
);
alter table usuario_grupo
  owner to postgres;

-- Tabela: responsavel_chamado

--drop table responsavel_chamado;

create table responsavel_chamado
(
  nr_matricula integer not null,
  nr_chamado bigint not null,
  constraint pk_responsavel_chamado primary key (nr_matricula,nr_chamado),
  constraint fk_usuario foreign key (nr_matricula)
    references usuario (nr_matricula)
    on update cascade on delete cascade,
  constraint fk_chamado foreign key (nr_chamado)
    references chamado (nr_chamado)
    on update cascade on delete cascade
)
with (
  oids=false
);
alter table responsavel_chamado
  owner to postgres;

-- Tabela: usuario_chamado

--drop table usuario_chamado;

create table usuario_chamado
(
  nr_chamado bigint not null,
  sq_usuario serial not null,
  nr_matricula integer,
  tx_nome character varying(200),
  tx_email character varying(200),
  tx_telefone character varying(50),
  constraint pk_usuario_chamado primary key (nr_chamado,sq_usuario),
  constraint fk_usuario foreign key (nr_matricula)
    references usuario (nr_matricula)
    on update cascade on delete cascade,
  constraint fk_chamado foreign key (nr_chamado)
    references chamado (nr_chamado)
    on update cascade on delete cascade
)
with (
  oids=false
);
alter table usuario_chamado
  owner to postgres;

