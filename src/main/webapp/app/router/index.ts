import { LandscapeVue } from '@/module/primary/landscape';
import { ModulesVue } from '@/module/primary/modules';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    redirect: { name: 'Modules' },
  },
  {
    path: '/modules',
    name: 'Modules',
    component: ModulesVue,
  },
  {
    path: '/landscape',
    name: 'landscape',
    component: LandscapeVue,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
