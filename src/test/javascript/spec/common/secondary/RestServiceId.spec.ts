import { Service } from '@/common/domain/Service';
import { toService } from '@/common/secondary/RestServiceId';

describe('RestServiceId', () => {
  it('should convert to Service', () => {
    expect(toService('init')).toEqual<Service>(Service.INITIALIZATION);
    expect(toService('maven')).toEqual<Service>(Service.MAVEN);
    expect(toService('java-base')).toEqual<Service>(Service.JAVA_BASE);
    expect(toService('springboot')).toEqual<Service>(Service.SPRINGBOOT);
    expect(toService('springboot-tomcat')).toEqual<Service>(Service.SPRINGBOOT_MVC_WITH_TOMCAT);
    expect(toService('angular')).toEqual<Service>(Service.ANGULAR);
    expect(toService('react')).toEqual<Service>(Service.REACT);
    expect(toService('vue')).toEqual<Service>(Service.VUE);
    expect(toService('frontend-maven-plugin')).toEqual<Service>(Service.FRONTEND_MAVEN_PLUGIN);
    expect(toService('beer')).toEqual<Service>(Service.UNKNOWN);
  });
});
