--------------------------------------------------------------------------
--
-- Creation des tables de la base de données de l'application
--
--------------------------------------------------------------------------


-- Scripts de création de la BDD
create table tabou_agapeo (
                        id_agapeo bigserial,
                        annee_prog integer,
                        num_dossier integer,
                        etat varchar(255),
                        maitre_ouvrage varchar(255),
                        commune varchar(255),
                        nom_operation varchar(255),
                        num_ads varchar(255),
                        convention_application_plh varchar(255),
                        logements_locatif_aide integer,
                        logements_access_aide integer,
                        logements_locatif_regule_prive integer,
                        logements_locatif_regule_hlm integer,
                        logements_access_maitrise integer,
                        create_date timestamp,
                        modif_date timestamp,
                        primary key (id_agapeo)
);

create table tabou_etape_operation (
                                 id_etape_operation bigserial,
                                 libelle varchar(255),
                                 code varchar(255) unique,
                                 mode varchar(255),
                                 type varchar(255),
                                 remove_restriction boolean,
                                 primary key (id_etape_operation)
);

create table tabou_etape_operation_workflow (
                                                id_etape_operation_workflow bigserial,
                                                id_etape_operation bigint,
                                                id_etape_operation_next bigint
);

create table tabou_etape_programme (
                                 id_etape_programme bigserial,
                                 libelle varchar(255),
                                 code varchar(255) unique,
                                 mode varchar(255),
                                 type varchar(255),
                                 remove_restriction boolean,
                                 primary key (id_etape_programme)
);

create table tabou_etape_programme_workflow (
                                 id_etape_programme_workflow bigserial,
                                 id_etape_programme bigint,
                                 id_etape_programme_next bigint
);

create table tabou_evenement_operation (
                                     id_evt_operation bigserial,
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
                                     id_evt_programme bigserial,
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
                        id_nature bigserial,
                        date_inactif timestamp,
                        libelle varchar(255),
                        primary key (id_nature)
);

create table tabou_vocation (
                        id bigserial,
                        libelle varchar(255),
                        code varchar(255) unique,
                        primary key (id)
);

create table tabou_decision (
                        id bigserial,
                        libelle varchar(255),
                        code varchar(255) unique,
                        primary key (id)
);

create table tabou_maitrise_ouvrage (
                        id bigserial,
                        libelle varchar(255),
                        code varchar(255) unique,
                        primary key (id)
);

create table tabou_mode_amenagement (
                        id bigserial,
                        libelle varchar(255),
                        code varchar(255) unique,
                        primary key (id)
);

create table tabou_consommation_espace (
                        id bigserial,
                        libelle varchar(255),
                        code varchar(255) unique,
                        primary key (id)
);

create table tabou_operation (
                           id_operation bigserial,
                           id_nature bigint,
                           id_vocation bigint,
                           id_decision bigint,
                           id_maitrise_ouvrage bigint,
                           id_mode_amenagement bigint,
                           id_consommation_espace bigint,
                           id_etape_operation bigint,
                           est_secteur boolean,
                           diffusion_restreinte boolean,
                           code varchar(255) unique,
                           nom varchar(255),
                           operation text,
                           description text,
                           autorisation_date timestamp,
                           operationnel_date timestamp,
                           cloture_date timestamp,
                           num_ads varchar(255),
                           surface_totale numeric(15,2),
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
                                 id_operation_tiers bigserial,
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
                           id_programme bigserial,
                           id_etape_programme bigint,
                           id_operation bigint,
                           code varchar(255) unique,
                           nom varchar(255),
                           programme text,
                           description text,
                           cloture_date timestamp,
                           diffusion_restreinte boolean,
                           nb_logements integer,
                           num_ads varchar(255),
                           ads_date_prevu timestamp,
                           doc_date_prevu timestamp,
                           dat_date_prevu timestamp,
                           date_livraison timestamp,
                           attribution_fonciere_annee integer,
                           attribution_date timestamp,
                           commercialisation_date timestamp,
                           logements_access_aide_prevu integer,
                           logements_access_libre_prevu integer,
                           logements_access_maitrise_prevu integer,
                           logements_locatif_aide_prevu integer,
                           logements_locatif_regule_prive_prevu integer,
                           logements_locatif_regule_hlm_prevu integer,
                           create_date timestamp,
                           create_user varchar(255),
                           modif_date timestamp,
                           modif_user varchar(255),

                           primary key (id_programme)
);


create table tabou_programme_tiers (
                                 id_programme_tiers bigserial,
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
                       id_tiers bigserial,
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
                               id_type_doc bigserial,
                               date_inactif timestamp,
                               libelle varchar(255),
                               create_date timestamp,
                               create_user varchar(255),
                               primary key (id_type_doc)
);


create table tabou_type_evenement (
                                id_type_evt bigserial,
                                libelle varchar(255),
                                date_inactif timestamp,
                                code varchar(255) unique,
                                systeme boolean,
                                create_date timestamp,
                                create_user varchar(255),
                                primary key (id_type_evt)
);


create table tabou_type_tiers (
                            id_type_tiers bigserial,
                            libelle varchar(255),
                            code varchar(255) unique,
                            create_date timestamp,
                            create_user varchar(255),
                            date_inactif timestamp,
                            primary key (id_type_tiers)
);


create table tabou_type_financement (
    id_type_financement bigserial,
    libelle varchar(50),
    code varchar(20) unique,
    est_aide boolean,
    primary key (id_type_financement)
);


-- Création de la table des permis de construire : nécessaire seulement pour environnement de dev et intégration continue
-- En réalité ce sera une Oracle_fdw, mais transparent pour nous
create table if not exists tabou_pc_ddc (
                                          id bigserial,
                                          num_ads varchar(255),
                                          date_depot_dossier timestamp,
                                          date_completude_dossier timestamp,
                                          ads_date timestamp,
                                          doc_date timestamp,
                                          dat_date timestamp,
                                          surf_autre float,
                                          surf_bureaux float,
                                          surf_commerces float,
                                          surf_equip_pub float,
                                          surf_industries float,
                                          primary key (id)
);


-- Ajout des clés étrangères

alter table if exists tabou_programme
    add constraint fk_tabou_programme_tabou_etape_programme
        foreign key (id_etape_programme)
            references tabou_etape_programme;

alter table if exists tabou_programme
    add constraint fk_tabou_programme_tabou_operation
        foreign key (id_operation)
            references tabou_operation;

alter table if exists tabou_evenement_operation
    add constraint fk_tabou_evenement_operation_tabou_operation
        foreign key (id_operation)
            references tabou_operation;


alter table if exists tabou_evenement_operation
    add constraint fk_tabou_evenement_operation_tabou_type_evenement
        foreign key (id_type_evt)
            references tabou_type_evenement;


alter table if exists tabou_evenement_programme
    add constraint fk_tabou_evenement_programme_tabou_programme
        foreign key (id_programme)
            references tabou_programme;


alter table if exists tabou_evenement_programme
    add constraint fk_tabou_evenement_programme_tabou_type_evenement
        foreign key (id_type_evt)
            references tabou_type_evenement;


alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_nature
        foreign key (id_nature)
            references tabou_nature;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_vocation
        foreign key (id_vocation) references tabou_vocation;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_decision
        foreign key (id_decision) references tabou_decision;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_maitrise_ouvrage
        foreign key (id_maitrise_ouvrage) references tabou_maitrise_ouvrage;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_mode_amenagement
        foreign key (id_mode_amenagement) references tabou_mode_amenagement;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_consommation_espace
        foreign key (id_consommation_espace) references tabou_consommation_espace;

alter table if exists tabou_operation
    add constraint fk_tabou_operation_tabou_etape_operation
        foreign key (id_etape_operation)
            references tabou_etape_operation;

alter table if exists tabou_operation_tiers
    add constraint fk_tabou_operation_tiers_tabou_operation
        foreign key (id_operation)
            references tabou_operation;


alter table if exists tabou_operation_tiers
    add constraint fk_tabou_operation_tiers_tabou_tiers
        foreign key (id_tiers)
            references tabou_tiers;


alter table if exists tabou_operation_tiers
    add constraint fk_tabou_operation_tiers_tabou_type_tiers
        foreign key (id_type_tiers)
            references tabou_type_tiers;


alter table if exists tabou_programme_tiers
    add constraint fk_tabou_programme_tiers_tabou_programme
        foreign key (id_programme)
            references tabou_programme;


alter table if exists tabou_programme_tiers
    add constraint fk_tabou_programme_tiers_tabou_tiers
        foreign key (id_tiers)
            references tabou_tiers;


alter table if exists tabou_programme_tiers
    add constraint fk_tabou_programme_tiers_tabou_type_tiers
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

CREATE INDEX "idx_tabou_programme_id_operation_fk" on tabou_programme(id_operation);
CREATE INDEX "idx_tabou_programme_id_etape_programme_fk" on tabou_programme(id_etape_programme);

CREATE INDEX "idx_tabou_programme_tiers_id_programme_fk" on tabou_programme_tiers(id_programme);
CREATE INDEX "idx_tabou_programme_tiers_id_tier_fk" on tabou_programme_tiers(id_tiers);

CREATE INDEX "idx_tabou_operation_id_nature_fk" on tabou_operation(id_nature);
CREATE INDEX "idx_tabou_operation_id_etape_operation_fk" on tabou_operation(id_etape_operation);

CREATE INDEX "idx_tabou_operation_tiers_id_operation_fk" on tabou_operation_tiers(id_operation);
CREATE INDEX "idx_tabou_operation_tiers_id_tiers_fk" on tabou_operation_tiers(id_tiers);
