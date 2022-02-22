import { defineConfig } from 'vite';
import react from '@vitejs/plugin-react';
import path from 'path';

export default defineConfig({
  plugins: [react()],
  build: {
    outDir: '../../../target/classes/static',
  },
  resolve: {
    alias: [
      { find: '@', replacement: path.resolve(__dirname, 'src') },
      { find: '@webapp', replacement: path.resolve(__dirname, 'src/main/webapp') },
    ],
  },
  root: 'src/main/webapp',
});
