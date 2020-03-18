<template>
  <div>
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
    <p>Credito disponibile: {{creditoDisponibile}}</p>
    <p>Prezzo partenza : {{prezzoPartenza}}</p>
    <p v-if="offertaMinima > 0">Ultima offerta: {{offertaMinima}}</p>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="Offerta:" label-for="input-1">
        <b-form-input
          id="input-1"
          v-model="form.offerta"
          required
          type="number"
          placeholder="Inserisci l'offerta che vuoi fare"
          min="prezzoPartenza"
          step="any"
        ></b-form-input>
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
      form: {
        offerta: 0
      },
      show: true,
      prezzoPartenza: 0,
      offertaMinima: -1,
      creditoDisponibile: 0
    };
  },
  created: function() {
    fetch("http://localhost:8080/api/asta/" + this.$route.params.idAsta, {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.prezzoPartenza = response.infoAsta.prezzoPartenza;
      });
  },
  mounted: function() {
    window.setInterval(() => {
      fetch(
        "http://localhost:8080/api/offerta/offerte/asta/" +
          this.$route.params.idAsta +
          "/maggiore",
        {
          method: "get"
        }
      )
        .then(response => response.json())
        .then(response => {
          this.offertaMinima = response.creditoOfferto;
        });

      fetch(
        "http://localhost:8080/api/utenteregistrato/credito/" +
          localStorage.getItem("idLog"),
        {
          method: "get"
        }
      )
        .then(response => response.json())
        .then(response => {
          this.creditoDisponibile = response.creditoDisponibile;
        });
    }, 5000);
  },

  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));

      if (this.form.offerta > this.creditoDisponibile) {
        alert("Non hai abbastanza soldi");
      }

      if (
        this.form.offerta >= this.offertaMinima &&
        this.form.offerta >= this.prezzoPartenza
      ) {
        fetch(
          "http://localhost:8080/api/asta/partecipa/" +
            this.$route.params.idAsta,
          {
            method: "post",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              creditoOfferto: this.form.offerta,
              dataOfferta: new Date().toISOString(),
              offerente: {
                id: localStorage.getItem("idLog")
              }
            })
          }
        ).then(() => {
          fetch(
            "http://localhost:8080/api/utenteregistrato/credito/aggiungi/" +
              localStorage.getItem("idLog") +
              "/" +
              -1 * parseFloat(this.form.offerta),
            {
              method: "get"
            }
          );
        });
      } else {
        alert("Offerta troppo bassa");
      }
    },
    onReset(evt) {
      evt.preventDefault();
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
        this.form.offerta = 0;
      });
    }
  }
};
</script>