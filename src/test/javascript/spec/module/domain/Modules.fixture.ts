import { Modules } from '@/module/domain/Modules';
import { ModuleToApply } from '@/module/domain/ModuleToApply';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import sinon, { SinonStub } from 'sinon';
import { Project } from '@/module/domain/Project';

export interface ModulesRepositoryStub extends ModulesRepository {
  list: SinonStub;
  apply: SinonStub;
  appliedModules: SinonStub;
  download: SinonStub;
}

export const stubModulesRepository = (): ModulesRepositoryStub =>
  ({
    list: sinon.stub(),
    apply: sinon.stub(),
    appliedModules: sinon.stub(),
    download: sinon.stub(),
  } as ModulesRepositoryStub);

export const defaultModules = (): Modules =>
  new Modules([
    {
      name: 'Spring',
      modules: [
        {
          slug: 'spring-cucumber',
          description: 'Add cucumber to the application',
          properties: [
            {
              type: 'STRING',
              mandatory: true,
              key: 'baseName',
              description: 'Application base name',
              example: 'jhipster',
            },
            {
              type: 'BOOLEAN',
              mandatory: false,
              key: 'optionalBoolean',
            },
            {
              type: 'INTEGER',
              mandatory: false,
              key: 'optionalInteger',
            },
          ],
          tags: ['server'],
        },
        {
          slug: 'banner',
          description: 'Add a banner to the application',
          properties: [],
          tags: [],
        },
      ],
    },
  ]);

export const defaultModuleToApply = (): ModuleToApply => ({
  projectFolder: '/tmp/dummy',
  properties: defaultPropertiesToApply(),
});

const defaultPropertiesToApply = () => {
  return new Map<string, string | number | boolean>()
    .set('baseName', 'testproject')
    .set('optionalBoolean', true)
    .set('optionalInteger', 42);
};

export const defaultProject = (): Project => ({
  filename: 'jhipster.zip',
  content: Uint8Array.from([]).buffer,
});
