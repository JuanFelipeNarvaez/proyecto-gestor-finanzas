<template>
    <ion-header>
        <ion-toolbar>
            <ion-title>Example</ion-title>
        </ion-toolbar>
    </ion-header>
    <ion-content color="light">
        <ion-accordion-group>
            <ion-accordion value="first" style="background: red;">
                <ion-item slot="header" color="success">
                    <ion-label>First Accordion</ion-label>
                </ion-item>
                <div class="ion-padding" slot="content">Juan</div>
                <div class="ion-padding" slot="content">Narvaez</div>
            </ion-accordion>
            <ion-accordion value="second">
                <ion-item slot="header">
                    <ion-label>Second Accordion</ion-label>
                </ion-item>
                <div class="ion-padding" slot="content">Second Content</div>
            </ion-accordion>
            <ion-accordion value="third">
                <ion-item slot="header">
                    <ion-label>Third Accordion</ion-label>
                </ion-item>
                <div class="ion-padding" slot="content">Third Content</div>
            </ion-accordion>
        </ion-accordion-group>
        <div class="table-container">
            <ion-list>
                <ion-accordion-group>
                    <ion-accordion value="first" style="background: red;">
                        <ion-item slot="header" color="success">
                            <ion-label>First Accordion</ion-label>
                        </ion-item>
                        <div class="ion-padding" slot="content">Juan</div>
                        <div class="ion-padding" slot="content">Narvaez</div>
                    </ion-accordion>
                </ion-accordion-group>
                <!-- Iterar sobre items para mostrar los datos en tarjetas -->
                <ion-card class="card-width">

                    <ion-card-content>
                        <ion-item>
                            <ion-label>Valor: </ion-label>
                            <ion-label>1</ion-label>
                        </ion-item>
                        <ion-item>
                            <ion-label>Categoria: </ion-label>
                            <ion-label>2</ion-label>
                        </ion-item>
                        <ion-item>
                            <ion-label>Fecha: </ion-label>
                            <ion-label>3</ion-label>
                        </ion-item>
                        <ion-item>
                            <ion-label>Comentario: </ion-label>
                            <ion-label>4</ion-label>
                        </ion-item>
                    </ion-card-content>
                    <ion-item>
                        <!-- Botones de editar y eliminar -->
                        <ion-button color="warning">
                            <ion-icon :icon="IonIcons.createOutline"></ion-icon>
                        </ion-button>
                        <ion-button color="danger">
                            <ion-icon :icon="IonIcons.trash"></ion-icon>
                        </ion-button>
                    </ion-item>
                </ion-card>
            </ion-list>
        </div>
    </ion-content>
</template>

<script setup lang="ts">
import {
    IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonIcon, IonModal, IonList, IonLabel,
    IonText, IonAvatar, IonListHeader, IonItemGroup, IonItem, IonThumbnail, IonItemSliding, IonItemOption, IonItemOptions
} from '@ionic/vue';
import InputComponent from '@/components/InputComponent.vue';
import CrudButtonComponent from '@/components/CrudButtonComponent.vue';
import { showSuccessMessage, showErrorMessage } from '@/utils/alerts';
import { onMounted, ref } from 'vue';
import * as IonIcons from 'ionicons/icons';
import axios from 'axios';

</script>

<style scoped src="../theme/ingresos.css"></style>