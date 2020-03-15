<template>
  <div class="aste">
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

    <b-form-select v-model="selected" :options="options"></b-form-select>
    <br />
    <br />
    <b-card img-src="https://placekitten.com/300/300" img-alt="Card image" img-left class="mb-3">
      <b-card-text>Some quick example text to build on the card and make up the bulk of the card's content.</b-card-text>
      <b-button>Prova</b-button>
    </b-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      selected: null,
      options: [
        {
          value: "a",
          text: "Visualizza aste in corso"
        },
        { value: "b", text: "Visualizza aste vinte" },
        { value: "c", text: "Visualizza aste dove sei massimo offerente" }
      ]
    };
  },
  watch: {
    selected: function() {
      if (this.selected === "a") {
        console.log("a");
      }
    }
  }
};
</script>