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
              <InputComponent v-model="nombre" id="nombre" name="nombre" label="Nombre: " />
              <InputComponent v-model="apellido" id="apellido" name="apellido" label="Apellido: " />
              <InputComponent v-model="edad" id="edad" name="edad" label="Edad: " />
              <InputComponent v-model="gmail" id="gmail" name="gmail" label="Gmail: " />
              <InputComponent v-model="password" type="password" id="password" name="password" label="Password: " />
              <ion-item>
                <ion-label>Rol: </ion-label>
                <ion-select v-model="rol" placeholder="Seleccione un rol">
                  <ion-select-option v-for="rolItem in roles" :key="rolItem.id" :value="rolItem.id">
                    {{ rolItem.nombre }}
                  </ion-select-option>
                </ion-select>
              </ion-item>
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
            <ion-accordion-group>
              <ion-accordion v-for="(item, index) in items" :key="index">
                <ion-item slot="header" color="success">
                  <ion-icon :icon="IonIcons.trendingUpOutline"></ion-icon>
                  <ion-label style="margin-left: 20px">Personas</ion-label>
                </ion-item>
                <div class="ion-padding" slot="content">
                  <ion-item style="background: rosybrown;">
                    <ion-label>Nombre: </ion-label>
                    <ion-label>{{ item.nombre }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-padding" slot="content">
                  <ion-item>
                    <ion-label>Apellido: </ion-label>
                    <ion-label>{{ item.apellido }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-padding" slot="content">
                  <ion-item>
                    <ion-label>Edad: </ion-label>
                    <ion-label>{{ item.edad }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-padding" slot="content">
                  <ion-item>
                    <ion-label>Gmail: </ion-label>
                    <ion-label>{{ item.gmail }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-padding" slot="content">
                  <ion-item>
                    <ion-label>Password: </ion-label>
                    <ion-label>{{ item.password }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-padding" slot="content">
                  <ion-item>
                    <ion-label>Rol: </ion-label>
                    <ion-label>{{ item.rol.nombre }}</ion-label>
                  </ion-item>
                </div>
                <div class="ion-pading" slot="content">
                  <ion-item>
                    <!-- Botones de editar y eliminar -->
                    <ion-button @click="findById(item.id)" color="warning">
                      <ion-icon :icon="IonIcons.createOutline"></ion-icon>
                    </ion-button>
                    <ion-button @click="deleteRecordPhysical(item.id)" color="danger">
                      <ion-icon :icon="IonIcons.trash"></ion-icon>
                    </ion-button>
                  </ion-item>
                </div>
              </ion-accordion>
            </ion-accordion-group>
          </ion-list>
        </div>
      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonIcon, IonModal, IonItem, IonLabel, IonSelect, IonSelectOption,
  IonButton, IonAccordion, IonAccordionGroup, IonList
 } from '@ionic/vue';
import InputComponent from '@/components/InputComponent.vue';
import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
import { showSuccessMessage, showErrorMessage } from '@/utils/alerts';
import { onMounted, ref } from 'vue';
import * as IonIcons from 'ionicons/icons';
import axios from 'axios';



// Rutas de la API
// const baseURL = 'http://localhost:9000/shopping-car/api/cliente';
const baseURL = 'http://localhost:9000/prueba/api/persona';
//const baseURL = 'https://zctlpc09-9000.use.devtunnels.ms/shopping-car/api/cliente';
const modalIsOpen = ref(false);
const items = ref<Array<ItemType>>([]);
const roles = ref<Array<RolType>>([]);


const id = ref('');
const nombre = ref('');
const apellido = ref('');
const edad = ref('');
const gmail = ref('');
const password = ref('');
const rol = ref('');


const showFind = ref<boolean>(false);
const showCreated = ref<boolean>(true);
const showUpdated = ref<boolean>(false);
const showDeleted = ref<boolean>(false);

// Tipos
interface ItemType {
  id: string;
  nombre: string;
  apellido: string;
  edad: string;
  gmail: string;
  password: string;
  rol: string;
}

interface RolType {
  id: string;
  nombre: string;
}


onMounted(() => {
  findAllRecords();
  findAllRoles();
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

async function findAllRoles() {
  try {
    const response = await axios.get('http://localhost:9000/prueba/api/rol');
    console.log('Roles response:', response.data)
    roles.value = response.data;
  } catch (error) {
    console.error('Error al obtener todos los roles:', error);
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
    nombre.value = data.nombre;
    apellido.value = data.apellido;
    edad.value = data.edad;
    gmail.value = data.gmail;
    password.value = data.password;
    rol.value = data.rol.id;

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
    nombre: nombre.value,
    apellido: apellido.value,
    edad: edad.value,
    gmail: gmail.value,
    password: password.value,
    rol: {
      id: rol.value,
    }
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
    nombre: nombre.value,
    apellido: apellido.value,
    edad: edad.value,
    gmail: gmail.value,
    password: password.value,
    rol: {
      id: rol.value,
    }
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
  nombre.value = '';
  apellido.value = '';
  edad.value = '';
  gmail.value = '';
  password.value = '';
  rol.value = '';
}


const openModalAdd = () => {
  modalIsOpen.value = true;
};

const closeModal = () => {
  modalIsOpen.value = false;
};
</script>

<style scoped src="../theme/container.css"></style>