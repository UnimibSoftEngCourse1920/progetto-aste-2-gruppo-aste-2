--
-- PostgreSQL database cluster dump
--

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE lime;
ALTER ROLE lime WITH NOSUPERUSER INHERIT NOCREATEROLE CREATEDB LOGIN NOREPLICATION NOBYPASSRLS PASSWORD 'md5a0147251ec39532acade3a53f74ffea4';
CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'md53175bce1d3201d16594cebf9d7eb3f9d';






--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- PostgreSQL database dump complete
--

--
-- Database "databaseaste" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- Name: databaseaste; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE databaseaste WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE databaseaste OWNER TO postgres;

\connect databaseaste

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
-- Name: tipoterminazioneasta; Type: TYPE; Schema: public; Owner: postgres
--

CREATE TYPE public.tipoterminazioneasta AS ENUM (
    'esaurimento_timeslot',
    'max_timeslot'
);


ALTER TYPE public.tipoterminazioneasta OWNER TO postgres;

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
    data_inizio timestamp(1) without time zone NOT NULL,
    data_fine timestamp(1) without time zone,
    durata_timeslot time without time zone NOT NULL,
    tipo character varying(100) NOT NULL,
    prezzo_partenza real NOT NULL,
    rifiutata boolean DEFAULT false NOT NULL,
    criterio_terminazione public.tipoterminazioneasta NOT NULL
);


ALTER TABLE public.asta OWNER TO postgres;

--
-- Name: attributo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.attributo (
    id uuid NOT NULL,
    id_categoria character varying(100) NOT NULL,
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
    id character varying(100) NOT NULL
);


ALTER TABLE public.categoria OWNER TO postgres;

--
-- Name: categoria_oggetto; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categoria_oggetto (
    id_oggetto uuid NOT NULL,
    id_categoria character varying(100) NOT NULL
);


ALTER TABLE public.categoria_oggetto OWNER TO postgres;

--
-- Name: configurazione; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.configurazione (
    id uuid NOT NULL,
    numero_max_timeslot bigint NOT NULL,
    numero_offerte_contemporanee_utente bigint NOT NULL,
    tipo_timeslot public.tipotimeslotasta NOT NULL,
    data_creazione timestamp(2) without time zone NOT NULL,
    penale real NOT NULL,
    durata_timeslot_fisso time(1) without time zone
);


ALTER TABLE public.configurazione OWNER TO postgres;

--
-- Name: offerta; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.offerta (
    id_offerente uuid NOT NULL,
    id_asta uuid NOT NULL,
    data_offerta timestamp(1) without time zone NOT NULL,
    credito_offerto real NOT NULL,
    id uuid NOT NULL
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
    credito_disponibile real DEFAULT 0 NOT NULL,
    notifica_email boolean DEFAULT false,
    notifica_sms boolean DEFAULT false
);


ALTER TABLE public.utente_registrato OWNER TO postgres;

--
-- Data for Name: amministratore; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.amministratore (username, id, email, password) FROM stdin;
Provolone	93fdad81-6724-46d0-9c90-c6b237a908d7	parmigiano@brie.com	boh
\.


--
-- Data for Name: asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.asta (id, id_asta_manager, id_configurazione, data_inizio, data_fine, durata_timeslot, tipo, prezzo_partenza, rifiutata, criterio_terminazione) FROM stdin;
0b0f9e7b-425b-4673-96f1-a4eaf28eb19f	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-02-02 01:00:00	\N	06:00:00	busta_chiusa	0	f	max_timeslot
af903c19-baa2-48ac-9173-cca1307ff0db	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:31:43.7	\N	00:11:06	busta_chiusa	12	f	max_timeslot
f9f07cd3-f580-46c8-b18b-a667208e6101	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:35:29.5	\N	00:11:06	busta_chiusa	666	f	esaurimento_timeslot
97a54467-1b84-438c-a7ba-b1bee7550aaa	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:37:24.4	\N	00:11:06	superamento_immediato	3432	f	esaurimento_timeslot
dee0bdfe-5236-4b17-b56d-62a0bdc12b01	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:37:59.1	\N	00:11:06	busta_chiusa	2	f	max_timeslot
ac673f65-2093-473d-8bc3-4cf84ded8141	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:48:10	\N	00:11:06	busta_chiusa	42	f	max_timeslot
72abfb24-6f56-40c6-afd4-ecf7f5880a68	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:49:48.1	\N	00:11:06	superamento_immediato	555	f	max_timeslot
f5756e7f-2552-41c3-90df-cf4fe781fd38	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:51:33.8	\N	00:11:06	busta_chiusa	333	f	max_timeslot
8cbc5444-882f-49e1-9122-97b4fb187a4d	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 11:55:00.7	\N	00:11:06	busta_chiusa	999	f	esaurimento_timeslot
3b76e391-abec-4e3a-865f-3480dda5d1ed	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 12:05:59.5	\N	00:11:06	busta_chiusa	2	f	max_timeslot
5716f564-2c91-4613-a413-eba54847fe05	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 12:07:46.4	\N	00:11:06	busta_chiusa	456	f	esaurimento_timeslot
14f9525e-4282-4395-82c2-2935c0fcef21	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 12:08:46	\N	00:11:06	busta_chiusa	456	f	esaurimento_timeslot
105b00d9-8fd1-4cda-8003-f443041ff483	b505c3b6-7769-4c6f-a000-80e1615b77a8	5455bc99-8fe9-4d45-9347-4c0aa180556c	2020-03-16 12:09:33.4	\N	00:11:06	busta_chiusa	456	f	esaurimento_timeslot
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

COPY public.categoria (id) FROM stdin;
mobile
elettrodomestico
\.


--
-- Data for Name: categoria_oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.categoria_oggetto (id_oggetto, id_categoria) FROM stdin;
\.


--
-- Data for Name: configurazione; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.configurazione (id, numero_max_timeslot, numero_offerte_contemporanee_utente, tipo_timeslot, data_creazione, penale, durata_timeslot_fisso) FROM stdin;
5455bc99-8fe9-4d45-9347-4c0aa180556c	10	10	fisso	2020-03-09 18:00:35.86	0.6	00:11:06
\.


--
-- Data for Name: offerta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.offerta (id_offerente, id_asta, data_offerta, credito_offerto, id) FROM stdin;
\.


--
-- Data for Name: oggetto; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.oggetto (id, id_asta, nome, descrizione, url_immagine) FROM stdin;
1aa8dcab-8033-43fe-89c6-dd6697533287	5716f564-2c91-4613-a413-eba54847fe05	Divano	Un bellissimo divano	https://www.youtube.com/watch?v=m1mLNJU0zAs
c6e7b09f-6cb2-44df-a56d-9de2096864f6	14f9525e-4282-4395-82c2-2935c0fcef21	Lampada	Una lampada stratosferica	https://www.youtube.com/watch?v=m1mLNJU0zAs
5cb0a4de-686e-43aa-a970-4d34f79433d3	105b00d9-8fd1-4cda-8003-f443041ff483	Divano	Un bellissimo divano	https://www.youtube.com/watch?v=m1mLNJU0zAs
0ed666d6-270d-4cb3-9fb7-f4d449e27590	105b00d9-8fd1-4cda-8003-f443041ff483	Lampada	Una lampada stratosferica	https://www.youtube.com/watch?v=m1mLNJU0zAs
\.


--
-- Data for Name: tipo_asta; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tipo_asta (nome) FROM stdin;
busta_chiusa
superamento_immediato
\.


--
-- Data for Name: utente_registrato; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.utente_registrato (id, username, password, email, telefono, credito_disponibile, notifica_email, notifica_sms) FROM stdin;
6c7983dc-e7f6-498b-801c-eee9e6c8e489	dsduca	1243	lol@boh.com	\N	666	f	f
55a32da1-3cc7-45d6-b019-a4b9f824246e	Carlo	666	carlo@carlo.com	\N	0	f	f
b505c3b6-7769-4c6f-a000-80e1615b77a8	Luca	1243	superBoh@boh.com	0392326424	25.5	t	t
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
-- Name: offerta offerta_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.offerta
    ADD CONSTRAINT offerta_pkey PRIMARY KEY (id);


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
-- Name: asta idConfig; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT "idConfig" FOREIGN KEY (id_configurazione) REFERENCES public.configurazione(id) ON UPDATE CASCADE ON DELETE SET NULL NOT VALID;


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
    ADD CONSTRAINT id_utente_registrato FOREIGN KEY (id_offerente) REFERENCES public.utente_registrato(id) NOT VALID;


--
-- Name: asta tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.asta
    ADD CONSTRAINT tipo FOREIGN KEY (tipo) REFERENCES public.tipo_asta(nome) ON UPDATE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--

--
-- Database "limesurveyDB" dump
--

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- Name: limesurveyDB; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE "limesurveyDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.utf8' LC_CTYPE = 'en_US.utf8';


ALTER DATABASE "limesurveyDB" OWNER TO postgres;

\connect "limesurveyDB"

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
-- PostgreSQL database dump complete
--

--
-- Database "postgres" dump
--

\connect postgres

--
-- PostgreSQL database dump
--

-- Dumped from database version 12.2
-- Dumped by pg_dump version 12.2

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
-- PostgreSQL database dump complete
--

--
-- PostgreSQL database cluster dump complete
--

