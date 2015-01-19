
    alter table sample 
        drop constraint sample__related if exists;

    drop table related if exists;

    drop table sample if exists;

    drop sequence related_seq;

    drop sequence sample_seq;

    create table related (
        id bigint not null,
        value varchar(100) not null,
        version bigint not null,
        primary key (id)
    );

    create table sample (
        id bigint not null,
        value varchar(100) not null,
        version bigint not null,
        related bigint not null,
        primary key (id)
    );

    alter table sample 
        add constraint sample__related 
        foreign key (related) 
        references related;

    create sequence related_seq;

    create sequence sample_seq;
