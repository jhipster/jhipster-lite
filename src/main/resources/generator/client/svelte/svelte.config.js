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
			routes: 'src/main/webapp/routes'
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
