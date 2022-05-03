import { NotificationService } from '@/common/domain/NotificationService';
import { ToastVue } from '@/common/primary/toast';

export default class ToastService implements NotificationService {
  toastRef: typeof ToastVue | undefined;

  register(toast: typeof ToastVue) {
    this.toastRef = toast;
  }

  success(message: string): void {
    this.toastRef?.success(message);
  }

  error(message: string): void {
    this.toastRef?.error(message);
  }
}
