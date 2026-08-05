import {
  AngularNodeAppEngine,
  createNodeRequestHandler,
  isMainModule,
  writeResponseToNodeResponse,
} from '@angular/ssr/node';
import express from 'express';
import { join } from 'node:path';

const browserDistFolder = join(import.meta.dirname, '../browser');

const app = express();
const angularApp = new AngularNodeAppEngine();

/**
 * Injeta window.__env no HTML para que o Angular client-side
 * leia API_URL e JWT_TOKEN a partir de variáveis de ambiente do container.
 */
function buildEnvScript(): string {
  const apiUrl = process.env['API_URL'] ?? 'http://localhost:8080';
  const jwtToken = process.env['JWT_TOKEN'] ?? '';
  return `<script>window.__env = { API_URL: "${apiUrl}", JWT_TOKEN: "${jwtToken}" };</script>`;
}

/**
 * Serve static files from /browser
 */
app.use(
  express.static(browserDistFolder, {
    maxAge: '1y',
    index: false,
    redirect: false,
  }),
);

/**
 * Handle all other requests by rendering the Angular application.
 * Injeta o script de env antes do </head>.
 */
app.use((req, res, next) => {
  angularApp
    .handle(req)
    .then(async (response) => {
      if (!response) return next();

      // Lê o HTML renderizado, injeta o env-script e devolve
      const text = await response.text();
      const patched = text.replace('</head>', `${buildEnvScript()}</head>`);
      const patched_response = new Response(patched, {
        status: response.status,
        headers: response.headers,
      });
      writeResponseToNodeResponse(patched_response, res);
    })
    .catch(next);
});

if (isMainModule(import.meta.url) || process.env['pm_id']) {
  const port = process.env['PORT'] || 4000;
  app.listen(port, (error) => {
    if (error) throw error;
    console.log(`Node Express server listening on http://localhost:${port}`);
    console.log(`API_URL: ${process.env['API_URL'] ?? 'http://localhost:8080 (padrão)'}`);
  });
}

export const reqHandler = createNodeRequestHandler(app);
