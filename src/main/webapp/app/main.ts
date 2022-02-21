import ProjectRepository from '@/main/webapp/app/generator/secondary/ProjectRepository';
import { createApp } from 'vue';
import App from './common/primary/app/App.vue';

const app = createApp(App);
app.provide('projectService', ProjectRepository);

app.mount('#app');
