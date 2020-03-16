<template>
  <div class="creaAsta">
    <b-nav pills tabs fill>
      <b-nav-item>
        <router-link to="/creaasta">Crea Asta</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/visualizzaaste">Visualizza Aste</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/utentecredito">Gestisci Credito</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/utentegestione">Gestisci Account</router-link>
      </b-nav-item>
    </b-nav>

    <br />
    <br />

    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="Prezzo di partenza:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.prezzoPartenza"
          required
          type="number"
          placeholder="Inserisci il prezzo di partenza"
          min="1"
          step="any"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Tipo asta:" label-for="input-2">
        <b-form-select id="input-2" v-model="form.tipoAstaScelto" :options="tipiAste" required></b-form-select>
      </b-form-group>

      <b-form-group
        id="input-group-3"
        label="Durata timeslot (in secondi)"
        for="time-3"
        v-if="cambiaDurataTimeSlot"
      >
        <b-form-input
          id="time-3"
          v-model="form.durata"
          type="number"
          step="1"
          required
          min="10"
          max="86400"
        ></b-form-input>
      </b-form-group>

      <label v-if="!cambiaDurataTimeSlot">Durata timeslot fisso: {{durataTimeSlotFisso}}</label>

      <b-form-group id="input-group-4" label="Criterio di terminazione:" label-for="input-4">
        <b-form-select
          id="input-4"
          v-model="form.tipoTerminazioneScelto"
          :options="tipiTerminazione"
          required
        ></b-form-select>
      </b-form-group>

      <b-form-group id="input-group-5" label="Quanti oggetti vuoi inserire?" label-for="input-5">
        <b-form-input
          id="input-5"
          type="number"
          v-model="form.numeroOggetti"
          required
          min="1"
          step="1"
        ></b-form-input>
      </b-form-group>

      <b-form-group v-for="i in parseInt(this.form.numeroOggetti)" :key="i" label="Oggetto">
        <br />

        <b-form-group label="Nome: ">
          <b-form-input
            placeholder="Inserisci il nome dell' oggetto"
            v-model="form.nomeOggetti[i - 1]"
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Url immagine: ">
          <b-form-input
            type="url"
            placeholder="Inserisci l'url dell' immagine oggetto"
            v-model="form.urlOggetti[i - 1]"
          ></b-form-input>
        </b-form-group>

        <b-form-group label="Descrizione: ">
          <b-form-textarea
            placeholder="Inserisci una descrizione"
            v-model="form.descrizioneOggetti[i - 1]"
          ></b-form-textarea>
        </b-form-group>

        <b-form-group label="Categorie: ">
          <b-form-tags
            input-id="tags-basic"
            v-model="form.categorie"
            class="mb-2"
            placeholder="Aggiungi delle categorie"
          ></b-form-tags>
        </b-form-group>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        tipoAstaScelto: null,
        prezzoPartenza: null,
        tipoTerminazioneScelto: null,
        durata: null,
        numeroOggetti: 0,
        nomeOggetti: [],
        descrizioneOggetti: [],
        urlOggetti: [],
        categorie: []
      },
      tipiAste: ["busta chiusa", "superamento immediato"],
      tipiTerminazione: [
        "esaurimento totale timeslot",
        "timeslot senza offerte"
      ],
      cambiaDurataTimeSlot: false,
      durataTimeSlotFisso: null,
      datiOggetti: [],
      show: true,
      idConfig: null
    };
  },
  created: function() {
    fetch("http://localhost:8080/api/configurazione/configurazioni/ultima", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        if (response.tipoTimeSlot === "fisso") {
          this.cambiaDurataTimeSlot = false;
          this.durataTimeSlotFisso = response.durataTimeSlotFisso;
        } else {
          this.cambiaDurataTimeSlot = true;
        }
        this.idConfig = response.id;
      });
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
      var tipo =
        this.form.tipoAstaScelto === "busta chiusa"
          ? "busta_chiusa"
          : "superamento_immediato";

      var terminazione =
        this.form.tipoTerminazioneScelto === "timeslot senza offerte"
          ? "esaurimento_timeslot"
          : "max_timeslot";

      var durata = this.cambiaDurataTimeSlot
        ? new Date(this.form.durataTimeSlotFisso * 1000)
            .toISOString()
            .substr(11, 8)
        : this.durataTimeSlotFisso;

      fetch("http://localhost:8080/api/asta/aggiungi", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          astaManager: {
            id: localStorage.getItem("idLog")
          },
          configurazione: {
            id: this.idConfig
          },
          infoAsta: {
            tipo: tipo,
            prezzoPartenza: this.form.prezzoPartenza,
            dataInizio: new Date().toISOString(),
            durataTimeSlot: durata,
            rifiutata: false,
            criterioTerminazione: terminazione
          }
        })
      })
        .then(response => response.json())
        .then(response => {
          console.log(response);
          console.log("tutto ok");
          var idAsta = response;
          var i;
          for (i = 0; i < this.form.numeroOggetti; i++) {
            this.datiOggetti.push({
              nome: this.form.nomeOggetti[i],
              descrizione: this.form.descrizioneOggetti[i],
              urlImmagine: this.form.urlOggetti[i]
            });

            fetch("http://localhost:8080/api/oggetto/inserisci/" + idAsta, {
              method: "post",
              headers: {
                Accept: "application/json",
                "Content-Type": "application/json"
              },
              body: JSON.stringify({
                nome: this.form.nomeOggetti[i],
                descrizione: this.form.descrizioneOggetti[i],
                urlImmagine: this.form.urlOggetti[i]
              })
            })
              .then(response => response.json())
              .then(response => {
                console.log(response);
              });
          }
        });
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.tipoAstaScelto = null;
      this.form.tipoTerminazioneScelto = null;
      this.form.prezzoPartenza = null;
      this.form.durata = null;
      this.form.numeroOggetti = 0;
      this.datiOggetti = [];
      this.form.nomeOggetti = [];
      this.form.descrizioneOggetti = [];
      this.form.urlOggetti = [];
      this.form.categorie = [];
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>