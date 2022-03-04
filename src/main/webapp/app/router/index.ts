import { HomeVue } from '@/common/primary/home';
import { GeneratorVue } from '@/generator/primary';
import { createRouter, createWebHistory } from 'vue-router';

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomeVue,
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
