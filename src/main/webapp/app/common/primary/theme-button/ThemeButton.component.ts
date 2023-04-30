import LocalStorage from '@/common/secondary/LocalStorage';
import { ThemePreference as Theme, getMediaPreference } from '@/module/secondary/GetMediaPreference';
import { defineComponent, ref, computed, onMounted } from 'vue';

/**
 * ThemeSwitchButton
 *
 * 1. use localStorage saved theme first.
 * 2. if no value set, use `prefer-color-scheme` defined.
 * 3. can change theme by toggle button.
 * 4. the change must be saved in localStorage for later theme decision.
 */
export default defineComponent({
  name: 'ThemeButton',

  setup() {
    const THEME_STORAGE_KEY = 'theme';

    const localStorage = new LocalStorage();

    const isDarkTheme = ref(true);

    // FIXME: how to set it and pass into `setTheme` fn.
    const theme = computed({
      get: () => getTheme() || (isDarkTheme.value ? 'dark-theme' : 'light-theme'),
      set: val => {
        isDarkTheme.value = val === 'dark-theme';
        localStorage.save(THEME_STORAGE_KEY, val);
      },
    });

    onMounted(() => {
      const initUserTheme = (getTheme() as Theme) || getMediaPreference();
      setTheme(initUserTheme);
    });

    const toggleTheme = () => {
      isDarkTheme.value = !isDarkTheme.value;
      setTheme(isDarkTheme.value ? 'dark-theme' : 'light-theme');
    };

    const getTheme = () => localStorage.load(THEME_STORAGE_KEY);

    const setTheme = (newTheme: Theme) => {
      localStorage.save(THEME_STORAGE_KEY, newTheme);
      document.documentElement.className = newTheme;
    };

    return {
      isDarkTheme,
      toggleTheme,
      theme,
    };
  },
});
