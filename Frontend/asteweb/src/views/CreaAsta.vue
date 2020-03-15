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

      <b-form-group id="input-group-3" label="Durata timeslot (in secondi)" for="time-3">
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

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
    <b-card class="mt-3" header="Form Data Result">
      <pre class="m-0">{{ form }}</pre>
    </b-card>

    <h1 v-for="i in 10" :key="i">Ciao</h1>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        tipoAstaScelto: null,
        prezzoPartenza: null,
        durata: null
      },
      tipiAste: ["busta chiusa", "superamento immediato"],

      show: true
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.tipoAstaScelto = null;
      this.form.prezzoPartenza = null;
      this.form.durata = null;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>