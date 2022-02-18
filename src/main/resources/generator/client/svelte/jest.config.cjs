module.exports = {
	moduleFileExtensions: ['js', 'ts', 'json', 'svelte'],
	transform: {
	  '^.+\\.ts$': 'ts-jest',
	  '^.+\\.svelte$': ['svelte-jester', { preprocess: true }],
	  '.+\\.(css|styl|less|sass|scss|png|jpg|svg|ttf|woff|woff2)$': 'jest-transform-stub',
	},
	modulePathIgnorePatterns: ['<rootDir>/src/main/resources/'],
	testEnvironment: "jsdom"
  };
  