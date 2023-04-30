import { defineComponent, onMounted } from 'vue';

export default defineComponent({
  name: 'ThemeButton',

  setup() {
    let userTheme = 'light-theme';

    onMounted(() => {
      const initUserTheme = (getTheme() as 'light-theme' | 'dark-theme') || getMediaPreference();
      setTheme(initUserTheme);
    });

    const toggleTheme = () => {
      const activeTheme = localStorage.getItem('user-theme');
      if (activeTheme === 'light-theme') {
        setTheme('dark-theme');
      } else {
        setTheme('light-theme');
      }
    };

    const getTheme = () => {
      return localStorage.getItem('user-theme');
    };

    const setTheme = (theme: 'light-theme' | 'dark-theme') => {
      localStorage.setItem('user-theme', theme);
      userTheme = theme;
      document.documentElement.className = theme;
    };

    const getMediaPreference = () => {
      const hasDarkPreference: MediaQueryList['matches'] = window.matchMedia('(prefers-color-scheme: dark)').matches;
      if (hasDarkPreference) {
        return 'dark-theme';
      } else {
        return 'light-theme';
      }
    };

    return {
      userTheme,
      toggleTheme,
      getTheme,
      setTheme,
      getMediaPreference,
    };
  },
});
