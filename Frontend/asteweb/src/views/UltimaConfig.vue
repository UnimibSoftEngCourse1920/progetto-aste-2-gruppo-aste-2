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
    <b-table striped hover :items="config"></b-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      config: []
    };
  },
  created: function() {
    fetch("http://localhost:8080/api/configurazione/configurazioni/ultima", {
      method: "get"
    })
      .then(response => response.json())
      .then(response => {
        this.config.push(response);
      });
  }
};
</script>