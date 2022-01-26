const { pathsToModuleNameMapper } = require('ts-jest');

const {
  compilerOptions: { paths = {}, baseUrl = './' },
} = require('./tsconfig.json');

module.exports = {
  transformIgnorePatterns: ['node_modules/(?!.*\\.mjs$|dayjs/esm)'],
  resolver: 'jest-preset-angular/build/resolvers/ng-jest-resolver.js',
  roots: ['<rootDir>', `<rootDir>/${baseUrl}`],
  modulePaths: [`<rootDir>/${baseUrl}`],
  cacheDirectory: '<rootDir>/target/jest-cache',
  coverageDirectory: '<rootDir>/target/test-results/',
  moduleNameMapper: pathsToModuleNameMapper(paths, { prefix: `<rootDir>/${baseUrl}/` }),
  testMatch: ['<rootDir>/src/main/webapp/app/**/@(*.)@(spec.ts)'],
  testURL: 'http://localhost/',
};
