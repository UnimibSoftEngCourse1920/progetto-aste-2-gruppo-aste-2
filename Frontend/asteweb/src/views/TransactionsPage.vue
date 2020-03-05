<template>
  <div>
    <b-table striped hover :offers="offers"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      offers: []
    };
  },
  mounted: function() {
    fetch("http://localhost:8080/api/offerta/offerte", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.offers = response;
      });
  }
};
</script>