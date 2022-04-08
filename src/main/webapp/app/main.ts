import ProjectRepository from '@/springboot/secondary/ProjectRepository';
import { AxiosHttp } from '@/http/AxiosHttp';
import router from '@/router';
import axios from 'axios';
import { createApp } from 'vue';
import App from '@/common/primary/app/App.vue';
import AngularRepository from '@/springboot/secondary/client/AngularRepository';
import ReactRepository from '@/springboot/secondary/client/ReactRepository';
import VueRepository from '@/springboot/secondary/client/VueRepository';
import SpringBootRepository from './springboot/secondary/SpringBootRepository';
import ConsoleLogger from '@/common/secondary/ConsoleLogger';
import { createPinia } from 'pinia';
import piniaPersist from 'pinia-plugin-persist';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap';
import '../content/css/custom.css';

const axiosHttp = new AxiosHttp(axios.create({ baseURL: '' }));
const consoleLogger = new ConsoleLogger(console);
const projectRepository = new ProjectRepository(axiosHttp);
const angularRepository = new AngularRepository(axiosHttp);
const reactRepository = new ReactRepository(axiosHttp);
const vueRepository = new VueRepository(axiosHttp);
const springBootRepository = new SpringBootRepository(axiosHttp);

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPersist);
app.provide('angularService', angularRepository);
app.provide('logger', consoleLogger);
app.provide('projectService', projectRepository);
app.provide('reactService', reactRepository);
app.provide('springBootService', springBootRepository);
app.provide('vueService', vueRepository);
app.use(router);
app.use(pinia);

app.mount('#app');
