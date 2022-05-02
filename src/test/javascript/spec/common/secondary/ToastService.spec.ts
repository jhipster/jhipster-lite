import sinon from 'sinon';

import ToastService from '@/common/secondary/ToastService';

describe('ToastService', () => {
  it('should register a toast instance', () => {
    const toast = {
      success: sinon.stub(),
      error: sinon.stub(),
    };
    const toastService = new ToastService();
    toastService.register(toast);

    expect(toastService.toastRef).toBeTruthy();
  });

  it('should trigger a success toast', () => {
    const toast = {
      success: sinon.stub(),
      error: sinon.stub(),
    };
    const toastService = new ToastService();
    toastService.register(toast);
    toastService.success('success message');

    expect(toast.success.called).toBe(true);
  });

  it('should trigger a failure toast', () => {
    const toast = {
      success: sinon.stub(),
      error: sinon.stub(),
    };
    const toastService = new ToastService();
    toastService.register(toast);
    toastService.error('error message');

    expect(toast.error.called).toBe(true);
  });
});
