import ProjectRepository from '@/springboot/secondary/ProjectRepository';
import { AxiosHttp } from '@/http/AxiosHttp';
import router from '@/router';
import axios from 'axios';
import { createApp } from 'vue';
import App from '@/common/primary/app/App.vue';
import AngularRepository from '@/springboot/secondary/client/AngularRepository';
import ReactRepository from '@/springboot/secondary/client/ReactRepository';
import VueRepository from '@/springboot/secondary/client/VueRepository';

const axiosHttp = new AxiosHttp(axios.create({ baseURL: 'http://localhost:7471' }));
const projectRepository = new ProjectRepository(axiosHttp);
const angularRepository = new AngularRepository(axiosHttp);
const reactRepository = new ReactRepository(axiosHttp);
const vueRepository = new VueRepository(axiosHttp);

const app = createApp(App);
app.provide('projectService', projectRepository);
app.provide('angularService', angularRepository);
app.provide('reactRepository', reactRepository);
app.provide('vueRepository', vueRepository);
app.use(router);

app.mount('#app');
