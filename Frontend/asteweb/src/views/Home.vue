<template>
  <div class="home">
    <input type="text" v-model="search" placeholder="search object"/>
    <p></p>
    <li v-for="asta in aste" :key="asta.id">
      <div v-for="oggetto in asta.oggetti" :key="oggetto.id">
        <b-card img-src="oggetto.urlImmagine" img-alt="Card image" img-left class="mb-3">
          <b-card-text>
            <p class="text-left">nome: {{oggetto.nome}}</p>
            <p class="text-left">prezzo: {{asta.infoAsta.prezzoPartenza}}</p>
            <p class="text-left">{{asta.infoAsta.tipo}}</p>
          </b-card-text>
        </b-card>
      </div>
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
      search:''
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
  },
  computed:{
    filterObject: function(){
      return this.aste.filter((asta)=>{
        return asta.oggetti[0].nome.match(this.search)
      });

    }
  }
  
};
</script>