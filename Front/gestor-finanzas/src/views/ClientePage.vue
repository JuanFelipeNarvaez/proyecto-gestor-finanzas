<template>
    <ion-page @ionViewDidEnter="findAllRecords">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-title>Carrito de compra</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-content fullscreen>
        <div id="container">
          <div class="nuevo-registro">
            <ion-button @click="openModalAdd"><ion-icon :icon="IonIcons.addSharp"></ion-icon></ion-button>
          </div>
  
          <!-- Modal para agregar o editar registros -->
          <ion-modal :is-open="modalIsOpen" @didDismiss="modalIsOpen = false" :css-class="['my-custom-modal']">
            <div class="modal-content">
              <div id="data-form">
                <InputComponent v-model="name" id="name" name="name"
                  label="Name: " />
                <InputComponent v-model="lastname" id="lastname" name="lastname" label="Lastname: " />
                <InputComponent v-model="username" id="username" name="username" label="Username: " />
                <InputComponent v-model="password" type="password" id="password" name="password" label="Password: " />
              </div>
              <div>
                <!-- Botones de CRUD dentro del modal -->
                <CrudButtonComponent :showFind="showFind" :showCreated="showCreated" :showUpdated="showUpdated"
                  :showDeleted="showDeleted" @findAllRecords="findAllRecords" @createRecord="createRecord"
                  @updateRecord="updateRecord" @deleteRecordPhysical="deleteRecordPhysical" />
              </div>
            </div>
          </ion-modal>
  
  
          <!-- Mostrar datos -->
          <div class="table-container">
            <ion-list>
              <!-- Iterar sobre items para mostrar los datos en tarjetas -->
              <ion-card v-for="(item, index) in items" :key="index" class="card-width">
                <ion-card-content>
                  <ion-item>
                    <ion-label>Name: </ion-label>
                    <ion-label>{{ item.name }}</ion-label>
                  </ion-item>
                  <ion-item>
                    <ion-label>Lastname: </ion-label>
                    <ion-label>{{ item.lastname }}</ion-label>
                  </ion-item>
                  <ion-item>
                    <ion-label>Username: </ion-label>
                    <ion-label>{{ item.username }}</ion-label>
                  </ion-item>
                  <ion-item>
                    <ion-label>Password: </ion-label>
                    <ion-label>{{ item.password }}</ion-label>
                  </ion-item>
                </ion-card-content>
                <ion-item>
                  <!-- Botones de editar y eliminar -->
                  <ion-button @click="findById(item.id)">
                    <ion-icon :icon="IonIcons.createOutline"></ion-icon>
                  </ion-button>
                  <ion-button @click="deleteRecordPhysical(item.id)">
                    <ion-icon :icon="IonIcons.trash"></ion-icon>
                  </ion-button>
                </ion-item>
              </ion-card>
            </ion-list>
          </div>
        </div>
      </ion-content>
    </ion-page>
  </template>
  
  <script setup lang="ts">
  import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonIcon, IonModal } from '@ionic/vue';
  import InputComponent from '@/components/InputComponent.vue';
  import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
  import { onMounted, ref } from 'vue';
  import * as IonIcons from 'ionicons/icons';
  import axios from 'axios';

  
  
  // Rutas de la API
  // const baseURL = 'http://localhost:9000/shopping-car/api/cliente';
  const baseURL = 'http://localhost:9000/gestor-finanzas/usuario';
  //const baseURL = 'https://zctlpc09-9000.use.devtunnels.ms/shopping-car/api/cliente';
  const modalIsOpen = ref(false);
  const items = ref<Array<ItemType>>([]);
  const id = ref('');
  const name = ref('');
  const lastname = ref('');
  const username = ref('');
  const password = ref('');
  
  const showFind = ref<boolean>(false);
  const showCreated = ref<boolean>(true);
  const showUpdated = ref<boolean>(false);
  const showDeleted = ref<boolean>(false);
  
  
  // Tipos
  interface ItemType {
    id: string;
    name: string;
    lastname: string;
    username: string;
    password: string;
  }
  
  onMounted(() => {
    findAllRecords();
  });
  
  // Métodos
  async function findAllRecords() {
    try {
      const response = await axios.get(baseURL);
      items.value = response.data;
    } catch (error) {
      console.error('Error al obtener todos los registros:', error);
      throw error;
    }
  }
  
  //Cargar los datos para edición
  async function findById(recordId: string) {
    try {
      await openModalAdd();
      const response = await axios.get(`${baseURL}/${recordId}`);
      const data = response.data;
  
      id.value = data.id;
      name.value = data.name;
      lastname.value = data.lastname;
      username.value = data.username;
      password.value = data.password;
  
      // Controlar la visibilidad de los botones
      showCreated.value = false;
      showUpdated.value = true;
  
  
    } catch (error) {
      console.error('Error al encontrar el registro por ID:', error);
      throw error;
    }
  }
  
  async function createRecord() {
    const data = {
      name: name.value,
      lastname: lastname.value,
      username: username.value,
      password: password.value
    };
  
    try {
      const response = await axios.post(baseURL, data);
      console.log('Registro creado exitosamente:', response.data);
      await findAllRecords();
      await clearData();
      await showSuccessMessage();
      await closeModal();
    } catch (error) {
      console.error('Error al crear el registro:', error);
      await showErrorMessage();
    }
  }
  
  async function updateRecord() {
    const data = {
      id: id.value,
      name: name.value,
      lastname: lastname.value,
      username: username.value,
      password: password.value
    };
  
    try {
      const response = await axios.put(`${baseURL}/${data.id}`, data);
      console.log('Registro actualizado exitosamente:', response.data, id);
  
      await findAllRecords();
      await clearData();
  
      showCreated.value = true;
      showUpdated.value = false;
  
      await closeModal();
  
      return response.data;
    } catch (error) {
      console.error('Error al actualizar el registro:', error);
      throw error;
    }
  }
  
  
  async function deleteRecordPhysical(id: String) {
    try {
      const response = await axios.delete(`${baseURL}/${id}`);
      await findAllRecords();
      return response.data;
    } catch (error) {
      console.error('Error al eliminar el registro físico:', error);
      throw error;
    }
  }
  
  async function deleteRecordLogical(id) {
    try {
      const response = await axios.put(`${baseURL}/delete-logical/${id}`);
      return response.data;
    } catch (error) {
      console.error('Error al realizar el eliminado lógico:', error);
      throw error;
    }
  }
  
  async function clearData() {
    name.value = '';
    lastname.value = '';
    username.value = '';
    password.value = '';
  }
  
  
  const openModalAdd = () => {
    modalIsOpen.value = true;
  };
  
  const closeModal = () => {
    modalIsOpen.value = false;
  };
  </script>
  
  <style scoped src="../theme/container.css"></style>