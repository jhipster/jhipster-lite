<template src="./LandscapeModule.html"></template>

<script lang="ts" src="./LandscapeModule.component.ts"></script>

<style lang="scss">
.jhipster-landscape-module {
  border: 1px solid $jhipster-lite-line-color;
  border-radius: $jhipster-lite-box-radius;
  background: rgba($color: $jhipster-lite-secondary-color, $alpha: 0.7);

  &--slug {
    text-align: center;

    &.-extended {
      padding: 5px 10px;
      font-weight: bold;
    }

    &.-compacted {
      padding: 2px 3px;
    }
  }

  &--operation {
    border-top: 1px dotted $jhipster-lite-line-color;
    padding: 5px 10px;
    font-size: 0.8em;

    &.-compacted {
      display: none;
    }
  }

  &.-compacted {
    margin-bottom: 5px;
  }

  &.-extended {
    margin-bottom: 15px;
  }
}
</style>
