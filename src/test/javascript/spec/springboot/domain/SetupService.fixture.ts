import { SetupService } from '../../../../../main/webapp/app/springboot/domain/SetupService';

export interface SetupServiceFixture extends SetupService {}

export const stubSetupService = (): SetupServiceFixture => ({});
