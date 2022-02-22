import ProjectRepository from '@/main/webapp/app/generator/secondary/ProjectRepository';
import router from '@/main/webapp/app/router';
import { createApp } from 'vue';
import App from './common/primary/app/App.vue';

const app = createApp(App);
app.provide('projectService', ProjectRepository);
app.use(router);

app.mount('#app');
