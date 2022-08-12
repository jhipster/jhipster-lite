import { Modules } from '@/module/domain/Modules';
import { ModuleToApply } from '@/module/domain/ModuleToApply';
import { ModulesRepository } from '@/module/domain/ModulesRepository';
import sinon, { SinonStub } from 'sinon';
import { Project } from '@/module/domain/Project';
import { ModulePropertyValue, ProjectHistory } from '@/module/domain/ProjectHistory';
import { ModulePropertyValueType } from '@/module/domain/ModuleProperties';
import { Landscape } from '@/module/domain/landscape/Landscape';
import { LandscapeModule } from '@/module/domain/landscape/LandscapeModule';
import { LandscapeFeature } from '@/module/domain/landscape/LandscapeFeature';

export interface ModulesRepositoryStub extends ModulesRepository {
  list: SinonStub;
  landscape: SinonStub;
  apply: SinonStub;
  history: SinonStub;
  format: SinonStub;
  download: SinonStub;
}

export const stubModulesRepository = (): ModulesRepositoryStub =>
  ({
    list: sinon.stub(),
    landscape: sinon.stub(),
    apply: sinon.stub(),
    history: sinon.stub(),
    format: sinon.stub(),
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
  commit: true,
  properties: defaultPropertiesToApply(),
});

const defaultPropertiesToApply = () => {
  return new Map<string, ModulePropertyValueType>().set('baseName', 'testproject').set('optionalBoolean', true).set('optionalInteger', 42);
};

export const moduleHistory = (): ProjectHistory => ({
  modules: ['spring-cucumber'],
  properties: appliedModuleProperties(),
});

const appliedModuleProperties = (): ModulePropertyValue[] => {
  return [{ key: 'baseName', value: 'settedbase' }];
};

export const defaultProject = (): Project => ({
  filename: 'jhipster.zip',
  content: Uint8Array.from([]).buffer,
});

export const defaultLandscape = (): Landscape => ({
  levels: [
    {
      elements: [
        new LandscapeModule('infinitest', 'Add infinitest filters', []),
        new LandscapeModule('init', 'Add some initial tools', []),
      ],
    },
    {
      elements: [
        new LandscapeFeature('client', [
          new LandscapeModule('vue', 'Add vue', ['init']),
          new LandscapeModule('react', 'Add react', ['init']),
          new LandscapeModule('angular', 'Add angular', ['init']),
        ]),
        new LandscapeFeature('java-build-tools', [
          new LandscapeModule('maven', 'Add maven', ['init']),
          new LandscapeModule('gradle', 'Add gradle', ['init']),
        ]),
      ],
    },
    {
      elements: [
        new LandscapeModule('java-base', 'Add base java classes', ['java-build-tools']),
        new LandscapeModule('spring-boot', 'Add spring boot core', ['java-build-tools']),
      ],
    },
    {
      elements: [
        new LandscapeFeature('jpa', [
          new LandscapeModule('postgresql', 'Add PostGreSQL', ['spring-boot']),
          new LandscapeModule('mariadb', 'Add mariaDB', ['spring-boot']),
        ]),
        new LandscapeFeature('spring-mvc', [
          new LandscapeModule('springboot-tomcat', 'Add Tomcat', ['spring-boot']),
          new LandscapeModule('springboot-undertow', 'Add Undertow', ['spring-boot']),
        ]),
        new LandscapeModule('bean-validation-test', 'Add bean validation test tools', ['spring-boot']),
      ],
    },
    {
      elements: [new LandscapeModule('dummy-feature', 'Add dummy feature', ['spring-mvc', 'bean-validation-test'])],
    },
  ],
});
