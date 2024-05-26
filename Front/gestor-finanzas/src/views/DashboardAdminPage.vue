<template>
  <ion-page id="main-content">
    <ion-header :translucent="true">
      <ion-toolbar>
        <ion-buttons slot="start" class="back">
          <ion-menu-button></ion-menu-button>
        </ion-buttons>
        <ion-button slot="end" class="back" fill="clear" href="inicio">
          <ion-icon slot="icon-only" :icon="IonIcons.logInOutline" style="color: black;"></ion-icon>
        </ion-button>
      </ion-toolbar>
    </ion-header>
    <ion-content>
      <div class="main_content_div">
        <div class="user_div">
          <div class="first_div">
            <ion-label class="welcome">Welcome</ion-label>
            <ion-label class="username">{{ nombre }}</ion-label>
            <ion-label class="username">{{ rol }}</ion-label>
          </div>
          <div class="user_back">
            <img src="../assets/perfil.png" />
          </div>
        </div>

        <div class="content_div">
          <ion-label class="hello_lbl">Hello, Please Choose Your Subject</ion-label>
          <ion-grid fixed>
            <ion-row>
              <ion-col size="6" click="Finanza">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" href="finanza" class="button">
                    <ion-icon :icon="IonIcons.barChartOutline" class="iconn"></ion-icon></ion-button>
                  <ion-label>Finanzas</ion-label>
                </div>
              </ion-col>
              <ion-col size="6" click="Opcion">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" class="button" href="opcion">
                    <ion-icon :icon="IonIcons.swapVerticalOutline" class="iconn"></ion-icon>
                  </ion-button>
                  <ion-label>Opciones</ion-label>
                </div>
              </ion-col>
            </ion-row>
            <ion-row>
              <ion-col size="6" click="usuario">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" href="persona">
                    <ion-icon :icon="IonIcons.personCircleOutline" class="iconn"></ion-icon></ion-button>
                  <ion-label>Usuarios</ion-label>
                </div>
              </ion-col>
              <ion-col size="6">
                <div class="col_div" click="rol">
                  <ion-button expand="full" fill="clear" class="button" href="rol">
                    <ion-icon :icon="IonIcons.peopleCircleOutline" class="iconn"></ion-icon>
                  </ion-button>
                  <ion-label>Roles</ion-label>
                </div>
              </ion-col>
            </ion-row>
            <ion-row>
              <ion-col size="6" click="categoria">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" class="button" href="/categoria">
                    <ion-icon :icon="IonIcons.pricetagsOutline" class="iconn"></ion-icon></ion-button>
                  <ion-label>Categorias</ion-label>
                </div>
              </ion-col>
              <ion-col size="6">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" class="button">
                    <ion-icon :icon="IonIcons.alert" class="iconn"></ion-icon>
                  </ion-button>
                  <ion-label></ion-label>
                </div>
              </ion-col>
            </ion-row>
          </ion-grid>
        </div>

      </div>
    </ion-content>
  </ion-page>
</template>

<script setup lang="ts">
import { Style } from '@capacitor/status-bar';
import {
  IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonSegment, IonSegmentButton, IonLabel, IonItem, IonButtons, IonGrid,
  IonRow, IonCol, IonButton, IonIcon, IonBackButton, IonMenu, IonMenuButton
} from '@ionic/vue';
import * as IonIcons from 'ionicons/icons';
import ButtonComponent from '@/components/ButtonComponent.vue';

const gmail = localStorage.getItem('gmail');
const id = localStorage.getItem('id');
const nombre = localStorage.getItem('nombre');
const rol = localStorage.getItem('rol');

</script>

<style scoped src="../theme/dashboard.css"></style>