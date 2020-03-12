<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group
        id="input-group-1"
        label="Email address:"
        label-for="input-1"
        description="We'll never share your email with anyone else."
      >
        <b-form-input
          id="input-1"
          v-model="form.email"
          type="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input id="input-2" v-model="form.username" required placeholder="Enter name"></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.password"
          type="password"
          required
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-4">
        <b-form-radio-group v-model="form.tipoUtente" id="checkboxes-4" required>
          <b-form-radio value="utente">Utente</b-form-radio>
          <b-form-radio value="amministratore">Amministratore</b-form-radio>
        </b-form-radio-group>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      form: {
        email: "",
        username: "",
        password: "",
        tipoUtente: ""
      },
      show: true,
      idAdmin: "",
      idUtente: ""
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));

      if (this.form.tipoUtente === "utente") {
        fetch("http://localhost:8080/api/utenteregistrato/controlla/utente", {
          method: "post",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username: this.form.username,
            password: this.form.password,
            email: this.form.email
          })
        })
          .then(response => response.json())
          .then(response => {
            console.log(response);
            if (response === true) {
              // move to user homepage
              alert("Utente trovato");
              this.$router.push({
                name: "user"
              });
            } else {
              alert("User not found");
            }
          });
      } else if (this.form.tipoUtente === "amministratore") {
        fetch(
          "http://localhost:8080/api/amministratore/controlla/amministratore",
          {
            method: "post",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              username: this.form.username,
              password: this.form.password,
              email: this.form.email
            })
          }
        )
          .then(response => response.json())
          .then(response => {
            if (response === true) {
              // move to admin homepage
              alert("Amministratore trovato");
              fetch("http://localhost:8080/api/amministratore/id", {
                method: "post",
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({
                  username: this.form.username,
                  password: this.form.password,
                  email: this.form.email
                })
              })
                .then(response => response.json())
                .then(response => {
                  this.idAdmin = response;
                  console.log(this.idAdmin);
                  this.$router.push({
                    name: "admin"
                  });
                });
            } else {
              alert("Amministratore non trovato");
            }
          });
      }
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.email = "";
      this.form.name = "";
      this.form.tipoUtente = "";
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>