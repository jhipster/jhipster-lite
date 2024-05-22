export default {
  'src/{test/javascript,main/webapp}/**/*.{ts,vue}': ['eslint --fix'],
  '{,src/*/resources/**/}*.{js,cjs,ts,tsx,vue}': ['prettier --write'],
  'src/main/style/**/*.{css,scss}': ['stylelint --fix', 'prettier --write'],
  'src/main/glyph/**/*.{css,scss}': ['prettier --write'],
  '*.pug': ['eslint --fix', 'prettier --write'],
  '*.{md,json,yml,html,java,xml,feature}': ['prettier --write'],
};
