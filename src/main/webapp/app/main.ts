import { AxiosHttp } from '@/http/AxiosHttp';
import router from '@/router';
import axios from 'axios';
import { createApp } from 'vue';
import App from '@/common/primary/app/App.vue';
import ConsoleLogger from '@/common/secondary/ConsoleLogger';
import { FileDownloader } from '@/common/primary/FileDownloader';
import { useHistoryStore } from '@/common/primary/HistoryStore';
import { createPinia } from 'pinia';
import piniaPersist from 'pinia-plugin-persist';
import { MittAlertBus } from '@/common/secondary/alert/MittAlertBus';
import mitt from 'mitt';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap-icons/font/bootstrap-icons.css';
import 'bootstrap';
import '../content/css/custom.css';
import { MittAlertListener } from '@/common/secondary/alert/MittAlertListener';
import { RestModulesRepository } from './module/secondary/RestModulesRepository';
import { RestProjectFoldersRepository } from '@/module/secondary/RestProjectFoldersRepository';

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPersist);
app.use(pinia);

const emitter = mitt();
const alertBus = new MittAlertBus(emitter);
const alertListener = new MittAlertListener(emitter);
const axiosHttp = new AxiosHttp(axios.create({ baseURL: '' }));
const fileDownloader = new FileDownloader(window);
const consoleLogger = new ConsoleLogger(console);
const historyStore = useHistoryStore();
const modulesRepository = new RestModulesRepository(axiosHttp);
const projectFoldersRepository = new RestProjectFoldersRepository(axiosHttp);

app.provide('alertBus', alertBus);
app.provide('alertListener', alertListener);
app.provide('fileDownloader', fileDownloader);
app.provide('globalWindow', window);
app.provide('historyStore', historyStore);
app.provide('logger', consoleLogger);
app.provide('modules', modulesRepository);
app.provide('projectFolders', projectFoldersRepository);
app.use(router);

app.mount('#app');
