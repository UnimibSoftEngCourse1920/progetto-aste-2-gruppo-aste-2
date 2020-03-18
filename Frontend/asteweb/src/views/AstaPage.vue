<template>
  <div>
    <div v-if="asta.infoAsta">
      <div v-for="oggetto in asta.oggetti" :key="oggetto.id">
        <b-img :src="oggetto.urlImmagine" fluid alt="Fluid image"></b-img>
        <p class="text-center">nome: {{oggetto.nome}}</p>
        <p class="text-center">descrizione: {{oggetto.descrizione}}</p>
      </div>
      <div v-if="asta.infoAsta.tipo==='superamento_immediato'">
        <div v-if="asta.prezzoPiuAlto &&asta.prezzoPiuAlto">
          <p class="text-center">offerta corrente: {{asta.prezzoPiuAlto}}</p>
        </div>
      </div>
       <div v-else>
          <p class="text-center">Prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
        </div>
      </div>
       <div v-else=""> caricamento</div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      asta: []
    };
  },
  created: function() {
    let vm = this;
    fetch("http://localhost:8080/api/asta/" + this.$route.params.idasta, {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.asta = response;
        vm.$set(vm.asta, response)
        if (
          this.asta.infoAsta.tipo === "superamento_immediato" &&
          this.asta.offerte != null
        ) {
          vm.trovaOffertaMaggiore(this.asta);
        }
      });
  },
  methods: {
    trovaOffertaMaggiore(asta) {
      let vm = this;
      fetch(
        "http://localhost:8080/api/offerta/offerte/asta/" +
          this.$route.params.idasta +
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
  }
};
</script>