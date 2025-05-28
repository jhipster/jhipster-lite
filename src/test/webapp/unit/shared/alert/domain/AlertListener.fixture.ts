import { AlertListener } from '@/shared/alert/domain/AlertListener';
import type { MockedFunction } from 'vitest';
import { vi } from 'vitest';

export interface AlertListenerFixture extends AlertListener {
  onSuccess: MockedFunction<any>;
  onError: MockedFunction<any>;
}

export const stubAlertListener = (): AlertListenerFixture => ({
  onSuccess: vi.fn(),
  onError: vi.fn(),
});
