/// <reference types="vitest" />

import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  resolve: {
    alias: [{ find: '@', replacement: path.resolve(__dirname, 'src/main/webapp/app') }],
  },
  test: {
    reporters: 'vitest-sonar-reporter',
    outputFile: 'target/test-results/jest/TESTS-results-sonar.xml',
    globals: true,
    logHeapUsage: true,
    minThreads: 1,
    maxThreads: 2,
    environment: 'jsdom',
    cache: false,
    include: ['src/test/javascript/spec/**/*.(spec|test).(ts|tsx)'],
    exclude: ['node_modules', 'src/test/javascript/integration/**/*.spec.ts'],
    coverage: {
        clean: true,
        provider: 'istanbul',
        reportsDirectory: 'target/test-results/',
        reporter: ['html', 'json-summary', 'text', 'text-summary', 'lcov', 'clover'],
        watermarks: {
          statements: [100, 100],
          branches: [100, 100],
          functions: [100, 100],
          lines: [100, 100],
        },
      },
  },
});
