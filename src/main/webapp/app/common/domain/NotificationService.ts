export interface NotificationService {
  register(toast: any): void;

  error(message: string): void;
  success(message: string): void;
}
