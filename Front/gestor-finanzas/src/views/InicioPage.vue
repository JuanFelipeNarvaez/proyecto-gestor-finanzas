<template>
    <ion-content :fullscreen="true">
        <div>
            <div id="description" class="contenedor">
                <img src="../assets/bg.png" alt="Imagen de carrito de compra" id="cart-image" />
                <ion-title>Bienvenido a gestor de finanzas</ion-title>
            </div>
            <div id="boton">
                <ion-fab vertical="bottom" horizontal="end" style="margin-bottom:30px;">
                    <ion-fab-button href="login">
                        <ion-icon :icon="IonIcons.chevronForwardSharp"></ion-icon>
                    </ion-fab-button>
                </ion-fab>
            </div>
        </div>
    </ion-content>
</template>

<script setup lang="ts">
import { IonContent, IonHeader, IonPage, IonTitle, IonToolbar, IonImg, IonFab, IonFabButton, IonIcon } from '@ionic/vue';
import * as IonIcons from 'ionicons/icons';
</script>

<style scoped src="../theme/container.css"></style>