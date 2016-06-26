--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: colleenminor; Tablespace: 
--

CREATE TABLE clients (
    id integer NOT NULL,
    client_name character varying,
    phone character varying,
    stylist_id integer
);


ALTER TABLE clients OWNER TO colleenminor;

--
-- Name: stylists; Type: TABLE; Schema: public; Owner: colleenminor; Tablespace: 
--

CREATE TABLE stylists (
    id integer NOT NULL,
    name character varying
);


ALTER TABLE stylists OWNER TO colleenminor;

--
-- Name: doctor_id_seq; Type: SEQUENCE; Schema: public; Owner: colleenminor
--

CREATE SEQUENCE doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE doctor_id_seq OWNER TO colleenminor;

--
-- Name: doctor_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: colleenminor
--

ALTER SEQUENCE doctor_id_seq OWNED BY stylists.id;


--
-- Name: patient_id_seq; Type: SEQUENCE; Schema: public; Owner: colleenminor
--

CREATE SEQUENCE patient_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE patient_id_seq OWNER TO colleenminor;

--
-- Name: patient_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: colleenminor
--

ALTER SEQUENCE patient_id_seq OWNED BY clients.id;


--
-- Name: specialties; Type: TABLE; Schema: public; Owner: colleenminor; Tablespace: 
--

CREATE TABLE specialties (
    id integer NOT NULL
);


ALTER TABLE specialties OWNER TO colleenminor;

--
-- Name: specialties_id_seq; Type: SEQUENCE; Schema: public; Owner: colleenminor
--

CREATE SEQUENCE specialties_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE specialties_id_seq OWNER TO colleenminor;

--
-- Name: specialties_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: colleenminor
--

ALTER SEQUENCE specialties_id_seq OWNED BY specialties.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: colleenminor
--

ALTER TABLE ONLY clients ALTER COLUMN id SET DEFAULT nextval('patient_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: colleenminor
--

ALTER TABLE ONLY specialties ALTER COLUMN id SET DEFAULT nextval('specialties_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: colleenminor
--

ALTER TABLE ONLY stylists ALTER COLUMN id SET DEFAULT nextval('doctor_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: colleenminor
--

COPY clients (id, client_name, phone, stylist_id) FROM stdin;
46	wad	34234	38
2	Kerry	09-09-1970	2
47	Bill	3423-2342-342	38
4	sf	se	3
7	ef	e	11
8	e	4	7
9	se	433	9
10	Andwer	403-203-203	3
11	Ookie	Lookie	14
48	Kevin	123456	42
49	Mike	123-232-2222	44
5	Jerry	403-302-203	9
14	Mike	403-34034-2	13
13	sarah	403-203-203	15
15	Bobby	403-032-0302	13
16	Kevin	403-203-203	20
18	esf	ferf	15
19	sef	sef	6
21	Bryan	4r403	23
23	Butter	4034-34	25
24	ersr	ser343	21
25	43534	43	21
26	esf	sf	21
20	504-300-3020	403-403-2302	21
27	ree	35	21
28	sefs	esf	26
30	ffrf	453	27
29	dd	34324	27
32	esfs	354345	29
33	sef	sf	24
34	et	et	24
36	456	4564	32
38	sdf	4353	24
31	sefsef	345353	24
39	sfes	43534535	24
40	esfet	435543	24
41	sfd	refe435	36
43	dsf	35	37
42	esfEW	435sf	37
44	Mortimor	403-203-203	37
45	Tiffany	403-203-2020	39
\.


--
-- Name: doctor_id_seq; Type: SEQUENCE SET; Schema: public; Owner: colleenminor
--

SELECT pg_catalog.setval('doctor_id_seq', 44, true);


--
-- Name: patient_id_seq; Type: SEQUENCE SET; Schema: public; Owner: colleenminor
--

SELECT pg_catalog.setval('patient_id_seq', 49, true);


--
-- Data for Name: specialties; Type: TABLE DATA; Schema: public; Owner: colleenminor
--

COPY specialties (id) FROM stdin;
\.


--
-- Name: specialties_id_seq; Type: SEQUENCE SET; Schema: public; Owner: colleenminor
--

SELECT pg_catalog.setval('specialties_id_seq', 1, false);


--
-- Data for Name: stylists; Type: TABLE DATA; Schema: public; Owner: colleenminor
--

COPY stylists (id, name) FROM stdin;
43	Nick
44	Kevin
\.


--
-- Name: doctor_pkey; Type: CONSTRAINT; Schema: public; Owner: colleenminor; Tablespace: 
--

ALTER TABLE ONLY stylists
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);


--
-- Name: patient_pkey; Type: CONSTRAINT; Schema: public; Owner: colleenminor; Tablespace: 
--

ALTER TABLE ONLY clients
    ADD CONSTRAINT patient_pkey PRIMARY KEY (id);


--
-- Name: specialties_pkey; Type: CONSTRAINT; Schema: public; Owner: colleenminor; Tablespace: 
--

ALTER TABLE ONLY specialties
    ADD CONSTRAINT specialties_pkey PRIMARY KEY (id);


--
-- Name: public; Type: ACL; Schema: -; Owner: colleenminor
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM colleenminor;
GRANT ALL ON SCHEMA public TO colleenminor;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

