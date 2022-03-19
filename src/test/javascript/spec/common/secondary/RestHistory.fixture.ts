import { RestHistory } from '../../../../../main/webapp/app/common/secondary/RestHistory';

export const createRestHistory = (restHistory?: Partial<RestHistory>): RestHistory => ({
  serviceIds: ['init', 'java-base', 'maven'],
  ...restHistory,
});
