<template>
  <div>
    <b-form @submit="onSubmit" @reset="onReset" v-if="show">
      <b-form-group id="input-group-1" label="Nuova email:" label-for="input-1">
        <b-form-input id="input-1" v-model="form.email" type="email" placeholder="Inserisci email"></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2" label="Username:" label-for="input-2">
        <b-form-input id="input-2" v-model="form.username" placeholder="Enter Username"></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-3" label="Password:" label-for="input-3">
        <b-form-input
          id="input-3"
          v-model="form.password"
          type="password"
          placeholder="Enter password"
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-5" label="Numero telefono:" label-for="input-5">
        <b-form-input
          id="input-5"
          v-model="form.tel"
          type="tel"
          placeholder="Inserisci numero telefono"
        ></b-form-input>
      </b-form-group>

      <b-form-group label="Notifiche sms">
        <b-form-radio v-model="form.selectedSms" name="some-radios" value="A">Abilita sms</b-form-radio>
        <b-form-radio v-model="form.selectedSms" name="some-radios" value="B">Disabilita sms</b-form-radio>
      </b-form-group>

      <b-form-group label="Notifiche email">
        <b-form-radio v-model="form.selectedEmail" name="some-radios" value="A">Abilita email</b-form-radio>
        <b-form-radio v-model="form.selectedEmail" name="some-radios" value="B">Disabilita email</b-form-radio>
      </b-form-group>

      <b-button type="submit" variant="primary">Submit</b-button>
      <b-button type="reset" variant="danger">Reset</b-button>
    </b-form>

    <b-alert v-model="showAlertEmail" variant="danger" dismissible>Email occupata</b-alert>
    <b-alert v-model="showAlertUsername" variant="danger" dismissible>Username occupato</b-alert>

    <b-card class="mt-3" header="Dati correnti">
      <pre class="m-0">{{ form }}</pre>
      <pre class="m-0">{{ userInfo }}</pre>
    </b-card>
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
        selectedSms: "",
        selectedEmail: "",
        tel: ""
      },
      show: true,
      userInfo: null,
      valid: true,
      showAlertEmail: false,
      showAlertUsername: false
    };
  },
  created: function() {
    fetch(
      "http://localhost:8080/api/utenteregistrato/" +
        localStorage.getItem("idLog"),
      {
        method: "get"
      }
    )
      .then(response => response.json())
      .then(response => {
        this.userInfo = response;
      });
  },

  methods: {
    onSubmit(evt) {
      evt.preventDefault();
      alert(JSON.stringify(this.form));

      fetch(
        "http://localhost:8080/api/utenteregistrato/controlla/username/" +
          this.form.username,
        {
          method: "get"
        }
      )
        .then(response => response.json())
        .then(response => {
          if (response === true) {
            this.showAlertUsername = true;
            this.valid = false;
          }
        });

      fetch(
        "http://localhost:8080/api/utenteregistrato/controlla/email/" +
          this.form.email,
        {
          method: "get"
        }
      )
        .then(response => response.json())
        .then(response => {
          if (response === true) {
            this.showAlertEmail = true;
            this.valid = false;
          }
        });

      if (this.valid) {
        var userAggiornato =
          this.form.username === ""
            ? this.userInfo.username
            : this.form.username;

        var emailAggiornata =
          this.form.email === "" ? this.userInfo.email : this.form.email;

        var passwordAggiornata =
          this.form.password === ""
            ? this.userInfo.password
            : this.form.password;

        var telefonoAggiornato =
          this.form.tel === "" ? this.userInfo.tel : this.form.tel;

        var notificheSmsAggiornato;
        if (this.form.selectedSms === "") {
          notificheSmsAggiornato = this.userInfo.notificheSms;
        } else if (this.form.selectedSms === "A") {
          notificheSmsAggiornato = true;
        } else {
          notificheSmsAggiornato = false;
        }

        var notificheEmailAggiornato;
        if (this.form.selectedEmail === "") {
          notificheEmailAggiornato = this.userInfo.notificheEmail;
        } else if (this.form.selectedEmail === "A") {
          notificheEmailAggiornato = true;
        } else {
          notificheEmailAggiornato = false;
        }

        fetch(
          "http://localhost:8080/api/utenteregistrato/aggiorna/" +
            localStorage.getItem("idLog"),
          {
            method: "post",
            headers: {
              Accept: "application/json",
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              username: userAggiornato,
              password: passwordAggiornata,
              email: emailAggiornata,
              notificheEmail: notificheEmailAggiornato,
              notificheSms: notificheSmsAggiornato,
              numeroTelefono: telefonoAggiornato
            })
          }
        )
          .then(response => response.json())
          .then(response => {
            console.log(response);
          });
      }

      this.valid = true;
    },
    onReset(evt) {
      evt.preventDefault();
      // Reset our form values
      this.form.email = "";
      this.form.username = "";
      this.form.password = "";
      this.form.checked = [];
      // Trick to reset/clear native browser form validation state
      this.show = false;
      this.$nextTick(() => {
        this.show = true;
      });
    }
  }
};
</script>