<template>
    <h1>hola</h1>
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
  
  </script>
  
  <style scoped src="../theme/dashboard.css"></style>