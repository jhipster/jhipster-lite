import { RestModulesRepository } from '@/module/secondary/RestModulesRepository';
import { RestProjectHistory } from '@/module/secondary/RestProjectHistory';
import { RestModuleProperties } from '@/module/secondary/RestModuleProperties';
import { RestModules } from '@/module/secondary/RestModules';
import { dataBackendResponse, stubAxiosHttp } from '../../http/AxiosHttpStub';
import { defaultLandscape, defaultModules, defaultModuleToApply, defaultProjectHistory } from '../domain/Modules.fixture';
import { RestLandscape } from '@/module/secondary/RestLandscape';
import { RestLandscapeDependency, RestLandscapeModule } from '@/module/secondary/RestLandscapeModule';
import { RestLandscapeFeature } from '@/module/secondary/RestLandscapeFeature';
import { ModuleSlug } from '@/module/domain/ModuleSlug';

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

    await repository.apply(new ModuleSlug('module'), defaultModuleToApply());

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

    expect(appliedModules).toEqual(defaultProjectHistory());
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
          landscapeModule('vue', 'Add vue', [moduleDependency('init')]),
          landscapeModule('react', 'Add react', [moduleDependency('init')]),
          landscapeModule('angular', 'Add angular', [moduleDependency('init')]),
        ]),
        landscapeFeature('java-build-tools', [
          landscapeModule('maven', 'Add maven', [moduleDependency('init')]),
          landscapeModule('gradle', 'Add gradle', [moduleDependency('init')]),
        ]),
      ],
    },
    {
      elements: [
        landscapeModule('java-base', 'Add base java classes', [featureDependency('java-build-tools')]),
        landscapeModule('spring-boot', 'Add spring boot core', [featureDependency('java-build-tools')]),
        landscapeFeature('ci', [
          landscapeModule('gitlab-maven', 'Add simple gitlab ci for maven', [moduleDependency('maven')]),
          landscapeModule('gitlab-gradle', 'Add simple gitlab ci for gradle', [moduleDependency('gradle')]),
        ]),
      ],
    },
    {
      elements: [
        landscapeFeature('jpa', [landscapeModule('postgresql', 'Add PostGreSQL', [moduleDependency('spring-boot')])]),
        landscapeFeature('spring-mvc', [
          landscapeModule('spring-boot-tomcat', 'Add Tomcat', [moduleDependency('spring-boot')]),
          landscapeModule('spring-boot-undertow', 'Add Undertow', [moduleDependency('spring-boot')]),
        ]),
        landscapeModule('bean-validation-test', 'Add bean validation test tools', [moduleDependency('spring-boot')]),
        landscapeModule('build', 'Add build information', [featureDependency('ci')]),
      ],
    },
    {
      elements: [
        landscapeModule('dummy-feature', 'Add dummy feature', [featureDependency('spring-mvc'), moduleDependency('bean-validation-test')]),
        landscapeModule('liquibase', 'Add liquibase', [featureDependency('jpa')]),
      ],
    },
  ],
});

const landscapeModule = (slug: string, operation: string, dependencies?: RestLandscapeDependency[]): RestLandscapeModule => ({
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

const moduleDependency = (slug: string): RestLandscapeDependency => ({ type: 'MODULE', slug });
const featureDependency = (slug: string): RestLandscapeDependency => ({ type: 'FEATURE', slug });

const restModuleHistory = (): RestProjectHistory => ({
  modules: [{ slug: 'spring-cucumber' }],
  properties: appliedModuleProperties(),
});

const appliedModuleProperties = (): {} => {
  return { baseName: 'settedbase' };
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
