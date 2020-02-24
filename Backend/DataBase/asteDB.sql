--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: tipopenale; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipopenale AS ENUM (
    'minima',
    'massima'
);


ALTER TYPE public.tipopenale OWNER TO postgres;

--
-- Name: tipotimeslotasta; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipotimeslotasta AS ENUM (
    'fisso',
    'variabile'
);


ALTER TYPE public.tipotimeslotasta OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: amministratore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.amministratore (
    username character varying(100) NOT NULL,
    id uuid NOT NULL,
    email character varying(300) NOT NULL
);


ALTER TABLE public.amministratore OWNER TO postgres;

--
-- Name: asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asta (
    id uuid NOT NULL,
    id_asta_manager uuid NOT NULL,
    id_configurazione uuid NOT NULL,
    data_inizio date NOT NULL,
    data_fine date,
    durata_timeslot time without time zone NOT NULL,
    tipo character varying(100) NOT NULL
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- Name: attributo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo (
    id uuid NOT NULL,
    id_categoria uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.attributo OWNER TO postgres;

--
-- Name: attributo_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo_oggetto (
    id_oggetto uuid NOT NULL,
    id_attributo uuid NOT NULL,
    valore character varying(100) NOT NULL
);


ALTER TABLE public.attributo_oggetto OWNER TO postgres;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_oggetto (
    id_oggetto uuid NOT NULL,
    id_categoria uuid NOT NULL
);


ALTER TABLE public.categoria_oggetto OWNER TO postgres;

--
-- Name: configurazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configurazione (
    id uuid NOT NULL,
    "durata-timeslot-fisso" time without time zone NOT NULL,
    "numero-max-timeslot" bigint NOT NULL,
    "numero-offerte-contemporanee-utente" bigint NOT NULL,
    "tipo-timeslot" public.tipotimeslotasta NOT NULL,
    "tipo-penale" public.tipopenale NOT NULL
);


ALTER TABLE public.configurazione OWNER TO postgres;

--
-- Name: oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oggetto (
    id uuid NOT NULL,
    id_asta uuid NOT NULL,
    nome character varying(100) NOT NULL,
    descrizione text,
    url_immagine character varying(200)
);


ALTER TABLE public.oggetto OWNER TO postgres;

--
-- Name: tipo_asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_asta (
    nome character varying(100) NOT NULL
);


ALTER TABLE public.tipo_asta OWNER TO postgres;

--
-- Name: utente_registrato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente_registrato (
    id uuid NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(355) NOT NULL,
    telefono character varying(12),
    credito_disponibile real DEFAULT 0 NOT NULL
);


ALTER TABLE public.utente_registrato OWNER TO postgres;

--
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amministratore (username, id, email) FROM stdin;
\.


--
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_asta_manager, id_configurazione, data_inizio, data_fine, durata_timeslot, tipo) FROM stdin;
\.


--
-- Data for Name: attributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo (id, id_categoria, nome) FROM stdin;
\.


--
-- Data for Name: attributo_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo_oggetto (id_oggetto, id_attributo, valore) FROM stdin;
\.


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
\.


--
-- Data for Name: categoria_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria_oggetto (id_oggetto, id_categoria) FROM stdin;
\.


--
-- Data for Name: configurazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configurazione (id, "durata-timeslot-fisso", "numero-max-timeslot", "numero-offerte-contemporanee-utente", "tipo-timeslot", "tipo-penale") FROM stdin;
\.


--
-- Data for Name: oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.oggetto (id, id_asta, nome, descrizione, url_immagine) FROM stdin;
\.


--
-- Data for Name: tipo_asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_asta (nome) FROM stdin;
\.


--
-- Data for Name: utente_registrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente_registrato (id, username, password, email, telefono, credito_disponibile) FROM stdin;
02942f14-7636-4d81-bb48-31b8977edad7	userProva	password	userProva@gmail.com	\N	58
\.


--
-- Name: asta Asta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "Asta_pkey" PRIMARY KEY (id);


--
-- Name: oggetto Oggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "Oggetto_pkey" PRIMARY KEY (id);


--
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (id);


--
-- Name: attributo_oggetto attributoOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "attributoOggetto_pkey" PRIMARY KEY (id_oggetto);


--
-- Name: attributo attributo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT attributo_pkey PRIMARY KEY (id);


--
-- Name: categoria_oggetto categoriaOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "categoriaOggetto_pkey" PRIMARY KEY (id_oggetto, id_categoria);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: configurazione configurazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configurazione
    ADD CONSTRAINT configurazione_pkey PRIMARY KEY (id);


--
-- Name: amministratore email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT email UNIQUE (email);


--
-- Name: oggetto nome; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT nome UNIQUE (nome);


--
-- Name: tipo_asta tipoAsta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_asta
    ADD CONSTRAINT "tipoAsta_pkey" PRIMARY KEY (nome);


--
-- Name: amministratore username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT username UNIQUE (username);


--
-- Name: utente_registrato utenteregistrato_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_email_key UNIQUE (email);


--
-- Name: utente_registrato utenteregistrato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_pkey PRIMARY KEY (id);


--
-- Name: utente_registrato utenteregistrato_telefono_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_telefono_key UNIQUE (telefono);


--
-- Name: utente_registrato utenteregistrato_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_username_key UNIQUE (username);


--
-- Name: oggetto idAsta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "idAsta" FOREIGN KEY (id_asta) REFERENCES public.asta(id);


--
-- Name: asta idAstaManager; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idAstaManager" FOREIGN KEY (id_asta_manager) REFERENCES public.utente_registrato(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idAttributo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idAttributo" FOREIGN KEY (id_attributo) REFERENCES public.attributo(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- Name: categoria_oggetto idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: asta idConfigurazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idConfigurazione" FOREIGN KEY (id_configurazione) REFERENCES public.configurazione(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id);


--
-- Name: categoria_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id) ON UPDATE CASCADE;


--
-- Name: asta tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT tipo FOREIGN KEY (tipo) REFERENCES public.tipo_asta(nome) ON UPDATE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: tipotimeslotasta; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipotimeslotasta AS ENUM (
    'fisso',
    'variabile'
);


ALTER TYPE public.tipotimeslotasta OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: amministratore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.amministratore (
    username character varying(100) NOT NULL,
    id uuid NOT NULL,
    email character varying(300) NOT NULL
);


ALTER TABLE public.amministratore OWNER TO postgres;

--
-- Name: asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asta (
    id uuid NOT NULL,
    id_asta_manager uuid NOT NULL,
    id_configurazione uuid NOT NULL,
    data_inizio date NOT NULL,
    data_fine date,
    durata_timeslot time without time zone NOT NULL,
    tipo character varying(100) NOT NULL,
    prezzo_partenza real NOT NULL,
    penale real NOT NULL,
    CONSTRAINT check_penale CHECK (((penale >= (0)::double precision) AND (penale <= (1)::double precision)))
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- Name: attributo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo (
    id uuid NOT NULL,
    id_categoria uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.attributo OWNER TO postgres;

--
-- Name: attributo_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo_oggetto (
    id_oggetto uuid NOT NULL,
    id_attributo uuid NOT NULL,
    valore character varying(100) NOT NULL
);


ALTER TABLE public.attributo_oggetto OWNER TO postgres;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_oggetto (
    id_oggetto uuid NOT NULL,
    id_categoria uuid NOT NULL
);


ALTER TABLE public.categoria_oggetto OWNER TO postgres;

--
-- Name: configurazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configurazione (
    id uuid NOT NULL,
    durata_timeslot_fisso time without time zone NOT NULL,
    numero_max_timeslot bigint NOT NULL,
    numero_offerte_contemporanee_utente bigint NOT NULL,
    tipo_timeslot public.tipotimeslotasta NOT NULL
);


ALTER TABLE public.configurazione OWNER TO postgres;

--
-- Name: offerta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offerta (
    id_utente_registrato uuid NOT NULL,
    id_asta uuid NOT NULL,
    data date NOT NULL,
    credito_offerto real NOT NULL
);


ALTER TABLE public.offerta OWNER TO postgres;

--
-- Name: oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oggetto (
    id uuid NOT NULL,
    id_asta uuid NOT NULL,
    nome character varying(100) NOT NULL,
    descrizione text,
    url_immagine character varying(200)
);


ALTER TABLE public.oggetto OWNER TO postgres;

--
-- Name: tipo_asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_asta (
    nome character varying(100) NOT NULL
);


ALTER TABLE public.tipo_asta OWNER TO postgres;

--
-- Name: utente_registrato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente_registrato (
    id uuid NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(355) NOT NULL,
    telefono character varying(12),
    credito_disponibile real DEFAULT 0 NOT NULL
);


ALTER TABLE public.utente_registrato OWNER TO postgres;

--
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amministratore (username, id, email) FROM stdin;
\.


--
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_asta_manager, id_configurazione, data_inizio, data_fine, durata_timeslot, tipo, prezzo_partenza, penale) FROM stdin;
\.


--
-- Data for Name: attributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo (id, id_categoria, nome) FROM stdin;
\.


--
-- Data for Name: attributo_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo_oggetto (id_oggetto, id_attributo, valore) FROM stdin;
\.


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
\.


--
-- Data for Name: categoria_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria_oggetto (id_oggetto, id_categoria) FROM stdin;
\.


--
-- Data for Name: configurazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configurazione (id, durata_timeslot_fisso, numero_max_timeslot, numero_offerte_contemporanee_utente, tipo_timeslot) FROM stdin;
\.


--
-- Data for Name: offerta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.offerta (id_utente_registrato, id_asta, data, credito_offerto) FROM stdin;
\.


--
-- Data for Name: oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.oggetto (id, id_asta, nome, descrizione, url_immagine) FROM stdin;
\.


--
-- Data for Name: tipo_asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_asta (nome) FROM stdin;
\.


--
-- Data for Name: utente_registrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente_registrato (id, username, password, email, telefono, credito_disponibile) FROM stdin;
02942f14-7636-4d81-bb48-31b8977edad7	userProva	password	userProva@gmail.com	\N	58
b505c3b6-7769-4c6f-a000-80e1615b77a8	Luca	1243	superBoh@boh.com	\N	666
\.


--
-- Name: asta Asta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "Asta_pkey" PRIMARY KEY (id);


--
-- Name: oggetto Oggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "Oggetto_pkey" PRIMARY KEY (id);


--
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (id);


--
-- Name: attributo_oggetto attributoOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "attributoOggetto_pkey" PRIMARY KEY (id_oggetto);


--
-- Name: attributo attributo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT attributo_pkey PRIMARY KEY (id);


--
-- Name: categoria_oggetto categoriaOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "categoriaOggetto_pkey" PRIMARY KEY (id_oggetto, id_categoria);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: configurazione configurazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configurazione
    ADD CONSTRAINT configurazione_pkey PRIMARY KEY (id);


--
-- Name: amministratore email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT email UNIQUE (email);


--
-- Name: oggetto nome; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT nome UNIQUE (nome);


--
-- Name: offerta offerta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT offerta_pkey PRIMARY KEY (id_utente_registrato, id_asta);


--
-- Name: tipo_asta tipoAsta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_asta
    ADD CONSTRAINT "tipoAsta_pkey" PRIMARY KEY (nome);


--
-- Name: amministratore username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT username UNIQUE (username);


--
-- Name: utente_registrato utenteregistrato_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_email_key UNIQUE (email);


--
-- Name: utente_registrato utenteregistrato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_pkey PRIMARY KEY (id);


--
-- Name: utente_registrato utenteregistrato_telefono_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_telefono_key UNIQUE (telefono);


--
-- Name: utente_registrato utenteregistrato_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_username_key UNIQUE (username);


--
-- Name: oggetto idAsta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "idAsta" FOREIGN KEY (id_asta) REFERENCES public.asta(id);


--
-- Name: asta idAstaManager; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idAstaManager" FOREIGN KEY (id_asta_manager) REFERENCES public.utente_registrato(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idAttributo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idAttributo" FOREIGN KEY (id_attributo) REFERENCES public.attributo(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- Name: categoria_oggetto idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: asta idConfigurazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idConfigurazione" FOREIGN KEY (id_configurazione) REFERENCES public.configurazione(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id);


--
-- Name: categoria_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id) ON UPDATE CASCADE;


--
-- Name: offerta id_asta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT id_asta FOREIGN KEY (id_asta) REFERENCES public.asta(id);


--
-- Name: offerta id_utente_registrato; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT id_utente_registrato FOREIGN KEY (id_utente_registrato) REFERENCES public.utente_registrato(id) NOT VALID;


--
-- Name: asta tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT tipo FOREIGN KEY (tipo) REFERENCES public.tipo_asta(nome) ON UPDATE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: uuid-ossp; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS "uuid-ossp" WITH SCHEMA public;


--
-- Name: EXTENSION "uuid-ossp"; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION "uuid-ossp" IS 'generate universally unique identifiers (UUIDs)';


--
-- Name: tipotimeslotasta; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipotimeslotasta AS ENUM (
    'fisso',
    'variabile'
);


ALTER TYPE public.tipotimeslotasta OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: amministratore; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.amministratore (
    username character varying(100) NOT NULL,
    id uuid NOT NULL,
    email character varying(300) NOT NULL,
    password character varying(50) NOT NULL
);


ALTER TABLE public.amministratore OWNER TO postgres;

--
-- Name: asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.asta (
    id uuid NOT NULL,
    id_asta_manager uuid NOT NULL,
    id_configurazione uuid NOT NULL,
    data_inizio date NOT NULL,
    data_fine date,
    durata_timeslot time without time zone NOT NULL,
    tipo character varying(100) NOT NULL,
    prezzo_partenza real NOT NULL,
    penale real NOT NULL,
    CONSTRAINT check_penale CHECK (((penale >= (0)::double precision) AND (penale <= (1)::double precision)))
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- Name: attributo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo (
    id uuid NOT NULL,
    id_categoria uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.attributo OWNER TO postgres;

--
-- Name: attributo_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo_oggetto (
    id_oggetto uuid NOT NULL,
    id_attributo uuid NOT NULL,
    valore character varying(100) NOT NULL
);


ALTER TABLE public.attributo_oggetto OWNER TO postgres;

--
-- Name: categoria; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria (
    id uuid NOT NULL,
    nome character varying(100) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_oggetto (
    id_oggetto uuid NOT NULL,
    id_categoria uuid NOT NULL
);


ALTER TABLE public.categoria_oggetto OWNER TO postgres;

--
-- Name: configurazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configurazione (
    id uuid NOT NULL,
    durata_timeslot_fisso time without time zone NOT NULL,
    numero_max_timeslot bigint NOT NULL,
    numero_offerte_contemporanee_utente bigint NOT NULL,
    tipo_timeslot public.tipotimeslotasta NOT NULL
);


ALTER TABLE public.configurazione OWNER TO postgres;

--
-- Name: offerta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offerta (
    id_utente_registrato uuid NOT NULL,
    id_asta uuid NOT NULL,
    data date NOT NULL,
    credito_offerto real NOT NULL
);


ALTER TABLE public.offerta OWNER TO postgres;

--
-- Name: oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.oggetto (
    id uuid NOT NULL,
    id_asta uuid NOT NULL,
    nome character varying(100) NOT NULL,
    descrizione text,
    url_immagine character varying(200)
);


ALTER TABLE public.oggetto OWNER TO postgres;

--
-- Name: tipo_asta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tipo_asta (
    nome character varying(100) NOT NULL
);


ALTER TABLE public.tipo_asta OWNER TO postgres;

--
-- Name: utente_registrato; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.utente_registrato (
    id uuid NOT NULL,
    username character varying(100) NOT NULL,
    password character varying(50) NOT NULL,
    email character varying(355) NOT NULL,
    telefono character varying(12),
    credito_disponibile real DEFAULT 0 NOT NULL
);


ALTER TABLE public.utente_registrato OWNER TO postgres;

--
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amministratore (username, id, email, password) FROM stdin;
\.


--
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_asta_manager, id_configurazione, data_inizio, data_fine, durata_timeslot, tipo, prezzo_partenza, penale) FROM stdin;
\.


--
-- Data for Name: attributo; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo (id, id_categoria, nome) FROM stdin;
\.


--
-- Data for Name: attributo_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.attributo_oggetto (id_oggetto, id_attributo, valore) FROM stdin;
\.


--
-- Data for Name: categoria; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria (id, nome) FROM stdin;
\.


--
-- Data for Name: categoria_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria_oggetto (id_oggetto, id_categoria) FROM stdin;
\.


--
-- Data for Name: configurazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configurazione (id, durata_timeslot_fisso, numero_max_timeslot, numero_offerte_contemporanee_utente, tipo_timeslot) FROM stdin;
\.


--
-- Data for Name: offerta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.offerta (id_utente_registrato, id_asta, data, credito_offerto) FROM stdin;
\.


--
-- Data for Name: oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.oggetto (id, id_asta, nome, descrizione, url_immagine) FROM stdin;
\.


--
-- Data for Name: tipo_asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_asta (nome) FROM stdin;
\.


--
-- Data for Name: utente_registrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente_registrato (id, username, password, email, telefono, credito_disponibile) FROM stdin;
02942f14-7636-4d81-bb48-31b8977edad7	userProva	password	userProva@gmail.com	\N	58
b505c3b6-7769-4c6f-a000-80e1615b77a8	Luca	1243	superBoh@boh.com	\N	666
\.


--
-- Name: asta Asta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "Asta_pkey" PRIMARY KEY (id);


--
-- Name: oggetto Oggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "Oggetto_pkey" PRIMARY KEY (id);


--
-- Name: amministratore amministratore_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT amministratore_pkey PRIMARY KEY (id);


--
-- Name: attributo_oggetto attributoOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "attributoOggetto_pkey" PRIMARY KEY (id_oggetto);


--
-- Name: attributo attributo_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT attributo_pkey PRIMARY KEY (id);


--
-- Name: categoria_oggetto categoriaOggetto_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "categoriaOggetto_pkey" PRIMARY KEY (id_oggetto, id_categoria);


--
-- Name: categoria categoria_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria
    ADD CONSTRAINT categoria_pkey PRIMARY KEY (id);


--
-- Name: configurazione configurazione_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.configurazione
    ADD CONSTRAINT configurazione_pkey PRIMARY KEY (id);


--
-- Name: amministratore email; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT email UNIQUE (email);


--
-- Name: oggetto nome; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT nome UNIQUE (nome);


--
-- Name: offerta offerta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT offerta_pkey PRIMARY KEY (id_utente_registrato, id_asta);


--
-- Name: tipo_asta tipoAsta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tipo_asta
    ADD CONSTRAINT "tipoAsta_pkey" PRIMARY KEY (nome);


--
-- Name: amministratore username; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.amministratore
    ADD CONSTRAINT username UNIQUE (username);


--
-- Name: utente_registrato utenteregistrato_email_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_email_key UNIQUE (email);


--
-- Name: utente_registrato utenteregistrato_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_pkey PRIMARY KEY (id);


--
-- Name: utente_registrato utenteregistrato_telefono_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_telefono_key UNIQUE (telefono);


--
-- Name: utente_registrato utenteregistrato_username_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.utente_registrato
    ADD CONSTRAINT utenteregistrato_username_key UNIQUE (username);


--
-- Name: oggetto idAsta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.oggetto
    ADD CONSTRAINT "idAsta" FOREIGN KEY (id_asta) REFERENCES public.asta(id);


--
-- Name: asta idAstaManager; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idAstaManager" FOREIGN KEY (id_asta_manager) REFERENCES public.utente_registrato(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idAttributo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idAttributo" FOREIGN KEY (id_attributo) REFERENCES public.attributo(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id);


--
-- Name: categoria_oggetto idCategoria; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idCategoria" FOREIGN KEY (id_categoria) REFERENCES public.categoria(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: asta idConfigurazione; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idConfigurazione" FOREIGN KEY (id_configurazione) REFERENCES public.configurazione(id) ON UPDATE CASCADE NOT VALID;


--
-- Name: attributo_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.attributo_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id);


--
-- Name: categoria_oggetto idOggetto; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.categoria_oggetto
    ADD CONSTRAINT "idOggetto" FOREIGN KEY (id_oggetto) REFERENCES public.oggetto(id) ON UPDATE CASCADE;


--
-- Name: offerta id_asta; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT id_asta FOREIGN KEY (id_asta) REFERENCES public.asta(id);


--
-- Name: offerta id_utente_registrato; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT id_utente_registrato FOREIGN KEY (id_utente_registrato) REFERENCES public.utente_registrato(id) NOT VALID;


--
-- Name: asta tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT tipo FOREIGN KEY (tipo) REFERENCES public.tipo_asta(nome) ON UPDATE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

