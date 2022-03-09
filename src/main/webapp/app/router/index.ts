import { GeneratorVue } from '@/generator/primary';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    redirect: { name: 'Generator' },
  },
  {
    path: '/generator',
    name: 'Generator',
    component: GeneratorVue,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
