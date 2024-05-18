<template>
    <div>    
      <ButtonComponent id="created" value="Agregar" expand="full" color="success" nameMethod="createRecord" @click="handleClickCreate" href="login" icon="IonIcons.lockClosedOutline"/>   
    </div>
  </template>
  
  <script setup lang="ts">
    import ButtonComponent from '@/components/ButtonComponent.vue';
    import { defineEmits } from 'vue';
    import * as IonIcons from 'ionicons/icons';
  
    // Definir los eventos que se emitirán en el componente
    const emit = defineEmits(['createRecord']);
  
    // Método para manejar el clic en el botón de crear
    const handleClickCreate = () => {
      emit('createRecord');
    };
  </script>
  
  <style scoped src="../theme/container.css"></style>