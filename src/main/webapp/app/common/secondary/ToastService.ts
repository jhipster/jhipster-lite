import { NotificationService } from '@/common/domain/NotificationService';

export default class ToastService implements NotificationService {
  toastRef: any;

  register(toast: any) {
    this.toastRef = toast;
  }

  success(message: string): void {
    this.toastRef.success(message);
  }

  error(message: string): void {
    this.toastRef.error(message);
  }
}
