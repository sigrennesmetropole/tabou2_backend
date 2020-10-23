-- Scripts temporaire. A revoir pour intégrer les scripts fournis par denis
CREATE TABLE if not exists limite_admin.commune_emprise
(
    objectid integer NOT NULL,
    code_insee numeric(15,0) NOT NULL,
    nom character varying(50),
    commune_agglo smallint,
    x_centrbrg numeric(38,8),
    y_centrbrg numeric(38,8),
    shape geometry,
    code_postal character varying(30),
    CONSTRAINT enforce_geotype_shape CHECK (geometrytype(shape) = 'POLYGON'::text OR shape IS NULL),
    CONSTRAINT enforce_srid_shape CHECK (st_srid(shape) = 3948)
)
    WITH (
        OIDS=FALSE
    );


CREATE TABLE if not exists demographie.iris
(
    objectid integer NOT NULL,
    nom_groupe character varying(41),
    ccom character varying(6),
    code_iris character varying(10),
    axe_eo integer,
    val integer,
    nusectmorp character varying(5),
    nmsectmorp character varying(60),
    nmiris character varying(50),
    niris character varying(50),
    st_area_shape_ numeric(38,8) NOT NULL,
    st_length_shape_ numeric(38,8) NOT NULL,
    st_area_shape1 numeric(38,8) NOT NULL,
    st_length_shape1 numeric(38,8) NOT NULL,
    shape geometry,
    CONSTRAINT enforce_geotype_shape CHECK (geometrytype(shape) = 'POLYGON'::text),
    CONSTRAINT enforce_srid_shape CHECK (st_srid(shape) = 3948)
)
    WITH (
        OIDS=FALSE
    );


CREATE TABLE if not exists urba_foncier.plui_zone_urba
(
    objectid integer,
    anc_objectid integer,
    id_rm integer,
    id_docurba character varying(17),
    code_insee character varying(5),
    semio character varying(255),
    commentaire character varying(255),
    libelle character varying(254),
    libelong character varying(254),
    typezone character varying(3),
    destdomi character varying(2),
    nomfic character varying(80),
    urlfic character varying(254),
    date_proc_creat character varying(8),
    date_proc_modif character varying(8),
    date_creat timestamp without time zone,
    date_modif timestamp without time zone,
    shape geometry(MultiPolygon,3948)
)
    WITH (
        OIDS=FALSE
    );


CREATE TABLE if not exists limite_admin.quartier
(
    objectid integer NOT NULL,
    matricule character varying(15),
    nuquart smallint,
    nmquart character varying(60),
    numnom character varying(60),
    nom character varying(50),
    st_area_shape_ numeric(38,8) NOT NULL,
    st_length_shape_ numeric(38,8) NOT NULL,
    shape geometry,
    code_insee integer,
    CONSTRAINT enforce_geotype_shape CHECK (geometrytype(shape) = 'POLYGON'::text),
    CONSTRAINT enforce_srid_shape CHECK (st_srid(shape) = 3948)
) WITH (
    OIDS=FALSE
);

GRANT USAGE ON SCHEMA limite_admin TO sig_user;
GRANT USAGE ON SCHEMA urba_foncier TO sig_user;
GRANT USAGE ON SCHEMA demographie TO sig_user;

ALTER TABLE limite_admin.quartier  OWNER TO sig_user;
ALTER TABLE urba_foncier.plui_zone_urba  OWNER TO sig_user;
ALTER TABLE demographie.iris  OWNER TO sig_user;
ALTER TABLE limite_admin.commune_emprise  OWNER TO sig_user;

-- GRANT ALL
GRANT ALL ON TABLE limite_admin.quartier TO sig_user;
GRANT ALL ON TABLE urba_foncier.plui_zone_urba TO sig_user;
GRANT ALL ON TABLE demographie.iris TO sig_user;
GRANT ALL ON TABLE limite_admin.commune_emprise TO sig_user;
