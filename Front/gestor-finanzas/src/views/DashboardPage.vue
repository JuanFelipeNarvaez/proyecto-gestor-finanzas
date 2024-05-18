<template>
  <ion-menu content-id="main-content">
    <ion-header>
      <ion-toolbar>
        <ion-title>Menu Content</ion-title>
      </ion-toolbar>
    </ion-header>
    <ion-content class="ion-padding">
      <ion-button style="height: 10px" fill="clear" expand="full">
        <ion-icon slot="icon-only" :icon="IonIcons.logInOutline" style="color: black;"></ion-icon>
        <a>hola</a>
      </ion-button>
      <ion-button fill="clear" expand="full">
        <ion-icon slot="icon-only" :icon="IonIcons.logInOutline" style="color: black;"></ion-icon>
        <ion-label>button de no c</ion-label>
      </ion-button>
    </ion-content>
  </ion-menu>
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
          </div>
          <div class="user_back">
            <img src="../assets/perfil.png" />
          </div>
        </div>

        <div class="content_div">
          <ion-label class="hello_lbl">Hello, Please Choose Your Subject</ion-label>
          <ion-grid fixed>
            <ion-row>
              <ion-col size="6" click="producto">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" href="ingreso" class="button">
                    <ion-icon :icon="IonIcons.trendingUpOutline" class="iconn"></ion-icon></ion-button>
                  <ion-label>Ingresos</ion-label>
                </div>
              </ion-col>
              <ion-col size="6">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" class="button" href="gasto">
                    <ion-icon :icon="IonIcons.trendingDownOutline" class="iconn"></ion-icon>
                  </ion-button>
                  <ion-label>Gastos</ion-label>
                </div>
              </ion-col>
            </ion-row>
            <ion-row>
              <ion-col size="6" click="producto">
                <div class="col_div">
                  <ion-button expand="full" fill="clear">
                    <ion-icon :icon="IonIcons.personCircleOutline" class="iconn"></ion-icon></ion-button>
                  <ion-label>Perfil</ion-label>
                </div>
              </ion-col>
              <ion-col size="6">
                <div class="col_div">
                  <ion-button expand="full" fill="clear" class="button" href="https://github.com/code-corhuila/programacion-movil-2024-a">
                    <ion-icon :icon="IonIcons.logoGithub" class="iconn"></ion-icon>
                  </ion-button>
                  <ion-label>GitHub</ion-label>
                </div>
              </ion-col>
            </ion-row>
            <ion-row>
              <ion-col size="6" click="producto">
                <div class="col_div">
                  <ion-button expand="full" fill="clear">
                    <ion-icon :icon="IonIcons.airplane" class="iconn"></ion-icon></ion-button>
                  <ion-label>hola</ion-label>
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
const nombre = localStorage.getItem('nombre')

</script>

<style scoped src="../theme/dashboard.css"></style>