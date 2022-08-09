export interface RestAppliedModule {
  slug: string;
}

export const mapAppliedModules = (modules: RestAppliedModule[] | undefined): string[] => {
  if (modules === undefined) {
    return [];
  }

  return modules.map(module => module.slug);
};
