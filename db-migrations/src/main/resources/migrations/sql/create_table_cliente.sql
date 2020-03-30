
CREATE TABLE IF NOT EXISTS cliente (
       id bigint not null auto_increment,
        empregado bit not null,
        endereco varchar(255),
        nome varchar(255),
        rendimento_mensal double precision,
        risco varchar(255),
        tipo varchar(255),
        vlr_dividas_atuais double precision,
        vlr_total_patrimonio double precision,
        primary key (id)
    ) engine=InnoDB
