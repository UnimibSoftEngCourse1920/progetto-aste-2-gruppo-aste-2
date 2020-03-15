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
      id: ""
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
      fetch(
        "http://localhost:8080/api/utenteregistrato/controlla/email" +
          this.form.email,
        {
          method: "get",
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            username: this.form.email
          })
        }
      )
        .then(response => response.json())
        .then(response => {
          console.log(response);
          if (response === false) {
            // move to user homepage
            alert("email non resgistrata");
            fetch(
              "http://localhost:8080/api/utenteregistrato/controlla/username" +
                this.form.username,
              {
                method: "get",
                headers: {
                  Accept: "application/json",
                  "Content-Type": "application/json"
                },
                body: JSON.stringify({
                  username: this.form.username
                })
              }
            )
              .then(response => response.json())
              .then(response => {
                console.log(response);
                if (response === false) {
                  // move to user homepage
                  alert("username non resgistrato");
                  fetch("http://localhost:8080/api/utenteregistrato/aggiungi", {
                    method: "post",
                    headers: {
                      Accept: "application/json",
                      "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                      username: this.form.username,
                      email: this.form.email,
                      password: this.form.password
                    })
                  });
                }
              });
          }
        });
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