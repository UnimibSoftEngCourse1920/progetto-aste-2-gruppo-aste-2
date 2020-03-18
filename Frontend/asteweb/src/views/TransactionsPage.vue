<template>
  <div>
    <b-nav pills tabs fill>
      <b-nav-item>
        <router-link to="/creaconfig">Crea Configurazione</router-link>
      </b-nav-item>
      <b-nav-item>
        <router-link to="/ultimaconfig">Configurazione attiva</router-link>
      </b-nav-item>
      <b-nav-item active>
        <router-link to="/transactions">Visualizza transazioni</router-link>
      </b-nav-item>
    </b-nav>
    <b-table striped hover :items="offers"></b-table>
  </div>
</template>


<script>
export default {
  data() {
    return {
      offers: []
    };
  },
  created: function() {
    fetch("http://localhost:8080/api/offerta/offerte", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        response.forEach(element => {
          this.offers.push({
            idOfferta: element.id,
            creditoOfferto: element.creditoOfferto,
            dataOfferta: element.dataOfferta,
            offerente: element.offerente.id
          });
        });
      });
  }
};
</script>