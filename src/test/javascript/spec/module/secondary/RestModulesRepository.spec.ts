import { RestModulesRepository } from '@/module/secondary/RestModulesRepository';
import { RestProjectHistory } from '@/module/secondary/RestProjectHistory';
import { RestModuleProperties } from '@/module/secondary/RestModuleProperties';
import { RestModules } from '@/module/secondary/RestModules';
import { dataBackendResponse, stubAxiosHttp } from '../../http/AxiosHttpStub';
import { defaultLandscape, defaultModules, defaultModuleToApply } from '../domain/Modules.fixture';
import { RestLandscape } from '@/module/secondary/RestLandscape';
import { RestLandscapeModule } from '@/module/secondary/RestLandscapeModule';
import { RestLandscapeFeature } from '@/module/secondary/RestLandscapeFeature';

describe('Rest modules repository', () => {
  it('Should list modules using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves(dataBackendResponse(restModules()));

    const modules = await repository.list();

    expect(modules).toEqual(defaultModules());
  });

  it('Should list module without properties', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves(dataBackendResponse(restModulesWithoutProperties()));

    const modules = await repository.list();

    expect(modules.categories[0].modules[0].properties).toEqual([]);
  });

  it('Should get landscape using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves(dataBackendResponse(restLandscape()));

    const landscape = await repository.landscape();

    expect(landscape).toEqual(defaultLandscape());
  });

  it('Should apply modules using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.post.resolves(dataBackendResponse(null));

    await repository.apply('module', defaultModuleToApply());

    expect(axiosInstance.post.calledOnce).toBe(true);
  });

  it('Should get empty module history using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves(dataBackendResponse({}));

    const appliedModules = await repository.history('test');

    expect(appliedModules).toEqual({
      modules: [],
      properties: [],
    });
  });

  it('Should get module history using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves(dataBackendResponse(restModuleHistory()));

    const appliedModules = await repository.history('test');

    expect(appliedModules).toEqual({
      modules: ['spring-cucumber'],
      properties: [
        {
          key: 'key',
          value: 'value',
        },
      ],
    });
  });

  it('Should format project using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.post.resolves(dataBackendResponse(null));

    await repository.format('path/to/project');

    expect(axiosInstance.post.calledOnce).toBe(true);
  });

  it('Should download project using axios', async () => {
    const axiosInstance = stubAxiosHttp();
    const repository = new RestModulesRepository(axiosInstance);
    axiosInstance.get.resolves({ headers: { 'x-suggested-filename': 'file.zip' }, data: [1, 2, 3] });

    const project = await repository.download('path/to/project');

    expect(axiosInstance.get.lastCall.args[0]).toBe('/api/projects?path=path/to/project');
    expect(project.filename).toBe('file.zip');
    expect(project.content).toEqual([1, 2, 3]);
  });
});

const restModules = (): RestModules => ({
  categories: [
    {
      name: 'Spring',
      modules: [
        {
          slug: 'spring-cucumber',
          description: 'Add cucumber to the application',
          properties: restModuleProperties(),
          tags: ['server'],
        },
        {
          slug: 'banner',
          description: 'Add a banner to the application',
        },
      ],
    },
  ],
});

const restModulesWithoutProperties = (): RestModules => ({
  categories: [
    {
      name: 'Spring',
      modules: [
        {
          slug: 'spring-cucumber',
          description: 'Add cucumber to the application',
        },
      ],
    },
  ],
});

const restLandscape = (): RestLandscape => ({
  levels: [
    {
      elements: [landscapeModule('infinitest', 'Add infinitest filters'), landscapeModule('init', 'Add some initial tools')],
    },
    {
      elements: [
        landscapeFeature('client', [
          landscapeModule('vue', 'Add vue', ['init']),
          landscapeModule('react', 'Add react', ['init']),
          landscapeModule('angular', 'Add angular', ['init']),
        ]),
        landscapeFeature('java-build-tools', [
          landscapeModule('maven', 'Add maven', ['init']),
          landscapeModule('gradle', 'Add gradle', ['init']),
        ]),
      ],
    },
    {
      elements: [
        landscapeModule('java-base', 'Add base java classes', ['java-build-tools']),
        landscapeModule('spring-boot', 'Add spring boot core', ['java-build-tools']),
      ],
    },
    {
      elements: [
        landscapeFeature('jpa', [
          landscapeModule('postgresql', 'Add PostGreSQL', ['spring-boot']),
          landscapeModule('mariadb', 'Add mariaDB', ['spring-boot']),
        ]),
        landscapeFeature('spring-mvc', [
          landscapeModule('springboot-tomcat', 'Add Tomcat', ['spring-boot']),
          landscapeModule('springboot-undertow', 'Add Undertow', ['spring-boot']),
        ]),
        landscapeModule('bean-validation-test', 'Add bean validation test tools', ['spring-boot']),
      ],
    },
    {
      elements: [landscapeModule('dummy-feature', 'Add dummy feature', ['spring-mvc', 'bean-validation-test'])],
    },
  ],
});

const landscapeModule = (slug: string, operation: string, dependencies?: string[]): RestLandscapeModule => ({
  type: 'MODULE',
  slug,
  operation,
  dependencies,
});

const landscapeFeature = (slug: string, modules: RestLandscapeModule[]): RestLandscapeFeature => ({
  type: 'FEATURE',
  slug,
  modules,
});

const restModuleHistory = (): RestProjectHistory => ({
  modules: [{ slug: 'spring-cucumber' }],
  properties: appliedModuleProperties(),
});

const appliedModuleProperties = (): {} => {
  return { key: 'value' };
};

const restModuleProperties = (): RestModuleProperties => ({
  definitions: [
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
});
