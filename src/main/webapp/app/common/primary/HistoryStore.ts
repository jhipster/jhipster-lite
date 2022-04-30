import { defineStore } from 'pinia';
import { History } from '@/common/domain/History';
import { Service } from '@/common/domain/Service';

const emptyHistory = (): History => ({
  services: [],
});

export const useHistoryStore = defineStore('HistoryStore', {
  state: () => {
    return {
      history: emptyHistory(),
    };
  },

  getters: {
    getHistory: state => state.history,
    hasCalledService:
      state =>
      (service: Service): boolean =>
        state.history.services.includes(service),
  },

  actions: {
    async setHistory(history: History) {
      this.history = history;
    },
  },
});
