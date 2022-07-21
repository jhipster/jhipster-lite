<template src="./Modules.html"></template>

<script lang="ts" src="./Modules.component.ts"></script>

<style scoped>
.jhipster-modules {
  color: #29293b;
  display: flex;
}

.jhipster-modules-form {
  border-right: 1px solid #29293b;
  padding: 0 15px;
  width: 350px;
  flex-direction: column;
  justify-content: space-between;
  height: 100vh;
  display: flex;
}

.jhipster-modules-form--field-line {
  align-self: flex-start;
  margin-bottom: 10px;
}

.jhipster-modules-form--field {
  width: 100%;
  border: 1px solid #29293b;
  background-color: #fff;
  border-radius: 10px;
  padding: 2px 7px;
}

.jhipster-modules-form--field-description {
  font-style: italic;
}
.jhipster-modules-form--field-key {
  font-weight: bold;
}
.jhipster-modules-form--field-optional-marker {
  text-align: right;
  font-style: italic;
}
.jhipster-modules-form--download-project {
  align-self: flex-end;
  margin-bottom: 15px;
  background-color: #fff;
  border: 1px solid #29293b;
  border-radius: 20px;
  padding: 10px;
}
.jhipster-modules-list {
  flex-grow: 1;
}

.jhipster-modules-list--search {
  margin: 15px 0;
  padding: 0 15px;
  height: 70px;
}

.jhipster-modules-list--search-field {
  width: 100%;
  font-size: 2em;
  border: 1px solid #29293b;
  border-radius: 20px;
  padding: 0 15px;
}

.jhipster-modules-list--categories {
  height: calc(100vh - 100px);
  overflow-y: auto;
  flex-grow: 1;
  padding: 0 15px;
}

.jhipster-module {
  border: 1px solid #29293b;
  border-radius: 10px;
  margin-bottom: 10px;
  display: flex;
}

.jhipster-module--content {
  cursor: pointer;
  flex-grow: 1;
  padding: 10px;
}

.jhipster-module--tags {
  display: flex;
  margin: 5px 0;
}

.jhipster-module--tag {
  color: #fff;
  background-color: #29293b;
  font-style: italic;
  margin-right: 7px;
  padding: 0 0.5em;
  border-radius: 1em;
}

.jhipster-module--slug {
  font-weight: bold;
}

.jhipster-module--apply-button {
  background-color: transparent;
  border: 0;
  border-left: 1px solid #29293b;
  padding: 0 10px;
}

.jhipster-module--apply-button:disabled {
  color: #868686;
}
</style>
