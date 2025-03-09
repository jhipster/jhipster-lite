export default {
  '*.{js,cjs,pug,ts,tsx,vue}': ['eslint --fix', 'prettier --write'],
  '*.{css,scss}': ['stylelint --fix --allow-empty-input', 'prettier --write'],
  '*.{md,json,jsonc,yml,html,java,xml,feature,sh}': ['prettier --write'],
};
