import { Port } from '@/springboot/domain/Port';
import { Folder } from '@/springboot/domain/Folder';
import { PackageName } from '@/springboot/domain/PackageName';
import { BaseName } from '@/springboot/domain/BaseName';
import { ProjectName } from '@/springboot/domain/ProjectName';

export interface Project {
  folder: Folder;
  baseName?: BaseName;
  projectName?: ProjectName;
  packageName?: PackageName;
  serverPort?: Port;
}
