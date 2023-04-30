<template>
  <div class="container">
    <input id="switch" class="container_toggle" type="checkbox" name="mode" @change="toggleTheme" />
    <label for="switch" class="theme-toggle-button__label">Toggle</label>
  </div>
</template>

<script lang="ts" src="./ThemeButton.component.ts"></script>

<style>
.container {
  display: flex;
}

.container_toggle {
  height: 0;
  width: 0;
  visibility: hidden;
}

.theme-toggle-button__label {
  cursor: pointer;
  text-indent: -9999px;
  width: 55px;
  height: 30px;
  background: var(--lightBtn);
  margin: 0 auto;
  display: flex;
  justify-content: center;
  align-items: center;
  -webkit-border-radius: 100px;
  -moz-border-radius: 100px;
  border-radius: 100px;
  position: relative;
}

.theme-toggle-button__label:after {
  content: '';
  background: #fff;
  width: 20px;
  height: 20px;
  -webkit-border-radius: 50%;
  -moz-border-radius: 50%;
  border-radius: 50%;
  position: absolute;
  top: 5px;
  left: 4px;
  transition: cubic-bezier(0.68, -0.55, 0.27, 01.55) 320ms;
}

.container_toggle + .theme-toggle-button__label {
  background: red;
}

.container_toggle:checked + .theme-toggle-button__label {
  background: #ffbd07;
}

.container_toggle:checked + .theme-toggle-button__label:after {
  left: calc(100% - 5px);
  -webkit-transform: translateX(-100%);
  -moz-transform: translateX(-100%);
  -ms-transform: translateX(-100%);
  -o-transform: translateX(-100%);
  transform: translateX(-100%);
}
</style>
