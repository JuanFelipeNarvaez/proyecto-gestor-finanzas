<template>
    <ion-page @ionViewDidEnter="findAllRecords">
        <ion-header :translucent="true">
            <ion-toolbar>
                <ion-buttons slot="start">
                    <ion-back-button defaultHref="dashboardAdmin" style="margin-top: 5px"></ion-back-button>
                </ion-buttons>
                <ion-title>Opciones</ion-title>
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
                        </div>
                        <div>
                            <!-- Botones de CRUD dentro del modal -->
                            <CrudButtonComponent :showFind="showFind" :showCreated="showCreated"
                                :showUpdated="showUpdated" :showDeleted="showDeleted" @findAllRecords="findAllRecords"
                                @createRecord="createRecord" @updateRecord="updateRecord"
                                @deleteRecordPhysical="deleteRecordPhysical" />
                        </div>
                    </div>
                </ion-modal>


                <!-- Mostrar datos -->
                <div class="table-container">
                    <ion-list style="background: linear-gradient( to right, #f46b45, #eea849);">
                        <ion-accordion-group>
                            <ion-accordion v-for="(item, index) in items" :key="index" style="background: lightblue">
                                <ion-item slot="header" color="primary">
                                    <ion-icon :icon="IonIcons.swapVerticalOutline"></ion-icon>
                                    <ion-label style="margin-left: 20px">Opcion</ion-label>
                                </ion-item>
                                <div class="ion-padding" slot="content">
                                    <ion-item>
                                        <ion-label>Nombre: </ion-label>
                                        <ion-label>{{ item.nombre }}</ion-label>
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
    IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonIcon, IonModal, IonItem, IonLabel, IonSelect, IonSelectOption,
    IonButton, IonAccordion, IonAccordionGroup, IonList, IonButtons, IonBackButton
} from '@ionic/vue';
import InputComponent from '@/components/InputComponent.vue';
import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
import { showSuccessMessage, showErrorMessage } from '@/utils/alerts';
import { onMounted, ref } from 'vue';
import * as IonIcons from 'ionicons/icons';
import axios from 'axios';



// Rutas de la API
// const baseURL = 'http://localhost:9000/shopping-car/api/cliente';
//const baseURL = 'http://localhost:9000/prueba/api/opcion';
const baseURL = 'https://t5fkgr8c-9000.use2.devtunnels.ms/prueba/api/opcion';
const modalIsOpen = ref(false);
const items = ref<Array<ItemType>>([]);


const id = ref('');
const nombre = ref('');


const showFind = ref<boolean>(false);
const showCreated = ref<boolean>(true);
const showUpdated = ref<boolean>(false);
const showDeleted = ref<boolean>(false);

// Tipos
interface ItemType {
    id: string;
    nombre: string;
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
        nombre.value = data.nombre;

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
        nombre: nombre.value
    };
    console.log('Datos enviados:', data);

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
    nombre.value = '';
}


const openModalAdd = () => {
    modalIsOpen.value = true;
};

const closeModal = () => {
    modalIsOpen.value = false;
};
</script>

<style scoped src="../theme/container.css"></style>