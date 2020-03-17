<template>
  <div class="home">
    <div class="search-wrapper">
      <label>Ricerca:</label>
      <input type="text" v-model="search" placeholder="Search" />
    </div>
    <li v-for="asta in filteredList()" :key="asta.id">
      <b-card :img-src="asta.oggetti[0].urlImmagine" img-alt="Card image" img-left class="mb-3">
        <b-card-text>
          <p class="text-left">nome: {{asta.oggetti[0].nome}}</p>
          <div v-if="asta.infoAsta.tipo==='superamento_immediato'">
            <div v-if="asta.prezzoPiuAlto &&asta.prezzoPiuAlto">
              <p class="text-left">offerta corrente: {{asta.prezzoPiuAlto}}</p>
            </div>
            <div v-else>
              <p class="text-left">Prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
            </div>
          </div>
          <div v-else>
            <p class="text-left">Prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
          </div>
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
      search: ""
    };
  },
  methods: {
    filteredList() {
      if (this.search.length > 0)
        return this.aste.filter(asta => {
          return asta.oggetti[0].nome.includes(this.search);
        });
      else return this.aste;
    },
    trovaOffertaMaggiore(asta) {
      let vm = this;
      fetch(
        "http://localhost:8080/api/offerta/offerte/asta/" +
          asta.id +
          "/maggiore",
        {
          method: "get"
        }
      )
        .then(response => response.text())
        .then(response => {
          let b = asta;
          b.prezzoPiuAlto = response;
          vm.$set(asta, b);
        });
    }
  },
  created: function() {
    let vm = this;
    fetch("http://localhost:8080/api/asta/aste", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.aste = response;
        this.aste.forEach(asta => {
          if (
            asta.infoAsta.tipo === "superamento_immediato" &&
            asta.offerte != null
          ) {
            vm.trovaOffertaMaggiore(asta);
          }
        });
      });
  },
  computed: {}
};
</script>