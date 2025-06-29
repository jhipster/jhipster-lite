import { provideHttpClient } from '@angular/common/http';
import { enableProdMode, importProvidersFrom } from '@angular/core';
import { bootstrapApplication } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { provideRouter } from '@angular/router';

import { App } from './app/app';
import { routes } from './app/app.route';

import { environment } from './environments/environment';

if (environment.production) {
  enableProdMode();
}

bootstrapApplication(App, {
  providers: [provideHttpClient(), provideRouter(routes), importProvidersFrom([BrowserAnimationsModule])],
}).catch((err: unknown) => console.error(err));
