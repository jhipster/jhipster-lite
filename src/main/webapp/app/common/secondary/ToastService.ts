import { NotificationService } from '@/common/domain/NotificationService';

export default class ToastService implements NotificationService {
  private toastRef: any;

  register(toast: any) {
    this.toastRef = toast;
  }

  success(message: string): void {
    this.toastRef.success(message);
  }

  error(message: string): void {
    console.log(this.toastRef);
    this.toastRef.error(message);
  }
}
