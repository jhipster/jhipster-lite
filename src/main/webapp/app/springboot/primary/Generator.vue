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

      <div id="bdNavbar" class="collapse navbar-collapse fs-4">
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
            <input id="path" v-model="project.folder" type="text" class="form-control" required />
          </div>
          <div class="col-12">
            <label for="basename" class="form-label">Basename : </label>
            <input id="basename" v-model="project.baseName" type="text" class="form-control" />
          </div>
          <div class="col-12">
            <label for="projectname" class="form-label">Project name : </label>
            <input id="projectname" v-model="project.projectName" type="text" class="form-control" />
          </div>
          <div class="col-12">
            <label for="packagename" class="form-label">Package name : </label>
            <input id="packagename" v-model="project.packageName" type="text" class="form-control" />
          </div>
          <div class="col-12">
            <label for="serverport" class="form-label">Server port : </label>
            <input id="serverport" v-model="project.serverPort" type="number" class="form-control" min="0" />
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
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="language" class="form-check-input flex-shrink-0" type="radio" name="language" value="java" checked />
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
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="buildTool" class="form-check-input flex-shrink-0" type="radio" name="buildTool" value="maven" checked />
                      <span> Maven </span>
                    </label>
                  </div>
                  <img src="/content/MavenLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="buildTool" class="form-check-input flex-shrink-0" type="radio" name="buildTool" value="gradle" />
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
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="server" class="form-check-input flex-shrink-0" type="radio" name="server" value="none" checked />
                      <span>None</span>
                    </label>
                  </div>
                  <img src="/content/JHipster-Lite-neon-blue_40x.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="server" class="form-check-input flex-shrink-0" type="radio" name="server" value="springboot" />
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
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="client" class="form-check-input flex-shrink-0" type="radio" name="client" value="none" checked />
                      <span>None</span>
                    </label>
                  </div>
                  <img src="/content/JHipster-Lite-neon-blue_40x.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="client" class="form-check-input flex-shrink-0" type="radio" name="client" value="angular" />
                      <span>Angular</span>
                    </label>
                  </div>
                  <img src="/content/AngularLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="client" class="form-check-input flex-shrink-0" type="radio" name="client" value="react" />
                      <span>React</span>
                    </label>
                  </div>
                  <img src="/content/ReactLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item list-group-item-action gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="client" class="form-check-input flex-shrink-0" type="radio" name="client" value="vue" />
                      <span>Vue.js</span>
                    </label>
                  </div>
                  <img src="/content/VueLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item list-group-item-action gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input id="client" class="form-check-input flex-shrink-0" type="radio" name="client" value="svelte" />
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
    <div class="row py-3 d-flex align-items-start">
      <div class="col-4 col-md-2">
        <ul id="v-pills-tab" class="nav nav-pills flex-column w-100" role="tablist" aria-orientation="vertical">
          <li class="nav-item">
            <div class="focus-helper" tabindex="-1"></div>
            <button
              id="v-pills-home-tab"
              class="nav-link active"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-home"
              role="tab"
              aria-controls="v-pills-home"
              aria-selected="true"
            >
              <i class="bi bi-code-slash" style="font-size: 1.5rem; color: var(--primary-color)"></i>
              INITIALIZATION
            </button>
          </li>

          <li class="nav-item">
            <div class="q-focus-helper" tabindex="-1"></div>
            <button
              id="v-pills-profile-tab"
              class="nav-link text-spring"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-profile"
              role="tab"
              aria-controls="v-pills-profile"
              aria-selected="false"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" x="0px" y="0px" viewBox="0 0 64 64" xml:space="preserve">
                <path
                  d="M58.2 3.365a29.503 29.503 0 0 1-3.419 6.064A32.094 32.094 0 1 0 9.965 55.372l1.186 1.047a32.08 32.08 0 0 0 52.67-22.253c.875-8.17-1.524-18.51-5.62-30.8zM14.53 55.558a2.744 2.744 0 1 1-.404-3.857 2.744 2.744 0 0 1 .404 3.857zm43.538-9.61c-7.92 10.55-24.83 6.99-35.672 7.502 0 0-1.922.113-3.857.43 0 0 .73-.31 1.663-.663 7.614-2.65 11.213-3.16 15.838-5.54 8.708-4.427 17.322-14.122 19.112-24.2-3.313 9.695-13.373 18.032-22.53 21.418-6.276 2.313-17.614 4.566-17.614 4.566l-.457-.245c-7.714-3.75-7.952-20.457 6.077-25.845 6.143-2.366 12.02-1.067 18.654-2.65 7.084-1.683 15.28-6.99 18.615-13.916 3.73 11.08 8.224 28.422.166 39.15z"
                  fill="#68bd45"
                />
              </svg>
              SPRING BOOT
            </button>
          </li>

          <li class="nav-item">
            <button
              id="v-pills-settings-tab"
              class="nav-link text-angular"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-settings"
              type="button"
              role="tab"
              aria-controls="v-pills-settings"
              aria-selected="false"
            >
              <svg
                version="1.1"
                id="Layer_1"
                xmlns="http://www.w3.org/2000/svg"
                xmlns:xlink="http://www.w3.org/1999/xlink"
                x="0px"
                y="0px"
                viewBox="0 0 240 240"
                width="24"
                height="24"
                xml:space="preserve"
              >
                <g>
                  <polygon
                    fill="#DD0031"
                    points="125,30 125,30 125,30 31.9,63.2 46.1,186.3 125,230 125,230 125,230 203.9,186.3 218.1,63.2 	"
                  />
                  <polygon
                    fill="#C3002F"
                    points="125,30 125,52.2 125,52.1 125,153.4 125,153.4 125,230 125,230 203.9,186.3 218.1,63.2 125,30 	"
                  />
                  <path
                    fill="#FFFFFF"
                    d="M125,52.1L66.8,182.6h0h21.7h0l11.7-29.2h49.4l11.7,29.2h0h21.7h0L125,52.1L125,52.1L125,52.1L125,52.1
		L125,52.1z M142,135.4H108l17-40.9L142,135.4z"
                  />
                </g>
              </svg>
              ANGULAR
            </button>
          </li>
          <li class="nav-item">
            <button
              id="v-pills-react-tab"
              class="nav-link text-react"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-react"
              type="button"
              role="tab"
              aria-controls="v-pills-react"
              aria-selected="false"
            >
              <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="-11.5 -10.23174 23 20.46348">
                <title>React Logo</title>
                <circle cx="0" cy="0" r="2.05" fill="#61dafb" />
                <g stroke="#61dafb" stroke-width="1" fill="none">
                  <ellipse rx="11" ry="4.2" />
                  <ellipse rx="11" ry="4.2" transform="rotate(60)" />
                  <ellipse rx="11" ry="4.2" transform="rotate(120)" />
                </g>
              </svg>

              REACT
            </button>
          </li>

          <li class="nav-item">
            <button
              id="v-pills-vue-tab"
              class="nav-link text-vue"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-vue"
              type="button"
              role="tab"
              aria-controls="v-pills-vue"
              aria-selected="false"
            >
              <svg class="logo" viewBox="0 0 128 128" width="24" height="24" data-v-5f26462c="">
                <path fill="#42b883" d="M78.8,10L64,35.4L49.2,10H0l64,110l64-110C128,10,78.8,10,78.8,10z" data-v-5f26462c=""></path>
                <path fill="#35495e" d="M78.8,10L64,35.4L49.2,10H25.6L64,76l38.4-66H78.8z" data-v-5f26462c=""></path>
              </svg>
              VUE.JS
            </button>
          </li>
        </ul>
      </div>

      <div class="col-8 col-md-10">
        <div id="v-pills-tabContent" class="tab-content">
          <div id="v-pills-home" class="tab-pane fade show active" role="tabpanel" aria-labelledby="v-pills-home-tab">
            <button id="init" class="btn btn-outline-primary" data-selector="generator.init-button" @click.prevent="initProject">
              Init
            </button>
            &nbsp;
            <button id="maven" class="btn btn-outline-primary" data-selector="generator.add-maven-button" @click.prevent="addMaven">
              Maven
            </button>
            &nbsp;
            <button
              id="javabase"
              class="btn btn-outline-primary"
              data-selector="generator.add-java-base-button"
              @click.prevent="addJavaBase"
            >
              Java Base
            </button>
            &nbsp;
            <button
              id="frontend-maven-plugin"
              class="btn btn-outline-primary"
              data-selector="generator.add-frontend-maven-plugin-button"
              @click.prevent="addFrontendMavenPlugin"
            >
              Frontend Maven Plugin
            </button>
          </div>
          <div id="v-pills-profile" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-profile-tab">
            <button
              id="springboot"
              class="btn btn-outline-primary"
              data-selector="generator.add-spring-boot-button"
              @click.prevent="addSpringBoot"
            >
              Spring Boot
            </button>
            &nbsp;
            <button
              id="springbootmvctomcat"
              class="btn btn-outline-primary"
              data-selector="generator.add-spring-boot-mvc-tomcat-button"
              @click.prevent="addSpringBootMvcTomcat"
            >
              Spring MVC Tomcat
            </button>
          </div>
          <div id="v-pills-settings" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-settings-tab">
            <div class="list-group--inline py-2">
              <label for="angular-with-style" class="list-group-item gap-3">
                <input
                  id="angular-with-style"
                  v-model="isAngularWithStyle"
                  type="checkbox"
                  name="angular-with-style"
                  class="form-check-input flex-shrink-0"
                />
                <span class="form-checked-content">
                  <strong>Add Angular style</strong>
                </span>
              </label>
            </div>
            <button id="angular" class="btn btn-outline-primary" data-selector="generator.add-angular-button" @click.prevent="addAngular">
              Generate Angular
            </button>
          </div>
          <div id="v-pills-react" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-react-tab">
            <div class="list-group--inline py-2">
              <label for="react-with-style" class="list-group-item gap-3">
                <input
                  id="react-with-style"
                  v-model="isReactWithStyle"
                  name="react-with-style"
                  class="form-check-input flex-shrink-0"
                  type="checkbox"
                  value=""
                />
                <span class="form-checked-content">
                  <strong>Add React style</strong>
                </span>
              </label>
            </div>
            <button id="react" class="btn btn-outline-primary" data-selector="generator.add-react-button" @click.prevent="addReact">
              Generate React
            </button>
          </div>
          <div id="v-pills-vue" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-vue-tab">
            <div class="list-group--inline py-2">
              <label for="react-with-style" class="list-group-item gap-3">
                <input
                  id="vue-with-style"
                  v-model="isVueWithStyle"
                  type="checkbox"
                  name="vue-with-style"
                  class="form-check-input flex-shrink-0"
                  value=""
                />
                <span class="form-checked-content">
                  <strong>Add Vue.js style</strong>
                </span>
              </label>
            </div>
            <button id="vue" class="btn btn-outline-primary" data-selector="generator.add-vue-button" @click.prevent="addVue">
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
