<template>
  <DefaultButtonVue
    :id="`generator-button-${service}`"
    :label="label"
    :icon="hasCalledService ? 'arrow-clockwise' : undefined"
    :outlined="hasCalledService"
    :data-selector="`${selectorPrefix}.add-${service}-button`"
  />
</template>

<script lang="ts" src="./GeneratorButton.component.ts"></script>
