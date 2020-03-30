FROM node:10-alpine as angular
WORKDIR /app
COPY emprestimo-app/package.json /app
RUN npm install --silent
COPY ./emprestimo-app ./
RUN npm run build --prod

FROM nginx:alpine
VOLUME [ "/var/cache/nginx" ]
COPY --from=angular app/dist/emprestimo-app /usr/share/nginx/html

COPY emprestimo-app/config/nginx.conf /etc/nginx/conf.d/default.conf