import { AlertBus } from '@/shared/alert/domain/AlertBus';
import { AlertMessage } from '@/shared/alert/domain/AlertMessage';
import { AlertType } from '@/shared/alert/infrastructure/secondary/AlertType';
import { Emitter } from 'mitt';

export class MittAlertBus implements AlertBus {
  constructor(private emitter: Emitter<any>) {}

  success(alert: AlertMessage): void {
    this.emitter.emit(AlertType.SUCCESS, alert);
  }

  error(alert: AlertMessage): void {
    this.emitter.emit(AlertType.ERROR, alert);
  }
}
