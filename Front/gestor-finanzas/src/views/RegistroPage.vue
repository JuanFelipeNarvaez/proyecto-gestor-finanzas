<template>
    <ion-page>
        <ion-header>
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button defaultHref="login" style="margin-top: 5px"></ion-back-button>
                </ion-buttons>
                <ion-title>Register</ion-title>
            </ion-toolbar>
        </ion-header>
        <ion-content>
            <div class="header">
                <ion-title>Registro</ion-title>
                <p>Sign in to access your account</p>
            </div>
            <form class="formContainer">
                <div>
                    <ion-item>
                        <ion-icon :icon="IonIcons.personOutline"></ion-icon>
                        <InputComponent v-model="nombre" id="nombre" name="nombre" placeholder="nombre" class="input" />
                    </ion-item>
                    <ion-item>
                        <ion-icon :icon="IonIcons.personOutline"></ion-icon>
                        <InputComponent v-model="apellido" id="apellido" name="apellido" placeholder="apellido" class="input" />
                    </ion-item>
                    <ion-item>
                        <ion-icon :icon="IonIcons.todayOutline"></ion-icon>
                        <InputComponent v-model="edad" id="edad" name="edad" placeholder="edad" class="input" />
                    </ion-item>
                    <ion-item>
                        <ion-icon :icon="IonIcons.logoGoogle"></ion-icon>
                        <InputComponent v-model="gmail" id="gmail" name="gmail" placeholder="gmail" class="input" />
                    </ion-item>
                    <ion-item>
                        <ion-icon :icon="IonIcons.lockClosedOutline"></ion-icon>
                        <InputComponent v-model="password" id="password" name="password" placeholder="password" class="input" />
                    </ion-item>
                    <CrudButtonComponent @create-record="createRecord" href="login" class="buttonCrear"/>
                </div>
            </form>

        </ion-content>
    </ion-page>
</template>

<script setup lang="ts">
import {
    IonHeader, IonPage, IonTitle, IonToolbar, IonContent, IonItem, IonInput, IonIcon, IonLabel, IonFab,
    IonFabButton, IonButton, IonButtons, IonBackButton, IonMenuButton
} from '@ionic/vue';
import InputComponent from '@/components/InputComponent.vue';
import CrearComponent from '@/components/CrearComponent.vue';
import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
import ButtonComponent from '@/components/ButtonComponent.vue';
import * as IonIcons from 'ionicons/icons';
import axios from 'axios';
import Swal from 'sweetalert2';
import { ref } from 'vue';




const baseURL = 'http://localhost:9000/prueba/api/persona';
const nombre= ref('');
const apellido = ref('');
const edad = ref('');
const gmail = ref('');
const password = ref('')

interface ItemType {
  id: string;
  nombre: string;
  apellido: string;
  edad: string;
  gmail: string;
  password: string;
  rol: string;
}



async function findAllRecords() {
    try {
        const response = await axios.get(baseURL);
        return response.data;
    } catch (error) {
        console.error('Error al obtener todos los registros:', error);
        throw error;
    }
}

async function createRecord() {
    const data = {
        nombre: nombre.value,
        apellido: apellido.value,
        edad: edad.value,
        gmail: gmail.value,
        password: password.value,
        rol: {
            id: "664c42f942560c6047422235"
        }
    };

    try {
        const response = await axios.post(baseURL, data);
        console.log('Registro creado exitosamente:', response.data);
        window.location.href = '/login';
    } catch (error) {
        console.error('Error al crear el registro:', error);
    }
}

</script>

<style scoped src="../theme/login.css"></style>