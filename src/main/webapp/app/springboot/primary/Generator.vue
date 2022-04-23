<template>
  <header class="navbar navbar-expand-md bg-primary bd-navbar shadow mb-3">
    <nav class="container-fluid flex-wrap flex-md-nowrap align-text-center" aria-label="Main navigation">
      <div class="navbar-brand text-decoration-none d-flex align-items-center gap-4">
        <img src="../../../content/JHipster-Lite-neon-blue.png" alt="" width="48" />
        <span class="fs-3 text-decoration-none text-white title" :data-selector="selectorPrefix + '.title'">JHipster Lite</span>
      </div>
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
            <a class="nav-link p-2" href="./swagger-ui.html" target="_blank" rel="noopener">
              <em class="bi bi-book-half" role="img" aria-label="API"></em>
              <small class="d-md-none ms-2">API</small>
            </a>
          </li>
          <hr class="separator--vertical mx-2" />
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://www.jhipster.tech/" target="_blank" rel="noopener">
              <svg
                xmlns="http://www.w3.org/2000/svg"
                width="48"
                height="36"
                fill="currentColor"
                fill-rule="evenodd"
                class="navbar-nav-svg d-inline-block"
                viewBox="0 0 120 60"
                role="img"
                aria-label="JHipster"
              >
                <g id="a" />
                <g id="b">
                  <g id="c">
                    <g id="d">
                      <path
                        fill-opacity=".4"
                        id="e"
                        class="g"
                        d="M69.88,20.11S10.53-14.33,5.4,7.08C.27,28.5-5.5,44.79,10.31,47.8c15.81,3.01,59.57-27.69,59.57-27.69Z"
                      />
                      <path
                        id="f"
                        class="h"
                        d="M59.62,14.93c17.04-8.81,50.67-23.88,54.52-7.92,5.18,21.49,10.98,37.84-4.78,40.91-11.08,2.16-35.99-12.37-49.82-21.2-.01-10.44,.1-1.51,.09-11.79Z"
                      />
                    </g>
                  </g>
                </g>
              </svg>
              <small class="d-md-none ms-2">JHipster</small>
            </a>
          </li>
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://github.com/jhipster/jhipster-lite" target="_blank" rel="noopener">
              <em class="bi bi-github" role="img" aria-label="GitHub"></em>
              <small class="d-md-none ms-2">GitHub</small>
            </a>
          </li>
          <li class="nav-item col-6 col-md-auto">
            <a class="nav-link p-2" href="https://twitter.com/jhipster" target="_blank" rel="noopener">
              <em class="bi bi-twitter" role="img" aria-label="twitter"></em>
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
                aria-label="Open collective"
              >
                <title>Open Collective</title>
                <path
                  fill-opacity=".4"
                  d="M32.8 21c0 2.4-.8 4.9-2 6.9l5.1 5.1c2.5-3.4 4.1-7.6 4.1-12 0-4.6-1.6-8.8-4-12.2L30.7 14c1.2 2 2 4.3 2 7z"
                ></path>
                <path d="M20 33.7a12.8 12.8 0 0 1 0-25.6c2.6 0 5 .7 7 2.1L32 5a20 20 0 1 0 .1 31.9l-5-5.2a13 13 0 0 1-7 2z"></path>
              </svg>
              <small class="d-md-none">Open Collective</small>
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
                  <img src="../../../content/java_rounded_40x40.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
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
                      <input
                        id="buildTool"
                        v-model="buildTool"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="buildTool"
                        value="maven"
                        checked
                      />
                      <span> Maven </span>
                    </label>
                  </div>
                  <img src="../../../content/MavenLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="buildTool"
                        v-model="buildTool"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="buildTool"
                        value="gradle"
                      />
                      <span> Gradle </span>
                    </label>
                  </div>
                  <img src="../../../content/GradleLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
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
                      <input
                        id="option-no-server"
                        v-model="server"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="server"
                        value="none"
                        checked
                      />
                      <span>None</span>
                    </label>
                  </div>
                  <img
                    src="../../../content/JHipster-Lite-neon-blue_40x.png"
                    alt=""
                    width="32"
                    height="32"
                    class="rounded-circle flex-shrink-0"
                  />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="option-springboot"
                        v-model="server"
                        :data-selector="selectorPrefix + '.option-springboot'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="server"
                        value="springboot"
                      />
                      <span> Spring Boot </span>
                    </label>
                  </div>
                  <img src="../../../content/SpringLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
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
                      <input
                        id="option-none-client"
                        v-model="client"
                        :data-selector="selectorPrefix + '.option-none-client'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="client"
                        value="none"
                        checked
                      />
                      <span>None</span>
                    </label>
                  </div>
                  <img
                    src="../../../content/JHipster-Lite-neon-blue_40x.png"
                    alt=""
                    width="32"
                    height="32"
                    class="rounded-circle flex-shrink-0"
                  />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="option-angular"
                        v-model="client"
                        :data-selector="selectorPrefix + '.option-angular'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="client"
                        value="angular"
                      />
                      <span>Angular</span>
                    </label>
                  </div>
                  <img src="../../../content/AngularLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="option-react"
                        v-model="client"
                        :data-selector="selectorPrefix + '.option-react'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="client"
                        value="react"
                      />
                      <span>React</span>
                    </label>
                  </div>
                  <img src="../../../content/ReactLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="option-vue"
                        v-model="client"
                        :data-selector="selectorPrefix + '.option-vue'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="client"
                        value="vue"
                      />
                      <span>Vue.js</span>
                    </label>
                  </div>
                  <img src="../../../content/VueLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
                <div class="list-group-item align-items-center gap-3 py-3" aria-current="true">
                  <div class="gap-2 w-100 justify-content-between">
                    <label>
                      <input
                        id="option-svelte"
                        v-model="client"
                        :data-selector="selectorPrefix + '.option-svelte'"
                        class="form-check-input flex-shrink-0"
                        type="radio"
                        name="client"
                        value="svelte"
                      />
                      <span>Svelte</span>
                    </label>
                  </div>
                  <img src="../../../content/SvelteLogo.png" alt="" width="32" height="32" class="rounded-circle flex-shrink-0" />
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row my-4 py-3 d-flex align-items-start">
      <div class="col-12 col-md-3 col-lg-4">
        <ul id="v-pills-tab" class="nav nav-pills flex-column w-100" role="tablist" aria-orientation="vertical">
          <li class="nav-item">
            <div class="focus-helper" tabindex="-1"></div>
            <button
              id="section-init"
              class="nav-link active"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-init"
              role="tab"
              aria-controls="v-pills-init"
              aria-selected="true"
            >
              <em class="bi bi-code-slash"></em>
              INITIALIZATION
            </button>
          </li>

          <li v-if="server === 'springboot'" class="nav-item">
            <div class="q-focus-helper" tabindex="-1"></div>
            <button
              id="section-springboot"
              :data-selector="selectorPrefix + '.section-springboot'"
              class="nav-link text-spring"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-springboot"
              role="tab"
              aria-controls="v-pills-springboot"
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

          <li v-if="client === 'angular'" class="nav-item">
            <button
              id="section-angular"
              :data-selector="selectorPrefix + '.section-angular'"
              class="nav-link text-angular"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-angular"
              type="button"
              role="tab"
              aria-controls="v-pills-angular"
              aria-selected="false"
            >
              <svg
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
          <li v-if="client === 'react'" class="nav-item">
            <button
              id="section-react"
              :data-selector="selectorPrefix + '.section-react'"
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
          <li v-if="client === 'vue'" class="nav-item">
            <button
              id="section-vue"
              :data-selector="selectorPrefix + '.section-vue'"
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
          <li v-if="client === 'svelte'" class="nav-item">
            <button
              id="section-svelte"
              :data-selector="selectorPrefix + '.section-vue'"
              class="nav-link text-svelte"
              data-bs-toggle="tab"
              data-bs-target="#v-pills-svelte"
              type="button"
              role="tab"
              aria-controls="v-pills-svelte"
              aria-selected="false"
            >
              <img src="../../../content/SvelteLogo.png" alt="" width="24" height="24" class="rounded-circle flex-shrink-0" />
              SVELTE
            </button>
          </li>
        </ul>
      </div>

      <div class="col">
        <div id="v-pills-tab" class="tab-content">
          <div id="v-pills-init" class="tab-pane fade show active" role="tabpanel" aria-labelledby="v-pills-home-tab">
            <button id="init" class="btn btn-outline-primary" :data-selector="selectorPrefix + '.init-button'" @click.prevent="initProject">
              Init
            </button>

            <button
              v-if="buildTool === 'maven'"
              id="maven"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-maven-button'"
              @click.prevent="addMaven"
            >
              Maven
            </button>

            <button
              id="javabase"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-java-base-button'"
              @click.prevent="addJavaBase"
            >
              Java Base
            </button>

            <button
              id="frontend-maven-plugin"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-frontend-maven-plugin-button'"
              @click.prevent="addFrontendMavenPlugin"
            >
              Frontend Maven Plugin
            </button>

            <button
              v-if="project.folder !== ''"
              id="download"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.download-button'"
              @click.prevent="download"
            >
              Download
            </button>
          </div>
          <div
            v-if="server === 'springboot'"
            id="v-pills-springboot"
            class="tab-pane fade"
            role="tabpanel"
            aria-labelledby="v-pills-profile-tab"
          >
            <button
              id="springboot"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-button'"
              @click.prevent="addSpringBoot"
            >
              Spring Boot
            </button>

            <button
              id="springbootmvctomcat"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-mvc-tomcat-button'"
              @click.prevent="addSpringBootMvcTomcat"
            >
              Spring MVC Tomcat
            </button>
            <button
              id="springbootwebfluxnetty"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-webflux-netty-button'"
              @click.prevent="addSpringBootWebfluxNetty"
            >
              Spring Webflux Netty
            </button>
            <br />
            <br />
            <button
              id="springboot-jwt"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-jwt-button'"
              @click.prevent="addSpringBootSecurityJWT"
            >
              Security JWT
            </button>

            <button
              id="springboot-jwt-basic-auth"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-jwt-basic-auth-button'"
              @click.prevent="addSpringBootSecurityJWTBasicAuth"
            >
              Security JWT Basic Auth
            </button>
            <br />
            <br />
            <button
              id="springboot-database-postgresql"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-database-postgresql-button'"
              @click.prevent="addPostgreSQL"
            >
              PostgreSQL
            </button>

            <button
              id="springboot-database-mysql"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-database-mysql-button'"
              @click.prevent="addMySQL"
            >
              MySQL
            </button>

            <button
              id="springboot-database-mariadb"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-database-mariadb-button'"
              @click.prevent="addMariaDB"
            >
              MariaDB
            </button>

            <button
              id="springboot-database-mongodb"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-spring-boot-database-mongodb-button'"
              @click.prevent="addMongoDB"
            >
              MongoDB
            </button>
          </div>
          <div
            v-if="client === 'angular'"
            id="v-pills-angular"
            class="tab-pane fade"
            role="tabpanel"
            aria-labelledby="v-pills-settings-tab"
          >
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
            <button
              id="angular"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-angular-button'"
              @click.prevent="addAngular"
            >
              Generate Angular
            </button>
          </div>
          <div v-if="client === 'react'" id="v-pills-react" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-react-tab">
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
            <button
              id="react"
              class="btn btn-outline-primary"
              :data-selector="selectorPrefix + '.add-react-button'"
              @click.prevent="addReact"
            >
              Generate React
            </button>
          </div>
          <div v-if="client === 'vue'" id="v-pills-vue" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-vue-tab">
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
            <button id="vue" class="btn btn-outline-primary" :data-selector="selectorPrefix + '.add-vue-button'" @click.prevent="addVue">
              Generate Vue.js
            </button>
          </div>
          <div v-if="client === 'svelte'" id="v-pills-svelte" class="tab-pane fade" role="tabpanel" aria-labelledby="v-pills-svelte-tab">
            <div class="list-group--inline py-2">
              <label for="svelte-with-style" class="list-group-item gap-3">
                <input
                  id="svelte-with-style"
                  v-model="isSvelteWithStyle"
                  type="checkbox"
                  name="svelte-with-style"
                  class="form-check-input flex-shrink-0"
                  value=""
                />
                <span class="form-checked-content">
                  <strong>Add Svelte style</strong>
                </span>
              </label>
            </div>
            <button id="svelte" class="btn btn-outline-primary" :data-selector="selectorPrefix + '.add-svelte-button'" @click.prevent="">
              Generate Svelte (not implemented)
            </button>
          </div>
        </div>
      </div>
    </div>
  </main>
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <div class="col-md-4 d-flex align-items-center">
      <a href="https://www.jhipster.tech/" target="_blank" rel="noopener" class="mb-3 me-2 mb-md-0 text-muted text-decoration-none lh-1">
        <img src="../../../content/JHipster-Lite-neon-blue_40x.png" alt="" />
      </a>
      <span class="text-muted">© 2022 JHipster Lite</span>
    </div>
  </footer>
</template>

<script lang="ts" src="./Generator.component.ts"></script>
