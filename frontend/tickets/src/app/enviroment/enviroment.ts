declare global {
  interface Window {
    __env?: {
      API_URL?: string;
      JWT_TOKEN?: string;
    };
  }
}

function getEnv(key: 'API_URL' | 'JWT_TOKEN', fallback: string): string {
  if (typeof window !== 'undefined' && window.__env?.[key]) {
    return window.__env[key]!;
  }
  return fallback;
}

export const Environment = {
  production: false,
  apiUrl: getEnv('API_URL', 'http://localhost:8080'),
  // JWT hardcoded — substitua quando a autenticação estiver pronta
  jwtToken: getEnv(
    'JWT_TOKEN',
    'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIwMDAwMDAwMC0wMDAwLTAwMDAtMDAwMC0wMDAwMDAwMDAwMDEiLCJuYW1lIjoiSm9yZ2UgVm9uIERvZWxsaW5nZXIiLCJpYXQiOjE3NTQ0MjQ4MDB9.placeholder'
  ),
};
