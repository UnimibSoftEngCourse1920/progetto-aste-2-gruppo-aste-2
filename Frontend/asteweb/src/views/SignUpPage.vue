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
          v-model="form.emailnuova"
          type="email"
          required
          placeholder="Enter email"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input id="input-2" v-model="form.usernamenuovo" required placeholder="Enter name"></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.passwordnuova"
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
        emailnuova: "",
        usernamenuovo: "",
        passwordnuova: "",
      },
      show:true,
      valid: false
    };
  },
  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));
      fetch(
        "http://localhost:8080/api/utenteregistrato/controlla/username/" +
          this.form.usernamenuovo,
          {
          method: "get"
          }
        )
        .then(response => response.json())
        .then(response => {
          if (response === false) {
            this.valid=true
          }
          else
           this.valid=false
        });

        fetch(
        "http://localhost:8080/api/utenteregistrato/controlla/email/" +
          this.form.emailnuova,
          {
          method: "get"
          }
        )
        .then(response => response.json())
        .then(response => {
          if (response === false && this.valid===true) {
            this.valid=true
          }
          else
            this.valid=false
        });
        if(this.valid){
        fetch(
          "http://localhost:8080/api/utenteregistrato/aggiungi",
          {
            method: "post",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              username: this.form.usernamenuovo,
              password: this.form.passwordnuova,
              email: this.form.emailnuova,
            })
          }
        )
          .then(response => response.json())
          .then(response => {
            console.log(response);
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