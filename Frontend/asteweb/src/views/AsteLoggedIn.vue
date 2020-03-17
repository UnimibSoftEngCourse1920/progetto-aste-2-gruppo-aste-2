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
    <b-card
      v-for="i in this.asteDaVisualizzare.length"
      :key="i"
      :id="i"
      img-src="https://placekitten.com/300/300"
      img-alt="Card image"
      img-left
      class="mb-3"
    >
      <b-card-text>
        <p>Tipo di asta: {{asteDaVisualizzare[i - 1].infoAsta.tipo}}</p>
        <p>Prezzo di partenza: {{asteDaVisualizzare[i - 1].infoAsta.prezzoPartenza}}</p>
        <p>
          Iniziata il:
          {{asteDaVisualizzare[i - 1].infoAsta.dataInizio
          }}
        </p>
      </b-card-text>

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
      ],
      asteDaVisualizzare: []
    };
  },
  watch: {
    selected: function() {
      if (this.selected === "a") {
        fetch("http://localhost:8080/api/asta/aste/incorso", { method: "get" })
          .then(response => response.json())
          .then(response => {
            this.asteDaVisualizzare = response;
          })
          .then(() => {
            var i;
            for (i = 0; i < this.asteDaVisualizzare.length; i++) {
              console.log(i);
              console.log(document.getElementById("1"));
            }
          });
      }
    }
  }
};
</script>