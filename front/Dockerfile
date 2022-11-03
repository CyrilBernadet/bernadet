FROM node:alpine AS front-build
WORKDIR /front
COPY . .
RUN npm ci && npm run build

FROM nginx:1.15-alpine
COPY --from=front-build front/dist/front /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/nginx.conf
EXPOSE 443
