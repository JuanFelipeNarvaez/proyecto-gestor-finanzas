<template>
    <ion-page @ionViewDidEnter="findAllRecords">
      <ion-header :translucent="true">
        <ion-toolbar>
          <ion-buttons slot="start">
            <ion-back-button defaultHref="dashboardAdmin" style="margin-top: 5px"></ion-back-button>
          </ion-buttons>
          <ion-title>Finanzas</ion-title>
        </ion-toolbar>
      </ion-header>
      <ion-content fullscreen>
        <div id="container">
          <div class="nuevo-registro">
            <ion-button @click="openModalAdd"><ion-icon :icon="IonIcons.addSharp" color="sucess"></ion-icon></ion-button>
          </div>
  
          <!-- Modal para agregar o editar registros -->
          <ion-modal :is-open="modalIsOpen" @didDismiss="modalIsOpen = false" :css-class="['my-custom-modal']">
            <div class="modal-content">
              <div id="data-form">
                <InputComponent v-model="valor" id="valor" name="valor" label="valor: " type="number" />
                <ion-item>
                  <ion-label>Categoria: </ion-label>
                  <ion-select v-model="categoria" placeholder="Seleccione una categoria">
                    <ion-select-option v-for="categoriaItem in categorias" :key="categoriaItem.id"
                      :value="categoriaItem.id">
                      {{ categoriaItem.nombre }}
                    </ion-select-option>
                  </ion-select>
                </ion-item>
                <InputComponent v-model="fecha" id="fecha" name="fecha" label="Fecha: " type="date" />
                <InputComponent v-model="comentario" id="comentario" name="comentario" label="Comentario: " />
                <ion-item>
                  <ion-label>Opcion: </ion-label>
                  <ion-select v-model="opcion" placeholder="Seleccione una opcion">
                    <ion-select-option v-for="opcionItem in opciones" :key="opcionItem.id"
                      :value="opcionItem.id">
                      {{ opcionItem.nombre }}
                    </ion-select-option>
                  </ion-select>
                </ion-item>
                <ion-item>
                  <ion-label>Persona: </ion-label>
                  <ion-select v-model="persona" placeholder="Seleccione una categoria">
                    <ion-select-option v-for="personaItem in personas" :key="personaItem.id"
                      :value="personaItem.id">
                      {{ personaItem.nombre }}
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
            <ion-list style="background: linear-gradient( to right, #f46b45, #eea849);">
              <ion-accordion-group>
                <ion-accordion v-for="(item, index) in items" :key="index" style="background: yellow;">
                  <ion-item slot="header" color="warning">
                    <ion-icon :icon="IonIcons.barChartOutline"></ion-icon>
                    <ion-label style="margin-left: 20px">Finanza</ion-label>
                  </ion-item>
                  <div class="ion-padding" slot="content">
                    <ion-item style="background: rosybrown;">
                      <ion-label>Valor: </ion-label>
                      <ion-label>{{ item.valor }}</ion-label>
                    </ion-item>
                  </div>
                  <div class="ion-padding" slot="content">
                    <ion-item>
                      <ion-label>Categoria: </ion-label>
                      <ion-label>{{ item.categoria.nombre }}</ion-label>
                    </ion-item>
                  </div>
                  <div class="ion-padding" slot="content">
                    <ion-item>
                      <ion-label>Fecha: </ion-label>
                      <ion-label>{{ item.fecha }}</ion-label>
                    </ion-item>
                  </div>
                  <div class="ion-padding" slot="content">
                    <ion-item>
                      <ion-label>Comentario: </ion-label>
                      <ion-label>{{ item.comentario }}</ion-label>
                    </ion-item>
                  </div>
                  <div class="ion-padding" slot="content">
                    <ion-item>
                      <ion-label>Persona: </ion-label>
                      <ion-label>{{ item.persona.nombre }}</ion-label>
                    </ion-item>
                  </div>
                  <div class="ion-padding" slot="content">
                    <ion-item>
                      <ion-label>Opcion: </ion-label>
                      <ion-label>{{ item.opcion.nombre }}</ion-label>
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
  import {
    IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonIcon, IonModal,
    IonList, IonAccordion, IonAccordionGroup, IonItem, IonButton, IonLabel,
    IonButtons, IonBackButton, IonSelect, IonSelectOption
  } from '@ionic/vue';
  import InputComponent from '@/components/InputComponent.vue';
  import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
  import { showSuccessMessage, showErrorMessage } from '@/utils/alerts';
  import { onMounted, ref } from 'vue';
  import * as IonIcons from 'ionicons/icons';
  import axios from 'axios';
  
  
  
  // Rutas de la API
  //const baseURL = 'http://localhost:9000/prueba/api/finanza';
  const baseURL = 'https://t5fkgr8c-9000.use2.devtunnels.ms/prueba/api/finanza'
  const modalIsOpen = ref(false);
  const items = ref<Array<ItemType>>([]);
  const categorias = ref<Array<CategoriaType>>([]);
  const personas = ref<Array<PersonaType>>([]);
  const opciones = ref<Array<OpcionType>>([]);
  
  const id = ref('');
  const valor = ref('');
  const categoria = ref('');
  const fecha = ref('');
  const comentario = ref('');
  const opcion = ref('');
  const persona = ref('');
  
  const showFind = ref<boolean>(false);
  const showCreated = ref<boolean>(true);
  const showUpdated = ref<boolean>(false);
  const showDeleted = ref<boolean>(false);
  
  const idref = localStorage.getItem('id');
  localStorage.setItem('opcion', 'gasto');
  const opcion1 = localStorage.getItem('opcion');
  
  
  // Tipos
  interface ItemType {
    id: string;
    valor: string;
    categoria: string;
    fecha: Date;
    comentario: string;
    opcion: string;
    persona: string;
  }
  
  interface CategoriaType {
    id: string;
    nombre: string;
  }

  interface PersonaType {
    id: string;
    nombre: string;
  }
  interface OpcionType {
    id: string;
    nombre: string;
  }
  
  onMounted(() => {
    findAllRecords();
    findAllCategorias();
    findAllPersonas();
    findAllOpciones();
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
  async function findAllCategorias() {
    try {
      //const response = await axios.get('http://localhost:9000/prueba/api/categoria');
      const response = await axios.get('https://t5fkgr8c-9000.use2.devtunnels.ms/prueba/api/categoria')
      //console.log('Categorias response:', response.data)
      categorias.value = response.data;
    } catch (error) {
      console.error('Error al obtener todos los roles:', error);
      throw error;
    }
  }

  async function findAllPersonas() {
    try {
      //const response = await axios.get('http://localhost:9000/prueba/api/persona');
      const response = await axios.get('https://t5fkgr8c-9000.use2.devtunnels.ms/prueba/api/persona');
      //console.log('Personas response:', response.data)
      personas.value = response.data;
    } catch (error) {
      console.error('Error al obtener todos los roles:', error);
      throw error;
    }
  }

  async function findAllOpciones() {
    try {
      //const response = await axios.get('http://localhost:9000/prueba/api/opcion');
      const response = await axios.get('https://t5fkgr8c-9000.use2.devtunnels.ms/prueba/api/opcion');
      //console.log('Opciones response:', response.data)
      opciones.value = response.data;
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
      valor.value = data.valor;
      categoria.value = data.categoria.id;
      fecha.value = data.fecha;
      comentario.value = data.comentario;
      opcion.value = data.opcion.id;
      persona.value = data.persona.id;
  
      // Controlar la visibilidad de los botones
      showCreated.value = false;
      showUpdated.value = true;
  
  
    } catch (error) {
      console.error('Error al encontrar el registro por ID:', error);
      throw error;
    }
  }

  async function createRecord() {
    const personaId = localStorage.getItem('id');
    const data = {
      valor: valor.value,
      categoria: {
        id: categoria.value
      },
      fecha: fecha.value,
      comentario: comentario.value,
      opcion: {
        id: opcion.value
      },
      persona: {
        id: persona.value,
      }
      
    };
    //console.log('Datos enviados:', data);
  
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
      valor: valor.value,
      categoria: {
        id: categoria.value
      },
      fecha: fecha.value,
      comentario: comentario.value,
      opcion: {
        id: opcion.value
      },
      persona: {
        id: persona.value
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
  
  async function clearData() {
    valor.value = '';
    categoria.value = '';
    fecha.value = '';
    comentario.value = '';
    opcion.value = '';
    persona.value = '';
  }
  
  
  const openModalAdd = () => {
    modalIsOpen.value = true;
  };
  
  const closeModal = () => {
    modalIsOpen.value = false;
  };
  </script>
  
  <style scoped src="../theme/container.css"></style>