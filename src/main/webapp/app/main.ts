import ProjectRepository from '@/generator/secondary/ProjectRepository';
import { AxiosHttp } from '@/http/AxiosHttp';
import router from '@/router';
import axios from 'axios';
import { createApp } from 'vue';
import App from '@/common/primary/app/App.vue';

const axiosHttp = new AxiosHttp(axios.create({ baseURL: 'http://localhost:7471' }));
const projectRepository = new ProjectRepository(axiosHttp);

const app = createApp(App);
app.provide('projectService', projectRepository);
app.use(router);

app.mount('#app');
