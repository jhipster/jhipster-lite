import adapter from '@sveltejs/adapter-auto';
import preprocess from 'svelte-preprocess';
import path from 'path';

/** @type {import('@sveltejs/kit').Config} */
const config = {
	// Consult https://github.com/sveltejs/svelte-preprocess
	// for more information about preprocessors
	preprocess: preprocess(),

	kit: {
		adapter: adapter(),
		files: {
			routes: 'src/main/webapp/routes',
			template: 'src/main/webapp/app.html',
			assets: 'src/main/webapp/static',
			hooks: 'src/main/webapp/hooks',
			lib: 'src/main/webapp/lib',
			serviceWorker: 'src/main/webapp/service-worker'
		},
		vite: {
			resolve: {
				alias: {
					$webapp: path.resolve('./src/main/webapp')
				}
			}
		}
	}
};

export default config;
