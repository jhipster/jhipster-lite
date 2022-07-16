<template>
  <div id="v-pills-svelte" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-svelte-tab">
    <DefaultButtonVue
      id="svelte"
      :label="'Generate Svelte'"
      :data-selector="selectorPrefix + '.add-svelte-button'"
      @click.prevent="addSvelte"
    />
  </div>
</template>

<script lang="ts" src="./SvelteGenerator.component.ts"></script>
