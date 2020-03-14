<template>
  <div class="home">
    <template v-for="asta in aste">
      <li v-for="oggetto in asta.oggetti" :key="oggetto">
        <b-card img-src="oggetto.urlImmagine" img-alt="Card image" img-left class="mb-3">
          <b-card-text>
            <p class="text-left">nome: {{oggetto.nome}}</p>
            <p class="text-left">prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
            <p class="text-left">{{asta.infoAsta.tipo}}</p>
          </b-card-text>
        </b-card>
      </li>
    </template>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      aste: []
    };
  },
  created: function() {
    fetch("http://localhost:8080/api/asta/aste", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.aste = response;
      });
  }
};
</script>