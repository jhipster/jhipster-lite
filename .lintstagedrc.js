export default {
  '*.{js,cjs,ts,tsx,vue}': ['eslint --fix', 'prettier --write'],
  'src/main/style/**/*.{css,scss}': ['stylelint --fix', 'prettier --write'],
  'src/main/glyph/**/*.{css,scss}': ['prettier --write'],
  '*.pug': ['eslint --fix', 'prettier --write'],
  '*.{md,json,yml,html,java,xml,feature}': ['prettier --write'],
};
