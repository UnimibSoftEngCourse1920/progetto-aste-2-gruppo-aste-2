<template>
  <div>
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