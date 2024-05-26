<template>
  <ion-page>
    <ion-header>
            <ion-toolbar>
                <ion-title>Register</ion-title>
            </ion-toolbar>
        </ion-header>
    <ion-content>
      <div class="header">
        <ion-title>Welcome Back</ion-title>
        <p>Sign in to access your account</p>
      </div>

      <form class="formContainer">
        <div>
          <ion-item>
            <ion-icon :icon="IonIcons.personOutline"></ion-icon>
            <InputComponent v-model="gmail" id="gmail" name="gmail" placeholder="gmail" class="input" />
          </ion-item>
          <ion-item>
            <ion-icon :icon="IonIcons.lockClosedOutline"></ion-icon>
            <InputComponent v-model="password" type="password" id="password" name="password" placeholder="password"
              class="input" />
          </ion-item>
          <div>
            <a href="" style="color: darkblue;">Forgot password?</a>
          </div>
          <div>
            <p> New member?<a href="registro" style="color: darkblue">Signup now</a></p>
          </div>
          <ion-button shape='round' class='button' expand='full' @click="login">
            Login
          </ion-button>
        </div>
      </form>
      <ion-alert
        v-if="error"
        :is-open="true"
        header="Login Failed"
        :message="error"
        buttons="OK"
        @didDismiss="error = null"
      />
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import { useRouter } from 'vue-router'; // Importar el enrutador
import {
  IonHeader, IonPage, IonTitle, IonToolbar, IonContent, IonItem, IonInput, IonIcon, IonLabel, IonFab,
  IonFabButton, IonButton, IonAlert
} from '@ionic/vue';
import InputComponent from '@/components/InputComponent.vue';
import * as IonIcons from 'ionicons/icons';
import axios from 'axios';


const baseURL = 'http://localhost:9000/prueba/api/persona';
const selectedOption =ref('login');
const id=ref('');
const gmail = ref('');
const password = ref('');
const error = ref<string | null>(null);

const router = useRouter(); // Obtener el objeto del enrutador

const login = async () => {
  if (!gmail.value || !password.value) {
    error.value = 'Please enter both email and password.';
    return;
  }

  try {
    const response = await axios.post(`${baseURL}/login`, {
      gmail: gmail.value,
      password: password.value,
    });

    if (response.data) {
      const userId = response.data.id;
      const nombre = response.data.nombre;
      const rol = response.data.rol.nombre;
      localStorage.setItem('nombre', nombre);
      localStorage.setItem('id', userId);
      localStorage.setItem('gmail', gmail.value);
      localStorage.setItem('rol', rol);
      if(rol == 'Administrador'){
        router.push('/dashboardAdmin');
      }else{
        router.push('/dashboard')
      }
    } else {
      error.value = 'Incorrect credentials. Please try again.';
    }
  } catch (err) {
    console.error('Error:', err);
    error.value = 'An error occurred during login. Please try again later.';
  }
};

</script>

<style scoped src="../theme/login.css"></style>