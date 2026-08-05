#!/usr/bin/env bash
# Rising HelpDesk — Script de inicialização via Docker Compose
# =============================================================================
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
ENV_FILE="$ROOT_DIR/.env"
COMPOSE_FILE="$ROOT_DIR/docker/docker-compose.yaml"

# -----------------------------------------------------------------------------
# Cores para output
# -----------------------------------------------------------------------------
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color

log()    { echo -e "${CYAN}[Rising HelpDesk]${NC} $*"; }
ok()     { echo -e "${GREEN}[OK]${NC} $*"; }
warn()   { echo -e "${YELLOW}[WARN]${NC} $*"; }
error()  { echo -e "${RED}[ERROR]${NC} $*" >&2; }

# -----------------------------------------------------------------------------
# Verificar dependências
# -----------------------------------------------------------------------------
check_deps() {
  log "Verificando dependências..."
  for cmd in docker docker-compose; do
    if ! command -v "$cmd" &>/dev/null; then
      # docker compose v2 (plugin)
      if [ "$cmd" = "docker-compose" ] && docker compose version &>/dev/null 2>&1; then
        ok "docker compose (plugin v2) encontrado."
      else
        error "Dependência não encontrada: $cmd"
        error "Instale o Docker: https://docs.docker.com/get-docker/"
        exit 1
      fi
    else
      ok "$cmd encontrado."
    fi
  done
}

# -----------------------------------------------------------------------------
# Verificar / criar .env
# -----------------------------------------------------------------------------
setup_env() {
  if [ ! -f "$ENV_FILE" ]; then
    warn "Arquivo .env não encontrado. Criando a partir de .env.example..."
    if [ -f "$ROOT_DIR/.env.example" ]; then
      cp "$ROOT_DIR/.env.example" "$ENV_FILE"
      warn "Arquivo .env criado. Edite-o com suas configurações antes de continuar:"
      warn "  nano $ENV_FILE"
      echo ""
      read -r -p "Pressione ENTER após editar o .env para continuar, ou Ctrl+C para cancelar..."
    else
      error ".env.example não encontrado. Abortando."
      exit 1
    fi
  else
    ok "Arquivo .env encontrado."
  fi
}

# -----------------------------------------------------------------------------
# Escolher binário correto do compose
# -----------------------------------------------------------------------------
compose_cmd() {
  if docker compose version &>/dev/null 2>&1; then
    echo "docker compose"
  else
    echo "docker-compose"
  fi
}

# -----------------------------------------------------------------------------
# Iniciar serviços
# -----------------------------------------------------------------------------
start_services() {
  local compose
  compose=$(compose_cmd)

  log "Iniciando todos os serviços..."
  $compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" pull --quiet
  $compose -f "$COMPOSE_FILE" --env-file "$ENV_FILE" up -d --build

  ok "Todos os serviços iniciados!"
  echo ""
  echo -e "${CYAN}┌─────────────────────────────────────────────────────┐${NC}"
  echo -e "${CYAN}│        Rising HelpDesk — Serviços disponíveis       │${NC}"
  echo -e "${CYAN}├─────────────────────────────────────────────────────┤${NC}"
  echo -e "${CYAN}│${NC}  Gateway          →  http://localhost:8080          ${CYAN}│${NC}"
  echo -e "${CYAN}│${NC}  API (direto)     →  http://localhost:8001          ${CYAN}│${NC}"
  echo -e "${CYAN}│${NC}  Prometheus       →  http://localhost:9090          ${CYAN}│${NC}"
  echo -e "${CYAN}│${NC}  Grafana          →  http://localhost:3000          ${CYAN}│${NC}"
  echo -e "${CYAN}│${NC}  MySQL            →  localhost:3306                 ${CYAN}│${NC}"
  echo -e "${CYAN}│${NC}  Redis            →  localhost:6379                 ${CYAN}│${NC}"
  echo -e "${CYAN}└─────────────────────────────────────────────────────┘${NC}"
  echo ""
  log "Para acompanhar os logs: $compose -f $COMPOSE_FILE logs -f"
  log "Para parar os serviços: $compose -f $COMPOSE_FILE down"
}

# -----------------------------------------------------------------------------
# Tratar argumentos
# -----------------------------------------------------------------------------
ACTION="${1:-start}"

case "$ACTION" in
  start)
    check_deps
    setup_env
    start_services
    ;;
  stop)
    log "Parando todos os serviços..."
    compose_cmd=$(compose_cmd)
    $compose_cmd -f "$COMPOSE_FILE" down
    ok "Serviços parados."
    ;;
  restart)
    log "Reiniciando todos os serviços..."
    compose_cmd=$(compose_cmd)
    $compose_cmd -f "$COMPOSE_FILE" down
    start_services
    ;;
  logs)
    compose_cmd=$(compose_cmd)
    $compose_cmd -f "$COMPOSE_FILE" logs -f
    ;;
  status)
    compose_cmd=$(compose_cmd)
    $compose_cmd -f "$COMPOSE_FILE" ps
    ;;
  *)
    echo "Uso: $0 [start|stop|restart|logs|status]"
    echo ""
    echo "  start    — Inicia todos os serviços (padrão)"
    echo "  stop     — Para todos os serviços"
    echo "  restart  — Reinicia todos os serviços"
    echo "  logs     — Exibe logs em tempo real"
    echo "  status   — Exibe status dos containers"
    exit 1
    ;;
esac
