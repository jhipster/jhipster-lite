import { defineConfig } from 'cypress';

export default defineConfig({
  e2e: {
    baseUrl: 'http://localhost:7471',
    specPattern: 'src/test/webapp/cypress/integration/**/*.spec.ts',
    fixturesFolder: 'src/test/webapp/cypress/fixtures',
    supportFolder: 'src/test/webapp/cypress/support',
    supportFile: false,
    video: false,
  },
});
