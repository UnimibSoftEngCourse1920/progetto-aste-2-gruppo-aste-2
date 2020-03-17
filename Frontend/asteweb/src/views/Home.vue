<template>
  <div class="home">
    <div class="search-wrapper">
      <label>Ricerca:</label>
      <input type="text" v-model="search" placeholder="Search" />
    </div>
    <li v-for="asta in filteredList()" :key="asta.id">
        <b-card img-src="asta.oggetti[0].urlImmagine" img-alt="Card image" img-left class="mb-3">
          <b-card-text>
            <p class="text-left">nome: {{asta.oggetti[0].nome}}</p>
            <div v-if="asta.infoAsta.tipo===superamento_immediato && offerta>0" >
              <p class="text-left">offerta corrente: {{offerta}}</p>
            </div>
            <div v-else>
              <p class="text-left">Prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
            <p class="text-left">{{asta.infoAsta.tipo}}</p>
          </b-card-text>
        </b-card>
    </li>
  </div>
</template>

<script>
export default {
  name: "Home",
  components: {},
  data() {
    return {
      aste: [],
      search: "",
      offerta: 0
    };
  },
  methods:{    
    filteredList() {
      if (this.search.length > 0)
        return this.aste.filter(asta => {
          return asta.oggetti[0].tipo.includes(this.search);
        });
      else return this.aste;
    }},
  created: function() {
    fetch("http://localhost:8080/api/asta/aste", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.aste = response;
      });
    
    fetch("http://localhost:8080/api/offerte/asta"+ asta.id +"/maggiore", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.offerta = response;
      });
  },
  computed: {

  }
};
</script>