<template>
  <div>
    <b-nav pills tabs fill>
      <b-nav-item>
        <router-link to="/creaconfig">Crea Configurazione</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/ultimaconfig">Configurazione attiva</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/transactions">Visualizza transazioni</router-link>
      </b-nav-item>
    </b-nav>
    <br />

    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="Durata timeslot (in secondi)" for="time-1">
        <b-form-input
          id="time-1"
          v-model="form.durata"
          type="number"
          step="1"
          required
          min="10"
          max="86400"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-6" label="Numero massimo di timeslot per asta" for="maxSlot">
        <b-form-input
          id="maxSlot"
          v-model="form.numeroTimeSlot"
          type="number"
          step="1"
          required
          min="1"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="Offerte contemporanee per un utente" for="cont">
        <b-form-input
          id="cont"
          v-model="form.offerteContempo"
          type="number"
          step="1"
          required
          min="1"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Penale" for="range-1">
        <b-form-input
          id="range-1"
          v-model="form.penale"
          type="range"
          min="0"
          max="1"
          step="0.1"
          required
        ></b-form-input>
        <div class="mt-2">Penale: {{ form.penale * 100 }} %</div>
      </b-form-group>

      <b-form-group id="input-group-4" label="Tipo timeslot" for="checkboxes-1">
        <b-form-radio-group v-model="form.tipoTimeSlot" id="checkboxes-1" required>
          <b-form-radio value="fisso">Fisso</b-form-radio>
          <b-form-radio value="variabile">Variabile</b-form-radio>
        </b-form-radio-group>
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
        penale: "0",
        offerteContempo: "0",
        durata: "0",
        tipoTimeSlot: "",
        numeroTimeSlot: "0"
      },
      show: true
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));

      fetch("http://localhost:8080/api/configurazione/inserisci", {
        method: "post",
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          tipoTimeSlot: this.form.tipoTimeSlot,
          maxTimeSlot: this.form.numeroTimeSlot,
          maxOfferte: this.form.offerteContempo,
          penale: this.form.penale,
          dataCreazione: new Date().toISOString(),

          durataTimeSlotFisso: new Date(this.form.durata * 1000)
            .toISOString()
            .substr(11, 8)
        })
      });
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.penale = 0;
      this.form.offerteContempo = "1";
      this.form.durata = 10;
      this.form.tipoTimeSlot = "";
      this.form.numeroTimeSlot = 1;
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>