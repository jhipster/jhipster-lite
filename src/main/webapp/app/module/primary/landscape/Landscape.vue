<template src="./Landscape.html"></template>

<script lang="ts" src="./Landscape.component.ts"></script>

<style lang="scss">
.jhipster-landscape {
  padding-top: 20px;
  font-family: $jhipster-lite-font-family;
  color: $jhipster-lite-primary-text-color;
  height: calc(100vh - $jhipster-header-height);
  background-color: $jhipster-lite-secondary-color;
  overflow: auto;
  position: relative;
}

.jhipster-landscape-connectors {
  position: absolute;
  top: -$jhipster-header-height;
  left: 0;
  z-index: 1;

  &--line {
    stroke: $jhipster-lite-line-color;
    stroke-width: 1px;
    stroke-dasharray: 2 2;
  }
}

.jhipster-landscape-levels {
  display: grid;
  z-index: 2;
  position: relative;
  place-items: center;

  &--level {
    width: 250px;
    grid-row: 1;
    margin-right: 70px;
    padding: 0;
  }
}

.jhipster-landscape-feature {
  border: 2px dotted $jhipster-lite-line-color;
  border-radius: $jhipster-lite-box-radius;
  padding: 10px 10px 0 10px;
  background: rgba($color: $jhipster-lite-secondary-color, $alpha: 0.7);

  &--title {
    text-align: center;
    margin-top: 7px;
    font-size: 1em;
    font-weight: bold;
  }

  &--modules {
    padding: 0;
  }
}

.jhipster-landscape-element {
  list-style-type: none;
  margin-bottom: 15px;
}
</style>
