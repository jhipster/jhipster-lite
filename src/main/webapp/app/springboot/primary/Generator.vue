<template>
  <header class="navbar navbar-expand-md bg-light bd-navbar">
    <nav class="container-fluid flex-wrap flex-md-nowrap align-text-center" aria-label="Main navigation">
      <a class="navbar-brand text-decoration-none d-flex align-items-center" href="#">
        <img src="../../../content/JHipster-Lite-neon-blue.png" alt="" width="40" class="mx-2" />
        <span class="fs-4 text-decoration-none" data-selector="generator.title">JHipster Lite</span>
      </a>
      <button
        class="navbar-toggler text-primary"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#bdNavbar"
        aria-controls="bdNavbar"
        aria-expanded="false"
        aria-label="Toggle navigation"
        width="36"
        height="36"
        fill-rule="evenodd"
      >
        <svg class="bi" viewBox="0 0 16 16" width="24" height="24" fill="#4ba8f8" xmlns="http://www.w3.org/2000/svg">
          <path
            d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"
          ></path>
        </svg>
      </button>

      <div class="collapse navbar-collapse fs-4" id="bdNavbar">
        <hr class="d-md-none text-white-50" />

        <ul class="navbar-nav flex-row flex-wrap ms-md-auto">
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://www.jhipster.tech/" target="_blank" rel="noopener">
              <img src="../../../content/V2_JHipster_Bowtie_RGB_2x.png" alt="" height="24" class="d-inline-block align-text-center" />
              <small class="d-md-none ms-2">JHipster</small>
            </a>
          </li>
          <hr class="separator--vertical mx-4 bg-primary" />
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://github.com/jhipster/jhipster-lite" target="_blank" rel="noopener">
              <em class="bi bi-github"></em>
              <small class="d-md-none ms-2">GitHub</small>
            </a>
          </li>
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://twitter.com/jhipster" target="_blank" rel="noopener">
              <em class="bi bi-twitter"></em>
              <small class="d-md-none ms-2">Twitter</small>
            </a>
          </li>

          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://opencollective.com/generator-jhipster" target="_blank" rel="noopener">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="36"
                height="36"
                fill="currentColor"
                fill-rule="evenodd"
                class="navbar-nav-svg d-inline-block"
                viewBox="0 0 52 52"
                role="img"
              >
                <title>Open Collective</title>
                <path
                  fill-opacity=".4"
                  d="M32.8 21c0 2.4-.8 4.9-2 6.9l5.1 5.1c2.5-3.4 4.1-7.6 4.1-12 0-4.6-1.6-8.8-4-12.2L30.7 14c1.2 2 2 4.3 2 7z"
                ></path>
                <path d="M20 33.7a12.8 12.8 0 0 1 0-25.6c2.6 0 5 .7 7 2.1L32 5a20 20 0 1 0 .1 31.9l-5-5.2a13 13 0 0 1-7 2z"></path>
              </svg>
              <small class="d-md-none ms-2">Open Collective</small>
            </a>
          </li>
        </ul>
      </div>
    </nav>
  </header>

  <main class="container-fluid py-4">
    <div class="row">
      <div class="col-12 col-md-4">
        <h2>Project configuration</h2>
        <div class="row g-3">
          <div class="col-12">
            <label for="path" class="form-label">Path (required) : </label>
            <input id="path" type="text" class="form-control" v-model="project.folder" required />
          </div>
          <div class="col-12">
            <label for="basename" class="form-label">Basename : </label>
            <input id="basename" type="text" class="form-control" v-model="project.baseName" />
          </div>
          <div class="col-12">
            <label for="projectname" class="form-label">Project name : </label>
            <input id="projectname" type="text" class="form-control" v-model="project.projectName" />
          </div>
          <div class="col-12">
            <label for="packagename" class="form-label">Package name : </label>
            <input id="packagename" type="text" class="form-control" v-model="project.packageName" />
          </div>
          <div class="col-12">
            <label for="serverport" class="form-label">Server port : </label>
            <input id="serverport" type="number" class="form-control" v-model="project.serverPort" min="0" />
          </div>
        </div>
      </div>
      <div class="col">
        <h2>Options</h2>
        <div class="row gx-4">
          <div class="col-12 col-md-6">
            <h3 class="h5">Language</h3>
            <hr
              class="q-separator q-separator--horizontal q-separator--horizontal-item-inset"
              aria-orientation="horizontal"
              style="margin-bottom: 8px; margin-top: 8px"
            />
            <div class="row p-2">
              <div class="list-group--inline mx-0">
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="language" id="language" value="java" checked />
                      <span> Java </span>
                    </label>
                  </div>
                  <img src="/content/java_rounded_40x40.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
              </div>
            </div>
          </div>
          <div class="col-12 col-md-6">
            <h3 class="h5">Build tool</h3>
            <hr
              class="q-separator q-separator--horizontal q-separator--horizontal-item-inset"
              aria-orientation="horizontal"
              style="margin-bottom: 8px; margin-top: 8px"
            />
            <div class="row p-2">
              <div class="list-group--inline mx-0">
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="buildTool" id="buildTool" value="maven" checked />
                      <span> Maven </span>
                    </label>
                  </div>
                  <img src="/content/MavenLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="buildTool" id="buildTool" value="gradle" />
                      <span> Gradle </span>
                    </label>
                  </div>
                  <img src="/content/GradleLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row gx-4">
          <div class="col-12 col-md-12">
            <h3 class="h5">Server</h3>
            <hr
              class="q-separator q-separator--horizontal q-separator--horizontal-item-inset"
              aria-orientation="horizontal"
              style="margin-bottom: 8px; margin-top: 8px"
            />
            <div class="row p-2">
              <div class="list-group--inline mx-0">
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="server" id="server" value="none" checked />
                      <span>None</span>
                    </label>
                  </div>
                  <img src="/content/JHipster-Lite-neon-blue_40x.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="server" id="server" value="springboot" />
                      <span> Spring Boot </span>
                    </label>
                  </div>
                  <img src="/content/SpringLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row gx-4">
          <div class="col-12 col-md-12">
            <h3 class="h5">Client</h3>
            <hr
              class="q-separator q-separator--horizontal q-separator--horizontal-item-inset"
              aria-orientation="horizontal"
              style="margin-bottom: 8px; margin-top: 8px"
            />
            <div class="row p-2">
              <div class="list-group--inline mx-0">
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="client" id="client" value="none" checked />
                      <span>None</span>
                    </label>
                  </div>
                  <img src="/content/JHipster-Lite-neon-blue_40x.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="client" id="client" value="angular" />
                      <span>Angular</span>
                    </label>
                  </div>
                  <img src="/content/AngularLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="client" id="client" value="react" />
                      <span>React</span>
                    </label>
                  </div>
                  <img src="/content/ReactLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item list-group-item-action gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="client" id="client" value="vue" />
                      <span>Vue.js</span>
                    </label>
                  </div>
                  <img src="/content/VueLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item list-group-item-action gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input class="form-check-input flex-shrink-0" type="radio" name="client" id="client" value="svelte" />
                      <span>Svelte</span>
                    </label>
                  </div>
                  <img src="/content/SvelteLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row gap-3 py-3">
      <div class="d-flex align-items-start">
        <div class="nav flex-column nav-pills me-3" id="v-pills-tab" role="tablist" aria-orientation="vertical">
          <button
            class="nav-link active"
            id="v-pills-home-tab"
            data-bs-toggle="pill"
            data-bs-target="#v-pills-home"
            type="button"
            role="tab"
            aria-controls="v-pills-home"
            aria-selected="true"
          >
            INIT
          </button>
          <button
            class="nav-link"
            id="v-pills-profile-tab"
            data-bs-toggle="pill"
            data-bs-target="#v-pills-profile"
            type="button"
            role="tab"
            aria-controls="v-pills-profile"
            aria-selected="false"
          >
            SPRING BOOT
          </button>
          <button
            class="nav-link"
            id="v-pills-settings-tab"
            data-bs-toggle="pill"
            data-bs-target="#v-pills-settings"
            type="button"
            role="tab"
            aria-controls="v-pills-settings"
            aria-selected="false"
          >
            ANGULAR
          </button>
          <button
            class="nav-link"
            id="v-pills-react-tab"
            data-bs-toggle="pill"
            data-bs-target="#v-pills-react"
            type="button"
            role="tab"
            aria-controls="v-pills-react"
            aria-selected="false"
          >
            REACT
          </button>
          <button
            class="nav-link"
            id="v-pills-vue-tab"
            data-bs-toggle="pill"
            data-bs-target="#v-pills-vue"
            type="button"
            role="tab"
            aria-controls="v-pills-vue"
            aria-selected="false"
          >
            VUE.JS
          </button>
        </div>
        <hr class="separator--vertical mx-4 bg-primary" />
        <div class="tab-content" id="v-pills-tabContent">
          <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel" aria-labelledby="v-pills-home-tab">
            <button class="jh-btn btn-primary" id="init" @click.prevent="initProject" data-selector="generator.init-button">Init</button>
            &nbsp;
            <button class="jh-btn btn-primary" id="maven" @click.prevent="addMaven" data-selector="generator.add-maven-button">
              Maven
            </button>
            &nbsp;
            <button class="jh-btn btn-primary" id="javabase" @click.prevent="addJavaBase" data-selector="generator.add-java-base-button">
              Java Base
            </button>
            &nbsp;
            <button
              class="jh-btn btn-primary"
              id="frontend-maven-plugin"
              @click.prevent="addFrontendMavenPlugin"
              data-selector="generator.add-frontend-maven-plugin-button"
            >
              Frontend Maven Plugin
            </button>
          </div>
          <div class="tab-pane fade" id="v-pills-profile" role="tabpanel" aria-labelledby="v-pills-profile-tab">
            <button
              class="jh-btn btn-primary"
              id="springboot"
              @click.prevent="addSpringBoot"
              data-selector="generator.add-spring-boot-button"
            >
              Spring Boot
            </button>
            &nbsp;
            <button
              class="jh-btn btn-primary"
              id="springbootmvctomcat"
              @click.prevent="addSpringBootMvcTomcat"
              data-selector="generator.add-spring-boot-mvc-tomcat-button"
            >
              Spring MVC Tomcat
            </button>
          </div>
          <div class="tab-pane fade" id="v-pills-settings" role="tabpanel" aria-labelledby="v-pills-settings-tab">
            <div>
              <input type="checkbox" id="angular-with-style" name="angular-with-style" v-model="isAngularWithStyle" />
              &nbsp;
              <label for="angular-with-style">Add Angular style</label>
            </div>
            <button class="jh-btn btn-primary" id="angular" @click.prevent="addAngular" data-selector="generator.add-angular-button">
              Generate Angular
            </button>
          </div>
          <div class="tab-pane fade" id="v-pills-react" role="tabpanel" aria-labelledby="v-pills-react-tab">
            <div>
              <input type="checkbox" id="react-with-style" name="react-with-style" v-model="isReactWithStyle" />
              &nbsp;
              <label for="react-with-style">Add React style</label>
            </div>
            <button class="jh-btn btn-primary" id="react" @click.prevent="addReact" data-selector="generator.add-react-button">
              Generate React
            </button>
          </div>
          <div class="tab-pane fade" id="v-pills-vue" role="tabpanel" aria-labelledby="v-pills-vue-tab">
            <div>
              <input type="checkbox" id="vue-with-style" name="vue-with-style" v-model="isVueWithStyle" />
              &nbsp;
              <label for="vue-with-style">Add Vue.js style</label>
            </div>
            <button class="jh-btn btn-primary" id="vue" @click.prevent="addVue" data-selector="generator.add-vue-button">
              Generate Vue.js
            </button>
          </div>
        </div>
      </div>
    </div>
  </main>
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
      <a href="/" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
        <img src="../../../content/JHipster-Lite-neon-blue_40x.png" alt="" />
      </a>
      <span class="text-muted">© 2022 JHipster Lite</span>
    </div>

    <ul class="nav col-md-4 justify-content-end list-unstyled d-flex">
      <li class="ms-3">
        <a class="text-muted" href="#"
          ><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
            <path
              d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z"
            /></svg
        ></a>
      </li>
      <li class="ms-3">
        <a class="text-muted" href="#"
          ><svg class="bi" width="24" height="24"><use xlink:href="#instagram"></use></svg
        ></a>
      </li>
      <li class="ms-3">
        <a class="text-muted" href="#"
          ><svg class="bi" width="24" height="24"><use xlink:href="#facebook"></use></svg
        ></a>
      </li>
    </ul>
  </footer>
</template>

<script lang="ts" src="./Generator.component.ts"></script>
