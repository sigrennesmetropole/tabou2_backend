--------------------------------------------------------------------------
--
-- Creation des tables de la base de données de l'application
--
--------------------------------------------------------------------------


-- Scripts de création de la BDD
create table tabou_agapeo (
                        id_agapeo bigint generated by default as identity,
                        annee_prog integer,
                        num_dossier integer,
                        etat varchar(255),
                        maitre_ouvrage varchar(255),
                        commune varchar(255),
                        nom_operation varchar(255),
                        num_ads varchar(255),
                        convention_application_plh varchar(255),
                        logements_locat_aide integer,
                        logements_access_aide integer,
                        logements_locat_regul_prive integer,
                        logements_locat_regul_hlm integer,
                        logements_access_maitrise integer,
                        create_date timestamp,
                        modif_date timestamp,
                        primary key (id_agapeo)
);


create table tabou_etape_operation (
                                 id_etape_operation bigint generated by default as identity,
                                 libelle varchar(255),
                                 code varchar(255) unique,
                                 mode varchar(255),
                                 type varchar(255),
                                 remove_restriction boolean,
                                 primary key (id_etape_operation)
);

create table tabou_etape_operation_workflow (
                                                id_etape_operation_workflow bigint generated by default as identity,
                                                id_etape_operation bigint,
                                                id_etape_operation_next bigint
);

create table tabou_etape_programme (
                                 id_etape_programme bigint generated by default as identity,
                                 libelle varchar(255),
                                 code varchar(255) unique,
                                 mode varchar(255),
                                 type varchar(255),
                                 remove_restriction boolean,
                                 primary key (id_etape_programme)
);

create table tabou_etape_programme_workflow (
                                 id_etape_programme_workflow bigint generated by default as identity,
                                 id_etape_programme bigint,
                                 id_etape_programme_next bigint
);


create table tabou_evenement_operation (
                                     id_evt_operation bigint generated by default as identity,
                                     create_date timestamp,
                                     create_user varchar(255),
                                     description text,
                                     event_date timestamp,
                                     modif_date timestamp,
                                     modif_user varchar(255),
                                     systeme boolean default false,
                                     id_operation bigint,
                                     id_type_evt bigint,
                                     primary key (id_evt_operation)
);

create table tabou_evenement_programme (
                                     id_evt_programme bigint generated by default as identity,
                                     create_date timestamp,
                                     create_user varchar(255),
                                     description text,
                                     event_date timestamp,
                                     modif_date timestamp,
                                     modif_user varchar(255),
                                     systeme boolean default false,
                                     id_programme bigint,
                                     id_type_evt bigint,
                                     primary key (id_evt_programme)
);


create table tabou_nature (
                        id_nature bigint generated by default as identity,
                        date_inactif timestamp,
                        libelle varchar(255),
                        primary key (id_nature)
);

create table tabou_operation (
                           id_operation bigint generated by default as identity,
                           id_nature bigint,
                           id_etape_operation bigint,
                           est_secteur boolean,
                           diffusion_restreinte boolean,
                           code varchar(255) unique,
                           nom varchar(255) unique,
                           operation text,
                           description text,
                           autorisation_date timestamp,
                           operationnel_date timestamp,
                           cloture_date timestamp,
                           num_ads varchar(255),
                           surface_totale integer,
                           nb_logement_prevu integer,
                           plhlogement_prevu integer,
                           plhlogement_livre integer,
                           ql1 varchar(255),
                           ql2 boolean,
                           ql3 varchar(255),
                           nb_entreprise integer,
                           nb_salarie integer,
                           create_date timestamp,
                           create_user varchar(255),
                           modif_date timestamp,
                           modif_user varchar(255),
                           primary key (id_operation)
);


create table tabou_operation_tiers (
                                 id_operation_tiers bigint generated by default as identity,
                                 create_date timestamp,
                                 create_user varchar(255),
                                 modif_date timestamp,
                                 modif_user varchar(255),
                                 id_operation bigint,
                                 id_tiers bigint,
                                 id_type_tiers bigint,
                                 primary key (id_operation_tiers)
);


create table tabou_programme (
                           id_programme bigint generated by default as identity,
                           id_etape_programme bigint,
                           code varchar(255) unique,
                           nom varchar(255) unique,
                           programme text,
                           description text,
                           cloture_date timestamp,
                           diffusion_restreinte boolean,
                           nb_logements integer,
                           num_ads varchar(255),
                           ads_date_prevu timestamp,
                           doc_date_prevu timestamp,
                           dat_date_prevu timestamp,
                           attribution_fonciere_annee integer,
                           attribution_date timestamp,
                           commercialisation_date timestamp,
                           logements_access_aide_prevu integer,
                           logements_access_libre_prevu integer,
                           logements_access_maitrise_prevu integer,
                           logements_locat_aide_prevu integer,
                           logements_locat_regule_prive_prevu integer,
                           logements_locatif_regule_hlm_prevu integer,
                           create_date timestamp,
                           create_user varchar(255),
                           modif_date timestamp,
                           modif_user varchar(255),

                           primary key (id_programme)
);


create table tabou_programme_tiers (
                                 id_programme_tiers bigint generated by default as identity,
                                 create_date timestamp,
                                 create_user varchar(255),
                                 modif_date timestamp,
                                 modif_user varchar(255),
                                 id_programme bigint,
                                 id_tiers bigint,
                                 id_type_tiers bigint,
                                 primary key (id_programme_tiers)
);



create table tabou_tiers (
                       id_tiers bigint generated by default as identity,
                       nom varchar(255),
                       est_prive boolean,
                       adresse_cp varchar(255),
                       adresse_num varchar(255),
                       adresse_rue varchar(255),
                       adresse_ville varchar(255),
                       telecopie varchar(255),
                       telephone varchar(255),
                       contact varchar(255),
                       email varchar(255),
                       site_web varchar(255),
                       date_inactif timestamp,
                       create_date timestamp,
                       create_user varchar(255),
                       modif_date timestamp,
                       modif_user varchar(255),
                       primary key (id_tiers)
);


create table tabou_type_document (
                               id_type_doc bigint generated by default as identity,
                               date_inactif timestamp,
                               libelle varchar(255),
                               create_date timestamp,
                               create_user varchar(255),
                               primary key (id_type_doc)
);


create table tabou_type_evenement (
                                id_type_evt bigint generated by default as identity,
                                libelle varchar(255),
                                date_inactif timestamp,
                                create_date timestamp,
                                create_user varchar(255),
                                primary key (id_type_evt)
);


create table tabou_type_tiers (
                            id_type_tiers bigint generated by default as identity,
                            libelle varchar(255),
                            create_date timestamp,
                            create_user varchar(255),
                            date_inactif timestamp,
                            primary key (id_type_tiers)
);


-- Ajout des clés étrangères


alter table if exists tabou_programme
    add constraint fkn2d1j3xgkqge6kioikvkstcg9
        foreign key (id_etape_programme)
            references tabou_etape_programme;

alter table if exists tabou_evenement_operation
    add constraint FKkr4528e5v3lr3054v1d2tds4c
        foreign key (id_operation)
            references tabou_operation;


alter table if exists tabou_evenement_operation
    add constraint FKcr9ky2twrrxbmle71ploir5g6
        foreign key (id_type_evt)
            references tabou_type_evenement;


alter table if exists tabou_evenement_programme
    add constraint FK7sw203ns1mfwaqkccwplsobgu
        foreign key (id_programme)
            references tabou_programme;


alter table if exists tabou_evenement_programme
    add constraint FKgnmrutcu969nk0arjsh73i212
        foreign key (id_type_evt)
            references tabou_type_evenement;


alter table if exists tabou_operation
    add constraint FKrwdfcmejegpwofrh8suxs9xw6
        foreign key (id_nature)
            references tabou_nature;

alter table if exists tabou_operation
    add constraint fkipoc1d5r2vt2uqqwuegw1jsa0
        foreign key (id_etape_operation)
            references tabou_etape_operation;

alter table if exists tabou_operation_tiers
    add constraint FKepwn2h1eghs8imwf4dcplsrpd
        foreign key (id_operation)
            references tabou_operation;


alter table if exists tabou_operation_tiers
    add constraint FK1eadn10xklcnqh0unfuu411ba
        foreign key (id_tiers)
            references tabou_tiers;


alter table if exists tabou_operation_tiers
    add constraint FK7nefer0uwhs3fsi06b97a4yln
        foreign key (id_type_tiers)
            references tabou_type_tiers;


alter table if exists tabou_programme_tiers
    add constraint FKmhp8mixi41itd1rxx331uahtu
        foreign key (id_programme)
            references tabou_programme;


alter table if exists tabou_programme_tiers
    add constraint FKgkfw9jyg12hc41tp3udh7h49j
        foreign key (id_tiers)
            references tabou_tiers;


alter table if exists tabou_programme_tiers
    add constraint FKo2rxjnajre6agek7wlpfw3hfn
        foreign key (id_type_tiers)
            references tabou_type_tiers;

alter table if exists tabou_etape_programme
    add constraint enum_type_etape_programme
        check ( "type" in ('START', 'NORMAL', 'END') );

alter table if exists tabou_etape_programme
    add constraint enum_mode_etape_programme
        check ( "mode" in ('OFF', 'PUBLIC', 'NONE') );

alter table if exists tabou_etape_programme_workflow
    add constraint fk_etape_programme_workflow_etape_programme
        foreign key (id_etape_programme) references tabou_etape_programme;

alter table if exists tabou_etape_programme_workflow
    add constraint fk_etape_programme_workflow_etape_programme_next
        foreign key (id_etape_programme_next) references tabou_etape_programme;

alter table if exists tabou_etape_operation
    add constraint enum_type_etape_operation
        check ( "type" in ('START', 'NORMAL', 'END') );

alter table if exists tabou_etape_operation
    add constraint enum_mode_etape_operation
        check ( "mode" in ('OFF', 'PUBLIC', 'NONE') );

alter table if exists tabou_etape_operation_workflow
    add constraint fk_etape_operation_workflow_etape_operation
        foreign key (id_etape_operation) references tabou_etape_operation;

alter table if exists tabou_etape_operation_workflow
    add constraint fk_etape_operation_workflow_etape_operation_next
        foreign key (id_etape_operation_next) references tabou_etape_operation;

alter table if exists tabou_nature
    add constraint enum_libelle_nature
        check ( "libelle" in ('ZAC', 'ZA', 'En diffus') );
