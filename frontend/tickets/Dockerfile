# Build stage
FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build

# Runtime stage
FROM node:20-alpine
WORKDIR /app
COPY --from=builder /app/dist/tickets ./dist/tickets
EXPOSE 4000
CMD ["node", "dist/tickets/server/server.mjs"]
