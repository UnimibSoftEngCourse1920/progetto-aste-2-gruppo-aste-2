<template>
  <div class="utenteCredito">
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

    <b-list-group>
      <b-list-group-item>Credito totale: {{this.creditoTotale}}</b-list-group-item>
      <b-list-group-item>Credito diponibibile: {{this.creditoDisponibile}}</b-list-group-item>
      <b-list-group-item>Credito impegnato: {{this.creditoImpegnato}}</b-list-group-item>
    </b-list-group>

    <b-form @submit="onSubmit" @reset="onReset">
      <b-form-group
        id="input-group-1"
        label="Credito da aggiungere:"
        label-for="input-1"
        description="Credito da aggiungere al bilancio"
      >
        <b-form-input id="input-1" type="number" v-model="creditoDaAggiungere" min="0" step="any"></b-form-input>
      </b-form-group>

      <b-form-group
        v-if="creditoDisponibile > 0"
        id="input-group-2"
        label="Credito da togliere:"
        label-for="input-2"
        description="Credito da togliere dal bilancio"
      >
        <b-form-input id="input-2" type="number" v-model="creditoDaRestituire" min="0" step="any"></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>



<script>
export default {
  data() {
    return {
      creditoDaAggiungere: 0,
      creditoDaRestituire: 0,
      creditoTotale: 0,
      creditoDisponibile: 0,
      creditoImpegnato: 0
    };
  },
  created: function() {
    fetch(
      "http://localhost:8080/api/utenteregistrato/credito/" +
        localStorage.getItem("idLog"),
      {
        method: "get"
      }
    )
      .then(response => response.json())
      .then(response => {
        this.creditoTotale = response.creditoTotale;
        this.creditoDisponibile = response.creditoDisponibile;
        this.creditoImpegnato = response.creditoImpegnato;
      });
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      fetch(
        "http://localhost:8080/api/utenteregistrato/credito/aggiungi/" +
          localStorage.getItem("idLog") +
          "/" +
          (parseFloat(this.creditoDaAggiungere) -
            parseFloat(this.creditoDaRestituire)),
        {
          method: "get"
        }
      ).then(response => {
        if (response) {
          this.creditoTotale =
            parseFloat(this.creditoTotale) +
            parseFloat(this.creditoDaAggiungere - this.creditoDaRestituire);
          this.creditoDisponibile =
            parseFloat(this.creditoDisponibile) +
            parseFloat(this.creditoDaAggiungere - this.creditoDaRestituire);

          if (this.creditoDisponibile === 0) {
            this.creditoDaRestituire = 0;
          }
        }
      });
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.creditoDaAggiungere = 0;
      this.creditoDaRestituire = 0;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>