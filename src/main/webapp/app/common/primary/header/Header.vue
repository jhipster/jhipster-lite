<template>
  <header class="jhlite-header">
    <div class="jhlite-header--slot with-theme-switch">
      <router-link class="jhlite-logo" to="/">
        <img class="jhlite-logo--icon" src="../../../../content/JHipster-Lite-neon-blue.png" alt="JHipster bow tie" width="48" />
        <span class="jhlite-logo--text">JHipster lite</span>
      </router-link>
      <div class="container">
        <input id="switch" class="container_toggle" type="checkbox" name="mode" @change="toggleTheme" />
        <label for="switch">Toggle</label>
      </div>
    </div>
    <div class="jhlite-header--slot -expand"></div>
    <div class="jhlite-header--slot">
      <nav aria-label="Main navigation">
        <ul class="jhlite-nav">
          <li class="jhlite-nav--item">
            <router-link class="jhlite-nav-item" to="/landscape">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="map" aria-label="Icon map" title="Module landscape" />
                </span>
                <span class="jhlite-icon-text--text">Landscape</span>
              </span>
            </router-link>
          </li>
          <li class="jhlite-nav--item">
            <router-link class="jhlite-nav-item" to="/patches">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="puzzle" aria-label="Icon puzzle" title="Patch list" />
                </span>
                <span class="jhlite-icon-text--text">Patches</span>
              </span>
            </router-link>
          </li>
          <li class="jhlite-nav--item">
            <a class="jhlite-nav-item" href="./swagger-ui.html" target="_blank">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="binoculars" aria-label="Icon binoculars" title="Swagger UI" />
                </span>
                <span class="jhlite-icon-text--text">Swagger UI</span>
              </span>
            </a>
          </li>
          <li class="jhlite-nav--item">
            <a class="jhlite-nav-item" href="https://www.jhipster.tech/jhipster-lite" target="_blank" rel="noopener">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="jhipster" aria-label="JHipster logo" title="JHipster website" />
                </span>
                <span class="jhlite-icon-text--text -lg-only">JHipster</span>
              </span>
            </a>
          </li>
          <li class="jhlite-nav--item">
            <a class="jhlite-nav-item" href="https://github.com/jhipster/jhipster-lite" target="_blank" rel="noopener">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="github-circled" aria-label="GitHub logo" title="JHipster Lite GitHub page" />
                </span>
                <span class="jhlite-icon-text--text -lg-only">GitHub</span>
              </span>
            </a>
          </li>
          <li class="jhlite-nav--item">
            <a class="jhlite-nav-item" href="https://twitter.com/jhipster" target="_blank" rel="noopener">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="twitter" aria-label="Twitter logo" title="JHipster Twitter" />
                </span>
                <span class="jhlite-icon-text--text -lg-only">Twitter</span>
              </span>
            </a>
          </li>
          <li class="jhlite-nav--item">
            <a class="jhlite-nav-item" href="https://opencollective.com/generator-jhipster" target="_blank" rel="noopener">
              <span class="jhlite-icon-text">
                <span class="jhlite-icon-text--icon">
                  <IconVue name="open-collective" aria-label="Open Collective logo" title="Open Collective JHipster page" />
                </span>
                <span class="jhlite-icon-text--text -lg-only">Open Collective</span>
              </span>
            </a>
          </li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script lang="ts" src="./Header.component.ts"></script>

<style>
.with-theme-switch {
  display: flex;
  align-items: center;
  gap: 16px;
}

.container {
  display: flex;
}

input[type='checkbox'] {
  height: 0;
  width: 0;
  visibility: hidden;
}

label {
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

label:after {
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

input + label {
  background: red;
}

input:checked + label {
  background: #ffbd07;
}

input:checked + label:after {
  left: calc(100% - 5px);
  -webkit-transform: translateX(-100%);
  -moz-transform: translateX(-100%);
  -ms-transform: translateX(-100%);
  -o-transform: translateX(-100%);
  transform: translateX(-100%);
}
</style>
