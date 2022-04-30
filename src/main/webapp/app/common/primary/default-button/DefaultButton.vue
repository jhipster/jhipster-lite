<template>
  <button class="btn" :class="outlined ? 'btn-outline-primary' : 'btn-primary'" :disabled="disabled">
    <IconVue v-if="icon" class="me-1" :name="icon" :aria-hidden="true" />
    {{ label }}
  </button>
</template>

<script lang="ts" src="./DefaultButton.component.ts"></script>
