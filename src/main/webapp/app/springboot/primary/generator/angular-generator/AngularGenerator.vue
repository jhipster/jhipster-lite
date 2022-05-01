<template>
  <div id="v-pills-angular" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-settings-tab">
    <GeneratorButtonVue :label="'Generate Angular'" :service="'angular'" :selector-prefix="selectorPrefix" @click.prevent="addAngular" />
  </div>
</template>

<script lang="ts" src="./AngularGenerator.component.ts"></script>
