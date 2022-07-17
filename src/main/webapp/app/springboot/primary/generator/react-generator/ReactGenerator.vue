<template>
  <div id="v-pills-react" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-react-tab">
    <GeneratorButtonVue :label="'Generate React'" :service="'react-styled'" :selector-prefix="selectorPrefix" @click.prevent="addReact" />
    <GeneratorButtonVue :label="'Add JWT'" :service="'react-jwt'" :selector-prefix="selectorPrefix" @click.prevent="addReactWithJWT" />
    <GeneratorButtonVue
      :label="'Cypress'"
      :service="'react-cypress'"
      :selector-prefix="selectorPrefix"
      @click.prevent="addCypressForReact"
    />
    <GeneratorButtonVue
      :label="'Playwright'"
      :service="'client-common-playwright'"
      :selector-prefix="selectorPrefix"
      @click.prevent="addPlaywrightForReact"
    />
  </div>
</template>

<script lang="ts" src="./ReactGenerator.component.ts"></script>
