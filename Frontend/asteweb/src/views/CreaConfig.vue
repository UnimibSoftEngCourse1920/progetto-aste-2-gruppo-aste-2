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
      <b-form-group id="input-group-1" label="Durata timeslot" for="time-1">
        <b-form-input id="time-1" v-model="form.durataTimeSlot" type="time" step="1" required></b-form-input>
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
        penale: "0",
        offerteContempo: "",
        durata: "",
        checked: []
      },      
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
      this.form.penale = 0;
      this.form.offerteContempo = "";
      this.form.durataTimeslot = "00:00:00-AM",
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>